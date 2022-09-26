package cn.widdo.assistant.enums;

/**
 * 响应状态码
 *
 * @author XYL
 * @version 1.0
 * @since 2021/4/21 0021 15:25
 */
public enum ResponseCode {

    /**
     * 成功
     */
    SUCCESS(200, "success"),

    /**
     * 组件接口成功
     */
    SKC_SUCCESS(20000, "success"),

    /**
     * 失败
     */
    ERROR(201, "error"),

    //================== 本体相关  10000 开始 ===================

    /**
     * 10000
     */
    ONTOLOGY_NOT_FOUND(10000, "本体不存在"),

    /**
     * 10001
     */
    ONTOLOGY_ALREADY_FOUND(10001, "本体已存在"),
    /**
     * 10002
     */
    NOT_HAVE_GRAPH(10002, "未生成图谱"),
    /**
     * 10003
     */
    NOT_BUILD_SPEECH(10003, "本体未构建语义"),
    /**
     * 10004
     */
    ALREADY_BUILD_SPEECH(10004, "本体已构建语义"),
    /**
     * 10007
     */
    NOT_PRODUCE_TRIPLE(10007, "未生成三元组"),
    /**
     * 10008
     */
    HAVE_SEMICOLON(10008, "本体详情概念中不能有分号"),

    /**
     * 本体搜索节点异常
     */
    ONTOLOGY_SEARCH_NODE_ERROR(10009, "本体搜索节点异常"),

    /**
     * 本体查询深度为1的关系异常
     */
    ONTOLOGY_SEARCH_ONE_DEPTH_EDGES_ERROR(10010, "本体查询深度为1的关系异常"),

    CO_TRIPLE_ERROR(10011, "生成三元组异常"),

    WORDS_DICT_ERROR(10012, "生成词典异常"),

    CO_GRAPH_ERROR(10013, "生成图谱异常"),

    CO_DECONSTRUCT_ERROR(10014, "补充继承异常"),

    SUPPLEMENT_SPEECH_ERROR(10015, "补充词性异常"),

    SUPPLEMENT_IMPORTANCE_ERROR(10016, "补充重度异常"),

    SUPPLEMENT_FREQUENCY_ERROR(10017, "补充额度异常"),

    SUPPLEMENT_OTHER_ERROR(10018, "更全本体补充异常"),

    SUPPLEMENT_FEEDBACK_ERROR(10019, "投影反馈异常"),

    REPORTS_ERROR(10020, "报告文档补充异常"),
    /**
     * 用报告文档补充 人工审核异常
     */
    SUPPLEMENT_AUDIT_ERROR(10021, "审核异常"),
    PARAMETER_PLUS_OBJECT_ERROR(10022, "参数补充对象异常"),


    //================== 投影相关  20000 开始 ===================
    PROJECT_EXCEL_RESULT_STORAGE_ERROR(20000, "excel投影结果，存储异常"),

    PROJECT_EXCEL_DELETE_ERROR(20001, "删除上传的元数据模型异常"),

    /**
     * 投影概念图谱异常
     */
    PROJECT_CONCEPT_GRAPH_ERROR(20002, "投影概念图谱异常"),

    /**
     * 投影概念图谱搜索节点异常
     */
    PROJECT_CONCEPT_GRAPH_SEARCH_NODE_ERROR(20003, "投影概念图谱搜索节点异常"),

    /**
     * 投影查询深度为1的关系异常
     */
    PROJECT_CONCEPT_GRAPH_SEARCH_ONE_DEPTH_EDGES_ERROR(20004, "投影查询深度为1的关系异常"),

    PROJECT_TREE_RESULT_ERROR(20005, "获取树型投影结果异常"),

    LINEAR_ERROR(20006, "线性化失败"),

    LINEAR_PARAMS_ERROR(20007, "线性化参数异常"),

    LINEAR_CONFIG_ERROR(20008, "线性化配置异常"),

    LINEAR_HAVE_NAME(20009, "配置名称已存在"),

    LINEAR_SEARCH_CONCEPT_GRAPH_ERROR(20010, "查看投影概念图谱异常"),

    PROJECT_RESULT_STORAGE_ERROR(20011, "投影结果记录持久化异常"),

