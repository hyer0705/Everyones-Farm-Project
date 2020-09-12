package com.kh.farmapp.farmpersonalpage.farmQnA.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.farmapp.farmpersonalpage.farmQnA.model.service.FarmQnAService;

import common.dto.Farm;

@Controller
public class FarmQnAController {

	@Autowired
	private FarmQnAService farmqnaService;

	//QnA 작성 화면
	@RequestMapping(value = "/farmQnA/farmQnAwrite.do", method = RequestMethod.GET)
	public void farmqnaWrite() {
//		System.out.println("wrtie 페이지 접속 완료");		
		

	}

	//QnA 작성
	@RequestMapping(value = "/QnA/QnAwrite.do", method = RequestMethod.POST)
	public String qnaWrite(@RequestParam Map<String, Object> commandMap, String farmerNo) {

		farmqnaService.writeFarmQnA(commandMap);
	

		return "redirect:/farmQnA/farmQnAlist.do?farmerNo=" + farmerNo;
	}

	//QnA 리스트 화면
	@RequestMapping(value = "/farmQnA/farmQnAlist.do", method = RequestMethod.GET)
	public ModelAndView farmdiaryList(@RequestParam(required = false, defaultValue = "1") int cPage, String farmerNo) {

		ModelAndView mav = new ModelAndView();
		
		int farmNo = farmqnaService.selectFarmNoByFarmerNo(farmerNo);
		System.out.println("FarmDiaryController farmNo: " + farmNo);
		
		int cntPerPage = 10;
		
//		System.out.println("FarmQnAContorller - farmerNo: " + farmerNo);
		
		Map<String,Object> res = farmqnaService.selectFarmQnAList(cPage, cntPerPage, farmerNo);
		
		
		mav.addObject("paging", res.get("paging"));
		mav.addObject("list", res.get("fdlist"));
		mav.setViewName("farmQnA/farmQnAlist");
		
//		System.out.println("컨트롤러값"+res);
//		System.out.println(mav);
		//		System.out.println(res);

		return mav;
	}

	//상세조회 화면
	@RequestMapping(value = "/farmQnA/farmQnAdetail.do", method = RequestMethod.GET)
	public ModelAndView farmdiaryDetail(int farmQnaQuestionNo) {

		ModelAndView mav = new ModelAndView();

		Map<String, Object> res = farmqnaService.selectQnADetail(farmQnaQuestionNo);
		mav.addObject("detail", res);
		mav.setViewName("farmQnA/farmQnAdetail");

		return mav;

	}

	//게시글 삭제
	@RequestMapping(value = "/farmQnA/farmQnAdelete.do", method = RequestMethod.GET )
	public String deleteQnA(@RequestParam(value="farmQnaQuestionNo") int farmQnaQuestionNo) {

		farmqnaService.deleteQnA(farmQnaQuestionNo);

		return "redirect:/farmQnA/farmQnAlist.do";
	}

	//게시글 수정 화면
	@RequestMapping(value = "/farmQnA/farmQnAmodify.do", method = RequestMethod.GET)
	public ModelAndView farmdiaryModify(int farmQnaQuestionNo) {
//		System.out.println("수정 페이지 접속 완료");

		ModelAndView mav = new ModelAndView();

		Map<String, Object> res = farmqnaService.selectQnADetail(farmQnaQuestionNo);
		mav.addObject("detail", res);	

		return mav;
	}
	//게시글 수정
	@RequestMapping(value = "/QnA/QnAmodify.do", method = RequestMethod.POST)
	public ModelAndView diarymodify(@RequestParam Map<String, Object> commandMap, int farmQnaQuestionNo) {

		ModelAndView mav = new ModelAndView();

		farmqnaService.modifyQnA(commandMap);

		mav.setViewName("redirect:/farmQnA/farmQnAdetail.do?farmQnaQuestionNo="+farmQnaQuestionNo);

		return mav;
	}
	
	//QnA 답변 작성
	@RequestMapping(value = "/QnA/QnAanswerwrite.do", method = RequestMethod.POST)
	public String qnaAnswer(@RequestParam Map<String, Object> commandMap) {

		farmqnaService.writeFarmQnAanswer(commandMap);

		System.out.println(commandMap);

		return "redirect:/farmQnA/farmQnAdetail.do";
	}

}
