/*==================================================================================================
�� INFORMATION
  �� Data : 05.Oct.2018
  �� Mail : eun1310434@gmail.com
  �� WebPage : https://eun1310434.github.io/
  �� Reference
    - RxJava ���α׷��� P179

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
    - Schedulers.single();
    - Schedulers.io();
    - Schedulers.trampoline();
    - Schedulers.computation();

  �� Schedulers.computation()
    - ���� �����ٷ�
    - CPU�� �����ϴ� ���� �����ٷ�
    - ���������� ������ Ǯ�� �����ϸ� ������ ������ �⺻������ ���μ��� ������ ����
    
  �� interval()�Լ� ����
    - @ShcedulerSupport(SchedulerSupport.COMPUTATION)
      public static Observable<Long> interval(long period, TimeUnit unit)
     
    - @ShcedulerSupport(SchedulerSupport.CUSTOM)
      public static Observable<Long> interval(long period, TimeUnit unit, Scheduler scheduler)
      
  �� Interval()
    - create an Observable that emits a sequence of integers spaced by a given time interval
    - ������ ���� ����Ǳ� ������ ���� �뵵�� ���� ���
    - ��� �����ٷ����� ����(���� ������X)
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
		 * ��Ȥ ������ ��� �����췯�� ���ؼ� ����Ǵµ� �̴� �ʹ����� ���ó���� ���Ͽ� �Ͼ
		 */
	}

	public void CompuationSchedulerStart(String[] objs){
		schedulerSubscribe(observableSet(objs, "computation() : A"));
		schedulerSubscribe(observableSet(objs, "computation() : B"));
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public Observable<String> observableSet(String[] objs, String title) {
		CommonUtils.exampleStart(title); // ���� �ð��� ǥ���ϴ� ��ƿ��Ƽ �޼���
		return Observable
				.fromArray(objs)
				.zipWith(Observable.interval(100L,TimeUnit.MILLISECONDS), (a,b) -> a);
		//zipWith : ������ �����͸� ��ġ�ų� �ϳ��� ���Ҽ� ����.ex) (a,b) -> a
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
