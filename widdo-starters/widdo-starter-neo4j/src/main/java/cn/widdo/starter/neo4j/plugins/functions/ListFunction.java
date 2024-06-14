package cn.widdo.starter.neo4j.plugins.functions;

import org.apache.commons.collections.CollectionUtils;
import org.neo4j.procedure.Description;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.UserFunction;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 集合交集.
 *
 * @author XYL
 * @date 2023/03/24 15:16
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
public class ListFunction {

	/**
	 * check if the given lists intersection.
	 * @param list1 list1
	 * @param list2 list2
	 * @return boolean
	 * @author XYL
	 * @date 2023/03/24 15:31:02
	 */
	@UserFunction(name = "widdo.list.intersects")
	@Description("widdo.list.intersects(list1,list2) - check if the given lists intersection.")
	public boolean intersects(@Name("list1") List<Object> list1, @Name("list2") List<Object> list2) {

		if (CollectionUtils.isNotEmpty(list1) && CollectionUtils.isNotEmpty(list2)) {

			final List<Object> collect = list1.stream().filter(list2::contains).collect(Collectors.toList());

			return CollectionUtils.isNotEmpty(collect);
		}

		return false;

	}

}
