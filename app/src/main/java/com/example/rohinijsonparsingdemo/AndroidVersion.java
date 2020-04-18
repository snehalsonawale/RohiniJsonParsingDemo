package com.example.rohinijsonparsingdemo;

public class AndroidVersion {
    private String ver;
    private String name;
    private String api;

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public AndroidVersion(String ver, String name, String api) {
        this.ver = ver;
        this.name = name;
        this.api = api;
    }

    @Override
    public String toString() {
        return "AndroidVersion{" +
                "ver='" + ver + '\'' +
                ", name='" + name + '\'' +
                ", api='" + api + '\'' +
                '}';
    }
}
