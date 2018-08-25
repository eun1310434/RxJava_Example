/*==================================================================================================
□ INFORMATION
○ Data : 25.08.2018
○ Mail : eun1310434@naver.com
○ WebPage : https://eun1310434.github.io/
○ Reference
- RxJava 프로그래밍 P82

□ FUNCTION
○ 
   
□ Study
○ Data Source
- Observable
- Single
- Maybe
- Subject
- Completable

○ Data Receiver
- Subscriber : Observable과 연결할 때는 "subscribe()" Calling. 
- Observer : RxJava는 observer pattern을 implement. 
- Consumer : RxJava 2에서는 Comsumer를 Parameter로 사용

○ Cold Observables
- subscribe() method를 calling 하지 않으면 Data Publish가 이루어 지지 않음

○ Hot Observables
- subscribe() method를 calling 하지 않아도 Data Publish가 이루어짐
- Many Subscriber가 있을 때 useful
- 단, Subscribe를 Publish한 Point에서 부터 Data Publish가 발생

● ConnectableObservable
- Cold Observable를 Hot Observable로 Change
* Cold Observable -> ( Subject Class ) -> Hot Observable
- Subject = Observable(:Data Source) + Subscriber(:Data Receiver)
- Object를 create 하려면 publish() method를 Calling 함.
- subscribe() 가 아닌 connect()를 호출하는 시점부터 Data publish가 이루어짐.
==================================================================================================*/
package com.eun1310434.java.rx.connetableobservable;
import static com.eun1310434.java.rx.common.Shape.*;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.RxTest;
import java.util.concurrent.TimeUnit;


import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public class Rx_ConnectableObservable_01_Connect implements RxTest{

	@Override
	public void marbleDiagram() { 
		// 1. marbleDiagram
		CommonUtils.exampleStart("01) marbleDiagram");
		
		String[] dt = {RED, GREEN, BLUE}; 
		Observable<String> balls = Observable.interval(1000L, TimeUnit.MILLISECONDS) // parameter(시간, 시간단위)
				.map(Long::intValue)
				.map(i -> dt[i])
				.take(dt.length);
		
		ConnectableObservable<String> source = balls.publish();
		//Object를 create 하려면 publish() method를 Calling 함.
		
		source.subscribe(data -> System.out.println("Subscriber #1 => " + data)); 
		source.subscribe(data -> System.out.println("Subscriber #2 => " + data)); 
		source.connect();
		//subscribe() 가 아닌 connect()를 호출하는 시점부터 Data publish가 이루어짐.
		
		CommonUtils.sleep(1500);
		source.subscribe(data -> System.out.println("Subscriber #3 => " + data)); 
		CommonUtils.sleep(1500);
		source.subscribe(data -> System.out.println("Subscriber #4 => " + data)); 
		CommonUtils.sleep(1500);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		Rx_ConnectableObservable_01_Connect demo = new Rx_ConnectableObservable_01_Connect();
		demo.marbleDiagram();
	}

}
