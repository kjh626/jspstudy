package com.gdu.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.ex.common.ActionForward;
import com.gdu.ex.domain.ExDto;
import com.gdu.ex.repository.ExDao;

public class ExSaveService implements ExService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String exContent = request.getParameter("exContent");
		
		ExDto ex = new ExDto();
		ex.setExContent(exContent);
		
		int result = ExDao.getInstance().save(ex);
		
		// Insert 이후에는 redirect 하자!
		// redirect 경로 : mapping을 작성한다. ContextPath부터 시작하는 경로로 작성한다.
		return new ActionForward(request.getContextPath() + "/list.do", true);
		
	}

}
