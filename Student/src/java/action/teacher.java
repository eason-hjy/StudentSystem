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
public class teacher {
    private String tusername;//教师账号
    private String tpassword;//教师密码
    private String tname;//教师名字
    private int tid;//教师编号
     @Inject
    Data d;
    Map s=new HashMap();
    public void settusername(String t){
        this.tusername=t;
    }
    public void settpassword(String t){
        this.tpassword=t;
    }
    public void settname(String t){
        this.tname=t;
    }
    public void settid(int t){
        this.tid=t;
    }
    public String gettusername(){
        return tusername;
    }
    public String gettpassword(){
        return tpassword;
    }
    public String gettname(){
        return tname;
    }
    public int gettid(){
        return tid;
    } 
    public String submit(){
         return "/index.xhtml";
     }
     public String sign(){         
       if(d.signt(tid,tpassword)!=null) {
           this.tname=d.signt(tid,tpassword);
           return "/index1/tea.xhtml";
       }
       return "erro.xhtml";
     }
     public String login(){
         if(d.logint(tusername, tid, tpassword, tname)) return"suce.xhtml";
         return "erro.xhtml";
     }
     public void getinfo(){
         s=d.getinfot(tid);
         this.tname=s.get("tname").toString();
         this.tpassword=s.get("tpassword").toString();
         this.tusername=s.get("tusername").toString();   
     }
     public void edit(){
         d.delete2(tid);
         d.logint(tusername, tid, tpassword, tname);
     }
}
