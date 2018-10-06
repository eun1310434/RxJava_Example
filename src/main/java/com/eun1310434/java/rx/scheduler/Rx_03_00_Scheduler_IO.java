/*==================================================================================================
□ INFORMATION
  ○ Data : 05.Oct.2018
  ○ Mail : eun1310434@gmail.com
  ○ WebPage : https://eun1310434.github.io/
  ○ Reference
    - RxJava 프로그래밍 P182

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
    - Schedulers.single();
    - Schedulers.trampoline();
    - Schedulers.from(executor);

  ○ Schedulers.io()
    - 네트워크
    - 입/출력 작업
    - 계산 스케줄러와 다르게 기본으로 생성되는 스레드 개수가 다름
      * 계산 스케줄러는 CPU 개수 만큼 스레드를 생성하지만 IO스케쥴러는 필요할 때 마다 스레드 계속 생성
==================================================================================================*/
package com.eun1310434.java.rx.scheduler;

import java.io.File;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.ScheduleTest;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class Rx_03_00_Scheduler_IO implements ScheduleTest{
	
	@Override
	public void run() {
		//list up files on C drive root
		IOSchedulerStart("c:\\");
	}

	public void IOSchedulerStart(String root ){
		File[] files = new File(root).listFiles();	
		schedulerSubscribe(observableSet(files, "IO() : A"));
		schedulerSubscribe(observableSet(files, "IO() : B"));
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public Observable<String> observableSet(File[] files, String title) {
		CommonUtils.exampleStart(title); // 시작 시간을 표시하는 유틸리티 메서드
		return Observable
				.fromArray(files)
				//.filter(f -> !f.isDirectory()) //Directory를 제외한 파일만 선별
				.map(f -> f.getAbsolutePath()); //Path 출력;
	}


	public void schedulerSubscribe(Observable<String> scheduler) {
		scheduler
		.subscribeOn(Schedulers.io())
		.subscribe(str->Log.i(str));	
	}

	
	public static void main(String[] args) { 
		Rx_03_00_Scheduler_IO test = new Rx_03_00_Scheduler_IO();
		test.run();
	}
}
