/*==================================================================================================
□ INFORMATION
○ Data : 24.08.2018
○ Mail : eun1310434@naver.com
○ WebPage : https://eun1310434.github.io/
○ Reference
- RxJava 프로그래밍 P68

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

○ AsyncSubject
- 구독자가 구독한 시점의  마지막값을 넘겨줌
==================================================================================================*/
package com.eun1310434.java.rx.subject;

import com.eun1310434.java.rx.common.CommonUtils;
import static com.eun1310434.java.rx.common.Shape.*;

import io.reactivex.Observable;
import io.reactivex.subjects.AsyncSubject;

public class Rx_01_00_AsyncSubjectClass {

	public void marbleDiagram() {
		// 1. 일반적인 Subject 선언
		CommonUtils.exampleStart("01) marbleDiagram()");

		AsyncSubject<String> subject = AsyncSubject.create();
		subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
		// 선언이전에 구독을 하여도 마지막 데이터 BLUE만 출력
		subject.onNext(RED);
		subject.onNext(GREEN);
		subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
		// 선언이전에 구독을 하여도 마지막 데이터 BLUE만 출력
		subject.onNext(BLUE);
		subject.onNext(PUPPLE);
		subject.subscribe(data -> System.out.println("Subscriber #3 => " + data));
		subject.onComplete();

		CommonUtils.exampleComplete();
	}

	public void asSubscriber() {
		// 2. Subscriber를 통한 Process
		CommonUtils.exampleStart("02) asSubscriber()");
		Float[] temperature = { 10.1f, 13.4f, 12.5f };
		Observable<Float> source = Observable.fromArray(temperature);

		AsyncSubject<Float> subject = AsyncSubject.create();
		subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
		// 마지막 데이터 12.5f만 출력

		source.subscribe(subject);
		// public abstract class Subject<T> extends Observable<T> implements
		// Observer<T>

		CommonUtils.exampleComplete();
	}

	public void afterComplete() {
		// 3. Subscriber를 통한 Process
		CommonUtils.exampleStart("03) afterComplete()");

		AsyncSubject<Integer> subject = AsyncSubject.create();
		subject.onNext(10);
		subject.onNext(11);
		subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
		subject.onNext(12);
		subject.onComplete();
		// Observable과 마찬가지로 onComplete() 함수 호출 이후 onNext 이벤트는 무시
		subject.onNext(13);
		subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
		subject.subscribe(data -> System.out.println("Subscriber #3 => " + data));

		CommonUtils.exampleComplete();
	}

	public static void main(String[] args) {
		Rx_01_00_AsyncSubjectClass test = new Rx_01_00_AsyncSubjectClass();
		test.marbleDiagram();
		test.asSubscriber();
		test.afterComplete();
	}

}
