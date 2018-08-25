/*==================================================================================================
□ INFORMATION
○ Data : 22.08.2018
○ Mail : eun1310434@naver.com
○ WebPage : https://eun1310434.github.io/
○ Reference
- RxJava 프로그래밍 P55
     
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

○ fromArray()
-

○ Map 객체에 관한 Observable 클래스의 from() 함수는 없다.
- Map 인터페이스는 배열이 아님
- Iterable<E> 인터페이스를 구현하지 않음
- from() 계열 함수는 존재할 수 없음.
==================================================================================================*/
package com.eun1310434.java.rx.observable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import com.eun1310434.java.rx.common.CommonUtils;
import io.reactivex.Observable;

public class Rx_Observable_05_FromIterable {
	public void listExample() {
		CommonUtils.exampleStart("01) listExample");
		List<String> names = new ArrayList<>();
		names.add("Jerry");
		names.add("William");
		names.add("Bob");

		Observable<String> source = Observable.fromIterable(names);
		source.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}

	public void setExample() {
		CommonUtils.exampleStart("02) setExample");
		Set<String> cities = new HashSet<>();
		cities.add("Seoul");
		cities.add("London");
		cities.add("Paris");

		Observable<String> source = Observable.fromIterable(cities);
		source.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}

	public void blockingQueueExample() {
		CommonUtils.exampleStart("03) blockingQueueExample");
		
		BlockingQueue<Order> orderQueue = new ArrayBlockingQueue<>(100);
		orderQueue.add(new Order("ORD-1"));
		orderQueue.add(new Order("ORD-2"));
		orderQueue.add(new Order("ORD-3"));

		Observable<Order> source = Observable.fromIterable(orderQueue);
		source.subscribe(order -> System.out.println(order.getId()));
		CommonUtils.exampleComplete();
	}


	public void arrayListExample() {
		CommonUtils.exampleStart("04) arrayListExample");
		
		ArrayList<Order> orderArraylist = new ArrayList<>();
		orderArraylist.add(new Order("ORD-1"));
		orderArraylist.add(new Order("ORD-2"));
		orderArraylist.add(new Order("ORD-3"));

		Observable<Order> source = Observable.fromIterable(orderArraylist);
		source.subscribe(order -> System.out.println(order.getId()));
		CommonUtils.exampleComplete();
	}


	public static void main(String[] args) {
		Rx_Observable_05_FromIterable test = new Rx_Observable_05_FromIterable();
		test.listExample();
		test.setExample();
		test.blockingQueueExample();
		test.arrayListExample();
	}
}

class Order {
	private String mId;

	public Order(String id) {
		mId = id;
	}

	public String getId() {
		return mId;
	}

	@Override
	public String toString() {
		return "Order ID: " + mId;
	}
}