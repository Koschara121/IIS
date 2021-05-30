package IIS.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@ComponentScan(basePackages = "/IIS")
public class ApplicationConfig {
}
