# 什么是Spring

Spring : 春天 --->给软件行业带来了春天

2002年，Rod Jahnson首次推出了Spring框架雏形interface21框架。

2004年3月24日，Spring框架以interface21框架为基础，经过重新设计，发布了1.0正式版。

很难想象Rod Johnson的学历 , 他是悉尼大学的博士，然而他的专业不是计算机，而是音乐学。

**Spring理念 : 使现有技术更加实用 . 本身就是一个大杂烩 , 整合现有的框架技术**



> 优点

1. Spring是一个开源的免费的框架（容器）
2. Spring是一个轻量级的框架，非入侵式的
3. 控制反转（IOC），面向切面（AOP）
4. 对事物的支持，对框架的支持

一句话概括：

**Spring是一个轻量级的控制反转（IOC）和面向切面编程的框架（容器）。**

# IOC的推导

1. 之前使用的做法是：UserDao  UserDaoImpl   UserServies UserServiceImpl

   **UserDao**

   ```java
   package com.jackxue.dao;
   
   public interface UserDao {
       public abstract void getUser();
   }
   ```

   **UserDaoImpl**

   ```java
   package com.jackxue.dao;
   
   public class UserDaoImpl implements UserDao {
   
       public void getUser() {
           System.out.println("默认获取用户!");
       }
   }
   ```

   **UserServies**

   ```java
   package com.jackxue.services;
   
   public interface UserServices {
       public abstract void getUser();
   }
   
   ```

   **UserServiesImpl**

   ```java
   import com.jackxue.dao.UserDao;
   import com.jackxue.dao.UserDaoImpl;
   
   public class UserServicesImpl implements UserServices {
       //控制权在程序猿上，切换实现类时需要修改源代码，非常麻烦
       UserDao dao = new MysqlUserDaoImpl();
       public void getUser() {
           dao.getUser();
       }
   }
   ```

   这种思想的弊端是：业务一旦修改，程序源码必须要修改，一旦增加实现类就必须在UserServicesImpl 修改Dao的实现类。这种方式非常不灵活。

   

2. IOC

   ```java
   import com.jackxue.dao.UserDao;
   import com.jackxue.dao.UserDaoImpl;
   
   public class UserServicesImpl implements UserServices {
       // 控制权在程序猿上，切换实现类时需要修改源代码，非常麻烦
   //    UserDao dao = new MysqlUserDaoImpl();
       // 通过set方法来，让用户自定义设置实现类，这个就是IOC的原理
       UserDao dao ;
       public void setDao(UserDao dao) {
           this.dao = dao;
       }
       public void getUser() {
           dao.getUser();
       }
   }
   ```

   通过一个setDao的方法就发生了革命性的变化，用户通过自己来选择实现类

   ```java
   import com.jackxue.dao.MysqlUserDaoImpl;
   import com.jackxue.dao.UserDaoImpl;
   import com.jackxue.services.UserServices;
   import com.jackxue.services.UserServicesImpl;
   
   public class Mytest {
       public static void main(String[] args) {
           // 1.之前的做法，写死实现类
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
   ```

   这个就是IOC控制反转的原理。



# 第一个Spring程序

1. 导入java包， 通常导入webmvc ，不需导入很多包

   ```xml
   <dependencies>
       <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-webmvc</artifactId>
           <version>5.2.7.RELEASE</version>
       </dependency>
   </dependencies>
   ```

2. 实体类

   ```java
   package com.jackxue.pojo;
   
   public class Hello {
       private String str;
   
       public Hello() {
       }
   
       public Hello(String str) {
           this.str = str;
       }
   
       public String getStr() {
           return str;
       }
   
       public void setStr(String str) {
           this.str = str;
       }
   
       @Override
       public String toString() {
           return "Hello{" +
                   "str='" + str + '\'' +
                   '}';
       }
   }
   ```

3. 配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--
        Hello hello  = new Hello();
        1. id 类似于变量名
        2. class: 类似于全类名
        3. property: 通过setter方法注入参数
    -->
    <bean id="hello" class="com.jackxue.pojo.Hello">
        <property name="str" value="Spring" />
    </bean>
