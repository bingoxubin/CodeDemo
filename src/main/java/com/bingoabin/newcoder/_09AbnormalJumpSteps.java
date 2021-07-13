//变态跳台阶
//一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。

//思路：
// 		0:0
// 		1:(1)
// 		2:(1,1)(2)
// 		3:(1,1,1)(2,1)(1,2)(3)
// 		4:(1,1,1,1)(2,1,1)(1,2,1)(3,1)(1,1,2)(1,3)(2,2)(4)
//
// 		显然，除了0，其他都是2^(n-1);
// 		OJ并未检查小于等于0的情况，所以也可将该界外判断去掉。
//
// 		n级台阶，第一步有n种跳法：1,2,3，...,n
// 		跳1级，剩下的有F(n-1)种。
// 		跳2级，剩下的有F(n-2)种。
// 		...
// 		跳n级，剩下的有F(0)=1种。
// 		所以F(n)=F(n-1)+F(n-2)+...+F(0)
// 		因为F(n-1)=F(n-2)+F(n-3)+...+F(0)
// 		所以F(n)=2*F(n-1)

package com.bingoabin.newcoder;

public class _09AbnormalJumpSteps {
	public class Solution {
		public int JumpFloorII(int target) {
			// 1 2 4 8
			int result = 1;
			if (target == 1) {
				return 1;
			}
			for (int i = 2; i <= target; i++) {
				result = result * 2;
			}
			return result;
		}
	}

	public class Solution1 {
		public int JumpFloorII(int target) {
			if (target <= 0) {
				return 0;
			}
			int temp = 1;
			while (target >= 2) {
				temp *= 2;
				target--;
			}
			return temp;
		}
	}

	public class Solution2 {
		public int JumpFloorII(int target) {
			if (target <= 0) {
				return 0;
			}
			return (int) Math.pow(2, target - 1);
		}
	}

	public class Solution3 {
		public int JumpFloorII(int target) {
			if (target <= 0) {
				return 0;
			}
			return 1 << (target - 1);
		}
	}

	public class Solution4 {
		public int JumpFloorII(int target) {
			if (target <= 0) {
				return 0;
			} else if (target == 1) {
				return 1;
			}
			return 2 * JumpFloorII(target - 1);
		}
	}

	public class Solution5 {
		public int JumpFloorII(int target) {
			int result = 0;
			if (target <= 0) {
				return 0;
			} else if (target <= 2) {
				return target;
			} else {
				for (int i = 3; i < target; i++) {
					result = result + JumpFloorII(i);
				}
				return result + 1;
			}

		}
	}
}
