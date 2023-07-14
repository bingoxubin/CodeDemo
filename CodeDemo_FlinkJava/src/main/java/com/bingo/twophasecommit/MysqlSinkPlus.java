package com.bingo.twophasecommit;

import com.bingo.util.HikariUtil;
import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.typeutils.base.VoidSerializer;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.typeutils.runtime.kryo.KryoSerializer;
import org.apache.flink.streaming.api.functions.sink.TwoPhaseCommitSinkFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.Timestamp;

/**
 * @author bingoabin
 * @date 2023/7/15 1:10
 * @Description:
 */
public class MysqlSinkPlus extends TwoPhaseCommitSinkFunction<Tuple2<String, Integer>, HikariUtil, Void> {
	private static final Logger log = LoggerFactory.getLogger(MysqlSinkPlus.class);

	public MysqlSinkPlus() {
		super(new KryoSerializer<>(HikariUtil.class, new ExecutionConfig()), VoidSerializer.INSTANCE);
	}

	/**
	 * 执行数据库入库操作  task初始化的时候调用
	 *
	 * @param hikariUtil
	 * @param tuple
	 * @param context
	 * @throws Exception
	 */
	@Override
	protected void invoke(HikariUtil hikariUtil, Tuple2<String, Integer> tuple, Context context) throws Exception {
		log.info("start invoke...");
		String value = tuple.f0;
		Integer total = tuple.f1;
		String sql = "insert into `t_test` (`value`,`total`,`insert_time`) values (?,?,?)";
		log.info("====执行SQL:{}===", sql);
		PreparedStatement ps = hikariUtil.getconn().prepareStatement(sql);
		ps.setString(1, value);
		ps.setInt(2, total);
		ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
		log.info("要插入的数据:{}----{}", value, total);
		if (ps != null) {
			String sqlStr = ps.toString().substring(ps.toString().indexOf(":") + 2);
			log.error("执行的SQL语句:{}", sqlStr);
		}
		//执行insert语句
		ps.execute();
	}

	/**
	 * 获取连接，开启手动提交事物（getConnection方法中）
	 *
	 * @return
	 * @throws Exception
	 */
	@Override
	protected HikariUtil beginTransaction() throws Exception {
		log.info("start beginTransaction.......");
		return new HikariUtil();
	}

	/**
	 * 预提交，这里预提交的逻辑在invoke方法中
	 *
	 * @param hikariUtil
	 * @throws Exception
	 */
	@Override
	protected void preCommit(HikariUtil hikariUtil) throws Exception {
		log.info("start preCommit...");
	}

	/**
	 * 如果invoke方法执行正常，则提交事务
	 *
	 * @param hikariUtil
	 */
	@Override
	protected void commit(HikariUtil hikariUtil) {
		log.info("start commit...");
		hikariUtil.commit();
	}

	/**
	 * 如果invoke执行异常则回滚事物，下一次的checkpoint操作也不会执行
	 *
	 * @param hikariUtil
	 */
	@Override
	protected void abort(HikariUtil hikariUtil) {
		log.info("start abort rollback...");
		hikariUtil.rollback();
	}
}
