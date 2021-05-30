package com.bingoabin.algorithm.queue;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author xubin03
 * @date 2021/5/30 10:19 下午
 */
public class TaskComplete {
	//Leetcode 5774. 使用服务器处理任务
	//样例：servers = [3,3,2], tasks = [1,2,3,2,1,2]  返回 [2,2,0,2,1,2]
	//分析：有服务数组，表示服务的优先级，数据小的服务先分配服务（如果优先级相同，先分配下标小的），任务表示任务需要执行的时间，返回的列表的是对应任务分配到的服务节点下标。
	//思路：1.创建一个空闲服务队列，放入int[]{servers[index]，index},按定制化排序；
	//     2.然后创建一个服务器运行队列，放入int[]{servers[index],index,completetime},按完成时间定制化排序
	//     3.将服务器信息存入到空闲服务队列
	//     4.遍历任务，如果运行服务器队列中有完成的服务器信息，弹出，放到空闲里面
	//     5.如果空闲服务队列不为空，开始执行任务
	//     6.如果如果空闲服务为空，跳到运行运行服务器中第一个运行完的前一秒，继续运行
	public static void main(String[] args) {
		int[] servers = {3, 3, 2};
		int[] tasks = {1, 2, 3, 2, 1, 2};
		System.out.println(Arrays.toString(assignTasks(servers, tasks)));
	}

	public static int[] assignTasks(int[] servers, int[] tasks) {
		//空闲服务器队列
		PriorityQueue<int[]> unused = new PriorityQueue<>((a, b) -> {
			if (a[0] == b[0]) {
				return a[1] - b[1];
			} else {
				return a[0] - b[0];
			}
		});
		//将servers放入空闲服务队列
		for (int i = 0; i < servers.length; i++) {
			unused.offer(new int[]{servers[i], i});
		}

		//使用中的服务器队列
		PriorityQueue<int[]> using = new PriorityQueue<>((a, b) -> a[2] - b[2]);

		//创建结果集
		int[] res = new int[tasks.length];
		//创建任务号
		int index = 0;
		for (int i = 0; index < tasks.length; i++) { //注意此处为index
			//如果已经使用的服务队列中，已经有完成的，从已经使用的服务队列移除，加入到空闲队列中
			while (!using.isEmpty() && using.peek()[2] <= i) {
				int[] temp = using.poll();
				unused.add(new int[]{temp[0], temp[1]});
			}
			//如果空闲队列不为空，将任务加入到队列中，进行执行
			while (!unused.isEmpty() && index < tasks.length && index <= i) {
				int[] temp = unused.poll();
				using.offer(new int[]{temp[0], temp[1], i + tasks[index]});  //i + tasks[index] 结束时间
				res[index++] = temp[1];
			}
			//如果空闲队列为空，那么本来应该等待，那么不等待，将时刻调整到运行服务器中第一个运行完的前一秒
			if (unused.isEmpty()) {
				int[] temp = using.poll();
				i = temp[2] - 1;
				unused.add(new int[]{temp[0], temp[1]});
			}
		}
		return res;
	}
}
