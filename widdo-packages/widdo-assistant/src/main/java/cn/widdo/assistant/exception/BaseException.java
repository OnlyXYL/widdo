package cn.widdo.assistant.exception;

import cn.widdo.assistant.result.IResultInterface;
import com.alibaba.fastjson.JSON;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义异常.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/06/29 15:59
 */
public class BaseException extends RuntimeException {

    /**
     * msg.
     */
    private String msg;

    /**
     * code.
     */
    private int code;

    /**
     * constructor has two params,one called code,another called msg.
     *
     * @param code code
     * @param msg  msg
     * @author XYL
     * @className cn.widdo.assistant.exception.BaseException
     * @date 2022/11/17 0:18
     **/
    public BaseException(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * constructor has one param called {@link IResultInterface}.
     *
     * @param iResultInterface the result type of interface called {@link IResultInterface}
     */
    public BaseException(final IResultInterface iResultInterface) {
        this(iResultInterface.getCode(), iResultInterface.getMsg());
    }

    /**
     * constructor has one param called {@link BindingResult}.
     *
     * @param bindingResult bindingResult
     */
    public BaseException(final BindingResult bindingResult) {
        List<String> jsonList = new ArrayList<>();
        bindingResult.getFieldErrors()
                .forEach(fieldError -> jsonList.add(fieldError.getDefaultMessage()));
        String s = JSON.toJSONString(jsonList);
        this.setMsg(s);
    }

    /**
     * get msg.
     *
     * @return a msg type of String
     */
    public String getMsg() {
        return msg;
    }

    /**
     * set msg.
     *
     * @param msg msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * get code.
     *
     * @return a code type of Integer
     */
    public int getCode() {
        return code;
    }

    /**
     * set code.
     *
     * @param code code
     */
    public void setCode(int code) {
        this.code = code;
    }
}
