package cn.widdo.assistant.entity.result;

import cn.widdo.assistant.enums.ResponseCode;
import cn.widdo.assistant.exception.MyException;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * widdo返回结果
 *
 * @author XYL
 * @version 263.1.1.0
 * @since 2021/4/20 0020 17:28
 */
@Data
public class WebResult implements Serializable {

    private static final long serialVersionUID = -7167592578210460779L;

    /**
     * 状态码
     * <p>
     * {@link ResponseCode}
     */
    private int code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;


    public WebResult() {
    }

    public WebResult(int code) {
        this.code = code;
    }

    /**
     * 成功，默认返回结果
     *
     * @return cn.widdo.assistant.entity.result.JsonResult
     * @author XYL
     * @className cn.widdo.assistant.entity.result.JsonResult
     * @date 2022/09/26 18:30
     **/
    public static WebResult success() {

        WebResult kgResult = new WebResult();
        kgResult.setMessage(ResponseCode.SUCCESS.getMessage());
        kgResult.setCode(ResponseCode.SUCCESS.getCode());

        return kgResult;
    }

    /**
     * 成功，有数据
     *
     * @param data 数据
     * @return cn.widdo.assistant.entity.result.JsonResult
     * @author XYL
     * @className cn.widdo.assistant.entity.result.JsonResult
     * @date 2022/09/26 18:30
     **/
    public static WebResult success(Object data) {
        WebResult kgResult = new WebResult();
        kgResult.setMessage(ResponseCode.SUCCESS.getMessage());
        kgResult.setCode(ResponseCode.SUCCESS.getCode());
        kgResult.setData(data);

        return kgResult;
    }

    /**
     * 构建返回结果
     *
     * @param code    编码
     * @param data    数据
     * @param message 消息
     * @return cn.widdo.assistant.entity.result.JsonResult
     * @author XYL
     * @className cn.widdo.assistant.entity.result.JsonResult
     * @date 2022/09/26 18:30
     **/
    public static WebResult build(ResponseCode code, Object data, String message) {
        WebResult kgResult = new WebResult();
        kgResult.setMessage(message);
        kgResult.setCode(code.getCode());
        kgResult.setData(data);
        return kgResult;
    }

    /**
     * 构建返回结果
     *
     * @param code 编码
     * @return cn.widdo.assistant.entity.result.JsonResult
     * @author XYL
     * @className cn.widdo.assistant.entity.result.JsonResult
     * @date 2022/09/26 18:28
     **/
    public static WebResult build(ResponseCode code) {
        WebResult kgResult = new WebResult();
        kgResult.setMessage(code.getMessage());
        kgResult.setCode(code.getCode());
        kgResult.setData(null);
        return kgResult;
    }

    /**
     * 构建返回结果
     *
     * @param e    异常
     * @param code 编码
     * @return cn.widdo.assistant.entity.result.JsonResult
     * @author XYL
     * @className cn.widdo.assistant.entity.result.JsonResult
     * @date 2022/09/26 18:28
     **/
    public static WebResult build(Exception e, ResponseCode code) {

        if (e instanceof MyException) {
            final MyException myException = (MyException) e;

            if (myException.getCode() != 0) {
                code = ResponseCode.getEnumByCode(myException.getCode());
            }
        }

        WebResult kgResult = new WebResult();
        assert code != null;
        kgResult.setMessage(code.getMessage());
        kgResult.setCode(code.getCode());
        kgResult.setData(null);
        return kgResult;
    }

    public static WebResult build(ResponseCode code, String message) {
        WebResult kgResult = new WebResult();
        kgResult.setMessage(message);
        kgResult.setCode(code.getCode());
        kgResult.setData(null);
        return kgResult;
    }

    public static WebResult build(ResponseCode code, Object data) {
        WebResult kgResult = new WebResult();
        kgResult.setMessage(code.getMessage());
        kgResult.setCode(code.getCode());
        kgResult.setData(data);
        return kgResult;
    }

    /**
     * 构建返回结果 远程调用返回code码不为200，返回指定结果
     *
     * @param code         编码
     * @param responseCode 编码对象
     * @return cn.widdo.assistant.entity.result.JsonResult
     * @author XYL
     * @className cn.widdo.assistant.entity.result.JsonResult
     * @date 2022/09/26 18:31
     **/
    public static WebResult build(String code, ResponseCode responseCode) {
        if (String.valueOf(ResponseCode.SUCCESS.getCode()).equals(code)) {
            return success();
        }
        WebResult kgResult = new WebResult();
        if (!String.valueOf(ResponseCode.SUCCESS.getCode()).equals(code)) {
            kgResult.setMessage(responseCode.getMessage());
            kgResult.setCode(responseCode.getCode());
            kgResult.setData(null);
        }
        return kgResult;
    }

    public static WebResult build(String code, ResponseCode responseCode, Object data) {
        if (String.valueOf(ResponseCode.SUCCESS.getCode()).equals(code)) {
            return success(data);
        }
        WebResult kgResult = new WebResult();
        if (!String.valueOf(ResponseCode.SUCCESS.getCode()).equals(code)) {
            kgResult.setMessage(responseCode.getMessage());
            kgResult.setCode(responseCode.getCode());
            kgResult.setData(null);
        }
        return kgResult;
    }

    /**
     * 根据组件接口返回的数据构建返回数据
     *
     * @param map          前端组件接口返回的数据
     * @param responseCode 编码对象
     * @return cn.widdo.assistant.entity.result.JsonResult
     * @author XYL
     * @className cn.widdo.assistant.entity.result.JsonResult
     * @date 2022/09/26 18:31
     **/
    public static WebResult build(Map<String, Object> map, ResponseCode responseCode) {
        WebResult kgResult = new WebResult();
        int status = Integer.parseInt(String.valueOf(map.get("status")));
        String message = Optional.ofNullable(map.get("message")).map(Objects::toString).orElse("");
        Object data = map.get("data");
        if (ResponseCode.SKC_SUCCESS.getCode() == status) {
            kgResult.setCode(ResponseCode.SUCCESS.getCode());
            kgResult.setMessage(message);
            kgResult.setData(data);
        }
        if (ResponseCode.SKC_SUCCESS.getCode() != status) {
            kgResult.setMessage(responseCode.getMessage());
            kgResult.setCode(responseCode.getCode());
            kgResult.setData(null);
        }
        return kgResult;
    }
}
