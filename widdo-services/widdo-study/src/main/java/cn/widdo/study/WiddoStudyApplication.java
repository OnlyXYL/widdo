package cn.widdo.study;

import cn.widdo.graph.annotation.EnableWiddoGraph;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author XYL
 * @version 1.0
 * @date 2022/08/15 16:49
 */
@EnableWiddoGraph
@SpringBootApplication
public class WiddoStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(WiddoStudyApplication.class,args);
    }
}
