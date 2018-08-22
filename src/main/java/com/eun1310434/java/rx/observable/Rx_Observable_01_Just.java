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
  �� just()
    - ���ڷ� ���� �����͸� ���ʷ� ����
    - �� ���� ���� ���� ���� �ְ� ���ڷ� ���� ���� ��(�ִ� 10��)�� ���� �� ����.
       *�� ���� Ÿ���̾�� ��
    - public static <T> Observable<T> just(T item1,... T item10)
  
==================================================================================================*/
package com.eun1310434.java.rx.observable;

import io.reactivex.Observable;


public class Rx_Observable_01_Just {
	public void emit() {
		Observable
		.just(1,2,3,4,5,6,7,8,9,10) //just() : Observable�� ������ 
		.subscribe(System.out::println); 	
	}

	public static void main(String args[]) { 
		Rx_Observable_01_Just just = new Rx_Observable_01_Just();
		just.emit();
	}	
}
