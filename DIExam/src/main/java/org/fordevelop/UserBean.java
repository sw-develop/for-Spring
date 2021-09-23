package org.fordevelop;

//Bean클래스
public class UserBean {
/* 조건 - 개발자가 직접 인스턴스 생성하는 것이 아니므로, 조건이 필요한 것!
1) 기본생성자 가짐
2) 필드는 private으로 선언함
3) getter, setter 메소드 가짐
   ex) getName(), setName() 메소드를 name property라고 함.
 */
    private String name;
    private int age;
    private boolean male;

    public UserBean(){ //기본 생성자 생성
    }

    public UserBean(String name, int age, boolean male){
        this.name = name;
        this.age = age;
        this.male = male;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }
}
