package com.l;

import cn.hutool.extra.qrcode.QrCodeUtil;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * QrCodeTest
 *
 * @author luliangyu
 * @date 2023-02-27 16:03
 */
public class QrCodeTest {
	private static final String FILE_PATH = "/qr-code/test.jpg";

	@Test
	public void test() throws FileNotFoundException {
		QrCodeUtil.generate("112233", 300, 300, "jpg", new FileOutputStream(FILE_PATH));
	}
}
