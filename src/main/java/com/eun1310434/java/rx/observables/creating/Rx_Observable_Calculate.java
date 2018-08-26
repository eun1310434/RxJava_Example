/*==================================================================================================
�� INFORMATION
  �� Data : 18.08.2018
  �� Mail : eun1310434@naver.com
  �� WebPage : https://eun1310434.github.io/
  �� Reference
     - RxJava ���α׷��� P33
     
�� FUNCTION
  �� 
   
�� Study
  ��
  
==================================================================================================*/
package com.eun1310434.java.rx.observables.creating;

import io.reactivex.Observable;

public class Rx_Observable_Calculate {
	public static void main(String[] args) { 
		new Rx_Observable_Calculate().run();
	}
	
	public void run() {
		Observable<Integer> a = Observable.just(1);
		Observable<Integer> b = Observable.just(100);
		Observable
		.combineLatest(a, b, (x,y) -> x + y)
		.subscribe(System.out::println);
	}
}