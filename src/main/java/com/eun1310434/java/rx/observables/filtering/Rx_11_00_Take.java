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
○ take(n)
- emit only the first n items emitted by an Observable
- You can emit only the first n items emitted by an Observable 
  and then complete while ignoring the remainder, 
  by modifying the Observable with the Take operator.

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

public class Rx_11_00_Take implements RxTest {

	@Override
	public void marbleDiagram() { 
		CommonUtils.exampleStart("01) MarbleDiagram : take()");
		 
		Integer[] numbers = {100, 200, 300, 400, 500};
		Observable<Integer> source;
		
		//3. take(N) 
		source = Observable.fromArray(numbers).take(3);
		source .subscribe(data -> Log.i("take(3) values =" + data));			
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		Rx_11_00_Take test = new Rx_11_00_Take();
		test.marbleDiagram();
	}
}
