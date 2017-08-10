package com.group.isoft.bearsport.controller.activity;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.group.isoft.bearsport.activity.IActivityService;
import com.group.isoft.bearsport.clientmodel.activity.ActivityReqModel;
import com.group.isoft.bearsport.clientmodel.activity.ActivityRespModel;
import com.group.isoft.bearsport.util.OperationCode;

@Controller
@RequestMapping("/activity")
public class ActivityController {
	private static final Logger sysLogger = Logger.getLogger("customer");

	@Resource
	IActivityService activityService;

	@RequestMapping(value = "/activityMaintain", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public Object activityMaintain(final HttpServletRequest request, final HttpServletResponse response,
			@RequestBody ActivityReqModel activityRequest) throws Exception {
		ActivityRespModel activityRespModel = new ActivityRespModel();

		if (activityRequest.getOperationCode().equals(OperationCode.ACT_CREATE)) {
			activityRespModel = createActivity(activityRequest);
		} else if (activityRequest.getOperationCode().equals(OperationCode.ACT_FETCH)) {
			activityRespModel = getActivityList(activityRequest);
		} else if (activityRequest.getOperationCode().equals(OperationCode.ACT_FETCH_DETL)) {
			activityRespModel = getActivityDetail(activityRequest);
		}
		return activityRespModel;
	}

	private ActivityRespModel createActivity(@RequestBody ActivityReqModel activityRequest) throws Exception {
		return activityService.createActivity(activityRequest);
	}

	private ActivityRespModel getActivityList(@RequestBody ActivityReqModel activityRequest) throws Exception {
		return activityService.fetchActivity(activityRequest);
	}

	private ActivityRespModel getActivityDetail(@RequestBody ActivityReqModel activityRequest) throws Exception {
		return activityService.fetchActivityDetl(activityRequest);
	}

}
