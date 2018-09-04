/*==================================================================================================
�� INFORMATION
�� Data : 04.Sep.2018
�� Mail : eun1310434@gmail.com
�� WebPage : https://eun1310434.github.io/
�� Reference : RxJava ���α׷��� P48
     
�� FUNCTION
�� 
   
�� Study
�� Creating(����) : Operators that originate new Observables.
�� create()
create an Observable from scratch by calling observer methods programmatically
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
package com.eun1310434.java.rx.observables.creating;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

public class Rx_01_00_Create implements RxTest{

	@Override
	public void marbleDiagram() { 
		lambda();
		anonymous();
	}
	
	public void lambda() {
		CommonUtils.exampleStart("01) Create - Lambda");
		Observable<Integer> source = Observable
				.create((ObservableEmitter<Integer> emitter) -> {
					emitter.onNext(100);
					emitter.onNext(200);
					emitter.onNext(300);
					emitter.onComplete();
				});

		source.subscribe(data -> Log.i(data));
		CommonUtils.exampleComplete();
	}
	
	
	public void anonymous() {
		CommonUtils.exampleStart("02) Create - Anonymous Print");
		Observable<Integer> source = Observable
				.create(new ObservableOnSubscribe<Integer>() {
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
				Log.i(data);
			}
		});
		CommonUtils.exampleComplete();
	}

	public static void main(String[] args) {
		Rx_01_00_Create test = new Rx_01_00_Create();
		test.marbleDiagram();
	}
}
