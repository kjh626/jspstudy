package ex07_ajax;

public class AgeHandleException extends MyHandleException {
	// Exception 상속하면 클래스이름에 경고메시지가 뜬다. 직렬화클래스(Serialized Class)들은 시리얼ID 추가해줘야 한다.
	// default, generated 2가지 생성방법이 있다. 
	private static final long serialVersionUID = -2423336808588365970L;

	public AgeHandleException(String message, int errorCode) {  // 메시지, 응답코드 받아오게끔 생성자 하나 만들어줌
		super(message, errorCode);    // super클래스(=Exception)에 메시지 전달
	}
	
	
}
