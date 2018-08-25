/*==================================================================================================
□ INFORMATION
○ Data : 22.08.2018
○ Mail : eun1310434@naver.com
○ WebPage : https://eun1310434.github.io/
○ Reference
- RxJava 프로그래밍 P57
     
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

○ fromPublisher()
- Publisher는 JAVA9의 표준인 Flow API의 일부
- Publisher Interface는 기존의 RxJava와 비교하면 패키지 이름이 다름
  Obervable -> io.reactivex
  Publisher -> org.reactivestreams
- Observable.create()와 만찬가지로 onNext()와 onComplete() Method를 calling.
==================================================================================================*/
package com.eun1310434.java.rx.observable;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import com.eun1310434.java.rx.common.CommonUtils;

import io.reactivex.Observable;

public class Rx_Observable_08_FromPublisher {
	public void lambda() { 
		CommonUtils.exampleStart("01) lambda");
		Publisher<String> publisher = (Subscriber<? super String> s) -> { 
			s.onNext("Hello Observable.fromPublisher()-lambda");
			s.onNext("A");
			s.onNext("B");
			s.onComplete();
		};
		Observable<String> source = Observable.fromPublisher(publisher);
		source.subscribe(data->System.out.println("lambda-> "+ data));
		CommonUtils.exampleComplete();
	}
	
	public void anonymous() { 
		CommonUtils.exampleStart("02) anonymous");
		Publisher<String> publisher = new Publisher<String>() {
			@Override
			public void subscribe(Subscriber<? super String> s) {
				s.onNext("Hello Observable.fromPublisher()-anonymous");
				s.onNext("A");
				s.onNext("B");
				s.onComplete();				
			}
		};
		Observable<String> source = Observable.fromPublisher(publisher);
		source.subscribe(data->System.out.println("anonymous-> "+ data));
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		Rx_Observable_08_FromPublisher test = new Rx_Observable_08_FromPublisher();
		test.lambda();
		test.anonymous();
	}
}
