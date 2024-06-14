package cn.widdo.starter.neo4j.validator;

import cn.widdo.starter.neo4j.entity.result.Result;
import cn.widdo.starter.neo4j.entity.result.ResultEnum;
import cn.widdo.starter.neo4j.function.MapParamsFunction;
import cn.widdo.starter.neo4j.utils.Neo4jUtil;
import cn.widdo.starter.neo4j.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 参数校验器.
 *
 * @author XYL
 * @date 2022/10/18 10:19
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
public class ParamsValidator {

	private static final Logger log = LoggerFactory.getLogger(ParamsValidator.class);

	/**
	 * 校验参数.
	 * @param params 参数
	 * @param function 函数
	 * @param checkKeys 需要校验的参数
	 * @return cn.widdo.starter.neo4j.entity.result.Result
	 * @author XYL
	 * @className cn.widdo.starter.neo4j.validator.ParamsValidator
	 * @date 2022/10/18 10:26
	 **/
	protected Result validateAndRun(Map<String, Object> params, MapParamsFunction function, String... checkKeys) {
		try {
			// 校验是否为空
			Neo4jUtil.throwExceptionIfNull(params, checkKeys);

			return function.execute(params);
		}
		catch (Exception e) {
			e.printStackTrace();
			log.debug("[Widdo] |- Starter [Neo4j Starter] |- ParamsValidator Result [Error] |- Message {} |- Trace {}.",
					e.getMessage(), e.getStackTrace());
			return ResultUtil.error(ResultEnum.ERROR, e.getMessage());
		}
	}

}
