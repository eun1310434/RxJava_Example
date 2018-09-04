/*==================================================================================================
�� INFORMATION
  �� Data : 18.08.2018
  �� Mail : eun1310434@naver.com
  �� WebPage : https://eun1310434.github.io/
  �� Reference
     - RxJava ���α׷��� P43
     
�� FUNCTION
  �� 
   
�� Study
�� Creating(����) : Operators that originate new Observables.

�� just()
- convert an object or a set of objects into an Observable that emits that or those objects
- ���ڷ� ���� �����͸� ���ʷ� ����
- �� ���� ���� ���� ���� �ְ� ���ڷ� ���� ���� ��(�ִ� 10��)�� ���� �� ����.
  *�� ���� Ÿ���̾�� ��
- public static <T> Observable<T> just(T item1,... T item10)  

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

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.RxTest;
import com.eun1310434.java.rx.common.Log;

import io.reactivex.Observable;


public class Rx_06_00_Just implements RxTest{
	
	@Override
	public void marbleDiagram() { 
		CommonUtils.exampleStart("01) emit"); 	
		Observable
		.just(1,2,3,4,5,6,7,8,9,10) //just() : Observable�� ������ 
		.subscribe(data -> Log.i(data));
		CommonUtils.exampleComplete();	
	}
	
	public static void main(String args[]) { 
		Rx_06_00_Just just = new Rx_06_00_Just();
		just.marbleDiagram();
	}	
}
