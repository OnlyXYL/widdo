package cn.widdo.autoconfigure.babelnet.reader;

import java.util.Map;

/**
 * a custom BabelNetReader by extends {@link AbstractBabelNetReaderDecorator}.
 *
 * @author XYL`
 * @date 2022/12/09 11:42
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
public class HttpBabelNetReader extends AbstractBabelNetReader<Map<String, Object>, Map<String, Object>> {

    @Override
    public Map<String, Object> read(Map<String, Object> params) {
        return null;
    }
}
