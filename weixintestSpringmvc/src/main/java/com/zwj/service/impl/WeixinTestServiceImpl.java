package com.zwj.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zwj.bean.WeixinTest;
import com.zwj.dao.IWeixinTestDao;
import com.zwj.service.IWeixinTestService;

@Service("weixinTestService")
public class WeixinTestServiceImpl implements IWeixinTestService {
	@Resource
	private IWeixinTestDao iweixinDao;
	@Override
	public boolean insert(WeixinTest weixinTest) {
		// TODO Auto-generated method stub
		if(iweixinDao.insert(weixinTest)==1){
			return true;
		}
		return false;
	}

}
