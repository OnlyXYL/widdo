package cn.widdo.assistant.entity.result;

import java.util.HashMap;

/**
 * widdo相应结果
 *
 * @author XYL
 * @version 263.1.0.0
 * @since 2021/4/20 0020 17:28
 */
public class MyResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public MyResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public MyResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    @Override
    public MyResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public String getMessage() {
        return String.valueOf(get("message"));
    }

    public Object getData() {
        return get("data");
    }
}
