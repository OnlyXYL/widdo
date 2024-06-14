package cn.widdo.autoconfigure.hadoop.actuator;

import cn.widdo.autoconfigure.hadoop.reader.HadoopReader;
import cn.widdo.autoconfigure.hadoop.writer.HadoopWriter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * AbstractHadoopActuator.
 *
 * @param <T> t
 * @param <R> r
 * @author XYL
 * @date 2023/09/13 0:48
 * @since 305.2.2.0
 */
@SuppressWarnings("ALL")
public abstract class AbstractHadoopActuator<T, R> implements HadoopActuator<T, R> {

	/**
	 * reader.
	 */
	protected HadoopReader<T, R> reader;

	/**
	 * writer.
	 */
	protected HadoopWriter<T, R> writer;

	/**
	 * reader 类全路径.
	 */
	private String readerClassName;

	/**
	 * writer 类全路径.
	 */
	private String writerClassName;

	/**
	 * uri.
	 */
	private String uri;

	/**
	 * hadoop user.
	 */
	private String user;

	/**
	 * 构造方法.
	 * @param uri hadoop nn uri.
	 * @param user hadoop 用户
	 * @param readerClassName writer 类全路径
	 * @param writerClassName reader 类全路径
	 */
	public AbstractHadoopActuator(final String uri, final String user, final String readerClassName,
			final String writerClassName) {
		this.uri = uri;
		this.user = user;
		this.readerClassName = readerClassName;
		this.writerClassName = writerClassName;
	}

	@Override
	public HadoopReader<T, R> reader() {

		if (reader == null) {
			reader = hadoopReader(readerClassName);
		}

		return reader;
	}

	@Override
	public HadoopWriter<T, R> writer() {

		if (writer == null) {
			writer = hadoopWriter(writerClassName);
		}

		return writer;
	}

	/**
	 * hadoop reader.
	 * @param className reader 类全路径.
	 * @return cn.widdo.autoconfigure.hadoop.reader.HadoopReader
	 * @author XYL
	 * @date 2023/10/26 15:57:16
	 */
	protected HadoopReader hadoopReader(String className) {
		try {
			final Class<?> aClass = Class.forName(className);

			// 反射，通过构造方法创建对象，需要Neo4jPreRWHelper实例.注意getConstructor方法只能获取public构造，protected和private需要getDeclaredConstructor方法
			final Constructor<?> constructor = aClass.getDeclaredConstructor(String.class, String.class);
			// allow to access private constructor
			constructor.setAccessible(true);
			return (HadoopReader) constructor.newInstance(uri, user);
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Hadooper Writer.
	 * @param className writer 类全路径
	 * @return cn.widdo.autoconfigure.hadoop.writer.HadoopWriter
	 * @author XYL
	 * @date 2023/10/26 15:59:38
	 */
	protected HadoopWriter hadoopWriter(String className) {
		try {
			final Class<?> aClass = Class.forName(className);

			// 反射，通过构造方法创建对象，需要Neo4jPreRWHelper实例.注意getConstructor方法只能获取public构造，protected和private需要getDeclaredConstructor方法
			final Constructor<?> constructor = aClass.getDeclaredConstructor(String.class, String.class);
			// allow to access private constructor
			constructor.setAccessible(true);
			return (HadoopWriter) constructor.newInstance(uri, user);
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			e.printStackTrace();
		}

		return null;
	}

}
