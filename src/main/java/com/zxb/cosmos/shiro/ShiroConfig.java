package com.zxb.cosmos.shiro;


import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ShiroConfig {
    private static Logger logger = LoggerFactory.getLogger(ShiroConfig.class);
    /**
     * shiro拦截器
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/index", "user");
        filterChainDefinitionMap.put("/", "user");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("register","anon");
        filterChainDefinitionMap.put("/doRegister", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        //authc表示需要验证身份才能访问，还有一些比如anon表示不需要验证身份就能访问等。
        shiroFilterFactoryBean.setLoginUrl("/login");

        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
     return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm()); //将Realm注入到securityManager
        securityManager.setCacheManager(ehCacheManager()); //注入缓存对象
        securityManager.setRememberMeManager(cookieRememberMeManager()); //注入cookieRememberMeManager
        return  securityManager;
    }

    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());  //设置加密规则
        return  myShiroRealm;
    }

    //加密规则
    //因为我们的密码是加过密的，所以要shiro验证用户身份的话，需要告诉我们用的是md5加的密，并且加了两次密
    //同时我们在自己的Realm中也通过SimpleAuthenticationInfo返回了加密时使用的y盐，这样
    //Shiro就能顺利的比对密码 验证用户名和密码是否正确了
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5"); //散列算法：这里使用md5算法
        hashedCredentialsMatcher.setHashIterations(2); //散列的次数，比如散列两次，相当于md5(md5(""))
        return  hashedCredentialsMatcher;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return  authorizationAttributeSourceAdvisor;
    }

    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
        logger.debug("ShiroConfig.simpleMappingExceptionResolver");
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        properties.setProperty("org.apache.shiro.authz.UnauthorizedException","/403");
        simpleMappingExceptionResolver.setExceptionMappings(properties);
        return  simpleMappingExceptionResolver;
    }


    @Bean
    public EhCacheManager ehCacheManager(){
        logger.info("ShiroConfig.ehCacheManager()");
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return  ehCacheManager;
    }

    /**
     * cookie对象
     * @return
     */
    @Bean
    public SimpleCookie simpleCookie(){
        logger.debug("ShiroConfig.simpleCookie()");
        //这个参数是cookie的名称，对应前端的checkbox的name = remenberMe
        SimpleCookie simpleCookie = new SimpleCookie("remenberMe");

        //记住我有效时间30天 单位秒
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * cookie管理对象
     * @return
     */
    @Bean
    public CookieRememberMeManager cookieRememberMeManager(){
        logger.debug("ShiroConfig.cookieRememberMeManager()");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(simpleCookie());
        return cookieRememberMeManager;
    }

}
