package cn.widdo.starter.jena;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * WiddoStarterJena.
 *
 * @author XYL
 * @date 2023/03/13 17:58
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
public class WiddoStarterJena {

	private final Logger log = LoggerFactory.getLogger(WiddoStarterJena.class);

	@PostConstruct
	public final void postConstruct() {
		log.info("[Widdo] |- Starters [Widdo Starter Jena].");
	}

}
