package cn.widdo.assistant.exception;

import cn.widdo.assistant.result.IResultInterface;

/**
 * 自定义异常.
 *
 * @author XYL
 * @date 2022/06/29 15:59
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
public class BaseException extends RuntimeException {

	/**
	 * msg.
	 */
	private String msg;

	/**
	 * code.
	 */
	private int code;

	/**
	 * constructor without params.
	 */
	public BaseException() {
	}

	/**
	 * constructor has one params called message.
	 * @param message message
	 */
	public BaseException(final String message) {
		super(message);
		this.msg = message;
	}

	/**
	 * constructor has two params.one typed of {@link String},another typed
	 * {@link Throwable}.
	 * @param message message
	 * @param cause cause
	 */
	public BaseException(final String message, final Throwable cause) {
		super(message, cause);
		this.msg = message;
	}

	/**
	 * constructor has one param typed of {@link Throwable}.
	 * @param cause
	 */
	public BaseException(final Throwable cause) {
		super(cause);
	}

	/**
	 * constructor has four params.
	 * @param message message
	 * @param cause cause
	 * @param enableSuppression enableSuppression
	 * @param writableStackTrace writableStackTrace
	 */
	public BaseException(final String message, final Throwable cause, final boolean enableSuppression,
			final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * constructor has one param called {@link IResultInterface}.
	 * @param iResultInterface the result type of interface called
	 * {@link IResultInterface}
	 */
	public BaseException(final IResultInterface iResultInterface) {
		super(iResultInterface.getMsg());
		this.msg = iResultInterface.getMsg();
		this.code = iResultInterface.getCode();
	}

	/**
	 * return msg typed of {@link String}.
	 * @return msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * set msg typed of {@link String}.
	 * @param msg msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * return code typed of {@link Integer}.
	 * @return a code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * set code.
	 * @param code code
	 */
	public void setCode(int code) {
		this.code = code;
	}

}
