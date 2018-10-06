/*==================================================================================================
□ INFORMATION
  ○ Data : 05.Oct.2018
  ○ Mail : eun1310434@gmail.com
  ○ WebPage : https://eun1310434.github.io/
  ○ Reference
    - RxJava 프로그래밍 P179

□ FUNCTION
  ○ 
   
□ Study
  ○ Utility(유틸리티) : A toolbox of useful Operators for working with Observables
  ○ Scheduler
    - 스케줄러는 RxJava 코드를 어느 스레드에서 실행할지 지정할 수 있다.
    - RxJava에서 추천하는 스케줄러는 크게 세가지
      : Schedulers.computation(), Schedulers.io(), Schedulers.trampoline()
    - subscribeOn() 함수와 observeOn() 함수를 모두 지정하면 
      Observable에서 데이터 흐름이 발생하는 스레드와 처리된 결과를 구독자에게 발행하는 스레드를 분리할 수 있다.
      * subscribeOn() : Observable에서 subscribe()함수를 호출하여 구독할 때 실행되는 스레드를 지정
      * observeOn() : Observable에서 생성한 흐름이 여기저기 함수를 거치며 처리될 때 동작이 어느 Thread에서 일어나는지 지정

  ○ Scheduler Types
    - Schedulers.newThread();
    - Schedulers.single();
    - Schedulers.io();
    - Schedulers.trampoline();
    - Schedulers.computation();

  ○ Schedulers.computation()
    - 계산용 스케줄러
    - CPU에 대응하는 계산용 스케줄러
    - 내부적으로 스레드 풀을 생성하며 스레드 개수는 기본적으로 프로세서 개수와 동일
    
  ○ interval()함수 원형
    - @ShcedulerSupport(SchedulerSupport.COMPUTATION)
      public static Observable<Long> interval(long period, TimeUnit unit)
     
    - @ShcedulerSupport(SchedulerSupport.CUSTOM)
      public static Observable<Long> interval(long period, TimeUnit unit, Scheduler scheduler)
      
  ○ Interval()
    - create an Observable that emits a sequence of integers spaced by a given time interval
    - 영원히 지속 실행되기 때문에 폴링 용도로 많이 사용
    - 계산 스케줄러에서 실행(현재 스레드X)
==================================================================================================*/
package com.eun1310434.java.rx.scheduler;


import static com.eun1310434.java.rx.common.Shape.BLUE;
import static com.eun1310434.java.rx.common.Shape.GREEN;
import static com.eun1310434.java.rx.common.Shape.RED;

import java.util.concurrent.TimeUnit;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.ScheduleTest;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


public class ComputationSchedulerExample implements ScheduleTest{
	
	@Override
	public void run() {
		String[] objs = {RED, GREEN, BLUE};
		CompuationSchedulerStart(objs);
		/*
		 * 간혹 동일한 계산 스케쥴러에 의해서 실행되는데 이는 너무빠른 계산처리로 인하여 일어남
		 */
	}

	public void CompuationSchedulerStart(String[] objs){
		schedulerSubscribe(observableSet(objs, "computation() : A"));
		schedulerSubscribe(observableSet(objs, "computation() : B"));
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public Observable<String> observableSet(String[] objs, String title) {
		CommonUtils.exampleStart(title); // 시작 시간을 표시하는 유틸리티 메서드
		return Observable
				.fromArray(objs)
				.zipWith(Observable.interval(100L,TimeUnit.MILLISECONDS), (a,b) -> a);
		//zipWith : 각각의 데이터를 합치거나 하나만 취할수 있음.ex) (a,b) -> a
	}


	public void schedulerSubscribe(Observable<String> scheduler) {
		scheduler
		.subscribeOn(Schedulers.computation())
		.subscribe(str->Log.i(str));	
	}

	
	public static void main(String[] args) { 
		ComputationSchedulerExample test = new ComputationSchedulerExample();
		test.run();
	}
}
