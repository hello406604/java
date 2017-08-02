package com.kaishengit.hibernate;

import com.kaishengit.pojo.Address;
import com.kaishengit.pojo.User;
import org.junit.Test;

public class ManyToOneTest extends BaseTest{

    @Test
    public void  findAll () {
//        User user = (User) session.get(User.class,1);
//        System.out.println(user);
        Address address = (Address) session.get(Address.class,2);

//        System.out.println(address.getCityName() + " --- " + address.getUser().getName());
    }

    @Test
    public void save() {
        User user = new User();
        user.setName("张三");
        Address address = new Address();
        address.setCityName("郑洲");
        address.setUser(user);
        //先存一再存读多 否则会多执行一次update或者Column 'user_id' cannot be null 报错
        session.save(user);
        session.save(address);
    }

    @Test
    public void del() {
        User user = (User) session.get(User.class,1);
        session.delete(user);
    }
}
