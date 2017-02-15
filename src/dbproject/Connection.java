/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Jabarda
 */
public class Connection {
   public static String SendToServer(String s){
        int port = 2154;
        String Answer = null;
        Socket socket = new Socket();
        String addres = "127.0.0.1";
        InetAddress ipAddress = null;
        try {
            ipAddress = InetAddress.getByName(addres);
            socket = new Socket(ipAddress, port);
        }
        catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        //----------------------------------------------

        try{
            DataOutputStream outD = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream( socket.getInputStream());
            outD.writeUTF(s);
            Answer = in.readUTF();
            //JOptionPane.showMessageDialog(null, in.readUTF(), "AND THE ANSWER IS", JOptionPane.PLAIN_MESSAGE);
            socket.close();
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, e);
        }
        
       return Answer;
   }
   
   public static String[] GetPayments(String s)
   {
       int port = 2154;
        String Answer[] = new String[100];
        int i=0;
        String inp=null;
        Socket socket = new Socket();
        String addres = "127.0.0.1";
        InetAddress ipAddress = null;
        try {
            ipAddress = InetAddress.getByName(addres);
            socket = new Socket(ipAddress, port);
        }
        catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        //----------------------------------------------

        try{
            DataOutputStream outD = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream( socket.getInputStream());
            outD.writeUTF(s);
            while (!"end".equals(inp)){
                inp = in.readUTF();
                Answer[i]=inp;
                
                i++;
            }
            //JOptionPane.showMessageDialog(null, in.readUTF(), "AND THE ANSWER IS", JOptionPane.PLAIN_MESSAGE);
            socket.close();
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, e);
        }
       
       return Answer;
      
   }
   
   public static void SendToClient(String s){
       
   }
    
}
