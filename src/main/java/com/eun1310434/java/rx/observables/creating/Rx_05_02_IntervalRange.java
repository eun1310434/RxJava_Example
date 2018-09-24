/*==================================================================================================
�� INFORMATION
�� Data : 24.Sep.2018
�� Mail : eun1310434@gmail.com
�� WebPage : https://eun1310434.github.io/
�� Reference : RxJava ���α׷��� P117
          
�� FUNCTION
�� 
   
�� Study
�� Creating(����) : Operators that originate new Observables.

�� IntervalRange()
  - create an Observable that emits a particular range of sequential integers
  - interval()�� range()�� ȥ���س��� �Լ�
  - interval() �Լ� ó�� ������ �ð� �������� ���� ��������� 
    range() �Լ�ó�� ���� ����(n)�κ��� m����ŭ�� ���� �����ϰ� onComplete �̺�Ʈ�� �߻�.
    *interval() �Լ�ó�� ������ ������ �帧�� �������� ����
  - interval() �Լ��� �����ϰ� Long Ÿ��
  - intervalRange(n,m,interval())
  
�� Original Form
   @SchedulerSupport(SchedulerSupport.COMPUTATION)
   public static Observable<Long> intervalRange(long start, long count, long initialDelay, long period, TimeUnit unit){}
   01) long start : ���� ����
   02) long count : �����Ǵ� ����
   03) initialDelay : ���� ���� �ð� ����
   04) period : �����͸� ������ϱ� ���� ���� �ð� ����
   05) @SchedulerSupport(SchedulerSupport.COMPUTATION)
       : intervalRange() �Լ��� ������ Computation Scheduler(��� �����ٷ�)���� ����ȴٴ� �ǹ�,
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

public class Rx_05_02_IntervalRange implements RxTest {

	@Override
	public void marbleDiagram() {
		// TODO Auto-generated method stub
		Function<Long, Long> fun = null;
		
		long start = 0; // ���� ����
		long count = 5; // �����Ǵ� ����
		long initialDelay = 0L; // ���� ���� �ð� ����
		long period = 100L; // �����Ǳ� ���� ���� �ð� ����
		int take = 3; // ������ �� �� 3���� ����
		
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
		
		CommonUtils.exampleStart(title); // ���� �ð��� ǥ���ϴ� ��ƿ��Ƽ �޼���
		
		Observable<Long> source = Observable
				.intervalRange(
						start,
						count,
						initialDelay, 
						period, 
						TimeUnit.MILLISECONDS) // �ּ� ���� �� initialDelay �� period�ð�(ms) �������� 0���� �����͸� ���� 
				.map(fun) // map() �Լ��� ȣ���Ͽ� �Է°��� 1�� ���ϰ� 100�� ���� -> 100,200,300... ����
				.take(take); // ���� 5���� �����͸� ���� -> 100,200,300,400,500
		
		source .subscribe(data -> Log.it(data));
		//source.subscribe(Log::it);
		
		CommonUtils.sleep(1000); 
		// Thread.sleep()�� ȣ��, �ٸ� ������(RxComputationThreadPool-1)���� ������ �Ϸ�� ������ ��ٸ��� ���Ͽ� ���
		// �ֳ��ϸ� ���� �����尡 �ƴ� ��� �����ٷ����� ����Ǳ� ������ ���
		
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
