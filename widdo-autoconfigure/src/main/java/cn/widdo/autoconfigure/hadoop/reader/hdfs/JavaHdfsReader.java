package cn.widdo.autoconfigure.hadoop.reader.hdfs;

import cn.widdo.assistant.result.IResultInterface;
import cn.widdo.assistant.result.WiddoResult;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * JavaHdfsReader.
 *
 * @author XYL
 * @date 2023/09/12 11:22
 * @since 305.2.2.0
 */
@SuppressWarnings("ALL")
public class JavaHdfsReader extends AbstractHdfsReader<Map<String, Object>, WiddoResult> {

	private static final Logger log = LoggerFactory.getLogger(JavaHdfsReader.class);

	/**
	 * 无参构造，不让自己实例化.
	 */
	protected JavaHdfsReader() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 构造方法.
	 * @param uri uri
	 * @param user user
	 */
	private JavaHdfsReader(final String uri, final String user) {
		super(uri, user);
	}

	/**
	 * 构造方法.
	 * @param uri uri
	 * @param user user
	 * @param conf conf
	 */
	private JavaHdfsReader(final String uri, final String user, final Configuration conf) {
		super(uri, user, conf);
	}

	@Override
	public WiddoResult get(Map<String, Object> params) throws Exception {

		try {

			final String source = params.get("source").toString();

			final String target = params.get("target").toString();

			// 文件上传。参数一：是否删除元数据，参数二：源文件路径，参数三：目标路径，参数四：？
			fs().copyToLocalFile(false, new Path(source), new Path(target), false);

			return WiddoResult.response(IResultInterface.HadoopEnum.SUCCESS);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			// 关闭资源
			close();
		}
	}

}
