/*==================================================================================================
�� INFORMATION
  �� Data : 06.Oct.2018
  �� Mail : eun1310434@gmail.com
  �� WebPage : https://eun1310434.github.io/
  �� Reference
    - RxJava ���α׷��� P189

�� FUNCTION
  �� 
   
�� Study
  �� 
  
==================================================================================================*/
package com.eun1310434.java.rx.sample;

import static com.eun1310434.java.rx.common.CommonUtils.GITHUB_ROOT;

import java.io.IOException;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.OkHttpHelper;
import com.eun1310434.java.rx.common.ScheduleTest;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Rx_02_03_OkHttp_CallbackSolve implements ScheduleTest{
	private static final String FIRST_URL = "https://api.github.com/zen";
	private static final String SECOND_URL = GITHUB_ROOT + "/samples/callback_heaven";

	@Override
	public void run() { 
		//OkHttp_CallbackProblem ���� ���� ����
		//1 - Declarative Concurrency(������ ���ü�) : ������ �κ��� ����
		//2 - legibility(������)
		
		//usingConcat(); //1�� ����� ���� �� 2�� ����� ����
		usingZip();//1���� 2���� ���� �����ѵ� ��� �� ��ħ
	}
	
	public void usingConcat() { 
		CommonUtils.exampleStart();
		Observable<String> source = 
				Observable
				.just(FIRST_URL)
				.subscribeOn(Schedulers.io())
				.map(str -> OkHttpHelper(str)) 
				// OkHttpClient�� enqueue() �޼��带 ȣ���Ͽ� �ݹ��� ���޹޴°��� �ƴ�
				// OkHttpHelper.get()�޼��� �ȿ��� OkHttpClint�� execute()�޼��带 ȣ���Ͽ�
				// ������ �߻��ϸ� �����ܰ� ���� ����
				.concatWith(
						//�ᱹ ù��° Observable���� ������ ������ ���� �� ���� ��ٷ��� ��
						Observable
						.just(SECOND_URL)
						.map(str -> OkHttpHelper.get(str))
				);
		
		source.subscribe(str -> Log.it(str));
		CommonUtils.sleep(5000);
		CommonUtils.exampleComplete();
	}

	public void usingZip() { 
		CommonUtils.exampleStart();
		Observable<String> first = 
				Observable
				.just(FIRST_URL)
				.subscribeOn(Schedulers.io())
				.map(str -> OkHttpHelper(str));
		// OkHttpClient�� enqueue() �޼��带 ȣ���Ͽ� �ݹ��� ���޹޴°��� �ƴ�
		// OkHttpHelper.get()�޼��� �ȿ��� OkHttpClint�� execute()�޼��带 ȣ���Ͽ�
		// ������ �߻��ϸ� �����ܰ� ���� ����
		
		Observable<String> second = 
				Observable
				.just(SECOND_URL)
				.subscribeOn(Schedulers.io())
				.map(str -> OkHttpHelper(str));
		// OkHttpClient�� enqueue() �޼��带 ȣ���Ͽ� �ݹ��� ���޹޴°��� �ƴ�
		// OkHttpHelper.get()�޼��� �ȿ��� OkHttpClint�� execute()�޼��带 ȣ���Ͽ�
		// ������ �߻��ϸ� �����ܰ� ���� ����
		
		
		
		Observable.zip(
				first, second, 
				// �ΰ��� Observable�� �̸� ������ �� ����� ��ħ
				(a, b) -> ("\n>>" + a + "\n>>" + b))
		.subscribe(str -> Log.it(str));
		
		CommonUtils.sleep(5000);
	}
	

	private static OkHttpClient client = new OkHttpClient();
	public static String OkHttpHelper(String url) throws IOException { 
		Request request = new Request.Builder().url(url).build();
		
		try {
			Response res = client.newCall(request).execute();
			return res.body().string();
		} catch (IOException e) {
			Log.e(e.getMessage());
			throw e;
		} 
	}
	
	public static void main(String[] args) { 
		Rx_02_03_OkHttp_CallbackSolve test = new Rx_02_03_OkHttp_CallbackSolve();
		test.run();
	}
}
