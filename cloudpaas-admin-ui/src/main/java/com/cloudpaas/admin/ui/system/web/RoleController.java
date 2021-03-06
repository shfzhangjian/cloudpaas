/**
 * 
 */
package com.cloudpaas.admin.ui.system.web;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloudpaas.admin.ui.base.BaseController;
import com.cloudpaas.admin.ui.base.UISimpleController;
import com.cloudpaas.admin.ui.constants.ApiConstants;
import com.cloudpaas.admin.ui.system.biz.RoleBiz;
import com.cloudpaas.common.constants.CommonConstants;
import com.cloudpaas.common.model.Role;
import com.cloudpaas.common.model.User;
import com.cloudpaas.common.result.ObjectResponse;
import com.cloudpaas.common.result.PageResponse;
import com.cloudpaas.plugin.redis.anno.CacheClear;
import com.google.common.collect.Maps;
import com.google.gson.Gson;

/**
 * @author 大鱼
 *
 * @date 2019年8月14日 下午2:35:55
 */
@Controller
@RequestMapping("role")
public class RoleController extends UISimpleController<RoleBiz,Role>{
	
	

	@RequestMapping("/index.html")
	public String index(){
		return "admin/system/role";
	}
	
	@RequestMapping("/add.html")
	public String add(){
		return "admin/system/roleAdd";
	}
	
//	@RequestMapping(value="/add.json",method=RequestMethod.POST)
//	@ResponseBody
//	public ObjectRestResponse<Role> add(@RequestBody Role entity){
//		ObjectRestResponse<Role> result= baseBiz.add(entity, ApiConstants.API_ROLE_ADD_URL);
//		return result;
//	}
	
	@RequestMapping("/edit.html")
	public String edit(@RequestParam Integer id,ModelMap modelMap){
		Map<String, Object> params = Maps.newHashMap();
		params.put("db", CommonConstants.DEFAULT_DATASOURCE_KEY);
		Role rp = new Role();
		rp.setId(id);
		Role role =getBaseBiz().get(rp, ApiConstants.API_ROLE_SELECTONE_URL,params);
		modelMap.put("role", role);
		return "admin/system/roleEdit";
	}
	
	@RequestMapping(value="/update.json",method=RequestMethod.PUT)
	@ResponseBody
	@CacheClear(prefix="UISC",pkg="true")
	public ObjectResponse<Role> update(@RequestBody Role entity){
		Map<String, Object> params = Maps.newHashMap();
		params.put("db", CommonConstants.DEFAULT_DATASOURCE_KEY);
		ObjectResponse<Role> result= getBaseBiz().update(entity, entity.getId(), 
				ApiConstants.API_ROLE_SINGLE_URL,params);
		return result;
	}
	
	
//	@RequestMapping(value="/delete.json",method=RequestMethod.DELETE)
//	@ResponseBody
//	public ObjectRestResponse<Role> remove(@RequestParam Integer id){
//		ObjectRestResponse<Role> result= baseBiz.remove(id, ApiConstants.API_ROLE_SINGLE_URL);
//		return result;
//	}
	
//	@RequestMapping(value="/deleteBatch.json",method=RequestMethod.DELETE)
//	@ResponseBody
//	public ObjectRestResponse<Role> remove(@RequestBody List<Role> lists){
//		ObjectRestResponse<Role> result= baseBiz.deleteBatch(lists, ApiConstants.API_ROLE_DELBATCH_URL);
//		return result;
//	}


	/* (non-Javadoc)
	 * @see com.cloudpaas.admin.ui.base.UISimpleController#sinpleUrl()
	 */
	@Override
	public String singleUrl() {
		// TODO Auto-generated method stub
		return ApiConstants.API_ROLE_SINGLE_URL;
	}

	
	
	
	
	
}
