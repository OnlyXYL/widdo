package cn.widdo.assistant.result;

import cn.widdo.assistant.exception.BaseException;
import cn.widdo.assistant.function.Either;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;

import java.util.HashMap;
import java.util.Optional;

/**
 * widdo result wrapper.
 *
 * @author XYL
 * @date 2022/12/29 17:47
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
public class WiddoResult extends HashMap<String, Object> {

	private static final Logger LOG = LoggerFactory.getLogger(WiddoResult.class);

	/**
	 * the key of result called code.
	 */
	public static final String CODE = "code";

	/**
	 * the key of result called msg.
	 */
	public static final String MSG = "msg";

	/**
	 * the key of result called data.
	 */
	public static final String DATA = "data";

	/**
	 * constructor has one param called msg.
	 * @param msg msg
	 */
	public WiddoResult(final String msg) {
		this.put(MSG, msg);
	}

	/**
	 * constructor has two params,one called code,another called msg.
	 * @param code code
	 * @param msg msg
	 */
	public WiddoResult(final int code, final String msg) {
		this.put(CODE, code);
		this.put(MSG, msg);
	}

	/**
	 * constructor has three params, one called code, one called msg,another called data.
	 * @param code code
	 * @param msg msg
	 * @param data data
	 */
	public WiddoResult(final int code, final String msg, final Object data) {
		this.put(CODE, code);
		this.put(MSG, msg);
		this.put(DATA, data);
	}

	/**
	 * the method which have one param called {@link IResultInterface} to build
	 * WiddoResult.
	 * @param iResultInterface iResultInterface
	 * @return an instance type of widdoResult
	 */
	public static WiddoResult response(IResultInterface iResultInterface) {
		return new WiddoResult(iResultInterface.getCode(), iResultInterface.getMsg());
	}

	/**
	 * the method which has two params,one called {@link IResultInterface},another called
	 * data.
	 * @param iResultInterface iResultInterface
	 * @param data data
	 * @return an instance type of widdoResult
	 */
	public static WiddoResult response(IResultInterface iResultInterface, Object data) {
		return new WiddoResult(iResultInterface.getCode(), iResultInterface.getMsg(), data);
	}

	/**
	 * the method which has one param, called {@link BaseException}.
	 * @param baseException baseException
	 * @return an instance type of widdoResult
	 */
	public static WiddoResult response(BaseException baseException) {
		return new WiddoResult(baseException.getCode(), baseException.getMsg());
	}

	/**
	 * 返回结果.
	 * @param either either
	 * @param <T> t
	 * @return T type of WiddoResult
	 * @author XYL
	 * @date 2023/08/02 13:54:22
	 */
	public static <T extends WiddoResult> T response(Either either) throws Exception {
		final Optional<Either> eitherOptional = Optional.of(either);
		if (eitherOptional.isPresent()) {
			Either entity = eitherOptional.get();
			if (entity.isLeft()) {
				Optional<Pair> optional = entity.mapLeft(x -> x);
				Object second = optional.get().getSecond();
				LOG.info("请求参数：{}", JSON.toJSONString(second));
				Exception first = (Exception) optional.get().getFirst();
				LOG.error("调用接口异常：" + first.getMessage(), first);
				throw new Exception(first.getMessage());
			}
			else {
				final Optional<T> optional = entity.mapRight(x -> x);
				return (T) optional.get();
			}
		}
		return null;
	}

}
