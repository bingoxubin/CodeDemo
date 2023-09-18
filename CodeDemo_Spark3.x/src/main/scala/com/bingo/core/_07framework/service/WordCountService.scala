package com.bingo.core._07framework.service

import com.bingo.core._07framework.common.TService
import com.bingo.core._07framework.dao.WordCountDao
import org.apache.spark.rdd.RDD

/**
  * 服务层
  */
class WordCountService extends TService {

    private val wordCountDao = new WordCountDao()

    // 数据分析
    def dataAnalysis() = {

        val lines = wordCountDao.readFile("datas/word.txt")
        val words: RDD[String] = lines.flatMap(_.split(" "))
        val wordToOne = words.map(word=>(word,1))
        val wordToSum: RDD[(String, Int)] = wordToOne.reduceByKey(_+_)
        val array: Array[(String, Int)] = wordToSum.collect()
        array
    }
}
