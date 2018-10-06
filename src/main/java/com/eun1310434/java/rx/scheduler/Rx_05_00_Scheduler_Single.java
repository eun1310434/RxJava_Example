/*==================================================================================================
□ INFORMATION
  ○ Data : 06.Oct.2018
  ○ Mail : eun1310434@gmail.com
  ○ WebPage : https://eun1310434.github.io/
  ○ Reference
    - RxJava 프로그래밍 P186

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
    - Schedulers.computation();
    - Schedulers.io();
    - Schedulers.trampoline();
    - Schedulers.single();
    - Schedulers.from(executor);

  ○ Schedulers.single()
    - 싱글 스레드 스케줄러는 RxSingleScheduler-1 스레드에서 실행
    - 결국 단일 스레드만 사용
==================================================================================================*/
package com.eun1310434.java.rx.scheduler;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.ScheduleTest;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class Rx_05_00_Scheduler_Single implements ScheduleTest{

	@Override
	public void run() {
		singleSchedulerStart();
	}

	public void singleSchedulerStart(){
		schedulerSubscribe(observableSet(100,5), "Single() : A");
		schedulerSubscribe(observableSet(200,5), "Single() : B");
		schedulerSubscribe(observableSet(300,5), "Single() : C");
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public Observable<Integer> observableSet(int start,int count) {
		return Observable.range(start,count);
		//현재 스래드에서 start 숫자부터 count개 숫자 발행 
	}


	public void schedulerSubscribe(Observable<Integer> scheduler, String title) {
		CommonUtils.exampleStart(title); // 시작 시간을 표시하는 유틸리티 메서드
		scheduler
		.subscribeOn(Schedulers.single())
		.subscribe(str -> Log.i(title + " - " + str));	
	}

	
	public static void main(String[] args) { 
		Rx_05_00_Scheduler_Single test = new Rx_05_00_Scheduler_Single();
		test.run();
	}

}
