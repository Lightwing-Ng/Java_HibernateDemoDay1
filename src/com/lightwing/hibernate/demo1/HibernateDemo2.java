package com.lightwing.hibernate.demo1;

import com.lightwing.hibernate.utils.HibernateUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Hibernate的工具类的测试
 *
 * @author Lightwing Ng
 */
public class HibernateDemo2 {
    @Test
    // 保存客户
    public void demo1() {
        Session session = HibernateUtils.openSession();
        Transaction tx = session.beginTransaction();

        Customer customer = new Customer();
        customer.setCust_name("王小西");
        Serializable id = session.save(customer);
        System.out.println(id);
        tx.commit();
        session.close();
    }

    @Test
    // 查询：
    // ***** get方法和load方法的区别
    public void demo2() {
        Session session = HibernateUtils.openSession();
        Transaction tx = session.beginTransaction();

        // 使用get方法查询
        Customer customer = session.get(Customer.class, 100l); // 发送SQL语句
        System.out.println(customer);

        // 使用load方法查询
        customer = session.load(Customer.class, 200l);

        tx.commit();
        session.close();
    }

    @Test
    // 修改操作
    public void demo3() {
        Session session = HibernateUtils.openSession();
        Transaction tx = session.beginTransaction();

        // 先查询，再修改(推荐)
        Customer customer = session.get(Customer.class, 1l);
        // customer.setCust_name("王小贱");
        // session.update(customer);

        tx.commit();
        session.close();
    }

    @Test
    // 删除操作
    public void demo4() {
        Session session = HibernateUtils.openSession();
        Transaction tx = session.beginTransaction();

        // 先查询再删除(推荐)--级联删除
        Customer customer = session.get(Customer.class, 2l);
        // session.delete(customer);

        tx.commit();
        session.close();
    }

    @Test
    // 保存或更新
    public void demo5() {
        Session session = HibernateUtils.openSession();
        Transaction tx = session.beginTransaction();

        Customer customer = new Customer();
        customer.setCust_id(3l);
        customer.setCust_name("李如花");
        session.saveOrUpdate(customer);

        // tx.commit();
        session.close();
    }

    @Test
    // 查询所有
    public void demo6() {
        Session session = HibernateUtils.openSession();
        Transaction tx = session.beginTransaction();
        // 接收HQL：Hibernate Query Language 面向对象的查询语言
        // 接收SQL：
        SQLQuery query = session.createSQLQuery("SELECT * FROM cst_customer");
        List<Object[]> list = query.list();
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }
        tx.commit();
        session.close();
    }
}
