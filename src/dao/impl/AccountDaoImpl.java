package dao.impl;

import dao.AccountDao;
import util.BaseDao;

import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDaoImpl extends BaseDao implements AccountDao {

    @Override
    public boolean login(String uid, String pwdHash, String salt, int counter) {
        String sql = "select * from Account where uid=?";
        Object[] obj = {uid};
        ResultSet rs = this.getUSERDataByAny(sql, obj);
        boolean flag = false;
        String pwdHash_db = null;
        String salt_db = null;
        int counter_db = -1;
        try {
            while (rs.next()) {
                pwdHash_db = rs.getString("pwdHash");
                salt_db = rs.getString("salt");
                counter_db = rs.getInt("counter");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //System.out.println("pwdHash_db:" + pwdHash_db + "\nsalt_db:" + salt_db+ "\ncounter_db:" + counter_db);
        String modify = uid + salt + counter + getSHA256(pwdHash + salt + counter);
        String modify_db = uid + salt_db + (counter_db + 1) + getSHA256(pwdHash_db + salt_db + (counter_db+1));
        if(modify.equals(modify_db)) {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean register(String uid, String pwdHash, String salt, int counter) {
        String sql = "select * from Account where uid=?";
        Object[] obj = {uid};
        ResultSet rs = this.getUSERDataByAny(sql, obj);
        boolean isExisted = false;
        boolean flag = false;
        try {
            while (rs.next()) {
                isExisted = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(isExisted) {
            return flag;
        }else {
            String sql_register = "insert into Account values(?,?,?,?)";
            Object[] obj_register = {uid, pwdHash, salt, counter};
            int result = this.modifyUSERData(sql_register, obj_register);
            if(result>0) {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public boolean uidExisted(String uid) {
        String sql = "select * from Account where uid=?";
        Object[] obj = {uid};
        ResultSet rs = this.getUSERDataByAny(sql, obj);
        boolean flag = true;
        try {
            while (rs.next()) {
                flag = false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public int getCounter(String uid) {
        String sql = "select counter from Account where uid=?";
        Object[] obj = {uid};
        ResultSet rs = this.getUSERDataByAny(sql, obj);
        int counter = -1;
        try {
            while (rs.next()) {
                counter = rs.getInt("counter");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return counter;
    }

    @Override
    public boolean updateCounter(String uid) {
        String sql_register = "update Account set counter=? where uid=?";
        Object[] obj_register = {getCounter(uid)+1, uid};
        int result = this.modifyUSERData(sql_register, obj_register);
        boolean flag = false;
        if(result>0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public String getSalt(String uid) {
        String sql = "select salt from Account where uid=?";
        Object[] obj = {uid};
        ResultSet rs = this.getUSERDataByAny(sql, obj);
        String salt = null;
        try {
            while (rs.next()) {
                salt = rs.getString("salt");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return salt;
    }

    @Override
    public String getSHA256(String str) {
        MessageDigest messageDigest;
        String sha256Str = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
            sha256Str = byte2hex(hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sha256Str;
    }

    @Override
    public String byte2hex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            builder.append(hex);
        }
        return builder.toString().toUpperCase();
    }

}
