package com.bingoabin.time
//#对于流式数据处理，最大的特点是数据上具有时间的属性特征
//#Flink根据时间产生的位置不同，可以将时间区分为三种时间概念
//1.Event Time（事件生成时间）:事件产生的时间，它通常由事件中的时间戳描述
//2.Ingestion time（事件接入时间）:事件进入Flink程序的时间
//3.Processing Time（事件处理时间）:事件被处理时当前系统的时间
//#Flink在流处理程序中支持不同的时间概念。

//### EventTime
//1、事件生成时的时间，在进入Flink之前就已经存在，可以从event的字段中抽取
//2、必须指定watermarks（水位线）的生成方式
//3、优势：确定性，乱序、延时、或者数据重放等情况，都能给出正确的结果
//4、弱点：处理无序事件时性能和延迟受到影响

//### IngestTime
//1、事件进入flink的时间，即在source里获取的当前系统的时间，后续操作统一使用该时间。
//2、不需要指定watermarks的生成方式(自动生成)
//3、弱点：不能处理无序事件和延迟数据

//### ProcessingTime
//1、执行操作的机器的当前系统时间(每个算子都不一样)
//2、不需要流和机器之间的协调
//3、优势：最佳的性能和最低的延迟
//4、弱点：不确定性 ，容易受到各种因素影像(event产生的速度、到达flink的速度、在算子之间传输速度等)，压根就不管顺序和延迟

//### 三种时间比较
//#性能
//ProcessingTime > IngestTime > EventTime
//#延迟
//ProcessingTime < IngestTime < EventTime
//#确定性
//EventTime > IngestTime > ProcessingTime

//### 设置Time类型
//#可以你的流处理程序是以哪一种时间为标志的。
//1.在我们创建StreamExecutionEnvironment的时候可以设置Time类型，不设置Time类型，默认是ProcessingTime。
//2.如果设置Time类型为EventTime或者IngestTime，需要在创建StreamExecutionEnvironment中调用setStreamTimeCharacteristic() 方法指定。
//
//val environment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
//
//#不设置Time 类型，默认是processingTime。
//environment.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime);
//
//#指定流处理程序以IngestionTime为准
//environment.setStreamTimeCharacteristic(TimeCharacteristic.IngestionTime);
//
//#指定流处理程序以EventTime为准
//environment.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
object Time {

}
