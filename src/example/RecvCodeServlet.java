package example;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.arnx.jsonic.JSON;

public class RecvCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(RecvCodeServlet.class.getName());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");
		log.warning("code=" + code);
		if (code != null){
			String param = "";
			param += "client_id=shuetsu512&";
			param += "client_secret=9d9e2b750d413ddcaddcd823fadf7460&";
			param += "redirect_uri=http://shuetsu512-yamareco.appspot.com/&";
			param += "code=" + code + "&";
			param += "grant_type=authorization_code";
			Map<?, ?> r = JSON.decode(Util.apiPost("oauth/access_token", param, null));
			HttpSession session = req.getSession(true);
			session.setAttribute("ACCESS_TOKEN", r.get("access_token"));
		}
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}

}
