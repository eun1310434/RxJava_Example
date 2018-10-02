/*==================================================================================================
�� INFORMATION
�� Data : 02.Oct.2018
�� Mail : eun1310434@gmail.com
�� WebPage : https://eun1310434.github.io/
�� Reference : RxJava ���α׷��� P135

�� FUNCTION
�� 
   
�� Study
�� Transforming(��ȯ) : Operators that transform items that are emitted by an Observable.

�� scan() : apply a function to each item emitted by an Observable, sequentially, and emit each successive value
- source�� Observable(String) Ÿ������ 
- ������ ������ �Է°��� �´� �߰� ��� �� ���� ����� �����ڿ��� ����

�� reduce() 
- source�� Maybe<String> Ÿ������
- ������ ���� �Էµ��� �ʰų� onComplete�̺�Ʈ�� �߻����� ������ �����ڿ��� ���� �������� �ʱ⿡ Maybe<String> Ÿ������ ����

==================================================================================================*/
package com.eun1310434.java.rx.observables.transforming;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;
import io.reactivex.Observable;

public class Rx_05_00_Scan implements RxTest {
	public void marbleDiagram() { 
		String[] colors = {"1", "2", "3"}; //1,3,5
		ObservableSet("Scan()",colors);
	}
	
	public void ObservableSet(String title, String[] colors){
		CommonUtils.exampleStart(title);
		Observable<String> source = Observable
				.fromArray(colors)
				.scan((ball1, ball2) -> ball2 + "(" + ball1 + ")");
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}	
	
	public static void main(String[] args) { 
		Rx_05_00_Scan demo = new Rx_05_00_Scan();
		demo.marbleDiagram();
	}
}

