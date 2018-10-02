/*==================================================================================================
□ INFORMATION
  ○ Data : 02.Oct.2018
  ○ Mail : eun1310434@gmail.com
  ○ WebPage : https://eun1310434.github.io/
  ○ Reference
    - RxJava 프로그래밍 132
    - http://rxmarbles.com/#switchMap

□ FUNCTION
○ 
   
□ Study
○ Transforming(변환) : Operators that transform items that are emitted by an Observable.

○ groupBy()
- divide an Observable into a set of Observables 
  that each emit a different group of items from the original Observable, 
  organized by key                        
==================================================================================================*/
package com.eun1310434.java.rx.observables.transforming;

import static com.eun1310434.java.rx.common.Shape.BLUE;
import static com.eun1310434.java.rx.common.Shape.GREEN;
import static com.eun1310434.java.rx.common.Shape.ORANGE;
import static com.eun1310434.java.rx.common.Shape.PUPPLE;
import static com.eun1310434.java.rx.common.Shape.RED;
import static com.eun1310434.java.rx.common.Shape.SKY;
import static com.eun1310434.java.rx.common.Shape.YELLOW;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.RxTest;
import com.eun1310434.java.rx.common.Shape;

import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

public class Rx_03_00_GroupBy implements RxTest {

	@Override
	public void marbleDiagram() { 
		String[] colors = {
				Shape.triangle(RED), Shape.triangle(YELLOW), 
				Shape.rectangle(GREEN), Shape.rectangle(SKY), 
				BLUE, PUPPLE, ORANGE}; 
		filterBallGroup(colors, Shape.BALL);
	}
	
	public Observable<GroupedObservable<String, String>> DataInsert_A(String[] objs){
		return Observable
				.fromArray(objs)
				.groupBy(obj -> Shape.getShape(obj));
	}
	
	public void filterBallGroup(String[] objs,String filter) { 
		Observable<GroupedObservable<String, String>> source = DataInsert_A(objs);
		source.subscribe(
				obj -> {
					obj
					.filter(val -> obj.getKey().equals(filter))
					.subscribe(val -> System.out.println("GROUP:" + obj.getKey() + "\t Value:" + val));
		});	
		
		CommonUtils.exampleComplete();
	}
	

	public static void main(String[] args) { 
		Rx_03_00_GroupBy test = new Rx_03_00_GroupBy();
		test.marbleDiagram();
		
	}
}
