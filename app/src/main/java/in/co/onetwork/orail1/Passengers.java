package in.co.onetwork.orail1;

/**
 * Created by abhi on 30/9/17.
 */

public class Passengers {
    String uid,name,age,seat;
    Passengers(){}
    Passengers(String uid,String name,String age,String seat){
        this.uid=uid;
        this.name=name;
        this.age=age;
        this.seat=seat;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUid() {
        return uid;
    }

    public String getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
