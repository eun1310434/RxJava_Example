/*==================================================================================================
□ INFORMATION
○ Data : 05.Sep.2018
○ Mail : eun1310434@gmail.com
○ WebPage : https://eun1310434.github.io/
○ Reference : RxJava 프로그래밍 P4
     
□ FUNCTION
  ○ 
   
□ Study
  ○ Creating(생성) : Operators that originate new Observables.
  ○ Range
    - create an Observable that emits a range of sequential integers
    - The Range operator emits a range of sequential integers,in order, 
      where you select the start of the range and its length.
    - range(n,m) : n ~ m개의 Integer 객체를 발행
      * interval()와 timer()함수는 Long 객체를 발행 

  ○ Original Form
   @SchedulerSupport(SchedulerSupport.NONE)
   public static Observable<Integer> range(final int start, final int count)
   01) @SchedulerSupport(SchedulerSupport.COMPUTATION)
       : 스케줄러에서 실행되지 않으며 현재의 스레드에서 실행됨
    
  ○ RxJava Generic Method Interface
    01) Predicate<T> : boolean test(T t) // return to true or false
    02) Consumer<T> : void accept(T t) // no return    
    03) Function<T,R> : R apply(T t) // return to R type
=================================================================================================*/
package com.eun1310434.java.rx.observables.creating;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;

import io.reactivex.Observable;
import io.reactivex.functions.Predicate;


public class Rx_07_00_Range implements RxTest{
	
	@Override
	public void marbleDiagram() { 
		Predicate<Integer> pre = null;
		ObservableSet_Loop("Range - anonymous",1,9, predicate_EvenNumber(pre));
		ObservableSet_Loop("Range - Lambda",1,9, num -> num % 2 == 1);
	}
	
	public void ObservableSet_Loop(String title,int start, int count, Predicate<Integer> predicate){
		CommonUtils.exampleStart(title); // 시작 시간을 표시하는 유틸리티 메서드
		Observable<Integer> source = Observable
				.range(start,count)
				.filter(predicate);
				
		source .subscribe(data -> Log.it(data));
		//source.subscribe(Log::it);	
		CommonUtils.exampleComplete();
	}
	
	public Predicate<Integer> predicate_EvenNumber(Predicate<Integer> pre) {
		pre = new Predicate<Integer>() {
			@Override
			public boolean test(Integer num) throws Exception {
				// TODO Auto-generated method stub
				return (num % 2 == 0) ? true : false;
			}
		};
		return pre;
	}
	
	
	public static void main(String args[]) { 
		Rx_07_00_Range test = new Rx_07_00_Range();
		test.marbleDiagram();
	}	
}
