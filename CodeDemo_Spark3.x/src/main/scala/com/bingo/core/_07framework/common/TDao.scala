package com.bingo.core._07framework.common

import com.bingo.core._07framework.util.EnvUtil


trait TDao {

    def readFile(path:String) = {
        EnvUtil.take().textFile(path)
    }
}
