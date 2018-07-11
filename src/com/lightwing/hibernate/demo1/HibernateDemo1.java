package com.lightwing.hibernate.demo1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

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
        customer.setCust_source("Gay Porn");


        session.save(customer);

        // 6.事务提交
        transaction.commit();

        // 7.资源释放
        session.close();
        sessionFactory.close();
    }
}
