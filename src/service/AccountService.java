package service;

public interface AccountService {
    public boolean login(String uid, String pwdHash, String salt, int counter);
    public boolean register(String uid, String pwdHash, String salt, int counter);
    public boolean uidExisted(String uid);
    public int getCounter(String uid);
    public String getSalt(String uid);
    public String getSHA256(String str);
    public String byte2hex(byte[] bytes);
    public boolean updateCounter(String uid);
}
