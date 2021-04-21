package com.jFinal.project.creation.business.hr;

import com.jfinal.config.Routes;

/**
 * @author 凉雨时旧
 */
public class HrRoutes extends Routes {
    @Override
    public void config() {
        setBaseViewPath("/_view/");
        add("/hr", HrController.class);
    }
}
