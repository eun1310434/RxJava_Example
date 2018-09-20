/*==================================================================================================
□ INFORMATION
○ Data : 04.Sep.2018
○ Mail : eun1310434@gmail.com
○ WebPage : https://eun1310434.github.io/
○ Reference : RxJava 프로그래밍 P54
          
□ FUNCTION
○ 
   
□ Study
○ Creating(생성) : Operators that originate new Observables.

○ From() : convert some other object or data structure into an Observable

○ fromCallable()
- asynchronism 계산을 위해 사용
- Executor 인터페이스의 인자로 활용되기 때문에 잠재적을 다른 스레드에서 실행되는 것을 의미

○ JAVA 5 - API : Callable
- 비동기 실행 후 결과를 반환하는 call()메서드를 정의
	public interface Callable<V>{
		//@return 계산된 결과값
		//@throws Exception 계산을 완료할 수 없을 때
		V call() throws Exception;
	}
- Runnable의 인터페이스 처럼 메서드가 1개이고 인자가 없다는 점에서 동일 하나 실행결과를 리턴하는 점에서 차이 있음

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

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;

import io.reactivex.Observable;

public class Rx_04_03_FromCallable implements RxTest{
	
	@Override
	public void marbleDiagram() { 
		Callable<String> callable = null;
		observableSet(lambda(callable));
		observableSet(anonymous(callable));
	}
	
	public void observableSet(Callable<String> call){
		Observable<String> source = Observable.fromCallable(call);
		source.subscribe(data -> Log.i(data));
		CommonUtils.exampleComplete();
	}
	
	public Callable<String> lambda(Callable<String> callable) { 
		CommonUtils.exampleStart("01) FromCallable - lambda");
		callable = () -> { 
			Thread.sleep(1000);
			return "After 1 Sec : Hello Callable-lambda";
		};
		return callable;
	}
	
	public Callable<String> anonymous(Callable<String> callable) { 
		CommonUtils.exampleStart("02) FromCallable - anonymous");
		callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(1000);
				return "After 1 Sec : Hello Callable-anonymous";
			}			
		};
		return callable;
	}
	
	public static void main(String[] args) { 
		Rx_04_03_FromCallable test = new Rx_04_03_FromCallable();
		test.marbleDiagram();
	}
}
