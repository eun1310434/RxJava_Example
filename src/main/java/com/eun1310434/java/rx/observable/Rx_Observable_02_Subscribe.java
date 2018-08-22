/*==================================================================================================
�� INFORMATION
  �� Data : 18.08.2018
  �� Mail : eun1310434@naver.com
  �� WebPage : https://eun1310434.github.io/
  �� Reference
     - RxJava ���α׷��� P33
     
�� FUNCTION
  �� 
   
�� Study
  �� Definition of "subscribe()"
    - just() ���� ���丮 �Լ��� �������� �帧�� ������ �� ȣ���Ͽ� ������ ����

  �� Types of "subscribe()"
       01) Disposable subscribe()
            - Occured  "onError()" Event and Throw "OnErrorNotImplementedException()"
            - Pass over the event : "onNext()" and a "onComplete()"
       02) Disposable subscribe(Consumer<? super T> onNext)
            - Occured  "onError()" Event and Throw "OnErrorNotImplementedException()"
            - Handle "onNext()" Event 
            - Pass over the event : "onComplete()"
       03) Disposable subscribe(Consumer<? super T> onNext, Consumer<? super java.lang.Throwable> onError)
            - Handle "onError()" Event
            - Handle "onNext()" Event 
            - Pass over the event : "onComplete()"
       04) Disposable subscribe(Consumer<? super T> onNext, Consumer<? super java.lang.Throwable> onError, Action onComplete)
            - Handle "onError()" Event
            - Handle "onNext()" Event 
            - Handle "onComplete()" Event 

  �� Definition of "Disposable" Interface
      01) void dispose()
          - Observable���� �� �̻� �����͸� �������� �ʵ��� ������ �����ϴ� �Լ�
          - ���� Observable::onComplete()���� �� ������ Disposable::dipose()�� ������ �ʿ� ������ �ڵ������� ���� ���� ��.
            * Observable::onComplete -> Disposable::dipose -> ���� ����
      02) booelan isDisposed()
          - ���� Ȯ�� �Լ�
==================================================================================================*/
package com.eun1310434.java.rx.observable;


import com.eun1310434.java.rx.common.CommonUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class Rx_Observable_02_Subscribe {
	public <T> void withObserver() { 
		Observable<String> source = Observable.just("A", "B", "C");
		source.subscribe(new Observer<String>() {
			@Override
			public void onSubscribe(Disposable d) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onNext(String v) {
				// TODO Auto-generated method stub
				System.out.println("onNext() : value - " + v);
			}

			@Override
			public void onError(Throwable e) {
				// TODO Auto-generated method stub
				System.err.println("onError() : err - " + e.getMessage());
			}

			@Override
			public void onComplete() {
				// TODO Auto-generated method stub
				System.out.println("onComplete()");
				
			}
		});	
		
		System.out.println("isDisposed() : " + source.subscribe().isDisposed()); 		
		CommonUtils.exampleComplete();
	}

	public <T> void withConsumer() { 
		Observable<String> source = Observable.just("A", "B", "C");
		source.subscribe(new Consumer<String>() {
			@Override
			public void accept(String t) throws Exception {
				// TODO Auto-generated method stub
				System.out.println("accept() : value - " + t);
			}
		});	
		
		System.out.println("isDisposed() : " + source.subscribe().isDisposed()); 		
		CommonUtils.exampleComplete();
	}
	
	
	public <T> void withLambda() { 
		Observable<String> source = Observable.just("A", "B", "C");
		Disposable d = source.subscribe(
			v -> System.out.println("onNext() : value - " + v), 
			err -> System.err.println("onError() : err - " + err.getMessage()),
			() -> System.out.println("onComplete()")
		);	
		
		System.out.println("isDisposed() : " + d.isDisposed()); 		
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		Rx_Observable_02_Subscribe rx_subscribe = new Rx_Observable_02_Subscribe();
		System.out.println("01.Subscribe With Observer anonymous class");
		rx_subscribe.withObserver();
		
		System.out.println("02.Subscribe With Consumer anonymous class");
		rx_subscribe.withConsumer();
		
		System.out.println("03.Subscribe With Lambda");
		rx_subscribe.withLambda();
	}

}
