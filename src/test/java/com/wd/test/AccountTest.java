package com.wd.test;

import com.wd.entity.Account;
import com.wd.entity.AccountUser;
import com.wd.mapper.AccountMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AccountTest {

    private InputStream in;
    private SqlSession session;
    private AccountMapper accountMapper;



    @Before //用于在测试方法执行之前执行
    public void init() throws IOException {
        //1.读取配置文件,生成读取字节流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂生产SqlSession对象
        session = factory.openSession();
        //4.使用SqlSession创建Mapper代理的接口对象
        accountMapper = session.getMapper(AccountMapper.class);
    }

    /**
     * 释放资源
     * @throws IOException
     */
    @After //用于在测试方法执行之后执行
    public void destroy() throws IOException {
        //提交事务
        // session.commit();
        session.close();
        in.close();
    }


    /**
     * 测试查询所有
     * @throws Exception
     */
    @Test
    public void testFindAll(){
        List<Account> accounts = accountMapper.findAll();
        for (Account account : accounts){
            System.out.println("--------每个account的信息------------");
            System.out.println(account);
            System.out.println(account.getUser());
        }

    }

    /**
     * 测试查询所有账户，同时包含用户名称和地址
     */
    @Test
    public void testFindAllAccountUser(){
        List<AccountUser> accountUsers = accountMapper.findAllAccount();
        for (AccountUser accountUser : accountUsers){
            System.out.println(accountUser);
        }
    }


    /**
     * 测试根据id查询用户信息
     */
//    @Test
//    public void testFindById(){
//        User user = userMapper.findById(50);
//        System.out.println(user);
//    }

}
