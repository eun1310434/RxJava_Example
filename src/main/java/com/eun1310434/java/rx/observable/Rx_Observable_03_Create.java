/*==================================================================================================
□ INFORMATION
○ Data : 22.08.2018
○ Mail : eun1310434@naver.com
○ WebPage : https://eun1310434.github.io/
○ Reference
- RxJava 프로그래밍 P50
     
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

○ create()
- Observable<T> create(ObservableInSubscribe<T> source)
- public interface ObservableOnSubscribe<T>{
	      void subscribe(ObservableEmitter<T> e) throws Exception;
  }
  
○ Observable.create()를 사용할 때 주의 할 점.
01) Observable이 구독 해지(dispose)되었을 때 등록된 콜백을 모두 해제해야 함.
     * 잠재적 메모리 누수(memory leak) 방지
02) 구독자가 구독하는 동안에만 onNext와 onComplete 이벤트를 호출해야 함.
03) 에러가 발생했을 때는 오직 onError이벤트로만 에러를 전달해야 함.
04) 배압(back pressure)을 직접 처리해야 함.
==================================================================================================*/
package com.eun1310434.java.rx.observable;

import com.eun1310434.java.rx.common.CommonUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

public class Rx_Observable_03_Create {
	public void lambda() {
		CommonUtils.exampleStart("01) Lambda");
		Observable<Integer> source = Observable.create((ObservableEmitter<Integer> emitter) -> {
			emitter.onNext(100);
			emitter.onNext(200);
			emitter.onNext(300);
			emitter.onComplete();
		});

		source.subscribe(System.out::print); // method reference java 8
		source.subscribe(data -> System.out.println("Result : " + data));
		CommonUtils.exampleComplete();
	}
	
	
	public void anonymous() {
		CommonUtils.exampleStart("02) Anonymous Print");
		Observable<Integer> source = Observable.create(new ObservableOnSubscribe<Integer>() {
			@Override
			public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
				// TODO Auto-generated method stub
				emitter.onNext(100);
				emitter.onNext(200);
				emitter.onNext(300);
				emitter.onComplete();
			}
		});
		
		source.subscribe(new Consumer<Integer>() {
			@Override
			public void accept(Integer data) throws Exception {
				System.out.println("Result : " + data);
			}
		});
		CommonUtils.exampleComplete();
	}

	public static void main(String[] args) {
		Rx_Observable_03_Create create = new Rx_Observable_03_Create();
		create.lambda();// <- Simple
		create.anonymous();
	}
}
