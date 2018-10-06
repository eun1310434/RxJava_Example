/*==================================================================================================
�� INFORMATION
�� Data : 25.Sep.2018
�� Mail : eun1310434@gmail.com
�� WebPage : https://eun1310434.github.io/
�� Reference : RxJava ���α׷��� P122
          
�� FUNCTION
�� 
   
�� Study
�� Creating(����) : Operators that originate new Observables.

�� Defer()
  - defer() : create an Observable that emits a particular item 
              or sequence of items repeatedly
  - ������ ����� �ϸ� �ش� ������ �� ����ִ��� Ȯ��(�� Ȯ�� ������ ���� ping Ȥ�� heart beat�̶�� ��)�ϴ� �ڵ忡 Ȱ��    
==================================================================================================*/
package com.eun1310434.java.rx.observables.creating;

import static com.eun1310434.java.rx.common.Shape.BLUE;
import static com.eun1310434.java.rx.common.Shape.GREEN;
import static com.eun1310434.java.rx.common.Shape.RED;

import java.util.concurrent.TimeUnit;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;
import com.eun1310434.java.rx.common.OkHttpHelper;

import io.reactivex.Observable;

public class Rx_08_00_Repeat implements RxTest{
	
	@Override
	public void marbleDiagram() { 
		String[] colors = {RED, GREEN, BLUE};
		ObservableSet("Repeat",colors,3);
		PingTest("PingTest",1,10000);//2�� �������� ������ ping ������
	}

	public void ObservableSet(String title,String[] array, int repeatTime){
		CommonUtils.exampleStart(title); // ���� �ð��� ǥ���ϴ� ��ƿ��Ƽ �޼���
		Observable<String> source = Observable
				.fromArray(array)
				.repeat(repeatTime);
		source.subscribe(data -> Log.it(data));
		CommonUtils.exampleComplete();
	}

	public void PingTest(String title, int repeatTime, int sleepTime) { 
		CommonUtils.exampleStart();
		String serverUrl = "https://api.github.com/zen";
		Observable.timer(repeatTime, TimeUnit.SECONDS)
			.map(val -> serverUrl)
			//.map(OkHttpHelper::get)
			.map(data-> OkHttpHelper.get(data))
			.repeat()
			.subscribe(res -> Log.it("Ping Result : " + res));	
		CommonUtils.sleep(sleepTime);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		Rx_08_00_Repeat demo = new Rx_08_00_Repeat();
		demo.marbleDiagram();
	}
}