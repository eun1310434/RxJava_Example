/*==================================================================================================
□ INFORMATION
○ Data : 05.Sep.2018
○ Mail : eun1310434@gmail.com
○ WebPage : https://eun1310434.github.io/
○ Reference : RxJava 프로그래밍 P90

□ FUNCTION
○ 
   
□ Study
○ Transforming(변환) : Operators that transform items that are emitted by an Observable.

○ flatMap()
- flatMap() 함수는 map()함수와 다르게 1:1 또는 1:N 함수이다.
- 결과값으로 Observable 이 나온다.
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
    BiFunction<? super T, ? super U, ? extends R> resultSelector
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

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

public class Rx_04_02_FlatMap implements RxTest {

	@Override
	public void marbleDiagram() { 
		String[] colors = new String [] {RED, GREEN, BLUE}; 
		
		Function<String, Observable<String>> getDoubleDiamonds = color -> Observable.just("Color1 : "+ color + "Color2 : "+ color);
		ObservableSet("1-1.flatMap()",colors, getDoubleDiamonds );		
		ObservableSet("1-2.flatMap() - Lambda", colors, (Function<String, Observable<String>>) color -> Observable.just("Color1 : "+ color + "Color2 : "+ color));
		ObservableSet("1-3.flatMap() - Lambda", colors, (Function<String, Observable<String>>) color -> Observable.just(color), (colorA, colorB) -> "Color1 : "+ colorA + "Color2 : "+ colorB);
		

		Function<Integer, Observable<String>> getMultiplicationTable = num -> Observable.range(1,9).map(row -> num + " * " + row + " = " + num*row);
		ObservableSet("2-1.flatMap() - Anony", 2, getMultiplicationTable);
		ObservableSet("2-2.flatMap() - Lambda", 2, num -> Observable.range(1,9).map(row -> num + " * " + row + " = " + num*row));
		ObservableSet("2-3.flatMap() - Lambda", 2, num -> Observable.range(1,9), (num, row) ->  num + " * " + row + " = " + num*row);
		
	}

	public <T, R> void ObservableSet(String title, T[] strs, Function<? super T, ? extends ObservableSource<? extends R>> fun){
		CommonUtils.exampleStart(title);
		Observable<R> source = Observable
				.fromArray(strs)
				.flatMap(fun);
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}	

	public <T, U, R> void ObservableSet(String title, T[] strs, Function<? super T, ? extends ObservableSource<? extends U>> fun, BiFunction<? super T, ? super U, ? extends R> bifun){
		CommonUtils.exampleStart(title);
		Observable<R> source = Observable
				.fromArray(strs)
				.flatMap(fun, bifun);
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}	

	public <T, R> void ObservableSet(String title, T dan, Function<? super T, ? extends ObservableSource<? extends R>> fun){
		CommonUtils.exampleStart(title);
		Observable<R> source = Observable
				.just(dan)
				.flatMap(fun);
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}	

	public <T, U, R> void ObservableSet(String title, T dan, Function<? super T, ? extends ObservableSource<? extends U>> fun, BiFunction<? super T, ? super U, ? extends R> bifun){
		CommonUtils.exampleStart(title);
		Observable<R> source = Observable
				.just(dan)
				.flatMap(fun, bifun);
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}	

	
	public static void main(String[] args) { 
		Rx_04_02_FlatMap test = new Rx_04_02_FlatMap();
		test.marbleDiagram();
		
	}
}
