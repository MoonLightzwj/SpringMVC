package com.zwj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zwj.util.GetProperties;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

/**
 * Created by FirenzesEagle on 2016/5/30 0030.
 * Email:liumingbo2008@gmail.com
 */
@Configuration
public class MainConfig {

    //TODO 填写公众号开发信息
    //商站宝测试公众号 APP_ID
    protected static String APP_ID = "";
    //商站宝测试公众号 APP_SECRET
    protected static  String APP_SECRET = "";
    //商站宝测试公众号 TOKEN
    protected static  String TOKEN = "";
    //商站宝测试公众号 AES_KEY
    protected static  String AES_KEY = "";

    //商站宝微信支付商户号
    protected static final String PARTNER_ID = "";
    //商站宝微信支付平台商户API密钥(https://pay.weixin.qq.com/index.php/core/account/api_cert)
    protected static final String PARTNER_KEY = "";

    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
    	 GetProperties.readProperties("weixin.properties");
    	 APP_ID=GetProperties.getProperty("appid");
    	 APP_SECRET=GetProperties.getProperty("app_secret");
    	 TOKEN=GetProperties.getProperty("token");
        WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
        configStorage.setAppId(APP_ID);
        configStorage.setSecret(APP_SECRET);
        configStorage.setToken(TOKEN);
        configStorage.setAesKey(MainConfig.AES_KEY);
        configStorage.setPartnerId(MainConfig.PARTNER_ID);
        configStorage.setPartnerKey(MainConfig.PARTNER_KEY);
        return configStorage;
    }

    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

}
