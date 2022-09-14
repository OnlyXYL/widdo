package cn.widdo.kernel.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 * @title: ServerProtectInterceptor
 * @projectName semanticube-cloud
 * @description: TODO
 * @date 2021/2/21 002117:58
 */
public class ServerProtectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // 从请求头中获取 Zuul Token
/*        String token = request.getHeader(MyConstant.ZUUL_TOKEN_HEADER);
        String zuulToken = new String(Base64Utils.encode(MyConstant.ZUUL_TOKEN_VALUE.getBytes()));
        // 校验 Zuul Token的正确性
        if (zuulToken.equals(token)) {
            return true;
        } else {
            MyResponse myResponse = new MyResponse();
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(JSONObject.toJSONString(myResponse.message("请通过网关获取资源")));
            return false;
        }*/

        return true;
    }
}