package cn.widdo.starter.elasticsearch;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * WiddoStarterElasticSearch.
 *
 * @author XYL
 * @date 2023/10/27 14:00
 * @since 302.2.2.0
 */
@SuppressWarnings("ALL")
public class WiddoStarterElasticSearch {

	private final Logger log = LoggerFactory.getLogger(WiddoStarterElasticSearch.class);

	@PostConstruct
	public final void postConstruct() {
		log.info("[Widdo] |- Starters [Widdo Starter ElasticSearch].");
	}

}
