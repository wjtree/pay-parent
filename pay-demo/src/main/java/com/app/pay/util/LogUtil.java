package com.app.pay.util;

import org.apache.commons.lang3.StringUtils;

import com.app.code.Symbol;
import com.app.pay.code.PayConstant;
import com.app.util.DateUtil;
import com.app.util.FileUtil;

/**
 * <p>功 能：第三方支付接口通讯报文处理</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月10日 下午8:07:24</p>
 * @author 王建
 * @version 1.0
 */
public class LogUtil {
	/**
	 * 将日志写入到文件中
	 * @param prefixPath 日志文件路径前缀
	 * @param service 接口名称
	 * @param content 接口报文内容
	 * @throws Exception
	 */
	public static void writeLog(String prefixPath, String service, String content) throws Exception {
		// 获取文件名称前缀
		String prefixName = StringUtils.replace(service, Symbol.DOT.getSymbol(), Symbol.UNDERSCORE.getSymbol());
		// 获取文件全路径名
		String fileName = FileUtil.getFileName(prefixPath, prefixName, PayConstant.PAY_LOG_SIZE);
		// 将报文写入文件
		FileUtil.writeStringToFile(fileName, content);
	}

	/**
	 * 组装日志的标题
	 * <p>示例：==== 2017-03-24 12:05:41 Request ====</p>
	 * @param params
	 * @return
	 */
	public static String getTitle(Object... params) throws Exception {
		// 标题前后的标识符号
		String mark = StringUtils.repeat(Symbol.EQUALS.getSymbol(), 15);
		// 日期时间，格式：yyyy-MM-dd HH:mm:ss
		String dateTime = DateUtil.getCurrentDateTime();
		// 将参数数组以空格分隔组成字符串
		String param = StringUtils.join(params, StringUtils.SPACE);
		// 组成最终标题
		return StringUtils.joinWith(StringUtils.SPACE, mark, dateTime, param, mark);
	}

	/**
	 * 将支付接口日志写入到文件中
	 * @param service 接口名称
	 * @param contents 接口报文内容
	 * @throws Exception
	 */
	public static void writePayLog(String service, String... contents) throws Exception {
		String content = null;
		// 待写入文件内容，用换行符分隔
		switch (contents.length) {
		case 1: // 通知报文
			content = StringUtils.joinWith(Symbol.LINE_BREAK.getSymbol(), getTitle("Notify"), contents[0]);
			break;
		case 2: // 接口请求与响应报文
			content = StringUtils.joinWith(Symbol.LINE_BREAK.getSymbol(), getTitle("Request"), contents[0], getTitle("Response"), contents[1]);
			break;
		default: // 其他报文
			content = StringUtils.join(contents, Symbol.LINE_BREAK.getSymbol());
			break;
		}
		// 记录通知报文
		writeLog(PayConstant.PAY_LOG, service, content);
	}
}