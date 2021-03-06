package com.kh.farmapp.admin.model.dao;

import common.dto.Admin;

public interface AdminLoginDao {

	/**
	 * 입력한 아이디와 비밀번호로 Admin 조회
	 * 
	 * @param admin - 아이디와 비밀번호를 갖는 Admin 객체
	 * @return Admin - 조회 결과 반환 객체
	 */
	public Admin selectAdminByIdAndPw(Admin admin);

}
