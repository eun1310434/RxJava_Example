/*==================================================================================================
�� INFORMATION
  �� Data : 07.Oct.2018
  �� Mail : eun1310434@gmail.com
  �� WebPage : https://eun1310434.github.io/
  �� Reference
    - RxJava ���α׷��� P198
    - http://rxmarbles.com/#switchMap

�� FUNCTION
  �� 
   
�� Study
  �� Schedulers.io()
    - ��Ʈ��ũ
    - ��/��� �۾�
    - ��� �����ٷ��� �ٸ��� �⺻���� �����Ǵ� ������ ������ �ٸ�
      * ��� �����ٷ��� CPU ���� ��ŭ �����带 ���������� IO�����췯�� �ʿ��� �� ���� ������ ��� ����
 
  �� JSON ������ ��
    {
    	"coord":{
    		"lon":-0.13,
    		"lat":51.51},
    		"weather":[
    			{
    				"id":800,
    				"main":"Clear",
    				"description":"clear sky",
    				"icon":"01d"
    			}
    		],
    		"base":"stations",
    		"main":{
    			"temp":284.48,
    			"pressure":1024,
    			"humidity":66,
    			"temp_min":283.15,
    			"temp_max":285.15
    		},
    		"visibility":10000,
    		"wind":{
    			"speed":2.6,
    			"deg":330
    		},
    		"clouds":{
    			"all":0
    		},"dt":1538907600,
    		"sys":{
    			"type":1,
    			"id":5091,
    			"message":0.0044,
    			"country":"GB",
    			"sunrise":1538892693,
    			"sunset":1538933039
    		},
    		"id":2643743,
    		"name":"London",
    		"cod":200
    	}
==================================================================================================*/
package com.eun1310434.java.rx.sample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.OkHttpHelper;
import com.eun1310434.java.rx.common.ScheduleTest;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static com.eun1310434.java.rx.common.CommonUtils.API_KEY;


public class Rx_03_00_JSON_WeatherMap implements ScheduleTest{
	

	private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=London&APPID=";
	
	@Override
	public void run() {
		//useConcat();
		useShare();
	}
	
	private void useConcat(){
		Observable<String> source = Observable
				.just(URL + API_KEY)
				.map(url -> OkHttpHelper.getWithLog(url))
				.subscribeOn(Schedulers.io());
				//Schedulers.io() : ��Ʈ��ũ, ��/��� �۾�
				//source : JSON ������ ������ �ǹ�
		
		//���ϰ��� �ϴ� ������ temperature, city, country ������ map() �Լ��� ȣ���Ͽ� �Ľ�
		//�����͸� �Ľ��ϴ� ���� ���� ǥ������ Ȱ����
		//JSON ������ ������ <����>:<����> ���¸� ��� �����Ƿ� ���� ǥ���� ������ �ſ� ����
		//������ ������ �Ľ��� ��쿡�� Gson ���� ���̺귯���� Ȱ���ϴ� ���� ����
		Observable<String> temperature = source.map(json -> parseTemperature(json));
		Observable<String> city = source.map(json -> parseCityName(json));
		Observable<String> country = source.map(json -> parseCountry(json));
		//Observable<String> country = source.map(this::parseCountry);
		
		CommonUtils.exampleStart();
		Observable
				.concat(temperature, city, country)
				//������ �����ϱ� ���Ͽ� concat�� ȣ��
				.observeOn(Schedulers.newThread())
				//Schedulers.newThread()�� �����Ͽ� ���ϴ� ������ ��������
				//REST APIȣ���� 3�� �߻��Ͽ��⿡ �ſ� ��ȿ����
				.subscribe(Log::it);
		
		CommonUtils.sleep(1000);
	}

	private void useShare(){
		CommonUtils.exampleStart();

		
		
		Observable<String> source = Observable
				.just(URL + API_KEY)
				.map(url -> OkHttpHelper.getWithLog(url))
				.subscribeOn(Schedulers.io())
				.share()//share()�� ConnectableObervable Ŭ������ publish�� refcount�� ���� ���̴�.
				//.publish().refCount()
				.observeOn(Schedulers.newThread());
		
		//ConnectableObervable Ŭ����
		//: 1���� Observable�� ���� �����ڰ� �����ϴ� ������� ������ Observable�� �߰ſ� Observable�� ��ȯ
		
		source.map(this::parseTemperature).subscribe(Log::it);
		source.map(this::parseCityName).subscribe(Log::it);
		source.map(this::parseCountry).subscribe(Log::it);
		//������ Rx_03_01_JSON_WeatherMapV1
		//REST APIȣ���� 3�� �߻��Ͽ��⿡ �ſ� ��ȿ�����̾��� ����
		//.share()�� Ȱ���Ͽ� .subscribe()�Լ��� ȣ���ϸ� 
		//Observable�� �����Ͱ� �ٽ� ����Ǳ� ������ ������ RESTAPI�� ȣ������ �ʾƵ� �˴ϴ�.
		
		CommonUtils.sleep(1000);
		
	}
	
	private String parseTemperature(String json) { 
		return parse(json, "\"temp\":[0-9]*.[0-9]*");
	}

	private String parseCityName(String json) { 
		return parse(json, "\"name\":\"[a-zA-Z]*\"");
	}

	private String parseCountry(String json) { 
		return parse(json, "\"country\":\"[a-zA-Z]*\"");
	}
	
	private String parse(String json, String regex) { 
		Pattern pattern = Pattern.compile(regex);
		Matcher match = pattern.matcher(json);
		if (match.find()) {
			return match.group();
		}
		return "N/A";				
	}
	
	public static void main(String[] args) { 
		Rx_03_00_JSON_WeatherMap demo = new Rx_03_00_JSON_WeatherMap();
		demo.run();
	}	
}
