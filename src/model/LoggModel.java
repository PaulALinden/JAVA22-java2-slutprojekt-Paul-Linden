package model;

public class LoggModel {
    String filePath;

    public LoggModel(String filePath){
        this.filePath = filePath;
    }
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
