package com.jFinal.project.creation.service;

import com.jFinal.project.creation.common.model.CoreJx3Member;
import com.jfinal.log.Log;

import java.util.List;

/**
 * @author 凉雨时旧
 */
public class HrService {

    private static Log log = Log.getLog(HrService.class);
    private CoreJx3Member dao = new CoreJx3Member().dao();
    public static final HrService me = new HrService();


    public List<CoreJx3Member> getCoreMember(){
        log.info("HrService---getCoreMember----查询所有用户信息----start");
        List<CoreJx3Member> coreJx3Members = dao.find("select * from core_jx3_member");
        return coreJx3Members;
    }


}
