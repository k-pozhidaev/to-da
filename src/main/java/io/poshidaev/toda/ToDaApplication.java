package io.poshidaev.toda;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ToDaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToDaApplication.class, args);
    }

}
