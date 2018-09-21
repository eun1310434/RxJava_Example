/*==================================================================================================
□ INFORMATION
○ Data : 18.Sep.2018
○ Mail : eun1310434@gmail.com
○ WebPage : https://eun1310434.github.io/
○ Reference : RxJava 프로그래밍 P114
          
□ FUNCTION
○ 
   
□ Study
○ Creating(생성) : Operators that originate new Observables.

○ Interval()
  - create an Observable that emits a sequence of integers spaced by a given time interval
  - 영원히 지속 실행되기 때문에 폴링 용도로 많이 사용
  - 계산 스케줄러에서 실행(현재 스레드X)
  
○ Original Form
   @SchedulerSupport(SchedulerSupport.COMPUTATION)
   public static Observable<Long> interval(long period, TimeUnit unit)
   public Static Observable<Long> interval(long initialDelay, long period, TimeUnit unit)
   01) period : 데이터를 재발행하기 까지 쉬는 시간 조절
   02) initialDelay : 최초 지연 시간 조절
   03) @SchedulerSupport(SchedulerSupport.COMPUTATION)
       : interval() 함수의 동작이 Computation Scheduler(계산 스케줄러)에서 실행된다는 의미,
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

public class Rx_05_00_Interval implements RxTest {

	@Override
	public void marbleDiagram() {
		// TODO Auto-generated method stub
		Function<Long, Long> fun = null;
		long initialDelay = 0L;
		long period = 100L;
		int take = 5;
		
		ObservableSet("Interval - anonymous", initialDelay, period, take, getNumber(fun));
		ObservableSet("Interval - lambda", initialDelay, period, take, data -> (data + 1) * 100);
	}
	
	public void ObservableSet(String title,long initialDelay ,long period, int take,Function<Long, Long> fun){
		CommonUtils.exampleStart(title); // 시작 시간을 표시하는 유틸리티 메서드
		Observable<Long> source = Observable
				.interval(initialDelay, period, TimeUnit.MILLISECONDS) // 최소 실행 전 initialDelay 후 period시간(ms) 간격으로 0부터 데이터를 발행 
				.map(fun) // map() 함수를 호출하여 입력값에 1을 더하고 100을 곱함 -> 100,200,300... 발행
				.take(take); // 최초 5개의 데이터만 취함 -> 100,200,300,400,500
		
		source .subscribe(data -> Log.it(data));
		//source.subscribe(Log::it);
		
		CommonUtils.sleep(1000); 
		// Thread.sleep()을 호출, 다른 스레드(RxComputationThreadPool-1)에서 실행이 완료될 때까지 기다리기 위하여 사용
		// 왜냐하면 메인 스레드가 아닌 계산 스케줄러에서 실행됙 ㅣ때문에 사용
		
		CommonUtils.exampleComplete();
	}
	 
	public Function<Long, Long> getNumber(Function<Long, Long> fun) {
		fun = new Function<Long, Long>() {
			@Override
			public Long apply(Long t) throws Exception {
				// TODO Auto-generated method stub
				return (t+1)*100;
			}
		};
		return fun;
	}
	
	public static void main(String[] args) { 
		Rx_05_00_Interval test = new Rx_05_00_Interval();
		test.marbleDiagram();
	}
	
}
