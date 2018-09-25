/*==================================================================================================
□ INFORMATION
○ Data : 25.Sep.2018
○ Mail : eun1310434@gmail.com
○ WebPage : https://eun1310434.github.io/
○ Reference : RxJava 프로그래밍 P122
          
□ FUNCTION
○ 
   
□ Study
○ Creating(생성) : Operators that originate new Observables.

○ Defer()
  - defer() : create an Observable that emits a particular item 
              or sequence of items repeatedly
  - 서버와 통신을 하면 해당 서버가 잘 살아있는지 확인(이 확인 과정을 보통 ping 혹은 heart beat이라고 함)하는 코드에 활용    
==================================================================================================*/
package com.eun1310434.java.rx.observables.creating;

import static com.eun1310434.java.rx.common.Shape.BLUE;
import static com.eun1310434.java.rx.common.Shape.GREEN;
import static com.eun1310434.java.rx.common.Shape.RED;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;

import io.reactivex.Observable;

public class Rx_08_00_Repeat implements RxTest{
	
	@Override
	public void marbleDiagram() { 
		String[] colors = {RED, GREEN, BLUE};
		ObservableSet("Repeat",colors,3);
	}

	public void ObservableSet(String title,String[] array, int repeatTime){
		CommonUtils.exampleStart(title); // 시작 시간을 표시하는 유틸리티 메서드
		Observable<String> source = Observable
				.fromArray(array)
				.repeat(repeatTime);
		source.subscribe(data -> Log.it(data));
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		Rx_08_00_Repeat demo = new Rx_08_00_Repeat();
		demo.marbleDiagram();
	}
}