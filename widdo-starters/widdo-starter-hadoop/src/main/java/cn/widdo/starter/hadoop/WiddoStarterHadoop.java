package cn.widdo.starter.hadoop;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * WiddoStarterHadoop.
 *
 * @author XYL
 * @date 2023/09/08 12:13
 * @since 305.2.2.0
 */
@SuppressWarnings("ALL")
public class WiddoStarterHadoop {

	private final Logger log = LoggerFactory.getLogger(WiddoStarterHadoop.class);

	@PostConstruct
	public final void postConstruct() {
		log.info("[Widdo] |- Starters [Widdo Starter Hadoop].");
	}

}
