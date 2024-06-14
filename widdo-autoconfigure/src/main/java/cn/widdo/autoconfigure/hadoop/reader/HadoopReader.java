package cn.widdo.autoconfigure.hadoop.reader;

import cn.widdo.autoconfigure.hadoop.HadoopRunner;

/**
 * HadoopReader.
 *
 * @param <R> r
 * @param <T> t
 * @author XYL
 * @date 2023/09/12 10:53
 * @since 305.2.2.0
 */
@SuppressWarnings("ALL")
public interface HadoopReader<T, R> extends HadoopRunner {

	/**
	 * 读取hdfs文件.
	 * @param t
	 * @return R
	 * @author XYL
	 * @date 2023/09/13 00:41:15
	 */
	R get(T t) throws Exception;

}
