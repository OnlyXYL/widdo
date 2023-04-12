package cn.widdo.study.babelnet.service.impl;

import cn.widdo.assistant.result.IResultInterface;
import cn.widdo.assistant.result.WiddoResult;
import cn.widdo.autoconfigure.babelnet.actuator.BabelNetActuator;
import cn.widdo.study.babelnet.service.BabelNetService;
import it.uniroma1.lcl.babelnet.BabelNet;
import it.uniroma1.lcl.babelnet.BabelNetQuery;
import it.uniroma1.lcl.babelnet.BabelSense;
import it.uniroma1.lcl.jlt.util.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * babelNetServiceImpl.
 *
 * @author XYL
 * @date 2023/03/15 13:37
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
@Service
public class BabelNetServiceImpl implements BabelNetService {

    /**
     * BabelNetActuator.
     */
    private final BabelNetActuator<Map<String, Object>, List<BabelSense>> babelNetActuator;

    @Autowired
    public BabelNetServiceImpl(final BabelNetActuator babelNetActuator) {
        this.babelNetActuator = babelNetActuator;
    }

    @Override
    public WiddoResult query(Map<String, Object> params) {

        final String lemma = params.get("lemma").toString();

        babelNetActuator.read(params);

        final BabelNet bt = BabelNet.getInstance();
        BabelNetQuery query = new BabelNetQuery.Builder(lemma)
                .from(Language.ZH)
                .build();

        final List<BabelSense> sensesFrom = bt.getSensesFrom(query);

        return WiddoResult.response(IResultInterface.StudyResultEnum.SUCCESS, sensesFrom);
    }
}
