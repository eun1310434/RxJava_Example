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
○ fromCallable()
-  asynchronism 계산을 위해 사용
- Executor 인터페이스의 인자로 활용되기 때문에 잠재적을 다른 스레드에서 실행되는 것을 의미
○ JAVA 5 - API : Callable
- 비동기 실행 후 결과를 반환하는 call()메서드를 정의
public interface Callable<V>{
	//@return 계산된 결과값
	//@throws Exception 계산을 완료할 수 없을 때
	V call() throws Exception;
}
- Runnable의 인터페이스 처럼 메서드가 1개이고 인자가 없다는 점에서 동일 하나 실행결과를 리턴하는 점에서 차이 있음
==================================================================================================*/
package com.eun1310434.java.rx.observable;

import java.util.concurrent.Callable;

import com.eun1310434.java.rx.common.CommonUtils;

import io.reactivex.Observable;

public class Rx_Observable_06_FromCallable {
	public void lambda() { 
		CommonUtils.exampleStart("01) lambda");
		Callable<String> callable = () -> { 
			Thread.sleep(1000);
			return "After 1 Sec : Hello Callable-lambda";
		};
		
		Observable<String> source = Observable.fromCallable(callable);
		source.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}
	
	public void anonymous() { 
		CommonUtils.exampleStart("02) anonymous");
		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(1000);
				return "After 1 Sec : Hello Callable-anonymous";
			}			
		};
		
		Observable<String> source = Observable.fromCallable(callable);
		source.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		Rx_Observable_06_FromCallable test = new Rx_Observable_06_FromCallable();
		test.lambda();
		test.anonymous();
	}
}
