package cn.widdo.autoconfigure.babelnet.configure;

import cn.widdo.autoconfigure.babelnet.properties.WiddoBabelNetProperties;
import cn.widdo.autoconfigure.babelnet.annotation.WiddoBabelNet;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * BabelNet 自动配置类.
 *
 * @author XYL
 * @date 2022/12/02 18:39
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
@WiddoBabelNet
@EnableConfigurationProperties(WiddoBabelNetProperties.class)
public class WiddoBabelNetAutoConfigure {

	private static final Logger log = LoggerFactory.getLogger(WiddoBabelNetAutoConfigure.class);

	@PostConstruct
	private void postConstruct() {
		log.info("[Widdo] |- AutoConfigure [Widdo BabelNet] Auto Configure.");
	}

}
