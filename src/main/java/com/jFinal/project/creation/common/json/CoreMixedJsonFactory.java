package com.jFinal.project.creation.common.json;

import com.jfinal.json.*;

/**
 * @author 凉雨时旧
 */
public class CoreMixedJsonFactory implements IJsonFactory {
    private static final CoreMixedJsonFactory me = new CoreMixedJsonFactory();

    public static CoreMixedJsonFactory me() {return me;}

    private static MixedJson mixedJson = new MixedJson();

    @Override
    public Json getJson() {
        return mixedJson;
    }

    private static class MixedJson extends Json {

        private static JFinalJson jFinalJson = JFinalJson.getJson();
        private static Jackson jackson = Jackson.getJson();

        @Override
        public String toJson(Object o) {
            return jFinalJson.toJson(o);
        }

        @Override
        public <T> T parse(String s, Class<T> aClass) {
            return jackson.parse(s,aClass);
        }

        public MixedJson(){
            Jackson.setDefaultGenerateNullValue(false);
        }
    }
}
