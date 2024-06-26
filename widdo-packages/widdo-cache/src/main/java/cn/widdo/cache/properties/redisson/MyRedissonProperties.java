package cn.widdo.cache.properties.redisson;

import cn.widdo.assistant.constant.PropertyConstant;
import lombok.Data;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 配置文件.
 *
 * @author XYL
 * @date 2022/07/05 17:37
 * @since 263.1.1.0
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = PropertyConstant.PROPERTY_REDIS_REDISSON)
@ConditionalOnClass(RedissonClient.class)
public class MyRedissonProperties {

	/**
	 * 配置信息.
	 */
	private MyRedissonConfigProperties config = new MyRedissonConfigProperties();

}
