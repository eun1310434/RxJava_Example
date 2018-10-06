/*==================================================================================================
�� INFORMATION
  �� Data : 05.Oct.2018
  �� Mail : eun1310434@gmail.com
  �� WebPage : https://eun1310434.github.io/
  �� Reference
    - RxJava ���α׷��� P129

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

  �� Schedulers.newThread()
    - ���ο� �����带 ���� ���� 
     
==================================================================================================*/
package com.eun1310434.java.rx.scheduler;


import static com.eun1310434.java.rx.common.Shape.BLUE;
import static com.eun1310434.java.rx.common.Shape.GREEN;
import static com.eun1310434.java.rx.common.Shape.RED;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.ScheduleTest;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class NewThreadSchedulerExample implements ScheduleTest{
	
	@Override
	public void run() {

		String[] objs  = {RED, GREEN, BLUE};
		newSchedulerStart(objs);
	}

	public void newSchedulerStart(String[] objs){
		schedulerSubscribe(observableSet(objs, "newThread() : A"));
		schedulerSubscribe(observableSet(objs, "newThread() : B"));
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public Observable<String> observableSet(String[] objs, String title) {
		CommonUtils.exampleStart(title); // ���� �ð��� ǥ���ϴ� ��ƿ��Ƽ �޼���
		return Observable
				.fromArray(objs)
				.map(data -> title + " - "+ data);
	}

	//Schedulers.newThread()
	public void schedulerSubscribe(Observable<String> scheduler) {
		scheduler
		.subscribeOn(Schedulers.newThread())
		.subscribe(str->Log.i(str));	
	}

	
	public static void main(String[] args) { 
		NewThreadSchedulerExample test = new NewThreadSchedulerExample();
		test.run();
	}
}
