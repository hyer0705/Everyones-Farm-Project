package com.kh.farmapp.admin.model.dao;

import java.util.List;
import java.util.Map;

import common.dto.AnsweredOneonone;
import common.dto.QuestionOneonone;
import common.util.AdminPaging;

/**
 * 1대1문의 관리 페이지의 Dao interface
 */
public interface AdminOneOnOneDao {

	/**
	 * 일반 회원 1대1 문의 목록 조회
	 * @param apaging - paging 정보를 갖는 AdminPaging 객체
	 * @return List<Map<String, Object>> - 조회 결과 반환
	 */
	public List<Map<String, Object>> selectAllUserOneOnOneList(AdminPaging apaging);

	/**
	 * 농업인 회원 1대1 문의 목록 조회
	 * @param apaging - 페이징 정보를 갖는 AdminPaging 객체
	 * @return List<Map<String, Object>> - 조회 결과 반환
	 */
	public List<Map<String, Object>> selectAllFarmerOneOnOneList(AdminPaging apaging);

	/**
	 * 일반 회원 1대1 문의 상세 조회
	 * @param qNo - 상세 조회될 질문에 대한 정보를 갖는 QuestionOneonone 객체
	 * @return Map<String, Object> - 조회 결과 반환
	 */
	public Map<String, Object> selectUserOneOnOneByNo(QuestionOneonone qNo);

	/**
	 * 농업인 회원 1대1 문의 상세 조회
	 * @param qNo - 상세 조회될 질문에 대한 정보를 갖는 QuestionOneonone 객체
	 * @return Map<String, Object> - 조회 결과 반환
	 */	
	public Map<String, Object> selectFarmerOneOnOneByNo(QuestionOneonone qNo);

	/**
	 * 일반 회원의 문의에 답변 작성하기
	 * @param a - 작성한 답변에 대한 정보를 갖는 AnsweredOneOnOne 객체
	 * @return int - 작성 결과(insert 결과)
	 */
	public int insertAnswerToUser(AnsweredOneonone a);

	/**
	 * 농업인 회원의 문의에 답변 작성하기
	 * @param a - 작성한 답변에 대한 정보를 갖는 AnsweredOneOnOne 객체
	 * @return int - 작성 결과(insert 결과)
	 */
	public int insertAnswerToFarmer(AnsweredOneonone a);

	/**
	 * 일반 회원의 문의에 작성한 답변 수정하기
	 * @param a - 수정한 답변에 대한 정보를 갖는 AnsweredOneOnOne 객체
	 * @return int - 수정 결과(update 결과)
	 */
	public int updateAnswer(AnsweredOneonone a);

	/**
	 * 농업인 회원의문의에 작성한 답변 수정하기
	 * @param a - 수정할 답변에 대한 정보를 갖는 AnsweredOneOnOne 객체
	 * @return int - 수정 결과(update 결과)
	 */
	public int updateAnswerToFarmer(AnsweredOneonone a);

	/**
	 * 일반 회원의 문의 목록 총 갯수 조회
	 * @param search - 검색어
	 * @return int - 조회 결과 반환
	 */
	public int selectCntAllUserOneOnOne(String search);

	/**
	 * 답변 조회
	 * 
	 * @param q - questionNo 정보를 갖는 QuestionOneonone 객체
	 * @return Map<String, Object> - 조회 결과 반환
	 */
	public Map<String, Object> selectAnswerOneOnOneByQuestionNo(QuestionOneonone q);

	/**
	 * 답변 삭제
	 * 
	 * @param deleteAnswer - 삭제할 답변 정보를 갖는 AnsweredOneonone 객체
	 * @return int - delete 결과
	 */
	public int deleteAnswer(AnsweredOneonone deleteAnswer);

	/**
	 * 답변 상태 업데이트
	 * 
	 * @param answer - 답변 상태를 업데이트할 문의글 정보를 갖는 AnsweredOneonone 객체
	 * @return int - update 결과 반환
	 */
	public int updateAnswerStateByQuestionNo(AnsweredOneonone answer);

	/**
	 * 답변 상태 업데이트 - 답변 대기로
	 * 
	 * @param deleteAnswer - 답변 상태를 업데이트할 문의글 정보를 갖는 AnsweredOneonone 객체
	 * @return int - update 결과 반환
	 */
	public int updateWaitAnswerByQuestionNo(AnsweredOneonone deleteAnswer);

	/**
	 * farmer 회원이 작성한 문의글만 조회하기
	 * 
	 * @param search - 검색어
	 * @return int - 조회 결과 반환(count)
	 */
	public int selectCntAllFarmerOneOnOne(String search);

}
