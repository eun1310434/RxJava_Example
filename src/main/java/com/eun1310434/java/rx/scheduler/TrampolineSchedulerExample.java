/*==================================================================================================
�� INFORMATION
  �� Data : 05.Oct.2018
  �� Mail : eun1310434@gmail.com
  �� WebPage : https://eun1310434.github.io/
  �� Reference
    - RxJava ���α׷��� P185

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

  �� Schedulers.trampoline()
    - ���ο� �����带 �������� �ʰ� main �����忡�� ��� �۾��� ����
    - ���� �����忡 ������ ũ���� ��� ���(Queue)�� �ڵ����� ����
    - ť�� �۾��� ���� �� 1���� ������ ����
    - ���� ������ �ٲ��� ����
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
		CommonUtils.exampleStart(title); // ���� �ð��� ǥ���ϴ� ��ƿ��Ƽ �޼���
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
