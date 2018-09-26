/*==================================================================================================
□ INFORMATION
  ○ Data : 26.Sep.2018
  ○ Mail : eun1310434@gmail.com
  ○ WebPage : https://eun1310434.github.io/
  ○ Reference
    - RxJava 프로그래밍 P129
    - http://rxmarbles.com/#switchMap

□ FUNCTION
○ 
   
□ Study
○ Transforming(변환) : Operators that transform items that are emitted by an Observable.

○ switchMap()
- 먼저 들어온 데이터 순서대로 처리해서 결과를 내보냄.                         
==================================================================================================*/
package com.eun1310434.java.rx.observables.transforming;

import java.util.concurrent.TimeUnit;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;

import io.reactivex.Observable;

public class Rx_04_04_SwitchMap implements RxTest {

	@Override
	public void marbleDiagram() { 
		String[] colors = {"A", "B", "C"}; //1, 3, 5
		
		observableSet("basic",
				DataInsert_A(colors)
				.doOnNext(data -> Log.it(data)) // 이전 실행결과 확인
				.switchMap( beforeData -> DataInsert_B(colors, beforeData)));


		observableSet("switchMap",
				DataInsert_A(colors)
					.switchMap( beforeData -> DataInsert_B(colors, beforeData)));
        			//switchMap이 발생하는 순간 기존에 진행 중이던 작업 중단
	}
	
	public Observable<String> DataInsert_A(String[] colors){
		return Observable
				.interval(50L, TimeUnit.MILLISECONDS)
				//.map(Long::intValue)//0부터 발생하는 Long객체 값을 Integer 객체 값으로 변환
				.map(longNum -> new Long(longNum).intValue())//0부터 발생하는 Long객체 값을 Integer 객체 값으로 변환
				.map(idx -> "	IN 1 - "+ colors[idx])//인덱스 값을 기준으로 colors의 값을 추출
				.take(colors.length);//colors의 길이 만큼 가지고 오기
	}
	
	public Observable<String> DataInsert_B(String[] colors, String beforeData){
		return Observable
				.interval(200L, TimeUnit.MILLISECONDS)
				.map(longNum -> new Long(longNum).intValue())//0부터 발생하는 Long객체 값을 Integer 객체 값으로 변환
				.map(idx -> beforeData+",	IN 2 - "+ colors[idx])
				.take(colors.length);
	}
	
	public void observableSet(String title, Observable<String> observable){
		
		CommonUtils.exampleStart(title); // 시작 시간을 표시하는 유틸리티 메서드
		Observable<String> source = observable; 
		source.subscribe(data -> Log.it(data));
		
		CommonUtils.sleep(2000); 
		// Thread.sleep()을 호출, 다른 스레드(RxComputationThreadPool-1)에서 실행이 완료될 때까지 기다리기 위하여 사용
		// 왜냐하면 메인 스레드가 아닌 계산 스케줄러에서 실행되기 때문에 사용
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		Rx_04_04_SwitchMap test = new Rx_04_04_SwitchMap();
		test.marbleDiagram();
		
	}
}
