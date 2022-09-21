package cn.widdo.data.p6spy;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 格式化日志
 *
 * @author XYL
 * @version 1.2
 * @date 2022/09/21 17:56
 */
public class P6SpyMessageFormatting implements MessageFormattingStrategy {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {

        StringBuilder builder = new StringBuilder(this.format.format(new Date()));
        builder.append(" | took ");
        builder.append(elapsed);
        builder.append("ms | ");
        builder.append(category);
        builder.append(" | connection ");
        builder.append(connectionId);
        builder.append(" | url ");
        builder.append(url);
        builder.append("\n------------------------| ");
        builder.append(sql);
        builder.append(";");

        return StringUtils.isNotEmpty(sql.trim()) ? String.valueOf(builder) : "";
    }
}