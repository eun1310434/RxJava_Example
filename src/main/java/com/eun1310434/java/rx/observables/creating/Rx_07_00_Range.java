/*==================================================================================================
�� INFORMATION
  �� Data : 28.08.2018
  �� Mail : eun1310434@gmail.com
  �� WebPage : https://eun1310434.github.io/
  �� Reference
     - RxJava ���α׷��� P43
     
�� FUNCTION
  �� 
   
�� Study
�� Data Source
- Observable
- Single
- Maybe
- Subject
- Completable

�� Data Receiver
- Subscriber : Observable�� ������ ���� "subscribe()" Calling. 
- Observer : RxJava�� observer pattern�� implement. 
- Consumer : RxJava 2������ Comsumer�� Parameter�� ���

�� range()
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
