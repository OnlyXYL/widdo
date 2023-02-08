package cn.widdo.cloud.gateway.filter;

import cn.widdo.assistant.entity.result.MyResponse;
import cn.widdo.cloud.gateway.properties.WiddoGatewayProperties;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.*;

/**
 * 网关请求拦截器.
 * <p>
 * 用来控制资源请求要不要经过网关
 *
 * @author XYL
 * @since 263.1.1.0
 * @date 2022/06/10 14:42
 */
@Slf4j
@Component
public class GatewayRequestFilter implements GlobalFilter {

    /**
     * properties.
     */
    private WiddoGatewayProperties widdoGatewayProperties;

    @Autowired
    public GatewayRequestFilter(final WiddoGatewayProperties widdoGatewayProperties) {
        this.widdoGatewayProperties = widdoGatewayProperties;
    }

    /**
     * pathMatcher.
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        final ServerHttpRequest request = exchange.getRequest();
        final ServerHttpResponse response = exchange.getResponse();

        //禁止客户端访问的资源逻辑
        final Mono<Void> validateForbidUri = validateForbidUri(request, response);
        if (validateForbidUri != null) {
            return validateForbidUri;
        }

        //日志打印
        printLog(exchange);

        return chain.filter(exchange);
    }


    /**
     * 校验禁止访问的资源.
     *
     * @param request
     * @param response
     * @return reactor.core.publisher.Mono<java.lang.Void>
     * @throws
     * @author XYL
     * @className cn.widdo.filter.GatewayRequestFilter
     * @date 2022/06/10 15:41
     **/
    private Mono<Void> validateForbidUri(ServerHttpRequest request, ServerHttpResponse response) {
        String uri = request.getPath().toString();
        boolean shouldForward = true;
        String forbidRequestUri = widdoGatewayProperties.getForbidRequestUri();
        String[] forbidRequestUris = StringUtils.splitByWholeSeparatorPreserveAllTokens(forbidRequestUri, ",");
        if (forbidRequestUris != null && ArrayUtils.isNotEmpty(forbidRequestUris)) {
            for (String u : forbidRequestUris) {
                if (pathMatcher.match(u, uri)) {
                    shouldForward = false;
                }
            }
        }
        if (!shouldForward) {
            MyResponse myResponse = new MyResponse().message("该URI不允许外部访问");
            return makeResponse(response, myResponse);
        }
        return null;
    }

    /**
     * 封装返回结果.
     *
     * @param response
     * @param myResponse
     * @return reactor.core.publisher.Mono<java.lang.Void>
     * @throws
     * @author XYL
     * @className cn.widdo.filter.GatewayRequestFilter
     * @date 2022/06/10 15:41
     **/
    private Mono<Void> makeResponse(ServerHttpResponse response, MyResponse myResponse) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONObject.toJSONString(myResponse).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }

    /**
     * 打印日志.
     *
     * @param exchange
     * @author XYL
     * @className cn.widdo.filter.GatewayRequestFilter
     * @date 2022/06/10 15:42
     **/
    private void printLog(ServerWebExchange exchange) {
        URI url = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR);
        Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
        LinkedHashSet<URI> uris = exchange.getAttribute(GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        URI originUri = null;
        if (uris != null) {
            originUri = uris.stream().findFirst().orElse(null);
        }
        if (url != null && route != null && originUri != null) {
            log.info("转发请求：{}://{}{} --> 目标服务：{}，目标地址：{}://{}{}，转发时间：{}",
                    originUri.getScheme(), originUri.getAuthority(), originUri.getPath(),
                    route.getId(), url.getScheme(), url.getAuthority(), url.getPath(), LocalDateTime.now()
            );
        }
    }
}
