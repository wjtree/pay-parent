package com.app.pay.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * <p>功 能：支付专用线程执行器</p>
 * <p>公司：深圳华海乐盈网络科技有限公司</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年4月20日 下午2:38:50</p>
 * @author 王建
 * @version 1.0
 */
@Component
public class PayExecutor {
	private static final Logger LOG = LogManager.getLogger(PayExecutor.class);

	/** 线程执行器 */
	private static ThreadPoolExecutor executor = null;

	/**
	 * 创建线程执行器
	 */
	@PostConstruct
	public void init() {
		if (null == executor) {
			// 创建固定大小的线程执行器
			executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
			if (LOG.isInfoEnabled())
				LOG.info("支付专用线程执行器创建成功，线程池大小：{}", executor.getMaximumPoolSize());
		}
	}

	/**
	 * 关闭线程执行器
	 */
	@PreDestroy
	public void destroy() {
		if (null != executor) {
			executor.shutdown();
			if (LOG.isInfoEnabled())
				LOG.info("支付专用线程执行器关闭成功");
		}
	}

	/**
	 * 执行任务
	 * @param command
	 */
	public static void execute(final Runnable command) {
		// 线程开始执行
		executor.execute(command);
		if (LOG.isDebugEnabled())
			LOG.debug("支付专用线程执行器中当前线程数量：{}，活跃线程数量：{}", executor.getPoolSize(), executor.getActiveCount());
	}
}