package cn.widdo.autoconfigure.hadoop.configure;

import cn.widdo.autoconfigure.hadoop.annotation.WiddoHadoop;
import cn.widdo.autoconfigure.hadoop.actuator.HadoopActuator;
import cn.widdo.autoconfigure.hadoop.properties.WiddoHadoopProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * WiddoHadoopAutoConfigure.
 *
 * @author XYL
 * @date 2023/09/08 11:52
 * @since 305.2.2.0
 */
@SuppressWarnings("ALL")
@WiddoHadoop
@EnableConfigurationProperties(WiddoHadoopProperties.class)
public class WiddoHadoopAutoConfigure {

	private static final Logger log = LoggerFactory.getLogger(WiddoHadoopAutoConfigure.class);

	/**
	 * 实例化执行器.
	 * @param widdoHadoopProperties
	 * @return cn.widdo.autoconfigure.hadoop.actuator.HadoopActuator
	 * @author XYL
	 * @date 2023/10/27 13:58:14
	 */
	@Bean
	@ConditionalOnMissingBean(HadoopActuator.class)
	public HadoopActuator hadoopActuator(WiddoHadoopProperties widdoHadoopProperties) {

		final String className = widdoHadoopProperties.getActuator().getClassName();

		return createHadoopActuator(className, widdoHadoopProperties.getHdfs().getNn().getInsideAddr(),
				widdoHadoopProperties.getUser(), widdoHadoopProperties.getActuator().getWriter().getClassName(),
				widdoHadoopProperties.getActuator().getReader().getClassName());
	}

	/**
	 * 创建执行器.
	 * @param className 执行器类全路径
	 * @param uri nn uri
	 * @param user hadoop 用户
	 * @param readClassName reader 类全路径
	 * @param writerClassName writer类全路径
	 * @return cn.widdo.autoconfigure.hadoop.actuator.HadoopActuator
	 * @author XYL
	 * @date 2023/10/26 15:52:08
	 */
	private HadoopActuator createHadoopActuator(String className, String uri, String user, String readClassName,
			String writerClassName) {
		try {

			if (StringUtils.hasLength(className)) {
				// check whether it`s legitimate
				ClassUtils.isPresent(className, HadoopActuator.class.getClassLoader());
			}
			else {
				// set the default classname.
				className = HadoopActuator.class.getName();
			}

			final Class<?> aClass = Class.forName(className);

			// 反射，通过构造方法创建对象，需要Neo4jPreRWHelper实例.注意getConstructor方法只能获取public构造，protected和private需要getDeclaredConstructor方法
			final Constructor<?> constructor = aClass.getDeclaredConstructor(String.class, String.class, String.class,
					String.class);
			// allow to access private constructor
			constructor.setAccessible(true);

			return (HadoopActuator) constructor.newInstance(readClassName, writerClassName);

		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			e.printStackTrace();
		}

		return null;
	}

}
