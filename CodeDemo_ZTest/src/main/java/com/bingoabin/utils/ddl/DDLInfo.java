package com.bingoabin.utils.ddl;

import lombok.AllArgsConstructor;

import java.util.List;

/**
 * @author ouzhenwei
 * @ClassName DDLInfo
 * @Description
 * @createTime 2021-07-30 17:17:43
 */
@AllArgsConstructor
public class DDLInfo {
    private String tblComment;

    private List<DDLColumn> columns;

    public String getTblComment() {
        return tblComment;
    }

    public void setTblComment(String tblComment) {
        this.tblComment = tblComment;
    }

    public List<DDLColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<DDLColumn> columns) {
        this.columns = columns;
    }


}
