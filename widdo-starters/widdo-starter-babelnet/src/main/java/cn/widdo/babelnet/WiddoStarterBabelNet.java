package cn.widdo.babelnet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

/**
 * babelNet.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/11/30 14:22
 */
public class WiddoStarterBabelNet {

    private final Logger log = LoggerFactory.getLogger(WiddoStarterBabelNet.class);

    @PostConstruct
    public final void postConstruct() {
        log.info("[Widdo] |- Starters [Widdo Starter JWI].");
    }
}
