package cn.widdo.study.either.service.impl;

import cn.widdo.assistant.result.IResultInterface;
import cn.widdo.assistant.result.WiddoResult;
import cn.widdo.study.either.service.EitherService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * EitherServiceImpl.
 *
 * @author XYL
 * @date 2023/07/31 9:59
 * @since 305.2.2.0
 */
@Service
public class EitherServiceImpl implements EitherService {

	@Override
	public WiddoResult testEither(Map<String, Object> params) {
		final String input = params.get("input").toString();
		return WiddoResult.response(IResultInterface.StudyResultEnum.SUCCESS, input);
	}

}
