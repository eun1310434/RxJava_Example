/*==================================================================================================
�� INFORMATION
�� Data : 25.08.2018
�� Mail : eun1310434@naver.com
�� WebPage : https://eun1310434.github.io/
�� Reference
- RxJava ���α׷��� P82

�� FUNCTION
�� 
   
�� Study
�� Data Source
- Observable
- Single
- Maybe
- Subject
- Completable

�� Data Receiver
- Subscriber : Observable�� ������ ���� "subscribe()" Calling. 
- Observer : RxJava�� observer pattern�� implement. 
- Consumer : RxJava 2������ Comsumer�� Parameter�� ���

�� Cold Observables
- subscribe() method�� calling ���� ������ Data Publish�� �̷�� ���� ����

�� Hot Observables
- subscribe() method�� calling ���� �ʾƵ� Data Publish�� �̷����
- Many Subscriber�� ���� �� useful
- ��, Subscribe�� Publish�� Point���� ���� Data Publish�� �߻�

�� ConnectableObservable
- Cold Observable�� Hot Observable�� Change
* Cold Observable -> ( Subject Class ) -> Hot Observable
- Subject = Observable(:Data Source) + Subscriber(:Data Receiver)
- Object�� create �Ϸ��� publish() method�� Calling ��.
- subscribe() �� �ƴ� connect()�� ȣ���ϴ� �������� Data publish�� �̷����.
==================================================================================================*/
package com.eun1310434.java.rx.connetableobservable;
import static com.eun1310434.java.rx.common.Shape.*;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.RxTest;
import java.util.concurrent.TimeUnit;


import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public class Rx_ConnectableObservable_01_Connect implements RxTest{

	@Override
	public void marbleDiagram() { 
		// 1. marbleDiagram
		CommonUtils.exampleStart("01) marbleDiagram");
		
		String[] dt = {RED, GREEN, BLUE}; 
		Observable<String> balls = Observable.interval(1000L, TimeUnit.MILLISECONDS) // parameter(�ð�, �ð�����)
				.map(Long::intValue)
				.map(i -> dt[i])
				.take(dt.length);
		
		ConnectableObservable<String> source = balls.publish();
		//Object�� create �Ϸ��� publish() method�� Calling ��.
		
		source.subscribe(data -> System.out.println("Subscriber #1 => " + data)); 
		source.subscribe(data -> System.out.println("Subscriber #2 => " + data)); 
		source.connect();
		//subscribe() �� �ƴ� connect()�� ȣ���ϴ� �������� Data publish�� �̷����.
		
		CommonUtils.sleep(1500);
		source.subscribe(data -> System.out.println("Subscriber #3 => " + data)); 
		CommonUtils.sleep(1500);
		source.subscribe(data -> System.out.println("Subscriber #4 => " + data)); 
		CommonUtils.sleep(1500);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		Rx_ConnectableObservable_01_Connect demo = new Rx_ConnectableObservable_01_Connect();
		demo.marbleDiagram();
	}

}
