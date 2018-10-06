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
    
  �� Schedulers.from(executor)
    - 
==================================================================================================*/
package com.eun1310434.java.rx.scheduler;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class Rx_06_00_Scheduler_Executor {
	public void run() { 
		final int THREAD_NUM = 2;
		
		String[] data = {"RED", "GREEN", "BLUE"};
		Observable<String> source = Observable.fromArray(data);
		Executor executor = Executors.newFixedThreadPool(THREAD_NUM);
		
		source.subscribeOn(Schedulers.from(executor)).subscribe(Log::i);
		source.subscribeOn(Schedulers.from(executor)).subscribe(Log::i);
		CommonUtils.sleep(1000);		
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		Rx_06_00_Scheduler_Executor test = new Rx_06_00_Scheduler_Executor();
		test.run();
	}
}
