/*==================================================================================================
�� INFORMATION
�� Data : 04.Sep.2018
�� Mail : eun1310434@gmail.com
�� WebPage : https://eun1310434.github.io/
�� Reference : RxJava ���α׷��� P54
          
�� FUNCTION
�� 
   
�� Study
�� Creating(����) : Operators that originate new Observables.

�� From() : convert some other object or data structure into an Observable

�� fromCallable()
- asynchronism ����� ���� ���
- Executor �������̽��� ���ڷ� Ȱ��Ǳ� ������ �������� �ٸ� �����忡�� ����Ǵ� ���� �ǹ�

�� JAVA 5 - API : Callable
- �񵿱� ���� �� ����� ��ȯ�ϴ� call()�޼��带 ����
	public interface Callable<V>{
		//@return ���� �����
		//@throws Exception ����� �Ϸ��� �� ���� ��
		V call() throws Exception;
	}
- Runnable�� �������̽� ó�� �޼��尡 1���̰� ���ڰ� ���ٴ� ������ ���� �ϳ� �������� �����ϴ� ������ ���� ����

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
==================================================================================================*/
package com.eun1310434.java.rx.observables.creating;

import java.util.concurrent.Callable;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;

import io.reactivex.Observable;

public class Rx_04_03_FromCallable implements RxTest{
	
	@Override
	public void marbleDiagram() { 
		Callable<String> callable = null;
		observableSet(lambda(callable));
		observableSet(anonymous(callable));
	}
	
	public void observableSet(Callable<String> call){
		Observable<String> source = Observable.fromCallable(call);
		source.subscribe(data -> Log.i(data));
		CommonUtils.exampleComplete();
	}
	
	public Callable<String> lambda(Callable<String> callable) { 
		CommonUtils.exampleStart("01) FromCallable - lambda");
		callable = () -> { 
			Thread.sleep(1000);
			return "After 1 Sec : Hello Callable-lambda";
		};
		return callable;
	}
	
	public Callable<String> anonymous(Callable<String> callable) { 
		CommonUtils.exampleStart("02) FromCallable - anonymous");
		callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(1000);
				return "After 1 Sec : Hello Callable-anonymous";
			}			
		};
		return callable;
	}
	
	public static void main(String[] args) { 
		Rx_04_03_FromCallable test = new Rx_04_03_FromCallable();
		test.marbleDiagram();
	}
}
