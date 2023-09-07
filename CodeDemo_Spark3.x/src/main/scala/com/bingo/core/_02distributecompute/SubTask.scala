package com.bingo.core._02distributecompute

class SubTask extends Serializable {
    var datas : List[Int] = _
    var logic : (Int)=>Int = _

    // 计算
    def compute() = {
        datas.map(logic)
    }
}
