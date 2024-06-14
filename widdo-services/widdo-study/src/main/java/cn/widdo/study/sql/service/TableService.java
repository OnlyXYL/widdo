package cn.widdo.study.sql.service;

import cn.widdo.assistant.result.WiddoResult;

import java.util.Map;

/**
 * TableService.
 *
 * @author XYL
 * @date 2023/08/03 10:22
 * @since 305.2.2.0
 */
public interface TableService {

	/**
	 * 解析sql.
	 * @param params
	 * @return void
	 * @author XYL
	 * @date 2023/08/03 10:23:53
	 */
	WiddoResult parser(Map<String, Object> params);

}
