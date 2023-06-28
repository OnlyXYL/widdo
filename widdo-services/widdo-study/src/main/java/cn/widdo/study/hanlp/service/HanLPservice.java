package cn.widdo.study.hanlp.service;

import cn.widdo.assistant.result.WiddoResult;

/**
 * HanLpService.
 *
 * @author XYL
 * @date 2023/04/26 15:14
 * @since 305.1.1.0
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public interface HanLPservice {

    /**
     * 依存句法分析.
     *
     * @param text
     * @return cn.widdo.assistant.result.WiddoResult
     * @author XYL
     * @date 2023/04/26 15:15:10
     */
    WiddoResult dependencyParser(String text);
}
