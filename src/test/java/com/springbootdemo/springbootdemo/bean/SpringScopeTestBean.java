package com.springbootdemo.springbootdemo.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableListableBeanFactory.SCOPE_PROTOTYPE)
public class SpringScopeTestBean {
    private String key;
    private String val;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("key", key)
                .append("val", val)
                .toString();
    }
}
