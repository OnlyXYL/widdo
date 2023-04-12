/**
 * 描述：自动配置模块
 * 这是自动配置模块，提供所有组件的默认配置。
 * <p>
 * 创建时间：2022年11月17日 00点34分
 * <p>
 * 版本：302.1.0.0
 * <p>
 * 同时package-info.java有以下作用：
 * <p>
 * 1. 提供包级别的变量
 * <p>
 * 满足只在包里使用对应的变量，而不想在其他包里使用，就可以把变量放到package-info.java下面，实现分包自用的理念
 * <p>
 * 2. 提供包级别的注释
 * 如上
 * <p>
 * 3. 用来生成javadoc
 *
 * @author XYL
 * @date 2023/02/08 10:43:15
 * @since 302.1.0.0
 **/
package cn.widdo.autoconfigure;

/**
 * 包常量.
 */
class AutoconfigureConst {

    /**
     * 包常量.
     */
    public static final String PACKAGE_INFO_TEST = "package-info.java";

    /**
     * construct have no params.
     */
    protected AutoconfigureConst() {
        throw new UnsupportedOperationException(AutoconfigureConst.class.getName() + " can`t have instance.");
    }
}
