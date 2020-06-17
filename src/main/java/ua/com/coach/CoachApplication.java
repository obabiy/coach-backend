package ua.com.coach;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import java.util.TimeZone;


@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class})
@EnableTransactionManagement
public class CoachApplication {

    @PostConstruct
    void started(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(CoachApplication.class, args);
    }

}
