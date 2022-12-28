package cn.widdo.autoconfigure.neo4j.result;

/**
 * result.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/12/06 0:03
 */
public interface Result {


    enum Status {

        /**
         * success.
         */
        SUCCESS(200),

        /**
         * error.
         */
        ERROR(201);

        /**
         * code.
         */
        private final int code;

        /**
         * constructor has one param called:code.
         * @param code
         */
        Status(final int code) {
            this.code = code;
        }
    }
}
