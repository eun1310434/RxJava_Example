/*==================================================================================================
�� INFORMATION
  �� Data : 06.Oct.2018
  �� Mail : eun1310434@gmail.com
  �� WebPage : https://eun1310434.github.io/
  �� Reference
    - RxJava ���α׷��� P186

�� FUNCTION
  �� 
   
�� Study
  �� Utility(��ƿ��Ƽ) : A toolbox of useful Operators for working with Observables
  �� Scheduler
    - �����ٷ��� RxJava �ڵ带 ��� �����忡�� �������� ������ �� �ִ�.
    - RxJava���� ��õ�ϴ� �����ٷ��� ũ�� ������
      : Schedulers.computation(), Schedulers.io(), Schedulers.trampoline()
    - subscribeOn() �Լ��� observeOn() �Լ��� ��� �����ϸ� 
      Observable���� ������ �帧�� �߻��ϴ� ������� ó���� ����� �����ڿ��� �����ϴ� �����带 �и��� �� �ִ�.
      * subscribeOn() : Observable���� subscribe()�Լ��� ȣ���Ͽ� ������ �� ����Ǵ� �����带 ����
      * observeOn() : Observable���� ������ �帧�� �������� �Լ��� ��ġ�� ó���� �� ������ ��� Thread���� �Ͼ���� ����

  �� Scheduler Types
    - Schedulers.newThread();
    - Schedulers.computation();
    - Schedulers.io();
    - Schedulers.trampoline();
    - Schedulers.single();
    - Schedulers.from(executor);

  �� Schedulers.single()
    - �̱� ������ �����ٷ��� RxSingleScheduler-1 �����忡�� ����
    - �ᱹ ���� �����常 ���
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
		//���� �����忡�� start ���ں��� count�� ���� ���� 
	}


	public void schedulerSubscribe(Observable<Integer> scheduler, String title) {
		CommonUtils.exampleStart(title); // ���� �ð��� ǥ���ϴ� ��ƿ��Ƽ �޼���
		scheduler
		.subscribeOn(Schedulers.single())
		.subscribe(str -> Log.i(title + " - " + str));	
	}

	
	public static void main(String[] args) { 
		Rx_05_00_Scheduler_Single test = new Rx_05_00_Scheduler_Single();
		test.run();
	}

}
