package com.bingoabin.cep

//#描述:在我们操作某些银行APP的时候，经常会发现，如果上一个操作与下一个操作IP变换了（例如上一个操作使用的流量操作，下一个操作我连接上了wifi去操作，IP就会变换），那么APP就要求我们重新进行登录，避免由于IP变换产生的风险操作
//#需求:用户上一个操作与下一个操作IP变换报警
//
//#数据格式如下:从socket当中输入数据源
//192.168.52.100,zhubajie,https://icbc.com.cn/login.html,2020-02-12 12:23:45
//192.168.54.172,tangseng,https://icbc.com.cn/login.html,2020-02-12 12:23:46
//192.168.145.77,sunwukong,https://icbc.com.cn/login.html,2020-02-12 12:23:47
//192.168.52.100,zhubajie,https://icbc.com.cn/transfer.html,2020-02-12 12:23:47
//192.168.54.172,tangseng,https://icbc.com.cn/transfer.html,2020-02-12 12:23:48
//192.168.145.77,sunwukong,https://icbc.com.cn/transfer.html,2020-02-12 12:23:49
//192.168.145.77,sunwukong,https://icbc.com.cn/save.html,2020-02-12 12:23:52
//192.168.52.100,zhubajie,https://icbc.com.cn/save.html,2020-02-12 12:23:53
//192.168.54.172,tangseng,https://icbc.com.cn/save.html,2020-02-12 12:23:54
//192.168.54.172,tangseng,https://icbc.com.cn/buy.html,2020-02-12 12:23:57
//192.168.145.77,sunwukong,https://icbc.com.cn/buy.html,2020-02-12 12:23:58
//192.168.52.100,zhubajie,https://icbc.com.cn/buy.html,2020-02-12 12:23:59
//192.168.44.110,zhubajie,https://icbc.com.cn/pay.html,2020-02-12 12:24:03
//192.168.38.135,tangseng,https://icbc.com.cn/pay.html,2020-02-12 12:24:04
//192.168.89.189,sunwukong,https://icbc.com.cn/pay.html,2020-02-12 12:24:05
//192.168.44.110,zhubajie,https://icbc.com.cn/login.html,2020-02-12 12:24:04
//192.168.38.135,tangseng,https://icbc.com.cn/login.html,2020-02-12 12:24:08
//192.168.89.189,sunwukong,https://icbc.com.cn/login.html,2020-02-12 12:24:07
//192.168.38.135,tangseng,https://icbc.com.cn/pay.html,2020-02-12 12:24:10
//192.168.44.110,zhubajie,https://icbc.com.cn/pay.html,2020-02-12 12:24:06
//192.168.89.189,sunwukong,https://icbc.com.cn/pay.html,2020-02-12 12:24:09
//192.168.38.135,tangseng,https://icbc.com.cn/pay.html,2020-02-12 12:24:13
//192.168.44.110,zhubajie,https://icbc.com.cn/pay.html,2020-02-12 12:24:12
//192.168.89.189,sunwukong,https://icbc.com.cn/pay.html,2020-02-12 12:24:15

//#整理之后的格式如下：
//192.168.145.77,sunwukong,https://icbc.com.cn/login.html,2020-02-12 12:23:47
//192.168.145.77,sunwukong,https://icbc.com.cn/transfer.html,2020-02-12 12:23:49
//192.168.145.77,sunwukong,https://icbc.com.cn/save.html,2020-02-12 12:23:52
//192.168.145.77,sunwukong,https://icbc.com.cn/buy.html,2020-02-12 12:23:58
//192.168.89.189,sunwukong,https://icbc.com.cn/pay.html,2020-02-12 12:24:05
//192.168.89.189,sunwukong,https://icbc.com.cn/login.html,2020-02-12 12:24:07
//192.168.89.189,sunwukong,https://icbc.com.cn/pay.html,2020-02-12 12:24:09
//192.168.89.189,sunwukong,https://icbc.com.cn/pay.html,2020-02-12 12:24:15

//192.168.52.100,zhubajie,https://icbc.com.cn/login.html,2020-02-12 12:23:45
//192.168.52.100,zhubajie,https://icbc.com.cn/transfer.html,2020-02-12 12:23:47
//192.168.52.100,zhubajie,https://icbc.com.cn/save.html,2020-02-12 12:23:53
//192.168.52.100,zhubajie,https://icbc.com.cn/buy.html,2020-02-12 12:23:59
//192.168.44.110,zhubajie,https://icbc.com.cn/pay.html,2020-02-12 12:24:03
//192.168.44.110,zhubajie,https://icbc.com.cn/login.html,2020-02-12 12:24:04
//192.168.44.110,zhubajie,https://icbc.com.cn/pay.html,2020-02-12 12:24:06
//192.168.44.110,zhubajie,https://icbc.com.cn/pay.html,2020-02-12 12:24:12

//192.168.54.172,tangseng,https://icbc.com.cn/login.html,2020-02-12 12:23:46
//192.168.54.172,tangseng,https://icbc.com.cn/transfer.html,2020-02-12 12:23:48
//192.168.54.172,tangseng,https://icbc.com.cn/save.html,2020-02-12 12:23:54
//192.168.54.172,tangseng,https://icbc.com.cn/buy.html,2020-02-12 12:23:57
//192.168.38.135,tangseng,https://icbc.com.cn/pay.html,2020-02-12 12:24:04
//192.168.38.135,tangseng,https://icbc.com.cn/login.html,2020-02-12 12:24:08
//192.168.38.135,tangseng,https://icbc.com.cn/pay.html,2020-02-12 12:24:10
//192.168.38.135,tangseng,https://icbc.com.cn/pay.html,2020-02-12 12:24:13
object CEPExample1 {

}
