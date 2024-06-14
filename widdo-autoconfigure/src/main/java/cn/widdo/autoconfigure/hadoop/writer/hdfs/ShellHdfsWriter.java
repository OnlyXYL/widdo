package cn.widdo.autoconfigure.hadoop.writer.hdfs;

import java.util.Map;

/**
 * ShellHdfsWriter.
 *
 * @author XYL
 * @date 2023/09/12 11:49
 * @since 305.2.2.0
 */
public class ShellHdfsWriter extends AbstractHdfsWriter<Map<String, Object>, Map<String, Object>> {

	@Override
	public Map<String, Object> put(Map<String, Object> stringObjectMap) {
		return null;
	}

	@Override
	public Map<String, Object> mkdirs(Map<String, Object> stringObjectMap) {
		return null;
	}

}
