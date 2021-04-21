package com.jFinal.project.creation.render;

import com.jfinal.render.Render;
import com.jfinal.render.RenderFactory;

/**
 * @author 凉雨时旧
 */
public class GlobalRenderFactory extends RenderFactory {

    @Override
    public Render getErrorRender(int errorCode, String view) {
        return new GlobalErrorRender(errorCode, view);
    }

}
