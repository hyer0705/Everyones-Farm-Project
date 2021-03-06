package com.kh.farmapp.admin.user.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.executor.ReuseExecutor;
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

import common.dto.Admin;
import common.dto.Farmer;
import common.dto.UserTB;
import common.util.AdminPaging;
import common.util.Paging;

@Controller
public class AdminUserController {

	// log 를 남기기 위한 Logger 객체
	private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);
	
	// service 객체
	@Autowired
	private AdminUserService adminUserService;
	
	// pagingConfig 상수
	private static final int USER_CODE = 0;
	private static final int FARMER_CODE = 1;
	private static final int FARM_APPLICATION_CODE = 2;
	
	// 서버 url 알려주는 method
	private String getServerPath(
			HttpServletRequest req
			) {
		
		return req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
	}
	
	// 농업인 회원 관리 페이지
	@RequestMapping(value = "/adminmember/farmerlist", method = RequestMethod.GET)
	public String adminFarmerList(
			Model model
			// 페이징 처리시 필요한 변수
			, @RequestParam(defaultValue = "0") String curPage
			// 검색 처리시 필요한 변수
			, @RequestParam(defaultValue = "") String search
			, HttpSession session
			) {
		
		// admin login이 안되어 있을 때
		Admin loginAdmin = (Admin) session.getAttribute("adminInfo");
		if(loginAdmin == null) {
			return "redirect:/admin/login";
		}
		
		// logger 찍기 - 어떤 url
		logger.info("/adminmember/farmerlist - [GET] 요청");
		
		// 페이징 설정에 대한 정보를 갖는 Map 객체
		Map<String, Object> pagingConfig = new HashMap<>();
		pagingConfig.put("curPage", curPage);
		pagingConfig.put("search", search);
		pagingConfig.put("listCode", FARMER_CODE);
		
//		logger.debug("pagingConfig: " + pagingConfig.toString());
		
		// 페이징 설정
		AdminPaging apaging = adminUserService.getPaging(pagingConfig);
//		logger.debug("apaging: " + apaging.toString());
		
		// select 조회 연산 수행
		List<Map<String, Object>> farmerList = adminUserService.selectFarmerByPaging(apaging);
//		for(Map<String, Object> f : farmerList) {
//			logger.debug("f: " + f.toString());
//		}
		
		// model 값 넘겨주기
		// 페이징 객체 넘기기
		if( apaging != null ) {
			
			model.addAttribute("apaging", apaging);
			
		}
		
		// 페이징 처리된 목록 넘기기
		if( farmerList != null ) {
			
			model.addAttribute("farmerList", farmerList);
			
		}
		
		return "admin/member/admin_farmer_list";
	}
	
	// 일반 회원 관리 페이지
	@RequestMapping(value = "/adminmember/userlist", method = RequestMethod.GET)
	public String adminUserList(
				Model model
				// 페이징 처리시 필요한 변수
				, @RequestParam(defaultValue = "0") String curPage
				// 검색 처리시 필요한 변수
				, @RequestParam(defaultValue = "") String search
				, HttpSession session
			) {
		
		// admin login이 안되어 있을 때
		Admin loginAdmin = (Admin) session.getAttribute("adminInfo");
		if(loginAdmin == null) {
			return "redirect:/admin/login";
		}
		
		// logger 찍기 - 해당 url에 들어왔다는 표시
		logger.info("/adminmember/userlist [GET] 요청");
		
		// 페이징 설정에 대한 정보를 갖는 Map 객체
		Map<String, Object> pagingConfig = new HashMap<>();
		pagingConfig.put("curPage", curPage);
		pagingConfig.put("search", search);
		pagingConfig.put("listCode", USER_CODE);
		
		logger.debug("pagingConfig: " + pagingConfig.toString());
		
		// 페이징 설정
		AdminPaging apaging = adminUserService.getPaging(pagingConfig);
		logger.debug("apaging: " + apaging.toString());
		
		// select 조회 연산 수행
		List<Map<String, Object>> userList = adminUserService.selectAllUserByPaging(apaging);
//		for(Map< String, Object> m : userList) {
//			logger.debug("m: " + m.toString());
//		}
		
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
	public String adminFarmerApplicationList(
				Model model
				// 페이징 처리시 필요한 변수
				, @RequestParam(defaultValue = "0") String curPage
				// 검색 처리시 필요한 변수
				, @RequestParam(defaultValue = "") String search
				, HttpSession session
			) {
		
		// admin login이 안되어 있을 때
		Admin loginAdmin = (Admin) session.getAttribute("adminInfo");
		if(loginAdmin == null) {
			return "redirect:/admin/login";
		}
		
		// logger 찍기
		logger.info("/adminmember/fapplicationlist - [GET] 요청");
		
		// 페이징 설정에 대한 정보를 갖는 Map 객체
		Map<String, Object> pagingConfig = new HashMap<>();
		pagingConfig.put("curPage", curPage);
		pagingConfig.put("search", search);
		pagingConfig.put("listCode", FARM_APPLICATION_CODE);
//		logger.debug("pagingConfig: " + pagingConfig.toString());
		
		// 페이징 설정
		AdminPaging apaging = adminUserService.getPaging(pagingConfig);
		logger.debug("apaging: " + apaging.toString());
		
		// select 조회 연산 수행
		List<Map<String, Object>> farmApplicationList = adminUserService.selectFarmApplicationByPaging(apaging);
		for(Map<String, Object> fa : farmApplicationList) {
			logger.debug("fa: " + fa.toString());
		}
		
		// model 값 넘겨주기
		// 페이징 객체 넘기기
		if(apaging != null) {
			
			model.addAttribute("apaging", apaging);
			
		}
		
		// 페이징 처리된 목록 넘기기
		if( farmApplicationList != null ) {
			
			model.addAttribute("farmApplicationList", farmApplicationList);
			
		}
		
		return "admin/member/admin_farmer_application_list";
	}
	
	// 농업인 회원 신청 관리 상세 페이지
	@RequestMapping(value = "/adminmember/fapplicationdetail", method = RequestMethod.GET)
	public String adminFarmerApplicationDetail(
				@RequestParam(defaultValue = "0")int farmerNo
				, Model model
				, HttpSession session
			) {
		
		// admin login이 안되어 있을 때
		Admin loginAdmin = (Admin) session.getAttribute("adminInfo");
		if(loginAdmin == null) {
			return "redirect:/admin/login";
		}
		
		// logger 찍기
		logger.info("/adminmember/fapplicationdetail - [GET] 요청");
		
		// farmerNo 찍기
		logger.debug("farmerNo: " + farmerNo);
		
		// farmerNo로 신청서 조회하기
		Map<String, Object> fApplication = adminUserService.selectFarmerApplicationDetailByFarmerNo(farmerNo);
		logger.debug("fapplication: " + fApplication.toString());
		
		// Model 값 넘겨주기
		if(fApplication != null) {
			model.addAttribute("fApplication", fApplication);
		}
		
		return "admin/member/admin_farmer_application_detail";
	}
	
	// 농업인 회원 신청 승인
	@RequestMapping(value = "/adminmember/approvefapplication", method = RequestMethod.POST)
	@ResponseBody
	public int approveFarmerApplication(
				// 입점 수락할 번호들
				@RequestParam Map<String, Object> approveNums
				// 서버 url 을 알기 위한 request 객체
				, HttpServletRequest req
			) {
		
		// logger 찍기
		logger.info("/adminmember/approvefapplication - [POST] 요청");
		
		// 반환값
		int result = 0;
		
//		logger.debug("approveNums: " + approveNums);
		
		// approveNums -> ArrayList 로 변환
		String[] farmerNums = approveNums.get("farmerNo").toString().split(",");
		List<String> farmerNoList = new ArrayList<>(Arrays.asList(farmerNums));
		logger.debug("farmerNoList: " + farmerNoList.toString());
		
		// 입점 수락
		int approveRes = adminUserService.approveFarmerApplication(farmerNoList);
		logger.debug("approveRes: " + approveRes);
		
		// 서버 url
		String urlPath = getServerPath(req);
		logger.debug("urlPath: " + urlPath);
		
		// 선택된 회원들의 메일 조회
		List<Farmer> farmerMailList = adminUserService.selectFarmerMailByFarmerNo(farmerNoList);
		logger.debug("farmerMailList: " + farmerMailList.toString());
		
		// 입점 수락 성공/실패 시
		// 입점 수락 성공 -> 메일 발송해주기
		if(approveRes >= 1) {
			
			logger.info("입점 승인 성공~");
			result = 1;

			// 메일 발송 - 선택된 회원들의 수 만큼 메일 발송 foreach 사용
			for(Farmer mailRecipient : farmerMailList) {
				adminUserService.approveMailSend(mailRecipient, urlPath);
			}
			
			return result;
		} else { // 입점 수락 실패... -> 메일 발송 x
			
			logger.info("입점 승인 실패...");
			return result;
			
		}
//		return result;
	}
	
	// 농업인 회원 신청 보류
	@RequestMapping(value = "/adminmember/putfapplicationonhold", method = RequestMethod.POST)
	@ResponseBody
	public int putFarmerApplicationOnHold(
			// 입점 수락할 번호들
			@RequestParam Map<String, Object> approveCancelNums
			// 서버 url 을 알기 위한 request 객체
			, HttpServletRequest req
			) {
		
		// logger 찍기
		logger.info("/adminmember/putfapplicationonhold - [POST] 요청");
		
		// ajax 반환 값
		int result = 0;
		
		// approveCancelNums print
		logger.debug("approveCancelNums: " + approveCancelNums);
		
		// approveCancelNums -> ArrayList 로 변환
		String[] farmerNums = approveCancelNums.get("farmerNo").toString().split(",");
		List<String> farmerNoList = new ArrayList<>(Arrays.asList(farmerNums));
		logger.debug("farmerNoList: " + farmerNoList.toString());
		
		// 입점 신청 보류
		int holdRes = adminUserService.holdFarmerApplication(farmerNoList);
		logger.debug("holdRes: " + holdRes);
		
		// 서버 url
		String urlPath = getServerPath(req);
		logger.debug("urlPath: " + urlPath);
		
		// 선택된 회원들의 메일 조회
		List<Farmer> farmerMailList = adminUserService.selectFarmerMailByFarmerNo(farmerNoList);
		logger.debug("farmerMailList: " + farmerMailList);
		
		// 입점 보류 성공/ 실패
		// 임점 수락 성공 -> 메일 발송해주기
		if(holdRes >= 1) {
			
			logger.info("입점 보류 성공~");
			result = 1;
			
			// 메일 발송 - 선택된 회원들의 수 만큼 메일 발송 foreach 사용
			for(Farmer mailRecipient : farmerMailList) {
				adminUserService.holdMailSend(mailRecipient, urlPath);
			}
			return result;
			
		} else { // 입점 수락 실패... -> 메일 발송 x
			
			logger.info("입점 보류 실패...");
			return result;
			
		}
		
		
//		return result;
		
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
		
		// 탈퇴 처리
		int delRes = adminUserService.deleteUserByUserNo(userNoList);
		
		// 탈퇴 성공
		if( delRes >= 1 ) {
			
			logger.info("탈퇴 성공~");
			result = 1;
			return result;
			
		} else { // 탈퇴 결과 실패
			
			logger.info("탈퇴 실패...");
			return result;
			
		}
	}
	// 일반 회원 탈퇴 취소
	@RequestMapping(value = "/adminmember/delcanceluser", method = RequestMethod.POST)
	@ResponseBody
	public int deleteCancelUser(
			// 탈퇴 취소할 번호들
			@RequestParam Map<String, Object> cancelNums
			) {
		
		// 현재 어떤 url 이 실행되고 있는지
		logger.info("/adminmember/delcanceluser [POST] 요청");
		
		int result = 0;
		
		// userNo -> ArrayList 로 변환
		String[] userNums = cancelNums.get("userNo").toString().split(",");
		List<String> userNoList = new ArrayList<>(Arrays.asList(userNums));
		logger.debug("userNoList: " + userNoList.toString());
		
		// 탈퇴 처리
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
	@ResponseBody
	public int deleteFarmer(
				// 탈퇴할 번호들
				@RequestParam Map<String, Object> deleteNums
			) {
		
		// logger 어떤 url 요청했는지
		logger.info("/adminmember/deletefarmer - [POST] 요청");
		
		// 반환할 값
		int result = 0;
		
		// deleteNums test print
		logger.debug("deleteNums: " + deleteNums.toString());
		
		// farmerNo -> ArrayList 로 변환
		String[] farmerNums = deleteNums.get("farmerNo").toString().split(",");
		List<String> farmerNoList = new ArrayList<>(Arrays.asList(farmerNums));
		logger.debug("farmerNoList: " + farmerNoList);
		
		// 탈퇴 처리
		int delRes = adminUserService.deleteFarmerByFaremrNo(farmerNoList);
//		logger.debug("delRes: " + delRes);
		
		// 탈퇴 성공
		if( delRes >= 1 ) {
			
			logger.info("탈퇴 성공");
			result = 1;
			return result;
			
		} else { // 탈퇴 실패
			
			logger.info("탈퇴 실패...");
			return result;
			
		}
	}
	
	// 농업인 회원 탈퇴 취소
	@RequestMapping(value = "/adminmember/delcancelfarmer", method = RequestMethod.POST)
	@ResponseBody
	public int deleteCancelFarmer(
			// 탈퇴 취소 번호들
			@RequestParam Map<String, Object> cancelNums
			) {

		// logger 찍기
		logger.info("/adminmember/delcancelfarmer - [POST] 요청");
		
		int result = 0;
		
		// cancelNums test pring
		logger.debug("cancelNums: " + cancelNums.toString());
		
		// farmerNo -> ArrayList로 변환
		String[] farmerNums = cancelNums.get("farmerNo").toString().split(",");
		List<String> farmerNoList = new ArrayList<>(Arrays.asList(farmerNums));
		logger.debug("farmerNoList: " + farmerNoList.toString());
		
		// 탈퇴 취소 처리
		int cancelRes = adminUserService.delCancelFarmerByFarmerNo(farmerNoList);
		logger.debug("cancelRes: " + cancelRes);
		
		// 탈퇴 처리 성공
		if(cancelRes >= 1) {
			
			logger.info("탈퇴 처리 성공");
			result = 1;
			return result;
			
		} else { // 탈퇴 처리 실패...
			
			logger.info("탈퇴 처리 실패...");
			return result;
		}
	}
	
}
