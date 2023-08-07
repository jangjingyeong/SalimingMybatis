package member.model.dao;


import org.apache.ibatis.session.SqlSession;

import member.model.vo.Member;

public class MemberDAO {

	public int insertMember(SqlSession session, Member member) {
		int result = session.insert("insertMember", member);
		return result;
	}
	
	public int updateMember(SqlSession session, Member member) {
		int result = session.update("updateMember", member);
		return result;
	}
	
	public int deleteMember(SqlSession session, String memberId) {
		int result = session.delete("deleteMember", memberId);
		return result;
	}

	public Member selectCheckLogin(SqlSession session, Member member) {
		Member mOne = session.selectOne("selectCheckLogin", member);
		return mOne;
	}

	public Member selectOneById(SqlSession session, String memberId) {
		Member member = session.selectOne("selectOneById", memberId);
		return member;
	}

}
