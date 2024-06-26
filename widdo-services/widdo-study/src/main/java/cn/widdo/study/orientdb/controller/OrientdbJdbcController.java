package cn.widdo.study.orientdb.controller;

import cn.widdo.assistant.base.BaseController;
import cn.widdo.assistant.result.WiddoResult;
import cn.widdo.autoconfigure.orientdb.configure.WiddoOrientdbConfigure;
import cn.widdo.study.orientdb.service.OrientdbJdbcService;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * orientdb controller.
 *
 * @author XYL
 * @date 2022/07/14 18:01
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
@RequestMapping(value = "/orientdb/jdbc")
@RestController
@ConditionalOnBean({ WiddoOrientdbConfigure.class })
public class OrientdbJdbcController extends BaseController {

	/**
	 * orientdbJdbcService.
	 */
	@Resource
	private OrientdbJdbcService orientdbJdbcService;

	/**
	 * 创建点.
	 * <p>
	 * params.put("name") params.put("age") params.put("city")
	 * @param params params
	 * @return java.util.Map<java.lang.String, java.lang.Object>
	 * @author XYL
	 * @date 2022/07/15 11:43
	 **/
	@PostMapping(value = "/create/v")
	public WiddoResult createV(@RequestBody Map<String, Object> params) {
		return this.validateAndRun(params, p -> orientdbJdbcService.createV(p), "name", "age", "city");
	}

	/**
	 * 创建关系.
	 * <p>
	 * params.put("label") params.put("source") params.put("target")
	 * @param params params typed map {@link Map}
	 * @return cn.widdo.entity.JsonResult
	 * @author XYL
	 * @date 2022/07/15 11:46
	 **/
	@PostMapping(value = "/create/e")
	public WiddoResult createE(@RequestBody Map<String, Object> params) {
		return this.validateAndRun(params, p -> orientdbJdbcService.createE(p), "label", "source", "target");
	}

}
