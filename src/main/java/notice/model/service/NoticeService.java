package notice.model.service;

import java.sql.Connection;
import java.util.List;

import common.JDBCTemplate;
import notice.model.dao.NoticeDAO;
import notice.model.vo.Notice;
import notice.model.vo.PageData;

public class NoticeService {
	
	private NoticeDAO nDao;
	private JDBCTemplate jdbcTemplate;
	
	public NoticeService() {
		nDao = new NoticeDAO();
		// jdbcTemplate = new JDBCTemplate(); // 싱글톤패턴 적용해서 생성자 private라 못만든다! 
		jdbcTemplate = JDBCTemplate.getInstance(); // 이렇게 객체 만들어줌 ! 
	}
	
	// 글작성 
	public int insertNotice(Notice notice) {
		Connection conn = jdbcTemplate.createConnection();
		int result = nDao.insertNotice(conn, notice);
		if(result > 0) {
			jdbcTemplate.commit(conn);
		} else {
			jdbcTemplate.rollback(conn);
		} 
		jdbcTemplate.close(conn);
		return result;
	}
	
	// 공지사항 전체 목록 조회
	public PageData selectNoticeList(int currentPage) {
		Connection conn = jdbcTemplate.createConnection();
		List<Notice> nList = nDao.selectNoticeList(conn, currentPage);
		String pageNavi = nDao.generatePageNavi(currentPage);
		// 1. Map이용
		// 2. VO클래스 이용
		PageData pd = new PageData(nList, pageNavi);
		jdbcTemplate.close(conn);
		return pd;
	}
	
	// 공지사항 상세 내용 조회
	public Notice selectOneByNo(int noticeNo) {
		Connection conn = jdbcTemplate.createConnection();
		Notice notice = nDao.selectOneByNo(conn, noticeNo);
		jdbcTemplate.close(conn);
		return notice;
	}



}
