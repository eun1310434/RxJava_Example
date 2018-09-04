/*==================================================================================================
□ INFORMATION
○ Data : 04.Sep.2018
○ Mail : eun1310434@gmail.com
○ WebPage : https://eun1310434.github.io/
○ Reference : RxJava 프로그래밍 P82

□ FUNCTION
○ 
   
□ Study
● ConnectableObservable
- 하나의 데이터를 여러 구독자에게 동시에 전달 할 때 사용
- 기존의 subscribe() 함수 호출이 아닌 connect() 함수를 호출한 시점부터 
   subscribe() 함수를 호출한 구독자에게 데이터 발행
- Cold Observable -> Hot Observable
- Subject = Observable(:Data Source) + Subscriber(:Data Receiver)

○ Cold Observables
- subscribe() method를 calling 하지 않으면 Data Publish가 이루어 지지 않음

○ Hot Observables
- subscribe() method를 calling 하지 않아도 Data Publish가 이루어짐
- Many Subscriber가 있을 때 useful
- 단, Subscribe를 Publish한 Point에서 부터 Data Publish가 발생
==================================================================================================*/
package com.eun1310434.java.rx.observables.connectableobservable;
import static com.eun1310434.java.rx.common.Shape.*;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.RxTest;
import java.util.concurrent.TimeUnit;


import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public class Rx_01_00_Connect implements RxTest{

	@Override
	public void marbleDiagram() { 
		// 1. marbleDiagram
		CommonUtils.exampleStart("01) marbleDiagram");
		
		String[] dt = {RED, GREEN, BLUE}; 
		Observable<String> balls = Observable
				.interval(1000L, TimeUnit.MILLISECONDS) // parameter(시간, 시간단위)의 시간과 단위 간격으로 publish 함
				.map(Long::intValue)
				.map(i -> dt[i])
				.take(dt.length);
		
		ConnectableObservable<String> source = balls.publish();
		//Object를 create 하려면 publish() method를 Calling 함.
		
		source.subscribe(data -> System.out.println("Subscriber #1 => " + data)); 
		source.connect();
		source.subscribe(data -> System.out.println("Subscriber #2 => " + data)); 

		//subscribe() 가 아닌 connect()를 호출하는 시점부터 Data publish가 이루어짐.
		
		CommonUtils.sleep(2500);
		source.subscribe(data -> System.out.println("Subscriber #3 => " + data)); 
		CommonUtils.sleep(1000);
		
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		Rx_01_00_Connect test = new Rx_01_00_Connect();
		test.marbleDiagram();
	}

}
