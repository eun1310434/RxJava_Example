/*==================================================================================================
�� INFORMATION
  �� Data : 26.Sep.2018
  �� Mail : eun1310434@gmail.com
  �� WebPage : https://eun1310434.github.io/
  �� Reference
    - RxJava ���α׷��� P129
    - http://rxmarbles.com/#switchMap

�� FUNCTION
�� 
   
�� Study
�� Transforming(��ȯ) : Operators that transform items that are emitted by an Observable.

�� switchMap()
- ���� ���� ������ ������� ó���ؼ� ����� ������.                         
==================================================================================================*/
package com.eun1310434.java.rx.observables.transforming;

import java.util.concurrent.TimeUnit;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;

import io.reactivex.Observable;

public class Rx_04_04_SwitchMap implements RxTest {

	@Override
	public void marbleDiagram() { 
		String[] colors = {"A", "B", "C"}; //1, 3, 5
		
		observableSet("basic",
				DataInsert_A(colors)
				.doOnNext(data -> Log.it(data)) // ���� ������ Ȯ��
				.switchMap( beforeData -> DataInsert_B(colors, beforeData)));


		observableSet("switchMap",
				DataInsert_A(colors)
					.switchMap( beforeData -> DataInsert_B(colors, beforeData)));
        			//switchMap�� �߻��ϴ� ���� ������ ���� ���̴� �۾� �ߴ�
	}
	
	public Observable<String> DataInsert_A(String[] colors){
		return Observable
				.interval(50L, TimeUnit.MILLISECONDS)
				//.map(Long::intValue)//0���� �߻��ϴ� Long��ü ���� Integer ��ü ������ ��ȯ
				.map(longNum -> new Long(longNum).intValue())//0���� �߻��ϴ� Long��ü ���� Integer ��ü ������ ��ȯ
				.map(idx -> "	IN 1 - "+ colors[idx])//�ε��� ���� �������� colors�� ���� ����
				.take(colors.length);//colors�� ���� ��ŭ ������ ����
	}
	
	public Observable<String> DataInsert_B(String[] colors, String beforeData){
		return Observable
				.interval(200L, TimeUnit.MILLISECONDS)
				.map(longNum -> new Long(longNum).intValue())//0���� �߻��ϴ� Long��ü ���� Integer ��ü ������ ��ȯ
				.map(idx -> beforeData+",	IN 2 - "+ colors[idx])
				.take(colors.length);
	}
	
	public void observableSet(String title, Observable<String> observable){
		
		CommonUtils.exampleStart(title); // ���� �ð��� ǥ���ϴ� ��ƿ��Ƽ �޼���
		Observable<String> source = observable; 
		source.subscribe(data -> Log.it(data));
		
		CommonUtils.sleep(2000); 
		// Thread.sleep()�� ȣ��, �ٸ� ������(RxComputationThreadPool-1)���� ������ �Ϸ�� ������ ��ٸ��� ���Ͽ� ���
		// �ֳ��ϸ� ���� �����尡 �ƴ� ��� �����ٷ����� ����Ǳ� ������ ���
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		Rx_04_04_SwitchMap test = new Rx_04_04_SwitchMap();
		test.marbleDiagram();
		
	}
}
