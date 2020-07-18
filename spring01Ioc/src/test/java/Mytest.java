import com.jackxue.dao.MysqlUserDaoImpl;
import com.jackxue.dao.UserDaoImpl;
import com.jackxue.services.UserServices;
import com.jackxue.services.UserServicesImpl;

public class Mytest {
    public static void main(String[] args) {
        // 1.
/*        UserServices userServices = new UserServicesImpl();
        userServices.getUser();*/
        //2. 通过set方法来选择实现类
        UserServicesImpl userServices = new UserServicesImpl();
        // 通过设置实现来灵活选择实现类，灵活
//        userServices.setDao(new MysqlUserDaoImpl());
        userServices.setDao(new UserDaoImpl());
        userServices.getUser();
    }
}
