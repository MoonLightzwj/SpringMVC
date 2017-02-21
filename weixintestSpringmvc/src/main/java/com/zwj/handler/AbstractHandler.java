package com.zwj.handler;

import java.util.Map;

import com.google.gson.Gson;
import com.zwj.bean.WeixinTest;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 消息处理Handler的父类
 *
 * Created by FirenzesEagle on 2016/7/27 0027.
 * Email:liumingbo2008@gmail.com
 */
public abstract class AbstractHandler implements WxMpMessageHandler {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected final Gson gson = new Gson();
	public WxMpXmlOutMessage handle(
			WxMpXmlMessage wxMessage, Map<String, Object> context,
			WxMpService wxMpService, WxSessionManager sessionManager)
			throws WxErrorException {
		// TODO Auto-generated method stub
		return null;
	}
}
