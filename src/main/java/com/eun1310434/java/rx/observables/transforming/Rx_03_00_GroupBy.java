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

�� groupBy()
- divide an Observable into a set of Observables 
  that each emit a different group of items from the original Observable, 
  organized by key                        
==================================================================================================*/
package com.eun1310434.java.rx.observables.transforming;

import java.util.concurrent.TimeUnit;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;
import com.eun1310434.java.rx.common.Shape;

import static com.eun1310434.java.rx.common.Shape.RED;
import static com.eun1310434.java.rx.common.Shape.YELLOW;
import static com.eun1310434.java.rx.common.Shape.GREEN;
import static com.eun1310434.java.rx.common.Shape.SKY;
import static com.eun1310434.java.rx.common.Shape.BLUE;
import static com.eun1310434.java.rx.common.Shape.PUPPLE;
import static com.eun1310434.java.rx.common.Shape.ORANGE;

import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

public class Rx_03_00_GroupBy implements RxTest {

	@Override
	public void marbleDiagram() { 
		String[] colors = {
				Shape.triangle(RED), Shape.triangle(YELLOW), 
				Shape.rectangle(GREEN), Shape.rectangle(SKY), 
				BLUE, PUPPLE, ORANGE}; 
		filterBallGroup(colors);
	}
	

	public void colorGroup(String[] objs) { 
		Observable<GroupedObservable<String, String>> source = DataInsert_A(objs);
		
		source.subscribe(obj -> {
			obj
			.filter(val -> obj.getKey().equals(Shape.BALL))
			.subscribe(val -> 
			System.out.println("GROUP:" + obj.getKey() + "\t Value:" + val));
		});	
		
		CommonUtils.exampleComplete();
	}
	
	public void filterBallGroup(String[] objs) { 
		Observable<GroupedObservable<String, String>> source = DataInsert_A(objs);
		
		source.subscribe(obj -> {
			obj
			.filter(val -> obj.getKey().equals(Shape.BALL))
			.subscribe(val -> 
			System.out.println("GROUP:" + obj.getKey() + "\t Value:" + val));
		});	
		
		CommonUtils.exampleComplete();
	}
	
	public Observable<GroupedObservable<String, String>> DataInsert_A(String[] objs){
		return Observable
				.fromArray(objs)
				.groupBy(obj -> Shape.getShape(obj));
	}
	
	public Observable<String> DataInsert_B(String[] colors, String beforeData){
		return Observable
				.interval(200L, TimeUnit.MILLISECONDS)
				.map(longNum -> new Long(longNum).intValue())//0���� �߻��ϴ� Long��ü ���� Integer ��ü ������ ��ȯ
				.map(idx -> beforeData+",	IN 2 - "+ colors[idx])
				.take(colors.length);
	}
	
	public void observableSet(String title, Observable<GroupedObservable<String, String>> observable){
		CommonUtils.exampleStart(title); // ���� �ð��� ǥ���ϴ� ��ƿ��Ƽ �޼���
		Observable<GroupedObservable<String, String>> source = observable; 
		source.subscribe(data -> Log.it(data));
		
		CommonUtils.sleep(2000); 
		// Thread.sleep()�� ȣ��, �ٸ� ������(RxComputationThreadPool-1)���� ������ �Ϸ�� ������ ��ٸ��� ���Ͽ� ���
		// �ֳ��ϸ� ���� �����尡 �ƴ� ��� �����ٷ����� ����Ǳ� ������ ���
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		Rx_03_00_GroupBy test = new Rx_03_00_GroupBy();
		test.marbleDiagram();
		
	}
}
