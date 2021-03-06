/**
 * 
 */
package com.cloudpaas.admin.ui.base;

import java.util.Iterator;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.cloudpaas.admin.ui.prop.AdminUIProperites;
import com.cloudpaas.common.constants.CommonConstants;
import com.cloudpaas.common.model.User;

/**
 * @author 大鱼
 *
 * @date 2019年8月26日 下午3:49:21
 */
public abstract class AbstractBaseBiz {
	
	protected Logger log = LoggerFactory.getLogger(getClass());

	HttpHeaders headers = new HttpHeaders();
	
	@Autowired
	protected RestTemplate restTemplate;
	
	@Autowired
	protected AdminUIProperites auiProp;
	
	public HttpHeaders getHttpHeaders(){
        headers.add("Content-Type", "application/json;charset=utf-8");
        headers.add("Accept", "application/json");
        headers.add("X-Request-sId", getXRequestSID());
        //headers.add(CommonConstants.TOKEN_HEADER_KEY, getToken());
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
	}
	
	public void setHttpHeaders(Map<String,String> header){
		Iterator<String> it = header.keySet().iterator();
		while(it.hasNext()){
			String key = (String) it.next();
			String value = header.get(key);
			headers.add(key, value);
		}
	}
	
	private String getXRequestSID(){
		return getSID();
	}
	
	protected String getBaseUrl(){
		if("header".equals(auiProp.getType())){
			return auiProp.getApiUrl();
		}else if("path".equals(auiProp.getType())){
			return auiProp.getApiUrl()+"/"+getSID();
		}else{
			return auiProp.getApiUrl();
		}
	}
	
	
	/*
	protected User getUser(){
		Session session = SecurityUtils.getSubject().getSession();
		User user = (User) session.getAttribute(CommonConstants.USER_SESSION_ID);
		return user;
	}
	*/
	/*
	protected String getToken(){
		Session session = SecurityUtils.getSubject().getSession();
		String token = (String) session.getAttribute(CommonConstants.USER_TOKEN);
		return token;
	}*/
	
	public abstract String getSID();

}
