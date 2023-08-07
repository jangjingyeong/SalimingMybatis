package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/member/update.do")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String memberId = request.getParameter("id"); 
		String memberPw = request.getParameter("pw");
		String memberPw2 = request.getParameter("pw2");
		String memberNickname = request.getParameter("nickname");
		String memberPhone = request.getParameter("tel");
		String memberEmail = request.getParameter("email");
		String memberAddress = 
				request.getParameter("postcode") 
				+ request.getParameter("address")
				+ request.getParameter("detailAddress");
		Member member = new Member(memberId, memberPw, memberPw2, memberNickname, memberPhone, memberEmail, memberAddress);
		// UPDATE MEMBER_TBL SET MEMBER_PW = ?, MEMBER_EMAIL = ?, MEMBER_PHONE = ?, MEMBER_ADDRESS = ?, MEMBER_HOBBY = ?, UPDATE_DATE = DEFAULT WHERE MEMBER_ID = ?
		MemberService service = new MemberService();
		int result = service.updateMember(member);
		if(result > 0) {
			// 성공하면 메인페이지
			request.getRequestDispatcher("/WEB-INF/views/member/mypage.jsp").forward(request, response);
		} else {
			// 실패하면 에러페이지
			request.setAttribute("msg", "회원 수정이 완료되지 않았습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/serviceFailed.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
