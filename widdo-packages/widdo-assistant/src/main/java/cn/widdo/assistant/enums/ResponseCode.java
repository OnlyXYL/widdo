package cn.widdo.assistant.enums;

import java.util.Arrays;

/**
 * 响应状态码.
 *
 * @author XYL
 * @date 2021/4/21 0021 15:25
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
public enum ResponseCode {

    /**
     * 成功.
     */
    SUCCESS(200, "success"),

    /**
     * 组件接口成功.
     */
    SKC_SUCCESS(20000, "success"),

    /**
     * 失败.
     */
    ERROR(201, "error"),

    //================== 本体相关  10000 开始 ===================

    //================== 投影相关  20000 开始 ===================

    //================== 本体识别相关  30000 开始 ===================

    //=================== 语义网相关 40000 开始 =====================

    //=================== 远程调用相关 50000 开始 =====================

    //=================== 文档提取单词相关 60000 开始 =====================

    //=================== 文档四标相关 70000 开始 =====================

    //=================== 远程调用相关 90000 开始 =====================

    //=================== 知识源管理相关 100000 开始 =====================

    //=================== 前端组件相关 110000 开始 =====================

    //=================== 系统级别 900 开始 =====================

    /**
     * 参数异常.
     */
    PARAMS_ERROR(901, "参数异常"),

    /**
     * 新增信息异常.
     */
    INSERT_ERROR(902, "新增信息异常"),

    /**
     * 修改信息异常.
     */
    UPDATE_ERROR(903, "修改信息异常"),

    /**
     * 信息删除异常.
     */
    DELETE_ERROR(904, "信息删除异常"),

    /**
     * 信息查询异常.
     */
    FIND_ERROR(905, "信息查询异常"),

    /**
     * 文件下载异常.
     */
    DOWNLOAD_ERROR(906, "文件下载异常"),

    /**
     * 文件上传异常.
     */
    UPLOAD_ERROR(907, "文件上传异常"),

    /**
     * 文件获取异常.
     */
    NOT_FIND_FILE_ERROR(908, "文件获取异常"),

    /**
     * 不支持的操作.
     */
    WIDDO_UNSUPPORTED_OPERATION_ERROR(1, "不支持的类型");

    /**
     * code.
     */
    private int code;

    /**
     * message.
     */
    private String message;

    /**
     * 构造方法.
     *
     * @param code    cdoe
     * @param message message
     */
    ResponseCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 获取code.
     *
     * @return int
     * @author XYL
     * @date 2022/11/18 11:34
     **/
    public int getCode() {
        return code;
    }

    /**
     * 设置code.
     *
     * @param code code typed int
     * @author XYL
     * @date 2022/11/18 11:34
     **/
    public void setCode(final int code) {
        this.code = code;
    }

    /**
     * 获取message.
     *
     * @return java.lang.String
     * @author XYL
     * @date 2022/11/18 11:33
     **/
    public String getMessage() {
        return message;
    }

    /**
     * 设置message.
     *
     * @param message message typed String {@link String}
     * @author XYL
     * @date 2022/11/18 11:34
     **/
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * 根据code获取枚举对象.
     *
     * @param code code
     * @return cn.widdo.assistant.enums.ResponseCode
     * @author XYL
     * @date 2022/11/17 0:12
     **/
    public static ResponseCode getEnumByCode(final int code) {
        return Arrays.stream(values()).filter(s -> s.getCode() == code)
                .findAny()
                .orElse(ResponseCode.WIDDO_UNSUPPORTED_OPERATION_ERROR);
    }
}
