package com.example.rohinijsonparsingdemo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Network {


    @SerializedName("StData")
    @Expose
    private Object stData;
    @SerializedName("TeData")
    @Expose
    private List<TeDatum> teData = null;

    public Object getStData() {
        return stData;
    }

    public void setStData(Object stData) {
        this.stData = stData;
    }

    public List<TeDatum> getTeData() {
        return teData;
    }

    public void setTeData(List<TeDatum> teData) {
        this.teData = teData;
    }

    public static class TeDatum {

        @SerializedName("Tid")
        @Expose
        private Integer tid;
        @SerializedName("fname")
        @Expose
        private String fname;
        @SerializedName("lname")
        @Expose
        private String lname;
        @SerializedName("Subject_id")
        @Expose
        private String subjectId;
        @SerializedName("Subject_Name")
        @Expose
        private String subjectName;
        @SerializedName("SubSubject_id")
        @Expose
        private String subSubjectId;
        @SerializedName("SubSubject_Name")
        @Expose
        private String subSubjectName;

        public Integer getTid() {
            return tid;
        }

        public void setTid(Integer tid) {
            this.tid = tid;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getLname() {
            return lname;
        }

        public void setLname(String lname) {
            this.lname = lname;
        }

        public String getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(String subjectId) {
            this.subjectId = subjectId;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }

        public String getSubSubjectId() {
            return subSubjectId;
        }

        public void setSubSubjectId(String subSubjectId) {
            this.subSubjectId = subSubjectId;
        }

        public String getSubSubjectName() {
            return subSubjectName;
        }

        public void setSubSubjectName(String subSubjectName) {
            this.subSubjectName = subSubjectName;
        }

        @Override
        public String toString() {
            return "TeDatum{" +
                    "tid=" + tid +
                    ", fname='" + fname + '\'' +
                    ", lname='" + lname + '\'' +
                    ", subjectId='" + subjectId + '\'' +
                    ", subjectName='" + subjectName + '\'' +
                    ", subSubjectId='" + subSubjectId + '\'' +
                    ", subSubjectName='" + subSubjectName + '\'' +
                    '}';
        }
    }


}
