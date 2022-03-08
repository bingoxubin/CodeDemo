package com.bingoabin.utils.exception;

import com.bingoabin.utils.constant.BizCodes;
import com.bingoabin.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.function.Supplier;

/**
 * @author HanJunsheng
 * @date 2021/7/16
 * @copyright sankuai.com
 */
public class BizException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(BizException.class);

    private final int code;
    private final String msg;

    public BizException(int code, String msg) {
        this(code, msg, true, null);
    }

    public BizException(int code, String msg, boolean trace, Exception e) {
        super(msg, e, true, trace);
        this.code = code;
        this.msg = msg;
    }

    public BizException(int code, Exception e) {
        this(code, e.getMessage(), true, e);
    }

    public BizException(int code, String msg, Exception e) {
        this(code, msg, true, e);
    }

    public BizException(BizCodes error, String msg) {
        this(error.getCode(), msg, true, null);
    }

    public BizException(BizCodes error) {
        this(error.getCode(), error.getName(), true, null);
    }

    public BizException(BizCodes error, String msg, Exception e) {
        this(error.getCode(), msg, e);
    }

    public static BizException check(Exception e) {
        if (e == null) {
            return new BizException(BizCodes.ERROR.getCode(), BizCodes.ERROR.getName(),
                    new NullPointerException("nothing to check"));
        }
        if (e instanceof BizException) {
            return ((BizException) e);
        }
        return new BizException(BizCodes.ERROR.getCode(), e.toString(), e);
    }

    public static BizException check(Exception e, Supplier<BizException> exGen) {
        if (e instanceof BizException) {
            return ((BizException) e);
        }
        return exGen.get();
    }

    public int getCode() {
        return code;
    }

    public BizException log(Object... args) {
        if (LOGGER.isInfoEnabled()) {
            if (args == null || args.length == 0) {
                LOGGER.info("code:[{}] msg[{}] no args", code, msg);
            } else if (args[args.length - 1] instanceof Exception) {
                LOGGER.info("code:[{}] msg[{}] args[{}] e[{}]",
                        code, msg,
                        JsonUtil.JSON.toJSONString(Arrays.asList(args).subList(0, args.length - 1)),
                        args[args.length - 1]);
            } else {
                LOGGER.info("code:[{}] msg[{}] args[{}]", code, msg, JsonUtil.JSON.toJSONString(args));
            }
        }
        return this;
    }
}
