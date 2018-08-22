/*==================================================================================================
�� INFORMATION
�� Data : 22.08.2018
�� Mail : eun1310434@naver.com
�� WebPage : https://eun1310434.github.io/
�� Reference
- RxJava ���α׷��� P57
     
�� FUNCTION
�� 
   
�� Study
�� fromCallable()
-  asynchronism ����� ���� ���
- Executor �������̽��� ���ڷ� Ȱ��Ǳ� ������ �������� �ٸ� �����忡�� ����Ǵ� ���� �ǹ�
�� JAVA 5 - API : Callable
- �񵿱� ���� �� ����� ��ȯ�ϴ� call()�޼��带 ����
public interface Callable<V>{
	//@return ���� �����
	//@throws Exception ����� �Ϸ��� �� ���� ��
	V call() throws Exception;
}
- Runnable�� �������̽� ó�� �޼��尡 1���̰� ���ڰ� ���ٴ� ������ ���� �ϳ� �������� �����ϴ� ������ ���� ����
==================================================================================================*/
package com.eun1310434.java.rx.observable;

import java.util.concurrent.Callable;

import com.eun1310434.java.rx.common.CommonUtils;

import io.reactivex.Observable;

public class Rx_Observable_06_FromCallable {
	public void lambda() { 
		CommonUtils.exampleStart("01) lambda");
		Callable<String> callable = () -> { 
			Thread.sleep(1000);
			return "After 1 Sec : Hello Callable-lambda";
		};
		
		Observable<String> source = Observable.fromCallable(callable);
		source.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}
	
	public void anonymous() { 
		CommonUtils.exampleStart("02) anonymous");
		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(1000);
				return "After 1 Sec : Hello Callable-anonymous";
			}			
		};
		
		Observable<String> source = Observable.fromCallable(callable);
		source.subscribe(System.out::println);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		Rx_Observable_06_FromCallable test = new Rx_Observable_06_FromCallable();
		test.lambda();
		test.anonymous();
	}
}
