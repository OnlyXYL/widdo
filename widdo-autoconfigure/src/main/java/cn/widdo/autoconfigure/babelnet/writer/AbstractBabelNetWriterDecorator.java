package cn.widdo.autoconfigure.babelnet.writer;

/**
 * abstract babelNet  writer decorator.
 *
 * @param <T> t
 * @param <R> r
 * @author XYL
 * @date 2023/03/15 10:35
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
public abstract class AbstractBabelNetWriterDecorator<T, R> extends AbstractBabelNetWriter<T, R> {

    /**
     * BabelNetWriter.
     */
    protected BabelNetWriter<T, R> babelNetWriter;
}
