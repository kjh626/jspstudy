package ex07_ajax_practice;

public class MyHandleException extends Exception {
	private static final long serialVersionUID = 1663883917739002682L;
	
	private int errorCode;
	
	public MyHandleException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
