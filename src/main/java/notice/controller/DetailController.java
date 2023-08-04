package notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class DetailController
 */
@WebServlet("/notice/detail.do")
public class DetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// SELECT * FROM NOTICE_TBL WHERE NOTICENO = ?
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		NoticeService service = new NoticeService();
		Notice notice = service.selectOneByNo(noticeNo);
		if(notice != null) {
			// 상세페이지로 이동 
			request.setAttribute("noticeOne", notice);
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/help/detail.jsp");
			view.forward(request, response);
		} else {
			// 실패페이지로 이동
			request.setAttribute("msg", "데이터가 존재하지 않습니다.");
			request.getRequestDispatcher("/WEB-INF/views/serviceFailed.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
