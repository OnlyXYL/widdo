package cn.widdo.starter.neo4j.utils;

import cn.widdo.starter.neo4j.entity.result.Result;
import cn.widdo.starter.neo4j.entity.result.ResultEnum;
import cn.widdo.starter.neo4j.entity.result.ResultPageInfo;

/**
 * 结果工具类
 *
 * @author XYL
 * @version 263.1.0.0
 * @date 2022/08/15 15:09
 */
public class ResultUtil {
    public static final Integer PAGE_SIZE_MAX = 500;
    public static final Integer PAGE_SIZE_DEFAULT = 10;

    public static <T> Result<T> success(T t) {
        return success(t, null);
    }

    public static <T> Result<T> success(T t, ResultPageInfo resultPageInfo) {
        Result<T> result = new Result<>();
        result.setStatus(ResultEnum.SUCCESS);
        result.setCode(200);
        result.setMsg("接口调用成功！");
        result.setData(t);
        result.setPageInfo(resultPageInfo);
        return result;
    }

    public static Result<?> success() {
        return success(null);
    }

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
