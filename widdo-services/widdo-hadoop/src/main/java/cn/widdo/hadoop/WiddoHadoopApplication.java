package cn.widdo.hadoop;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * WiddoHadoopApplication.
 *
 * @author XYL
 * @date 2023/07/24 10:40
 * @since 305.2.2.0
 */
@SuppressWarnings("ALL")
@SpringBootApplication
public class WiddoHadoopApplication {

	private static final Logger LOG = LoggerFactory.getLogger(WiddoHadoopApplication.class);

	@PostConstruct
	private void postConstruct() {
		LOG.info("#############################################");
		LOG.info("[Widdo] |- Service [Widdo Hadoop] Application.");
		LOG.info("#############################################");
	}

	public static void main(String[] args) {
		SpringApplication.run(WiddoHadoopApplication.class, args);
	}

}
