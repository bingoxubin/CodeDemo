package com.bingo.core.distributecompute

class Task extends Serializable {

    val datas = List(1,2,3,4)

    //val logic = ( num:Int )=>{ num * 2 }
    val logic : (Int)=>Int = _ * 2


}
