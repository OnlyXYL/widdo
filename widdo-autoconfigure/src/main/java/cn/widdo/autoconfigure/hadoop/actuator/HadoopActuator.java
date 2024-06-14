package cn.widdo.autoconfigure.hadoop.actuator;

import cn.widdo.autoconfigure.hadoop.reader.HadoopReader;
import cn.widdo.autoconfigure.hadoop.writer.HadoopWriter;

/**
 * HadoopActuator.
 *
 * @param <T> t
 * @param <R> r
 * @author XYL
 * @date 2023/09/13 0:47
 * @since 305.2.2.0
 */
@SuppressWarnings("ALL")
public interface HadoopActuator<T, R> {

	/**
	 * reader.
	 * @return HadoopReader
	 */
	HadoopReader<T, R> reader();

	/**
	 * writer.
	 * @return HadoopWriter
	 */
	HadoopWriter<T, R> writer();

}
