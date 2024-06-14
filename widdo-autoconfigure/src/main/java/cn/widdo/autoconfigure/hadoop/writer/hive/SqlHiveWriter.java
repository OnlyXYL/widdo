package cn.widdo.autoconfigure.hadoop.writer.hive;

import java.util.Map;

/**
 * SqlHiveWriter.
 *
 * @author XYL
 * @date 2023/09/12 11:52
 * @since 305.2.2.0
 */
public class SqlHiveWriter extends AbstractHiveWriter<Map<String, Object>, Map<String, Object>> {

	@Override
	public Map<String, Object> put(Map<String, Object> stringObjectMap) {
		return null;
	}

	@Override
	public Map<String, Object> mkdirs(Map<String, Object> stringObjectMap) {
		return null;
	}

}
