package com.ps.beans.ctr;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CIBeansTest {
    private Logger logger = LoggerFactory.getLogger(CIBeansTest.class);

    @Test
    public void testConfig() {
        final ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/ctr/sample-config-02.xml");

        logger.info(" --- All beans in context --- ");
        for (String beanName : ctx.getBeanDefinitionNames()) {
            logger.info("Bean " + beanName + " of type " + ctx.getBean(beanName).getClass().getSimpleName());
        }

        //DONE 3. Retrieve beans of types ComplexBean and make sure their dependencies were correctly set.
        ComplexBeanImpl bean0 = ctx.getBean("complexBean0", ComplexBeanImpl.class);
        assertNotNull(bean0);
        assertNotNull(bean0.getSimpleBean());

        ComplexBeanImpl bean1 = ctx.getBean("complexBean1", ComplexBeanImpl.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getSimpleBean());
        assertTrue(bean1.isComplex());

        ComplexBean2Impl bean2 = ctx.getBean("complexBean2", ComplexBean2Impl.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getSimpleBean1());
        assertNotNull(bean2.getSimpleBean2());

    }
}
