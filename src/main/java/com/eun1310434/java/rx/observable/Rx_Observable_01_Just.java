/*==================================================================================================
□ INFORMATION
  ○ Data : 18.08.2018
  ○ Mail : eun1310434@naver.com
  ○ WebPage : https://eun1310434.github.io/
  ○ Reference
     - RxJava 프로그래밍 P43
     
□ FUNCTION
  ○ 
   
□ Study
  ○ just()
    - 인자로 넣은 데이터를 차례로 발행
    - 한 개의 값을 넣을 수도 있고 인자로 여러 개의 값(최대 10개)를 넣을 수 있음.
       *단 같은 타입이어야 함
    - public static <T> Observable<T> just(T item1,... T item10)
  
==================================================================================================*/
package com.eun1310434.java.rx.observable;

import io.reactivex.Observable;


public class Rx_Observable_01_Just {
	public void emit() {
		Observable
		.just(1,2,3,4,5,6,7,8,9,10) //just() : Observable의 선언방식 
		.subscribe(System.out::println); 	
	}

	public static void main(String args[]) { 
		Rx_Observable_01_Just just = new Rx_Observable_01_Just();
		just.emit();
	}	
}
