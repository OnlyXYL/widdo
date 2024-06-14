package cn.widdo.autoconfigure.hadoop.writer;

import cn.widdo.autoconfigure.hadoop.HadoopRunner;

/**
 * HadoopWriter.
 *
 * @param <T> t
 * @param <R> r
 * @author XYL
 * @date 2023/09/12 11:44
 * @since 305.2.2.0
 */
@SuppressWarnings("ALL")
public interface HadoopWriter<T, R> extends HadoopRunner {

	/**
	 * 上传文件.
	 * @param t
	 * @return R
	 * @throws Exception
	 * @author XYL
	 * @date 2023/09/12 13:12:00
	 */
	R put(T t) throws Exception;

	/**
	 * 创建目录.
	 * @param t t
	 * @return R R
	 * @throws Exception
	 * @author XYL
	 * @date 2023/09/12 13:12:23
	 */
	R mkdirs(T t) throws Exception;

}
