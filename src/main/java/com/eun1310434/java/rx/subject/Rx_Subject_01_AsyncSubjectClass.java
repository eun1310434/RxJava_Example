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
○ Subject Class
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
- Observable에서 issue한 last data를 get 할수 있는 Class.
- Only interest in Last data


==================================================================================================*/
package com.eun1310434.java.rx.subject;

import com.eun1310434.java.rx.common.CommonUtils;
import static com.eun1310434.java.rx.common.Shape.RED;
import static com.eun1310434.java.rx.common.Shape.GREEN;
import static com.eun1310434.java.rx.common.Shape.BLUE;


import io.reactivex.Observable;
import io.reactivex.subjects.AsyncSubject;

public class Rx_Subject_01_AsyncSubjectClass {
	
	public void marbleDiagram() { 
		// 1. 일반적인 Subject 선언
		CommonUtils.exampleStart("01) marbleDiagram()");
		
		AsyncSubject<String> subject = AsyncSubject.create();
		subject.subscribe(data -> System.out.println("Subscriber #1 => "+ data));
		// 선언이전에 구독을 하여도 마지막 데이터 BLUE만 출력
		subject.onNext(RED);
		subject.onNext(GREEN);
		subject.subscribe(data -> System.out.println("Subscriber #2 => "+ data));
		// 선언이전에 구독을 하여도 마지막 데이터 BLUE만 출력
		subject.onNext(BLUE);
		subject.onComplete();
		
		CommonUtils.exampleComplete();
	}
	
	public void asSubscriber() { 
		// 2. Subscriber를 통한 Process
		CommonUtils.exampleStart("02) asSubscriber()");
		Float[] temperature = { 10.1f, 13.4f, 12.5f  };
		Observable<Float> source = Observable.fromArray(temperature);
		
		AsyncSubject<Float> subject = AsyncSubject.create();
		subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));	
		//마지막 데이터 12.5f만 출력
		
		source.subscribe(subject);
		//public abstract class Subject<T> extends Observable<T> implements Observer<T>
		
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
		//Observable과 마찬가지로 onComplete() 함수 호출 이후 onNext 이벤트는 무시
		subject.onNext(13);
		subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
		subject.subscribe(data -> System.out.println("Subscriber #3 => " + data));
		
		CommonUtils.exampleComplete();		
	}
	
	public static void main(String[] args) { 
		Rx_Subject_01_AsyncSubjectClass test = new Rx_Subject_01_AsyncSubjectClass();
		test.marbleDiagram(); 
		test.asSubscriber();
		test.afterComplete();
	}

}
