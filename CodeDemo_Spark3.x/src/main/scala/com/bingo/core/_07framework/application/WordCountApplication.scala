package com.bingo.core._07framework.application

import com.bingo.core._07framework.common.TApplication
import com.bingo.core._07framework.controller.WordCountController


object WordCountApplication extends App with TApplication{

    // 启动应用程序
    start(){
        val controller = new WordCountController()
        controller.dispatch()
    }

}
