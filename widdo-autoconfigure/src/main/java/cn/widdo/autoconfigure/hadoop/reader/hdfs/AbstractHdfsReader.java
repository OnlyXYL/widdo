package cn.widdo.autoconfigure.hadoop.reader.hdfs;

import cn.widdo.autoconfigure.hadoop.reader.HdfsReader;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.net.URI;

/**
 * AbstractHdfsReader.
 *
 * @param <T> t
 * @param <R> r
 * @author XYL
 * @date 2023/09/12 11:23
 * @since 305.2.2.0
 */
public abstract class AbstractHdfsReader<T, R> implements HdfsReader<T, R> {

	/**
	 * uri.
	 */
	private String uri;

	/**
	 * hadoop 用户.
	 */
	protected String user;

	/**
	 * conf.
	 */
	protected Configuration conf;

	/**
	 * 无参构造.
	 */
	public AbstractHdfsReader() {
	}

	/**
	 * 构造方法.
	 * @param uri hdfs访问地址
	 * @param user hadoop 用户
	 */
	public AbstractHdfsReader(final String uri, final String user) {
		this.uri = uri;
		this.user = user;
	}

	/**
	 * 构造方法.
	 * @param uri hdfs访问地址
	 * @param user hadoop 用户
	 * @param conf 配置
	 */
	protected AbstractHdfsReader(final String uri, final String user, final Configuration conf) {
		this.uri = uri;
		this.user = user;
		this.conf = conf;
	}

	/**
	 * 客户端.
	 * @param
	 * @return org.apache.hadoop.fs.FileSystem
	 * @author XYL
	 * @date 2023/09/15 11:11:34
	 */
	protected FileSystem fs() throws Exception {
		if (StringUtils.isNoneBlank(user)) {
			return FileSystem.get(new URI(uri), conf, user);
		}
		return FileSystem.get(new URI(uri), conf);
	}

	/**
	 * 配置.
	 * @param conf 配置
	 * @author XYL
	 * @date 2023/09/15 11:13:32
	 */
	protected void setConf(Configuration conf) {
		this.conf = conf;
	}

	/**
	 * 关闭资源.
	 */
	protected void close() throws Exception {

		final FileSystem fs = fs();

		if (fs != null) {
			fs.close();
		}
	}

}
