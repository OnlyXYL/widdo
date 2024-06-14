package cn.widdo.autoconfigure.babelnet.reader;

/**
 * BabelNet 读取数据装饰器.
 *
 * @param <T> params
 * @param <R> return something
 * @author XYL
 * @date 2022/12/02 18:50
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
public abstract class AbstractBabelNetReaderDecorator<T, R> extends AbstractBabelNetReader<T, R> {

	/**
	 * {@link BabelNetReader}.
	 */
	protected BabelNetReader<T, R> babelNetReader;

}
