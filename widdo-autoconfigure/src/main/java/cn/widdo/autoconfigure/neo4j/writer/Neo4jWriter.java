package cn.widdo.autoconfigure.neo4j.writer;

import cn.widdo.autoconfigure.neo4j.Neo4jHelper;

/**
 * neo4j writer.
 *
 * @param <R>
 * @param <T>
 * @author XYL
 * @date 2022/10/14 16:36
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
public interface Neo4jWriter<T, R> extends Writer, Neo4jHelper, EnhanceWriter {

	/**
	 * 写方法.
	 * @param params 参数
	 * @return R
	 * @author XYL
	 * @className cn.widdo.autoconfigure.neo4j.write.Neo4jWriter
	 * @date 2022/10/14 16:37
	 **/
	R write(T params);

}
