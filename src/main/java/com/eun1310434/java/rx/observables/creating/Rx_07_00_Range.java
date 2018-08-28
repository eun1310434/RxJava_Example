/*==================================================================================================
□ INFORMATION
  ○ Data : 28.08.2018
  ○ Mail : eun1310434@gmail.com
  ○ WebPage : https://eun1310434.github.io/
  ○ Reference
     - RxJava 프로그래밍 P43
     
□ FUNCTION
  ○ 
   
□ Study
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

○ range()
- create an Observable that emits a range of sequential integers 
- The Range operator emits a range of sequential integers, in order, where you select the start of the range and its length.
==================================================================================================*/
package com.eun1310434.java.rx.observables.creating;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;

import io.reactivex.Observable;


public class Rx_07_00_Range implements RxTest{

	public static void main(String args[]) { 

		Rx_07_00_Range test = new Rx_07_00_Range();
		test.marbleDiagram();
	}

	@Override
	public void marbleDiagram() {
		// TODO Auto-generated method stub
		CommonUtils.exampleStart("01) marbleDiagram"); 	
		Observable.range(1,9).subscribe(str -> Log.i(str));
		CommonUtils.exampleComplete();			
	}	
}
