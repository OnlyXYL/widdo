package cn.widdo.autoconfigure.hadoop.reader.hdfs;

import cn.widdo.autoconfigure.hadoop.reader.HdfsReader;
import org.apache.hadoop.conf.Configuration;

/**
 * AbstractHdfsReaderDecorator.
 *
 * @param <T> t
 * @param <R> r
 * @author XYL
 * @date 2023/09/12 11:23
 * @since 305.2.2.0
 */
public abstract class AbstractHdfsReaderDecorator<T, R> extends AbstractHdfsReader<T, R> {

	/**
	 * hdfsReader.
	 */
	protected HdfsReader<T, R> hdfsReader;

	/**
	 * 无参构造.
	 */
	protected AbstractHdfsReaderDecorator() {
	}

	/**
	 * 构造方法.
	 * @param uri hdfs访问地址
	 * @param user hadoop 用户
	 */
	protected AbstractHdfsReaderDecorator(final String uri, final String user) {
		super(uri, user);
	}

	/**
	 * 构造方法.
	 * @param uri hdfs访问地址
	 * @param user hadoop 用户
	 * @param conf 配置
	 */
	protected AbstractHdfsReaderDecorator(final String uri, final String user, final Configuration conf) {
		super(uri, user, conf);
	}

}
