import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

public class Demo {
    public static void main(String[] args) {
        IniSecurityManagerFactory iniSecurity=new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        SecurityManager securityManager = iniSecurity.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("ls","123");
        try {
            subject.login(token);
            System.out.println("登录成功！！");
        }catch(IncorrectCredentialsException e1){
            System.out.println("密码错误！！");
        }catch(UnknownAccountException e2){
            System.out.println("账号不存在！！");
        }catch (Exception e) {
            e.printStackTrace();
        }
        if (subject.hasRole("role1")){
            System.out.println("管理员");
        }else if(subject.hasRole("role2")){
            System.out.println("经理");
        }else if(subject.hasRole("role3")){
            System.out.println("普通用户");
        }

        //权限判断
        if(subject.isPermitted("user:update")){
            System.out.println("拥有修改权限");
        }else{
            System.out.println("无权修改");
        }

    }
}
