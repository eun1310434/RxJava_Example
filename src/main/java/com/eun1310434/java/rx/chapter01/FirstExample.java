/*==================================================================================================
�� INFORMATION
  �� Data : 10.08.2018
  �� Mail : eun1310434@naver.com
  �� WebPage : https://eun1310434.github.io/
  �� Reference
     - RxJava ���α׷��� P33
     
�� FUNCTION
   �� 
   
�� Study
  ��
  
==================================================================================================*/

package com.eun1310434.java.rx.chapter01;

import io.reactivex.Observable;
//RxJava 2�� �⺻ ��Ű�� �̸�

public class FirstExample {	
	
	public void emit() {
		Observable // Observable : �������� ��ȭ�� �߻��ϴ� ������ �ҽ�(data source)
		.just("Hello", "RxJava2!!") //just : Observable ���� ���
		.subscribe(System.out::println); // subscribe : ��ȭ�� �����͸� �����ڿ��� ����
	}

	public static void main(String args[]) { 
		FirstExample demo = new FirstExample();
		demo.emit();
	}	
}