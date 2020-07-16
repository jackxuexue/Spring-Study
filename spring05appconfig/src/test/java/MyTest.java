import com.jackxue.pojo.MyAppConfig;
import com.jackxue.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        //通过配置类来获取ApplicationContext
        ApplicationContext context = new AnnotationConfigApplicationContext(MyAppConfig.class);
        //通过配置类的方法名来获取bean
        User user = context.getBean("getUser", User.class);
        System.out.println(user.getName());
    }
}
