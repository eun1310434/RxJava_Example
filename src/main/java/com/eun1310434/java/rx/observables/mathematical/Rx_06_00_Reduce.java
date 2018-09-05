/*==================================================================================================
�� INFORMATION
�� Data : 04.Sep.2018
�� Mail : eun1310434@gmail.com
�� WebPage : https://eun1310434.github.io/
�� Reference : RxJava ���α׷��� P100
       
�� FUNCTION
�� 
   
�� Study

�� Mathematical and Aggregate(���а� ������)
- Operators that operate on the entire sequence of items emitted by an Observable

�� reduce()
- apply a function to each item emitted by an Observable, sequentially, and emit the final value
- �����͸� �����Ͽ� � ����� ���� �� ���
- Observable�� �̿��� ������ �����͸� 1���� ��Ƽ� ���� ����� ������ �� �� ���
- reduce() Method�� call�ϸ� Parameter�� �ѱ� ���� ǥ���Ŀ� ���� ��� ���� �Ϸ� �ɼ� ����
    �׷��� Maybe Object�� ���ϵ�
- The Reduce operator applies a function to the first item emitted by the source Observable 
  and then feeds the result of the function back into the function along with the second item 
  emitted by the source Observable, continuing this process until the source Observable emits its final item 
  and completes, whereupon the Observable returned from Reduce emits the final value returned from the function.
  This sort of operation is sometimes called ��accumulate,�� ��aggregate,�� ��compress,�� ��fold,�� or ��inject�� in other contexts.

�� the original form of Maybe()
- public final Maybe<T> reduce(BiFunction<T1, T2, T3> reducer)
- T1 : input 1
- T2 : input 2
- T3 : output 3

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
		String[] words = {"A", "B", "C", "D", "E"}; 
		MaybeSet("01) reduce()",words,(word1, word2) -> word2 + "(" + word1 + ")");
		
		BiFunction<String, String, String> mergeWords = (word1, word2) -> "(" + word1 + ")" + word2;
		MaybeSet("02) reduce() - BiFunction",words,mergeWords);

		Integer[] nums = {1, 2, 3, 4, 5}; 
		MaybeSet("03) reduce()", nums , (num1, num2) -> num1 + num2);
	}
	
	
	public <T> void MaybeSet(String title, T[] strs, BiFunction<T, T, T> biFunc){
		CommonUtils.exampleStart(title);
		Maybe<T> source = Observable
				.fromArray(strs)
				.reduce(biFunc);
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args){ 
		Rx_06_00_Reduce test = new Rx_06_00_Reduce();
		test.marbleDiagram();
	}
}
