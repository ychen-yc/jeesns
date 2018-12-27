package com.lxinet.jeesns.core.handler;

import freemarker.core.Environment;
import freemarker.template.*;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DirectiveHandler {
    public static final SimpleDateFormat FULL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final int FULL_DATE_LENGTH = 19;
    public static final SimpleDateFormat SHORT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final int SHORT_DATE_LENGTH = 10;
    private Environment environment;
    private Map<String, TemplateModel> parameters;
    private TemplateModel[] loopVars;
    private TemplateDirectiveBody templateDirectiveBody;
    private Map<String, Object> map = new HashMap();

    public DirectiveHandler(Environment environment, Map<String, TemplateModel> parameters, TemplateModel[] loopVars, TemplateDirectiveBody templateDirectiveBody) {
        this.environment = environment;
        this.loopVars = loopVars;
        this.parameters = parameters;
        this.templateDirectiveBody = templateDirectiveBody;
    }

    public void render()
            throws IOException, TemplateException {
        Map<String, TemplateModel> reduceMap = reduce();
        if (null != this.templateDirectiveBody) {
            this.templateDirectiveBody.render(this.environment.getOut());
        }
        reduce(reduceMap);
    }

    public void renderIfNotNull(Object notEmptyObject)
            throws IOException, TemplateException {
        if (null != notEmptyObject) {
            render();
        }
    }

    public void print(String str)
            throws IOException, TemplateException {
        this.environment.getOut().append(str);
    }

    public DirectiveHandler put(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

    private Map<String, TemplateModel> reduce()
            throws TemplateModelException {
        Map<String, TemplateModel> reduceMap = new HashMap();
        for (String key : this.map.keySet()) {
            TemplateModel value = this.environment.getVariable(key);
            if (null != value) {
                reduceMap.put(key, this.environment.getVariable(key));
            }
            this.environment.setVariable(key, this.environment.getObjectWrapper().wrap(this.map.get(key)));
        }
        return reduceMap;
    }

    private void reduce(Map<String, TemplateModel> map)
            throws TemplateModelException {
        for (String key : map.keySet()) {
            this.environment.setVariable(key, (TemplateModel) map.get(key));
        }
    }

    public TemplateHashModel getMap(String name)
            throws TemplateModelException {
        TemplateModel model = (TemplateModel) this.parameters.get(name);
        if (null == model) {
            return null;
        }
        if ((model instanceof TemplateHashModelEx)) {
            return (TemplateHashModelEx) model;
        }
        if ((model instanceof TemplateHashModel)) {
            return (TemplateHashModel) model;
        }
        return null;
    }

    public String getString(String name, String defaultValue)
            throws TemplateException {
        String result = getString(name);
        if (null == result) {
            return defaultValue;
        }
        return result;
    }

    public String getString(String name)
            throws TemplateException {
        TemplateModel model = (TemplateModel) this.parameters.get(name);
        if (null == model) {
            return null;
        }
        if ((model instanceof TemplateScalarModel)) {
            return ((TemplateScalarModel) model).getAsString();
        }
        if ((model instanceof TemplateNumberModel)) {
            return ((TemplateNumberModel) model).getAsNumber().toString();
        }
        return null;
    }

    public Integer getInteger(String name, int defaultValue)
            throws TemplateException {
        Integer result = getInteger(name);
        if (null == result) {
            return Integer.valueOf(defaultValue);
        }
        return result;
    }

    public Integer getInteger(String name)
            throws TemplateException {
        TemplateModel model = (TemplateModel) this.parameters.get(name);
        if (null == model) {
            return null;
        }
        if ((model instanceof TemplateNumberModel)) {
            return Integer.valueOf(((TemplateNumberModel) model).getAsNumber().intValue());
        }
        if ((model instanceof TemplateScalarModel)) {
            String s = ((TemplateScalarModel) model).getAsString();
            if (StringUtils.isBlank(s)) {
                return null;
            }
            try {
                return Integer.valueOf(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    public Short getShort(String name)
            throws TemplateException {
        TemplateModel model = (TemplateModel) this.parameters.get(name);
        if (null == model) {
            return null;
        }
        if ((model instanceof TemplateNumberModel)) {
            return Short.valueOf(((TemplateNumberModel) model).getAsNumber().shortValue());
        }
        if ((model instanceof TemplateScalarModel)) {
            String s = ((TemplateScalarModel) model).getAsString();
            if (StringUtils.isBlank(s)) {
                return null;
            }
            try {
                return Short.valueOf(Short.parseShort(s));
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    public Long getLong(String name)
            throws TemplateException {
        TemplateModel model = (TemplateModel) this.parameters.get(name);
        if (null == model) {
            return null;
        }
        if ((model instanceof TemplateNumberModel)) {
            return Long.valueOf(((TemplateNumberModel) model).getAsNumber().longValue());
        }
        if ((model instanceof TemplateScalarModel)) {
            String s = ((TemplateScalarModel) model).getAsString();
            if (StringUtils.isBlank(s)) {
                return null;
            }
            try {
                return Long.valueOf(Long.parseLong(s));
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    public Double getDouble(String name)
            throws TemplateException {
        TemplateModel model = (TemplateModel) this.parameters.get(name);
        if (null == model) {
            return null;
        }
        if ((model instanceof TemplateNumberModel)) {
            return Double.valueOf(((TemplateNumberModel) model).getAsNumber().doubleValue());
        }
        if ((model instanceof TemplateScalarModel)) {
            String s = ((TemplateScalarModel) model).getAsString();
            if (StringUtils.isBlank(s)) {
                return null;
            }
            try {
                return Double.valueOf(Double.parseDouble(s));
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    public Integer[] getIntegerArray(String name)
            throws TemplateException {
        String[] arr = getStringArray(name);
        if (null != arr) {
            Integer[] ids = new Integer[arr.length];
            int i = 0;
            try {
                for (String s : arr) {
                    ids[(i++)] = Integer.valueOf(s);
                }
                return ids;
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    public Long[] getLongArray(String name)
            throws TemplateException {
        String[] arr = getStringArray(name);
        if (null != arr) {
            Long[] ids = new Long[arr.length];
            int i = 0;
            try {
                for (String s : arr) {
                    ids[(i++)] = Long.valueOf(s);
                }
                return ids;
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    public String[] getStringArray(String name)
            throws TemplateException {
        String str = getString(name);
        if (StringUtils.isBlank(str)) {
            return null;
        }
        return StringUtils.split(str, ',');
    }

    public Boolean getBoolean(String name, Boolean defaultValue)
            throws TemplateException {
        Boolean result = getBoolean(name);
        if (null == result) {
            return defaultValue;
        }
        return result;
    }

    public Boolean getBoolean(String name)
            throws TemplateException {
        TemplateModel model = (TemplateModel) this.parameters.get(name);
        if (null == model) {
            return null;
        }
        if ((model instanceof TemplateBooleanModel)) {
            return Boolean.valueOf(((TemplateBooleanModel) model).getAsBoolean());
        }
        if ((model instanceof TemplateNumberModel)) {
            return Boolean.valueOf(0 != ((TemplateNumberModel) model).getAsNumber().intValue());
        }
        if ((model instanceof TemplateScalarModel)) {
            String s = ((TemplateScalarModel) model).getAsString();
            if (StringUtils.isNotBlank(s)) {
                return Boolean.valueOf((!"0".equals(s)) && (!"false".equalsIgnoreCase(s)));
            }
            return null;
        }
        return null;
    }

    public Date getDate(String name)
            throws TemplateException {
        TemplateModel model = (TemplateModel) this.parameters.get(name);
        if (null == model) {
            return null;
        }
        if ((model instanceof TemplateDateModel)) {
            return ((TemplateDateModel) model).getAsDate();
        }
        if ((model instanceof TemplateScalarModel)) {
            String temp = StringUtils.trimToEmpty(((TemplateScalarModel) model).getAsString());
            try {
                if (19 == temp.length()) {
                    return FULL_DATE_FORMAT.parse(temp);
                }
                if (10 == temp.length()) {
                    return SHORT_DATE_FORMAT.parse(temp);
                }
                return null;
            } catch (ParseException e) {
                return null;
            }
        }
        return null;
    }

    public Map<String, TemplateModel> getParameters() {
        return this.parameters;
    }

    public Environment getEnvironment() {
        return this.environment;
    }

    public TemplateDirectiveBody getTemplateDirectiveBody() {
        return this.templateDirectiveBody;
    }

    public TemplateModel[] getLoopVars() {
        return this.loopVars;
    }

    public void setLoopVars(TemplateModel[] loopVars) {
        this.loopVars = loopVars;
    }
}
