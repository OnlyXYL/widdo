package cn.widdo.autoconfigure.babelnet.configure;

import cn.widdo.autoconfigure.condition.WiddoBabelNet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

/**
 * BabelNet 自动配置类.
 *
 * @author XYL
 * @since 263.1.1.0
 * @date 2022/12/02 18:39
 */
@WiddoBabelNet
public class WiddoBabelNetAutoConfigure {

    private static final Logger log = LoggerFactory.getLogger(WiddoBabelNetAutoConfigure.class);

    @PostConstruct
    private void postConstruct() {
        log.info("[Widdo] |- AutoConfigure [Widdo BabelNet] Auto Configure.");
    }
}
