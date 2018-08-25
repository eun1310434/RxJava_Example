/*==================================================================================================
�� INFORMATION
�� Data : 25.08.2018
�� Mail : eun1310434@naver.com
�� WebPage : https://eun1310434.github.io/
�� Reference
- RxJava ���α׷��� P76

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

�� Subject Class
- Subject = Observable(:Data Source) + Subscriber(:Data Receiver)
- ������ ������ ������ ���ÿ� ����
- Cold Observable�� Hot Observable�� �ٲ�
* Cold Observable -> ( Subject Class ) -> Hot Observable
- Issue and Process the Data
- Kind
01) AsyncSubject
02) BehaviorSubject
03) PublishSubject
04) ReplaySubject
05) CompletableSubject
06) MaybeSubject
07) SingleSubject
08) UnicastSubject

�� PublishSubject
- ���� �ش� �ð��� �߻��� �����͸� �״�� �����ڿ��� ���� ����
==================================================================================================*/
package com.eun1310434.java.rx.subject;

import static com.eun1310434.java.rx.common.Shape.*;

import com.eun1310434.java.rx.common.CommonUtils;

import io.reactivex.subjects.PublishSubject;

public class Rx_Subject_03_PublishSubject {
	public void publishSubjectTest() { 
		// 1. PublishSubject
		CommonUtils.exampleStart("01) PublishSubjectTest");
		PublishSubject<String> subject = PublishSubject.create();
		subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));// Subscriber - 1
		subject.onNext(RED);
		subject.onNext(GREEN);
		subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));// Subscriber - 2
		subject.onNext(BLUE);
		subject.onNext(PUPPLE);
		subject.subscribe(data -> System.out.println("Subscriber #3 => " + data)); // Subscriber - 3
		subject.onComplete();
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		Rx_Subject_03_PublishSubject test = new Rx_Subject_03_PublishSubject();
		test.publishSubjectTest();
	}
}
