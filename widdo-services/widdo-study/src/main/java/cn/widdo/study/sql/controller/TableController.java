package cn.widdo.study.sql.controller;

import cn.widdo.assistant.base.BaseController;
import cn.widdo.assistant.function.Either;
import cn.widdo.assistant.result.WiddoResult;
import cn.widdo.study.sql.service.TableService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * TableController.
 *
 * @author XYL
 * @date 2023/08/03 10:21
 * @since 305.2.2.0
 */
@RestController
@RequestMapping(value = "/table")
@AllArgsConstructor
public class TableController extends BaseController {

	/**
	 * tableService.
	 */
	private final TableService tableService;

	/**
	 * 解析sql.
	 * @param params params
	 * @return cn.widdo.assistant.result.WiddoResult
	 * @author XYL
	 * @date 2023/08/03 10:47:57
	 */
	@PostMapping(value = "/parse")
	public WiddoResult parser(@RequestBody Map<String, Object> params) throws Exception {
		return WiddoResult.response(Either.liftWithValue(tableService::parser, "sql").apply(params));
	}

}
