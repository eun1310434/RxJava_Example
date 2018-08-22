/*==================================================================================================
�� INFORMATION
  �� Data : 18.08.2018
  �� Mail : eun1310434@naver.com
  �� WebPage : https://eun1310434.github.io/
  �� Reference
     - RxJava ���α׷��� P33
     
�� FUNCTION
  �� 
   
�� Study
  ��
  
==================================================================================================*/

package com.eun1310434.java.rx.basic;

import io.reactivex.Observable;

public class Rx_Observable_Print {	
	
	public void emit() {
		Observable // Observable : �������� ��ȭ�� �߻��ϴ� ������ �ҽ�(data source)
		.just("Hello", "RxJava2!!") //just() : Observable�� ������ 
		.subscribe(data->System.out.println(data)); // subscribe() : ��ȭ�� �����͸� �����ڿ��� ����
		//.subscribe(data->{System.out.println(data);}); 
		//.subscribe(System.out::println); 	
	}

	public static void main(String args[]) { 
		Rx_Observable_Print demo = new Rx_Observable_Print();
		demo.emit();
	}	
}