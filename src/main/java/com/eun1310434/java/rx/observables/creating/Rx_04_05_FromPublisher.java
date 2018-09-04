/*==================================================================================================
□ INFORMATION
○ Data : 04.Sep.2018
○ Mail : eun1310434@gmail.com
○ WebPage : https://eun1310434.github.io/
○ Reference : RxJava 프로그래밍 P59
          
□ FUNCTION
○ 
   
□ Study
○ Creating(생성) : Operators that originate new Observables.

○ From() : convert some other object or data structure into an Observable

○ fromPublisher()
- Publisher는 JAVA9의 표준인 Flow API의 일부
- Publisher Interface는 기존의 RxJava와 비교하면 패키지 이름이 다름
  Obervable -> io.reactivex
  Publisher -> org.reactivestreams
- Observable.create()와 만찬가지로 onNext()와 onComplete() Method를 calling.

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
==================================================================================================*/
package com.eun1310434.java.rx.observables.creating;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;

import io.reactivex.Observable;

public class Rx_04_05_FromPublisher implements RxTest{
	
	@Override
	public void marbleDiagram() { 
		observableSet(lambda());
		observableSet(anonymous());
	}
	
	public void observableSet(Publisher<String> publisher){
		Observable<String> source = Observable.fromPublisher(publisher);
		source.subscribe(data->Log.i("lambda-> "+ data));
		CommonUtils.exampleComplete();
	}
	
	public Publisher<String> lambda() { 
		CommonUtils.exampleStart("01) lambda");
		Publisher<String> publisher = (Subscriber<? super String> s) -> { 
			s.onNext("Hello Observable.fromPublisher()::lambda");
			s.onNext("A");
			s.onNext("B");
			s.onComplete();
		};
		return publisher;
	}
	
	public Publisher<String> anonymous() { 
		CommonUtils.exampleStart("02) anonymous");
		Publisher<String> publisher = new Publisher<String>() {
			@Override
			public void subscribe(Subscriber<? super String> s) {
				s.onNext("Hello Observable.fromPublisher()::anonymous");
				s.onNext("A");
				s.onNext("B");
				s.onComplete();				
			}
		};
		return publisher;
	}
	
	public static void main(String[] args) { 
		Rx_04_05_FromPublisher test = new Rx_04_05_FromPublisher();
		test.marbleDiagram();
	}
}
