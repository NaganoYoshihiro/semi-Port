package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RoomResult")
public class RoomResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RoomResult() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String nobox = request.getParameter("no");

	    boolean isRoom = execute(nobox);

	    // マッチ成功時の処理
	    if (isRoom) {
	    	request.setAttribute("no", nobox);
		    RequestDispatcher dispatcher = request.getRequestDispatcher ("/Main");
			dispatcher.forward(request, response);
	    }else {
	    	String noRoom = "true";
	    	request.setAttribute("noRoom", noRoom);
		    RequestDispatcher dispatcher = request.getRequestDispatcher ("/index.jsp");
			dispatcher.forward(request, response);
	    }
	}
	/**
	 * 部屋があるか判定
	 *
	 * @param nobox
	 * @return boolean
	 */
	  public static boolean execute(String nobox) {
		    if (nobox.equals("1111")) {
		      return true;
		    }
		    return false;
	  }
}
