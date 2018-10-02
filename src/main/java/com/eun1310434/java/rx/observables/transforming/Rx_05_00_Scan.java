/*==================================================================================================
□ INFORMATION
○ Data : 02.Oct.2018
○ Mail : eun1310434@gmail.com
○ WebPage : https://eun1310434.github.io/
○ Reference : RxJava 프로그래밍 P135

□ FUNCTION
○ 
   
□ Study
○ Transforming(변환) : Operators that transform items that are emitted by an Observable.

○ scan() : apply a function to each item emitted by an Observable, sequentially, and emit each successive value
- source가 Observable(String) 타입으로 
- 실행할 때마다 입력값에 맞는 중간 결과 및 최종 결과를 구독자에게 발행

○ reduce() 
- source가 Maybe<String> 타입으로
- 마지막 값이 입력되지 않거나 onComplete이벤트가 발생하지 않으면 구독자에게 값을 발행하지 않기에 Maybe<String> 타입으로 선언

==================================================================================================*/
package com.eun1310434.java.rx.observables.transforming;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;
import io.reactivex.Observable;

public class Rx_05_00_Scan implements RxTest {
	public void marbleDiagram() { 
		String[] colors = {"1", "2", "3"}; //1,3,5
		ObservableSet("Scan()",colors);
	}
	
	public void ObservableSet(String title, String[] colors){
		CommonUtils.exampleStart(title);
		Observable<String> source = Observable
				.fromArray(colors)
				.scan((ball1, ball2) -> ball2 + "(" + ball1 + ")");
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}	
	
	public static void main(String[] args) { 
		Rx_05_00_Scan demo = new Rx_05_00_Scan();
		demo.marbleDiagram();
	}
}

