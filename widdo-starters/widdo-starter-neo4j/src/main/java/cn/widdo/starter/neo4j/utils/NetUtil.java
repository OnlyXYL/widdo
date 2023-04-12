package cn.widdo.starter.neo4j.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

/**
 * 网络工具.
 *
 * @author XYL
 * @date 2022/10/18 10:07
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
public class NetUtil {

    protected NetUtil() {
        throw new UnsupportedOperationException(NetUtil.class.getName() + "不能被实例化");
    }

    /**
     * ping.
     *
     * @param ip ip
     * @return boolean
     * @author XYL
     * @date 2022/11/28 14:13:28
     **/
    public static boolean ping(String ip) {
        String command = "ping " + ip + " -c 1 -W 1.5  -t 100";
        String line;
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec(command);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
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

    /**
     * telnet.
     *
     * @param ip   ip
     * @param port port
     * @return boolean
     * @author XYL
     * @date 2022/11/28 14:13:45
     **/
    public static boolean telnet(String ip, String port) {
        String command = "curl " + ip + ":" + port;
        String line;
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec(command);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
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
     * 获取客户真实ip.
     *
     * @return java.lang.String
     * @author XYL
     * @className cn.widdo.starter.neo4j.utils.NetUtil
     * @date 2022/10/20 11:54
     **/
    public static String getRealIp() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        return Optional.ofNullable(request.getHeader("X-Forwarded-For"))
                .orElse(Optional.ofNullable(request.getHeader("X-Real-IP")).orElse(request.getRemoteAddr()));
    }
}
