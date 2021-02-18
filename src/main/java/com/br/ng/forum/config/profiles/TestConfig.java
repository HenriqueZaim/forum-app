package com.br.ng.forum.config.profiles;

import java.text.ParseException;

import com.br.ng.forum.common.DBService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {
    
    @Autowired
    private DBService dBService;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dBService.instantiateTestDatabase();
		return true;
	}
}
