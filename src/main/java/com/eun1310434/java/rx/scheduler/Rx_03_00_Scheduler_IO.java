/*==================================================================================================
�� INFORMATION
  �� Data : 05.Oct.2018
  �� Mail : eun1310434@gmail.com
  �� WebPage : https://eun1310434.github.io/
  �� Reference
    - RxJava ���α׷��� P182

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
    - Schedulers.single();
    - Schedulers.trampoline();
    - Schedulers.from(executor);

  �� Schedulers.io()
    - ��Ʈ��ũ
    - ��/��� �۾�
    - ��� �����ٷ��� �ٸ��� �⺻���� �����Ǵ� ������ ������ �ٸ�
      * ��� �����ٷ��� CPU ���� ��ŭ �����带 ���������� IO�����췯�� �ʿ��� �� ���� ������ ��� ����
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
		CommonUtils.exampleStart(title); // ���� �ð��� ǥ���ϴ� ��ƿ��Ƽ �޼���
		return Observable
				.fromArray(files)
				//.filter(f -> !f.isDirectory()) //Directory�� ������ ���ϸ� ����
				.map(f -> f.getAbsolutePath()); //Path ���;
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
