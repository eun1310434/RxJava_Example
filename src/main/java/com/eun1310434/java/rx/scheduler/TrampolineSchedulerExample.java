/*==================================================================================================
□ INFORMATION
  ○ Data : 05.Oct.2018
  ○ Mail : eun1310434@gmail.com
  ○ WebPage : https://eun1310434.github.io/
  ○ Reference
    - RxJava 프로그래밍 P185

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

  ○ Schedulers.trampoline()
    - 새로운 스레드를 생성하지 않고 main 스레드에서 모든 작업을 실행
    - 현재 스레드에 무한한 크기의 대기 행렬(Queue)를 자동으로 생성
    - 큐에 작업을 넣은 후 1개씩 꺼내서 동작
    - 구독 순서가 바뀌지 않음
==================================================================================================*/
package com.eun1310434.java.rx.scheduler;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.ScheduleTest;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class TrampolineSchedulerExample implements ScheduleTest{
	
	@Override
	public void run() {
		String[] orgs = {"RED", "GREEN", "BLUE"};	
		trampolineSchedulerStart(orgs);
	}

	public void trampolineSchedulerStart(String[] orgs ){
		schedulerSubscribe(observableSet(orgs, "Trampline() : A"));
		schedulerSubscribe(observableSet(orgs, "Trampline() : B"));
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public Observable<String> observableSet(String[] orgs, String title) {
		CommonUtils.exampleStart(title); // 시작 시간을 표시하는 유틸리티 메서드
		return Observable
				.fromArray(orgs)
				.map(str -> title +" - "+str);
	}


	public void schedulerSubscribe(Observable<String> scheduler) {
		scheduler
		.subscribeOn(Schedulers.trampoline())
		.subscribe(str->Log.i(str));	
	}

	public static void main(String[] args) { 
		TrampolineSchedulerExample test = new TrampolineSchedulerExample();
		test.run();
	}
}
