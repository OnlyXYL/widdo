package cn.widdo.assistant.entity.result;

import java.util.HashMap;

/**
 * widdo相应结果.
 *
 * @author XYL
 * @since 263.1.1.0
 * @since 2021/4/20 0020 17:28
 */
public class MyResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    /**
     * 设置message.
     *
     * @param message
     * @return cn.widdo.assistant.entity.result.MyResponse
     * @author XYL
     * @date 2022/11/18 14:13
     **/
    public final MyResponse message(final String message) {
        this.put("message", message);
        return this;
    }

    /**
     * 设置数据.
     *
     * @param data
     * @return cn.widdo.assistant.entity.result.MyResponse
     * @author XYL
     * @date 2022/11/18 14:13
     **/
    public final MyResponse data(final Object data) {
        this.put("data", data);
        return this;
    }

    @Override
    public MyResponse put(final String key, final Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * 获取message.
     *
     * @return java.lang.String
     * @author XYL
     * @date 2022/11/18 13:51
     **/
    public final String getMessage() {
        return String.valueOf(get("message"));
    }

    /**
     * 获取数据.
     *
     * @return java.lang.Object
     * @author XYL
     * @date 2022/11/18 13:51
     **/
    public Object getData() {
        return get("data");
    }
}
