/*==================================================================================================
□ INFORMATION
○ Data : 28.08.2018
○ Mail : eun1310434@gamil.com
○ WebPage : https://eun1310434.github.io/
○ Reference
- RxJava 프로그래밍 P93

□ FUNCTION
○ 
   
□ Study
○ Lang3 Library of Apache Commons(아파치 커먼즈) 
- 자료구조 클래스를 만들기 보다 Pair 혹은 Tuple 같은 일반화된 자료구조를 선호
- Pair <T,U> : Lang3 Library of Apache Commons(아파치 커먼즈)  

○ Setting for Lang3 Library of Apache Commons(아파치 커먼즈) 
- 코드추가 : [build.gradle] -> compile 'org.apache.commons:commons-lang3:3.1'
- 셋팅 : [프로젝트 이름]을 선택한 후 [Gradle] -> [Refresh Gradle Project]를 선택하면 자동으로 라이브러리를 다운
- 안드로이드의 경우 android.util.Pair 클래스를 내장

○ RxJava Generic Method Interface
01) Predicate<T> : boolean test(T t) // return to true or false
02) Consumer<T> : void accept(T t) // no return
03) Function<T,R> : R apply(T t) // return to R type
==================================================================================================*/
package com.eun1310434.java.rx.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.tuple.Pair;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;

import io.reactivex.Maybe;
import io.reactivex.Observable;

public class Rx_01_00_Query implements RxTest {
	
	@Override
	public void marbleDiagram() { 
		// 1. MarbleDiagram : flatMap()
		CommonUtils.exampleStart("01) Query : marbleDiagram()");

		Scanner in = new Scanner(System.in);
		System.out.println("Input Order(A,B,C,D) :");
		String order = in.nextLine();			

	
		List<Pair<String,Integer>> items = new ArrayList<>();
		items.add(Pair.of("A", 100));
		items.add(Pair.of("B", 200));
		items.add(Pair.of("C", 300));
		items.add(Pair.of("D", 400));
		items.add(Pair.of("A", 500));
		items.add(Pair.of("A", 600));
		items.add(Pair.of("A", 700));
		

		Maybe<Integer> tvSales = Observable
				.fromIterable(items)
				//2. 검색어 기준 필터링 함 
				.filter(itemPair -> order.equals(itemPair.getLeft()))
				.map(sale -> sale.getRight())
				//3. 검색어 기준 합을 구함 
				.reduce((pairRight1, pairRight2) -> pairRight1 + pairRight2);		
		tvSales.subscribe(Log::i);

		in.close();
		CommonUtils.exampleComplete();		
	}
	
	public static void main(String[] args) { 
		Rx_01_00_Query test = new Rx_01_00_Query();
		test.marbleDiagram();
	}
}
