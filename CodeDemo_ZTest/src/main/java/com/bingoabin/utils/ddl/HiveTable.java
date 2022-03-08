package com.bingoabin.utils.ddl;

import java.util.List;

/**
 * @author ouzhenwei
 * @ClassName HiveTable
 * @Description
 * @createTime 2021-07-29 17:39:40
 */
public class HiveTable {
    private List<HiveColumn> columns;

    private String comment;

    public List<HiveColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<HiveColumn> columns) {
        this.columns = columns;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "HiveTable{" +
                "comment='" + comment + '\'' +
                ", columns=" + columns + '}';
    }
}
