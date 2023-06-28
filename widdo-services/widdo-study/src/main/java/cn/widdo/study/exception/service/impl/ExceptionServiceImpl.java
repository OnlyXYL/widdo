package cn.widdo.study.exception.service.impl;

import cn.widdo.assistant.exception.BaseException;
import cn.widdo.assistant.result.IResultInterface;
import cn.widdo.assistant.result.WiddoResult;
import cn.widdo.study.exception.service.ExceptionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * ExceptionServiceImpl.
 *
 * @author XYL
 * @date 2023/05/15 11:41
 * @since 305.2.0.1
 */
@Service
@AllArgsConstructor
public class ExceptionServiceImpl implements ExceptionService {

    @Override
    public WiddoResult test() {
        throw new BaseException(IResultInterface.StudyResultEnum.FAIL);
    }
}
