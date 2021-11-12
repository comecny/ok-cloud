package com.example.es;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class ESApplication {

    @Autowired
    private ElasticsearchRestTemplate template;

    public static void main(String[] args) {
        SpringApplication.run(ESApplication.class, args);
    }

    @GetMapping("/TEST")
    public void Test(){

    }
}
