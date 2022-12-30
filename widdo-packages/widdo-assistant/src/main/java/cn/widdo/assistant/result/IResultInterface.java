package cn.widdo.assistant.result;

/**
 * widdo result interface.
 *
 * @param <C>
 * @param <T>
 * @param <R>
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/12/29 17:39
 */
public interface IResultInterface<C extends Enum, T, R> {

    /**
     * return code of current result.
     *
     * @return java.lang.Integer
     * @author XYL
     * @date 2022/12/29 17:40:09
     **/
    Integer getCode();

    /**
     * return the message of current result.
     *
     * @param
     * @return java.lang.String
     * @throws
     * @author XYL
     * @date 2022/12/29 17:40:41
     **/
    String getMsg();

    /**
     * wrapper result.
     *
     * @param t
     * @return R
     */
    R wrapper(T t);

    enum SysResultEnum implements IResultInterface {
        /**
         * success.
         */
        SUCCESS(0, "成功"),

        /**
         * fail.
         */
        FAIL(-1, "失败"),

        /**
         * params error.
         */
        PARAMS_ERROR(-2, "参数异常");

        /**
         * code.
         */
        private final Integer code;

        /**
         * msg.
         */
        private final String msg;

        /**
         * constructor of has two params.one called:code,another called:msg.
         *
         * @param code code
         * @param msg  msg
         */
        SysResultEnum(final Integer code, final String msg) {
            this.code = code;
            this.msg = msg;
        }

        @Override
        public Integer getCode() {
            return code;
        }

        @Override
        public String getMsg() {
            return msg;
        }

        @Override
        public Object wrapper(Object o) {
            return null;
        }


    }

    enum StudyResultEnum implements IResultInterface {

        /**
         * success.
         */
        SUCCESS(0, "成功"),

        /**
         * fail.
         */
        FAIL(-1, "失败");

        /**
         * code.
         */
        private final Integer code;

        /**
         * msg.
         */
        private final String msg;

        /**
         * constructor of has two params.one called:code,another called:msg.
         *
         * @param code code
         * @param msg  msg
         */
        StudyResultEnum(final Integer code, final String msg) {
            this.code = code;
            this.msg = msg;
        }

        @Override
        public Integer getCode() {
            return code;
        }

        @Override
        public String getMsg() {
            return msg;
        }

        @Override
        public Object wrapper(Object o) {
            return null;
        }


    }

    enum LifeResultEnum implements IResultInterface {

        /**
         * the result typed success of life.
         */
        SUCCESS(0, "成功"),

        /**
         * the result typed fail of life.
         */
        FAIL(-1, "失败");

        /**
         * code.
         */
        private final Integer code;

        /**
         * msg.
         */
        private final String msg;

        /**
         * constructor has two params,one called code,another called msg.
         *
         * @param code
         * @param msg
         */
        LifeResultEnum(final Integer code, final String msg) {
            this.code = code;
            this.msg = msg;
        }

        @Override
        public Integer getCode() {
            return code;
        }

        @Override
        public String getMsg() {
            return msg;
        }

        @Override
        public Object wrapper(Object o) {
            return null;
        }


    }

    enum Neo4jResultEnum implements IResultInterface {

        /**
         * the result typed enum of neo4jResult.
         */
        SUCCESS(0, "成功") {
            @Override
            public Object wrapper(Object o) {
                return null;
            }
        },

        /**
         * the result typed enum of neo4jResult.
         */
        FAIL(-1, "失败") {
            @Override
            public Object wrapper(Object o) {
                return null;
            }
        };

        /**
         * code.
         */
        private final Integer code;

        /**
         * msg.
         */
        private final String msg;

        /**
         * constructor has two params,one called code,another called msg.
         *
         * @param code
         * @param msg
         */
        Neo4jResultEnum(final Integer code, final String msg) {
            this.code = code;
            this.msg = msg;
        }

        @Override
        public Integer getCode() {
            return code;
        }

        @Override
        public String getMsg() {
            return msg;
        }
    }
}
