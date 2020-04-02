package cigo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "cigo")
@EntityScan( basePackages = {"cigo"} )
@EnableJpaRepositories(basePackages = "cigo")
public class StartApplication /*implements CommandLineRunner */ {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

//    @Override
//    public void run(String... args) {
//
//        log.info("StartApplication...");
//
//        System.out.println("findAll()");
//        repository.findAll().forEach(x -> System.out.println(x));
//    }

}