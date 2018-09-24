/*==================================================================================================
□ INFORMATION
○ Data : 24.Sep.2018
○ Mail : eun1310434@gmail.com
○ WebPage : https://eun1310434.github.io/
○ Reference : RxJava 프로그래밍 P117
          
□ FUNCTION
○ 
   
□ Study
○ Creating(생성) : Operators that originate new Observables.

○ IntervalRange()
  - create an Observable that emits a particular range of sequential integers
  - interval()과 range()를 혼합해놓은 함수
  - interval() 함수 처럼 일정한 시간 간격으로 값을 출력하지만 
    range() 함수처럼 시작 숫자(n)로부터 m개만큼의 값만 생성하고 onComplete 이벤트가 발생.
    *interval() 함수처럼 무한히 데이터 흐름을 발행하지 않음
  - interval() 함수와 동일하게 Long 타입
  - intervalRange(n,m,interval())
  
○ Original Form
   @SchedulerSupport(SchedulerSupport.COMPUTATION)
   public static Observable<Long> intervalRange(long start, long count, long initialDelay, long period, TimeUnit unit){}
   01) long start : 시작 숫자
   02) long count : 생성되는 갯수
   03) initialDelay : 최초 지연 시간 조절
   04) period : 데이터를 재발행하기 까지 쉬는 시간 조절
   05) @SchedulerSupport(SchedulerSupport.COMPUTATION)
       : intervalRange() 함수의 동작이 Computation Scheduler(계산 스케줄러)에서 실행된다는 의미,
         Current Thread가 아닌  Other Thread가 실행되는 중임.
         
○ Parameter of map() method
Function<? super T,? extends R> mapper

○ RxJava Generic Method Interface
01) Predicate<T> : boolean test(T t) // return to true or false
02) Consumer<T> : void accept(T t) // no return
03) Function<T,R> : R apply(T t) // return to R type
==================================================================================================*/
package com.eun1310434.java.rx.observables.creating;

import java.util.concurrent.TimeUnit;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class Rx_05_02_IntervalRange implements RxTest {

	@Override
	public void marbleDiagram() {
		// TODO Auto-generated method stub
		Function<Long, Long> fun = null;
		
		long start = 0; // 시작 숫자
		long count = 5; // 생성되는 갯수
		long initialDelay = 0L; // 최초 지연 시간 조절
		long period = 100L; // 재발행되기 까지 쉬는 시간 조절
		int take = 3; // 생성된 것 중 3개만 취함
		
		ObservableSet(
				"IntervalRange - anonymous", 
				start, 
				count, 
				initialDelay, 
				period, 
				take, 
				getNumber(fun));
		
		ObservableSet(
				"IntervalRange - lambda", 
				start, 
				count, 
				initialDelay, 
				period, 
				take, 
				data -> (data + 1) * 100);
	}
	
	public void ObservableSet(String title,long start, long count, long initialDelay ,long period, int take,Function<Long, Long> fun){
		
		CommonUtils.exampleStart(title); // 시작 시간을 표시하는 유틸리티 메서드
		
		Observable<Long> source = Observable
				.intervalRange(
						start,
						count,
						initialDelay, 
						period, 
						TimeUnit.MILLISECONDS) // 최소 실행 전 initialDelay 후 period시간(ms) 간격으로 0부터 데이터를 발행 
				.map(fun) // map() 함수를 호출하여 입력값에 1을 더하고 100을 곱함 -> 100,200,300... 발행
				.take(take); // 최초 5개의 데이터만 취함 -> 100,200,300,400,500
		
		source .subscribe(data -> Log.it(data));
		//source.subscribe(Log::it);
		
		CommonUtils.sleep(1000); 
		// Thread.sleep()을 호출, 다른 스레드(RxComputationThreadPool-1)에서 실행이 완료될 때까지 기다리기 위하여 사용
		// 왜냐하면 메인 스레드가 아닌 계산 스케줄러에서 실행되기 때문에 사용
		
		CommonUtils.exampleComplete();
	}
	 
	public Function<Long, Long> getNumber(Function<Long, Long> fun) {
		fun = new Function<Long, Long>() {
			@Override
			public Long apply(Long t) throws Exception {
				// TODO Auto-generated method stub
				return (t+1)*10;
			}
		};
		return fun;
	}
	
	public static void main(String[] args) { 
		Rx_05_02_IntervalRange test = new Rx_05_02_IntervalRange();
		test.marbleDiagram();
	}
	
}
