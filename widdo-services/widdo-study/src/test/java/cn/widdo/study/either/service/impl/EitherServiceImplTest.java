package cn.widdo.study.either.service.impl;

import cn.widdo.assistant.result.IResultInterface;
import cn.widdo.assistant.result.WiddoResult;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class EitherServiceImplTest {

    private EitherServiceImpl eitherServiceImplUnderTest;

    @Before
    public void setUp() {
        eitherServiceImplUnderTest = new EitherServiceImpl();
    }

    @Test
    public void testTestEither() {
        // Setup
        final Map<String, Object> params = Map.ofEntries(Map.entry("input", "value"));

        final WiddoResult expectedResult = WiddoResult.response(IResultInterface.StudyResultEnum.SUCCESS, "value");

        // Run the test
        final WiddoResult result = eitherServiceImplUnderTest.testEither(params);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testTestEither_ThrowsException() {
        // Setup
        final Map<String, Object> params = Map.ofEntries(Map.entry("input", "value"));

        // Run the test
        assertThrows(Exception.class, () -> eitherServiceImplUnderTest.testEither(params));
    }
}
