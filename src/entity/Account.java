package entity;

public class Account {
    private String uid;
    private String pwdHash;
    private String salt;
    private int counter;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPwdHash() {
        return pwdHash;
    }

    public void setPwdHash(String pwdHash) {
        this.pwdHash = pwdHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public String toString() {
        return "Account{" +
                "uid='" + uid + '\'' +
                ", pwdHash='" + pwdHash + '\'' +
                ", salt='" + salt + '\'' +
                ", counter=" + counter +
                '}';
    }

    public Account(String uid, String pwdHash, String salt, int counter) {
        this.uid = uid;
        this.pwdHash = pwdHash;
        this.salt = salt;
        this.counter = counter;
    }
}
