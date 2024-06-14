package cn.widdo.autoconfigure.hadoop.reader.hive;

import cn.widdo.autoconfigure.hadoop.reader.HiveReader;

/**
 * AbstractHiveReaderDecorator.
 *
 * @param <R> r
 * @param <T> t
 * @author XYL
 * @date 2023/09/12 11:29
 * @since 305.2.2.0
 */
public abstract class AbstractHiveReaderDecorator<T, R> extends AbstractHiveReader<T, R> {

	/**
	 * hiveReader.
	 */
	protected HiveReader<T, R> hiveReader;

}
