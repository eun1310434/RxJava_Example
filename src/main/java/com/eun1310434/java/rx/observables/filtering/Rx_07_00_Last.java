/*==================================================================================================
□ INFORMATION
○ Data : 28.08.2018
○ Mail : eun1310434@gmail.com
○ WebPage : https://eun1310434.github.io/
○ Reference
- RxJava 프로그래밍 P97
     
□ FUNCTION
○ 
   
□ Study
○ last()
- emit only the last item (or the last item that meets some condition) emitted by an Observable
- If you are only interested in the last item emitted by an Observable, 
  or the last item that meets some criteria, you can filter the Observable with the Last operator.
- last(default) : Observable의 마지막 값을 필터함. 만약 값없이 완료되면 대신 기본값을 리턴

○ RxJava Generic Method Interface
01) Predicate<T> : boolean test(T t) // return to true or false
02) Consumer<T> : void accept(T t) // no return
03) Function<T,R> : R apply(T t) // return to R type

==================================================================================================*/
package com.eun1310434.java.rx.observables.filtering;


import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;

import io.reactivex.Observable;
import io.reactivex.Single;

public class Rx_07_00_Last implements RxTest {

	@Override
	public void marbleDiagram() { 
		CommonUtils.exampleStart("01) MarbleDiagram : last()");
		Integer[] numbers = {100, 200, 300, 400, 500};
		Single<Integer> single;
		
		//last 
		single = Observable.fromArray(numbers).last(999);//if no value, return to 999
		single.subscribe(Log::i);
		
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		Rx_07_00_Last test = new Rx_07_00_Last();
		test.marbleDiagram();
	}
}
