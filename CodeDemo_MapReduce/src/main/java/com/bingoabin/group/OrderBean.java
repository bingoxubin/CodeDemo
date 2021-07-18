package com.bingoabin.group;

/**
 * @author xubin34
 * @date 2021/7/18 9:46 下午
 */
//自定义OrderBean对象
//案例
//需求：求取每个订单当中金额最大的商品
// 订单id         	  商品id  	 成交金额
// 	Order_0000001	   Pdt_01	     222.8
// 	Order_0000001	   Pdt_05	      25.8
// 	Order_0000002	   Pdt_03	     522.8
// 	Order_0000002	   Pdt_04	     122.4
// 	Order_0000002	   Pdt_05	     722.4
// 	Order_0000003	   Pdt_01	     222.8
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OrderBean implements WritableComparable<OrderBean> {
	private String orderId;
	private Double price;

	//key间的比较规则
	@Override
	public int compareTo(OrderBean o) {
		//注意：如果是不同的订单之间，金额不需要排序，没有可比性
		int orderIdCompare = this.orderId.compareTo(o.orderId);
		if (orderIdCompare == 0) {
			//比较金额，按照金额进行倒序排序
			int priceCompare = this.price.compareTo(o.price);
			return -priceCompare;
		} else {
			//如果订单号不同，没有可比性，直接返回订单号的升序排序即可
			return orderIdCompare;
		}
	}

	//序列化方法
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(orderId);
		out.writeDouble(price);
	}

	//反序列化方法
	@Override
	public void readFields(DataInput in) throws IOException {
		this.orderId = in.readUTF();
		this.price = in.readDouble();
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return orderId + "\t" + price;
	}
}
