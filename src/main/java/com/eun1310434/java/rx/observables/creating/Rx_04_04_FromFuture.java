/*==================================================================================================
�� INFORMATION
�� Data : 04.Sep.2018
�� Mail : eun1310434@gmail.com
�� WebPage : https://eun1310434.github.io/
�� Reference : RxJava ���α׷��� P59
          
�� FUNCTION
�� 
   
�� Study
�� Creating(����) : Operators that originate new Observables.

�� From() : convert some other object or data structure into an Observable

�� fromFuture()
- asynchronism(�񵿱�) ����� ���� ���
- Executor Interface�� implementation�� Class�� Callable Object�� Parameter�� input Future Object�� return.
- get() method�� calling�ϸ� Callable object���� implementation�� ��� ����� ���� �� ���� Blocking��.

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
==================================================================================================*/
package com.eun1310434.java.rx.observables.creating;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;

import io.reactivex.Observable;

public class Rx_04_04_FromFuture implements RxTest{
	
	@Override
	public void marbleDiagram() { 
		observableSet(lambda());
		observableSet(anonymous());
	}
	
	public void observableSet(Future<String> future){
		Observable<String> source = Observable.fromFuture(future);
		source.subscribe(data -> Log.i(data));
		CommonUtils.exampleComplete();	
	}
	
	
	public Future<String> lambda() {
		CommonUtils.exampleStart("01) FromFuture - lambda");
		Future<String> future = Executors.newSingleThreadExecutor().submit(() -> {
			Thread.sleep(1000);
			return "After 1 Sec : Hello Future-lambda";
		});
		// Executor Interface�� implementation�� Class�� Callable Object�� Parameter�� input Future Object�� return.
		// get() method�� calling�ϸ� Callable object���� implementation�� ��� ����� ���� �� ���� Blocking��

		return future;
	}

	public Future<String> anonymous() {
		CommonUtils.exampleStart("02) FromFuture - anonymous");
		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(1000);
				return "After 1 Sec : Hello Future-anonymous";
			}
		};

		Future<String> future = Executors.newSingleThreadExecutor().submit(callable);
		// Executor Interface�� implementation�� Class�� Callable Object�� Parameter�� input Future Object�� return.
		// get() method�� calling�ϸ� Callable object���� implementation�� ��� ����� ���� �� ���� Blocking��

		return future;		
	}

	public static void main(String[] args) {
		Rx_04_04_FromFuture test = new Rx_04_04_FromFuture();
		test.marbleDiagram();
	}
}