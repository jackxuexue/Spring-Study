import com.jackxue.pojo.Student;
import com.jackxue.services.UserServices;
import org.junit.Test;
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

    @Test
    public void TestSpring(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserServices usersvr = (UserServices) context.getBean("usersvr");
        usersvr.getUser();
    }

    @Test
    public void TestDI(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student student = context.getBean("student", Student.class);
        System.out.println(student);
    }
}
