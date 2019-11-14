/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;
import dao.Data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.*;
import java.util.*;
import javax.inject.Inject;
import javax.enterprise.context.Dependent;
@Dependent
@ManagedBean
@SessionScoped
public class course {
    private int cid;//课程编号
    private String cname;//课程名
    private int cscore;//课程分数
    private int sid;//学生编号
     @Inject
    Data d;
    ArrayList courselist=new ArrayList();
    public void setcid(int g){
        this.cid=g;
    }
    public void setcname(String g){
        this.cname=g;
    }
    public void setcscore(int g){
        this.cscore=g;
    }
    public void setsid(int g){
        this.sid=g;
    }
    public String getcname(){
        return cname;
    }
    public int getcid(){
        return cid;
    }
    public int getcscore(){
        return cscore;
    }
    public int getsid(){
        return sid;
    }
    public void select(){
        courselist.clear();
        courselist=d.selectcourse();     
    }
    public void deleteAction(Data.Course course){
        d.delete3(course);
    }
    public ArrayList getCourselist(){
        return courselist;
    }
    public void editscore(Data.Course course){
        courselist=d.editscore(course);
    }
    public void selectbysid(){
        courselist.clear();
        courselist=d.selectcoursebysid(sid);
    }
    public void selectbycname(){
        courselist.clear();
        courselist=d.selectcoursebycname(cname);
    }
    public void addAction(){
        courselist.clear();
        courselist=d.addcourse(sid,cid,cname,cscore);
    }
}
