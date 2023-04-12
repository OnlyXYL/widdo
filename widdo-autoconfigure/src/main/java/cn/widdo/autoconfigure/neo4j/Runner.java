package cn.widdo.autoconfigure.neo4j;

import cn.widdo.assistant.utils.BeanUtils;
import cn.widdo.starter.neo4j.constant.Neo4jConstants;
import cn.widdo.starter.neo4j.entity.Value;
import cn.widdo.starter.neo4j.entity.result.Result;
import cn.widdo.starter.neo4j.entity.result.ResultEnum;
import cn.widdo.starter.neo4j.utils.Neo4jUtil;
import cn.widdo.starter.neo4j.utils.ResultUtil;
import org.neo4j.driver.AccessMode;
import org.neo4j.driver.SessionConfig;
import org.neo4j.driver.exceptions.ServiceUnavailableException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Neo4j Runner, to execute neo4y operation.
 *
 * @author XYL
 * @date 2023/03/02 16:47
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
public interface Runner extends Session {

    /**
     * method named run to execute neo4j cypher without explicit transaction.
     *
     * @param params params
     * @return org.neo4j.driver.Result
     * @author XYL
     * @date 2023/03/02 17:40:38
     */
    default Result<List<Map<String, Value>>> run(Map<String, Object> params) {

        final org.neo4j.driver.Session session = open(AccessMode.WRITE);

        try {

            String cqlStr = params.get(Neo4jConstants.PARAM_CYPHER_QL).toString();

            Map<String, Object> map = Optional.ofNullable(params.get(Neo4jConstants.PARAM_MAP))
                    .map(BeanUtils::<Map<String, Object>>cast)
                    .orElse(null);

            final org.neo4j.driver.Result result = session.run(cqlStr, map);

            return Neo4jUtil.packResult(result);
        } catch (ServiceUnavailableException exception) {
            return (Result) ResultUtil.error(ResultEnum.ERROR, 10010, exception.getMessage());
        } catch (org.neo4j.driver.exceptions.ClientException exception) {
            System.out.println("--error by ClientException");
            return (Result) ResultUtil.error(ResultEnum.ERROR, exception.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

    /**
     * open the session.
     *
     * @param accessMode accessMode
     * @return org.neo4j.driver.Session
     * @author XYL
     * @date 2023/03/02 17:14:24
     */
    @Override
    default org.neo4j.driver.Session open(AccessMode accessMode) {
        return this.driver().session(SessionConfig.builder().withDefaultAccessMode(accessMode).build());
    }

    /**
     * close the session.
     *
     * @param session session
     * @author XYL
     * @date 2023/03/02 17:17:16
     */
    @Override
    default void close(org.neo4j.driver.Session session) {
        if (session != null) {
            session.close();
        }
    }
}
