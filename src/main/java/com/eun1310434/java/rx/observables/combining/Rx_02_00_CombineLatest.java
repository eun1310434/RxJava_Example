/*==================================================================================================
□ INFORMATION
  ○ Data : 28.08.2018
  ○ Mail : eun1310434@google.com
  ○ WebPage : https://eun1310434.github.io/
  ○ Reference
     - RxJava 프로그래밍 P33
     
□ FUNCTION
○ 
   
□ Study
○ combineLatest()
- when an item is emitted by either of two Observables, 
  combine the latest item emitted by each Observable via a specified function and emit items based on the results of this function
==================================================================================================*/
package com.eun1310434.java.rx.observables.combining;

import io.reactivex.Observable;

public class Rx_02_00_CombineLatest {
	public static void main(String[] args) { 
		new Rx_02_00_CombineLatest().run();
	}
	
	public void run() {
		Observable<Integer> a = Observable.just(1);
		Observable<Integer> b = Observable.just(100);
		Observable
		.combineLatest(a, b, (x,y) -> x + y)
		.subscribe(System.out::println);
	}
}