    PROJECT_PROGRESS_ERROR(20012, "投影进行中，无需重复操作"),

    NOT_FIND_PROJECT_RESULT_FILE_ERROR(20013, "投影结果文件或三元组文件或关系规则文件为空"),

    //================== 本体识别相关  30000 开始 ===================
    WORD_ALREADY_DISCERN(30000, "该文件名已被识别，请您更改文件名"),

    DISCERN_FAIL(30003, "识别失败"),

    DISCERN_RESULT_ERROR(30004, "查询结果详情异常"),
    /**
     * 30004
     */
    NOT_MARK_WORD(30004, "未标注文档"),
    /**
     * 30005
     */
    NOT_ANALYSIS_WORD(30005, "未解析文档"),

    //=================== 语义网相关 40000 开始 =====================
    /**
     * 语义网搜索节点异常
     */
    SEMANTIC_SEARCH_NODE_ERROR(40001, "语义网搜索节点异常"),

    /**
     * 语义网查询深度为1的关系异常
     */
    SEMANTIC_SEARCH_ONE_DEPTH_EDGES_ERROR(40002, "语义网查询深度为1的关系异常"),

    SPEECH_BUILD_ERROR(40003, "语义构建失败"),

    GRAPH_CREATE_ERROR(40004, "图谱生成失败"),

    //=================== 远程调用相关 50000 开始 =====================
    POST_DATA_DISCERN_FAIL(50000, "数据识别失败"),

    POST_FOUR_DISCERN_FAIL(50001, "文档四标失败"),

    POST_MARK_DISCERN_FAIL(50002, "分词标注失败"),

    POST_DATA_MARK_DISCERN_FAIL(50003, "数据标注失败"),

    POST_ANALYSIS_DISCERN_FAIL(50004, "解析失败"),

    POST_DISCERN_FAIL(50005, "识别失败"),

    POST_EXTRACT_ERROR(50006, "文档提取失败"),

    POST_LABEL_INDEXING_ERROR(50007, "标签标引失败"),
    /**
     * 本体详情提取子集
     */
    POST_EXTRACT_SUBSET_ERROR(50007, "提取失败"),
    /**
     * 根据来源提取子集
     */
    POST_SOURCE_ERROR(50008, "提取失败"),

    /**
     * 文档提取单词 审核失败
     */
    POST_AUDIT_ERROR(50009, "审核异常"),

    POST_CO_TRIPLE_ERROR(50010, "生成三元组异常"),

    POST_WORDS_DICT_ERROR(50011, "生成词典异常"),

    POST_CO_GRAPH_ERROR(50012, "生成图谱异常"),

    POST_CO_DECONSTRUCT_ERROR(50013, "补充继承异常"),

    POST_SUPPLEMENT_SPEECH_ERROR(50014, "补充词性异常"),

    POST_SUPPLEMENT_IMPORTANCE_ERROR(50015, "补充重度异常"),

    POST_SUPPLEMENT_FREQUENCY_ERROR(50016, "补充额度异常"),

    POST_SUPPLEMENT_OTHER_ERROR(50017, "更全本体补充异常"),

    POST_SUPPLEMENT_FEEDBACK_ERROR(50018, "投影反馈异常"),

    POST_REPORTS_ERROR(50019, "报告文档补充异常"),
    /**
     * 用报告文档补充 审核失败
     */
    POST_SUPPLEMENT_AUDIT_ERROR(50020, "审核异常"),
    POST_PARAMETER_PLUS_OBJECT_ERROR(50021, "参数补充对象异常"),
    POST_PREPROCESS_ERROR(50022, "文档预处理异常"),

    //=================== 文档提取单词相关 60000 开始 =====================
    EXTRACT_ERROR(60000, "文档提取失败"),
    EXTRACT_DATA_ERROR(60001, "单词分页查询异常"),
    /**
     * 文档提取单词 审核失败
     */
    AUDIT_ERROR(60001, "审核异常"),

    //=================== 文档四标相关 70000 开始 =====================
    DATA_DISCERN_FAIL(70000, "数据识别失败"),
    FOUR_DISCERN_FAIL(70001, "文档四标失败"),

    //=================== 文档预处理相关 80000 开始 =====================
    PREPROCESS_ERROR(80000, "文档预处理异常"),
    MARK_DISCERN_FAIL(80001, "标注失败"),