</beans>
```

4. 测试类

   ```java
   import com.jackxue.pojo.Hello;
   import org.springframework.context.ApplicationContext;
   import org.springframework.context.support.ClassPathXmlApplicationContext;
   
   public class SpringTest {
       public static void main(String[] args) {
           // 1. 获取applicationContext:
           ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
           Hello hello = (Hello) context.getBean("hello");
           System.out.println(hello.toString());
       }
   }
   ```

# 使用Spring重新实现之前做法

1. 创建UserDao接口，UserDaoImpl实现类，MysqlUserDaoImpl 实现类

2. 创建配置文件

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
           https://www.springframework.org/schema/beans/spring-beans.xsd">
   
       <!-- services -->
       <bean id="userdaoimpl" class="com.jackxue.dao.UserDaoImpl" />
       <bean id="mysqldaoimpl" class="com.jackxue.dao.MysqlUserDaoImpl" />
       <bean id="usersvr" class="com.jackxue.services.UserServicesImpl" >
           <property name="dao" ref="userdaoimpl" />
       </bean>
   </beans>
   ```

   ref为配置文件中已经映射好的对象

3. 写测试类

   ```java
   import com.jackxue.services.UserServices;
   import org.springframework.context.ApplicationContext;
   import org.springframework.context.support.ClassPathXmlApplicationContext;
   
   public class SpringIOCTest {
       public static void main(String[] args) {
           ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
           UserServices usersvr = (UserServices) context.getBean("usersvr");
           usersvr.getUser();
       }
   }
   ```

   

# IOC创建对象的方式

1. 使用无参构造创建对象，默认！

2. 假设我们要使用有参构造创建对象。

   实体类

   ```java
   package com.jackxue.dao;
   
   public class OracleUserDaoImpl  implements UserDao{
   
       private String str;
       public OracleUserDaoImpl(String str){
           this.str = str;
       }
   
       public String getStr() {
           return str;
       }
   
       public void setStr(String str) {
           this.str = str;
       }
   
       public void getUser() {
           System.out.println(this.str);
       }
   
       @Override
       public String toString() {
           return "OracleUserDaoImpl{" +
                   "str='" + str + '\'' +
                   '}';
       }
   
   
   }
   ```

   

   1.index 来指定构造函数的参数

   ```xml
   <!--使用index调用有参构造方法-->
       <bean id="oracledaoimpl" class="com.jackxue.dao.OracleUserDaoImpl" >
           <constructor-arg index="0" value="Oracle get user!" />
       </bean>
   ```

   2.使用参数类型来构建

   ```xml
   <!--使用构造函数的参数来指定  type要写全类名，不推荐使用-->
   <bean id="oracledaoimpl" class="com.jackxue.dao.OracleUserDaoImpl">
       <constructor-arg type="java.lang.String" value="Oracle get user"/>
   </bean>
   ```

   **3.使用参数名来指定，常用**

   ```xml
    <!--使用参数名来构造-->
   <bean id="oracledaoimpl" class="com.jackxue.dao.OracleUserDaoImpl">
       <constructor-arg name="str" value="Oracle get user" />
   </bean>
   ```

   总结：在配置文件加载的时候，容器中管理的所有对象就已经初始化了

# Spring配置

1. 别名

   ```xml
   <!--设置别名：在获取Bean的时候可以使用别名获取-->
   <alias name="userT" alias="userNew"/>
   ```

   

2. bean的配置

   ```xml
   <!--bean就是java对象,由Spring创建和管理-->
   
   <!--
      id : bean的标识符,要唯一,如果没有配置id,name就是默认标识符
      如果配置id,又配置了name,那么name是别名
      name可以设置多个别名,可以用逗号,分号,空格隔开
      如果不配置id和name,可以根据applicationContext.getBean(.class)获取对象;
   
      class : bean的全限定名=包名+类名
   -->
   <bean id="hello" name="hello2 h2,h3;h4" class="com.kuang.pojo.Hello">
      <property name="name" value="Spring"/>
   </bean>
   ```

3. import

   这个import,一般用于团队开发使用，他可以将多个配置文件，导入合并为一个；

   假设，现在项目中有多个人开发，这三个人复制不同的类开发，不同的类需要注册在不同的bean中，我们可以利用import将所有人的beans.xml合并为一个总的！

   ```xml
   <import resource="{path}/beans.xml"/>
   ```

# 依赖注入

## 构造器注入

```xml
 <!--使用参数名来构造-->
<bean id="oracledaoimpl" class="com.jackxue.dao.OracleUserDaoImpl">
    <constructor-arg name="str" value="Oracle get user" />
</bean>
```

