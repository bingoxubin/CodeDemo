package com.bingoabin.algorithm.sort;

import java.util.Arrays;

/**
 * @author bingoabin
 * @date 2021/6/12 2:12
 */
public class HeapSort {
	//堆排序
	//将一个数组看成一个完全二叉树，然后从一半位置开始先调整数组，为大根堆的样子，然后再次进行遍历，将头上的点放到数组最后，是最大值，然后不断交换调整堆
	// 10, 9, 8, 6, 5, 7, 1, 4, 2, 3
	//                  10
	//              9        8
	//          6     5    7    1
	//       4    2  3
	//比如上面的数组，先从5数字开始往上遍历  不断调整堆，然后将最上面的值，放到数组最后，然后不断调整堆即可
	public static void main(String[] args) {
		int[] arr = {4, 3, 5, 7, 2, 1, 8, 9, 0};
		heapSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static void heapSort(int[] arr) {
		//先调整整个堆的形状，从中间开始往上调整
		for (int i = (arr.length - 1) / 2; i >= 0; i--) {
			adjustHeap(arr, i, arr.length);
		}

		//将最上面的值跟最后的值进行交换，然后不断缩小最后的范围
		for (int i = arr.length - 1; i >= 0; i--) {
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			adjustHeap(arr, 0, i);
		}
	}

	//先记录下parent节点的值，然后定义左边的值2parent +1 找到右子节点的值，下面2个最大的定义为lchild，如果lchild<parent，那么break，否则，arr[parent] = arr[child]
	//然后向下调整，parent = lchild，并且lchild = 2 * lchild + 1，最终将temp给parent  因为parent已经改为lchild了，赋值即可
	public static void adjustHeap(int[] arr, int parent, int end) {
		int temp = arr[parent];
		int lchild = 2 * parent + 1;
		while(lchild < end){
			int rchild = lchild + 1;
			if(rchild < end && arr[lchild] < arr[rchild]){
				lchild ++;
			}
			if(arr[lchild] < temp) break;

		}
	}
}
