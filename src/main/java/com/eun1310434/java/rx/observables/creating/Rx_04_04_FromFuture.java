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

○ fromFuture()
- asynchronism(비동기) 계산을 위해 사용
- Executor Interface를 implementation한 Class에 Callable Object를 Parameter로 input Future Object를 return.
- get() method를 calling하면 Callable object에서 implementation한 계산 결과가 나올 때 까지 Blocking됨.

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
		Future<String> future = null;
		Callable<String> callable = null;
		
		observableSet(lambda(future));
		observableSet(anonymous(callable, future));
	}
	
	public void observableSet(Future<String> future){
		Observable<String> source = Observable.fromFuture(future);
		source.subscribe(data -> Log.i(data));
		CommonUtils.exampleComplete();	
	}
	
	
	public Future<String> lambda(Future<String> future ) {
		CommonUtils.exampleStart("01) FromFuture - lambda");
		future = Executors.newSingleThreadExecutor().submit(() -> {
			Thread.sleep(1000);
			return "After 1 Sec : Hello Future-lambda";
		});
		// Executor Interface를 implementation한 Class에 Callable Object를 Parameter로 input Future Object를 return.
		// get() method를 calling하면 Callable object에서 implementation한 계산 결과가 나올 때 까지 Blocking됨

		return future;
	}

	public Future<String> anonymous(Callable<String> callable, Future<String> future) {
		CommonUtils.exampleStart("02) FromFuture - anonymous");
		callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(1000);
				return "After 1 Sec : Hello Future-anonymous";
			}
		};

		future = Executors.newSingleThreadExecutor().submit(callable);
		// Executor Interface를 implementation한 Class에 Callable Object를 Parameter로 input Future Object를 return.
		// get() method를 calling하면 Callable object에서 implementation한 계산 결과가 나올 때 까지 Blocking됨

		return future;		
	}

	public static void main(String[] args) {
		Rx_04_04_FromFuture test = new Rx_04_04_FromFuture();
		test.marbleDiagram();
	}
}