package cn.widdo.autoconfigure.babelnet.reader;

import it.uniroma1.lcl.babelnet.BabelNet;
import it.uniroma1.lcl.babelnet.BabelNetQuery;
import it.uniroma1.lcl.babelnet.BabelSense;
import it.uniroma1.lcl.jlt.util.Language;

import java.util.List;
import java.util.Map;

/**
 * a default implements of BabelNetReader.
 *
 * @author XYL
 * @date 2022/12/02 18:54
 * @since 302.1.0.0
 */
public final class DefaultBabelNetReader extends AbstractBabelNetReader<Map<String, Object>, List<BabelSense>> {

    /**
     * constructor has none params.
     */
    private DefaultBabelNetReader() {
    }

    @Override
    public List<BabelSense> read(Map<String, Object> params) {

        final String lemma = params.get("lemma").toString();

        final BabelNet bt = BabelNet.getInstance();
        BabelNetQuery query = new BabelNetQuery.Builder(lemma)
                .from(Language.ZH)
                .build();

        return bt.getSensesFrom(query);
    }
}
