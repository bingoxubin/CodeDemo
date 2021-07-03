package com.bingoabin.designpattern.behavior;

/**
 * @author bingoabin
 * @date 2021/6/15 1:49
 */
public class VisitorPattern {
	//访问者模式：用于封装一些作用于某种数据结构中的各元素的操作，它可以在不改变数据结构的前提下定义作用于这些元素的新的操作
	//比如：通过软件包对系统进行升级，软件包中包含新的操作跟行为，里面包含了更多的指令，可以使得当前系统升级
	public static void main(String[] args) {
		EggRoot eggRoot = new EggRoot();
		eggRoot.calc();

		UpdateVisitor updateVisitor = new UpdateVisitor();
		eggRoot.accept(updateVisitor);
		eggRoot.calc();
	}
}

//抽象硬件类  需要更新的硬件
abstract class Hardware {
	String command;

	public Hardware(String command) {
		this.command = command;
	}

	public void run() {
		System.out.println(command);
	}

	public abstract void accept(Visitor visitor);
}

//具体硬件 CPU
class CPU extends Hardware {
	public CPU(String command) {
		super(command);
	}

	public void accept(Visitor visitor) {
		visitor.visitCPU(this);
	}
}

//具体硬件 磁盘
class HardDisk extends Hardware {
	public HardDisk(String command) {
		super(command);
	}

	public void accept(Visitor visitor) {
		visitor.visitDisk(this);
	}
}

//访问者接口
interface Visitor {
	void visitCPU(CPU cpu);

	void visitDisk(Hardware disk);
}

//具体的更新包
class UpdateVisitor implements Visitor {

	@Override
	public void visitCPU(CPU cpu) {
		cpu.command += ":1+1=2";
	}

	@Override
	public void visitDisk(Hardware disk) {
		disk.command += ":记下 ：1+1 = 2";
	}
}

//二代产品
class EggRoot {
	private HardDisk disk;
	private CPU cpu;

	public EggRoot() {
		disk = new HardDisk("记住 1+1 = 1");
		cpu = new CPU("1+1 = 1");
	}

	public void calc() {
		cpu.run();
		disk.run();
	}

	public void accept(Visitor visitor) {
		cpu.accept(visitor);
		disk.accept(visitor);
	}
}