## Set方式注入 【重点】

要求被注入的属性 , 必须有set方法 , set方法的方法名由set + 属性首字母大写 , 如果属性是boolean类型 , 没有set方法 , 是 is 

Address.java

```java
package com.jackxue.pojo;

public class MyAddress {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "MyAddress{" +
                "address='" + address + '\'' +
                '}';
    }
}
```

Student.java

```java
package com.jackxue.pojo;

import java.util.*;

public class Student {
    private String name;
    private MyAddress addr;
    private String[] books;
    private List<String> hobbys;
    private Map<String,String> card;
    private Set<String> games;
    private String wife;
    private Properties info;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyAddress getAddr() {
        return addr;
    }

    public void setAddr(MyAddress addr) {
        this.addr = addr;
    }

    public String[] getBooks() {
        return books;
    }

    public void setBooks(String[] books) {
        this.books = books;
    }

    public List<String> getHobbys() {
        return hobbys;
    }

    public void setHobbys(List<String> hobbys) {
        this.hobbys = hobbys;
    }

    public Map<String, String> getCard() {
        return card;
    }

    public void setCard(Map<String, String> card) {
        this.card = card;
    }

    public Set<String> getGames() {
        return games;
    }

    public void setGames(Set<String> games) {
        this.games = games;
    }

    public String getWife() {
        return wife;
    }

    public void setWife(String wife) {
        this.wife = wife;
    }

    public Properties getInfo() {
        return info;
    }

    public void setInfo(Properties info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", addr=" + addr +
                ", books=" + Arrays.toString(books) +
                ", hobbys=" + hobbys +
                ", card=" + card +
                ", games=" + games +
                ", wife='" + wife + '\'' +
                ", info=" + info +
                '}';
    }
}

```

测试类

```java
@Test
public void TestDI(){
    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    Student student = context.getBean("student", Student.class);
    System.out.println(student);
}
```



1.常量注入

```xml
<property name="name" value="JackXue" />
```

2.Bean注入

```xml
<bean id="addr" class="com.jackxue.pojo.MyAddress" >
    <property name="address" value="宝安" />
</bean>

<property name="addr" ref="addr" />
```

3.数组注入

```xml
<property name="books" >
    <array >
        <value>Java从入门到精通</value>
        <value>C++</value>
    </array>
</property>
```

4.List注入

```xml
<property name="hobbys" >
    <list>
        <value>编程</value>
        <value>游戏</value>
    </list>
</property>
```

5.Map注入

```xml
<property name="card" >
    <map>
        <entry key="身份证" value="44182719941102" />
        <entry key="银行卡" value="123123213" />
    </map>
</property>
```

6.Set注入

```xml
<property name="games" >
    <set>
        <value>LOL</value>
        <value>COC</value>
    </set>
</property>
```

7.null 注入

```xml
<property name="wife">
    <null></null>
</property>
```

8.Properties注入

```xml
<property name="info" >
    <props>
        <prop key="driver" >jdbc</prop>
        <prop key="url" >mysql://localhost:3306/srping</prop>
        <prop key="user" >root</prop>
        <prop key="password" >123456</prop>
    </props>
</property>
```

## 第三方命名空间注入

**P命名空间注入**

1. 引入命名空间

   ```xml
   <beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           https://www.springframework.org/schema/beans/spring-beans.xsd">
      
   ```

2. 使用

   ```xml
   <bean id="student" class="com.jackxue.pojo.Student"  p:name="JackXue" p:addr-ref="addr">
   </bean>
   ```



**C命名空间注入**

1. 引入命名空间

   ```xml
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:p="http://www.springframework.org/schema/p"
          xmlns:c="http://www.springframework.org/schema/c"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
           https://www.springframework.org/schema/beans/spring-beans.xsd">
   ```

2. 使用

   ```xml
   <bean id="oracledaoimpl" class="com.jackxue.dao.OracleUserDaoImpl" c:str="Oracle get User">
       <constructor-arg name="str" value="Oracle get user" />
   </bean>
   ```

   

## Bean的作用域

在Spring中，那些组成应用程序的主体及由Spring IoC容器所管理的对象，被称之为bean。简单地讲，bean就是由IoC容器初始化、装配及管理的对象 .

