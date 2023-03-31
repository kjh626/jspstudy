package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public interface MyService {
	// 반환 타입(String)을 ActionForward로 바꿔줌 , path와 true,false를 반환할 수 있게끔
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response);
}
