/*==================================================================================================
□ INFORMATION
○ Data : 04.Sep.2018
○ Mail : eun1310434@gmail.com
○ WebPage : https://eun1310434.github.io/
○ Reference : RxJava 프로그래밍 P33
     
□ FUNCTION
○ 
   
□ Study
○ Combining(합성)
- Operators that work with multiple source Observables to create a single Observable

○ combineLatest()
- when an item is emitted by either of two Observables, 
  combine the latest item emitted by each Observable via a specified function 
  and emit items based on the results of this function
==================================================================================================*/
package com.eun1310434.java.rx.observables.combining;

import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;

import io.reactivex.Observable;

public class Rx_02_00_CombineLatest implements RxTest {

	@Override
	public void marbleDiagram() {
		combineLatest_integer();
		combineLatest_character();
	}

	public void combineLatest_integer(){ 
		Observable<Integer> a = Observable.just(1);
		Observable<Integer> b = Observable.just(100);
		Observable
		.combineLatest(a, b, (x,y) -> x + y)
		.subscribe(data -> Log.i(data));
	}
	
	public void combineLatest_character(){ 
		Observable<Integer> a = Observable.just(100);
		Observable<String> b = Observable.just("A");
		Observable
		.combineLatest(a, b, (x,y) -> "" + x + y)
		.subscribe(data -> Log.i(data));
	}			

	public static void main(String[] args) { 
		new Rx_02_00_CombineLatest().marbleDiagram();
	}

}

