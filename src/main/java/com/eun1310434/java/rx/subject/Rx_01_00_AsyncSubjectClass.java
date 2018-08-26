/*==================================================================================================
�� INFORMATION
�� Data : 24.08.2018
�� Mail : eun1310434@naver.com
�� WebPage : https://eun1310434.github.io/
�� Reference
- RxJava ���α׷��� P68

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

�� AsyncSubject
- �����ڰ� ������ ������  ���������� �Ѱ���
==================================================================================================*/
package com.eun1310434.java.rx.subject;

import com.eun1310434.java.rx.common.CommonUtils;
import static com.eun1310434.java.rx.common.Shape.*;

import io.reactivex.Observable;
import io.reactivex.subjects.AsyncSubject;

public class Rx_01_00_AsyncSubjectClass {

	public void marbleDiagram() {
		// 1. �Ϲ����� Subject ����
		CommonUtils.exampleStart("01) marbleDiagram()");

		AsyncSubject<String> subject = AsyncSubject.create();
		subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
		// ���������� ������ �Ͽ��� ������ ������ BLUE�� ���
		subject.onNext(RED);
		subject.onNext(GREEN);
		subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
		// ���������� ������ �Ͽ��� ������ ������ BLUE�� ���
		subject.onNext(BLUE);
		subject.onNext(PUPPLE);
		subject.subscribe(data -> System.out.println("Subscriber #3 => " + data));
		subject.onComplete();

		CommonUtils.exampleComplete();
	}

	public void asSubscriber() {
		// 2. Subscriber�� ���� Process
		CommonUtils.exampleStart("02) asSubscriber()");
		Float[] temperature = { 10.1f, 13.4f, 12.5f };
		Observable<Float> source = Observable.fromArray(temperature);

		AsyncSubject<Float> subject = AsyncSubject.create();
		subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
		// ������ ������ 12.5f�� ���

		source.subscribe(subject);
		// public abstract class Subject<T> extends Observable<T> implements
		// Observer<T>

		CommonUtils.exampleComplete();
	}

	public void afterComplete() {
		// 3. Subscriber�� ���� Process
		CommonUtils.exampleStart("03) afterComplete()");

		AsyncSubject<Integer> subject = AsyncSubject.create();
		subject.onNext(10);
		subject.onNext(11);
		subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
		subject.onNext(12);
		subject.onComplete();
		// Observable�� ���������� onComplete() �Լ� ȣ�� ���� onNext �̺�Ʈ�� ����
		subject.onNext(13);
		subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
		subject.subscribe(data -> System.out.println("Subscriber #3 => " + data));

		CommonUtils.exampleComplete();
	}

	public static void main(String[] args) {
		Rx_01_00_AsyncSubjectClass test = new Rx_01_00_AsyncSubjectClass();
		test.marbleDiagram();
		test.asSubscriber();
		test.afterComplete();
	}

}
