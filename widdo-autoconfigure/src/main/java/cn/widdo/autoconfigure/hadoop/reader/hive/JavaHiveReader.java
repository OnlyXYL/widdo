package cn.widdo.autoconfigure.hadoop.reader.hive;

import cn.widdo.autoconfigure.result.WiddoResultInterface;

import java.util.Map;

/**
 * JavaHiveReader.
 *
 * @author XYL
 * @date 2023/09/12 11:27
 * @since 305.2.2.0
 */
public class JavaHiveReader extends AbstractHiveReader<Map<String, Object>, WiddoResultInterface> {

	@Override
	public WiddoResultInterface get(Map<String, Object> stringObjectMap) throws Exception {
		return null;
	}

}
