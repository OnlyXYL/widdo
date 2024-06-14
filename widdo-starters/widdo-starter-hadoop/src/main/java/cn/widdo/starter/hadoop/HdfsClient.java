package cn.widdo.starter.hadoop;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * HdfsClient.
 * <p>
 * HDFS客户端，封装HDFS的操作.
 * <p>
 * 步骤：
 * <p>
 * 1. 获取客户端对象
 * <p>
 * 2. 执行操作命令
 * <p>
 * 3. 关闭资源
 * <p>
 * <p>
 *
 * @author XYL
 * @date 2023/09/08 0:32
 * @since 305.2.2.0
 */
public class HdfsClient {

	/**
	 * hdfs schema.
	 */
	public static final String HDFS_SCHEMA = "hdfs://";

	/**
	 * 无参构造.
	 */
	protected HdfsClient() {
		throw new UnsupportedOperationException("工具类不能被实例化.");
	}

	/**
	 * 获取客户端.
	 * <p>
	 * 参数为nameNode地址,例如：hdsf://hadoop102:8020
	 * @param nnInsideAddr nnInsideAddr
	 * @param user 用户
	 * @return org.apache.hadoop.fs.FileSystem
	 * @author XYL
	 * @date 2023/09/08 00:50:26
	 */
	public static FileSystem fs(String nnInsideAddr, String user) {

		try {

			if (!nnInsideAddr.startsWith(HDFS_SCHEMA)) {
				nnInsideAddr = String.format("%s%s", HDFS_SCHEMA, nnInsideAddr);
			}

			// 连接集群nameNode
			final URI uri = new URI(nnInsideAddr);

			// 创建一个配置文件
			final Configuration configuration = new Configuration(false);

			if (StringUtils.isBlank(user)) {
				return FileSystem.get(uri, configuration);
			}

			// 获取客户端对象
			return FileSystem.get(uri, configuration, user);

		}
		catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取客户端.
	 * @param nnInsideAddr
	 * @return org.apache.hadoop.fs.FileSystem
	 * @author XYL
	 * @date 2023/09/08 00:57:09
	 */
	public static FileSystem fs(String nnInsideAddr) {

		try {
			if (!nnInsideAddr.startsWith(HDFS_SCHEMA)) {
				nnInsideAddr = String.format("%s%s", HDFS_SCHEMA, nnInsideAddr);
			}

			// 连接集群nameNode
			final URI uri = new URI(nnInsideAddr);

			// 创建一个配置文件
			final Configuration configuration = new Configuration(false);

			// 获取客户端对象
			return FileSystem.get(uri, configuration);

		}
		catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 释放资源.
	 * @param fs
	 * @author XYL
	 * @date 2023/09/08 00:55:00
	 */
	public static void close(FileSystem fs) {
		try {
			if (fs != null) {
				fs.close();
			}
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 源文件转换path.
	 * @param sources 源文件
	 * @return org.apache.hadoop.fs.Path[]
	 * @author XYL
	 * @date 2023/09/08 15:43:15
	 */
	public static Path[] sources(List<String> sources) {

		final Path[] paths = new Path[sources.size()];

		for (int i = 0; i < sources.size(); i++) {
			paths[i] = new Path(sources.get(i));
		}
		return paths;
	}

}
