package member.model.service;

import java.sql.Connection;

import common.JDBCTemplate;
import member.model.dao.MemberDAO;
import member.model.vo.Member;

public class MemberService {
	
	private MemberDAO mDao;
	
	public MemberService() {
		mDao = new MemberDAO();
	}

}
