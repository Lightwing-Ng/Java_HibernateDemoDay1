# Java_HibernateDemoDay1

### 第1章	Hibernate_day01 笔记

### 1.1 `Hibernate` 框架的学习

#### 路线

第一天：Hibernate 的入门（Hibernate 的环境搭建、Hibernate 的API、Hibernate 的 CRUD）
第二天：Hibernate 的一级缓存、其他的 API
第三天：Hibernate 的一对多配置、Hibernate 的多对多的配置
第四天：Hibernate 的查询方式、抓取策略

#### 1.2 `CRM` 的案例

##### 1.2.1 CRM的概述（了解）

​	客户关系管理（<u>C</u>ustomer <u>R</u>elationship <u>M</u>anagement，缩写 **CRM**），企业活动面向长期的客户关系，以求提升企业成功的管理方式，其目的之一是要协助企业管理销售循环：新客户的招徕、保留旧客户、提供客户服务及进一步提升企业和客户的关系，并运用市场营销工具，提供创新式的个人化的客户商谈和服务，辅以相应的信息系统或信息技术如数据挖掘和数据库营销来协调所有公司与顾客间在销售、营销以及服务上的交互。 

###### 1.2.1.1 什么是 CRM

###### 1.2.1.2 CRM 有哪些模块

#### 1.3 Hibernate 的框架的概述

##### 1.3.1 框架的概述

###### 1.3.1.1 什么是框架

​	框架：指的是软件的半成品，已经完成了部分功能。

​	软件框架（software framework），通常指的是为了实现某个业界标准或完成特定基本任务的软件组件规范，也指为了实现某个软件组件规范时，提供规范所要求之基础功能的软件产品。

​	框架的功能类似于基础设施，与具体的软件应用无关，但是提供并实现最为基础的软件架构和体系。软件开发者通常依据特定的框架实现更为复杂的商业运用和业务逻辑。这样的软件应用可以在支持同一种框架的软件系统中运行。

​	简而言之，框架就是制定一套规范或者规则（思想），大家（程序员）在该规范或者规则（思想）下工作。或者说使用别人搭好的舞台来做编剧和表演。

##### 1.3.2 EE 的三层架构

###### 1.3.2.1 EE 的经典三层结构

##### 1.3.3 Hibernate 的概述

###### 1.3.3.1 什么是 Hibernate

​	Hibernate 是一个持久层的 `ORM` 框架。

​	Hibernate 是一种 Java 语言下的对象关系映射解决方案。它是使用 `GNU` 宽通用公共许可证发行的自由、开源的软件。它为面向对象的领域模型到传统的关系型数据库的映射，提供了一个使用方便的框架。

###### 1.3.3.2 什么是 ORM

​	ORM：Object Relational Mapping（对象关系映射）。指的是将一个 Java中的对象与关系型数
	据库中的表建立一种映射关系，从而操作对象就可以操作数据库中的表。

###### 1.3.3.3 为什么要学习 Hibernate

#### 1.4 Hibernate 的入门

##### 1.4.1 Hibernate 的入门

###### 1.4.1.1 下载 Hibernate 的开发环境

`Hibernate3.x`  

`Hibernate4.x`  

`Hibernate5.x`

```
https://sourceforge.net/projects/hibernate/files/hibernate-orm/5.0.7.Final/
```

###### 1.4.1.2 解压 Hibernate

| 目录结构        |                                  |
| :-------------- | -------------------------------- |
| `documentation` | Hibernate 开发的文档             |
| `lib`           | Hibernate 开发包                 |
| `required`      | Hibernate 开发的**必须的依赖包** |
| `optional`      | Hibernate 开发的可选的 jar 包    |
| `project`       | Hibernate 提供的项目             |

###### 1.4.1.3 创建一个项目，引入 jar 包

* 数据库驱动包

`mysql-connector-java-5.1.7-bin.jar`

* Hibernate 开发的必须的 jar 包

`/required`

* Hibernate 引入日志记录包

`log4j-1.2.16.jar`
`slf4j-api-1.6.1.jar`
`slf4j-log4j12-1.7.2.jar`

###### 1.4.1.4 创建表（数据库）

