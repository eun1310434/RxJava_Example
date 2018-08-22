/*==================================================================================================
�� INFORMATION
�� Data : 22.08.2018
�� Mail : eun1310434@naver.com
�� WebPage : https://eun1310434.github.io/
�� Reference
- RxJava ���α׷��� P57
     
�� FUNCTION
�� 
   
�� Study
�� fromCallable()
-  asynchronism ����� ���� ���
- Executor Interface�� implementation�� Class�� Callable Object�� Parameter�� input Future Object�� return.
- get() method�� calling�ϸ� Callable object���� implementation�� ��� ����� ���� �� ���� Blocking��.
==================================================================================================*/
package com.eun1310434.java.rx.observable;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.eun1310434.java.rx.common.CommonUtils;

import io.reactivex.Observable;

public class Rx_Observable_07_FromFuture {
	public void lambda() {
		CommonUtils.exampleStart("01) lambda");
		Future<String> future = Executors.newSingleThreadExecutor().submit(() -> {
			Thread.sleep(1000);
			return "After 1 Sec : Hello Future-lambda";
		});
		// Executor Interface�� implementation�� Class�� Callable Object�� Parameter�� input Future Object�� return.
		// get() method�� calling�ϸ� Callable object���� implementation�� ��� ����� ���� �� ���� Blocking��

		Observable<String> source = Observable.fromFuture(future);

		source.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}

	public void anonymous() {
		CommonUtils.exampleStart("02) anonymous");
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
		
		Observable<String> source = Observable.fromFuture(future);
		
		source.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}

	public static void main(String[] args) {
		Rx_Observable_07_FromFuture test = new Rx_Observable_07_FromFuture();
		test.lambda();
		test.anonymous();
	}
}