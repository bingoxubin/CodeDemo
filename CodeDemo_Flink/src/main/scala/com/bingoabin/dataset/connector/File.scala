package com.bingoabin.dataset.connector

//为了从文件系统读取数据，Flink内置了对以下文件系统的支持
//| 文件系统 | Schema     | 备注                       |
//| -------- | ---------- | -------------------------- |
//| HDFS     | hdfs://    | Hdfs文件系统               |
//| S3       | s3://      | 通过hadoop文件系统实现支持 |
//| MapR     | maprfs://  | 需要用户添加jar            |
//| Alluxio  | alluxio:// | 通过hadoop文件系统实现     |

//#注意
//#Flink允许用户使用实现org.apache.hadoop.fs.FileSystem接口的任何文件系统。例如S3、 Google Cloud Storage Connector for Hadoop、 Alluxio、 XtreemFS、 FTP等各种文件系统
//#Flink与Apache Hadoop MapReduce接口兼容，因此允许重用Hadoop MapReduce实现的代码：
//使用Hadoop Writable data type
//使用任何Hadoop InputFormat作为DataSource(flink内置HadoopInputFormat)
//使用任何Hadoop OutputFormat作为DataSink(flink内置HadoopOutputFormat)
//使用Hadoop Mapper作为FlatMapFunction
//使用Hadoop Reducer作为GroupReduceFunction
object File {

}
