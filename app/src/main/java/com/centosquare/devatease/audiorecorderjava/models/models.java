package com.centosquare.devatease.audiorecorderjava.models;


public class models
{
    private String Uri, fileName;
    private boolean isPlaying = false;


    public models(String uri, String fileName, boolean isPlaying) {
        Uri = uri;
        this.fileName = fileName;
        this.isPlaying = isPlaying;
    }

    public String getUri() {
        return Uri;
    }

    public void setUri(String uri) {
        Uri = uri;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}
