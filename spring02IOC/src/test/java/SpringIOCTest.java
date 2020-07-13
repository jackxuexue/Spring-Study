import com.jackxue.services.UserServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIOCTest {
    public static void main(String[] args) {
        //1. 获取ApplicationContext
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //2. 从容器中取出bean
        UserServices usersvr = (UserServices) context.getBean("usersvr");
        //3. 调用方法
        usersvr.getUser();
    }
}
