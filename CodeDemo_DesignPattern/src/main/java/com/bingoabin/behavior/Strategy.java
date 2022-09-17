package com.bingoabin.behavior;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author bingoabin
 * @date 2021/6/15 1:41
 */
public class Strategy {
	//策略模式：定义一组算法，将每个算法都封装起来，并且使他们之间可以互换。策略模式让算法独立于使用它的客户而变化，也称为政策模式 -- 跟状态模式很像
	//比如 洗衣机的 标准 浸洗 快洗 大物等

	//例如ThreadPoolExecutor构造方法中的拒绝策略
	// public static void main(String[] args) {
	// 	new ThreadPoolExecutor(2, 5, 1000, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2), new ThreadPoolExecutor.AbortPolicy());
	// }
}

interface IFileStragegy{
	//属于那种文件解析类型
	FileTypeResolveEnum getFileType();

	//具体处理方法
	void resolve(Object objectParam);
}

@Component
class AFileResolve implements IFileStragegy {
	private final Logger logger = LoggerFactory.getLogger(AFileResolve.class);
	@Override
	public FileTypeResolveEnum getFileType() {
		return FileTypeResolveEnum.FILE_A_RESOLVE;
	}

	@Override
	public void resolve(Object objectParam) {
		logger.info("A 逻辑");
	}
}

@Component
class BFileResolve implements IFileStragegy {
	private final Logger logger = LoggerFactory.getLogger(BFileResolve.class);
	@Override
	public FileTypeResolveEnum getFileType() {
		return FileTypeResolveEnum.FILE_B_RESOLVE;
	}

	@Override
	public void resolve(Object objectParam) {
		logger.info("B 逻辑");
	}
}

@Component
class StrategyUserService implements ApplicationContextAware {
	private final Map<FileTypeResolveEnum, IFileStragegy> iFileStragegyMap = new ConcurrentHashMap<>();

	public void resolveFile(FileTypeResolveEnum fileTypeResolveEnum, Object objectParams) {
		IFileStragegy iFileStragegy = iFileStragegyMap.get(fileTypeResolveEnum);
		if (iFileStragegy != null) {
			iFileStragegy.resolve(objectParams);
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Map<String, IFileStragegy> tempMap = applicationContext.getBeansOfType(IFileStragegy.class);
		tempMap.values().forEach(strategyService -> iFileStragegyMap.put(strategyService.getFileType(), strategyService));
	}
}

enum FileTypeResolveEnum{
	FILE_A_RESOLVE(1, "a"),
	FILE_B_RESOLVE(2, "b"),
	;

	FileTypeResolveEnum(Integer id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	private Integer id;
	private String desc;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}