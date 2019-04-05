package com.ps.beans.others;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 3/26/16.
 */
public class MtBeanTest {

    private Logger logger = LoggerFactory.getLogger(MtBeanTest.class);

    @Test
    public void testConfig() {
        //DONE 6. Modify this class to use the new set of configuration files, created by resolving DONE 5.
        //DONE 7. Try to use wildcards as well.
        ApplicationContext numCtx = new ClassPathXmlApplicationContext("classpath:spring/others/num-cfg.xml");
        MultipleTypesBean mtBean = numCtx.getBean("mtBean", MultipleTypesBean.class);
        assertNotNull(mtBean);

        ApplicationContext collectionCtx = new ClassPathXmlApplicationContext("classpath:spring/others/collection-cfg.xml");
        CollectionHolder collectionHolder = collectionCtx.getBean("collectionHolder", CollectionHolder.class);
        assertNotNull(collectionHolder);


    }
}
