package cn.widdo.autoconfigure.hadoop.writer.hdfs;

import cn.widdo.autoconfigure.hadoop.writer.HdfsWriter;
import org.apache.hadoop.fs.FileSystem;

/**
 * AbstractHdfsWriter.
 *
 * @param <T> t
 * @param <R> r
 * @author XYL
 * @date 2023/09/12 11:47
 * @since 305.2.2.0
 */
public abstract class AbstractHdfsWriter<T, R> implements HdfsWriter<T, R> {

	/**
	 * 客户端.
	 */
	protected FileSystem fs;

	/**
	 * 关闭资源.
	 */
	protected void safelyCloseResources() throws Exception {
		if (fs != null) {
			fs.close();
		}
	}

}
