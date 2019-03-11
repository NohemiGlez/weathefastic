package main.java.com.weather;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "thymeleaf.setup", "thymeleaf.mvc.controller" })
public class WeatherAppConfiguration {

}
