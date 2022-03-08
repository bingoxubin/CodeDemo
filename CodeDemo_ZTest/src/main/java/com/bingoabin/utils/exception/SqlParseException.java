package com.bingoabin.utils.exception;

/**
 * @author ouzhenwei
 * @ClassName SqlParseException
 * @Description
 * @createTime 2021-07-29 17:35:47
 */
public class SqlParseException extends Exception {

    public SqlParseException(String message) {
        super(message);
    }

    public SqlParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
