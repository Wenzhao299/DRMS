package service.impl;

import dao.FileDao;
import entity.File;
import service.FileService;

import java.util.List;

public class FileServiceImpl implements FileService {
    private FileDao fd = new dao.impl.FileDaoImpl();

    @Override
    public boolean uploadFile(String uuid, String filename, String filesize, String uploader, String uptime) {
        return fd.uploadFile(uuid, filename, filesize, uploader, uptime);
    }

    @Override
    public List<File> showFile() {
        return fd.showFile();
    }

    @Override
    public List<String> getMacList() throws Exception {
        return fd.getMacList();
    }
}
