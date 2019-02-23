package kr.or.teamserver.coinserver;

import kr.or.teamserver.coinserver.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})

public class CoinServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoinServerApplication.class, args);
	}

}

