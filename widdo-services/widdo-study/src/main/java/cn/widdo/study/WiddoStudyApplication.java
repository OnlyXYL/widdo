package cn.widdo.study;

import cn.widdo.graph.annotation.EnableWiddoGraph;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

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

    @Bean
    public Consumer<Person> log() {
        return person -> {
            System.out.println("Received: " + person);
        };
    }

    public static class Person {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }
}
