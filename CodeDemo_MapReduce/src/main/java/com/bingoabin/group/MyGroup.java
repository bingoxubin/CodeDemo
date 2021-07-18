package com.bingoabin.group;

/**
 * @author xubin34
 * @date 2021/7/18 9:48 下午
 */
//自定义分组类
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
public class MyGroup extends WritableComparator {
	public MyGroup() {
		//分组类：要对OrderBean类型的k进行分组
		super(OrderBean.class, true);
	}
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		OrderBean a1 = (OrderBean) a;
		OrderBean b1 = (OrderBean) b;
		//需要将同一订单的kv作为一组
		return a1.getOrderId().compareTo(b1.getOrderId());
	}
}
