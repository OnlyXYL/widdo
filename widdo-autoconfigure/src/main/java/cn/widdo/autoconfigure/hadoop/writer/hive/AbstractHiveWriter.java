package cn.widdo.autoconfigure.hadoop.writer.hive;

import cn.widdo.autoconfigure.hadoop.writer.HiveWriter;
import org.apache.hadoop.fs.FileSystem;

/**
 * AbstractHiveWriter.
 *
 * @param <R> r
 * @param <T> t
 * @author XYL
 * @date 2023/09/12 11:50
 * @since 305.2.2.0
 */
public abstract class AbstractHiveWriter<T, R> implements HiveWriter<T, R> {

	/**
	 * 客户端.
	 */
	protected FileSystem fs;

}
