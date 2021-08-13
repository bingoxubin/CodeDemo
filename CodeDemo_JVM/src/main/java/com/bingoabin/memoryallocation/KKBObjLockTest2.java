package com.bingoabin.memoryallocation;

import org.openjdk.jol.info.ClassLayout;

public class KKBObjLockTest2 {
	public static void main(String[] args) {
		A a = new A();
		System.out.println("new A:" + ClassLayout.parseInstance(a).toPrintable());
		a.setFlag(true);
		a.setI(1);
		a.setStr("ABC");
		System.out.println("赋值 A:" + ClassLayout.parseInstance(a).toPrintable());
	}

	static class A {
		private boolean flag;
		private int i;
		private String str;

		public void setFlag(boolean flag) {
			this.flag = flag;
		}

		public void setStr(String str) {
			this.str = str;
		}

		public void setI(int i) {
			this.i = i;
		}
	}
}
