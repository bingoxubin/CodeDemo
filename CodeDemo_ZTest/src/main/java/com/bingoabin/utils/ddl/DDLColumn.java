package com.bingoabin.utils.ddl;

/**
 * @author ouzhenwei
 * @ClassName DDLColumn
 * @Description
 * @createTime 2021-07-30 17:29:36
 */
public class DDLColumn {
    private String name;

    private String type;

    private String comment;

    private boolean asPartitionKey = false;

    public DDLColumn() {
    }

    public DDLColumn(String name, String type, String comment, boolean asPartitionKey) {
        this.name = name;
        this.type = type;
        this.comment = comment;
        this.asPartitionKey = asPartitionKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isAsPartitionKey() {
        return asPartitionKey;
    }

    public void setAsPartitionKey(boolean asPartitionKey) {
        this.asPartitionKey = asPartitionKey;
    }
}
