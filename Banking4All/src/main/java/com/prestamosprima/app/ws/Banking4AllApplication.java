package com.prestamosprima.app.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class Banking4AllApplication {

	public static void main(String[] args) {
		SpringApplication.run(Banking4AllApplication.class, args);
	}
	
	/*@Bean
    public BCryptPasswordEncoder bCryptpasswordEncoder() {
        return new BCryptPasswordEncoder();
    }*/

}
