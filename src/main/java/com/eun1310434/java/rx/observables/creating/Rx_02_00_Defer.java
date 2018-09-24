/*==================================================================================================
□ INFORMATION
○ Data : 24.Sep.2018
○ Mail : eun1310434@gmail.com
○ WebPage : https://eun1310434.github.io/
○ Reference : RxJava 프로그래밍 P119
          
□ FUNCTION
○ 
   
□ Study
○ Creating(생성) : Operators that originate new Observables.

○ Defer()
  - defer() : Do not create the Observable until the observer subscribes, 
              and create a fresh Observable for each observer
  - timer() 함수와 비슷하지만 데이터 흐름 생성을 구독자가 subscribe() 함수를 호출할 때까지 미룸
  - Observable의 생성이 구독할 때까지 미뤄지기 때문에 최신 데이터를 얻을 수 있음
  
    
○ Original Form
   @SchedulerSupport(SchedulerSupport.NONE)
   public static <T>Observable<T> defer(Callable <? extends ObervableSource<? extends T>> supplier){}
   01) Callable<Observable<T>>
       : Callable 객체는 구독자가 subscribe()를 호출할 때까지 call() 메서드의 호출을 미룰수 있음.
   05) @SchedulerSupport(SchedulerSupport.NONE)
       : 스케쥴러가 없음(NONE)이기 때문에 현재 스레드 (main 스레드)에서 실행
       
                
○ Parameter of map() method
Function<? super T,? extends R> mapper

○ RxJava Generic Method Interface
01) Predicate<T> : boolean test(T t) // return to true or false
02) Consumer<T> : void accept(T t) // no return
03) Function<T,R> : R apply(T t) // return to R type
==================================================================================================*/
package com.eun1310434.java.rx.observables.creating;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.Callable;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;
import com.eun1310434.java.rx.common.Shape;
import static com.eun1310434.java.rx.common.Shape.RED;
import static com.eun1310434.java.rx.common.Shape.GREEN;
import static com.eun1310434.java.rx.common.Shape.BLUE;
import static com.eun1310434.java.rx.common.Shape.PUPPLE;

import io.reactivex.Observable;

public class Rx_02_00_Defer implements RxTest{
	Iterator<String> colors = Arrays.asList(RED, GREEN, BLUE, PUPPLE).iterator();
	//별도 인덱스 변수를 만들지 않고 차례로 가져오기 위해 iterator() 함수(반복자)를 활용
	
	@Override
	public void marbleDiagram() { 
		Callable<Observable<String>> supplier = () -> getObservable();
		
		defferred("Deffer", supplier);
		notDeferred("Not Deffer", getObservable());
	}

	//도형을 발행하는 Observable 생성
	private Observable<String> getObservable() { 
		if (colors.hasNext()) { 
			String color = colors.next();
			return Observable.just(
				Shape.getString(color, Shape.BALL), 
				Shape.getString(color, Shape.RECTANGLE), 
				Shape.getString(color, Shape.PENTAGON)); 			
		}
		return Observable.empty();		
	}
	
	public void defferred(String title, Callable<Observable<String>> supplier){
		CommonUtils.exampleStart(title); // 시작 시간을 표시하는 유틸리티 메서드
		
		//subscribe() 함수를 호출할 때의 상황을 반영하여 데이터 ㅎ름의 생성을 지연하는 효과를 보여줌
		Observable<String> source = Observable.defer(supplier);
		
		source.subscribe(val -> Log.i("Subscriber #1:" + val));
		//RED 구독 : subscribe()를 호출하면 그때 supplier의 call():getObservable() 메서드를 호출
		
		source.subscribe(val -> Log.i("Subscriber #2:" + val));
		//GREEN 구독 : subscribe()를 호출하면 그때 supplier의 call():getObservable() 메서드를 호출
		
		source.subscribe(val -> Log.i("Subscriber #3:" + val));
		//BLUE 구독 : subscribe()를 호출하면 그때 supplier의 call():getObservable() 메서드를 호출
		
		//source.subscribe(val -> Log.i("Subscriber #4:" + val));
		//PUPPLE 구독 : subscribe()를 호출하면 그때 supplier의 call():getObservable() 메서드를 호출
		
		//source.subscribe(val -> Log.i("Subscriber #5:" + val));
		//EMPTY : subscribe()를 호출하면 그때 supplier의 call():getObservable() 메서드를 호출

		CommonUtils.exampleComplete();
	}
	
	
	public void notDeferred(String title, Observable<String> source ){
		CommonUtils.exampleStart(title); // 시작 시간을 표시하는 유틸리티 메서드
		
		//앞에서 구독하지 않은 colors의 멤버를 구독
		//그 때 해당 데이터 흐름을 그대도 발행
		source.subscribe(val -> Log.i("Subscriber #1:" + val));
		source.subscribe(val -> Log.i("Subscriber #2:" + val));
		//source.subscribe(val -> Log.i("Subscriber #3:" + val));
		//source.subscribe(val -> Log.i("Subscriber #4:" + val));
		//source.subscribe(val -> Log.i("Subscriber #5:" + val));
		
		CommonUtils.exampleComplete();		
	}
	
	public static void main(String[] args) { 
		Rx_02_00_Defer demo = new Rx_02_00_Defer();
		demo.marbleDiagram();
	}
}