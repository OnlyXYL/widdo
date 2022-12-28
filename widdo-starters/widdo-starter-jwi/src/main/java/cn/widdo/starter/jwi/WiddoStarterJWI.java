package cn.widdo.starter.jwi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

/**
 * JWI.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/11/29 11:22
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class WiddoStarterJWI {

    private final Logger log = LoggerFactory.getLogger(WiddoStarterJWI.class);

    @PostConstruct
    public final void postConstruct() {
        log.info("[Widdo] |- Starters [Widdo Starter JWI].");
    }

}
