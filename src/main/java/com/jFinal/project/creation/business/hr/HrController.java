package com.jFinal.project.creation.business.hr;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;

/**
 * @author 凉雨时旧
 */

public class HrController extends Controller {
    @Before({GET.class})
    public void index() {
        renderText("Hello JFinal World.");
    }
}
