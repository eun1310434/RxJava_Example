/*==================================================================================================
□ INFORMATION
  ○ Data : 07.Oct.2018
  ○ Mail : eun1310434@gmail.com
  ○ WebPage : https://eun1310434.github.io/
  ○ Reference
    - RxJava 프로그래밍 P198
    - http://rxmarbles.com/#switchMap

□ FUNCTION
  ○ 
   
□ Study
  ○ Schedulers.io()
    - 네트워크
    - 입/출력 작업
    - 계산 스케줄러와 다르게 기본으로 생성되는 스레드 개수가 다름
      * 계산 스케줄러는 CPU 개수 만큼 스레드를 생성하지만 IO스케쥴러는 필요할 때 마다 스레드 계속 생성
 
  ○ JSON 데이터 예
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
				//Schedulers.io() : 네트워크, 입/출력 작업
				//source : JSON 데이터 원본을 의미
		
		//원하고자 하는 정보인 temperature, city, country 각각에 map() 함수를 호출하여 파싱
		//데이터를 파싱하는 것은 정규 표현식을 활용함
		//JSON 정보의 내용이 <제목>:<정보> 형태를 띄고 있으므로 정규 표현식 패턴이 매우 간편
		//복잡한 내용을 파싱할 경우에는 Gson 등의 라이브러리를 활요하는 것이 좋음
		Observable<String> temperature = source.map(json -> parseTemperature(json));
		Observable<String> city = source.map(json -> parseCityName(json));
		Observable<String> country = source.map(json -> parseCountry(json));
		//Observable<String> country = source.map(this::parseCountry);
		
		CommonUtils.exampleStart();
		Observable
				.concat(temperature, city, country)
				//정보를 취합하기 위하여 concat을 호출
				.observeOn(Schedulers.newThread())
				//Schedulers.newThread()를 실행하여 원하는 정보는 나왔지만
				//REST API호출이 3번 발생하였기에 매우 비효율적
				.subscribe(Log::it);
		
		CommonUtils.sleep(1000);
	}

	private void useShare(){
		CommonUtils.exampleStart();

		
		
		Observable<String> source = Observable
				.just(URL + API_KEY)
				.map(url -> OkHttpHelper.getWithLog(url))
				.subscribeOn(Schedulers.io())
				.share()//share()는 ConnectableObervable 클래스의 publish와 refcount를 합한 것이다.
				//.publish().refCount()
				.observeOn(Schedulers.newThread());
		
		//ConnectableObervable 클래스
		//: 1개의 Observable을 여러 구독자가 공유하는 방식으로 차가운 Observable을 뜨거운 Observable로 변환
		
		source.map(this::parseTemperature).subscribe(Log::it);
		source.map(this::parseCityName).subscribe(Log::it);
		source.map(this::parseCountry).subscribe(Log::it);
		//이전의 Rx_03_01_JSON_WeatherMapV1
		//REST API호출이 3번 발생하였기에 매우 비효율적이었던 것을
		//.share()를 활용하여 .subscribe()함수를 호출하면 
		//Observable의 데이터가 다시 발행되기 때문에 서버의 RESTAPI를 호출하지 않아도 됩니다.
		
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
