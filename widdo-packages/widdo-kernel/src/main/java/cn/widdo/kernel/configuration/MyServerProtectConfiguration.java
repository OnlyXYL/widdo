package cn.widdo.kernel.configuration;

import cn.widdo.kernel.annotation.ConditionalOnServerProtectEnabled;
import cn.widdo.kernel.interceptor.ServerProtectInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * @author XYL
 * @version 1.0
 * @date 2022/06/21 19:29
 */
@Configuration
@ConditionalOnServerProtectEnabled
public class MyServerProtectConfiguration implements WebMvcConfigurer {

    @Bean
    @ConditionalOnMissingBean(value = PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        final File file = new File("");

        registry.addInterceptor(new ServerProtectInterceptor());
    }
}