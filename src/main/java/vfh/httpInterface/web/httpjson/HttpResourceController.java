package vfh.httpInterface.web.httpjson;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vfh.httpInterface.commons.RequestParamUtil;
import vfh.httpInterface.service.resource.ResourceService;

@Controller
@RequestMapping("/jsondata/resource")
@SuppressWarnings(value={"rawtypes","unchecked"})
public class HttpResourceController {
	@Autowired
    private ResourceService resourceService;
	@RequestMapping("/getResourceList")
	@ResponseBody
	public void getResourceList(HttpServletRequest request,HttpServletResponse response){
		Map res = new HashMap();
		Map params = RequestParamUtil.requestToMap(request);
		Map m =  resourceService.findResourceList(params);
		if("000000".equals(m.get("returnCode"))){
			res.put("data", m.get("data"));
			res.put("statusCode","0000");
		}else{
			res.put("statusCode","1111");
		}
		res.put("returnMsg",m.get("returnMsg"));
		RequestParamUtil.responseWriter(request, response, res);
	}
}
