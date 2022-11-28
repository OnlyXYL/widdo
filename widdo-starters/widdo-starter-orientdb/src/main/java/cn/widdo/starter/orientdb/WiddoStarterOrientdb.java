package cn.widdo.starter.orientdb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

/**
 * widdo starter orientdb.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/10/14 15:24
 */
public class WiddoStarterOrientdb {

    private final Logger log = LoggerFactory.getLogger(WiddoStarterOrientdb.class);

    @PostConstruct
    public final void postConstruct() {
        log.info("[Widdo] |- Starters [Widdo Starter Orientdb].");
    }
}
