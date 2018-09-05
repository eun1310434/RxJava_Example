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

○ takeLast(n)
- emit only the final n items emitted by an Observable
- You can emit only the final n items emitted by an Observable 
  and ignore those items that come before them, by modifying the Observable with the TakeLast operator.

○ RxJava Generic Method Interface
01) Predicate<T> : boolean test(T t) // return to true or false
02) Consumer<T> : void accept(T t) // no return
03) Function<T,R> : R apply(T t) // return to R type

==================================================================================================*/
package com.eun1310434.java.rx.observables.filtering;


import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.RxTest;

import io.reactivex.Observable;

public class Rx_12_00_TakeLast implements RxTest {

	@Override
	public void marbleDiagram() { 
		CommonUtils.exampleStart("01) takeLast()");
		Integer[] numbers = {100, 200, 300, 400, 500};
		
		Observable<Integer> source = Observable
				.fromArray(numbers)
				.takeLast(3);
		source.subscribe(data -> System.out.println("takeLast(3) values =" + data));
		
		CommonUtils.exampleComplete();
	}

	public static void main(String[] args) { 
		Rx_12_00_TakeLast test = new Rx_12_00_TakeLast();
		test.marbleDiagram();
	}
}
