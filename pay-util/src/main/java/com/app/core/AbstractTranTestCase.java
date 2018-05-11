package com.app.core;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:spring-system.xml" })
@Rollback(false)
public abstract class AbstractTranTestCase extends AbstractTransactionalJUnit4SpringContextTests {
}