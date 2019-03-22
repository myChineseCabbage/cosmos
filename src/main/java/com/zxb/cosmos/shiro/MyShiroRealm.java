package com.zxb.cosmos.shiro;

import com.zxb.cosmos.controller.UserController;
import com.zxb.cosmos.pojo.Permission;
import com.zxb.cosmos.pojo.Role;
import com.zxb.cosmos.pojo.User;
import com.zxb.cosmos.service.PermissionService;
import com.zxb.cosmos.service.RoleService;
import com.zxb.cosmos.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class MyShiroRealm extends AuthorizingRealm {
    private static Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private RoleService roleServiceImpl;
    @Autowired
    private PermissionService permissionServiceImpl;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("授权--权限配置 MyShiroRealm.doGetAuthorizationInfo");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //获取当前正在登录得用户信息
        User user = (User) principalCollection.getPrimaryPrincipal();
        //查询当前用户的所有角色
        List<Role> roles = roleServiceImpl.getRolesByUid(user.getUid());
        //查询当前用户的所有权限
        List<Permission> permissions = permissionServiceImpl.getPermisByRoleID(roles);
        //将角色和权限配置给authorizationInfo
        for(int i=0;i<roles.size();i++){
            authorizationInfo.addRole(roles.get(i).getRoleName());
        }
        for(int i=0;i<permissions.size();i++){
            authorizationInfo.addStringPermission(permissions.get(i).getPermissionName());
        }
        return authorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("用户认证 MyShiroRealm.doGetAuthenticationInfo ");
        //获取用户的输入账号
        String uid =(String)authenticationToken.getPrincipal();
        logger.info(uid);
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User userT = new User();
        userT.setUid(uid);
        User user = userServiceImpl.getUserInfo(userT);
        logger.info("---------当前user:"+user);
        if(null == user){
            //没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt = username+salt
                getName() //realm
        );
        return authenticationInfo;
    }
}
