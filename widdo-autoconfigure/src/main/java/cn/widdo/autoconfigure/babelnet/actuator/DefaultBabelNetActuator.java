package cn.widdo.autoconfigure.babelnet.actuator;

import cn.widdo.autoconfigure.babelnet.properties.WiddoBabelNetProperties;
import cn.widdo.autoconfigure.babelnet.reader.BabelNetReader;
import cn.widdo.autoconfigure.babelnet.reader.DefaultBabelNetReader;
import cn.widdo.autoconfigure.babelnet.writer.BabelNetWriter;
import cn.widdo.autoconfigure.babelnet.writer.DefaultBabelNetWriter;
import cn.widdo.autoconfigure.neo4j.reader.DefaultNeo4jReader;
import cn.widdo.autoconfigure.neo4j.writer.DefaultNeo4jWriter;
import it.uniroma1.lcl.babelnet.BabelSense;
import org.neo4j.driver.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * DefaultBabelNetActuator.
 *
 * @author XYL
 * @date 2023/03/15 15:23
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
public final class DefaultBabelNetActuator extends AbstractBabelNetActuator<Map<String, Object>, List<BabelSense>> {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultBabelNetActuator.class);

	/**
	 * Type constant of neo4j query cypher.
	 */
	private static final String BABELNET_READ = "read";

	/**
	 * Type constant of neo4j write cypher.
	 */
	private static final String BABELNET_WRITE = "write";

	/**
	 * properties.
	 */
	private final WiddoBabelNetProperties widdoBabelNetProperties;

	/**
	 * constructor has no param,at the same time, if you create instance by this
	 * constructor, it will throw exception typed {@link UnsupportedOperationException}.
	 */
	protected DefaultBabelNetActuator() {
		throw new UnsupportedOperationException();
	}

	/**
	 * constructor has one params called {@link WiddoBabelNetProperties}.
	 * @param widdoBabelNetProperties
	 */
	private DefaultBabelNetActuator(final WiddoBabelNetProperties widdoBabelNetProperties) {
		this.widdoBabelNetProperties = widdoBabelNetProperties;
	}

	@Override
	public List<BabelSense> read(Map<String, Object> params) {
		return readIfHave(params);
	}

	@Override
	public List<BabelSense> write(Map<String, Object> params) {
		return writeIfHave(params);
	}

	/**
	 * read.
	 * @param params params
	 * @return java.util.List<it.uniroma1.lcl.babelnet.BabelSense>
	 * @author XYL
	 * @date 2023/03/15 15:49:02
	 */
	private List<BabelSense> readIfHave(Map<String, Object> params) {

		String className = widdoBabelNetProperties.getActuator().getReader().getClassName();

		if (StringUtils.hasLength(className)) {
			// check whether it`s legitimate.
			final boolean present = ClassUtils.isPresent(className, BabelNetReader.class.getClassLoader());

			if (!present) {
				className = DefaultNeo4jReader.class.getName();
			}
		}
		else {
			// set the default classname.
			className = DefaultBabelNetReader.class.getName();
		}

		return reflectObject(className, BABELNET_READ, params);
	}

	/**
	 * write.
	 * @param params params
	 * @return java.util.List<it.uniroma1.lcl.babelnet.BabelSense>
	 * @author XYL
	 * @date 2023/03/15 15:49:13
	 */
	private List<BabelSense> writeIfHave(Map<String, Object> params) {
		String className = widdoBabelNetProperties.getActuator().getWriter().getClassName();

		if (StringUtils.hasLength(className)) {
			// check whether it`s legitimate
			final boolean present = ClassUtils.isPresent(className, BabelNetWriter.class.getClassLoader());
			if (!present) {
				className = DefaultBabelNetWriter.class.getName();
			}
		}
		else {
			// set the default classname.
			className = DefaultNeo4jWriter.class.getName();
		}

		return reflectObject(className, BABELNET_WRITE, params);
	}

	/**
	 * reflect to create instance.
	 * @param className className
	 * @param type type
	 * @param params params
	 * @return java.util.List<it.uniroma1.lcl.babelnet.BabelSense>
	 * @author XYL
	 * @date 2023/03/15 15:49:30
	 */
	private List<BabelSense> reflectObject(String className, String type, Map<String, Object> params) {
		try {
			final Class<?> aClass = Class.forName(className);

			// 反射，通过构造方法创建对象，需要Neo4jPreRWHelper实例.注意getConstructor方法只能获取public构造，protected和private需要getDeclaredConstructor方法
			final Constructor<?> constructor = aClass.getDeclaredConstructor(Driver.class);
			// allow to access private constructor
			constructor.setAccessible(true);
			final Object classObj = constructor.newInstance();

			final Method query = aClass.getMethod(type, Map.class);

			return (List<BabelSense>) query.invoke(classObj, params);
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			e.printStackTrace();
		}

		return null;
	}

}
