package com.jFinal.project.creation;

import com.jFinal.project.creation.config.jfinalConfig.CoreConfig;
import com.jfinal.server.undertow.UndertowServer;

/**
 * @author 凉雨时旧
 */
public class ProjectStart {
    public static void main(String[] args) {
        UndertowServer.start(CoreConfig.class, 8085, true);
    }
}
