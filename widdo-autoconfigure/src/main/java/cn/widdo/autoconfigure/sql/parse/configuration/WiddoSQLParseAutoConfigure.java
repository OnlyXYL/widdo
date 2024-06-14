package cn.widdo.autoconfigure.sql.parse.configuration;

import cn.hutool.core.builder.GenericBuilder;
import cn.widdo.autoconfigure.sql.annotation.WiddoSQL;
import cn.widdo.autoconfigure.sql.properties.WiddoSQLProperties;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * WiddoSQLParseAutoConfigure.
 *
 * @author XYL
 * @date 2023/08/07 16:27
 * @since 305.2.2.0
 */
@SuppressWarnings("ALL")
@WiddoSQL
@EnableConfigurationProperties(WiddoSQLProperties.class)
public class WiddoSQLParseAutoConfigure {

	/**
	 * SQL解析器 provider.
	 *
	 * @author XYL
	 * @date 2023/09/07 19:43:02
	 */
	@PostConstruct
	public void sqlParserProvider() {
		try {

			final Class<?> aClass = Class.forName(SQLParserProvider.class.getName());

			// 反射，通过构造方法创建对象，需要Neo4jPreRWHelper实例.注意getConstructor方法只能获取public构造，protected和private需要getDeclaredConstructor方法
			final Constructor<?> constructor = aClass.getDeclaredConstructor();
			// allow to access private constructor
			constructor.setAccessible(true);

			SQLParserProvider sqlParserProvider = (SQLParserProvider) constructor.newInstance();

			GenericBuilder.of(SQLParserManager::new).with(SQLParserManager::setSqlParserProvider, sqlParserProvider)
					.build();

		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

}
