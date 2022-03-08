package com.bingoabin.utils.ddl;

/**
 * 列信息
 */
public class HiveColumn {
    /**
     * 字段名
     */
    private String name;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 备注
     */
    private String comment;

    /**
     * 是否是分区字段
     */
    private boolean partition;

    public HiveColumn() {
        this.partition = false;
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

    public boolean isPartition() {
        return partition;
    }

    public void setPartition(boolean partition) {
        this.partition = partition;
    }

    @Override
    public String toString() {
        return "HiveColumn{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", comment='" + comment + '\'' +
                ", partition=" + partition +
                '}';
    }
}
