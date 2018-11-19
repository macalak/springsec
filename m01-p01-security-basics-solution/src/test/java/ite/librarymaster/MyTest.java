package ite.librarymaster;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes={MyTestConfiguration.class})
// Use @Import if your configuration is annotated as @TestConfiguration
// @Import(MyTestConfiguration.class)
public class MyTest {
    final private static Logger LOG = LoggerFactory.getLogger(MyTest.class);

    @Autowired
    MyTestUtility myTestUtility;

    @Test
    public void testCase(){
        LOG.info("Executing test");
        myTestUtility.doStuff();
    }
}
