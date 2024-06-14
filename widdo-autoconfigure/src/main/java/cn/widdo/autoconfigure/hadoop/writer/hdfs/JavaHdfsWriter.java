package cn.widdo.autoconfigure.hadoop.writer.hdfs;

import cn.widdo.assistant.result.IResultInterface;
import cn.widdo.assistant.result.WiddoResult;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * JavaHdfsWriter.
 *
 * @author XYL
 * @date 2023/09/12 11:48
 * @since 305.2.2.0
 */
public class JavaHdfsWriter extends AbstractHdfsWriter<Map<String, Object>, WiddoResult> {

    private static final Logger log = LoggerFactory.getLogger(JavaHdfsWriter.class);

    /**
     * 无参构造，不让自己实例化.
     */
    protected JavaHdfsWriter() {
        throw new UnsupportedOperationException();
    }

    protected JavaHdfsWriter(final FileSystem fs) {
        this.fs = fs;
    }

    @Override
    public WiddoResult put(Map<String, Object> params) throws Exception {

        try {
            final List<String> sourceList = (List<String>) params.getOrDefault("source", List.of());

            if (sourceList.isEmpty()) {
                throw new IllegalArgumentException("Source list cannot be empty");
            }

            final Path[] sources = buildPathsFromSourceList(sourceList);

            final String target = params.get("target").toString();

            fs.copyFromLocalFile(false, true, sources, new Path(target));

            return WiddoResult.response(IResultInterface.HadoopEnum.SUCCESS);
        } catch (Exception e) {
            log.error("--------- [widdo][hadoop][put]，结果：[error]，信息：{}", e);
            throw new IOException("Error during directories creation", e);
        } finally {
            // 关闭资源
            super.safelyCloseResources();
        }
    }

    @Override
    public WiddoResult mkdirs(Map<String, Object> params) throws Exception {

        try {
            final String path = params.get("path").toString();

            fs.mkdirs(new Path(path));

            return WiddoResult.response(IResultInterface.HadoopEnum.SUCCESS);
        } catch (IOException e) {
            log.error("--------- [widdo][hadoop][put]，结果：[error]，信息：{}", e);
            throw new IOException("Error during directories creation", e);
        } finally {
            // 关闭资源
            super.safelyCloseResources();
        }
    }

    /**
     * 封装path.
     *
     * @param sources 参数
     * @return org.apache.hadoop.fs.Path[]
     * @author XYL
     * @date 2023/09/13 00:24:38
     */
    private Path[] buildPathsFromSourceList(List<String> sources) {
        return IntStream.range(0, sources.size())
                .mapToObj(sources::get)
                .map(Path::new)
                .toArray(Path[]::new);
    }

}
