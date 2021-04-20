package com.jFinal.project.creation.render;

import com.jFinal.project.creation.api.kit.ApiKit;
import com.jfinal.render.Render;
import com.jfinal.render.RenderException;
import com.jfinal.render.RenderManager;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 凉雨时旧
 */
public class GlobalErrorRender extends Render {

    protected static final String CONTENT_TYPE = "text/html; charset =" + getEncoding();

    protected int errorCode;

    public GlobalErrorRender(int errorCode, String view) {
        this.errorCode = errorCode;
        this.view = view;
    }

    @Override
    public void render() {
        //httpServletResponse.SC
        response.setStatus(getErrorCode());

        String view = getView();
        if (view != null) {
            RenderManager.me().getRenderFactory().getRender(view).setContext(request, response).render();
            return;
        }

        PrintWriter writer = null;

        try {
            response.setContentType(CONTENT_TYPE);
            writer = response.getWriter();
            writer.write(getErrorJson());
            writer.flush();
        } catch (IOException e) {
           throw new RenderException();
        }finally {
            if(writer != null){
                writer.close();
            }
        }


    }


    private String getErrorJson() {
        int errorCode = getErrorCode();
        switch (errorCode) {
            case 500:
                return ApiKit.getMethodHandlerError().toString();
            case 401:
            case 403:
            case 404:
            default:
                return ApiKit.getMethodError().toString();
        }
    }

    private int getErrorCode() {
        return errorCode;
    }
}
