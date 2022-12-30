package cn.widdo.assistant.result;

import java.util.HashMap;

/**
 * widdo result wrapper.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/12/29 17:47
 */
public class WiddoResult extends HashMap<String, Object> {

    /**
     * the key of result called code.
     */
    public static final String CODE = "code";

    /**
     * the key of result called msg.
     */
    public static final String MSG = "msg";

    /**
     * the key of result called  data.
     */
    public static final String DATA = "data";

    /**
     * constructor has two params,one called code,another called msg.
     *
     * @param code code
     * @param msg  msg
     */
    public WiddoResult(final int code, final String msg) {
        super.put(CODE, code);
        super.put(MSG, msg);
    }

    /**
     * constructor has three params, one called code, one called msg,another called data.
     *
     * @param code code
     * @param msg  msg
     * @param data data
     */
    public WiddoResult(final int code, final String msg, final Object data) {
        super.put(CODE, code);
        super.put(MSG, msg);
        super.put(DATA, data);
    }

    /**
     * the method which have one param called {@link IResultInterface} to build WiddoResult.
     *
     * @param iResultInterface iResultInterface
     * @return an instance type of widdoResult
     */
    public static WiddoResult response(IResultInterface iResultInterface) {
        return new WiddoResult(iResultInterface.getCode(), iResultInterface.getMsg());
    }

    /**
     * the method which has two param,one called {@link IResultInterface},another called data.
     *
     * @param iResultInterface iResultInterface
     * @param data             data
     * @return  an instance type of widdoResult
     */
    public static WiddoResult response(IResultInterface iResultInterface, Object data) {
        return new WiddoResult(iResultInterface.getCode(), iResultInterface.getMsg(), data);
    }
}
