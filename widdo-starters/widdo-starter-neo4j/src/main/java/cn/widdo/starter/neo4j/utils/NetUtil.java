package cn.widdo.starter.neo4j.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

/**
 * 网络工具
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/10/18 10:07
 */
public class NetUtil {
    public static boolean ping(String ip) {
        String command = "ping " + ip + " -c 1 -W 1.5  -t 100";
        String line;
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec(command);
            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(process.getInputStream()));
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("100% packet loss") || line.contains("100.0% packet loss")) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean telnet(String ip, String port) {
        String command = "curl " + ip + ":" + port;
        String line;
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec(command);
            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(process.getInputStream()));
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("not a WebSocket")) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取客户真实ip
     *
     * @author XYL
     * @className cn.widdo.starter.neo4j.utils.NetUtil
     * @return java.lang.String
     * @date 2022/10/20 11:54
     **/
    public static String getRealIp() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return Optional.ofNullable(request.getHeader("X-Forwarded-For")).orElse(Optional.ofNullable(request.getHeader("X-Real-IP")).orElse(request.getRemoteAddr()));
    }
}
