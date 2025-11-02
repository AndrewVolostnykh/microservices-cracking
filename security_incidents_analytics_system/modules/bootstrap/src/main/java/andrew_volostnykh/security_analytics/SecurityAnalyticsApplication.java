package andrew_volostnykh.security_analytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SecurityAnalyticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityAnalyticsApplication.class, args);
	}

}
