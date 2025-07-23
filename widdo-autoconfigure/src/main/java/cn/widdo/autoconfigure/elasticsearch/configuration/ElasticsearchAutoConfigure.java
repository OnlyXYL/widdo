package cn.widdo.autoconfigure.elasticsearch.configuration;

import cn.widdo.autoconfigure.elasticsearch.annotation.WiddoElasticsearch;
import cn.widdo.autoconfigure.elasticsearch.properties.WiddoElasticsearchProperties;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * ElasticsearchAutoConfigure.
 *
 * @author XYL
 * @date 2023/10/27 15:31
 * @since 305.2.2.0
 */
@WiddoElasticsearch
@EnableConfigurationProperties(WiddoElasticsearchProperties.class)
public class ElasticsearchAutoConfigure {

    /**
     * log.
     */
    private static final Logger log = LoggerFactory.getLogger(ElasticsearchAutoConfigure.class);

    /**
     * elastic client.
     *
     * @param widdoElasticsearchProperties 配置信息
     * @return co.elastic.clients.elasticsearch.ElasticsearchClient
     * @author XYL
     * @date 2023/11/14 19:20:48
     */
    @Bean
    public ElasticsearchClient restClient(WiddoElasticsearchProperties widdoElasticsearchProperties) {

        log.info("[Widdo] |- AutoConfigure [Widdo Elasticsearch] AutoConfigure.");
        log.info("[Widdo] |- Elasticsearch [enabled]:{}", widdoElasticsearchProperties.getEnabled());
        log.info("[Widdo] |- Elasticsearch [hosts]:{}", widdoElasticsearchProperties.getHosts());

        final RestClient restClient = RestClient.builder(createHttpHosts(widdoElasticsearchProperties))
                .setHttpClientConfigCallback(httpClientBuilder -> {
                    if (widdoElasticsearchProperties.getUsername() != null && widdoElasticsearchProperties.getPassword() != null) {
                        final BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();

                        credentialsProvider.setCredentials(AuthScope.ANY,
                                new UsernamePasswordCredentials(widdoElasticsearchProperties.getUsername(), widdoElasticsearchProperties.getPassword()));

                        httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);

                    }
                    return httpClientBuilder;
                })
                .build();

        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        // And create the API client
        return new ElasticsearchClient(transport);
    }

    /**
     * 创建 HttpHosts.
     *
     * @param widdoElasticsearchProperties
     * @return org.apache.http.HttpHost[]
     * @author XYL
     * @date 2025/07/21 16:02:07
     */
    private HttpHost[] createHttpHosts(WiddoElasticsearchProperties widdoElasticsearchProperties) {
        return Arrays.stream(widdoElasticsearchProperties.getHosts().split(","))
                .map(host -> {
                    final String[] hosts = host.split(":");
                    return new HttpHost(hosts[0], Integer.valueOf(hosts[1]));
                })
                .toArray(HttpHost[]::new);
    }

}
