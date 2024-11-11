package com.contas.contas;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan(value = {"com.contas.contas"})
@EnableMongoRepositories
public class MongoTestConfig {
}
