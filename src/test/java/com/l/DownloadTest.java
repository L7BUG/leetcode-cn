package com.l;

import cn.hutool.http.HttpUtil;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

/**
 * DownloadTest
 *
 * @author luliangyu
 * @date 2023-02-20 16:38
 */
public class DownloadTest {
	private static final String FILE_URL = "https://lwres.yzw.cn/labor/attendance/Original/2023/0220/7c90b87a6d1f4cc397795905c2d107c1-5935236.jpg";

	@Test
	public void test() throws URISyntaxException {
		String[] split = FILE_URL.split("/");
		HttpUtil.downloadFile(FILE_URL, "./xxx/xxx/xxx/" + split[split.length - 1]);
	}
}
