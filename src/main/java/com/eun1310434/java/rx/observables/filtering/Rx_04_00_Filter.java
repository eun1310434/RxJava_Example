/*==================================================================================================
□ INFORMATION
○ Data : 28.08.2018
○ Mail : eun1310434@gmail.com
○ WebPage : https://eun1310434.github.io/
○ Reference
- RxJava 프로그래밍 P100
     
□ FUNCTION
○ 

□ Study
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
import io.reactivex.Single;
import io.reactivex.functions.Predicate;

public class Rx_04_00_Filter implements RxTest {

	@Override
	public void marbleDiagram() { 
		CommonUtils.exampleStart("01) MarbleDiagram : filter()");
		String[] objs = {
				RED+" A", 
				YELLOW+" B", 
				GREEN+" C", 
				SKY+" D", 
				BLUE+" A", 
				PUPPLE+" B"};
		
		Observable<String> source = 
				Observable.fromArray(objs)
				.filter(obj -> obj.endsWith("A"));
		//source.subscribe(_color -> Log.i(_color));
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}

	public void usingPredicate() {
		CommonUtils.exampleStart("02) MarbleDiagram : usingPredicate()");
		Predicate<String> filterCircle = obj -> obj.endsWith("A");
		
		String[] objs = {
				"RED A", 
				"YELLOW B", 
				"GREEN C", 
				"SKY D", 
				"BLUE A", 
				"PUPPLE E"};
		
		Observable<String> source = 
				Observable.fromArray(objs)
				.filter(filterCircle);
		//source.subscribe(_color -> Log.i(_color));
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}
	
	public void evenNumbers() { 
		CommonUtils.exampleStart("03) MarbleDiagram : evenNumbers()");
		Integer[] data = {100, 34, 27, 99, 50};
		Observable<Integer> source = 
				Observable.fromArray(data)
				.filter(number -> number % 2 == 0);
		//source.subscribe(_num -> Log.i(_num));
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}
	
	public void otherFilters() { 
		Integer[] numbers = {100, 200, 300, 400, 500};
		Single<Integer> single;
		Observable<Integer> source;
				
		//1. first 
		single = Observable.fromArray(numbers).first(-1);
		single.subscribe(data -> System.out.println("first() value = " + data));
		
		//2. last 
		single = Observable.fromArray(numbers).last(999);
		single.subscribe(data -> System.out.println("last() value = " + data));
		
		//3. take(N) 
		source = Observable.fromArray(numbers).take(3);
		source.subscribe(data -> System.out.println("take(3) values =" + data));

		//4. takeLast(N) 
		source = Observable.fromArray(numbers).takeLast(3);
		source.subscribe(data -> System.out.println("takeLast(3) values =" + data));
		
		//5. skip(N) 
		source = Observable.fromArray(numbers).skip(2);
		source.subscribe(data -> System.out.println("skip(2) values = " + data));
		
		//6. skipLast(N) 
		source = Observable.fromArray(numbers).skipLast(2);
		source.subscribe(data -> System.out.println("skipLast(2) values = " + data));
		CommonUtils.exampleComplete();
	}	
	
	public static void main(String[] args) { 
		Rx_04_00_Filter test = new Rx_04_00_Filter();
		test.marbleDiagram();
		test.usingPredicate();
		test.evenNumbers();
//		test.otherFilters();
	}
}
