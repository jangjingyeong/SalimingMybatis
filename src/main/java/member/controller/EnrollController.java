package member.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class EnrollController
 */
@WebServlet("/member/register.do")
public class EnrollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/enroll.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String memberId = request.getParameter("id");
		String memberPw = request.getParameter("pw");
		String memberPw2 = request.getParameter("pw2");
		String memberName = request.getParameter("name");
		String memberNickname = request.getParameter("nickname");
		String memberBirthday = request.getParameter("birth");
		String memberPhone = request.getParameter("tel");
		String memberEmail = request.getParameter("email");
		String memberAddress = 
				request.getParameter("postcode") 
				+ request.getParameter("address")
				+ request.getParameter("detailAddress");
		Member member = new Member(memberId, memberPw, memberPw2, memberName, memberNickname, memberBirthday, memberPhone, memberEmail, memberAddress);
		
		MemberService service = new MemberService();
		int result = service.insertMember(member);
		if(result > 0) {
			request.setAttribute("msg", "회원가입 성공");
			request.setAttribute("url", "/index.jsp");
			request.getRequestDispatcher("/WEB-INF/views/common/serviceSuccess.jsp")
			.forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/views/common/serviceFailed.jsp")
			.forward(request, response);
		}
	}


}
