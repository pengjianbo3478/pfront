package com.gl365.app.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.gl365.app.common.HttpParamConstant;
public class BaseController {
	private HttpSession session;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public HttpSession getSession() {
		return session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	@ModelAttribute
	public void init(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		this.session = session;
		this.request = request;
		this.response = response;
	}

	/**
	 * @return 返回当前正在调用接口的用户ID
	 */
	public String getCurrentOperatorId() {
		Object operatorId = request.getSession().getAttribute(HttpParamConstant.Session.GL_APP_USER_ID);
		if (operatorId != null) {
			return operatorId.toString();
		}
		else {
			return null;
		}
	}
}