```sql
CREATE TABLE `cst_customer` (
	`cust_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '客户编号(主键)',
	`cust_name` varchar(32) NOT NULL COMMENT '客户名称(公司名称)',
	`cust_source` varchar(32) DEFAULT NULL COMMENT '客户信息来源',
	`cust_industry` varchar(32) DEFAULT NULL COMMENT '客户所属行业',
	`cust_level` varchar(32) DEFAULT NULL COMMENT '客户级别',
	`cust_phone` varchar(64) DEFAULT NULL COMMENT '固定电话',
	`cust_mobile` varchar(16) DEFAULT NULL COMMENT '移动电话',
	PRIMARY KEY (`cust_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARSET = utf8;
```

###### 1.4.1.5 创建实体类

```java
public class Customer {
    private Long cust_id;
    private String cust_name;
    private String cust_source;
    private String cust_industry;
    private String cust_level;
    private String cust_phone;
    private String cust_mobile;
```

###### 1.4.1.6 创建映射（*****）

​	映射需要通过 **XML** 的配置文件来完成，这个配置文件可以任意命名。尽量统一命名规范（类名`.hbm.xml`）

`Customer.hbm.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-mapping PUBLIC
        "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
<hibernate-mapping>
    <!-- 建立类与表的映射 -->
    <class name="com.lightwing.hibernate.demo1.Customer" table="cst_customer">
        <!-- 建立类中的属性与表中的主键对应 -->
        <id name="cust_id" column="cust_id">
            <generator class="native"/>
        </id>
        <!-- 建立类中的普通的属性和表的字段的对应 -->
        <property name="cust_name" column="cust_name" length="32"/>
        <property name="cust_source" column="cust_source" length="32"/>
        <property name="cust_industry" column="cust_industry"/>
        <property name="cust_level" column="cust_level"/>
        <property name="cust_phone" column="cust_phone"/>
        <property name="cust_mobile" column="cust_mobile"/>
    </class>
</hibernate-mapping>
```

###### 1.4.1.7 创建一个 Hibernate 的核心配置文件（*****）

​	Hibernate 的核心配置文件的名称：`hibernate.cfg.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">

<hibernate-configuration>
    <session-factory>
        <!-- 连接数据库的基本参数 -->
        <property name="hibernate.connection.driver_class">com.mysql.jdbc.Driver</property>
        <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/User</property>
        <property name="hibernate.connection.username">root</property>
        <property name="hibernate.connection.password">canton0520</property>
        <property name="hibernate.dialect">
            org.hibernate.dialect.MySQLDialect
        </property>

        <!-- Optional -->
        <!-- 打印SQL -->
        <property name="hibernate.show_sql">true</property>
        <!-- 格式化SQL -->
        <property name="hibernate.format_sql">true</property>
        <!-- 自动创建表 -->
        <property name="hibernate.hbm2ddl.auto">update</property>
        <!-- 配置C3P0连接池 -->
        <property name="connection.provider_class">
            org.hibernate.connection.C3P0ConnectionProvider
        </property>
        <!--在连接池中可用的数据库连接的最少数目 -->
        <property name="c3p0.min_size">5</property>
        <!--在连接池中所有数据库连接的最大数目  -->
        <property name="c3p0.max_size">20</property>
        <!--设定数据库连接的过期时间,以秒为单位,
        如果连接池中的某个数据库连接处于空闲状态的时间超过了timeout时间,就会从连接池中清除 -->
        <property name="c3p0.timeout">120</property>
        <!--每3000秒检查所有连接池中的空闲连接 以秒为单位-->
        <property name="c3p0.idle_test_period">3000</property>
        <mapping resource="com/lightwing/hibernate/demo1/Customer.hbm.xml"/>
    </session-factory>
</hibernate-configuration>
```

###### 1.4.1.8 编写测试代码（*****）

```java
/**
 * Hibernate的入门案例
 *
 * @author Lightwing Ng
 */
public class HibernateDemo1 {
    @Test
    public void demo1() {
        // 1.加载 Hibernate 的核心配置文件
        Configuration configuration = new Configuration().configure();

        // 2.创建一个 SessionFactory 对象：类似于 JDBC 中连接池
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // 3.通过 SessionFactory 获取到Session对象：类似于JDBC中Connection
        Session session = sessionFactory.openSession();

        // 4.手动开启事务：
        Transaction transaction = session.beginTransaction();

        // 5.编写代码
        Customer customer = new Customer();
        customer.setCust_name("Simon Sheraton");
        session.save(customer);

        // 6.事务提交
        transaction.commit();

        // 7.资源释放
        session.close();
        sessionFactory.close();
    }
}
```

#### 1.5 Hibernate的常见配置

##### 1.5.1	XML 提示的配置

###### 1.5.1.1 配置 XML 提示问题

###### 1.5.2	Hibernate 的映射的配置

###### 1.5.2.1 映射的配置

1. class 标签的配置

​    标签用来建立类与表的映射关系
    属性：

| name    | 类的全路径                            |
| ------- | ------------------------------------- |
| table   | 表名（类名与表名一致，table可以省略） |
| catalog | 数据库名                              |

2. id 标签的配置

​    标签用来建立类中的属性与表中的主键的对应关系
    属性：

| name   | 类中的属性名                                                 |
| ------ | ------------------------------------------------------------ |
| column | 表中的字段名（类中的属性名和表中的字段名如果一致，column 可以省略） |
| length | 长度                                                         |
| type   | 类型                                                         |

3. property 标签的配置

​    标签用来建立类中的普通属性与表的字段的对应关系
    属性：

| name     | 类中的属性名 |
| -------- | ------------ |
| column   | 类中的属性名 |
| length   | 长度         |
| type     | 类型         |
| not-null | 设置非空     |
| unique   | 设置唯一     |

##### 1.5.3 Hibernate 的核心的配置

###### 1.5.3.1 Hibernate 的核心配置方式（了解）

一种方式: **属性文件的方式**

```xml
hibernate.properties
hibernate.connection.driver_class=com.mysql.jdbc.Driver
…
hibernate.show_sql=true
```

属性文件的方式不能引入映射文件（手动编写代码加载映射文件）

二种方式: **XML** 文件的方式**（*）**
`hibernate.cfg.xml`

###### 1.5.3.2 核心的配置

1. 必须的配置

- 连接数据库的基本的参数

- 驱动类
- url 路径
- 用户名
- 密码
- 方言

2. 可选的配置

| 显示 SQL       | hibernate.show_sql                                           |
| -------------- | ------------------------------------------------------------ |
| 格式化 SQL     | hibernate.format_sql                                         |
| 自动建表       | hibernate.hbm2ddl.auto                                       |
| none           | 不使用 hibernate 的自动建表                                  |
| create-drop    | 如果数据库中已经有表，删除原有表，重新创建，如果没有表，新建表。（测试） |
| update         | 如果数据库中有表，使用原有表，如果没有表，创建新表（更新表结构） |
| validate       |                                                              |
| 映射文件的引入 | 引入映射文件的位置                                           |

#### 1.6 Hibernate 的核心 API

##### 1.6.1 Hibernate 的 API

###### 1.6.1.1 `Configuration: Hibernate` 的配置对象

作用：加载核心配置文件
hibernate.properties

```java
Configuration cfg = new Configuration();
```

`hibernate.cfg.xml`

```java
Configuration cfg = new Configuration().configure();
```

加载映射文件

```java
// 手动加载映射
   configuration.addResource("com/itheima/hibernate/demo1/Customer.hbm.xml");
```

###### 1.6.1.2 SessionFactory: Session工厂

​	SessionFactory 内部维护了 Hibernate 的连接池和 Hibernate 的二级缓存（不讲）。是线程安全的对
象。一个项目创建一个对象即可。

1. 配置连接池：（了解）

```properties
<!-- 配置C3P0连接池 -->
        <property name="connection.provider_class">
            org.hibernate.connection.C3P0ConnectionProvider
        </property>
        <!--在连接池中可用的数据库连接的最少数目 -->
        <property name="c3p0.min_size">5</property>
        <!--在连接池中所有数据库连接的最大数目  -->
        <property name="c3p0.max_size">20</property>
        <!--设定数据库连接的过期时间,以秒为单位,
        如果连接池中的某个数据库连接处于空闲状态的时间超过了timeout时间,就会从连接池中清除 -->
        <property name="c3p0.timeout">120</property>
        <!--每3000秒检查所有连接池中的空闲连接 以秒为单位-->
        <property name="c3p0.idle_test_period">3000</property>
```

2. 抽取工具类

###### 1.6.1.3 Session: 类似 Connection 对象是连接对象

Session 代表的是 Hibernate 与数据库的链接对象。不是线程安全的。与数据库交互桥梁。
Session 中的API

1. 保存方法：

```java
Serializable save(Object obj);
```

2. 查询方法：

```java
T get(Class c, Serializable id);
T load(Class c, Serializable id);
```

get方法和load方法的区别？

3. 修改方法

```java
void update(Object obj);
```

4. 删除方法

```java
void delete(Object obj);
```

5. 保存或更新

```java
void saveOrUpdate(Object obj);
```

查询所有

###### 1.6.1.4 Transaction: 事务对象

Hibernate 中管理事务的对象。

```
commit();
rollback();
```