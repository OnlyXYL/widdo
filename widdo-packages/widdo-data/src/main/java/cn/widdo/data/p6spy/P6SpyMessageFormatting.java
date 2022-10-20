package cn.widdo.data.p6spy;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 格式化日志
 *
 * @author XYL
 * @version 263.1.0.0
 * @date 2022/09/21 17:56
 */
public class P6SpyMessageFormatting implements MessageFormattingStrategy {

    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {

        String builder = this.format.format(new Date()) + " | took " +
                elapsed +
                "ms | " +
                category +
                " | connection " +
                connectionId +
                " | url " +
                url +
                "\n------------------------| " +
                sql +
                ";";
        return StringUtils.isNotEmpty(sql.trim()) ? builder : "";
    }
}
