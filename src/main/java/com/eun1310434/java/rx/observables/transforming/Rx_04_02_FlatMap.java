/*==================================================================================================
□ INFORMATION
○ Data : 28.08.2018
○ Mail : eun1310434@gamil.com
○ WebPage : https://eun1310434.github.io/
○ Reference
- RxJava 프로그래밍 P90

□ FUNCTION
○ 
   
□ Study
○ Transforming(변환) : Operators that transform items that are emitted by an Observable.

○ flatMap()
- Webpage : http://reactivex.io/documentation/operators/flatmap.html
- transform the items emitted by an Observable into Observables, 
  then flatten the emissions from those into a single Observable
- The FlatMap operator transforms an Observable by applying a function 
  that you specify to each item emitted by the source Observable, 
  where that function returns an Observable that itself emits items. 
  FlatMap then merges the emissions of these resulting Observables, 
  emitting these merged results as its own sequence.

○ the original form of flatMap() - A
@SchedulerSupport(SchedulerSupport.NONE)
public final <R> Observable<R> flatMap(
    Function<? super T, ? extends ObservableSource<? extends R>> mapper
)

- @SchedulerSupport(SchedulerSupport.NONE) : 스케줄러를 지원하지 않는다(현재 스레드에서 실행)
- ObservableSource : Observable, AsyncSubject, BehaviorSubject, ConnectableObervable등이 공통으로 구현한 Interface
- SingleSource : Single 클래스에서 구현한 인터페이스

○ the original form of flatMap() - B
@CehckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
public final <U, R> Observable<R> flatMap(
    Function<? super T, ? extends ObservableSource<? extends U>> mapper,
    BitFunction<? super T, ? super U, ? extends R> resultSelector
)
- BitFunction<T,U,R> resultSelector : T parameter is mapper


○ RxJava Generic Method Interface
01) Predicate<T> : boolean test(T t) // return to true or false
02) Consumer<T> : void accept(T t) // no return
03) Function<T,R> : R apply(T t) // return to R type
==================================================================================================*/
package com.eun1310434.java.rx.observables.transforming;

import static com.eun1310434.java.rx.common.Shape.BLUE;
import static com.eun1310434.java.rx.common.Shape.GREEN;
import static com.eun1310434.java.rx.common.Shape.RED;

import java.util.Scanner;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class Rx_04_02_FlatMap implements RxTest {

	@Override
	public void marbleDiagram() { 
		// 1. MarbleDiagram : flatMap()
		CommonUtils.exampleStart("01) MarbleDiagram : flatMap()");
		
		Function<String, Observable<String>> getDoubleDiamonds = 
				color -> Observable.just("Color : "+ color, "Color : "+ color);
		
		String[] colors = {RED, GREEN, BLUE}; 
		Observable<String> source = Observable.fromArray(colors).flatMap(getDoubleDiamonds);

		// source.subscribe(_color -> Log.i(_color));
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}

	public void flatMapLambda() { 
		// 2. MarbleDiagram : flatMapLambda()
		CommonUtils.exampleStart("02) MarbleDiagram : flatMapLambda()");
				
		String[] colors = {RED, GREEN, BLUE}; 
		Observable<String> source = Observable.fromArray(colors).flatMap(
				color -> Observable.just("Color : "+ color, "Color : "+ color)
				);
		
		//source.subscribe(_color -> Log.i(_color));
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}

	public void multiplicationTable_A() { 
		// 3. MarbleDiagram : multiplicationTable()
		CommonUtils.exampleStart("03) MarbleDiagram : multiplicationTable()");
		

		Function<Integer, Observable<String>> getMultiplicationTable = 
				num -> Observable.range(1,9).map(row -> num + " * " + row + " = " + num*row);
		
		Scanner in = new Scanner(System.in);
		System.out.println("Multiplication Table Input:");
		int dan = Integer.parseInt(in.nextLine());				
			
		Observable<String> source = Observable
				.just(dan)
				.flatMap(getMultiplicationTable);
				//.flatMap(num -> Observable.range(1,9).map(row -> num + " * " + row + " = " + num*row));
		

		source.subscribe(_dan -> Log.i(_dan));
		//source.subscribe(Log::i);
		//source.subscribe(System.out::println);
		in.close();
		
		CommonUtils.exampleComplete();
	}
	
	public void multiplicationTable_B() { // usingResultSelector
		// 4. MarbleDiagram : multiplicationTable_B() - usingResultSelector
		CommonUtils.exampleStart("04) MarbleDiagram : multiplicationTable_B() - usingResultSelector");

		Scanner in = new Scanner(System.in);
		System.out.println("Multiplication Table Input:");
		int dan = Integer.parseInt(in.nextLine());			

		Observable<String> source = Observable
				.just(dan)
				.flatMap(num -> Observable.range(1, 9), (num, row) ->  num + " * " + row + " = " + num * row);
		//Observable.range(1, 9) becomes a row 
		source.subscribe(System.out::println);
		in.close();

		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		Rx_04_02_FlatMap test = new Rx_04_02_FlatMap();
		test.marbleDiagram();
		test.flatMapLambda();
		//test.multiplicationTable_A();
		test.multiplicationTable_B();
		
	}
}
