package com.bingoabin.watermark
//#乱序数据流中引入 Watermark 和 EventTime
//#对于乱序数据流，有两种常见的引入方法：周期性和间断性

//#1、With Periodic（周期性的） Watermark
//周期性地生成 Watermark 的生成，默认是 100ms。每隔 N 毫秒自动向流里注入一个 Watermark，时间间隔由 streamEnv.getConfig.setAutoWatermarkInterval()决定
//#需求:对socket中无序数据流，进行每5s处理一次，数据中会有延迟
object OutOfOrderStreamPeriodicWaterMark {

}
