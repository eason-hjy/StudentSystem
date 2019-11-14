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
public class student {
    private String susername;//学生账号
    private String spassword;//学生密码
    private String sname;//学生名
    private int testscore=0;//综合分
    private int prefence=0;//表现分
    private int attendance=0;//出勤分
    private int sid;//学生编号
    private int sex;//性别
    private String classnumber;//班级编号
     @Inject
    Data d;
    Map s=new HashMap();
    ArrayList courselist=new ArrayList();
    public ArrayList getCourselist(){
        return courselist;
    }
    public void selectbysid(){
        courselist.clear();
        courselist=d.selectcoursebysid(sid);
    }
    public void setsusername(String Susername){
        this.susername=Susername;
    }
    public void setspassword(String Spassword){
        this.spassword=Spassword;
    }
    public void setsname(String sname){
        this.sname=sname;
    }
    public void settestscore(int s){
        this.testscore=s;
    }
    public void setprefence(int s){
        this.prefence=s;
    }
    public void setattendance(int s){
        this.attendance=s;
    }
    public void setsid(int s){
        this.sid=s;
    }
    public void setsex(int s){
        this.sex=s;
    }
    public void setclassnumber(String classnumber){
        this.classnumber=classnumber;
    }        
    public String getsusername(){
        return susername;
    }
    public String getspassword(){
        return spassword;
    }
    public String getsname(){
        return sname;
    }
    public String getclassnumber(){
        return classnumber;
    }
    public int gettestscore(){
        return testscore;
    }
     public int getprefence(){
         return prefence;
     }
     public int getattendance(){
          return attendance;
      }
     public int getsid(){
           return sid;
       }
     public int getsex(){
         return sex;
     }
     public String submit(){
         return "/index.xhtml";
     }
     public String sign(){         
       if(d.signs(sid,spassword)!=null) {
           this.sname=d.signs(sid,spassword);
           return "/index1/stu.xhtml";
       }
       return "erro.xhtml";
     }
     public String login(){
        if(d.logins(sid, sex, classnumber, sname, spassword, susername,attendance,prefence,testscore))return "suce.xhtml";
         return "erro.xhtml";
     }
     public void getinfo(){
         s=d.getinfos(sid);
         this.sname=s.get("sname").toString();
         this.sex=Integer.parseInt(s.get("sex").toString());
         this.classnumber=s.get("classnumber").toString();
         this.spassword=s.get("spassword").toString();
         this.susername=s.get("susername").toString();
         this.testscore=Integer.parseInt(s.get("testscore").toString());
         this.prefence=Integer.parseInt(s.get("prefence").toString());
         this.attendance=Integer.parseInt(s.get("attendance").toString());                    
     }
        public void edit(){
           d.delete1(sid);
           d.logins(sid, sex, classnumber, sname, spassword, susername,attendance,prefence,testscore);
      }
        public void selectcourse(){
        courselist.clear();
        courselist=d.selectcoursebysid(sid);
        }
}
