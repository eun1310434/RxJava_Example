/*==================================================================================================
□ INFORMATION
  ○ Data : 07.Oct.2018
  ○ Mail : eun1310434@gmail.com
  ○ WebPage : https://eun1310434.github.io/
  ○ Reference
    - RxJava 프로그래밍 P193
    - http://rxmarbles.com/#switchMap

□ FUNCTION
○ 
   
□ Study
○ Utility(유틸리티) : A toolbox of useful Operators for working with Observables
○ Scheduler
  - 스케줄러는 RxJava 코드를 어느 스레드에서 실행할지 지정할 수 있다.
  - subscribeOn() 함수와 observeOn() 함수를 모두 지정하면 
    Observable에서 데이터 흐름이 발생하는 스레드와 처리된 결과를 구독자에게 발행하는 스레드를 분리할 수 있다.
    *subscribeOn() : Observable에서 subscribe()함수를 호출하여 구독할 때 실행되는 스레드를 지정
    *observeOn() : Observable에서 생성한 흐름이 여기저기 함수를 거치며 처리될 때 동작이 어느 Thread에서 일어나는지 지정
○ ObserveOn()
  - specify the scheduler on which an observer will observe this Observable
  - observeOn()은 여러 번 호출할 수 있으며 호출되면 그 다음부터 동작하는 스레드를 바꿀 수 있다.
==================================================================================================*/
package com.eun1310434.java.rx.observables.utility;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class Rx_04_00_ObserveOn {
	public void basic() { 
		Integer[] data = { 1,2,3,4,5,6,7,8,9,10};
		Observable<Integer> source = Observable
				.fromArray(data)
				//.subscribeOn(Schedulers.trampoline())//<-Schedulers.trampoline()를 통하여 main Thread로 지정
				.map(num -> num + 48)
				.doOnNext(Log::d)
				.observeOn(Schedulers.computation())
				.filter(num -> (num %2) == 0);
		
		source.subscribe(Log::i);
		CommonUtils.sleep(500);
		CommonUtils.exampleComplete();
	}
	
	
	public static void main(String[] args) { 
		Rx_04_00_ObserveOn demo = new Rx_04_00_ObserveOn();
		demo.basic();
	}
}
