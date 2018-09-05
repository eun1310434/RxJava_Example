/*==================================================================================================
�� INFORMATION
�� Data : 04.Sep.2018
�� Mail : eun1310434@gmail.com
�� WebPage : https://eun1310434.github.io/
�� Reference : RxJava ���α׷��� P97
   
�� FUNCTION
�� 
   
�� Study
�� Filtering Observables
- Operators that selectively emit items from a source Observable.

�� last()
- emit only the last item (or the last item that meets some condition) emitted by an Observable
- If you are only interested in the last item emitted by an Observable, 
  or the last item that meets some criteria, you can filter the Observable with the Last operator.
- last(default) : Observable�� ������ ���� ������. ���� ������ �Ϸ�Ǹ� ��� �⺻���� ����

�� RxJava Generic Method Interface
01) Predicate<T> : boolean test(T t) // return to true or false
02) Consumer<T> : void accept(T t) // no return
03) Function<T,R> : R apply(T t) // return to R type

�� Single Class
- Observable Ŭ������ �����͸� �����ϰ� ������ �� ������ Single Ŭ������ ���� 1���� �����͸� �����ϵ��� ��
- ������ �ϳ��� ����� ���ÿ� ����(onSuccess)
* onSuccess() = onNext() + onComplete()
- Single Ŭ������ ������ ����Ŭ �Լ��� onSuccess(T value)�Լ��� onError()�Լ��� ����
==================================================================================================*/
package com.eun1310434.java.rx.observables.filtering;


import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;

import io.reactivex.Observable;
import io.reactivex.Single;

public class Rx_07_00_Last implements RxTest {

	@Override
	public void marbleDiagram() { 
		CommonUtils.exampleStart("01) last()");
		Integer[] numbers = {100, 200, 300, 400, 500};
		
		Single<Integer> single = Observable
				.fromArray(numbers)
				.last(999);//if no value, return to 999
		single.subscribe(Log::i);
		
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		Rx_07_00_Last test = new Rx_07_00_Last();
		test.marbleDiagram();
	}
}
