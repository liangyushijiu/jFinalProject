package com.jFinal.project.creation.config.jfinalConfig;

import com.jFinal.project.creation.routes.CoreRoutes;
import com.jfinal.config.*;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.jFinal.project.creation.common.model._MappingKit;

/**
 * @author 凉雨时旧
 */
public class CoreConfig extends JFinalConfig {


    static Prop p;

    /**
     * 配置常量
     *
     * @param me 常量
     */
    @Override
    public void configConstant(Constants me) {
        loadConfig();

        me.setDevMode(p.getBoolean("devMode", false));
        /**
         * 支持 Controller、Interceptor、Validator 之中使用 @Inject 注入业务层，并且自动实现 AOP
         * 注入动作支持任意深度并自动处理循环注入
         */
        me.setInjectDependency(true);
        // 配置对超类中的属性进行注入
        me.setInjectSuperClass(true);
        me.setDevMode(true);
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
        /*routes.scan("com.jFinal.project.creation.business.");*/
    }

    @Override
    public void configEngine(Engine engine) {
        /*engine.addSharedFunction("/common/_layout.html");
        engine.addSharedFunction("/common/_paginate.html");*/
    }

    /**
     * 配置插件
     *
     * @param plugins
     */
    @Override
    public void configPlugin(Plugins plugins) {
        DruidPlugin druidPlugin = createDruidPlugin();
        plugins.add(druidPlugin);
        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        // 显示SQL语句
        arp.setShowSql(true);
        // 所有映射在 MappingKit 中自动化搞定
        _MappingKit.mapping(arp);
        plugins.add(arp);
    }

    public static DruidPlugin createDruidPlugin() {
        //配置数据库
        loadConfig();
        return new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password"));
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }

    static void loadConfig() {
        if (p == null) {
            p = PropKit.useFirstFound("application.properties");
        }
    }
}
