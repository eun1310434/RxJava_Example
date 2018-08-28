/*==================================================================================================
□ INFORMATION
○ Data : 28.08.2018
○ Mail : eun1310434@gmail.com
○ WebPage : https://eun1310434.github.io/
○ Reference
- RxJava 프로그래밍 P100
     
□ FUNCTION
○ 
   
□ Study
○ first()
- emit only the first item, or the first item that meets a condition, from an Observable
- If you are only interested in the first item emitted by an Observable, 
  or the first item that meets some criteria, you can filter the Observable with the First operator.

○ RxJava Generic Method Interface
01) Predicate<T> : boolean test(T t) // return to true or false
02) Consumer<T> : void accept(T t) // no return
03) Function<T,R> : R apply(T t) // return to R type

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
		Single<Integer> single;
				 
		single = Observable.fromArray(numbers).first(-1);//if no value, return to -1
		single.subscribe(Log::i);
		
		CommonUtils.exampleComplete();
	}

	public static void main(String[] args) { 
		Rx_05_00_First test = new Rx_05_00_First();
		test.marbleDiagram();
	}
}
