package cn.widdo.autoconfigure.hadoop.properties;

import cn.widdo.assistant.constant.PropertyConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * WiddoHadoopProperties.
 * <p>
 * NameNode Web : hadoop102:9870 NameNode inside: hadoop102:8020
 *
 * @author XYL
 * @date 2023/09/08 11:07
 * @since 305.2.2.0
 */
@SuppressWarnings("ALL")
@ConfigurationProperties(prefix = PropertyConstant.PROPERTY_WIDDO_HADOOP)
public class WiddoHadoopProperties {

	/**
	 * enabled.
	 */
	private boolean enabled;

	/**
	 * hdfs.
	 */
	private Hdfs hdfs = new Hdfs();

	/**
	 * actuator.
	 */
	private final Actuator actuator = new Actuator();

	/**
	 * user.
	 */
	private String user;

	/**
	 * get hdfs key.
	 * @return return a hdfs key type of String
	 */
	public Hdfs getHdfs() {
		return hdfs;
	}

	/**
	 * set hdfs.
	 * @param hdfs hdfs
	 */
	public void setHdfs(Hdfs hdfs) {
		this.hdfs = hdfs;
	}

	/**
	 * 开关状态.
	 * @return 返回开关
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * 设置开关.
	 * @param enabled 开关
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * 获取执行器.
	 * @return Actuator
	 */
	public Actuator getActuator() {
		return actuator;
	}

	/**
	 * 获取用户.
	 * @return 用户
	 */
	public String getUser() {
		return user;
	}

	/**
	 * 设置用户.
	 * @param user
	 */
	public void setUser(String user) {
		this.user = user;
	}

	public class Hdfs {

		/**
		 * 开关.
		 */
		private boolean enabled;

		/**
		 * NameNode.
		 */
		private NN nn = new NN();

		/**
		 * 2nn:secondaryNameNode.
		 */
		private SNN snn = new SNN();

		/**
		 * 无参构造.
		 */
		public Hdfs() {
		}

		/**
		 * 构造方法.
		 * @param enabled 开关状态
		 * @param nn NameNode key
		 * @param snn SecondaryNameNode key
		 */
		public Hdfs(final boolean enabled, final NN nn, final SNN snn) {
			this.enabled = enabled;
			this.nn = nn;
			this.snn = snn;
		}

		/**
		 * get a nameNode key.
		 * @return return a nameNode key type of String
		 */
		public NN getNn() {
			return nn;
		}

		/**
		 * 设置NameNode.
		 * @param nn nn
		 */
		public void setNn(NN nn) {
			this.nn = nn;
		}

		/**
		 * 获取 SecondaryNameNode key.
		 * @return 获取一个SecondaryNameNode key
		 */
		public SNN getSnn() {
			return snn;
		}

		/**
		 * 设置SecondaryNameNode key.
		 * @param snn snn
		 */
		public void setSnn(SNN snn) {
			this.snn = snn;
		}

		/**
		 * 获取开关.
		 * @return 返回开关
		 */
		public boolean isEnabled() {
			return enabled;
		}

		/**
		 * 设置开关.
		 * @param enabled 开关
		 */
		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}

	}

	public class NN {

		/**
		 * NameNode web 地址.
		 */
		private String webAddr;

		/**
		 * NameNode 内部地址.
		 */
		private String insideAddr;

		/**
		 * 无参构造.
		 */
		public NN() {
		}

		/**
		 * 有参构造.
		 * @param webAddr webAddr
		 * @param insideAddr insideAddr
		 */
		public NN(final String webAddr, final String insideAddr) {
			this.webAddr = webAddr;
			this.insideAddr = insideAddr;
		}

		/**
		 * 获取 NameNode web地址.
		 * @return 返回一个web地址
		 */
		public String getWebAddr() {
			return webAddr;
		}

		/**
		 * 设置 NameNode web地址.
		 * @param webAddr webAddr
		 */
		public void setWebAddr(String webAddr) {
			this.webAddr = webAddr;
		}

		/**
		 * 获取 NameNode 内部访问地址.
		 * @return 返回NameNode内部访问地址
		 */
		public String getInsideAddr() {
			return insideAddr;
		}

		/**
		 * 设置 NameNode 内部访问地址.
		 * @param insideAddr NameNode内部地址
		 */
		public void setInsideAddr(String insideAddr) {
			this.insideAddr = insideAddr;
		}

	}

	public class SNN {

		/**
		 * SecondaryNameNode web地址.
		 */
		private String webAddr;

		/**
		 * 无参构造.
		 */
		public SNN() {
		}

		/**
		 * 有参构造.
		 * @param webAddr SecondaryNameNode Web 访问地址
		 */
		public SNN(final String webAddr) {
			this.webAddr = webAddr;
		}

		/**
		 * 获取 SecondaryNameNode web访问地址.
		 * @return 返回一个 SecondaryNameNode web 访问地址
		 */
		public String getWebAddr() {
			return webAddr;
		}

		/**
		 * 设置 SecondaryNameNode web访问地址.
		 * @param webAddr SecondaryNameNode web 访问地址
		 */
		public void setWebAddr(String webAddr) {
			this.webAddr = webAddr;
		}

	}

	public static class Actuator {

		/**
		 * 开启读写开关.
		 */
		private Boolean enable = false;

		/**
		 * the className of {@link cn.widdo.autoconfigure.hadoop.actuator.HadoopActuator}.
		 */
		private String className;

		/**
		 * reader.
		 */
		private final Reader reader = new Reader();

		/**
		 * writer.
		 */
		private final Writer writer = new Writer();

		/**
		 * return the switch of hadoop actuator.
		 * @return the result of switch
		 */
		public Boolean getEnable() {
			return enable;
		}

		/**
		 * set the switch of hadoop actuator.
		 * @param enable the result of switch
		 */
		public void setEnable(Boolean enable) {
			this.enable = enable;
		}

		/**
		 * get the className of
		 * {@link cn.widdo.autoconfigure.hadoop.actuator.HadoopActuator}.
		 * @return the classname of
		 * {@link cn.widdo.autoconfigure.hadoop.actuator.HadoopActuator}
		 */
		public String getClassName() {
			return className;
		}

		/**
		 * set className of {@link cn.widdo.autoconfigure.hadoop.actuator.HadoopActuator}.
		 * @param className set className of
		 * {@link cn.widdo.autoconfigure.hadoop.actuator.HadoopActuator}
		 */
		public void setClassName(String className) {
			this.className = className;
		}

		/**
		 * get reader.
		 * @return an instance of reader
		 */
		public Reader getReader() {
			return reader;
		}

		/**
		 * get writer.
		 * @return an instance of writer
		 */
		public Writer getWriter() {
			return writer;
		}

	}

	public static class Reader {

		/**
		 * className.
		 */
		private String className;

		/**
		 * get className.
		 * @return a result type of String
		 */
		public String getClassName() {
			return className;
		}

		/**
		 * set className.
		 * @param className the className of neo4j Reader
		 */
		public void setClassName(String className) {
			this.className = className;
		}

	}

	public static class Writer {

		/**
		 * className.
		 */
		private String className;

		/**
		 * get className.
		 * @return a result type of String
		 */
		public String getClassName() {
			return className;
		}

		/**
		 * set className.
		 * @param className the className of Neo4j Writer
		 */
		public void setClassName(String className) {
			this.className = className;
		}

	}

}
