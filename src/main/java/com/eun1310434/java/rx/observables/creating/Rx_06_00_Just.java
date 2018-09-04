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
○ Creating(생성) : Operators that originate new Observables.

○ just()
- convert an object or a set of objects into an Observable that emits that or those objects
- 인자로 넣은 데이터를 차례로 발행
- 한 개의 값을 넣을 수도 있고 인자로 여러 개의 값(최대 10개)를 넣을 수 있음.
  *단 같은 타입이어야 함
- public static <T> Observable<T> just(T item1,... T item10)  

○ Data Source
- Observable
- Single
- Maybe
- Subject
- Completable

○ Data Receiver
- Subscriber : Observable과 연결할 때는 "subscribe()" Calling. 
- Observer : RxJava는 observer pattern을 implement. 
- Consumer : RxJava 2에서는 Comsumer를 Parameter로 사용
==================================================================================================*/
package com.eun1310434.java.rx.observables.creating;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.RxTest;
import com.eun1310434.java.rx.common.Log;

import io.reactivex.Observable;


public class Rx_06_00_Just implements RxTest{
	
	@Override
	public void marbleDiagram() { 
		CommonUtils.exampleStart("01) emit"); 	
		Observable
		.just(1,2,3,4,5,6,7,8,9,10) //just() : Observable의 선언방식 
		.subscribe(data -> Log.i(data));
		CommonUtils.exampleComplete();	
	}
	
	public static void main(String args[]) { 
		Rx_06_00_Just just = new Rx_06_00_Just();
		just.marbleDiagram();
	}	
}
