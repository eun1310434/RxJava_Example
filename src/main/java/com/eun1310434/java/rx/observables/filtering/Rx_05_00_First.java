/*==================================================================================================
□ INFORMATION
○ Data : 04.Sep.2018
○ Mail : eun1310434@gmail.com
○ WebPage : https://eun1310434.github.io/
○ Reference : RxJava 프로그래밍 P100
     
□ FUNCTION
○ 
   
□ Study
○ Filtering Observables
- Operators that selectively emit items from a source Observable.

○ first()
- emit only the first item, or the first item that meets a condition, from an Observable
- If you are only interested in the first item emitted by an Observable, 
  or the first item that meets some criteria, you can filter the Observable with the First operator.

○ RxJava Generic Method Interface
01) Predicate<T> : boolean test(T t) // return to true or false
02) Consumer<T> : void accept(T t) // no return
03) Function<T,R> : R apply(T t) // return to R type

○ Single Class
- Observable 클래스는 데이터를 무한하게 발행할 수 있지만 Single 클래스는 오직 1개의 데이터만 발행하도록 함
- 데이터 하나가 발행과 동시에 종료(onSuccess)
* onSuccess() = onNext() + onComplete()
- Single 클래스의 라이프 사이클 함수는 onSuccess(T value)함수와 onError()함수로 구성
==================================================================================================*/
package com.eun1310434.java.rx.observables.filtering;


import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;

import io.reactivex.Observable;
import io.reactivex.Single;

public class Rx_05_00_First implements RxTest {

	@Override
	public void marbleDiagram() { 
		CommonUtils.exampleStart("01) MarbleDiagram : first()");
		Integer[] numbers = {100, 200, 300, 400, 500};
		
		Single<Integer> single = Observable
				.fromArray(numbers)
				.first(-1);//if no value, return to -1
		single.subscribe(Log::i);
		
		CommonUtils.exampleComplete();
	}

	public static void main(String[] args) { 
		Rx_05_00_First test = new Rx_05_00_First();
		test.marbleDiagram();
	}
}
