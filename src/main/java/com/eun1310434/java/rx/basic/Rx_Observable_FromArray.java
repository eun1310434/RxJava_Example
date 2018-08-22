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
○ fromArray()
-
==================================================================================================*/
package com.eun1310434.java.rx.basic;

import java.util.stream.IntStream;

import io.reactivex.Observable;

public class Rx_Observable_FromArray {
	public void integerArray() { 
		System.out.println("01) integerArray");
		Integer[] arr = {100, 200, 300}; // Declaration Integer array
		Observable<Integer> source = Observable.fromArray(arr);
		source.subscribe(System.out::println);// method reference java 8
		System.out.println("");
	}
	
	public void intArray() {
		System.out.println("02) intArray");
		int[] intArray = {400, 500, 600}; // Declaration int array
		Observable<Integer> source = Observable.fromArray(toIntegerArray(intArray));
		source.subscribe(System.out::println);// method reference java 8
		System.out.println("");
	}
	
	private static Integer[] toIntegerArray(int[] intArray) { 
		return IntStream.of(intArray).boxed().toArray(Integer[]::new);
		//IntegerArray -> intArray : convert
	}
	
	public static void main(String[] args){ 
		Rx_Observable_FromArray test = new Rx_Observable_FromArray();
		test.integerArray();
		test.intArray();
	}	
}
