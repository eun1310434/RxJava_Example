/*==================================================================================================
□ INFORMATION
○ Data : 22.08.2018
○ Mail : eun1310434@naver.com
○ WebPage : https://eun1310434.github.io/
○ Reference
- RxJava 프로그래밍 P55
     
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

○ fromArray()
-
==================================================================================================*/
package com.eun1310434.java.rx.observable;

import java.util.stream.IntStream;

import com.eun1310434.java.rx.common.CommonUtils;

import io.reactivex.Observable;

public class Rx_Observable_04_FromArray {
	public void integerArray() { 
		CommonUtils.exampleStart("01) integerArray");
		Integer[] arr = {100, 200, 300}; // Declaration Integer array
		Observable<Integer> source = Observable.fromArray(arr);
		source.subscribe(System.out::println);// method reference java 8
		CommonUtils.exampleComplete();
	}
	
	public void intArray() {
		CommonUtils.exampleStart("02) intArray");
		int[] intArray = {400, 500, 600}; // Declaration int array
		Observable<Integer> source = Observable.fromArray(toIntegerArray(intArray));
		source.subscribe(System.out::println);// method reference java 8
		CommonUtils.exampleComplete();
	}
	
	private static Integer[] toIntegerArray(int[] intArray) { 
		return IntStream.of(intArray).boxed().toArray(Integer[]::new);
		//IntegerArray -> intArray : convert
	}
	
	public static void main(String[] args){ 
		Rx_Observable_04_FromArray test = new Rx_Observable_04_FromArray();
		test.integerArray();
		test.intArray();
	}	
}
