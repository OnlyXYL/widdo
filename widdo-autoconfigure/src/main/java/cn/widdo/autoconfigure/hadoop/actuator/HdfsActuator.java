package cn.widdo.autoconfigure.hadoop.actuator;

import cn.widdo.assistant.result.WiddoResult;

import java.util.Map;

/**
 * HdfsActuator.
 *
 * @author XYL
 * @date 2023/09/15 10:47
 * @since 305.2.2.0
 */
public class HdfsActuator extends AbstractHadoopActuator<Map<String, Object>, WiddoResult> {

	/**
	 * 构造方法.
	 * @param uri uri
	 * @param user user
	 * @param readerClassName readerClassName
	 * @param writerClassName writerClassName
	 * @return
	 * @author XYL
	 * @date 2023/10/27 13:51:51
	 */
	public HdfsActuator(final String uri, final String user, final String readerClassName,
			final String writerClassName) {
		super(uri, user, readerClassName, writerClassName);
	}

}
