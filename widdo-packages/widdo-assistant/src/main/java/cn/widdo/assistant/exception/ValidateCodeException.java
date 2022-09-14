package cn.widdo.assistant.exception;

/**
 * 验证码异常
 *
 * @author XYL
 * @version 1.0
 * @date 2022/06/29 15:59
 */
public class ValidateCodeException extends Exception{

    private static final long serialVersionUID = 7514854456967620043L;

    public ValidateCodeException(String message){
        super(message);
    }
}