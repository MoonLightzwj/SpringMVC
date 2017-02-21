package com.zwj.handler;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.WxMpTemplateMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.zwj.bean.WeixinTest;
import com.zwj.config.MainConfig;
import com.zwj.service.CoreService;
import com.zwj.service.IUserService;
import com.zwj.service.IWeixinTestService;
import com.zwj.util.GetProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * 用户关注公众号Handler
 * 
 * Created by FirenzesEagle on 2016/7/27 0027. Email:liumingbo2008@gmail.com
 */
@Component
public class SubscribeHandler extends AbstractHandler {

	@Autowired
	protected WxMpConfigStorage configStorage;
	@Autowired
	protected WxMpService wxMpService;
	@Autowired
	protected CoreService coreService;
	@Resource
	private IWeixinTestService weixinTestService = null;
	
	private WeixinTest weixinTest=new WeixinTest();

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
			Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {
		WxMpUser wxMpUser = coreService.getUserInfo(
				wxMessage.getFromUserName(), "zh_CN");

		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("openId", wxMpUser.getOpenId()));
		params.add(new BasicNameValuePair("nickname", wxMpUser.getNickname()));
		params.add(new BasicNameValuePair("headImgUrl", wxMpUser
				.getHeadImgUrl()));
		GetProperties.readProperties("weixin.properties");
		String appid = GetProperties.getProperty("appid");
		GetProperties.readProperties("url.properties");
		String url = GetProperties.getProperty("subscribeurl");

		// TODO 在这里可以进行用户关注时对业务系统的相关操作（比如新增用户）
		WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
		item.setDescription("作为中国口岸物流信息服务领域的知名企业，上海国际航运中心信息化建设领域的骨干企业，亿通国际取得了众多的荣誉，于2003年被国家信息产业部和人力资源部联合授予“全国信息产业系统先进集体” 称号，2004年被国家科技部评为“全国");
		item.setPicUrl(url + "/images/1.jpg");
		item.setTitle("亿通国际");
		item.setUrl("");
		WxMpXmlOutNewsMessage.Item item2 = new WxMpXmlOutNewsMessage.Item();
		item2.setDescription("");
		item2.setPicUrl(url + "/images/2.jpg");
		item2.setTitle("已有账号，立即绑定");
		item2.setUrl("http://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ appid
				+ "&redirect_uri="
				+ url
				+ "/index.jsp&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect");
		WxMpXmlOutNewsMessage.Item item3 = new WxMpXmlOutNewsMessage.Item();
		item3.setDescription("");
		item3.setPicUrl(url + "/images/3.jpg");
		item3.setTitle("未有账号，请注册");
		item3.setUrl("http://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ appid
				+ "&redirect_uri="
				+ url
				+ "/index.jsp&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect");
		WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS()
				.fromUser(wxMessage.getToUserName())
				.toUser(wxMessage.getFromUserName()).addArticle(item)
				.addArticle(item2).addArticle(item3).build();
		//获取openid和其他数据存入数据库
		String nickName = wxMpUser.getNickname();
		String openId = wxMpUser.getOpenId();
		if (nickName != null && openId != null) {
			weixinTest.setNickname(nickName);
			weixinTest.setOpenid(openId);
			weixinTestService.insert(weixinTest);
		}
	
		// WxMpXmlOutTextMessage m
		// = WxMpXmlOutMessage.TEXT()
		// .content("尊敬的" + wxMpUser.getNickname() + "，您好！")
		// .fromUser(wxMessage.getToUserName())
		// .toUser(wxMessage.getFromUserName())
		// .build();
		// logger.info("subscribeMessageHandler" + m.getContent());
		return m;
	}

};
