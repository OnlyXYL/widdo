package cn.widdo.autoconfigure.babelnet.writer;

import java.util.Map;

/**
 * default babelNet writer.
 *
 * @author XYL
 * @date 2023/03/15 10:37
 * @since 302.1.0.0
 */
public final class DefaultBabelNetWriter extends AbstractBabelNetWriter<Map<String, Object>, Map<String, Object>> {

    /**
     * constructor has none params.
     */
    private DefaultBabelNetWriter() {
    }

    @Override
    public Map<String, Object> write(Map<String, Object> params) {
        return null;
    }
}
