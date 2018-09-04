/*==================================================================================================
�� INFORMATION
�� Data : 04.Sep.2018
�� Mail : eun1310434@gmail.com
�� WebPage : https://eun1310434.github.io/
�� Reference : RxJava ���α׷��� P54
     
�� FUNCTION
�� 
   
�� Study
�� Creating(����) : Operators that originate new Observables.
�� From() : convert some other object or data structure into an Observable
�� fromArray()
�� Map ��ü�� ���� Observable Ŭ������ from() �Լ��� ����.
- Map �������̽��� �迭�� �ƴ�
- Iterable<E> �������̽��� �������� ����
- from() �迭 �Լ��� ������ �� ����.
==================================================================================================*/
package com.eun1310434.java.rx.observables.creating;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Item;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.RxTest;

import io.reactivex.Observable;

public class Rx_04_02_FromIterable implements RxTest{
	
	@Override
	public void marbleDiagram() { 
		observableSet(list());
		observableSet(set());
		observableSet(blockingQueue());
		observableSet(arrayList());
	}
	
	public void observableSet(Iterable<Item> items){
		Observable<Item> source = Observable.fromIterable(items);
		source.subscribe(data -> Log.i(data.getId()));
		CommonUtils.exampleComplete();
	}
	
	public List<Item> list() {
		CommonUtils.exampleStart("01) FromIterable - list");
		List<Item> itemList = new ArrayList<>();
		itemList.add(new Item("list-1"));
		itemList.add(new Item("list-2"));
		itemList.add(new Item("list-3"));
		return itemList;
	}

	public Set<Item> set() {
		CommonUtils.exampleStart("02) FromIterable - set");
		Set<Item> itemSet = new HashSet<>();
		itemSet.add(new Item("set-1"));
		itemSet.add(new Item("set-2"));
		itemSet.add(new Item("set-3"));
		return itemSet;
	}

	public BlockingQueue<Item> blockingQueue() {
		CommonUtils.exampleStart("03) FromIterable - blockingQueue");
		BlockingQueue<Item> itemQueue = new ArrayBlockingQueue<>(100);
		itemQueue.add(new Item("blockingQueue-1"));
		itemQueue.add(new Item("blockingQueue-2"));
		itemQueue.add(new Item("blockingQueue-3"));
		return itemQueue;
	}

	public ArrayList<Item> arrayList() {
		CommonUtils.exampleStart("04) FromIterable - arrayList");
		ArrayList<Item> itemArraylist = new ArrayList<>();
		itemArraylist.add(new Item("arrayList-1"));
		itemArraylist.add(new Item("arrayList-2"));
		itemArraylist.add(new Item("arrayList-3"));
		return itemArraylist;
	}

	public Map<Item, Integer> map() {
		/*
		 * �� Map ��ü�� ���� Observable Ŭ������ from() �Լ��� ����. - Map �������̽��� �迭�� �ƴ� -
		 * Iterable<E> �������̽��� �������� ���� - from() �迭 �Լ��� ������ �� ����.
		 */
		return null;
	}

	public static void main(String[] args) {
		Rx_04_02_FromIterable test = new Rx_04_02_FromIterable();
		test.marbleDiagram();
	}
}