package cn.widdo.starter.sql;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * WiddoStarterSQL.
 *
 * @author XYL
 * @date 2023/08/07 16:44
 * @since 305.2.2.0
 */
@SuppressWarnings("ALL")
public class WiddoStarterSQL {

	private final Logger log = LoggerFactory.getLogger(WiddoStarterSQL.class);

	@PostConstruct
	public final void postConstruct() {
		log.info("[Widdo] |- Starters [Widdo Starter SQL].");
	}

}
