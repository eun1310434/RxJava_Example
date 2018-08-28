/*==================================================================================================
�� INFORMATION
�� Data : 28.08.2018
�� Mail : eun1310434@gamil.com
�� WebPage : https://eun1310434.github.io/
�� Reference
- RxJava ���α׷��� P93

�� FUNCTION
�� 
   
�� Study
�� Lang3 Library of Apache Commons(����ġ Ŀ����) 
- �ڷᱸ�� Ŭ������ ����� ���� Pair Ȥ�� Tuple ���� �Ϲ�ȭ�� �ڷᱸ���� ��ȣ
- Pair <T,U> : Lang3 Library of Apache Commons(����ġ Ŀ����)  

�� Setting for Lang3 Library of Apache Commons(����ġ Ŀ����) 
- �ڵ��߰� : [build.gradle] -> compile 'org.apache.commons:commons-lang3:3.1'
- ���� : [������Ʈ �̸�]�� ������ �� [Gradle] -> [Refresh Gradle Project]�� �����ϸ� �ڵ����� ���̺귯���� �ٿ�
- �ȵ���̵��� ��� android.util.Pair Ŭ������ ����

�� RxJava Generic Method Interface
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
				//2. �˻��� ���� ���͸� �� 
				.filter(itemPair -> order.equals(itemPair.getLeft()))
				.map(sale -> sale.getRight())
				//3. �˻��� ���� ���� ���� 
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
