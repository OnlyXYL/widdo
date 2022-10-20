package cn.widdo.starter.neo4j.entity.result;

import java.io.Serializable;

/**
 * 结果分页
 *
 * @author XYL
 * @version 263.1.1.0
 * @date 2022/08/15 15:09
 */
public class ResultPageInfo implements Serializable{
    public ResultPageInfo() {
    }

    public ResultPageInfo(String totalRecord, String currentPage, String pageSize) {
            this.totalRecord = totalRecord;
            this.currentPage = currentPage;
            this.pageSize = pageSize;
    }


    /**
     * 总记录数
     */
    public  String totalRecord;

    /**
     * 当前页
     */
    public String currentPage;

    /**
     * 页面最大记录数
     */
    public String pageSize;

    public String getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(String totalRecord) {
        this.totalRecord = totalRecord;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
