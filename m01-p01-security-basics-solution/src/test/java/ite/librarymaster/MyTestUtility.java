package ite.librarymaster;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.annotation.PostConstruct;


public class MyTestUtility {
    final private static Logger LOG = LoggerFactory.getLogger(MyTestUtility.class);

    @PostConstruct
    private void init(){
        LOG.info("Initializing MyTestUtility ...");
    }

    public void doStuff(){
        LOG.info("Doing stuff  ...");
    }

}
