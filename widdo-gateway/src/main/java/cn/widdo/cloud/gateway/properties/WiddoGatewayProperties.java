package cn.widdo.cloud.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * 网关配置文件.
 *
 * @author XYL
 * @date 2022/06/10 14:51
 * @since 263.1.1.0
 */
@RefreshScope
@Data
@Configuration
@ConfigurationProperties(prefix = "widdo.gateway", ignoreUnknownFields = false)
public class WiddoGatewayProperties {

	/**
	 * 禁止外部访问的 URI，多个值的话以逗号分隔.
	 */
	private String forbidRequestUri;

}
