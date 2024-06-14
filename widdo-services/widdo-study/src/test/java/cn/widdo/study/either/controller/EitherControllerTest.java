package cn.widdo.study.either.controller;

import cn.widdo.assistant.exception.BaseException;
import cn.widdo.assistant.result.IResultInterface;
import cn.widdo.assistant.result.WiddoResult;
import cn.widdo.study.either.service.EitherService;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(EitherController.class)
public class EitherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EitherService mockEitherService;

    @Test
    public void testTestEither() throws Exception {
        // Setup
        when(mockEitherService.testEither(Map.ofEntries(Map.entry("input", "value"))))
                .thenReturn(WiddoResult.response(IResultInterface.StudyResultEnum.SUCCESS, "value"));

        final WiddoResult expectedResponse = WiddoResult.response(IResultInterface.StudyResultEnum.SUCCESS, "value");

        final String input = JSON.toJSONString(ImmutableMap.of("input", "value"));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/either/test")
                        .content(input).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(JSON.toJSONString(expectedResponse), response.getContentAsString());
    }

    @Test
    public void testTestEither_EitherServiceReturnsFailure() throws Exception {
        // Setup
        when(mockEitherService.testEither(Map.ofEntries(Map.entry("input", "value"))))
                .thenReturn(WiddoResult.response(new BaseException("message", new Exception("message"), false, false)));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/either/test")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    public void testTestEither_EitherServiceThrowsException() throws Exception {
        // Setup
        when(mockEitherService.testEither(Map.ofEntries(Map.entry("input", "value")))).thenThrow(Exception.class);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/either/test")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }
}
