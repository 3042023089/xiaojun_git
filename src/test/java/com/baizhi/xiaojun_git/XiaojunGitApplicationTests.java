package com.baizhi.xiaojun_git;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XiaojunGitApplicationTests {
    @Test
    public void testHhiro(){
        //创建安全管理工厂
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //根据安全管理器工厂获取安全管理器  shiro的核心
        SecurityManager securityManager = factory.createInstance();
        //将安全管理器交给安全工具类
        SecurityUtils.setSecurityManager(securityManager);
        //根据安全工具类获取主体对象
        Subject subject = SecurityUtils.getSubject();
        //创建用户Token(令牌)=用户身份信息+凭证信息
        AuthenticationToken token=new UsernamePasswordToken("bobo","111111");
        try {
            //认证
            subject.login(token);
        } catch (UnknownAccountException e) {
            System.out.println("未知的账户异常");
        } catch (IncorrectCredentialsException e) {
            System.out.println("不正确的凭证异常");
        }

        //UnknownAccountException           未知的账户异常    用户名不对
        //IncorrectCredentialsException    不正确的凭证异常   密码错了

        //查看是否认证通过
        boolean authenticated = subject.isAuthenticated();
        System.out.println("认证状态: "+authenticated);
    }
}

