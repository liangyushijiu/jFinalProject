package com.jFinal.project.creation.business.hr;

import com.jFinal.project.creation.common.model.CoreJx3Member;
import com.jFinal.project.creation.service.HrService;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.kit.Ret;

import java.util.List;

/**
 * @author 凉雨时旧
 */

public class HrController extends Controller {


    @Before({GET.class})
    public void index() {
        List<CoreJx3Member> coreJx3Members = HrService.me.getCoreMember();
        renderJson(Ret.ok("msg",coreJx3Members));
    }
}
