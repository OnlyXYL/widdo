package cn.widdo.hadoop.service;

import cn.widdo.assistant.result.WiddoResult;

import java.util.Map;

/**
 * HdfsService.
 *
 * @author XYL
 * @date 2023/07/24 19:18
 * @since 305.2.2.0
 */
public interface HdfsService {

	/**
	 * 创建文件夹.
	 * @param params params
	 * @return cn.widdo.assistant.result.WiddoResult
	 * @author XYL
	 * @date 2023/09/08 00:36:28
	 */
	WiddoResult mkdirs(Map<String, Object> params);

	/**
	 * 文件上传.
	 * @param params 参数
	 * @return cn.widdo.assistant.result.WiddoResult
	 * @author XYL
	 * @date 2023/07/24 19:21:00
	 */
	WiddoResult put(Map<String, Object> params);

	/**
	 * 文件下载.
	 * @param params 参数
	 * @return cn.widdo.assistant.result.WiddoResult
	 * @author XYL
	 * @date 2023/09/08 18:59:22
	 */
	WiddoResult get(Map<String, Object> params);

}
