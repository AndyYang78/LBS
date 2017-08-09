package com.group.isoft.bearsport.activity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.group.isoft.bearsport.clientmodel.activity.ActivityReqModel;
import com.group.isoft.bearsport.clientmodel.activity.ActivityRespData;
import com.group.isoft.bearsport.clientmodel.activity.ActivityRespModel;
import com.group.isoft.bearsport.clientmodel.location.Location;
import com.group.isoft.bearsport.model.activity.Activity;
import com.group.isoft.bearsport.persist.activity.ActivityMapper;
import com.group.isoft.bearsport.util.ErrorCode;
import com.group.isoft.bearsport.util.Utils;

@Service
public class ActivityServiceImpl implements IActivityService {

	@Resource
	ActivityMapper activityMapper;

	public ActivityRespModel createActivity(ActivityReqModel activityReqModel) throws Exception {

		ActivityRespModel activityRespModel = new ActivityRespModel();
		Activity activity = new Activity();

		activity.setId(Utils.getUUID());
		activity.setActId("0001");
		activity.setActStatus("A");
		activity.setActDate(activityReqModel.getActDate());
		activity.setActSubject(activityReqModel.getActSubject());
		activity.setActTime(activityReqModel.getActTime());
		activity.setActType(activityReqModel.getActType());
		activity.setAreaAddress(activityReqModel.getAreaAddress());
		activity.setAreaLatitude(activityReqModel.getAreaLocation().getLatitude());
		activity.setArealongitude(activityReqModel.getAreaLocation().getLongitude());
		activity.setAreaName(activityReqModel.getAreaName());
		activity.setCreateDate(new Timestamp(System.currentTimeMillis()));
		activity.setFee(activityReqModel.getFeeEst());
		activity.setFeeType(activityReqModel.getFeeType());
		activity.setHeat("0");
		activity.setOpenId(activityReqModel.getOpenId());
		activity.setUserId("0001");
		activity.setPlanPeople(activityReqModel.getPlanPeople());
		activity.setSportType(activityReqModel.getSprType());
		activity.setWordInput(activityReqModel.getWordInput());

		if (activityMapper.addActivity(activity)) {
			activityRespModel.setResult(ErrorCode.RESPONSE_SUCCESS);
		} else {
			activityRespModel.setResult(ErrorCode.RESPONSE_ERROR);
		}
		return activityRespModel;
	}

	public ActivityRespModel fetchActivity(ActivityReqModel activityReqModel) throws Exception {
		ActivityRespModel activityRespModel = new ActivityRespModel();
		List<Activity> activityList = new ArrayList<Activity>();
		List<ActivityRespData> activityRespDataList = new ArrayList<ActivityRespData>();
		activityList = activityMapper.queryActivityList();

		for (Activity activity : activityList) {
			ActivityRespData activityRespData = new ActivityRespData();
			activityRespData.setActId(activity.getActId());
			activityRespData.setActDate(activity.getActDate());
			activityRespData.setActSubject(activity.getActSubject());
			activityRespData.setActTime(activity.getActTime());
			activityRespData.setActType(activity.getActType());
			activityRespData.setAreaAddress(activity.getAreaAddress());
			Location areaLocation = new Location();
			areaLocation.setLatitude(activity.getAreaLatitude());
			areaLocation.setLongitude(activity.getArealongitude());
			activityRespData.setAreaLocation(areaLocation);
			activityRespData.setAreaName(activity.getAreaName());
			activityRespData.setEnrPeople(1);
			activityRespData.setFeeEst(activity.getFee());
			activityRespData.setFeeType(activity.getFeeType());
			activityRespData.setOpenId(activity.getOpenId());
			activityRespData.setPlanPeople(activity.getPlanPeople());
			activityRespData.setSprType(activity.getSportType());
			activityRespData.setWordsInput(activity.getWordInput());

			activityRespDataList.add(activityRespData);
		}

		activityRespModel.setListData(activityRespDataList);
		activityRespModel.setResult(ErrorCode.RESPONSE_SUCCESS);
		return activityRespModel;
	}

}
