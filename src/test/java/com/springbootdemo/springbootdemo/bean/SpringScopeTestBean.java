package com.springbootdemo.springbootdemo.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(ConfigurableListableBeanFactory.SCOPE_PROTOTYPE)
//@Scope(ConfigurableListableBeanFactory.SCOPE_SINGLETON)
//@Scope(value = WebApplicationContext.SCOPE_SESSION)
/**
 * 当对接口设置作用域时，需要用@Scope(value = WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.INTERFACES)
 * spring会根据proxyMode的配置内容在容器中找相应的bean 没有此接口会报错;
 */
//@Scope(value = WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.TARGET_CLASS)
//@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
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





    /*@Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(key)
                .append(val)
                .toHashCode();
    }*/
}
