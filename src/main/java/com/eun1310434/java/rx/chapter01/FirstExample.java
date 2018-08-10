/*==================================================================================================
□ INFORMATION
  ○ Data : 10.08.2018
  ○ Mail : eun1310434@naver.com
  ○ WebPage : https://eun1310434.github.io/
  ○ Reference
     - RxJava 프로그래밍 P33
     
□ FUNCTION
   ○ 
   
□ Study
  ○
  
==================================================================================================*/

package com.eun1310434.java.rx.chapter01;

import io.reactivex.Observable;
//RxJava 2의 기본 패키지 이름

public class FirstExample {	
	
	public void emit() {
		Observable // Observable : 데이터의 변화가 발생하는 데이터 소스(data source)
		.just("Hello", "RxJava2!!") //just : Observable 선언 방식
		.subscribe(System.out::println); // subscribe : 변화한 데이터를 구독자에게 발행
	}

	public static void main(String args[]) { 
		FirstExample demo = new FirstExample();
		demo.emit();
	}	
}