package cn.widdo.starter.orientdb;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * widdo starter orientdb.
 *
 * @author XYL
 * @date 2022/10/14 15:24
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
public class WiddoStarterOrientdb {

    private final Logger log = LoggerFactory.getLogger(WiddoStarterOrientdb.class);

    @PostConstruct
    public final void postConstruct() {
        log.info("[Widdo] |- Starters [Widdo Starter Orientdb].");
    }
}
