package com.app.core;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:spring-system.xml" })
public abstract class AbstractTestCase extends AbstractJUnit4SpringContextTests {
}