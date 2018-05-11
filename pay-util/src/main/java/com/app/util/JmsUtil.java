package com.app.util;

import javax.jms.Destination;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;

/**
 * <p>功 能：ActiveMQ 消息生产者工具类</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年2月9日 下午6:30:41</p>
 * @author 王建
 * @version 1.0
 */
public class JmsUtil {
	private static final Logger LOG = LogManager.getLogger(JmsUtil.class);

	/** Spring JMS Topic 操作模板 */
	private static JmsTemplate jmsTopicTemplate;
	/** Spring JMS Queue 操作模板 */
	private static JmsTemplate jmsQueueTemplate;

	@Autowired
	@Qualifier("jmsTopicTemplate")
	public void setJmsTopicTemplate(JmsTemplate jmsTopicTemplate) {
		JmsUtil.jmsTopicTemplate = jmsTopicTemplate;
	}

	@Autowired
	@Qualifier("jmsQueueTemplate")
	public void setJmsQueueTemplate(JmsTemplate jmsQueueTemplate) {
		JmsUtil.jmsQueueTemplate = jmsQueueTemplate;
	}

	public static JmsTemplate getJmsTopicTemplate() {
		return jmsTopicTemplate;
	}

	public static JmsTemplate getJmsQueueTemplate() {
		return jmsQueueTemplate;
	}

	/**
	 * 发送JMS Topic消息，使用默认目的地
	 * @param message
	 */
	public static void sendTopicMessage(Object message) {
		try {
			jmsTopicTemplate.convertAndSend(message);
		} catch (Exception e) {
			LOG.error("发送JMS Topic消息失败", e);
		}
	}

	/**
	 * 发送JMS Topic消息
	 * @param destinationName
	 * @param message
	 */
	public static void sendTopicMessage(String destinationName, final Object message) {
		try {
			jmsTopicTemplate.convertAndSend(destinationName, message);
		} catch (Exception e) {
			LOG.error("发送JMS Topic消息失败", e);
		}
	}

	/**
	 * 发送JMS Topic消息
	 * @param destination
	 * @param message
	 */
	public static void sendTopicMessage(Destination destination, final Object message) {
		try {
			jmsTopicTemplate.convertAndSend(destination, message);
		} catch (Exception e) {
			LOG.error("发送JMS Topic消息失败", e);
		}
	}

	/**
	 * 发送JMS Queue消息，使用默认目的地
	 * @param message
	 */
	public static void sendQueueMessage(Object message) {
		try {
			jmsQueueTemplate.convertAndSend(message);
		} catch (Exception e) {
			LOG.error("发送JMS Queue消息失败", e);
		}
	}

	/**
	 * 发送JMS Queue消息
	 * @param destinationName
	 * @param message
	 */
	public static void sendQueueMessage(String destinationName, final Object message) {
		try {
			jmsQueueTemplate.convertAndSend(destinationName, message);
		} catch (Exception e) {
			LOG.error("发送JMS Queue消息失败", e);
		}
	}

	/**
	 * 发送JMS Queue消息
	 * @param destination
	 * @param message
	 */
	public static void sendQueueMessage(Destination destination, final Object message) {
		try {
			jmsQueueTemplate.convertAndSend(destination, message);
		} catch (Exception e) {
			LOG.error("发送JMS Queue消息失败", e);
		}
	}
}