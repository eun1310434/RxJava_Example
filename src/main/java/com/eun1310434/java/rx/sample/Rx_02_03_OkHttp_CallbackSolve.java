/*==================================================================================================
□ INFORMATION
  ○ Data : 06.Oct.2018
  ○ Mail : eun1310434@gmail.com
  ○ WebPage : https://eun1310434.github.io/
  ○ Reference
    - RxJava 프로그래밍 P189

□ FUNCTION
  ○ 
   
□ Study
  ○ 
  
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
		//OkHttp_CallbackProblem 보다 좋은 이유
		//1 - Declarative Concurrency(선언적 동시성) : 스레드 부분은 구별
		//2 - legibility(가독성)
		
		//usingConcat(); //1번 결과를 얻은 뒤 2번 결과를 실행
		usingZip();//1번과 2번을 먼저 실행한뒤 결과 만 합침
	}
	
	public void usingConcat() { 
		CommonUtils.exampleStart();
		Observable<String> source = 
				Observable
				.just(FIRST_URL)
				.subscribeOn(Schedulers.io())
				.map(str -> OkHttpHelper(str)) 
				// OkHttpClient의 enqueue() 메서드를 호출하여 콜백을 전달받는것이 아닌
				// OkHttpHelper.get()메서드 안에서 OkHttpClint의 execute()메서드를 호출하여
				// 에러가 발생하면 다음단계 실행 안함
				.concatWith(
						//결국 첫번째 Observable에서 데이터 발행이 끝날 때 까지 기다려야 함
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
		// OkHttpClient의 enqueue() 메서드를 호출하여 콜백을 전달받는것이 아닌
		// OkHttpHelper.get()메서드 안에서 OkHttpClint의 execute()메서드를 호출하여
		// 에러가 발생하면 다음단계 실행 안함
		
		Observable<String> second = 
				Observable
				.just(SECOND_URL)
				.subscribeOn(Schedulers.io())
				.map(str -> OkHttpHelper(str));
		// OkHttpClient의 enqueue() 메서드를 호출하여 콜백을 전달받는것이 아닌
		// OkHttpHelper.get()메서드 안에서 OkHttpClint의 execute()메서드를 호출하여
		// 에러가 발생하면 다음단계 실행 안함
		
		
		
		Observable.zip(
				first, second, 
				// 두개의 Observable을 미리 수행한 뒤 결과만 합침
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
