package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
    public int modifyUSERData(String sql, Object[] obj) {
        Connection conn = ConnUtil.getUSERConn();
        int result = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i+1,obj[i]);
            }
            result = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    public ResultSet getUSERDataByAny(String sql, Object[] obj) {
        Connection conn = ConnUtil.getUSERConn();
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i+1,obj[i]);
            }
            rs = ps.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }
    public int modifyDRMSData(String sql, Object[] obj) {
        Connection conn = ConnUtil.getDRMSConn();
        int result = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i+1,obj[i]);
            }
            result = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    public ResultSet getDRMSDataByAny(String sql, Object[] obj) {
        Connection conn = ConnUtil.getDRMSConn();
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i+1,obj[i]);
            }
            rs = ps.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }
}
