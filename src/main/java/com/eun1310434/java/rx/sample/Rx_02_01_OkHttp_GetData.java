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

import java.io.IOException;

import com.eun1310434.java.rx.common.CommonUtils;
import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.ScheduleTest;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Rx_02_01_OkHttp_GetData implements ScheduleTest{

	private final OkHttpClient client = new OkHttpClient();
	
	private static final String URL_README = 
			"https://raw.githubusercontent.com/yudong80/reactivejava/master/README.md";

	@Override	
	public void run() { 
		Request request = new Request.Builder().url(URL_README).build();
		
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				Log.i(response.body().string());
			}			
		});
		CommonUtils.exampleComplete();
	}
		
	public static void main(String[] args) { 
		Rx_02_01_OkHttp_GetData test = new Rx_02_01_OkHttp_GetData();
		test.run();
	}
}
