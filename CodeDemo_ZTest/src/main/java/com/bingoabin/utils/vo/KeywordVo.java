package com.bingoabin.utils.vo;

/**
 * @author HanJunsheng
 * @date 2021/7/16
 * @copyright sankuai.com
 */
public class KeywordVo {
    private String keyword;
    private Integer pageSize;
    private Integer pageNo;

    public KeywordVo() {
        super();
    }

    public KeywordVo(Integer pageSize, Integer pageNo) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
    }

    public KeywordVo(String keyword, Integer pageSize, Integer pageNo) {
        this.keyword = keyword;
        this.pageSize = pageSize;
        this.pageNo = pageNo;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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
        return "KeywordVo{" +
                "keyword='" + keyword + '\'' +
                ", pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                '}';
    }
}
