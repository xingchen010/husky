package com.husky.exception;

public class QueryException extends Exception {

	private static final long serialVersionUID = 1L;

	public QueryException(String message) {
        super(message);
    }
/*	private final String userErrMsg;
	private final ErrorCode errorCode;*/

	
	/*public QueryException(ErrorCode errorCode, String sysErrMsg, String userErrMsg) {
	        super(sysErrMsg);
	        this.userErrMsg = userErrMsg;
	        this.errorCode = errorCode;
	    }

	public QueryException(ErrorCode errorCode, String sysErrMsg, String userErrMsg, Throwable cause) {
	        super(sysErrMsg, cause);
	        this.userErrMsg = userErrMsg;
	        this.errorCode = errorCode;
	    }

	public QueryException(ErrorCode errorCode, String sysErrMsg) {
	        super(sysErrMsg);
	        this.userErrMsg = null;
	        this.errorCode = errorCode;
	    }

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public String getUserErrMsg() {
		return userErrMsg;
	}

	public String getSysErrMsg() {
		return getMessage();
	}*/
}
