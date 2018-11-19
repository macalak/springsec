package ite.librarymaster;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Use @TestConfiguration if you like to add configuration
// for your test as addition to SpringBoot app configuration.
//@TestConfiguration
@Configuration
public class MyTestConfiguration {

    @Bean
    public MyTestUtility myTestUtility(){
        return new MyTestUtility();
    }
}
