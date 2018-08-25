/*==================================================================================================
□ INFORMATION
○ Data : 24.08.2018
○ Mail : eun1310434@naver.com
○ WebPage : https://eun1310434.github.io/
○ Reference
- RxJava 프로그래밍 P67

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

● Subject Class
- Subject = Observable(:Data Source) + Subscriber(:Data Receiver)
- 데이터 구독과 발행을 동시에 가능
- Cold Observable를 Hot Observable로 바꿈
* Cold Observable -> ( Subject Class ) -> Hot Observable
- Issue and Process the Data
- Kind
01) AsyncSubject
02) BehaviorSubject
03) PublishSubject
04) ReplaySubject
05) CompletableSubject
06) MaybeSubject
07) SingleSubject
08) UnicastSubject

○ BehaviorSubject
- 구독자가 구독한 시점의 최초 값을 넘겨줌
==================================================================================================*/
package com.eun1310434.java.rx.subject;


import static com.eun1310434.java.rx.common.Shape.*;

import com.eun1310434.java.rx.common.CommonUtils;

import io.reactivex.subjects.BehaviorSubject;

public class Rx_Subject_02_BehaviorSubject {
	
	public void behaviorSubjectTest() { 
		// 1. BehaviorSubject
		CommonUtils.exampleStart("01) behaviorSubjectTest()");
		BehaviorSubject<String> subject = BehaviorSubject.createDefault(PUPPLE);
		subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));// Subscriber - 1
		subject.onNext(RED);
		subject.onNext(GREEN);
		subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));// Subscriber - 2
		subject.onNext(BLUE);
		subject.onNext(PUPPLE);
		subject.subscribe(data -> System.out.println("Subscriber #3 => " + data));// Subscriber - 3
		subject.onComplete();
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		Rx_Subject_02_BehaviorSubject test = new Rx_Subject_02_BehaviorSubject();
		test.behaviorSubjectTest();
	}

}
