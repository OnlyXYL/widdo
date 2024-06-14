package cn.widdo.autoconfigure.hadoop.writer.hdfs;

import cn.widdo.autoconfigure.hadoop.writer.HdfsWriter;

/**
 * AbstractHdfsWriterDecorator.
 *
 * @param <T> t
 * @param <R> r
 * @author XYL
 * @date 2023/09/12 11:48
 * @since 305.2.2.0
 */
public abstract class AbstractHdfsWriterDecorator<T, R> extends AbstractHdfsWriter<T, R> {

	/**
	 * hdfsWriter.
	 */
	protected HdfsWriter<T, R> hdfsWriter;

}
