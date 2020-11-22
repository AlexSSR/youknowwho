package com.t9d.tech.org;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpringBootApp {

    public static void main(String[] args) {

        SpringApplicationBuilder builder = new SpringApplicationBuilder(SpringBootApp.class);
        //SpringApplication.run(SystemctlApplication.class, args);
        builder.headless(false)
                // .web(WebApplicationType.NONE)
                // .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
