package com.kh.farmapp.admin.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.farmapp.admin.model.dao.AdminOneOnOneDao;

import common.dto.AnsweredOneonone;
import common.dto.QuestionOneonone;
import common.util.AdminPaging;
/**
 * AdminOneOnOneService 를 상속 받는 클래스
 * AdminOneOnOneService 구현 하는 클래스
 */
@Service
public class AdminOneOnOneServiceImpl implements AdminOneOnOneService{

	// member field
	private static final int USER_CODE = 1;
	
	@Autowired
	private AdminOneOnOneDao adminOneOnOneDao;
	
	// 일반 회원 문의 목록 조회
	@Override
	public List<Map<String, Object>> selectAllUserOneOnOneList(AdminPaging apaging) {
		return adminOneOnOneDao.selectAllUserOneOnOneList(apaging);
	}

	@Override
	public List<Map<String, Object>> selectAllFarmerOneOnOneList() {
		return adminOneOnOneDao.selectAllFarmerOneOnOneList();
	}

	@Override
	public Map<String, Object> selectUserOneOnOneByNo(QuestionOneonone qNo) {
		return adminOneOnOneDao.selectUserOneOnOneByNo(qNo);
	}

	@Override
	public List<Map<String, Object>> selectFarmerOneOnOneByNo(QuestionOneonone qNo) {
		return adminOneOnOneDao.selectFarmerOneOnOneByNo(qNo);
	}

	@Override
	public int writeAnswerToUser(AnsweredOneonone a) {
		return adminOneOnOneDao.insertAnswerToUser(a);
	}

	@Override
	public int writeAnswerToFarmer(AnsweredOneonone a) {
		return adminOneOnOneDao.insertAnswerToFarmer(a);
	}

	@Override
	public int updateAnswerToUser(AnsweredOneonone a) {
		return adminOneOnOneDao.updateAnswertToUser(a);
	}

	@Override
	public int updateAnswerToFarmer(AnsweredOneonone a) {
		return adminOneOnOneDao.updateAnswerToFarmer(a);
	}

	@Override
	public int deleteAnswerToUserByNo(AnsweredOneonone aNo) {
		return adminOneOnOneDao.deleteAnswerToUserByNo(aNo);
	}

	@Override
	public int deleteAnswerToFarmerByNo(AnsweredOneonone aNo) {
		return adminOneOnOneDao.deleteAnswerToFarmerByNo(aNo);
	}
	
	// 페이징 설정
	@Override
	public AdminPaging getPaging(Map<String, Object> pagingConfig) {
		
		// 현재 페이지
		String curPage = (String) pagingConfig.get("curPage");
		// 검색어
		String search = (String) pagingConfig.get("search");
		// 일반 회원 문의 목록을 조회할 지 농업인 회원 문의 목록을 조회할 지에 대한 코드
		int listCode = (int) pagingConfig.get("listCode");
		
		// curPage 초기화, curPageNo 은 현재 페이지 번호를 뜻함!
		int curPageNo = 0;
		if( curPage != null && !"".equals(curPage) ) {
			curPageNo = Integer.parseInt(curPage);
		}
		
		// 문의 테이블의 총 게시글 수를 조회
		int totalCount = 0;
		switch (listCode) {
		case USER_CODE:
			totalCount = adminOneOnOneDao.selectCntAllUserOneOnOne(search);
			break;
		}
		
		// AdminPaging 객체 생성 - 현재 페이지(curPage), 총 게시글 수 (totalCount) 활용
		AdminPaging paging = new AdminPaging(totalCount, curPageNo);
		// 검색어 AdminPaging 객체에 넣어주기
		paging.setSearch(search);
		
		return paging;
	}

}
