package example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json; charset=utf-8");
		PrintWriter w = resp.getWriter();
		HttpSession session = req.getSession(true);
		String accessToken = (String)session.getAttribute("ACCESS_TOKEN");
		if (accessToken != null){
			w.print(Util.apiGet("getUserInfo/", accessToken));
		}else{
			w.print("{\"err\":1}");
		}
		w.close();
	}

}
