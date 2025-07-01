package com.l;

import com.alibaba.fastjson2.JSONObject;
import okhttp3.*;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class WxWorkBotTest {
	@Test
	public void test() throws Exception {
		OkHttpClient client = new OkHttpClient().newBuilder()
			.build();
		MediaType mediaType = MediaType.parse("application/json");
		JSONObject bodyJson = new JSONObject();
		bodyJson.put("msgtype", "markdown");
		bodyJson.put("markdown", Map.of("content", """
			# 一级标题
			## 二级标题
			### 3
			
			> asdsadas
			
			---
			
			- asdasd
			- asdsad
			- asd
			
			---
			
			1. asdasd
			2. asdasfg
			
			-------------
			
			`System.out.println("12312312");`
			
			```
			select
			```
			
			[百度一下](https://www.baidu.com)
			"""));

		RequestBody body = RequestBody.create(mediaType, bodyJson.toString());
		Request request = new Request.Builder()
			.url("https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=f0e51cab-2180-42d5-8016-523093965082")
			.method("POST", body)
			.addHeader("Content-Type", "application/json")
			.build();
		Response response = client.newCall(request).execute();
		System.err.println(response.body().string());
	}
}
