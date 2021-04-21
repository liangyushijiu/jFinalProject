package com.jFinal.project.creation.routes;

import com.jFinal.project.creation.business.hr.HrRoutes;
import com.jfinal.config.Routes;

/**
 * @author 凉雨时旧
 */
public class CoreRoutes extends Routes {
    @Override
    public void config() {
        setBaseViewPath("/_view");
        add(new HrRoutes());
    }
}
