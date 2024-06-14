package cn.widdo.starter.jwi;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JWI.
 *
 * @author XYL
 * @date 2022/11/29 11:22
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
public class WiddoStarterJWI {

	private final Logger log = LoggerFactory.getLogger(WiddoStarterJWI.class);

	@PostConstruct
	public final void postConstruct() {
		log.info("[Widdo] |- Starters [Widdo Starter JWI].");
	}

}
