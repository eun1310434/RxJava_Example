/*==================================================================================================
□ INFORMATION
  ○ Data : 21.Sep.2018
  ○ Mail : eun1310434@gmail.com
  ○ WebPage : https://eun1310434.github.io/
  ○ Reference : RxJava 프로그래밍 P117
          
□ FUNCTION
  ○ 
   
□ Study
  ○ Creating(생성) : Operators that originate new Observables.

  ○ Interval()
  - create an Observable that emits a single item after a given delay
  - interval() 함수와 유사하나 한 번만 실행하는 함수
  - 일정 시간이 지난 후 에 한개의 데이터를 발행하고 onComplete() 이벤트가 발생
  - 계산 스케줄러에서 실행(현재 스레드X)
  -  
  
  ○ Original Form
   @SchedulerSupport(SchedulerSupport.COMPUTATION)
   public static Observable<java.lang.Long> timer(long delay, java.util.concurret.TimeUnit unit)
   01) period : 데이터를 재발행하기 까지 쉬는 시간 조절
   02) initialDelay : 최초 지연 시간 조절
   03) @SchedulerSupport(SchedulerSupport.COMPUTATION)
       : interval() 함수의 동작이 Computation Scheduler(계산 스케줄러)에서 실행된다는 의미,
         Current Thread가 아닌  Other Thread가 실행되는 중임.
  
  ○ RxJava Generic Method Interface
    01) Predicate<T> : boolean test(T t) // return to true or false
    02) Consumer<T> : void accept(T t) // no return    
    03) Function<T,R> : R apply(T t) // return to R type
==================================================================================================*/
package com.eun1310434.java.rx.observables.creating;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
public class Rx_10_00_Timer implements RxTest {

	@Override
	public void marbleDiagram() {
		// TODO Auto-generated method stub

		long initialDelay = 0L;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		Function<Object, String> fun = null;
		
		ObservableSet("Timmer - anonymous", initialDelay, function_ShowTime(sdf, date, fun));
		ObservableSet("Timmer - lambda", initialDelay, noUsed -> {return sdf.format(date);});
		//람다 표현식의 인자 이름 notUsed라고 지은 이유는 실제로 map()함수를 사용하는 것이 아님
	}

	public void ObservableSet(String title,long initialDelay ,Function<Object, String> fun){
		CommonUtils.exampleStart(title); // 시작 시간을 표시하는 유틸리티 메서드
		Observable<String> source = Observable
				.timer(initialDelay, TimeUnit.MILLISECONDS) // 최소 실행 전 initialDelay 후 period시간(ms) 간격으로 0부터 데이터를 발행 
				.map(fun); // map() 함수를 
				
		source .subscribe(data -> Log.it(data));
		//source.subscribe(Log::it);
		
		CommonUtils.sleep(1000); 
		// Thread.sleep()을 호출, 다른 스레드(RxComputationThreadPool-1)에서 실행이 완료될 때까지 기다리기 위하여 사용
		// 왜냐하면 메인 스레드가 아닌 계산 스케줄러에서 실행됙 ㅣ때문에 사용
		
		CommonUtils.exampleComplete();
	}

	public <T, R> Function<T, String> function_ShowTime(SimpleDateFormat sdf, Date date, Function<T, String> fun) {
		fun = new Function<T, String>() {
			@Override
			public String apply(T notUsed) throws Exception {
				String out = (String) sdf.format(date);
				return out;
			}
		};
		return fun;
	}
	
	public static void main(String[] args) { 
		Rx_10_00_Timer test = new Rx_10_00_Timer(); 
		test.marbleDiagram();
	}
}
