/*==================================================================================================
�� INFORMATION
�� Data : 22.08.2018
�� Mail : eun1310434@naver.com
�� WebPage : https://eun1310434.github.io/
�� Reference
- RxJava ���α׷��� P50
     
�� FUNCTION
�� 
   
�� Study
�� Data Source
- Observable
- Single
- Maybe
- Subject
- Completable

�� Data Receiver
- Subscriber : Observable�� ������ ���� "subscribe()" Calling. 
- Observer : RxJava�� observer pattern�� implement. 
- Consumer : RxJava 2������ Comsumer�� Parameter�� ���

�� create()
- Observable<T> create(ObservableInSubscribe<T> source)
- public interface ObservableOnSubscribe<T>{
	      void subscribe(ObservableEmitter<T> e) throws Exception;
  }
  
�� Observable.create()�� ����� �� ���� �� ��.
01) Observable�� ���� ����(dispose)�Ǿ��� �� ��ϵ� �ݹ��� ��� �����ؾ� ��.
     * ������ �޸� ����(memory leak) ����
02) �����ڰ� �����ϴ� ���ȿ��� onNext�� onComplete �̺�Ʈ�� ȣ���ؾ� ��.
03) ������ �߻����� ���� ���� onError�̺�Ʈ�θ� ������ �����ؾ� ��.
04) ���(back pressure)�� ���� ó���ؾ� ��.
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
