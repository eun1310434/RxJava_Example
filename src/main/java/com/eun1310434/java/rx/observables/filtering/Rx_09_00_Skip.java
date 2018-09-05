/*==================================================================================================
□ INFORMATION
○ Data : 04.Sep.2018
○ Mail : eun1310434@gmail.com
○ WebPage : https://eun1310434.github.io/
○ Reference : RxJava 프로그래밍 P97
   
□ FUNCTION
○ 
   
□ Study
○ Filtering Observables
- Operators that selectively emit items from a source Observable.

○ skip(n)
- suppress the first n items emitted by an Observable
- You can ignore the first n items emitted by an Observable and attend only to those items that come after, 
  by modifying the Observable with the Skip operator.

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

public class Rx_09_00_Skip implements RxTest {

	@Override
	public void marbleDiagram() { 
		CommonUtils.exampleStart("01) MarbleDiagram : skip()");
		Integer[] numbers = {100, 200, 300, 400, 500};
		
		Observable<Integer> source = Observable
				.fromArray(numbers)
				.skip(2);
		source .subscribe(data -> Log.i("skip(2) values = " + data));	
		
		CommonUtils.exampleComplete();
	}

	
	public static void main(String[] args) { 
		Rx_09_00_Skip test = new Rx_09_00_Skip();
		test.marbleDiagram();
	}
}
