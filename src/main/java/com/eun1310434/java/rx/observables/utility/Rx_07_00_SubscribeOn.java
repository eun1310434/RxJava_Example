/*==================================================================================================
�� INFORMATION
  �� Data : 05.Oct.2018
  �� Mail : eun1310434@gmail.com
  �� WebPage : https://eun1310434.github.io/
  �� Reference
    - RxJava ���α׷��� P129
    - http://rxmarbles.com/#switchMap

�� FUNCTION
  �� 
   
�� Study
  �� Utility(��ƿ��Ƽ) : A toolbox of useful Operators for working with Observables
  �� Scheduler
    - �����ٷ��� RxJava �ڵ带 ��� �����忡�� �������� ������ �� �ִ�.
    - subscribeOn() �Լ��� observeOn() �Լ��� ��� �����ϸ� 
      Observable���� ������ �帧�� �߻��ϴ� ������� ó���� ����� �����ڿ��� �����ϴ� �����带 �и��� �� �ִ�.
    - subscribeOn() : Observable���� subscribe()�Լ��� ȣ���Ͽ� ������ �� ����Ǵ� �����带 ����
    - observeOn() : Observable���� ������ �帧�� �������� �Լ��� ��ġ�� ó���� �� ������ ��� Thread���� �Ͼ���� ����
  �� subscribeOn()
    - specify the scheduler an Observable should use when it is subscribed to
    - �ѹ� ȣ������ �� ������ �����带 �����ϸ� ���Ŀ��� �ٽ� ȣ���ص� �����尡 �ٲ��� �ʴ´�.
==================================================================================================*/
package com.eun1310434.java.rx.observables.utility;

import static com.eun1310434.java.rx.common.Shape.GREEN;
import static com.eun1310434.java.rx.common.Shape.RED;
import static com.eun1310434.java.rx.common.Shape.YELLOW;
import static com.eun1310434.java.rx.common.Shape.pentagon;
import static com.eun1310434.java.rx.common.Shape.star;
import static com.eun1310434.java.rx.common.Shape.triangle;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;
import com.eun1310434.java.rx.common.Shape;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class Rx_07_00_SubscribeOn implements RxTest{
	@Override
	public void marbleDiagram() {
		String[] objs = {star(RED), triangle(YELLOW), pentagon(GREEN)};
		
		subscribeOnWithobserveOn(objs,"subscribeOn && observeOn");
		subscribeOn(objs,"Only SubscribeOn");
	}
	
	public void subscribeOnWithobserveOn(String[] objs, String title) {
		CommonUtils.exampleStart(title); // ���� �ð��� ǥ���ϴ� ��ƿ��Ƽ �޼���
		Observable<String> source = Observable
				.fromArray(objs)
				.subscribeOn(Schedulers.newThread())// <- subscribeOn
				.doOnNext(data -> Log.v("Original data = " + data))
				.observeOn(Schedulers.newThread())// <- observeOn
				.map(data -> Shape.flip(data));
		source.subscribe(str -> Log.i(str));
		CommonUtils.sleep(500);
		CommonUtils.exampleComplete();
	
		
		
		/*
		 *  ������ ������ �帧�� �߻��ϴ� ������� flip() �Լ��� ���ļ� �����ڿ��� ���޵Ǵ� �����尡 �ٸ�.
		 *  ����, subscribeOn()�� observeOn() �Լ��� � �����ٷ�( �� ������ �����ٷ�)�� ������ ��
		 */
	}
	
	public void subscribeOn(String[] objs, String title) {
		CommonUtils.exampleStart(title); // ���� �ð��� ǥ���ϴ� ��ƿ��Ƽ �޼���
		Observable<String> source = Observable.fromArray(objs)
				.doOnNext(data -> Log.v("Origianl data = " + data))
				.subscribeOn(Schedulers.newThread())
				//removed .observeOn(Schedulers.newThread())
				.map(data -> Shape.flip(data));
		source.subscribe(str -> Log.i(str));
		CommonUtils.sleep(500);
		/*
		 * observeOn() �Լ��� �������� ������ subscribeOn()�Լ��� ������ �����忡�� ��� ���� ����
		 */
	}
	
	public static void main(String[] args) { 
		Rx_07_00_SubscribeOn test = new Rx_07_00_SubscribeOn();
		test.marbleDiagram();
	}

}
