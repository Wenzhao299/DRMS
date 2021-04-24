package dao.impl;

import dao.FileDao;
import entity.File;
import util.BaseDao;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileDaoImpl extends BaseDao implements FileDao {
    @Override
    public boolean uploadFile(String uuid, String filename, String filesize, String uploader, String uptime) {
        String sql_register = "insert into FileList values(?,?,?,?,?)";
        Object[] obj_register = {uuid, filename, filesize, uploader, uptime};
        int result = this.modifyDRMSData(sql_register, obj_register);
        boolean flag = false;
        if(result>0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<File> showFile() {
        String sql = "select * from FileList";
        Object[] obj = {};
        ResultSet rs = this.getDRMSDataByAny(sql, obj);
        List<File> filelist = new ArrayList();
        try {
            while (rs.next()) {
                try {
                    File file = new File(rs.getString("uuid"),rs.getString("filename"),rs.getString("filesize"),rs.getString("uploader"),rs.getString("uptime"));
                    filelist.add(file);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return filelist;
    }

    @Override
    public List<String> getMacList() throws Exception{
        java.util.Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
        StringBuilder sb = new StringBuilder();
        List<String> tmpMacList=new ArrayList<>();
        while(en.hasMoreElements()) {
            NetworkInterface iface = en.nextElement();
            List<InterfaceAddress> addrs = iface.getInterfaceAddresses();
            for(InterfaceAddress addr : addrs) {
                InetAddress ip = addr.getAddress();
                NetworkInterface network = NetworkInterface.getByInetAddress(ip);
                if(network==null) {
                    continue;
                }
                byte[] mac = network.getHardwareAddress();
                if(mac==null) {
                    continue;
                }
                sb.delete(0, sb.length());
                for (int i = 0; i < mac.length; i++) {
                    sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                }
                tmpMacList.add(sb.toString());
            }
        }
        if(tmpMacList.size()<=0) {
            return tmpMacList;
        }
        List<String> MacList = tmpMacList.stream().distinct().collect(Collectors.toList());
        return MacList;
    }
}
