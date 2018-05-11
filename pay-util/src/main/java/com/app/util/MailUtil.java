package com.app.util;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;

/**
 * <p>功 能：邮件收发工具类</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年2月20日 下午5:09:32</p>
 * @author 王建
 * @version 1.0
 */
public class MailUtil {
	private static final Logger LOG = LogManager.getLogger(MailUtil.class);
	/** 是否使用SSL加密协议发送邮件，默认使用SSL */
	private static final boolean DEFAULT_SSL = true;
	/** 邮件发送服务器地址 */
	private static String host = null;
	/** 发件人用户名 */
	private static String userName = null;
	/** 发件人密码 */
	private static String password = null;
	/** 发件人昵称 */
	private static String nickName = null;
	/** 邮件发送服务器是否启用SSL */
	private static boolean ssl = DEFAULT_SSL;

	/**
	 * 发送服务器地址
	 * @param host
	 */
	public void setHost(String host) {
		MailUtil.host = host;
	}

	/**
	 * 发件人用户名
	 * @param userName
	 */
	public void setUserName(String userName) {
		MailUtil.userName = userName;
	}

	/**
	 * 发件人密码
	 * @param password
	 */
	public void setPassword(String password) {
		MailUtil.password = password;
	}

	/**
	 * 发件人昵称
	 * @param nickName
	 */
	public void setNickName(String nickName) {
		MailUtil.nickName = nickName;
	}

	/**
	 * 邮件发送服务器是否启用SSL
	 * @param ssl
	 */
	public void setSsl(boolean ssl) {
		MailUtil.ssl = ssl;
	}

	public static String getHost() {
		return host;
	}

	public static String getUserName() {
		return userName;
	}

	public static String getPassword() {
		return password;
	}

	public static String getNickName() {
		return nickName;
	}

	public static boolean isSsl() {
		return ssl;
	}

	/**
	 * 发送文本邮件
	 * @param subject 标题
	 * @param msg 内容
	 * @param to 收件人邮件
	 * @return
	 */
	public static String sendTextMail(String subject, String msg, String... to) {
		String rs = null;
		try {
			// 参数检查
			Assert.notNull(subject, "邮件标题不能为空");
			Assert.notNull(msg, "邮件内容不能为空");
			Assert.notEmpty(to, "收件人不能为空");
			// 初始化文本邮件配置
			Email textMail = new SimpleEmail();
			textMail.setHostName(host);
			textMail.setAuthentication(userName, password);
			textMail.setSSLOnConnect(ssl);
			textMail.setFrom(userName, nickName, StandardCharsets.UTF_8.name());
			// 发送文本邮件
			rs = textMail.setSubject(subject).setMsg(msg).addTo(to).send();
		} catch (Exception e) {
			LOG.error("发送文本邮件出错", e);
		}
		return rs;
	}
}