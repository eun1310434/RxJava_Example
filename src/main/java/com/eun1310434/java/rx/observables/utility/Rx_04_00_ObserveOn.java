/*==================================================================================================
�� INFORMATION
  �� Data : 07.Oct.2018
  �� Mail : eun1310434@gmail.com
  �� WebPage : https://eun1310434.github.io/
  �� Reference
    - RxJava ���α׷��� P193
    - http://rxmarbles.com/#switchMap

�� FUNCTION
�� 
   
�� Study
�� Utility(��ƿ��Ƽ) : A toolbox of useful Operators for working with Observables
�� Scheduler
  - �����ٷ��� RxJava �ڵ带 ��� �����忡�� �������� ������ �� �ִ�.
  - subscribeOn() �Լ��� observeOn() �Լ��� ��� �����ϸ� 
    Observable���� ������ �帧�� �߻��ϴ� ������� ó���� ����� �����ڿ��� �����ϴ� �����带 �и��� �� �ִ�.
    *subscribeOn() : Observable���� subscribe()�Լ��� ȣ���Ͽ� ������ �� ����Ǵ� �����带 ����
    *observeOn() : Observable���� ������ �帧�� �������� �Լ��� ��ġ�� ó���� �� ������ ��� Thread���� �Ͼ���� ����
�� ObserveOn()
  - specify the scheduler on which an observer will observe this Observable
  - observeOn()�� ���� �� ȣ���� �� ������ ȣ��Ǹ� �� �������� �����ϴ� �����带 �ٲ� �� �ִ�.
==================================================================================================*/
package com.eun1310434.java.rx.observables.utility;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class Rx_04_00_ObserveOn {
	public void basic() { 
		Integer[] data = { 1,2,3,4,5,6,7,8,9,10};
		Observable<Integer> source = Observable
				.fromArray(data)
				//.subscribeOn(Schedulers.trampoline())//<-Schedulers.trampoline()�� ���Ͽ� main Thread�� ����
				.map(num -> num + 48)
				.doOnNext(Log::d)
				.observeOn(Schedulers.computation())
				.filter(num -> (num %2) == 0);
		
		source.subscribe(Log::i);
		CommonUtils.sleep(500);
		CommonUtils.exampleComplete();
	}
	
	
	public static void main(String[] args) { 
		Rx_04_00_ObserveOn demo = new Rx_04_00_ObserveOn();
		demo.basic();
	}
}
