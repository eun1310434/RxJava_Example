/*==================================================================================================
�� INFORMATION
�� Data : 24.Sep.2018
�� Mail : eun1310434@gmail.com
�� WebPage : https://eun1310434.github.io/
�� Reference : RxJava ���α׷��� P119
          
�� FUNCTION
�� 
   
�� Study
�� Creating(����) : Operators that originate new Observables.

�� Defer()
  - defer() : Do not create the Observable until the observer subscribes, 
              and create a fresh Observable for each observer
  - timer() �Լ��� ��������� ������ �帧 ������ �����ڰ� subscribe() �Լ��� ȣ���� ������ �̷�
  - Observable�� ������ ������ ������ �̷����� ������ �ֽ� �����͸� ���� �� ����
  
    
�� Original Form
   @SchedulerSupport(SchedulerSupport.NONE)
   public static <T>Observable<T> defer(Callable <? extends ObervableSource<? extends T>> supplier){}
   01) Callable<Observable<T>>
       : Callable ��ü�� �����ڰ� subscribe()�� ȣ���� ������ call() �޼����� ȣ���� �̷�� ����.
   05) @SchedulerSupport(SchedulerSupport.NONE)
       : �����췯�� ����(NONE)�̱� ������ ���� ������ (main ������)���� ����
       
                
�� Parameter of map() method
Function<? super T,? extends R> mapper

�� RxJava Generic Method Interface
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
	//���� �ε��� ������ ������ �ʰ� ���ʷ� �������� ���� iterator() �Լ�(�ݺ���)�� Ȱ��
	
	@Override
	public void marbleDiagram() { 
		Callable<Observable<String>> supplier = () -> getObservable();
		
		defferred("Deffer", supplier);
		notDeferred("Not Deffer", getObservable());
	}

	//������ �����ϴ� Observable ����
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
		CommonUtils.exampleStart(title); // ���� �ð��� ǥ���ϴ� ��ƿ��Ƽ �޼���
		
		//subscribe() �Լ��� ȣ���� ���� ��Ȳ�� �ݿ��Ͽ� ������ ������ ������ �����ϴ� ȿ���� ������
		Observable<String> source = Observable.defer(supplier);
		
		source.subscribe(val -> Log.i("Subscriber #1:" + val));
		//RED ���� : subscribe()�� ȣ���ϸ� �׶� supplier�� call():getObservable() �޼��带 ȣ��
		
		source.subscribe(val -> Log.i("Subscriber #2:" + val));
		//GREEN ���� : subscribe()�� ȣ���ϸ� �׶� supplier�� call():getObservable() �޼��带 ȣ��
		
		source.subscribe(val -> Log.i("Subscriber #3:" + val));
		//BLUE ���� : subscribe()�� ȣ���ϸ� �׶� supplier�� call():getObservable() �޼��带 ȣ��
		
		//source.subscribe(val -> Log.i("Subscriber #4:" + val));
		//PUPPLE ���� : subscribe()�� ȣ���ϸ� �׶� supplier�� call():getObservable() �޼��带 ȣ��
		
		//source.subscribe(val -> Log.i("Subscriber #5:" + val));
		//EMPTY : subscribe()�� ȣ���ϸ� �׶� supplier�� call():getObservable() �޼��带 ȣ��

		CommonUtils.exampleComplete();
	}
	
	
	public void notDeferred(String title, Observable<String> source ){
		CommonUtils.exampleStart(title); // ���� �ð��� ǥ���ϴ� ��ƿ��Ƽ �޼���
		
		//�տ��� �������� ���� colors�� ����� ����
		//�� �� �ش� ������ �帧�� �״뵵 ����
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