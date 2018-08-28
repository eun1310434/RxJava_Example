/*==================================================================================================
�� INFORMATION
�� Data : 28.08.2018
�� Mail : eun1310434@gmail.com
�� WebPage : https://eun1310434.github.io/
�� Reference
- RxJava ���α׷��� P100
     
�� FUNCTION
�� 
   
�� Study
�� reduce()
- ��Ȳ�� ���� ����� �����͸� �����Ͽ� � ����� ���� �� ���
- Observable�� �̿��� ������ �����͸� 1���� ��Ƽ� ���� ����� ������ �� �� ���
- reduce() Method�� call�ϸ� Parameter�� �ѱ� ���� ǥ���Ŀ� ���� ��� ���� �Ϸ� �ɼ� ����
    �׷��� Maybe Object�� ���ϵ�
- apply a function to each item emitted by an Observable, sequentially, and emit the final value

�� the original form of Maybe()
public final Maybe<T> reduce(BiFunction<T, T, T> reducer)

�� RxJava Generic Method Interface
01) Predicate<T> : boolean test(T t) // return to true or false
02) Consumer<T> : void accept(T t) // no return
03) Function<T,R> : R apply(T t) // return to R type

==================================================================================================*/
package com.eun1310434.java.rx.observables.mathematical;


import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class Rx_06_00_Reduce implements RxTest {
	
	@Override
	public void marbleDiagram() { 
		// 1. MarbleDiagram : reduce()
		CommonUtils.exampleStart("01) MarbleDiagram : reduce()");
		
		String[] words = {"A", "B", "C", "D", "E"}; 
		Maybe<String> source = Observable
				.fromArray(words)
				.reduce((word1, word2) -> word2 + "(" + word1 + ")");

		// source.subscribe(_color -> Log.i(_color));
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}
	
	public void biFunction() {
		// 2. MarbleDiagram : reduce()
		CommonUtils.exampleStart("02) MarbleDiagram : biFunction()");
		
		BiFunction<String, String, String> mergeWords = 
				(word1, word2) -> "(" + word1 + ")" + word2;

				String[] words = {"A", "B", "C", "D", "E"}; 
		Maybe<String> source = Observable
				.fromArray(words)
				.reduce(mergeWords);

		// source.subscribe(_color -> Log.i(_color));
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args){ 
		Rx_06_00_Reduce test = new Rx_06_00_Reduce();
		test.marbleDiagram();
		test.biFunction();
	}
}
