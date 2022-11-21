package com.bingoabin.sort;

/**
 * @author bingoabin
 * @date 2022/11/8 22:50
 * @Description:
 */
public class Test {
	public static void main(String[] args){
		// Scanner sc = new Scanner(System.in);
		// int n = sc.nextInt();
		// int nums = sc.nextInt();
		// int[] arr = new int[n];
		// for(int i = 0;i<arr.length;i++){
		// 	arr[i] = sc.nextInt();
		// }
		// for(int i = 0;i<nums;i++){
		// 	int num = sc.nextInt();
		// 	if(getMinMax(arr,num) == -1 || getMaxMin(arr,num) == n){
		// 		System.out.printf("%d %d",-1,-1);
		// 	}else{
		// 		System.out.printf("%d %d",getMinMax(arr,num),getMaxMin(arr,num));
		// 	}
		// }

		int[] arr = {1, 3, 3, 4, 4, 6};
		System.out.println(getMinMax(arr, 7));
		System.out.println(getMaxMin(arr,-1));
	}

	//求小于等于num的最大下标
	public static int getMinMax(int[] arr,int num){
		int left = 0;
		int right = arr.length -1;
		int res = -1;
		while(left <= right){
			int mid = (right - left) /2 + left;
			if(arr[mid] <= num){
				res = mid;
				left = mid + 1;
			}else{
				right = mid - 1;
			}
		}
		return res;
	}

	//求大于等于num的最小下标
	public static int getMaxMin(int[] arr,int num){
		int left = 0;
		int right = arr.length -1 ;
		int res = -1;
		while(left <= right){
			int mid = (right - left) / 2 + left;
			if(arr[mid] >= num){
				res = mid;
				right = mid - 1;
			}else{
				left = mid + 1;
			}
		}
		return res;
	}
}
