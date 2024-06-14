package cn.widdo.autoconfigure.hadoop.writer.hive;

import cn.widdo.autoconfigure.hadoop.writer.HiveWriter;

/**
 * AbstractHiveWriterDecorator.
 *
 * @param <R> r
 * @param <T> t
 * @author XYL
 * @date 2023/09/12 11:50
 * @since 305.2.2.0
 */
public abstract class AbstractHiveWriterDecorator<T, R> extends AbstractHiveWriter<T, R> {

	/**
	 * hiveWriter.
	 */
	protected HiveWriter<T, R> hiveWriter;

}
