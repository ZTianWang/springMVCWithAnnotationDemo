package com.wnwn.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		/*可有多个初始化参数*/
		initParams={@WebInitParam(name="test1",value="123"),@WebInitParam(name="test2",value="456")},
		/*可有多个urlPatterns*/
		urlPatterns={"/notetest","/test/notetest"}
		)
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*@Resource注解：需在web.xml中引入全局入口<env-entry>*/
	private @Resource(name="testEnv")String str;
	private StringBuilder sb;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req,resp);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		sb = new StringBuilder("initParams:");
		sb.append("<br/>test1->").append(getInitParameter("test1")).append("<br/>test2->").append(getInitParameter("test2")).append("<br/>testEnv->").append(str).append("<hr/>");
		req.setCharacterEncoding("UTF-8");
		Map<String, String[]> map = req.getParameterMap();
		/*迭代map*/
		Set<Entry<String, String[]>> set = map.entrySet();
		for (Entry<String, String[]> entry : set) {
			sb.append("<h3>").append(entry.getKey()).append("->").append(Arrays.toString(entry.getValue())).append("</h3>");
		}
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().write(sb.toString());
	}
}
