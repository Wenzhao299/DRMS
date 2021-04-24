package service.impl;

import dao.AccountDao;
import dao.impl.AccountDaoImpl;
import service.AccountService;

public class AccountServiceImpl implements AccountService {
    private AccountDao ad = new AccountDaoImpl();

    @Override
    public boolean login(String uid, String pwdHash, String salt, int counter) {
        return ad.login(uid, pwdHash, salt, counter);
    }

    @Override
    public boolean register(String uid, String pwdHash, String salt, int counter) {
        return ad.register(uid, pwdHash, salt, counter);
    }

    @Override
    public boolean uidExisted(String uid) {
        return ad.uidExisted(uid);
    }

    @Override
    public int getCounter(String uid) {
        return ad.getCounter(uid);
    }

    @Override
    public boolean updateCounter(String uid) {
        return ad.updateCounter(uid);
    }

    @Override
    public String getSalt(String uid) {
        return ad.getSalt(uid);
    }

    @Override
    public String getSHA256(String str) {
        return ad.getSHA256(str);
    }

    @Override
    public String byte2hex(byte[] bytes) {
        return ad.byte2hex(bytes);
    }

}
