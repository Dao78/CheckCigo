package cigo.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import cigo.analysis.persistence.repositories.IAnagDependentRepository;

@SpringBootApplication
@ComponentScan(basePackages = "cigo")
@EntityScan( basePackages = {"cigo"} )
@EnableJpaRepositories(basePackages = "cigo")
public class StartApplication /*implements CommandLineRunner */ {

    private static final Logger log = LoggerFactory.getLogger(StartApplication.class);

    @Autowired
    private IAnagDependentRepository repository;

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