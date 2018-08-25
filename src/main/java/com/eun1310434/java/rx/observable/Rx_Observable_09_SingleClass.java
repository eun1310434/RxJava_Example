/*==================================================================================================
�� INFORMATION
�� Data : 24.08.2018
�� Mail : eun1310434@naver.com
�� WebPage : https://eun1310434.github.io/
�� Reference
- RxJava ���α׷��� P61
     
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

�� Single Class
- Observable Ŭ������ �����͸� �����ϰ� ������ �� ������ Single Ŭ������ ���� 1���� �����͸� �����ϵ��� ��
- ������ �ϳ��� ����� ���ÿ� ����(onSuccess)
* onSuccess() = onNext() + onComplete()
- Single Ŭ������ ������ ����Ŭ �Լ��� onSuccess(T value)�Լ��� onError()�Լ��� ����
==================================================================================================*/

package com.eun1310434.java.rx.observable;

import com.eun1310434.java.rx.common.CommonUtils;

import io.reactivex.Observable;
import io.reactivex.Single;

public class Rx_Observable_09_SingleClass {
	public void just() {
		// 1. �Ϲ����� Single ����
		CommonUtils.exampleStart("01) just()");
		Single<String> source = Single.just("Hello Single");
		source.subscribe(str -> System.out.println(str));
		CommonUtils.exampleComplete();
	}

	public void fromObservable() {
		// 2. ���� Observable���� Single ��ü�� ��ȯ�ϱ�
		CommonUtils.exampleStart("02) fromObservable()");

		Observable<String> source = Observable.just("Hello Single");
		Single.fromObservable(source).subscribe(System.out::println);
		// ���� Observable���� ù ��° ���� �����ϸ� onSuccess �̺�Ʈ�� ȣ���� �� ����

		CommonUtils.exampleComplete();
	}

	public void single() {
		// 3. Observable���� single() �޼��带 ȣ���� Single ��ü �����ϱ�
		CommonUtils.exampleStart("03) single()");

		Observable.just("Hello Single").single("default item").subscribe(System.out::println);
		// Observable.just()�� ���ؼ� ������ Observable�� single() �Լ��� ȣ��.
		// single() �Լ��� default value�� ���ڷ� ����

		CommonUtils.exampleComplete();
	}

	public void first() {
		// 4. first() �޼��带 ȣ���Ͽ� Single ��ü �����ϱ�
		CommonUtils.exampleStart("04) first()");

		String[] colors = { "Red", "Blue", "Gold" };
		Observable.fromArray(colors).first("default value").subscribe(System.out::println);
		// ���� ���� �����͸� ������ �� �ִ� Observable�� Single ��ü�� ��ȯ
		// Observable -> first() �Լ��� ȣ�� -> Single��ü�� ��ȯ

		CommonUtils.exampleComplete();
	}

	public void empty() {
		// 5. empty Observable���� Single ��ü �����ϱ�
		CommonUtils.exampleStart("05) empty()");

		Observable.empty().single("default value").subscribe(System.out::println);
		// empty() �Լ��� ���� Single Object ����
		// Observable�� Data�� ��� default value�� ���� Object ����

		CommonUtils.exampleComplete();
	}

	public void take() {
		// 6. take() �Լ����� Single ��ü �����ϱ�
		CommonUtils.exampleStart("06) take()");
		Observable.just(new Item("ITEM-1"), new Item("ITEM-2"))
				.take(1).single(new Item("default item"))
				.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}

	public void nonSingleData() {
		// 7. Error Check - nonSingleData
		CommonUtils.exampleStart("07) nonSingleData");
		Single<String> source = Observable.just("Hello Single", "Error").single("default item");
		source.subscribe(System.out::println);
	}

	public static void main(String[] args) {
		Rx_Observable_09_SingleClass test = new Rx_Observable_09_SingleClass();
		test.just();
		test.fromObservable();
		test.single();
		test.first();
		test.empty();
		test.take();
		//test.nonSingleData();
	}
}

class Item {
	private String mId;

	public Item(String id) {
		mId = id;
	}

	public String getId() {
		return mId;
	}

	@Override
	public String toString() {
		return "ID: " + mId;
	}
}
