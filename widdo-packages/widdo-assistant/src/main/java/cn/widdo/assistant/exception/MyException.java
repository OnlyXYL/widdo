package cn.widdo.assistant.exception;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义异常
 *
 * @author XYL
 * @version 1.0
 * @date 2022/06/29 15:59
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class MyException extends Exception {

    private String message;

    private int code;

    public MyException(String message) {
        super(message);
        this.message = message;
    }

    public MyException(int code) {
        this.code = code;
    }

    public MyException(int code, String message) {
        super(message);
        this.code = code;
    }

    public MyException(BindingResult bindingResult) {
        List<String> jsonList = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(fieldError -> jsonList.add(fieldError.getDefaultMessage()));

        String s = JSON.toJSONString(jsonList);
        this.setMessage(s);

    }
}
