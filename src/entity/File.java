package entity;

public class File {
    private String uuid;
    private String filename;
    private String filesize;
    private String uploader;
    private String uptime;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    @Override
    public String toString() {
        return "File{" +
                "uuid='" + uuid + '\'' +
                ", filename='" + filename + '\'' +
                ", filesize='" + filesize + '\'' +
                ", uploader='" + uploader + '\'' +
                ", update=" + uptime +
                '}';
    }

    public File(String uuid, String filename, String filesize, String uploader, String uptime) {
        this.uuid = uuid;
        this.filename = filename;
        this.filesize = filesize;
        this.uploader = uploader;
        this.uptime = uptime;
    }

}
