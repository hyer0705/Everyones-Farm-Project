package com.kh.farmapp.admin.user.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.farmapp.admin.model.service.AdminUserService;

import common.dto.Farmer;
import common.dto.UserTB;
import common.util.AdminPaging;

@Controller
public class AdminUserController {

	// log 를 남기기 위한 Logger 객체
	private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);
	
	// service 객체
	@Autowired
	private AdminUserService adminUserService;
	
	// 농업인 회원 관리 페이지
	@RequestMapping(value = "/adminmember/farmerlist", method = RequestMethod.GET)
	public String adminFarmerList() {
		return "";
	}
	
	// 일반 회원 관리 페이지
	@RequestMapping(value = "/adminmember/userlist", method = RequestMethod.GET)
	public String adminUserList(
				Model model
				// 페이징 처리시 필요한 변수
				, @RequestParam(defaultValue = "0") String curPage
				// 검색 처리시 필요한 변수
				, @RequestParam(defaultValue = "") String search
			) {
		
		// logger 찍기 - 해당 url에 들어왔다는 표시
		logger.info("/adminmember/userlist [GET] 요청");
		
		logger.debug("search: " + search);
		
		// 페이징 설정
		// 페이징 설정
		AdminPaging apaging = adminUserService.getPaging(curPage, search);
		logger.debug("apaging: " + apaging.toString());
		
		// select 조회 연산 수행
		List<Map<String, Object>> userList = adminUserService.selectAllUserByPaging(apaging);
		for(Map< String, Object> m : userList) {
			logger.debug("m: " + m.toString());
		}
		
		// model 값 넘겨주기
		// 페이징 객체 넘기기
		if( apaging != null) {
			model.addAttribute("apaging", apaging);
		}
		// 페이징 처리된 목록 넘기기
		if( userList != null ) {
			model.addAttribute("userList", userList);
		}
		
		return "admin/member/admin_user_list";
	}
	
	// 농업인 회원 신청 관리 페이지
	@RequestMapping(value = "/adminmember/fapplicationlist", method = RequestMethod.GET)
	public String adminFarmerApplicationList() {
		return "";
	}
	
	// 농업인 회원 신청 관리 상세 페이지
	@RequestMapping(value = "/adminmember/fapplicationdetail", method = RequestMethod.GET)
	public String adminFarmerApplicationDetail() {
		return "";
	}
	
	// 농업인 회원 신청 승인
	@RequestMapping(value = "/adminmember/approvefapplication", method = RequestMethod.POST)
	public void approveFarmerApplication(Farmer farmer) {
	}
	
	// 농업인 회원 신청 보류
	@RequestMapping(value = "/adminmember/putfapplicationonhold", method = RequestMethod.POST)
	public void putFarmerApplicationOnHold(Farmer farmer) {
	}
	
	// 일반 회원 정지
	@RequestMapping(value = "/adminmember/pauseuser", method = RequestMethod.POST)
	public void pauseUser(UserTB user) {
	}
	
	// 일반 회원 탈퇴
	@RequestMapping(value = "/adminmember/deleteuser", method = RequestMethod.POST)
	@ResponseBody
	public int deleteUser(
				// 탈퇴할 번호들
				@RequestParam Map<String, Object> deleteNums
			) {
		
		// 현재 어떤 url 이 실행되고 있는지
		logger.info("/adminmember/deleteuser [POST] 요청");
		
		// ajax 반환 값
		int result = 0;
		
		// userNo -> ArrayList 로 변환
		String[] userNums = deleteNums.get("userNo").toString().split(",");
		List<String> userNoList = new ArrayList<>(Arrays.asList(userNums));
		logger.debug("userNoList: " + userNoList.toString());
		
		// 삭제 처리
		int delRes = adminUserService.deleteUserByUserNo(userNoList);
		
		// 삭제 성공
		if( delRes >= 1 ) {
			
			logger.info("탈퇴 성공~");
			result = 1;
			return result;
			
		} else { // 삭제 결과 실패
			
			logger.info("탈퇴 실패...");
			return result;
			
		}
	}
	// 일반 회원 탈퇴 취소
	@RequestMapping(value = "/adminmember/delcanceluser")
	@ResponseBody
	public int deleteCancelUser(
			// 삭제할 번호들
			@RequestParam Map<String, Object> cancelNums
			) {
		
		// 현재 어떤 url 이 실행되고 있는지
		logger.info("/adminmember/delcanceluser [POST] 요청");
		
		int result = 0;
		
		// userNo -> ArrayList 로 변환
		String[] userNums = cancelNums.get("userNo").toString().split(",");
		List<String> userNoList = new ArrayList<>(Arrays.asList(userNums));
		logger.debug("userNoList: " + userNoList.toString());
		
		int cancelRes = adminUserService.delCancelUserByUserNo(userNoList);
		logger.debug("cancelRes: " + cancelRes);
		
		if(cancelRes >= 1) {
			
			logger.info("회원 탈퇴 취소 성공~");
			result = 1;
			return result;
		} else {
			
			logger.info("회원 탈퇴 취소 실패...");
			return result;
		}
	}
	
	// 농업인 회원 정지
	@RequestMapping(value = "/adminmember/pausefarmer", method = RequestMethod.POST)
	public void pauseFarmer(Farmer farmer) {
	}
	
	// 농업인 회원 탈퇴
	@RequestMapping(value = "/adminmember/deletefarmer", method = RequestMethod.POST)
	public void deleteFarmer(Farmer farmer) {
	}
	
}
