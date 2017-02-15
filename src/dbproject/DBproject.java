/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbproject;

import dbproject.Forms.ClientWindow;
import dbproject.Forms.LoginWindow;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Jabarda
 */
public class DBproject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LoginWindow Login = new LoginWindow();
        Login.setVisible(true);
    }
    
}
