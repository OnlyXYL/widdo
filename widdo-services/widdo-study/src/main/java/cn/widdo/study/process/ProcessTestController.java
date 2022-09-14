package cn.widdo.study.process;

import cn.com.bmsmart.client.entity.SemanticProcess;
import cn.com.bmsmart.client.utils.RestTemplateUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 加工测试controller
 *
 * @author XYL
 * @version 1.2
 * @date 2022/09/07 17:02
 */
@RequestMapping(value = "/process")
@RestController
public class ProcessTestController {

    /**
     * 加工方法
     *
     * @param
     * @return cn.widdo.assistant.entity.result.JsonResult
     * @throws
     * @author XYL
     * @className cn.widdo.study.process.ProcessTestController
     * @date 2022/09/07 17:06
     **/
    @GetMapping(value = "/run")
    public Map process() {

        Map map = null;

        try {
            final SemanticProcess.Host host = SemanticProcess.Host.builder()
                    .uri("localhost:8301")
                    .build();

            String jsonStr = "{\"inputParams\": [\n" +
                    "    {\n" +
                    "      \"key\": \"text\",\n" +
                    "      \"value\": \"静态成员类是最简单的一种嵌套类。最好把他看做是普通类，只是碰巧被声明在另一个类的内部而已，它可以访问外围类的所有成员，包括哪些被声明为私有的成员。静态成员类是外围类的一个静态成员，与其他的静态成员一样，也遵守同样的可访问规则。如果他被声明为私有的，他就只能在外围类的内部才可以被访问，等等。\",\n" +
                    "      \"children\": []\n" +
                    "    }\n" +
                    "  ]}";

            final Map params = jsonToMap(jsonStr);

            final SemanticProcess process = SemanticProcess.builder()
                    .host(host)
                    .appId("APP51")
                    .params(params)
                    .build();

            map = process.run();

            System.out.println("默认加工方法：" + map.toString());

            functionRun(process);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     * json转map
     *
     * @param jsonString
     * @return java.util.Map
     * @throws
     * @author XYL
     * @className cn.widdo.study.process.ProcessTestController
     * @date 2022/09/08 11:22
     **/
    public Map jsonToMap(String jsonString) {
        final Map map = JSONUtil.toBean(jsonString, Map.class);
        return map;
    }


    /**
     * 自定义方法调用
     *
     * @param process
     * @return void
     * @throws
     * @author XYL
     * @className cn.com.bmsmart.client.test.ClientTest
     * @date 2022/09/07 16:14
     **/
    public static void functionRun(SemanticProcess process) throws Exception {

        final Map<String, Object> params = process.getParams();

        final String url = process.getUrl();

        final Map result = process.functionRun(url, params, (u, p) -> {
            try {
                return RestTemplateUtil.requestMethod(p, u);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });

        System.out.println("自定义调用方法：" + result.toString());
    }
}
