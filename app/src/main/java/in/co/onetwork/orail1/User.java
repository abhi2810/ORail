package in.co.onetwork.orail1;

/**
 * Created by abhi on 30/9/17.
 */

public class User {
    String uid,name,age,address,email;
    User(){

    }
    User(String uid,String name,String age,String address,String email){
        this.uid=uid;
        this.name=name;
        this.age=age;
        this.address=address;
        this.email=email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getUid() {
        return uid;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
