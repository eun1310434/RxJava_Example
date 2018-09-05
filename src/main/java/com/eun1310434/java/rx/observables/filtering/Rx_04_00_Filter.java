/*==================================================================================================
□ INFORMATION
○ Data : 04.Sep.2018
○ Mail : eun1310434@gmail.com
○ WebPage : https://eun1310434.github.io/
○ Reference : RxJava 프로그래밍 P100
     
□ FUNCTION
○ 

□ Study
○ Filtering Observables
- Operators that selectively emit items from a source Observable.

○ filter()
- emit only those items from an Observable that pass a predicate test

○ RxJava Generic Method Interface
01) Predicate<T> : boolean test(T t) // return to true or false
02) Consumer<T> : void accept(T t) // no return
03) Function<T,R> : R apply(T t) // return to R type
==================================================================================================*/
package com.eun1310434.java.rx.observables.filtering;

import static com.eun1310434.java.rx.common.Shape.BLUE;
import static com.eun1310434.java.rx.common.Shape.GREEN;
import static com.eun1310434.java.rx.common.Shape.PUPPLE;
import static com.eun1310434.java.rx.common.Shape.RED;
import static com.eun1310434.java.rx.common.Shape.SKY;
import static com.eun1310434.java.rx.common.Shape.YELLOW;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;

import io.reactivex.Observable;
import io.reactivex.functions.Predicate;

public class Rx_04_00_Filter implements RxTest {

	@Override
	public void marbleDiagram() { 
		String[] strs = {
				RED+" A", YELLOW+" B", GREEN+" C", SKY+" D", BLUE+" A", PUPPLE+" B"};
		
		observableSet("01-01) String - filter() - endsWith() - anonymous", strs, new Predicate<String>() {
			@Override
			public boolean test(String t) throws Exception {
				// TODO Auto-generated method stub
				if(t.endsWith("A")){
					return true;
				}
				return false;
			}
		});
		Predicate<String> predicateStr = str -> str.endsWith("A");
		observableSet("01-02) String - filter() - endsWith() - lambda", strs, predicateStr);
		observableSet("01-03) String - filter() - endsWith() - lambda", strs, str -> str.endsWith("A"));

		
		Integer[] numbers = {100, 34, 27, 99, 50};
		observableSet("02-01) Integer - filter() - endsWith() - anonymous", numbers, new Predicate<Integer>() {
			
			@Override
			public boolean test(Integer t) throws Exception {
				// TODO Auto-generated method stub
				if(t % 2 == 0){
					return true;
				}
				
				return false;
			}
		});
		Predicate<Integer> predicateInt = number -> number % 2 == 0;
		observableSet("02-02) Integer - filter() - evenNumbers() - lambda", numbers, predicateInt );
		observableSet("02-03) Integer - filter() - evenNumbers() - lambda", numbers, number -> number % 2 == 0);
				
	}
	
	public void observableSet(String title, String[] objs, Predicate<String> pre){
		CommonUtils.exampleStart(title);
		Observable<String> source = Observable
				.fromArray(objs)
				.filter(pre);
		
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();	
	}

	public void observableSet(String title, Integer[] objs, Predicate<Integer> pre){
		CommonUtils.exampleStart(title);
		Observable<Integer> source = Observable
				.fromArray(objs)
				.filter(pre);
		
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();	
	}
	
	public static void main(String[] args) { 
		Rx_04_00_Filter test = new Rx_04_00_Filter();
		test.marbleDiagram();
	}
}
