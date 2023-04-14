package cn.widdo.data.p6spy;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 格式化日志.
 *
 * @author XYL
 * @date 2022/09/21 17:56
 * @since 263.1.1.0
 */
public class P6SpyMessageFormatting implements MessageFormattingStrategy {

    /**
     * 格式化日期.
     */
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    @Override
    public String formatMessage(int connectionId,
                                String now,
                                long elapsed,
                                String category,
                                String prepared,
                                String sql,
                                String url) {

        String builder = String.format("%s | took %dms | %s | connection %d | url %s\n------------------------| %s;",
                this.format.format(new Date()), elapsed, category, connectionId, url, sql);
        return StringUtils.isNotEmpty(sql.trim()) ? builder : "";
    }
}
