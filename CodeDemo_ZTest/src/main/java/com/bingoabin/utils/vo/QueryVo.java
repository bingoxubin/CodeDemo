package com.bingoabin.utils.vo;

/**
 * @author HanJunsheng
 * @date 2021/7/16
 * @copyright sankuai.com
 */
public class QueryVo {
    private String query;
    private Integer pageSize;
    private Integer pageNo;

    public QueryVo() {
        super();
    }

    public QueryVo(Integer pageSize, Integer pageNo) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
    }

    public QueryVo(String query, Integer pageSize, Integer pageNo) {
        this.query = query;
        this.pageSize = pageSize;
        this.pageNo = pageNo;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public String toString() {
        return "QueryVo{" +
                "query='" + query + '\'' +
                ", pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                '}';
    }
}
