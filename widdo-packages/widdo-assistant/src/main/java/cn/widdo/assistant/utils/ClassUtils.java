package cn.widdo.assistant.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassUtils.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/12/30 14:40
 */
public class ClassUtils {

    private static final Logger LOG = LoggerFactory.getLogger(ClassUtils.class);


    protected ClassUtils() {
        throw new UnsupportedOperationException(ClassUtils.class.getName() + "can`t be instance");
    }

    /**
     * determine whether the class exists.
     *
     * @param className className
     * @return the result type of boolean
     */
    public static boolean isPresent(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            LOG.error("[Widdo] |- Packages [Widdo Assistant] |- ClassUtils.isPresent(). [Result] |- error, [Message] |- {} not found. [Trace] |- {}.", className, e.getStackTrace());

            return false;
        }
    }
}
