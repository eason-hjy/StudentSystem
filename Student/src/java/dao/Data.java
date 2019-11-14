/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Administrator
 */
import javax.enterprise.context.Dependent;
@Dependent
public class Data {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/work?useUnicode=true&characterEncoding=utf-8";
   
   static final String USER = "root";
   static final String PASS = "";
   Connection con = null; 
   Statement stmt = null;
    ResultSet rs=null;
     String sql=null;
   Map s=new HashMap();
   ArrayList courselist=new ArrayList();
   public Connection getConnection(){//连接       
       try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL,USER,PASS);
            }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();   
            }catch(Exception e){
            //Handle errors for Class.forName
             e.printStackTrace();
            }
        return con;
    }
   public String signs(int id,String pass){//学生登录验证
       String result= null;
       try{
       con=getConnection();
       stmt=con.createStatement();
       sql="select * from student where sid="+id;
       //System.out.println(sql);
       rs=stmt.executeQuery(sql);
        while(rs.next()){          
        String p=rs.getString("spassword");
        String t=rs.getString("sname");
        if(p.equals(pass)) result=t;
      }
   }catch(Exception e){       
   }
      return result;
   }
      public String signt(int id,String pass){//教师登录验证
       String result= null;
       try{
       con=getConnection();
       stmt=con.createStatement();
       sql="select * from teacher where tid="+id;
       //System.out.println(sql);
       rs=stmt.executeQuery(sql);
        while(rs.next()){          
        String p=rs.getString("tpassword");
        String t=rs.getString("tname");
        if(p.equals(pass)) result=t;
      }
   }catch(Exception e){       
   }
      return result;
   }
      public boolean logins(int sid,int sex,String classnumber,String sname,String spassword,String susername,int attendance,int prefence,int testscore){
        boolean re=false;
        try{
            con=getConnection();
            stmt=con.createStatement();
            sql="INSERT INTO `student` (`sid`, `sex`, `classnumber`, `attendance`, `prefence`, `testscore`, `sname`, `spassword`, `susername`) VALUES ('"+sid+"', '"+sex+"', '"+classnumber+"', '"+attendance+"', '"+prefence+"', '"+testscore+"', '"+sname+"', '"+spassword+"', '"+susername+"')";           
            if(stmt.executeUpdate(sql)!=0) re=true;
        }catch(Exception e){       
        }
        return re;
      }
      public boolean logint(String tusername,int tid,String tpassword,String tname){
        boolean re=false;
        try{
            con=getConnection();
            stmt=con.createStatement();
            sql="INSERT INTO `teacher` (`tusername`, `tid`, `tpassword`, `tname`) VALUES ('"+tusername+"', '"+tid+"', '"+tpassword+"', '"+tname+"')" ;           
            if(stmt.executeUpdate(sql)!=0) re=true;
        }catch(Exception e){       
        }
        return re;
      }
      public Map getinfos(int id){//获取账户信息
        try{
                 con=getConnection();
                 stmt=con.createStatement();
                 sql="select * from student where sid ="+id;
                 rs=stmt.executeQuery(sql);
                  while(rs.next()){          
                   s.put("sid",rs.getInt("sid"));
                   s.put("sex",rs.getInt("sex"));
                   s.put("sname",rs.getString("sname"));
                   s.put("spassword",rs.getString("spassword"));
                   s.put("susername",rs.getString("susername"));
                   s.put("attendance",rs.getString("attendance"));
                   s.put("classnumber",rs.getString("classnumber"));
                   s.put("prefence",rs.getString("prefence"));
                   s.put("testscore",rs.getString("testscore"));
                }
             }catch(Exception e){       
             }
               return s; 
      }
      public Map getinfot(int id){//获取账户信息
        try{
                 con=getConnection();
                 stmt=con.createStatement();
                 sql="select * from teacher where tid ="+id;
                 rs=stmt.executeQuery(sql);
                  while(rs.next()){          
                   s.put("tid",rs.getInt("tid"));
                   s.put("tname",rs.getString("tname"));
                   s.put("tpassword",rs.getString("tpassword"));
                   s.put("tusername",rs.getString("tusername"));
                }
             }catch(Exception e){       
             }
               return s;           
      }
      public boolean delete1(int id){
          boolean result=false;
          try{
                 con=getConnection();
                 stmt=con.createStatement();
                 sql="delete from student where sid="+id;
                 int rs=stmt.executeUpdate(sql);
                 if(rs!=0) result=true;
             }catch(Exception e){       
             }
               return result;           
      } 
     public boolean delete2(int id){
          boolean result=false;
          try{
                 con=getConnection();
                 stmt=con.createStatement();
                 sql="delete from teacher where tid="+id;
                 int rs=stmt.executeUpdate(sql);
                 if(rs!=0) result=true;
             }catch(Exception e){       
             }
               return result;           
      }
          public ArrayList delete3(Course course){
          courselist.remove(course);
          boolean result=false;
          try{
                 con=getConnection();
                 stmt=con.createStatement();
                 sql="delete from course where sid="+course.getsid();
                 int rs=stmt.executeUpdate(sql);
                 if(rs!=0) result=true;
             }catch(Exception e){       
             }
          
               return courselist;           
      }
     public static class Course {
        private int cid;
        private String cname;
        private int sid;
        private int cscore;
    public Course(int cid,String cname,int sid,int cscore){
        this.cid=cid;this.cname=cname;this.sid=sid;this.cscore=cscore;
    }
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
} 
       public Map selectall(String table){//查询
      try{
       con=getConnection();
       stmt=con.createStatement();
       sql="select * from "+table;
       //System.out.println(sql);
       rs=stmt.executeQuery(sql);
        while(rs.next()){          
         s.put("sid",rs.getInt("sid"));
         s.put("sex",rs.getInt("sex"));
         s.put("sname",rs.getString("sname"));
         s.put("spassword",rs.getString("spassword"));
         s.put("susername",rs.getString("susername"));
      }
   }catch(Exception e){       
   }
      return s;
   }
      public ArrayList<Course> selectcourse(){//查询
      try{
       con=getConnection();
       stmt=con.createStatement();
       sql="select * from course";
       //System.out.println(sql);
       rs=stmt.executeQuery(sql);
        while(rs.next()){          
            Course course=new Course(rs.getInt("cid"),rs.getString("cname"),rs.getInt("sid"),rs.getInt("cscore"));
            courselist.add(course);
      }
   }catch(Exception e){       
   }
      return courselist;
   }
    public ArrayList<Course> selectcoursebysid(int sid){//查询
      try{
       con=getConnection();
       stmt=con.createStatement();
       sql="select * from course where sid="+sid;
       rs=stmt.executeQuery(sql);
        while(rs.next()){          
            Course course=new Course(rs.getInt("cid"),rs.getString("cname"),rs.getInt("sid"),rs.getInt("cscore"));
            courselist.add(course);
      }
   }catch(Exception e){       
   }
      return courselist;
   }
    public ArrayList<Course> editscore(Course course){
        boolean result=false;    
        try{
                 con=getConnection();
                 stmt=con.createStatement();
                 sql="UPDATE course SET cscore="+course.getcscore()+ " WHERE sid="+course.getsid()+" AND cid="+course.getcid();
                 int rs=stmt.executeUpdate(sql);
                 if(rs!=0) result=true;
             }catch(Exception e){       
             }
               return courselist;  
    }
    public ArrayList<Course> selectcoursebycname(String cname){
       try{
       con=getConnection();
       stmt=con.createStatement();
       sql="select * from course where cname='"+cname+"'";
       rs=stmt.executeQuery(sql);
        while(rs.next()){          
            Course course=new Course(rs.getInt("cid"),rs.getString("cname"),rs.getInt("sid"),rs.getInt("cscore"));
            courselist.add(course);
      }
   }catch(Exception e){       
   }
      return courselist;
    }
    public ArrayList<Course> addcourse(int sid,int cid,String cname,int cscore){
        try{
       con=getConnection();
       stmt=con.createStatement();
       sql="INSERT INTO course (cid, cname, sid, cscore) VALUES ("+cid+",'"+cname+"',"+sid+","+cscore+")";
       int rs=stmt.executeUpdate(sql);
   }catch(Exception e){       
   }
        Course course=new Course(cid,cname,sid,cscore);
            courselist.add(course);
      return courselist;       
    }
}


