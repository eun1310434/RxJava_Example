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
