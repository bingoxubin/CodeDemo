package com.bingoabin.algorithm.math;

/**
 * @Author: xubin34
 * @Date: 2021/9/30 10:00 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class ComputeArea {
	//Leetcode 223. 矩形面积
	//示例：输入：ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2   输出：45
	//分析：给出坐标系中的4个点，分别是左下 右上点 表示一个矩形  另外一个也是表示左上 右上点的矩形 求两个矩形的覆盖总面积
	//思路：矩形覆盖总面积 = 两个矩形的总面积 - 相交的面积；两个矩形的水平边投影到x轴上的线段分别是[ax1,ax2],[bx1,bx2]，如果两个矩形重叠，重叠部分的水平边投影到x轴上的线段为[max(ax1,bx1)，min(,bx2)]
	//     根据重叠部分的水平边投影到轴上的长度计算重叠的面积，且只有当两条线段的长度都大于0时，重叠部分的面积才大于0，否则就是0
	public static void main(String[] args) {
		ComputeArea computeArea = new ComputeArea();
		System.out.println(computeArea.computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
		System.out.println(computeArea.computeArea1(-3, 0, 3, 4, 0, -1, 9, 2));
	}

	public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
		int area1 = (ax2 - ax1) * (ay2 - ay1), area2 = (bx2 - bx1) * (by2 - by1);
		int overlapWidth = Math.min(ax2, bx2) - Math.max(ax1, bx1), overlapHeight = Math.min(ay2, by2) - Math.max(ay1, by1);
		int overlapArea = Math.max(overlapWidth, 0) * Math.max(overlapHeight, 0);
		return area1 + area2 - overlapArea;
	}

	//自己实现一遍
	public int computeArea1(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
		//求出第一个矩形的面积
		int area1 = (ax2 - ax1) * (ay2 - ay1);
		//求出第二个矩形的面积
		int area2 = (bx2 - bx1) * (by2 - by1);
		//求出相交的长
		int overLength = Math.min(ax2, bx2) - Math.max(ax1, bx1);
		//求出相交的宽
		int overHeight = Math.min(ay2, by2) - Math.max(ay1, by1);
		//求出相交的面积
		int overArea = Math.max(overHeight, 0) * Math.max(overLength, 0);
		//得到最终的面积
		return area1 + area2 - overArea;
	}
}
