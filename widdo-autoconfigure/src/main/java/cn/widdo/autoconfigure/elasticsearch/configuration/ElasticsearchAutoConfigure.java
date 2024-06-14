package cn.widdo.autoconfigure.elasticsearch.configuration;

import cn.widdo.autoconfigure.elasticsearch.annotation.WiddoElasticsearch;
import cn.widdo.autoconfigure.elasticsearch.properties.WiddoElasticsearchProperties;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
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
     * @return co.elastic.clients.elasticsearch.ElasticsearchClient
     * @author XYL
     * @date 2023/11/14 19:20:48
     */
    @Bean
    public ElasticsearchClient restClient(WiddoElasticsearchProperties widdoElasticsearchProperties) {

        final String[] split = widdoElasticsearchProperties.getUris().split(",");

        //转换参数
        final HttpHost[] httpHosts = Arrays.asList(split).stream().map(this::setHost).toArray(HttpHost[]::new);

        log.info("[Widdo] |- AutoConfigure [Widdo Elasticsearch] AutoConfigure. Cluster：{}", Arrays.toString(httpHosts));

        RestClient restClient = RestClient.builder(httpHosts).build();

        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        // And create the API client
        return new ElasticsearchClient(transport);
    }

    /**
     * 转换HttpHost.
     *
     * @param uri
     * @return org.apache.http.HttpHost
     * @author XYL
     * @date 2024/03/06 20:39:40
     */
    private HttpHost setHost(String uri) {
        final String[] split = uri.split("\\:");
        return new HttpHost(split[0], Integer.valueOf(split[1]));
    }

}
