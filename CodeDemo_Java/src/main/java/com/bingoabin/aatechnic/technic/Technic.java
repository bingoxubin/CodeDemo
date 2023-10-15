package com.bingoabin.aatechnic.technic;

import org.junit.Test;

/**
 * @author bingoabin
 * @date 2023/10/11 8:03
 * @Description:
 */
public class Technic {
	@Test
	public void test() {
		//快捷键技巧
		//1. shift shift 全局搜索  注意其中的git，可以搜索git提交的东西
		//2. ctrl shift v 在idea中复制过的东西都在里面，可以一键粘贴
		//3. ctrl E  平时看了哪些类
		//4. alt 2   快速查看书签 定义书签右击ADD Bookmark
		//5. 抽取方法:alt shift M  抽取全局变量：ctrl alt C 抽取局部变量：ctrl alt V 抽取参数：ctrl alt P 抽取字段：ctrl alt F
		//6. 查看方法在哪边被调用过 ctrl G
		//7. 在controller中crtl shift t 快速生成测试类  选择Junit5  在快捷键中搜索Go To Test设置快捷键
		//8. 查看实现 ctrl alt B

		//源码阅读技巧
		//1.ctrl alt B 查看接口实现 service.test()  进到test方法，通过ctrl 左键只是到接口，用ctrl alt B能直接到实现
		//2.添加书签  还可以右击添加快捷键  全局ctrl + 2可以快速定位
		//3.查看类图 以及  时序图   类图中没有原类要手动添加
		//4.多线程调试  断点上右击选择thread，然后在栈针处进行切换
		//5.远程代码调试 启动类上 Edit Configurations -> 然后添加Remote JVM Debug -> 配置远程ip，端口默认是5050；然后生成参数拷贝
		//  然后注意*号转义：java -Xdebug -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=\*:5005 -jar demo.jar
		//  运行 remote debug demo 开启debug模式

		//debug的4个技巧
		//条件断点：
		//1.操作方式，在sysout哪一行，打上断点
		//2.在断点上面，右击，加上条件 i == 3
		//3.debug 当i==3的时候，才会停止在sysout
		//断点回退
		//1.操作方式，list和for循环上，分别打上断点
		//2.然后debug调试，当调试到systemout的时候
		//3.可以点击回退+乌龟的图标进行回退到上一步断点位置
		//执行表达式
		//1.操作方式，list和systemout循环上，分别打上断点
		//2.然后debug调试，当调试到systemout的时候
		//3.此时第一个list.get(i) 是1
		//4.可以在下面debug的结果值上面的输入框中输入  list.set(i,10) 进行修改结果
		//5.输出 10 2 3 4 5 6 7 8 9 只改变当前值
		//不想继续执行，直接跳出
		//1.操作方式，systemout上，打上断点
		//2.然后debug调试，当调试到systemout的时候
		//3.在下方的左边栈针上，右击进行Force Return然后代码走完，是不会打印出东西的
		//流式断点
		//1.在流处理中打上断点
		//2.debug的过程中，debug区域右上角有Trace Current Stream Chain进入流式展示

		//传递参数
		//程序参数
		//System.out.println("program para = " + arg);
		//jvm参数
		// String vmPara = System.getProperty("vmPara");
		//环境参数
		//String envParam = System.getenv("envParam");

		//插件
		//Key Promoter X 在右上角的Notifications中查看对应的快捷键，也可以进行修改对应的快捷键;可以通过shift shift的actions中进行统计使用的快捷键次数
		//CPU Usage Indicator 监控idea的内存使用
		//Nyan Progress Bar 进度条美化插件
		//string Manipulation 字符串处理插件 选中字符串，alt M进行快捷操作，加密解密

		//设置
		//1.ctrl + 方法名  查看该方法被哪些地方调用了，可以查看每个调用的地方的明细
		//2.新项目的默认配置  File -> New Projects Setup -> Settings for New Projects
		//3.设置代码提示  File -> settings -> Editor -> General -> Code Completion以及Auto Import

		//git使用
		//从git中创建项目  File -> New -> Project from Version Control
		//一个项目添加多个地址 左侧选择git -> Log -> Remote上面右击 -> manage remotes
		//跟上面同一个地方，可以对比多个分支的区别，先选中一个，然后ctrl选中另一个，右击 Compare Branches；同样选中一个，然后点击左侧的左右箭头，对比当前分支

		//maven配置
		//设置编译多线程，在maven的配置中

		//日志
		//方法调用：logm   报错：loge  return前返回：logr
		//private final Logger logger = LoggerFactory.getLogger(AFileResolve.class);
		//设置：File -> Editor -> Live Templates
		//具体设置：https://gist.github.com/ichengzi/6933bd03d1c25f8935d85859a5e6425a

		//测试
		//在controller中crtl shift t 快速生成测试类  选择Junit5  在快捷键中搜索Go To Test设置快捷键
	}
} 
