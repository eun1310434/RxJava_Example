/*==================================================================================================
□ INFORMATION
○ Data : 05.Sep.2018
○ Mail : eun1310434@gmail.com
○ WebPage : https://eun1310434.github.io/
○ Reference : RxJava 프로그래밍 P87

□ FUNCTION
○ 
   
□ Study
○ Transforming(변환) : Operators that transform items that are emitted by an Observable.

○ map()
- transform the items emitted by an Observable by applying a function to each item
- The Map operator applies a function of your choosing to each item emitted by the source Observable, 
  and returns an Observable that emits the results of these function applications.

○ the original form of map() 
@CheckReturnValue
@SchedulerSupport(value="none")
public final <R> Observable<R> map(Function<> super T,? extends R> mapper)

- @CheckReturnValue : 반환값을 확인한다는 의미.
- @SchedulerSupport(value="none") : 스케줄러를 지원하지 않는다(현재 스레드에서 실행)

○ Parameter of map() method
Function<? super T,? extends R> mapper

○ RxJava Generic Method Interface
01) Predicate<T> : boolean test(T t) // return to true or false
02) Consumer<T> : void accept(T t) // no return
03) Function<T,R> : R apply(T t) // return to R type

==================================================================================================*/
package com.eun1310434.java.rx.observables.transforming;

import static com.eun1310434.java.rx.common.Shape.BLUE;
import static com.eun1310434.java.rx.common.Shape.GREEN;
import static com.eun1310434.java.rx.common.Shape.RED;
import static com.eun1310434.java.rx.common.Shape.YELLOW;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class Rx_04_01_Map implements RxTest {

	@Override
	public void marbleDiagram() {
		// 1. MarbleDiagram : map()
		String[] colors = { RED, YELLOW, GREEN, BLUE };
		
		ObservableSet("01) map()", colors , color -> "Color: " + color);

		Function<String, String> getDiamond = color -> "Color: " + color;
		ObservableSet("02) mapFunction()", colors , getDiamond);
		
		Function<String, Integer> getIndex = ball -> {
			switch (ball) {
			case "RED":
				return 1;
			case "YELLOW":
				return 2;
			case "GREEN":
				return 3;
			case "BLUE":
				return 4;
			default:
				return -1;
			}
		};
		ObservableSet("03) mapFunction() - switch", colors , getIndex);
		
	}
	
	public <T, R> void ObservableSet(String title, T[] strs, Function<T, R> fun){
		CommonUtils.exampleStart(title);
		Observable<R> source = Observable
				.fromArray(strs)
				.map(fun);
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}	


	public static void main(String[] args) {
		Rx_04_01_Map test = new Rx_04_01_Map();
		test.marbleDiagram();
	}
}
