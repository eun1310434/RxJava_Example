/*==================================================================================================
�� INFORMATION
�� Data : 04.Sep.2018
�� Mail : eun1310434@gmail.com
�� WebPage : https://eun1310434.github.io/
�� Reference : RxJava ���α׷��� P82

�� FUNCTION
�� 
   
�� Study
�� ConnectableObservable
- �ϳ��� �����͸� ���� �����ڿ��� ���ÿ� ���� �� �� ���
- ������ subscribe() �Լ� ȣ���� �ƴ� connect() �Լ��� ȣ���� �������� 
   subscribe() �Լ��� ȣ���� �����ڿ��� ������ ����
- Cold Observable -> Hot Observable
- Subject = Observable(:Data Source) + Subscriber(:Data Receiver)

�� Cold Observables
- subscribe() method�� calling ���� ������ Data Publish�� �̷�� ���� ����

�� Hot Observables
- subscribe() method�� calling ���� �ʾƵ� Data Publish�� �̷����
- Many Subscriber�� ���� �� useful
- ��, Subscribe�� Publish�� Point���� ���� Data Publish�� �߻�
==================================================================================================*/
package com.eun1310434.java.rx.observables.connectableobservable;
import static com.eun1310434.java.rx.common.Shape.*;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.RxTest;
import java.util.concurrent.TimeUnit;


import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public class Rx_01_00_Connect implements RxTest{

	@Override
	public void marbleDiagram() { 
		// 1. marbleDiagram
		CommonUtils.exampleStart("01) marbleDiagram");
		
		String[] dt = {RED, GREEN, BLUE}; 
		Observable<String> balls = Observable
				.interval(1000L, TimeUnit.MILLISECONDS) // parameter(�ð�, �ð�����)�� �ð��� ���� �������� publish ��
				.map(Long::intValue)
				.map(i -> dt[i])
				.take(dt.length);
		
		ConnectableObservable<String> source = balls.publish();
		//Object�� create �Ϸ��� publish() method�� Calling ��.
		
		source.subscribe(data -> System.out.println("Subscriber #1 => " + data)); 
		source.connect();
		source.subscribe(data -> System.out.println("Subscriber #2 => " + data)); 

		//subscribe() �� �ƴ� connect()�� ȣ���ϴ� �������� Data publish�� �̷����.
		
		CommonUtils.sleep(2500);
		source.subscribe(data -> System.out.println("Subscriber #3 => " + data)); 
		CommonUtils.sleep(1000);
		
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		Rx_01_00_Connect test = new Rx_01_00_Connect();
		test.marbleDiagram();
	}

}
