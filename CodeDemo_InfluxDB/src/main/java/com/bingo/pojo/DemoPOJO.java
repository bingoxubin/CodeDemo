package com.bingo.pojo;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

import java.time.Instant;

/**
 * @author: bingoabin
 * @date: 2022/9/23 21:53
 * @description: 这是一个POJO类，对应将POJO类写入InfluxDB
 */
@Measurement(name="temperature")
public class DemoPOJO {

    /** 注意类上的@Measurement注解，我们既可以使用Measurement注解来指定一个POJO类的测量名称
     * 但是使用@Measurement注解会将测量名称直接写死在代码里
     * 当测量名称会在运行时发生改变时，我们可以使用@Column(menasurement=true)注解
     * 这样会将POJO类中被注解的值作为测量名称。
     * **/
    //@Column(measurement = true)
    //String measurementName;

    /** 相当于InfluxDB行协议中的标签集，此处标签的名称将会是location **/
    @Column(tag = true)
    String location;

    /** 相当于InfluxDB行协议中的字段集，此处字段的名称将会是value **/
    @Column
    Double value;

    /** 相当于InfluxDB行协议中的时间戳 **/
    @Column(timestamp = true)
    Instant timestamp;

    /** 全参构造器，方便调用者创建对象 **/
    public DemoPOJO(String location, Double value, Instant timestamp) {
        this.location = location;
        this.value = value;
        this.timestamp = timestamp;
    }
}
