/*==================================================================================================
□ INFORMATION
  ○ Data : 05.Oct.2018
  ○ Mail : eun1310434@gmail.com
  ○ WebPage : https://eun1310434.github.io/
  ○ Reference
    - RxJava 프로그래밍 P129
    - http://rxmarbles.com/#switchMap

□ FUNCTION
  ○ 
   
□ Study
  ○ Utility(유틸리티) : A toolbox of useful Operators for working with Observables
  ○ Scheduler
    - 스케줄러는 RxJava 코드를 어느 스레드에서 실행할지 지정할 수 있다.
    - subscribeOn() 함수와 observeOn() 함수를 모두 지정하면 
      Observable에서 데이터 흐름이 발생하는 스레드와 처리된 결과를 구독자에게 발행하는 스레드를 분리할 수 있다.
    - subscribeOn() : Observable에서 subscribe()함수를 호출하여 구독할 때 실행되는 스레드를 지정
    - observeOn() : Observable에서 생성한 흐름이 여기저기 함수를 거치며 처리될 때 동작이 어느 Thread에서 일어나는지 지정
  ○ subscribeOn()
    - specify the scheduler an Observable should use when it is subscribed to
    - 한번 호출했을 때 결정한 스레드를 고정하며 이후에는 다시 호출해도 스래드가 바뀌지 않는다.
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
		CommonUtils.exampleStart(title); // 시작 시간을 표시하는 유틸리티 메서드
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
		 *  최초의 데이터 흐름이 발생하는 스레드와 flip() 함수를 거쳐서 구독자에게 전달되는 스레드가 다름.
		 *  단지, subscribeOn()과 observeOn() 함수에 어떤 스케줄러( 뉴 스레드 스케줄러)를 지정할 뿐
		 */
	}
	
	public void subscribeOn(String[] objs, String title) {
		CommonUtils.exampleStart(title); // 시작 시간을 표시하는 유틸리티 메서드
		Observable<String> source = Observable.fromArray(objs)
				.doOnNext(data -> Log.v("Origianl data = " + data))
				.subscribeOn(Schedulers.newThread())
				//removed .observeOn(Schedulers.newThread())
				.map(data -> Shape.flip(data));
		source.subscribe(str -> Log.i(str));
		CommonUtils.sleep(500);
		/*
		 * observeOn() 함수를 지정하지 않은면 subscribeOn()함수로 지정한 스레드에서 모든 로직 실행
		 */
	}
	
	public static void main(String[] args) { 
		Rx_07_00_SubscribeOn test = new Rx_07_00_SubscribeOn();
		test.marbleDiagram();
	}

}
