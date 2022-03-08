package com.bingoabin.utils.ddl;

import java.util.List;

/**
 * ETL 信息
 */
public class ETLInfo {
    private String sourceDb;

    private String targetDb;

    private String targetTable;

    /**
     * 表注释
     */
    private String tableComment;


    /**
     * 列信息
     */
    private List<ETLColumn> columns;

    /**
     * 分区信息
     */
    private List<ETLColumn> partitionKeys;

    private String errMsg; // 错误信息


    public String getSourceDb() {
        return sourceDb;
    }

    public void setSourceDb(String sourceDb) {
        this.sourceDb = sourceDb;
    }

    public String getTargetDb() {
        return targetDb;
    }

    public void setTargetDb(String targetDb) {
        this.targetDb = targetDb;
    }

    public String getTargetTable() {
        return targetTable;
    }

    public void setTargetTable(String targetTable) {
        this.targetTable = targetTable;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public List<ETLColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<ETLColumn> columns) {
        this.columns = columns;
    }

    public List<ETLColumn> getPartitionKeys() {
        return partitionKeys;
    }

    public void setPartitionKeys(List<ETLColumn> partitionKeys) {
        this.partitionKeys = partitionKeys;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "ETLInfo{" +
                "sourceDb='" + sourceDb + '\'' +
                ", targetDb='" + targetDb + '\'' +
                ", targetTable='" + targetTable + '\'' +
                ", tableComment='" + tableComment + '\'' +
                ", columns=" + columns +
                ", partitionKeys=" + partitionKeys +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}
