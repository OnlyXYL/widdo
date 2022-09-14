package cn.widdo.assistant.entity.result;

import cn.widdo.assistant.enums.ResponseCode;
import cn.widdo.assistant.exception.MyException;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * SmartKG返回结果
 *
 * @author XYL
 * @version 1.0
 * @since 2021/4/20 0020 17:28
 */
@Data
public class JsonResult implements Serializable {

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


    public JsonResult() {
    }

    public JsonResult(int code) {
        this.code = code;
    }

    /**
     * 成功，默认返回结果
     *
     * @param
     * @return cn.com.bmsmart.entity.KgResult
     * @author XYL
     * @since 17:50 2021/4/20 0020
     **/
    public static JsonResult success() {

        JsonResult kgResult = new JsonResult();
        kgResult.setMessage(ResponseCode.SUCCESS.getMessage());
        kgResult.setCode(ResponseCode.SUCCESS.getCode());

        return kgResult;
    }

    /**
     * 成功，有数据
     *
     * @param data
     * @return cn.com.bmsmart.entity.KgResult
     * @author XYL
     * @since 18:16 2021/4/20 0020
     **/
    public static JsonResult success(Object data) {

        JsonResult kgResult = new JsonResult();
        kgResult.setMessage(ResponseCode.SUCCESS.getMessage());
        kgResult.setCode(ResponseCode.SUCCESS.getCode());
        kgResult.setData(data);

        return kgResult;
    }

    /**
     * 构建返回结果
     *
     * @param code
     * @param data
     * @return cn.com.bmsmart.entity.KgResult
     * @author XYL
     * @since 18:15 2021/4/20 0020
     **/
    public static JsonResult build(ResponseCode code, Object data, String message) {
        JsonResult kgResult = new JsonResult();
        kgResult.setMessage(message);
        kgResult.setCode(code.getCode());
        kgResult.setData(data);
        return kgResult;
    }

    public static JsonResult build(ResponseCode code) {
        JsonResult kgResult = new JsonResult();
        kgResult.setMessage(code.getMessage());
        kgResult.setCode(code.getCode());
        kgResult.setData(null);
        return kgResult;
    }

    public static JsonResult build(Exception e, ResponseCode code) {

        if (e instanceof MyException) {
            final MyException myException = (MyException) e;

            if (myException.getCode() != 0) {
                code = ResponseCode.getEnumByCode(myException.getCode());
            }
        }

        JsonResult kgResult = new JsonResult();
        kgResult.setMessage(code.getMessage());
        kgResult.setCode(code.getCode());
        kgResult.setData(null);
        return kgResult;
    }

    public static JsonResult build(ResponseCode code, String message) {
        JsonResult kgResult = new JsonResult();
        kgResult.setMessage(message);
        kgResult.setCode(code.getCode());
        kgResult.setData(null);
        return kgResult;
    }

    public static JsonResult build(ResponseCode code, Object data) {
        JsonResult kgResult = new JsonResult();
        kgResult.setMessage(code.getMessage());
        kgResult.setCode(code.getCode());
        kgResult.setData(data);
        return kgResult;
    }


    /**
     * 构建返回结果 远程调用返回code码不为200，返回指定结果
     *
     * @param code
     * @param responseCode
     * @return cn.com.bmsmart.entity.KgResult
     * @author ymy
     * @since 2021/12/31 16:10
     **/
    public static JsonResult build(String code, ResponseCode responseCode) {
        if (String.valueOf(ResponseCode.SUCCESS.getCode()).equals(code)) {
            return success();
        }
        JsonResult kgResult = new JsonResult();
        if (!String.valueOf(ResponseCode.SUCCESS.getCode()).equals(code)) {
            kgResult.setMessage(responseCode.getMessage());
            kgResult.setCode(responseCode.getCode());
            kgResult.setData(null);
        }
        return kgResult;
    }

    public static JsonResult build(String code, ResponseCode responseCode, Object data) {
        if (String.valueOf(ResponseCode.SUCCESS.getCode()).equals(code)) {
            return success(data);
        }
        JsonResult kgResult = new JsonResult();
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
     * @param map  前端组件接口返回的数据
     * @param responseCode
     * @return cn.com.bmsmart.entity.KgResult
     * @author ymy
     * @since 2022/4/6 17:54
     **/
    public static JsonResult build(Map<String, Object> map, ResponseCode responseCode) {
        JsonResult kgResult = new JsonResult();
        Integer status = Integer.valueOf(String.valueOf(map.get("status")));
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
