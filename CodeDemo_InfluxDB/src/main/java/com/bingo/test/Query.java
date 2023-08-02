package com.bingo.test;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.QueryApi;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;

import java.util.List;
import java.util.Map;

/**
 * @author: tony
 * @date: 2022/9/24 1:07
 * @description: 这是关于从InfluxDB查询数据的代码
 */
public class Query {

    /** token 操作时需要换成自己的 **/
    private static char[] token = "fqYcC_JV4n6UUyEYmg31DFiy3KrUgi7PbYX0I0VzbCVwjQywxR0FaS7FQGDLl6KbEtadNZw_1VVb0QSykIFj3A==".toCharArray();

    /** 组织名称 **/
    private static String org = "admin";

    /** InfluxDB服务提供的url **/
    private static String url = "http://bingo42:8086/";

    public static void main(String[] args) {

        // 0.创建InfluxDB客户端对象
        InfluxDBClient influxDBClient = InfluxDBClientFactory.create(url, token, org);

        // 1.获取查询API对象
        QueryApi queryApi = influxDBClient.getQueryApi();

        // 2.这个flux脚本会查询test_init存储桶中的go_goroutines测量，这个测量下只有一个序列
        String flux = "from(bucket: \"test_init\")\n" +
                "  |> range(start: -1h)\n" +
                "  |> filter(fn: (r) => r[\"_measurement\"] == \"go_goroutines\")\n" +
                "  |> aggregateWindow(every: 10s, fn: mean, createEmpty: false)\n" +
                "  |> yield(name: \"mean\")";

        // 3.这个flux脚本会查询example02存储桶中的cpu测量，指定字段名称为usage_user后，
        String flux2 = "from(bucket: \"example02\")\n" +
                "  |> range(start: -1h)\n" +
                "  |> filter(fn: (r) => r[\"_measurement\"] == \"cpu\")\n" +
                "  |> filter(fn: (r) => r[\"_field\"] == \"usage_user\")\n" +
                "  |> aggregateWindow(every: 10s, fn: mean, createEmpty: false)\n" +
                "  |> yield(name: \"mean\")";

        // 4.使用query方法将会得到一个List<FluxTable>对象，其中每一个FluxTable都对应着一个序列
        List<FluxTable> query = queryApi.query(flux);

        // 5.下面这个for循环会把遍历每个序列，并将这个序列中对应的每一行数据打印出来。
        for (FluxTable fluxTable : query) {
            List<FluxRecord> records = fluxTable.getRecords();
            for (FluxRecord record : records) {
                Map<String, Object> one = record.getValues();
                System.out.println(one);
            }
        }

        // 6.下面的queryRaw方法将会得到一个字符串，字符串中是FLUX拓展的CSV格式的数据
        String data = queryApi.queryRaw(flux2);
        System.out.println(data);

    }
}
