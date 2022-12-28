package cn.widdo.starter.neo4j.entity.result;

/**
 * 通用接口返回结果.
 *
 * @param <T> 泛型
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/08/15 15:09
 */
public class Result<T> {

    /**
     * status.
     */
    private ResultEnum status;

    /**
     * code.
     */
    private Integer code;

    /**
     * msg.
     */
    private String msg;

    /**
     * data.
     */
    private T data;

    /**
     * resultPageInfo.
     */
    private ResultPageInfo resultPageInfo;

    /**
     * 无参构造.
     */
    public Result() {

    }

    /**
     * get status.
     *
     * @return a Object type of {@link ResultEnum}
     */
    public ResultEnum getStatus() {
        return status;
    }

    /**
     * set status.
     *
     * @param code  code
     */
    public void setStatus(ResultEnum code) {
        this.status = code;
    }

    /**
     * get msg.
     *
     * @return a String msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * set msg.
     *
     * @param msg   msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * set code.
     *
     * @param code  code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * getCode.
     *
     * @return a Integer code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * set data type of anything.
     *
     * @param data  data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * get pageInfo.
     *
     * @return a Object type of {@link ResultPageInfo}
     */
    public ResultPageInfo getPageInfo() {
        return resultPageInfo;
    }

    /**
     * set pageInfo.
     *
     * @param pageInfo  pageInfo
     */
    public void setPageInfo(ResultPageInfo pageInfo) {
        this.resultPageInfo = pageInfo;
    }

    /**
     * get Data.
     *
     * @return a result type of anything
     */
    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return String.format("Result{status='%s',status_code='%d', msg='%s'}", status.name(), code, msg);
    }


}
