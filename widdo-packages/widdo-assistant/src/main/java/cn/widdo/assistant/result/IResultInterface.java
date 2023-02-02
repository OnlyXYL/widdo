package cn.widdo.assistant.result;

/**
 * widdo result interface.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/12/29 17:39
 */
public interface IResultInterface {

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
     * @return java.lang.String
     * @author XYL
     * @date 2022/12/29 17:40:41
     **/
    String getMsg();

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
         * @param code code
         * @param msg   msg
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
    }

    enum Neo4jResultEnum implements IResultInterface {

        /**
         * the result typed enum of neo4jResult.
         */
        SUCCESS(0, "成功"),

        /**
         * the result typed enum of neo4jResult.
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
         * @param code  code
         * @param msg   msg
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
