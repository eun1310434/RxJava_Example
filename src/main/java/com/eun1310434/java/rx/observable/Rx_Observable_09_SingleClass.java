/*==================================================================================================
□ INFORMATION
○ Data : 24.08.2018
○ Mail : eun1310434@naver.com
○ WebPage : https://eun1310434.github.io/
○ Reference
- RxJava 프로그래밍 P61
     
□ FUNCTION
○ 
   
□ Study
○ Data Source
- Observable
- Single
- Maybe
- Subject
- Completable

○ Data Receiver
- Subscriber : Observable과 연결할 때는 "subscribe()" Calling. 
- Observer : RxJava는 observer pattern을 implement. 
- Consumer : RxJava 2에서는 Comsumer를 Parameter로 사용

○ Single Class
- Observable 클래스는 데이터를 무한하게 발행할 수 있지만 Single 클래스는 오직 1개의 데이터만 발행하도록 함
- 데이터 하나가 발행과 동시에 종료(onSuccess)
* onSuccess() = onNext() + onComplete()
- Single 클래스의 라이프 사이클 함수는 onSuccess(T value)함수와 onError()함수로 구성
==================================================================================================*/

package com.eun1310434.java.rx.observable;

import com.eun1310434.java.rx.common.CommonUtils;

import io.reactivex.Observable;
import io.reactivex.Single;

public class Rx_Observable_09_SingleClass {
	public void just() {
		// 1. 일반적인 Single 선언
		CommonUtils.exampleStart("01) just()");
		Single<String> source = Single.just("Hello Single");
		source.subscribe(str -> System.out.println(str));
		CommonUtils.exampleComplete();
	}

	public void fromObservable() {
		// 2. 기존 Observable에서 Single 객체로 변환하기
		CommonUtils.exampleStart("02) fromObservable()");

		Observable<String> source = Observable.just("Hello Single");
		Single.fromObservable(source).subscribe(System.out::println);
		// 기존 Observable에서 첫 번째 값을 발행하면 onSuccess 이벤트를 호출한 후 종료

		CommonUtils.exampleComplete();
	}

	public void single() {
		// 3. Observable에서 single() 메서드를 호출해 Single 객체 생성하기
		CommonUtils.exampleStart("03) single()");

		Observable.just("Hello Single").single("default item").subscribe(System.out::println);
		// Observable.just()를 통해서 생성된 Observable에 single() 함수를 호출.
		// single() 함수는 default value를 인자로 갖음

		CommonUtils.exampleComplete();
	}

	public void first() {
		// 4. first() 메서드를 호출하여 Single 객체 생성하기
		CommonUtils.exampleStart("04) first()");

		String[] colors = { "Red", "Blue", "Gold" };
		Observable.fromArray(colors).first("default value").subscribe(System.out::println);
		// 여러 개의 데이터를 발행할 수 있는 Observable을 Single 객체로 변환
		// Observable -> first() 함수를 호출 -> Single객체로 변환

		CommonUtils.exampleComplete();
	}

	public void empty() {
		// 5. empty Observable에서 Single 객체 생성하기
		CommonUtils.exampleStart("05) empty()");

		Observable.empty().single("default value").subscribe(System.out::println);
		// empty() 함수를 통한 Single Object 생성
		// Observable에 Data가 없어도 default value을 갖는 Object 생성

		CommonUtils.exampleComplete();
	}

	public void take() {
		// 6. take() 함수에서 Single 객체 생성하기
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
