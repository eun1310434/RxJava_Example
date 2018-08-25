/*==================================================================================================
�� INFORMATION
�� Data : 22.08.2018
�� Mail : eun1310434@naver.com
�� WebPage : https://eun1310434.github.io/
�� Reference
- RxJava ���α׷��� P55
     
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

�� fromArray()
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
