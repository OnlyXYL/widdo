package cn.widdo.study.babelnet.service;

import cn.widdo.assistant.result.WiddoResult;

import java.util.Map;

/**
 * babelNet service.
 *
 * @author XYL
 * @date 2023/03/15 13:36
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
public interface BabelNetService {

	/**
	 * query.
	 * @param params params
	 * @return cn.widdo.assistant.result.WiddoResult
	 * @author XYL
	 * @date 2023/03/15 13:37:16
	 */
	WiddoResult query(Map<String, Object> params);

}
