package ex07_ajax;

public class NameHandleException extends Exception {
	private static final long serialVersionUID = -1889650509552652714L;
	private int errorCode;
	
	public NameHandleException(String message, int errorCode) {
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
