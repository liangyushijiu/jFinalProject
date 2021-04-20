package com.jFinal.project.creation.config.jfinalConfig;

import com.jFinal.project.creation.common.CoreRoutes;
import com.jFinal.project.creation.common.json.CoreMixedJsonFactory;
import com.jFinal.project.creation.render.GlobalRenderFactory;
import com.jfinal.config.*;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.mysql.cj.util.StringUtils;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 凉雨时旧
 */
public class CoreConfig extends JFinalConfig {
    private static Log log;

    /**
     * 配置常量
     *
     * @param me 常量
     */
    @Override
    public void configConstant(Constants me) {
        String homeDirectory = System.getProperty("directory");
        if (StringUtils.isEmptyOrWhitespaceOnly(homeDirectory)) {
            homeDirectory = "";
        }

        File log4j = new File(homeDirectory + "log4j.properties");
        if (log4j.exists()) {
            PropertyConfigurator.configure(log4j.getAbsolutePath());
            //未初始化不能应用
            System.out.println("load log4j.properties. path:" + log4j.getAbsolutePath());
        }

        log = Log.getLog(CoreConfig.class);
        log.info("CoreConfig.class");
        //加载少量必要配置
        String configName = "appConfig.properties";
        File config = new File(homeDirectory + configName);
        if (config.exists()) {
            log.info("load user home dir properties:" + homeDirectory + configName);
            PropKit.use(config);
        } else {
            log.info("load project properties:" + configName);
            PropKit.use(configName);
        }

        log.info("load project properties success");

        me.setDevMode(PropKit.getBoolean("devMode" + false));

        me.setRenderFactory(new GlobalRenderFactory());

        me.setJsonFactory(new CoreMixedJsonFactory());

        log.info("init config end");


    }

    /**
     * 配置路由
     *
     * @param routes 路由
     */
    @Override
    public void configRoute(Routes routes) {
        routes.setMappingSuperClass(true);
        routes.add(new CoreRoutes());
    }

    @Override
    public void configEngine(Engine engine) {

    }

    /**
     * 配置插件
     *
     * @param plugins
     */
    @Override
    public void configPlugin(Plugins plugins) {
        log.info("init plugin");
        DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"),
                PropKit.get("password").trim());
        plugins.add(druidPlugin);

        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        // 显示SQL语句
        arp.setShowSql(true);
        // 所有映射在 MappingKit 中自动化搞定

        plugins.add(arp);




    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }


    public static DruidPlugin createDruidPlugin(String prefix) {
        //配置数据库
        String password = Arrays.toString(PropKit.get(prefix + ".jdbc.password").getBytes(StandardCharsets.UTF_8));
        DruidPlugin dp = new DruidPlugin(PropKit.get(prefix + ".jdbc.url"),PropKit.get(prefix + ".jdbc.username"),password);
        if (PropKit.getBoolean("devMode",true)){
            dp.setFilters("stat,wall");
        }else {
            dp.setFilters("wall");
        }
        return dp;
    }
}
