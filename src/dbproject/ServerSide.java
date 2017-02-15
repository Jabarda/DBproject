/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbproject;

import static dbproject.Connection.SendToClient;
import dbproject.Forms.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.DriverManager;
import javax.swing.JTextArea;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Jabarda
 */
public class ServerSide {
    static JTextArea area;
    static DataOutputStream out;
    
    Connection c = null;
    Statement stmt = null;
    
    
    public static void connection(){
        Connection c = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/DBProject",
            "postgres", "123");
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
   }

    public static void SignUp(String s) throws SQLException
    {
        boolean flag=false;
        String Login="",Password="";
        int i=1;
        while (s.charAt(i)!=';'){
            Login+=s.charAt(i);
            i++;
        }
        i++;
        s+=';';
        while (s.charAt(i)!=';'){
            Password+=s.charAt(i);
            i++;
        }
        area.append("Get Command: SignUp \nLogin : " + Login + "\nPassword : " +Password);
        Connection c = null;
        Statement stmt = null;
        try {
             Class.forName("org.postgresql.Driver");
             c = DriverManager
             .getConnection("jdbc:postgresql://localhost:5432/DBProject",
             "postgres", "123");
            c.setAutoCommit(false);
             System.out.println("Opened database successfully");
          
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT max(id) FROM users;" );
            rs.next();
            int max=rs.getInt("max");
            max++;
            String sql = "INSERT INTO users (id, username, password, money) "
                      + "VALUES ("+max+", '"+Login+"','"+Password+"',0 );";
            stmt.executeUpdate(sql);
            rs.close();
            stmt.close();
            c.commit();
            c.close();  
        } catch (Exception e) {
            flag=true;
            area.append( e.getClass().getName()+": "+ e.getMessage() ); 
            try {
                out.writeUTF("Admin is already taken!");
            } catch (IOException ex) {
                Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (flag==false) 
        {
            area.append("Success!");
            try {
                LogIn(s);
            } catch (IOException ex) {
                Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
    public static void LogIn(String s) throws IOException
    {
        boolean flag=false;
        String Login="",Password="";
        int i=1;
        System.out.println("asd "+s);
        while (s.charAt(i)!=';'){
            Login+=s.charAt(i);
            i++;
        }
        i++;
        s+=';';
        while (s.charAt(i)!=';'){
            Password+=s.charAt(i);
            i++;
        }
        area.append("\nGet Command: LogIn Login : " + Login + "Password : " +Password);
        Connection c = null;
        Statement stmt = null;
        try {
             Class.forName("org.postgresql.Driver");
             c = DriverManager
             .getConnection("jdbc:postgresql://localhost:5432/DBProject",
             "postgres", "123");
            c.setAutoCommit(false);
             System.out.println("Opened database successfully");
          
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT id,password FROM users "
                    + "where username='"+Login+"'" );
            rs.next();
            int id=rs.getInt("id");
            if (Password.equals(rs.getString("password"))) 
                out.writeUTF("R"+Integer.toString(id));
            else out.writeUTF("W");
            
            rs.close();
            stmt.close();
            c.commit();
            c.close();  
        } catch (Exception e) {
            flag=true;
            out.writeUTF("W");
            area.append( e.getClass().getName()+": "+ e.getMessage() ); 
        }
    }
    
    public static void UpDateMoney(String id)
    {
        id=id.substring(1);
        area.append("\nGet Command: Update Money ID : " + id);
        Connection c = null;
        Statement stmt = null;
        try {
             Class.forName("org.postgresql.Driver");
             c = DriverManager
             .getConnection("jdbc:postgresql://localhost:5432/DBProject",
             "postgres", "123");
            c.setAutoCommit(false);
             System.out.println("Opened database successfully");
          
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT money FROM users "
                    + "where id="+id );
            rs.next();
            int money=rs.getInt("money");
            
                out.writeUTF(Integer.toString(money));
            
            
            rs.close();
            stmt.close();
            c.commit();
            c.close();  
        } catch (Exception e) {
            try {
                out.writeUTF("W");
            } catch (IOException ex) {
                Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
            }

            area.append( e.getClass().getName()+": "+ e.getMessage() ); 
        }
    }
    
    public static void UpDatePayment(String id)
    {
        id=id.substring(1);
        area.append("\nGet Command: Update Payments ID : " + id);
        Connection c = null;
        Statement stmt = null;
        try {
             Class.forName("org.postgresql.Driver");
             c = DriverManager
             .getConnection("jdbc:postgresql://localhost:5432/DBProject",
             "postgres", "123");
            c.setAutoCommit(false);
             System.out.println("Opened database successfully");
          
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT date, amount, cardnumber FROM payments "
                    + "where userid="+id+"" );
            while (rs.next()){
                Date time=rs.getDate("date");
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                int amount = rs.getInt("amount");
                int cardnumber=rs.getInt("cardnumber");
                out.writeUTF(df.format(time)+" "+amount+" "+cardnumber);
            }
            out.writeUTF("end");
            rs.close();
            stmt.close();
            c.commit();
            c.close();  
        } catch (Exception e) {
            try {
                out.writeUTF("W");
            } catch (IOException ex) {
                Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
            }

            area.append( e.getClass().getName()+": "+ e.getMessage() ); 
        }
    }
    
    public static void UpDatePriceList(String id)
    {
        id=id.substring(1);
        area.append("\nGet Command: Update prices ID : " + id);
        Connection c = null;
        Statement stmt = null;
        try {
             Class.forName("org.postgresql.Driver");
             c = DriverManager
             .getConnection("jdbc:postgresql://localhost:5432/DBProject",
             "postgres", "123");
            c.setAutoCommit(false);
             System.out.println("Opened database successfully");
          
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT price FROM functionlist ORDER BY id");
            while (rs.next()){
                
                int amount = rs.getInt("price");
                out.writeUTF(Integer.toString(amount));
            }
            out.writeUTF("end");
            rs.close();
            stmt.close();
            c.commit();
            c.close();  
        } catch (Exception e) {
            try {
                out.writeUTF("W");
            } catch (IOException ex) {
                Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
            }

            area.append( e.getClass().getName()+": "+ e.getMessage() ); 
        }
    }
    
    public static void UpDateLiciencelist(String id)
    {
        id=id.substring(1);
        area.append("\nGet Command: Update Licience List ID : " + id);
        Connection c = null;
        Statement stmt = null;
        try {
             Class.forName("org.postgresql.Driver");
             c = DriverManager
             .getConnection("jdbc:postgresql://localhost:5432/DBProject",
             "postgres", "123");
            c.setAutoCommit(false);
             System.out.println("Opened database successfully");
          
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT starttime, endtime, "
                    + "function, date_purchase, IsPromo"
                    + " FROM licienselist "
                    + "where userid="+id+"" );
            while (rs.next()){
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date starttime=rs.getDate("starttime");
                Date endtime=rs.getDate("endtime");
                int func=rs.getInt("function");
                Date purchaseDate=rs.getDate("date_purchase");
                boolean IsPromo=rs.getBoolean("IsPromo");
                out.writeUTF(df.format(starttime)+" "
                        +df.format(endtime)+" "
                        +Integer.toString(func)+" "
                        +df.format(purchaseDate));
            }
            out.writeUTF("end");
            rs.close();
            stmt.close();
            c.commit();
            c.close();  
        } catch (Exception e) {
            try {
                out.writeUTF("W");
            } catch (IOException ex) {
                Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
            }

            area.append( e.getClass().getName()+": "+ e.getMessage() ); 
        }
    }
    
    public static void UpDateAccess(String s){
        String id=s.substring(1);
        area.append("\nGet Command: Update Access ID : " + id);
        Connection c = null;
        Statement stmt = null;
        try {
             Class.forName("org.postgresql.Driver");
             c = DriverManager
             .getConnection("jdbc:postgresql://localhost:5432/DBProject",
             "postgres", "123");
            c.setAutoCommit(false);
             System.out.println("Opened database successfully");
          
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT function FROM licienselist " +
            "where (userid="+id+" and (starttime<=NOW() and NOW()<=endtime)) order by function");
            while (rs.next()){
                
                int amount = rs.getInt("function");
                out.writeUTF(Integer.toString(amount));
            }
            out.writeUTF("end");
            rs.close();
            stmt.close();
            c.commit();
            c.close();  
        } catch (Exception e) {
            try {
                out.writeUTF("W");
            } catch (IOException ex) {
                Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
            }

            area.append( e.getClass().getName()+": "+ e.getMessage() ); 
        }
    }
    
    public static void Payment(String s)
    {
        String id="",Amount="",CardNumber="";
        
        int i=2;
       
        while (s.charAt(i)!=';'){
            id+=s.charAt(i);
            i++;
        }
        i++;
        s+=';';
        while (s.charAt(i)!=';'){
            Amount+=s.charAt(i);
            i++;
        }
        i++;
        while (s.charAt(i)!=';'){
            CardNumber+=s.charAt(i);
            i++;
        }
        area.append("Get Command: Payment \nID : " +id);
        Connection c = null;
        Statement stmt = null;
        try {
             Class.forName("org.postgresql.Driver");
             c = DriverManager
             .getConnection("jdbc:postgresql://localhost:5432/DBProject",
             "postgres", "123");
            c.setAutoCommit(false);
            
            
            stmt = c.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT money from users where id="+id);
            rs.next();
            int current=rs.getInt("money");
            current+=Integer.parseInt(Amount);
            
            String sql = "UPDATE users set money = "+current+"where id="+id;
            stmt.executeUpdate(sql);
            rs.close();
            rs = stmt.executeQuery("select max(id) from payments");
            rs.next();
            int buf = rs.getInt("max")+1;
            sql = "insert into payments (id,date,amount,userid,cardnumber)" +
            "values ("+buf+",NOW(),"+Amount+","+id+","+CardNumber+")";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            rs.close();
            out.writeUTF("W");
            
           
            stmt.close();
            c.commit();
            c.close();  
        } catch (Exception e) {
            try {
                out.writeUTF("W");
            } catch (IOException ex) {
                Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
            }

            area.append( e.getClass().getName()+": "+ e.getMessage() ); 
        }
        
    }
    
    public static void BuyFunc(String s)
    {
        String id="",From="",To="", Func="",Price="";
        //
        int i=2;
        System.out.println(s);
        while (s.charAt(i)!=';'){
            id+=s.charAt(i);
            i++;
        }
        i++;
        s+=';';
        while (s.charAt(i)!=';'){
            From+=s.charAt(i);
            i++;
        }
        i++;
        while (s.charAt(i)!=';'){
            To+=s.charAt(i);
            i++;
        }
        i++;
        while (s.charAt(i)!=';'){
            Func+=s.charAt(i);
            i++;
        }
        i++;
        while (s.charAt(i)!=';'){
            Price+=s.charAt(i);
            i++;
        }
        //
        area.append("\nGet Command: Buy function ID : " +id);
        Connection c = null;
        Statement stmt = null;
        try {
             Class.forName("org.postgresql.Driver");
             c = DriverManager
             .getConnection("jdbc:postgresql://localhost:5432/DBProject",
             "postgres", "123");
            c.setAutoCommit(false);
            
            
            stmt = c.createStatement();
            String sql = "UPDATE users set money = "+Price+"where id="+id;
            stmt.executeUpdate(sql);
            ResultSet rs = stmt.executeQuery("select max(id) from licienselist");
            rs.next();
            int buf = rs.getInt("max")+1;
            sql = "insert into licienselist (id,starttime,endtime,function,userid,date_purchase,ispromo)" +
            "values ("+buf+",'"+From+"','"+To+"',"+Func+"," +id+", NOW(), false)";
            //System.out.println(sql);
            stmt.executeUpdate(sql);
            rs.close();
            out.writeUTF("W");
            stmt.close();
            c.commit();
            c.close();  
        } catch (Exception e) {
            try {
                out.writeUTF("W");
            } catch (IOException ex) {
                Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
            }

            area.append( e.getClass().getName()+": "+ e.getMessage() ); 
        }
        
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        //connection();
        ServerWindow server = new ServerWindow();
        server.setVisible(true);
        area = server.returnArea();
        int port = 2154;
        try {
            ServerSocket ss = new ServerSocket(port);
            while(true){
                Socket soket = ss.accept();
                InputStream in = soket.getInputStream();
                out = new DataOutputStream(soket.getOutputStream());
                DataInputStream din = new DataInputStream(in);
                String s=din.readUTF();
                if ( s.charAt(0)=='S') SignUp(s);
                if ( s.charAt(0)=='L') LogIn(s);
                if ( s.charAt(0)=='P') UpDatePayment(s);
                if ( s.charAt(0)=='M') UpDateMoney(s);
                if ( s.charAt(0)=='I') UpDateLiciencelist(s);
                if ( s.charAt(0)=='N') Payment(s);
                if ( s.charAt(0)=='C') UpDatePriceList(s);
                if ( s.charAt(0)=='B') BuyFunc(s);
                if ( s.charAt(0)=='A') UpDateAccess(s);
                //area.append("\nGet Command: " + s + "\n");
                //out.writeUTF("1");
                } 
            }
       
        catch(Exception e){
            e.printStackTrace();
        }
     
    }
    
}
