/*==================================================================================================
�� INFORMATION
�� Data : 18.Sep.2018
�� Mail : eun1310434@gmail.com
�� WebPage : https://eun1310434.github.io/
�� Reference : RxJava ���α׷��� P114
          
�� FUNCTION
�� 
   
�� Study
�� Creating(����) : Operators that originate new Observables.

�� Interval()
  - create an Observable that emits a sequence of integers spaced by a given time interval
  - ������ ���� ����Ǳ� ������ ���� �뵵�� ���� ���
  - ��� �����ٷ����� ����(���� ������X)
  
�� Original Form
   @SchedulerSupport(SchedulerSupport.COMPUTATION)
   public static Observable<Long> interval(long period, TimeUnit unit)
   public Static Observable<Long> interval(long initialDelay, long period, TimeUnit unit)
   01) period : �����͸� ������ϱ� ���� ���� �ð� ����
   02) initialDelay : ���� ���� �ð� ����
   03) @SchedulerSupport(SchedulerSupport.COMPUTATION)
       : interval() �Լ��� ������ Computation Scheduler(��� �����ٷ�)���� ����ȴٴ� �ǹ�,
         Current Thread�� �ƴ�  Other Thread�� ����Ǵ� ����.
         
�� Parameter of map() method
Function<? super T,? extends R> mapper

�� RxJava Generic Method Interface
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
		CommonUtils.exampleStart(title); // ���� �ð��� ǥ���ϴ� ��ƿ��Ƽ �޼���
		Observable<Long> source = Observable
				.interval(initialDelay, period, TimeUnit.MILLISECONDS) // �ּ� ���� �� initialDelay �� period�ð�(ms) �������� 0���� �����͸� ���� 
				.map(fun) // map() �Լ��� ȣ���Ͽ� �Է°��� 1�� ���ϰ� 100�� ���� -> 100,200,300... ����
				.take(take); // ���� 5���� �����͸� ���� -> 100,200,300,400,500
		
		source .subscribe(data -> Log.it(data));
		//source.subscribe(Log::it);
		
		CommonUtils.sleep(1000); 
		// Thread.sleep()�� ȣ��, �ٸ� ������(RxComputationThreadPool-1)���� ������ �Ϸ�� ������ ��ٸ��� ���Ͽ� ���
		// �ֳ��ϸ� ���� �����尡 �ƴ� ��� �����ٷ����� ������ �Ӷ����� ���
		
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
