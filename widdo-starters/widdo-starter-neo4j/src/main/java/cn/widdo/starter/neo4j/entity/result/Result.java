package cn.widdo.starter.neo4j.entity.result;

/**
 * 通用接口返回结果
 *
 * @author XYL
 * @version 263.1.0.0
 * @date 2022/08/15 15:09
 */
public class Result<T> {
    public Result(){

    }
    private ResultEnum status;
    private Integer code;
    private String msg;
    private T data;

    private ResultPageInfo resultPageInfo;

    public ResultEnum getStatus() {
        return status;
    }

    public void setStatus(ResultEnum code) {
        this.status = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultPageInfo getPageInfo() {
        return resultPageInfo;
    }

    public void setPageInfo(ResultPageInfo pageInfo) {
        this.resultPageInfo = pageInfo;
    }

    public  T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status='" + status.name() +"'"+
                ",status_code='" + code +"'"+
                ", msg='" + msg +"'"+
                "}";
    }



}
