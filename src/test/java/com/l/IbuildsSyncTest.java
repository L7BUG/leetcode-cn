package com.l;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.Getter;
import lombok.Setter;
import okhttp3.*;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * IbuildsSyncTest
 *
 * @author luliangyu
 * @date 2022-12-13 14:26
 */
public class IbuildsSyncTest {
	public static final String HOST = "http://ibuilds.seewintech.com/api/cloud";

	public static final String TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJnemR0MTdfMjAyMV9hZG1pbiIsInVzZXJfbmFtZSI6Imd6ZHQxN18yMDIxX2FkbWluIiwic2NvcGUiOlsicmVhZCJdLCJleHBpcmUiOjE2NzkwMjAzMTk5OTAsImV4cCI6MTY3OTAzMTExOSwiZGVwYXJ0IjpudWxsLCJ1c2VyTmFtZSI6IuW5v-W3nuWcsOmTgTE35Y-357q_566h55CG5ZGYIiwidXNlcklkIjoiNDM5MiIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJqdGkiOiI1YTExYTJmNS1hNTBlLTQyMjAtYjRmNS00NGE1ZTI1NGJlMzQiLCJ0ZW5hbnQiOiJnemR0MTdfMjAyMSIsImNsaWVudF9pZCI6InZ1ZSJ9.ATF7u6juu6uCmW4uv697N5MkdtlHbpdfPRbeeDBn1tcby_kS0zXfjZ6AaDpKIzGh_TY3Ir7kJ4XYwKD_OvXIhOcm19lsFrncRLApNoEzqmuwWykNwBW116aT6ckcSbQgbepQ4gsWlYHf23gfvMSo0lgzuZZkvP9An8tffv6Ou9g";

	@Test
	public void test() throws Exception {
		OkHttpClient client = new OkHttpClient().newBuilder().readTimeout(45, TimeUnit.SECONDS)
			.build();
		JSONArray jsonArray;
		LocalDate localDate = LocalDate.now().plusYears(8);
		int step = 133;
		int stop = 1;
		do {
			Request request = new Request.Builder()
				.url(HOST + "/personnel/page?page=" + step + "&limit=100&syncStatus=1")//
				.method("GET", null)
				.addHeader("Authorization", "Bearer " + TOKEN)
				.build();
			step--;
			Response response = client.newCall(request).execute();
			JSONObject jsonObject = JSON.parseObject(response.body().string());
			jsonArray = jsonObject.getJSONObject("data").getJSONArray("rows");

			List<Long> ids = new LinkedList<>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject item = jsonArray.getJSONObject(i);
				Long id = item.getLong("id");
				ids.add(id);
			}
			MediaType mediaType = MediaType.parse("application/json");
			JSONObject requestBody = new JSONObject();
			requestBody.put("endtime", localDate.plusDays((int) (Math.random() * 30)));
			requestBody.put("ids", ids);
			RequestBody body = RequestBody.create(mediaType, requestBody.toJSONString());
			request = new Request.Builder()
				.url(HOST + "/personnel/batchEndTime")
				.method("PUT", body)
				.addHeader("Authorization", "Bearer " + TOKEN)
				.addHeader("Content-Type", "application/json")
				.build();
			response = client.newCall(request).execute();
			System.err.println(jsonArray.size());
			System.err.println(response.body().string());
		} while (!jsonArray.isEmpty() && step >= stop);
	}

	@Test
	public void showConfAndSync() throws IOException {
		System.err.println("##########读取配置##########");
		InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("sync-conf.json");
		Conf conf = JSON.parseObject(resourceAsStream, Conf.class);
		System.err.println(conf);
		OkHttpClient client = new OkHttpClient().newBuilder().readTimeout(45, TimeUnit.SECONDS)
			.build();
		JSONArray jsonArray;
		LocalDate localDate = LocalDate.now().plusYears(8);
		int step = conf.getMaxPage();
		int stop = conf.getMinPage();
		do {
			String url = HOST + "/personnel/page?page=" + step + "&limit=100" + (conf.getStatus() == null ? "" : "&syncStatus=" + conf.getStatus());
			System.err.println(url);
			Request request = new Request.Builder()
				.url(url)
				.method("GET", null)
				.addHeader("Authorization", "Bearer " + conf.getToken())
				.build();
			step--;
			Response response = client.newCall(request).execute();
			JSONObject jsonObject = JSON.parseObject(response.body().string());
			jsonArray = jsonObject.getJSONObject("data").getJSONArray("rows");

			List<Long> ids = new LinkedList<>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject item = jsonArray.getJSONObject(i);
				Long id = item.getLong("id");
				ids.add(id);
			}
			MediaType mediaType = MediaType.parse("application/json");
			JSONObject requestBody = new JSONObject();
			requestBody.put("endtime", localDate.plusDays((int) (Math.random() * 30)));
			requestBody.put("ids", ids);
			RequestBody body = RequestBody.create(mediaType, requestBody.toJSONString());
			request = new Request.Builder()
				.url(HOST + "/personnel/batchEndTime")
				.method("PUT", body)
				.addHeader("Authorization", "Bearer " + conf.getToken())
				.addHeader("Content-Type", "application/json")
				.build();
			response = client.newCall(request).execute();
			System.err.println(jsonArray.size());
			System.err.println(response.body().string());
		} while (!jsonArray.isEmpty() && step >= stop);
	}

	@Getter
	@Setter
	public static class Conf {
		private Integer maxPage;
		private Integer minPage;
		private Integer status;
		private String token;

		@Override
		public String toString() {
			return "Conf{" +
				"最大页='" + maxPage + '\'' +
				", 最小页='" + minPage + '\'' +
				", 同步状态='" + status + '\'' +
				", token='" + token + '\'' +
				'}';
		}
	}
}
