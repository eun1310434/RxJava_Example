/*==================================================================================================
�� INFORMATION
  �� Data : 06.Oct.2018
  �� Mail : eun1310434@gmail.com
  �� WebPage : https://eun1310434.github.io/
  �� Reference
    - RxJava ���α׷��� P187

�� FUNCTION
  �� 
   
�� Study
  �� 
  
==================================================================================================*/
package com.eun1310434.java.rx.sample;

import java.io.IOException;

import com.eun1310434.java.rx.common.Log;
import com.eun1310434.java.rx.common.ScheduleTest;

import static com.eun1310434.java.rx.common.CommonUtils.GITHUB_ROOT;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Rx_02_02_OkHttp_CallbackProblem implements ScheduleTest{
	
	private static final String FIRST_URL = "https://api.github.com/zen";
	private static final String SECOND_URL = GITHUB_ROOT + "/samples/callback_hell";

	private final OkHttpClient client = new OkHttpClient();
	
	@Override
	public void run() { 
		Request request = new Request.Builder().url(FIRST_URL).build();
		
		client.newCall(request).enqueue(new Callback() {
			//onSuccess�� ������� �ʴ� ������ ������ �����Ͽ��� ��� �ϴ� 
			//�۾��� onSuccess���� ���� �����̴�.
			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				Log.i(response.body().string());
				
				//FIRST_URL ���� ������ SECOND_URL ���� �õ�
				Request request = new Request.Builder().url(SECOND_URL).build();
				client.newCall(request).enqueue(onSuccess);				
			}			
		});		
	}

	private Callback onSuccess = new Callback() {
		@Override
		public void onFailure(Call call, IOException e) {
			e.printStackTrace();
		}

		@Override
		public void onResponse(Call call, Response response) throws IOException {
			Log.i(response.body().string());
		} 
	};

	public static void main(String[] args) { 
		Rx_02_02_OkHttp_CallbackProblem test = new Rx_02_02_OkHttp_CallbackProblem();
		test.run();
	}
}
