package cn.widdo.hadoop.service.impl;

import cn.widdo.assistant.result.WiddoResult;
import cn.widdo.autoconfigure.result.WiddoResultInterface;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import java.util.HashMap;

/**
 * @author XYL
 * @date 2023/07/25 9:52
 * @since
 */
public class HdfsServiceImplTest {

	@InjectMocks
	HdfsServiceImpl hdfsService;

	@Test
	public void put() throws Exception {

		PowerMockito.when(hdfsService.put(Mockito.anyMap())).thenReturn(WiddoResultInterface.HADOOP.HDFS.wrapper(null));

		final WiddoResult result = hdfsService.put(new HashMap<>());

		Assert.assertEquals("结果不是预期", WiddoResultInterface.HADOOP.HDFS.wrapper(null), result);
	}

}