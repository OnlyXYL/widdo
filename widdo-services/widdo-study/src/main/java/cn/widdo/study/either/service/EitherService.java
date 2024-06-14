package cn.widdo.study.either.service;

import cn.widdo.assistant.result.WiddoResult;

import java.util.Map;

/**
 * EitherService.
 *
 * @author XYL
 * @date 2023/07/31 9:59
 * @since 305.2.2.0
 */
public interface EitherService {

	/**
	 * 测试Either.
	 * @param params
	 * @return cn.widdo.assistant.result.WiddoResult
	 * @throws Exception exception
	 * @author XYL
	 * @date 2023/07/31 10:00:27
	 */
	WiddoResult testEither(Map<String, Object> params) throws Exception;

}
