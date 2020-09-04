package com.kh.farmapp.mypage.user.model.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.kh.farmapp.mypage.user.model.dao.MyPageDao;

import common.dto.Application;
import common.dto.Basket;
import common.dto.Product;
import common.dto.QuestionOneonone;
import common.dto.TBOrder;
import common.dto.UserAddress;
import common.dto.UserTB;
import common.util.FileUtil;
import common.util.Paging;

@Service
public class MypageServiceImpl implements MyPageService{

	@Autowired
	private MyPageDao mypageDao;
	
	@Override
	public int modifyUser(UserTB user) {
		
		System.out.println("1");
		int res = mypageDao.modifyUser(user);
		System.out.println(res);
		
		return res;
	}


	
	@Override
	public int modifyprofile(UserTB user) {
		return 0;
	}

	@Override
	public void insertFile(UserTB user, File file, String root) {
		
		FileUtil fileUtil = new FileUtil();
		
//		List<Map<String, String>> filedata = fileUtil.fileUpload(file, root);
		
	}
	
	@Override
	public int leave(UserTB user) {
		return mypageDao.leave(user);
	}

	@Override
	public Map<String, Object> o3List(int cPage, int cntPerPage) {
		
		Map<String, Object> o3List = new HashMap<String, Object>();
		
		Paging page = new Paging(mypageDao.cntO3(), cPage, cntPerPage);

		List<Map<String, Object>> one = mypageDao.o3List(page);
		o3List.put("o3List", one);
		o3List.put("page", page);
		
		return o3List;
	}

	@Override
	public QuestionOneonone o3Detail(int qNo) {
		return mypageDao.o3Detail(qNo);
	}

	@Override
	public int o3Upload(QuestionOneonone qO3) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int o3Modify(int qNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int o3Delete(int qNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Map<String, Object>> appliActList(UserTB user) {
		return mypageDao.appliActList(user);
	}

	@Override
	public Application appliHelpList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> basketList(int userNo, int cPage, int cntPerPage) {
		
		int total= mypageDao.cntBasket(userNo);
		Paging page = new Paging(total, cPage, cntPerPage);

		System.out.println("전체 게시물 수 :" + total);
		Map<String, Object> sub = new HashMap<String, Object>();
		
		sub.put("page", page);
		sub.put("userNo", userNo);
		List<Map<String, Object>> baskets = mypageDao.basketList(sub);
		
		Map<String, Object> basketList = new HashMap<String, Object>();
		
		basketList.put("baskets", baskets);
		basketList.put("page", page);
		
		return basketList;
	}

	@Override
	public int addProduct(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Object> orderList(int userNo, int cPage, int cntPerPage) {
		
		Map<String, Object> orderList = new HashMap<String, Object>();
		
		int total= mypageDao.cntOrder(userNo);
		Paging page = new Paging(total, cPage, cntPerPage);

		System.out.println("전체 게시물 수 :" + total);
		Map<String, Object> sub = new HashMap<String, Object>();
		
		sub.put("page", page);
		sub.put("userNo", userNo);
		
		List<Map<String, Object>> orders = mypageDao.orderList(sub);
		orderList.put("orders", orders);
		orderList.put("page", page);
		
		return orderList;
		
	}

	@Override
	public Map<String, Object> orderDetail(int orderNo) {
		return mypageDao.orderDetail(orderNo);
	}

	@Override
	public UserAddress getAddress(UserTB user) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public UserTB selectUser(UserTB user) {
		
		return mypageDao.selectUser(user);
	}



	@Override
	public int getCount() {
		return mypageDao.cntO3();
	}



	@Override
	public int cntApli(UserTB user) {
		return mypageDao.cntApli(user);
	}






}