![img](https://mmbiz.qpic.cn/mmbiz_png/uJDAUKrGC7K5cyS8ZRTpajtSInicNHbMYfmmAQF8hrnicY49FRXEkR5xkxD5A4H5pVUia3mFhrDdh4gBt183EiaFaQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

几种作用域中，request、session作用域仅在基于web的应用中使用（不必关心你所采用的是什么web应用框架），只能用在基于web的Spring ApplicationContext环境。

#### Singleton

当一个bean的作用域为Singleton，那么Spring IoC容器中只会存在一个共享的bean实例，并且所有对bean的请求，只要id与该bean定义相匹配，则只会返回bean的同一实例。Singleton是单例类型，就是在创建起容器时就同时自动创建了一个bean的对象，不管你是否使用，他都存在了，每次获取到的对象都是同一个对象。注意，Singleton作用域是Spring中的缺省作用域。要在XML中将bean定义成singleton，可以这样配置：

```xml
 <bean id="ServiceImpl" class="cn.csdn.service.ServiceImpl" scope="singleton">
```

测试：

```java
 @Test public void test03(){     
     ApplicationContext context = newClassPathXmlApplicationContext("applicationContext.xml");     
     User user = (User) context.getBean("user");     
     User user2 = (User) context.getBean("user");     
     System.out.println(user==user2); 
 }
```

#### Prototype

当一个bean的作用域为Prototype，表示一个bean定义对应多个对象实例。Prototype作用域的bean会导致在每次对该bean请求（将其注入到另一个bean中，或者以程序的方式调用容器的getBean()方法）时都会创建一个新的bean实例。Prototype是原型类型，它在我们创建容器的时候并没有实例化，而是当我们获取bean的时候才会去创建一个对象，而且我们每次获取到的对象都不是同一个对象。根据经验，对有状态的bean应该使用prototype作用域，而对无状态的bean则应该使用singleton作用域。在XML中将bean定义成prototype，可以这样配置：

```xml
<bean id="account" class="com.foo.DefaultAccount" scope="prototype"/>   
或者
<bean id="account" class="com.foo.DefaultAccount" singleton="false"/>
```

#### Request

当一个bean的作用域为Request，表示在一次HTTP请求中，一个bean定义对应一个实例；即每个HTTP请求都会有各自的bean实例，它们依据某个bean定义创建而成。该作用域仅在基于web的Spring ApplicationContext情形下有效。考虑下面bean定义：

```xml
 <bean id="loginAction" class=cn.csdn.LoginAction" scope="request"/>
```

针对每次HTTP请求，Spring容器会根据loginAction bean的定义创建一个全新的LoginAction bean实例，且该loginAction bean实例仅在当前HTTP request内有效，因此可以根据需要放心的更改所建实例的内部状态，而其他请求中根据loginAction bean定义创建的实例，将不会看到这些特定于某个请求的状态变化。当处理请求结束，request作用域的bean实例将被销毁。

#### Session

当一个bean的作用域为Session，表示在一个HTTP Session中，一个bean定义对应一个实例。该作用域仅在基于web的Spring ApplicationContext情形下有效。考虑下面bean定义：

```xml
 <bean id="userPreferences" class="com.foo.UserPreferences" scope="session"/>
```

针对某个HTTP Session，Spring容器会根据userPreferences bean定义创建一个全新的userPreferences bean实例，且该userPreferences bean仅在当前HTTP Session内有效。与request作用域一样，可以根据需要放心的更改所创建实例的内部状态，而别的HTTP Session中根据userPreferences创建的实例，将不会看到这些特定于某个HTTP Session的状态变化。当HTTP Session最终被废弃的时候，在该HTTP Session作用域内的bean也会被废弃掉。





# Bean自动装配

- 自动装配是Spring满足Bean依赖的一种方式
- Spring会在上下文中自动寻找，并自动给Bean装配属性



在Spring中有三种转配的方式：

1. 在xml中显示的配置
2. 在Java中显示的配置
3. 隐式的自动转配bean

## 环境

1. pojo

   ```java
   package com.jackxue.pojo;
   
   public class Cat {
       public void shout(){
           System.out.println("喵~");
       }
   }
   ```

   ```java
   package com.jackxue.pojo;
   
   public class Dog {
       public void shout(){
           System.out.println("旺~");
       }
   }
   ```

   ```java
   package com.jackxue.pojo;
   
   public class People {
       private Cat cat;
       private Dog dog;
   
   ```

   ```java
   import com.jackxue.pojo.People;
   import org.springframework.context.ApplicationContext;
   import org.springframework.context.support.ClassPathXmlApplicationContext;
   
   public class MyTest {
       public static void main(String[] args) {
           ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
           People people = context.getBean("people", People.class);
           people.getCat().shout();
           people.getDog().shout();
       }
   }
   ```

2. xml配置

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:p="http://www.springframework.org/schema/p"
          xmlns:c="http://www.springframework.org/schema/c"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
           https://www.springframework.org/schema/beans/spring-beans.xsd">
   
       <bean id="cat" class="com.jackxue.pojo.Cat" />
       <bean id="dog" class="com.jackxue.pojo.Dog" />
   
   </beans>
   ```

## 装配

1. byName 

   会根据上下文自动寻找自己对象中的属性对应的bean id

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:p="http://www.springframework.org/schema/p"
          xmlns:c="http://www.springframework.org/schema/c"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
           https://www.springframework.org/schema/beans/spring-beans.xsd">
   
       <bean id="cat" class="com.jackxue.pojo.Cat" />
       <bean id="dog" class="com.jackxue.pojo.Dog" />
       <!--<bean id="dog2" class="com.jackxue.pojo.Dog" />-->
   
       <!--根据属性名来自动装配bean，people的cat属性找id为cat的对象 -->
       <bean id="people" class="com.jackxue.pojo.People" autowire="byName" />
   </beans>
   ```

2. byType

   会根据上下文自动寻找自己对象中的属性类型(com.jackxue.pojo.Dog)相对于的对象

   ```xml
    <!--根据People属性的类型来自动装配，只能转配全局唯一的，如果有多个会报错, 例如有
           多个dog类型
           <bean id="dog" class="com.jackxue.pojo.Dog" />
           <bean id="dog2" class="com.jackxue.pojo.Dog" />
    -->
   <bean id="people" class="com.jackxue.pojo.People" autowire="byType" />
   ```

## 使用注解实现自动装配

jdk1.5支持的注解，spring2.5就支持注解

1. 导入约束
2. 配置注解的支持

    ```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>
    ```

**@Autowired**

1. 配置

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
           https://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context
           https://www.springframework.org/schema/context/spring-context.xsd">
   
       <context:annotation-config/>
   
       <bean id="cat" class="com.jackxue.pojo.Cat" />
       <bean id="dog" class="com.jackxue.pojo.Dog" />
       <bean id="people" class="com.jackxue.pojo.People" />
   
   </beans>
   ```

2. 加注解

   ```java
   package com.jackxue.pojo;
   
   import org.springframework.beans.factory.annotation.Autowired;
   
   public class People {
       @Autowired
       private Cat cat;
       private Dog dog;
   
       public Cat getCat() {
           return cat;
       }
   
       public void setCat(Cat cat) {
           this.cat = cat;
       }
   
       public Dog getDog() {
           return dog;
       }
       @Autowired
       public void setDog(Dog dog) {
           this.dog = dog;
       }
   
       @Override
       public String toString() {
           return "People{" +
                   "cat=" + cat +
                   ", dog=" + dog +
                   '}';
       }
   }
   
   ```

3. 如果beanid和属性名不一致可以显示的指定id

   rug 

   ```xml
   <bean id="dog111" class="com.jackxue.pojo.Dog" />
   ```

   ```java
   @Autowired
   @Qualifier(value = "dog111")
   public void setDog(Dog dog) {
       this.dog = dog;
   }
   ```

4. 总结

   可以在setter上使用，也可以在类成员上加上注解

   所有的bean必须在xml上注册进IOC（Spring） 容器，并且满足byType的要求（即属性名和bean id匹配）

5. 扩展

   Autowired注解的required 默认为ture：

   ```java
   public @interface Autowired {
       boolean required() default true;
   }
   ```

   如果显示配置为false的话，可以为空

   ```java
   import org.springframework.beans.factory.annotation.Autowired;
   
   public class People {
       @Autowired(required = false)
       private Cat cat;
   ```

   使用Resources注解注入，如果名字和类型不唯一，可以通过name来指定，比较强大，但一般autowired就够用了
   
   ```java
   @Resource(name = "dog111")
   private Dog dog;
   ```