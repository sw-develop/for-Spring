package org.fordevelop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextExam01 {
    public static void main(String[] args){
        ApplicationContext ac = new ClassPathXmlApplicationContext(
                "classpath:applicationContext.xml"
        );
        System.out.println("초기화 완료!");

        UserBean userBean = (UserBean) ac.getBean("userBean"); //return 타입이 Object이므로 UserBean으로 downcasting 해줌
        userBean.setName("fordevelop");
        System.out.println(userBean.getName());

        UserBean userBean2 = (UserBean)ac.getBean("userBean");
        if(userBean == userBean2)
            System.out.println("동일한 인스턴스임");
    }
}
