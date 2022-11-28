package cn.widdo.assistant.exception;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Data
@EqualsAndHashCode(callSuper = true)
public class MyException extends Exception {

    /**
     * message.
     */
    private String message;

    /**
     * code.
     */
    private int code;

    /**
     * constructor.
     *
     * @param message   提示信息
     */
    public MyException(final String message) {
        super(message);
        this.message = message;
    }

    /**
     * constructor.
     *
     * @param code code {@link cn.widdo.assistant.enums.ResponseCode}
     */
    public MyException(final int code) {
        this.code = code;
    }

    /**
     * 构造方法.
     *
     * @param code    code
     * @param message message
     * @author XYL
     * @className cn.widdo.assistant.exception.MyException
     * @date 2022/11/17 0:18
     **/
    public MyException(final int code, final String message) {
        super(message);
        this.code = code;
    }

    /**
     * 自定义异常.
     *
     * @param bindingResult
     * @return
     * @throws
     * @author XYL
     * @className cn.widdo.assistant.exception.MyException
     * @date 2022/11/17 0:17
     **/
    public MyException(final BindingResult bindingResult) {
        List<String> jsonList = new ArrayList<>();
        bindingResult.getFieldErrors()
                .forEach(fieldError -> jsonList.add(fieldError.getDefaultMessage()));
        String s = JSON.toJSONString(jsonList);
        this.setMessage(s);

    }
}
