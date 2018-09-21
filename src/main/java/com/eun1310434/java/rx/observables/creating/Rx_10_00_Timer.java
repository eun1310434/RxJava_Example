/*==================================================================================================
�� INFORMATION
  �� Data : 21.Sep.2018
  �� Mail : eun1310434@gmail.com
  �� WebPage : https://eun1310434.github.io/
  �� Reference : RxJava ���α׷��� P117
          
�� FUNCTION
  �� 
   
�� Study
  �� Creating(����) : Operators that originate new Observables.

  �� Interval()
  - create an Observable that emits a single item after a given delay
  - interval() �Լ��� �����ϳ� �� ���� �����ϴ� �Լ�
  - ���� �ð��� ���� �� �� �Ѱ��� �����͸� �����ϰ� onComplete() �̺�Ʈ�� �߻�
  - ��� �����ٷ����� ����(���� ������X)
  -  
  
  �� Original Form
   @SchedulerSupport(SchedulerSupport.COMPUTATION)
   public static Observable<java.lang.Long> timer(long delay, java.util.concurret.TimeUnit unit)
   01) period : �����͸� ������ϱ� ���� ���� �ð� ����
   02) initialDelay : ���� ���� �ð� ����
   03) @SchedulerSupport(SchedulerSupport.COMPUTATION)
       : interval() �Լ��� ������ Computation Scheduler(��� �����ٷ�)���� ����ȴٴ� �ǹ�,
         Current Thread�� �ƴ�  Other Thread�� ����Ǵ� ����.
  
  �� RxJava Generic Method Interface
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
		//���� ǥ������ ���� �̸� notUsed��� ���� ������ ������ map()�Լ��� ����ϴ� ���� �ƴ�
	}

	public void ObservableSet(String title,long initialDelay ,Function<Object, String> fun){
		CommonUtils.exampleStart(title); // ���� �ð��� ǥ���ϴ� ��ƿ��Ƽ �޼���
		Observable<String> source = Observable
				.timer(initialDelay, TimeUnit.MILLISECONDS) // �ּ� ���� �� initialDelay �� period�ð�(ms) �������� 0���� �����͸� ���� 
				.map(fun); // map() �Լ��� 
				
		source .subscribe(data -> Log.it(data));
		//source.subscribe(Log::it);
		
		CommonUtils.sleep(1000); 
		// Thread.sleep()�� ȣ��, �ٸ� ������(RxComputationThreadPool-1)���� ������ �Ϸ�� ������ ��ٸ��� ���Ͽ� ���
		// �ֳ��ϸ� ���� �����尡 �ƴ� ��� �����ٷ����� ������ �Ӷ����� ���
		
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
