package cn.widdo.study.either.controller;

import cn.widdo.assistant.base.BaseController;
import cn.widdo.assistant.function.Either;
import cn.widdo.assistant.result.WiddoResult;
import cn.widdo.study.either.service.EitherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * EitherController.
 *
 * @author XYL
 * @date 2023/07/31 9:54
 * @since 305.2.2.0
 */
@RestController
@RequestMapping(value = "/either")
@AllArgsConstructor
public class EitherController extends BaseController {

	/**
	 * ` eitherService.
	 */
	private final EitherService eitherService;

	/**
	 * test.
	 * @param params
	 * @return cn.widdo.assistant.result.WiddoResult
	 * @author XYL
	 * @date 2023/07/31 10:04:23
	 */
	@PostMapping(value = "/test")
	public WiddoResult testEither(@RequestBody Map<String, Object> params) throws Exception {
		return WiddoResult.response(Either.liftWithValue(eitherService::testEither, "input").apply(params));
	}

}
