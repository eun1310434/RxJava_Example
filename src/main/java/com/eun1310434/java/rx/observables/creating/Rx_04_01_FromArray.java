/*==================================================================================================
□ INFORMATION
○ Data : 04.Sep.2018
○ Mail : eun1310434@gmail.com
○ WebPage : https://eun1310434.github.io/
○ Reference : RxJava 프로그래밍 P53
     
□ FUNCTION
○ 
   
□ Study
○ Creating(생성) : Operators that originate new Observables.
○ From() : convert some other object or data structure into an Observable
○ fromArray()
==================================================================================================*/
package com.eun1310434.java.rx.observables.creating;

import java.util.stream.IntStream;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;

import io.reactivex.Observable;

public class Rx_04_01_FromArray implements RxTest{
	
	@Override
	public void marbleDiagram() { 
		observableSet(integerArray());
		observableSet(toIntegerArray(intArray()));
	}
	
	public void observableSet(Integer[] arr){
		Observable<Integer> source = Observable.fromArray(arr);
		source.subscribe(data -> Log.i(data));// method reference java 8
		CommonUtils.exampleComplete();
	}
	
	private static Integer[] toIntegerArray(int[] intArray) { 
		return IntStream.of(intArray).boxed().toArray(Integer[]::new);
		//IntegerArray -> intArray : convert
	}
	
	private Integer[] integerArray() { 
		CommonUtils.exampleStart("01) integerArray");
		Integer[] arr = {100, 200, 300}; // Declaration Integer array
		return arr;
	}
	
	private int[] intArray() {
		CommonUtils.exampleStart("02) intArray");
		int[] intArray = {400, 500, 600}; // Declaration int array
		return intArray;
	}
	
	public static void main(String[] args){ 
		Rx_04_01_FromArray test = new Rx_04_01_FromArray();
		test.marbleDiagram();
	}	
}
