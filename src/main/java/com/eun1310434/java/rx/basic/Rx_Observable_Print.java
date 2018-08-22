/*==================================================================================================
□ INFORMATION
  ○ Data : 18.08.2018
  ○ Mail : eun1310434@naver.com
  ○ WebPage : https://eun1310434.github.io/
  ○ Reference
     - RxJava 프로그래밍 P33
     
□ FUNCTION
  ○ 
   
□ Study
  ○
  
==================================================================================================*/

package com.eun1310434.java.rx.basic;

import io.reactivex.Observable;

public class Rx_Observable_Print {	
	
	public void emit() {
		Observable // Observable : 데이터의 변화가 발생하는 데이터 소스(data source)
		.just("Hello", "RxJava2!!") //just() : Observable의 선언방식 
		.subscribe(data->System.out.println(data)); // subscribe() : 변화한 데이터를 구독자에게 발행
		//.subscribe(data->{System.out.println(data);}); 
		//.subscribe(System.out::println); 	
	}

	public static void main(String args[]) { 
		Rx_Observable_Print demo = new Rx_Observable_Print();
		demo.emit();
	}	
}