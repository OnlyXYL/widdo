package cn.widdo.hadoop.controller;

import cn.widdo.assistant.base.BaseController;
import cn.widdo.assistant.function.Either;
import cn.widdo.assistant.result.WiddoResult;
import cn.widdo.autoconfigure.hadoop.annotation.WiddoHadoop;
import cn.widdo.hadoop.service.HdfsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * HdfsController.
 *
 * @author XYL
 * @date 2023/07/24 19:02
 * @since 305.2.2.0
 */
@WiddoHadoop
@RestController
@RequestMapping(value = "/hdfs")
@RequiredArgsConstructor
public class HdfsController extends BaseController {

	/**
	 * hdfsService.
	 */
	private final HdfsService hdfsService;

	/**
	 * 创建文件夹.
	 * <p>
	 * 文件目录 path: /xiyou/huaguoshan
	 * @param params
	 * @return cn.widdo.assistant.result.WiddoResult
	 * @author XYL
	 * @date 2023/09/08 00:35:20
	 */
	@PostMapping(value = "/mkdirs")
	public WiddoResult mkdirs(@RequestBody Map<String, Object> params) throws Exception {
		return WiddoResult.response(Either.liftWithValue(hdfsService::mkdirs, "path", "user").apply(params));
	}

	/**
	 * 文件上传.
	 * <p>
	 * 注意：需要指定hdfs集群中用户，否则会报没权限错误
	 * @param params
	 * @return cn.widdo.assistant.result.WiddoResult
	 * @author XYL
	 * @date 2023/07/24 19:21:52
	 * @since 305.2.2.0
	 */
	@PostMapping(value = "/put")
	public WiddoResult put(@RequestBody Map<String, Object> params) throws Exception {
		return WiddoResult.response(Either.liftWithValue(hdfsService::put, "source", "target", "user").apply(params));
	}

	/**
	 * 文件下载.
	 * @param params
	 * @return cn.widdo.assistant.result.WiddoResult
	 * @author XYL
	 * @date 2023/09/08 19:06:54
	 * @since 305.2.2.0
	 */
	@PostMapping(value = "/get")
	public WiddoResult get(@RequestBody Map<String, Object> params) throws Exception {
		return WiddoResult.response(Either.liftWithValue(hdfsService::get, "source", "target", "user").apply(params));
	}

}
