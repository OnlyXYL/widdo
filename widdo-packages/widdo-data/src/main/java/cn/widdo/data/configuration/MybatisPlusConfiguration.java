package cn.widdo.data.configuration;

import cn.widdo.assistant.constant.PropertyConstant;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * mybatis plus配置
 *
 * @author XYL
 * @version 1.2
 * @date 2022/09/21 16:37
 */
@Configuration
public class MybatisPlusConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MybatisPlusConfiguration.class);

    @Value(PropertyConstant.ANNOTATION_SQL_INIT_PLATFORM)
    private String platform;

    @PostConstruct
    public void postConstruct() {
        log.debug("[Widdo] |- Plugin [Widdo Mybatis Plus] Auto Configure.");
    }

    private DbType parseDbType() {
        if (StringUtils.isNotBlank(platform)) {
            DbType type = DbType.getDbType(platform);
            if (ObjectUtils.isNotEmpty(type)) {
                return type;
            }
        }

        return DbType.POSTGRE_SQL;
    }

    /**
     * 防止 修改与删除时对全表进行操作
     *
     * @return {@link MybatisPlusInterceptor}
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(parseDbType()));
        log.trace("[Smart] |- Bean [Mybatis Plus Interceptor] Auto Configure.");
        return mybatisPlusInterceptor;
    }

    @Bean
    public BlockAttackInnerInterceptor blockAttackInnerInterceptor() {
        BlockAttackInnerInterceptor blockAttackInnerInterceptor = new BlockAttackInnerInterceptor();
        log.trace("[Smart] |- Bean [Block Attack Inner Interceptor] Auto Configure.");
        return blockAttackInnerInterceptor;
    }
}
