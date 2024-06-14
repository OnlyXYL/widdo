package cn.widdo.hadoop.service.impl;

import cn.widdo.assistant.result.IResultInterface;
import cn.widdo.assistant.result.WiddoResult;
import cn.widdo.autoconfigure.hadoop.annotation.WiddoHadoop;
import cn.widdo.autoconfigure.result.WiddoResultInterface;
import cn.widdo.hadoop.service.HdfsService;
import cn.widdo.starter.hadoop.HdfsClient;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * HdfsServiceImpl.
 *
 * @author XYL
 * @date 2023/07/24 19:18
 * @since 305.2.2.0
 */
@Service
@WiddoHadoop
public class HdfsServiceImpl implements HdfsService {

	private static final Logger log = LoggerFactory.getLogger(HdfsServiceImpl.class);

	/**
	 * hdfs nn uri: hdsf://hadoop102:8020.
	 */
	@Value("${widdo.hadoop.hdfs.nn.inside-addr}")
	private String hdfsNnInsideAddr;

	@Override
	public WiddoResult mkdirs(Map<String, Object> params) {

		FileSystem fs = null;

		try {

			// 目录 /xiyou/huaguoshan
			final String path = params.get("path").toString();

			final String user = params.get("user").toString();

			// 获取客户端
			fs = HdfsClient.fs(hdfsNnInsideAddr, user);

			// 创建文件夹
			fs.mkdirs(new Path(path));

			// 关闭资源
			HdfsClient.close(fs);

			return WiddoResultInterface.HADOOP.HDFS.wrapper(null);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		finally {
			// 关闭资源
			if (fs != null) {
				HdfsClient.close(fs);
			}
		}
	}

	@Override
	public WiddoResult put(Map<String, Object> params) {

		FileSystem fs = null;

		try {

			final List<String> sourceList = (List<String>) params.get("source");

			final Path[] paths = HdfsClient.sources(sourceList);

			final String target = params.get("target").toString();

			final String user = params.get("user").toString();

			// 获取客户端
			fs = HdfsClient.fs(hdfsNnInsideAddr, user);

			// 文件上传。参数一：是否删除元数据，参数二：是否允许覆盖，参数三：源文件路径，参数四：目标路径
			fs.copyFromLocalFile(true, true, paths, new Path(target));

			return WiddoResult.response(IResultInterface.HadoopEnum.SUCCESS);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		finally {
			// 关闭资源
			if (fs != null) {
				HdfsClient.close(fs);
			}
		}
	}

	@Override
	public WiddoResult get(Map<String, Object> params) {

		FileSystem fs = null;

		try {

			final String source = params.get("source").toString();

			final String target = params.get("target").toString();

			final String user = params.get("user").toString();

			// 获取客户端
			fs = HdfsClient.fs(hdfsNnInsideAddr, user);

			// 文件上传。参数一：是否删除元数据，参数二：源文件路径，参数三：目标路径，参数四：？
			fs.copyToLocalFile(false, new Path(source), new Path(target), false);

			return WiddoResult.response(IResultInterface.HadoopEnum.SUCCESS);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		finally {
			// 关闭资源
			if (fs != null) {
				HdfsClient.close(fs);
			}
		}
	}

}
