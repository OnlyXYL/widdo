package cn.widdo.starter.neo4j.utils;

import cn.widdo.starter.neo4j.entity.result.Result;
import cn.widdo.starter.neo4j.entity.result.ResultEnum;
import cn.widdo.starter.neo4j.entity.result.ResultPageInfo;

/**
 * 结果工具类.
 *
 * @author XYL
 * @date 2022/08/15 15:09
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
public class ResultUtil {
    /**
     * 分页大小.
     */
    public static final Integer PAGE_SIZE_MAX = 500;
    /**
     * 默认大小.
     */
    public static final Integer PAGE_SIZE_DEFAULT = 10;

    protected ResultUtil() {
        throw new UnsupportedOperationException(ResultUtil.class.getName() + "不能被实例化");
    }

    /**
     * 封装结果信息.
     *
     * @param <T> 泛型
     * @param t   t
     * @return cn.widdo.starter.neo4j.entity.result.Result<T>
     * @author XYL
     * @date 2022/11/28 14:08:40
     **/
    public static <T> Result<T> success(T t) {
        return success(t, null);
    }

    /**
     * 封装结果信息.
     *
     * @param <T>            泛型
     * @param t              t
     * @param resultPageInfo resultPageInfo
     * @return cn.widdo.starter.neo4j.entity.result.Result<T>
     * @author XYL
     * @date 2022/11/28 14:08:29
     **/
    public static <T> Result<T> success(T t, ResultPageInfo resultPageInfo) {
        Result<T> result = new Result<>();
        result.setStatus(ResultEnum.SUCCESS);
        result.setCode(200);
        result.setMsg("接口调用成功！");
        result.setData(t);
        result.setPageInfo(resultPageInfo);
        return result;
    }

    /**
     * 封装结果.
     *
     * @return cn.widdo.starter.neo4j.entity.result.Result<?>
     * @author XYL
     * @date 2022/11/28 14:10:19
     **/
    public static Result<?> success() {
        return success(null);
    }

    /**
     * 封装结果信息.
     *
     * @param status status {@link ResultEnum}
     * @param msg    msg
     * @return cn.widdo.starter.neo4j.entity.result.Result<?>
     * @author XYL
     * @date 2022/11/28 14:07:59
     **/
    public static Result<?> error(ResultEnum status, String msg) {
        Result<?> result = new Result<>();
        result.setStatus(status);
        //1000,程序默认错误
        result.setCode(1000);
        result.setMsg(msg);
        result.setData(null);
        ResultPageInfo resultPageInfo = new ResultPageInfo("0", "0", "0");
        result.setPageInfo(resultPageInfo);
        return result;
    }

    /**
     * 封装结果.
     *
     * @param status status {@link ResultEnum}
     * @param code   code
     * @param msg    msg
     * @return cn.widdo.starter.neo4j.entity.result.Result<?>
     * @author XYL
     * @date 2022/11/28 14:07:16
     **/
    public static Result<?> error(ResultEnum status, Integer code, String msg) {
        Result<?> result = new Result<>();
        result.setStatus(status);
        result.setCode(code);
        result.setMsg(msg);
        ResultPageInfo resultPageInfo = new ResultPageInfo("0", "0", "0");
        result.setPageInfo(resultPageInfo);
        return result;
    }

}
