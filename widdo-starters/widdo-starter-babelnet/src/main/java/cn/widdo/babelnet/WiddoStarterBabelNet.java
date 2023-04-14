package cn.widdo.babelnet;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * babelNet.
 *
 * @author XYL
 * @date 2022/11/30 14:22
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
public class WiddoStarterBabelNet {

    private final Logger log = LoggerFactory.getLogger(WiddoStarterBabelNet.class);

    @PostConstruct
    public final void postConstruct() {
        log.info("[Widdo] |- Starters [Widdo Starter JWI].");
    }
}
