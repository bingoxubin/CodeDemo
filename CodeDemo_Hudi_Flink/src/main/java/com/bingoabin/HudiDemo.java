package com.bingoabin;

import org.apache.flink.contrib.streaming.state.EmbeddedRocksDBStateBackend;
import org.apache.flink.contrib.streaming.state.PredefinedOptions;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author cjp
 * @version 1.0
 */
public class HudiDemo {
    public static void main(String[] args) {
        // IDEA运行时， 提供webui
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 设置状态后端RocksDB
        EmbeddedRocksDBStateBackend embeddedRocksDBStateBackend = new EmbeddedRocksDBStateBackend(true);
        //idea运行时指定rocksdb存储路径
//        embeddedRocksDBStateBackend.setDbStoragePath("file:///F:/rocksdb");
        embeddedRocksDBStateBackend.setPredefinedOptions(PredefinedOptions.SPINNING_DISK_OPTIMIZED_HIGH_MEM);
        env.setStateBackend(embeddedRocksDBStateBackend);

        // checkpoint配置
        env.enableCheckpointing(TimeUnit.SECONDS.toMillis(5), CheckpointingMode.EXACTLY_ONCE);
        CheckpointConfig checkpointConfig = env.getCheckpointConfig();
        checkpointConfig.setCheckpointStorage("hdfs://hadoop11:8020/ckps");
        checkpointConfig.setMinPauseBetweenCheckpoints(TimeUnit.SECONDS.toMillis(2));
        checkpointConfig.setTolerableCheckpointFailureNumber(5);
        checkpointConfig.setCheckpointTimeout(TimeUnit.MINUTES.toMillis(1));
        checkpointConfig.setExternalizedCheckpointCleanup(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);

        StreamTableEnvironment sTableEnv = StreamTableEnvironment.create(env);

        sTableEnv.executeSql("CREATE TABLE sourceT (\n" +
                "  uuid varchar(20),\n" +
                "  name varchar(10),\n" +
                "  age int,\n" +
                "  ts timestamp(3),\n" +
                "  `partition` varchar(20)\n" +
                ") WITH (\n" +
                "  'connector' = 'datagen',\n" +
                "  'rows-per-second' = '1'\n" +
                ")");



        sTableEnv.executeSql("create table t2(\n" +
                "  uuid varchar(20),\n" +
                "  name varchar(10),\n" +
                "  age int,\n" +
                "  ts timestamp(3),\n" +
                "  `partition` varchar(20)\n" +
                ")\n" +
                "with (\n" +
                "  'connector' = 'hudi',\n" +
                "  'path' = 'hdfs://hadoop1:8020/tmp/hudi_flink/t2',\n" +
                "  'table.type' = 'MERGE_ON_READ'\n" +
                ")");

        sTableEnv.executeSql("insert into t2 select * from sourceT");


    }
}