    ANALYSIS_DISCERN_FAIL(80002, "解析失败"),
    DOCUMENT_ALREADY_DISCERN(80003, "该文件名已被处理，请您更改文件名"),
    PREPROCESS_BE_BEING_ERROR(80004, "文档正在预处理"),
    MARK_BE_BEING_ERROR(80005, "文档标注中"),
    ANALYSIS_BE_BEING_ERROR(80006, "文档解析中"),

    //=================== 远程调用相关 90000 开始 =====================
    POST_KG_BUILD_CONCEPT_ERROR(90000, "构建概念图谱异常"),
    POST_KG_BUILD_RELATIONSHIP_RULES_ERROR(90001, "构建关系规则异常"),
    POST_KG_BUILD_INSTANCE_GRAPH_ERROR(90002, "实例图谱构建异常"),
    POST_KG_CREAT_GRAPH_ERROR(90003, "创建图谱异常"),
    NOT_FIND_LABEL_INDEXING_ERROR(90004, "未找到标签标引文件"),
    //    NOT_FIND_CONCEPT_ERROR(90004, "未找到概念图谱文件"),
    POST_KG_CONCEPT_PROPERTIES_ERROR(90004, "查询概念图谱概念属性异常"),
    LABEL_INDEXING_DATA_META_ERROR(90005, "标引文件数据元格式异常"),
    NOT_FIND_RELATIONSHIP_RULES_ERROR(90006, "未找到标签标引文件"),
    POST_KG_NOT_FIND_CONCEPT_PROPERTIES_ERROR(90007, "未发现概念图谱概念属性"),

    //=================== 知识源管理相关 100000 开始 =====================
    KNOWLEDGE_SOURCE_ALREADY_FOUND(100000, "知识源名称已存在"),
    KNOWLEDGE_SOURCE_FILE_NAME_ALREADY_FOUND(100001, "知识源详情文件名称已存在"),
    KNOWLEDGE_HAVE_STUDY_PROCESS(100002, "知识源下有学习中的知识源，请先停止学习"),
    KNOWLEDGE_STUDY_ERROR(100003, "知识源学习异常"),
    KNOWLEDGE_STUDY_PROCESS(100004, "知识源正学习"),
    KNOWLEDGE_HAVE_TASK(100005, "知识源有定时任务"),
    KNOWLEDGE_STOP_TO_MORE(100006, "知识源停止中过多，请稍后重试"),
    KNOWLEDGE_FILE_STUDY(100007, "移除失败，学习中的文件不能移除"),
    KNOWLEDGE_NOT_HAVE_TASK(100008, "知识源没有定时任务"),
    KNOWLEDGE_STOP_STUDY(100009, "知识源停止学习"),
    KNOWLEDGE_NOT_STUDY(100010, "每日0点至6点为模型补充时间"),

    //=================== 前端组件相关 110000 开始 =====================
    FIND_DIMENSION_BY_ID_ERROR(100000, "根据维度ID列表查询异常"),
    FIND_TYPE_BY_ID_ERROR(100001, "根据知识分类ID列表查询异常"),
    FIND_ALL_DIMENSION_ERROR(100002, "查询全部维度异常"),
    FIND_LEXICON_NOT_NULL_ERROR(100002, "查询有词库的分类异常"),

    //=================== 系统级别 900 开始 =====================

    /**
     * 文件服务器异常
     */
    FAST_DFS_EXCEPTION(900, "文件服务器异常"),

    /**
     * 参数异常
     */
    PARAMS_ERROR(901, "参数异常"),

    INSERT_ERROR(902, "新增信息异常"),

    UPDATE_ERROR(903, "修改信息异常"),

    DELETE_ERROR(904, "信息删除异常"),

    FIND_ERROR(905, "信息查询异常"),

    DOWNLOAD_ERROR(906, "文件下载异常"),

    UPLOAD_ERROR(907, "文件上传异常"),

    NOT_FIND_FILE_ERROR(908, "文件获取异常");

    private int code;

    private String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ResponseCode getEnumByCode(int code) {
        for (ResponseCode enumType : ResponseCode.values()) {
            if (enumType.getCode() == code) {
                return enumType;
            }
        }
        return null;
    }
}
