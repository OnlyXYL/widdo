package cn.widdo.autoconfigure.neo4j;

import org.neo4j.driver.AccessMode;
import org.neo4j.driver.Driver;

/**
 * neo4j session.
 *
 * @author XYL
 * @date 2023/03/02 16:50
 * @since 302.1.0.0
 */
@SuppressWarnings("ALL")
public interface Session {

    /**
     * neo4j driver.
     *
     * @return org.neo4j.driver.Driver
     * @author XYL
     * @date 2023/03/02 17:24:33
     */
    Driver driver();

    /**
     * 开启session.
     *
     * @param accessMode accessMode {@link AccessMode}
     * @return org.neo4j.driver.Session
     * @author XYL
     * @date 2023/03/02 16:51:46
     */
    org.neo4j.driver.Session open(AccessMode accessMode);

    /**
     * 关闭session.
     *
     * @param session session
     * @author XYL
     * @date 2023/03/02 16:51:56
     */
    void close(org.neo4j.driver.Session session);
}
