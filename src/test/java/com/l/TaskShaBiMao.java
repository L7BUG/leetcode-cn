package com.l;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * TaskShaBiMao
 *
 * @author Administrator
 * @since 2025/6/10 10:36
 */
public class TaskShaBiMao {
	// List<String> reList = Arrays.asList("</p>", "<p>", "&amp;", "ldquo;", "&amp;", "nbsp;", "<strong>", "</strong>", "ldquo;", "rdquo;");

	public void test(String jsonFileName) throws Exception {
		StringBuilder sb = new StringBuilder();
		JSONObject jsonObject = JSON.parseObject(this.getClass().getClassLoader().getResourceAsStream("sb-mao/" + jsonFileName));
		System.err.println(jsonObject);
		Assertions.assertNotNull(jsonObject);
		jsonObject = jsonObject.getJSONObject("data");
		String fileName = jsonObject.getString("name");
		sb.append("""
			# %s
			
			## 题目
			
			""".formatted(fileName));
		sb.append("\n");
		JSONArray questions = jsonObject.getJSONArray("questions");

		StringBuilder analysisSB = new StringBuilder();
		for (int i = 0; i < questions.size(); i++) {
			JSONObject question = questions.getJSONObject(i);
			JSONArray detail = question.getJSONArray("detail");
			List<String> answer = question.getList("answer", String.class);
			String analysis = question.getString("analysis");
			String h2 = """
				### %s-[%s] %s
				
				""".formatted(i + 1, answer.size() > 1 ? "多选" : "单选", question.getString("content"));
			sb.append(h2);
			for (int j = 0; j < detail.size(); j++) {
				JSONObject detailItem = detail.getJSONObject(j);
				String formatted = """
					#### %s %s
					
					""".formatted(detailItem.getString("label"), detailItem.getString("value"));
				sb.append(formatted);
			}
			// String replace = HtmlUtil.filter(analysis);
			String formatted = """
				%s. %s:%s
				""".formatted(i + 1, "[" + String.join(",", answer) + "]", analysis);
			analysisSB.append(formatted);
		}
		sb.append("""
			
			---
			
			## 答案
			""");
		sb.append(analysisSB);
		String temp = sb.toString();
		outMdFile(temp, fileName);
	}

	private void jsonToMd(JSONObject jsonObject) throws Exception {

		StringBuilder sb = new StringBuilder();
		Assertions.assertNotNull(jsonObject);
		jsonObject = jsonObject.getJSONObject("data");
		String fileName = jsonObject.getString("name");
		sb.append("""
			# %s
			
			## 题目
			
			""".formatted(fileName));
		sb.append("\n");
		JSONArray questions = jsonObject.getJSONArray("questions");

		StringBuilder analysisSB = new StringBuilder();
		for (int i = 0; i < questions.size(); i++) {
			JSONObject question = questions.getJSONObject(i);
			JSONArray detail = question.getJSONArray("detail");
			List<String> answer = question.getList("answer", String.class);
			String analysis = question.getString("analysis");
			String h2 = """
				### %s-[%s] %s
				
				""".formatted(i + 1, answer.size() > 1 ? "多选" : "单选", question.getString("content"));
			sb.append(h2);
			for (int j = 0; j < detail.size(); j++) {
				JSONObject detailItem = detail.getJSONObject(j);
				String formatted = """
					#### %s %s
					
					""".formatted(detailItem.getString("label"), detailItem.getString("value"));
				sb.append(formatted);
			}
			// String replace = HtmlUtil.filter(analysis);
			String formatted = """
				%s. %s:%s
				""".formatted(i + 1, "[" + String.join(",", answer) + "]", analysis);
			analysisSB.append(formatted);
		}
		sb.append("""
			
			---
			
			## 答案
			""");
		sb.append(analysisSB);
		String temp = sb.toString();
		outMdFile(temp, fileName);
	}

	public void outMdFile(String s, String fileName) throws Exception {
		URL resource = TaskShaBiMao.class.getClassLoader().getResource("mao/config.json");
		System.err.println("resource = " + resource);
		File root = new File(resource.toURI().getPath());
		File file = new File(root.getParentFile(), "/md-out/" + fileName + ".md");

		FileUtil.touch(file);
		FileUtil.writeUtf8String(s, file);
	}

	@Test
	public void testsetset() throws URISyntaxException {
		URI uri = this.getClass().getClassLoader().getResource("mao/config.json").toURI();
		System.out.println(uri);
		File root = new File(uri);
		System.out.println(root);
		System.out.println(root.getParentFile());
	}

	@Test
	public void test2() throws Exception {
		URL resource = this.getClass().getClassLoader().getResource("sb-mao");
		File root = new File(resource.toURI().getPath());
		for (File file : root.listFiles()) {
			test(file.getName());
		}
	}

	@Test
	public void test3() throws Exception {
		OkHttpClient okHttpClient = new OkHttpClient();
		// File file = new File(this.getClass().getClassLoader().getResource("sb-mao-config").toURI().getPath());
		JSONObject jsonObject = JSON.parseObject(this.getClass().getClassLoader().getResourceAsStream("mao/config.json"));
		Assertions.assertNotNull(jsonObject);
		JSONArray list = jsonObject.getJSONObject("data").getJSONArray("list");
		for (int i = 0; i < list.size(); i++) {
			JSONObject item = list.getJSONObject(i);
			String string = item.getJSONObject("detail").getJSONObject("question_set").getString("last_log_id");
			System.err.println("string = " + string);
			String responseBody = okHttpClient.newCall(new Request.Builder().url("https://online.jifenluohubang.com/api/student/question/set/" + string)
				.get()
				.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdXRoIjoic3R1ZGVudCIsImV4cCI6MTc1NTQ5OTc3NiwiaWF0IjoxNzU0ODk0OTc2LCJpZCI6MTkwNywibW9kZSI6Ind4YXBwIn0.jtJ7eoWcinabEyPHyN28gHNXhU4QLGEpY3w4v8e4VbM")
				.build()).execute().body().string();
			System.err.println("responseBody = " + responseBody);
			// File outJsonFile = new File(file, i + ".json");
			// FileUtil.touch(outJsonFile);
			// FileUtil.writeUtf8String(responseBody, outJsonFile);
			JSONObject temp = JSONObject.parseObject(responseBody);
			jsonToMd(temp);
		}
	}

	@Test
	public void test12312() {
		System.err.println("2025-06-10 18:29:33".length());
	}

	@Test
	public void test12312121() {
		System.err.println(new Date(1750043861));
		System.err.println(new Date(1750043861L * 1000L));
	}
}
