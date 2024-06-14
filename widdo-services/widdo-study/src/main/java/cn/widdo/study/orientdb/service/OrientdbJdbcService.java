package cn.widdo.study.orientdb.service;

import cn.widdo.assistant.result.WiddoResult;

import java.util.Map;

/**
 * Orientdb Jdbc Service.
 *
 * @author XYL
 * @date 2022/07/15 2:05
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
public interface OrientdbJdbcService {

	/**
	 * 查询线.
	 * @param params param typed Map
	 * @return cn.widdo.assistant.result.WiddoResult
	 * @author XYL
	 * @date 2022/07/15 9:53
	 **/
	WiddoResult queryE(Map<String, Object> params);

	/**
	 * 查询点.
	 * @param params param typed Map
	 * @return cn.widdo.assistant.result.WiddoResult
	 * @author XYL
	 * @date 2022/07/15 10:15
	 **/
	WiddoResult queryV(Map<String, Object> params);

	/**
	 * 创建点.
	 * @param params param typed Map
	 * @return cn.widdo.assistant.result.WiddoResult
	 * @author XYL
	 * @date 2022/07/15 10:38
	 **/
	WiddoResult createV(Map<String, Object> params);

	/**
	 * 创建关系.
	 * @param params params typed Map
	 * @return cn.widdo.assistant.result.WiddoResult
	 * @author XYL
	 * @date 2022/07/15 10:39
	 **/
	WiddoResult createE(Map<String, Object> params);

	/**
	 * 删除数据.
	 * @param
	 * @return cn.widdo.assistant.result.WiddoResult
	 * @author XYL
	 * @date 2022/07/15 10:39
	 **/
	WiddoResult delete();

}
