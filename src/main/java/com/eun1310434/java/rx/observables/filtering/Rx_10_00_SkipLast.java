/*==================================================================================================
□ INFORMATION
○ Data : 28.08.2018
○ Mail : eun1310434@gmail.com
○ WebPage : https://eun1310434.github.io/
○ Reference
- RxJava 프로그래밍 P97
     
□ FUNCTION
○ 
   
□ Study
○ skipLast(n)
- suppress the final n items emitted by an Observable
- You can ignore the final n items emitted by an Observable 
  and attend only to those items that come before them, 
  by modifying the Observable with the SkipLast operator.
- skipLast(N) : 마지막 N개 값을 건너뜀

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

public class Rx_10_00_SkipLast implements RxTest {

	@Override
	public void marbleDiagram() { 
		CommonUtils.exampleStart("01) MarbleDiagram : skipLast()");
		Integer[] numbers = {100, 200, 300, 400, 500};
		Observable<Integer> source;
		
		//skipLast(N) 
		source = Observable.fromArray(numbers).skipLast(2);
		source .subscribe(data -> Log.i("skipLast(2) values = " + data));	
		
		CommonUtils.exampleComplete();
	}

	public static void main(String[] args) { 
		Rx_10_00_SkipLast test = new Rx_10_00_SkipLast();
		test.marbleDiagram();
	}
}
