package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IMemberService {
	// ActionForward가 없기 때문에 반환타입 없다
	// ajax 는 PrintWriter를 써야 되는데 예외 처리를 여기서 해주겠다.
	// execute메소드 안에서는 try, catch 안 한다
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
