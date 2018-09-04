package com.eun1310434.java.rx.common;

public class Item {
	private String mId;

	public Item(String id) {
		mId = id;
	}

	public String getId() {
		return mId;
	}

	@Override
	public String toString() {
		return "Order ID: " + mId;
	}
}
