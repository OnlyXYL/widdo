package cn.widdo.study.orientdb.controller;

import cn.widdo.assistant.base.BaseController;
import cn.widdo.assistant.result.WiddoResult;
import cn.widdo.autoconfigure.orientdb.configure.WiddoOrientdbConfigure;
import cn.widdo.study.orientdb.service.OrientdbGremlinService;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * orientdb gremlin controller.
 *
 * @author XYL
 * @date 2022/07/15 11:39
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
@RequestMapping(value = "/orientdb/gremlin")
@RestController
@ConditionalOnBean({ WiddoOrientdbConfigure.class })
public class OrientdbGremlinController extends BaseController {

	/**
	 * orientdbGremlinService.
	 */
	@Resource
	private OrientdbGremlinService orientdbGremlinService;

	/**
	 * 查询点.
	 * <p>
	 * params.put("key") params.put("value") params.put("label")
	 * @param params params
	 * @return cn.widdo.entity.JsonResult
	 * @author XYL
	 * @date 2022/07/15 11:49
	 **/
	@PostMapping(value = "/query/v")
	public WiddoResult queryV(@RequestBody Map<String, Object> params) {
		return this.validateAndRun(params, p -> orientdbGremlinService.queryV(p), "key", "value", "label");
	}

}
