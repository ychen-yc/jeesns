package com.lxinet.jeesns.core.directive;

import com.lxinet.jeesns.core.handler.DirectiveHandler;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

import java.io.IOException;
import java.util.Map;

public abstract class BaseDirective
        implements TemplateDirectiveModel {
    public void execute(Environment environment, Map parameters, TemplateModel[] loopVars, TemplateDirectiveBody templateDirectiveBody)
            throws TemplateException, IOException {
        execute(new DirectiveHandler(environment, parameters, loopVars, templateDirectiveBody));
    }

    public abstract void execute(DirectiveHandler paramDirectiveHandler)
            throws TemplateException, IOException;
}
