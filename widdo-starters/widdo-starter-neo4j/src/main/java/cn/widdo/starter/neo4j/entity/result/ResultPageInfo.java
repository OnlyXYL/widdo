package cn.widdo.starter.neo4j.entity.result;

import java.io.Serializable;

/**
 * 结果分页.
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/08/15 15:09
 */
public class ResultPageInfo implements Serializable {

    /**
     * 总记录数.
     */
    private String totalRecord;

    /**
     * 当前页.
     */
    private String currentPage;

    /**
     * 页面最大记录数.
     */
    private String pageSize;

    /**
     * 无参构造.
     */
    public ResultPageInfo() {
    }

    /**
     * 构造方法.
     * @param totalRecord
     * @param currentPage
     * @param pageSize
     */
    public ResultPageInfo(final String totalRecord,
                          final String currentPage,
                          final String pageSize) {
        this.totalRecord = totalRecord;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    /**
     * get totalRecord.
     * @return a String result
     */
    public String getTotalRecord() {
        return totalRecord;
    }

    /**
     * set total record.
     * @param totalRecord
     */
    public void setTotalRecord(String totalRecord) {
        this.totalRecord = totalRecord;
    }

    /**
     * get current page.
     *
     * @return a String result
     */
    public String getCurrentPage() {
        return currentPage;
    }

    /**
     * set current page.
     * @param currentPage
     */
    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * get page size.
     * @return a String result
     */
    public String getPageSize() {
        return pageSize;
    }

    /**
     * set page size.
     * @param pageSize
     */
    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
