package cn.widdo.cloud.gateway.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * 跨域配置.
 *
 * @author XYL
 * @date 2022/06/10 15:50
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
@Configuration
public class GatewayCorsConfigure {

    /**
     * CorsWebFilter.
     *
     * @return org.springframework.web.cors.reactive.CorsWebFilter
     * @author XYL
     * @className cn.widdo.cloud.gateway.configure.GatewayCorsConfigure
     * @date 2022/11/18 17:36
     **/
    @Bean
    public CorsWebFilter corsWebFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowCredentials(true);
        cors.addAllowedOrigin(CorsConfiguration.ALL);
        cors.addAllowedHeader(CorsConfiguration.ALL);
        cors.addAllowedMethod(CorsConfiguration.ALL);
        source.registerCorsConfiguration("/**", cors);
        return new CorsWebFilter(source);
    }
}
