import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
/* @author Manal Irfan Khalil, Reem Al Mulla, Ahmed AbdelAziz, Saeed Ghafli
 * Description : The Login GUI 
 * 20th April, 2019
 * @Version 1.4
 */
public class startClient implements ActionListener {
    private JButton jbLogin = new JButton("Login");
    private JButton jbGo = new JButton("Lets Chat");
    private static String name;
    private static JFrame jf = new JFrame();
    private JTextField jfUserName = new JTextField(30);                          //Global attritbutes


    public static void main(String[] args) {                                     // Run the main program 
        startClient x = new startClient();
        System.out.println("Hello");
    }

    public startClient() {                                                       // Login GUI constructor start
        jf.setTitle("Login @Chat");
        jf.setSize(200, 200);
        JLabel userName = new JLabel("Enter a Username");


        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();


        jp1.add(userName);
        jp1.add(jfUserName);


        jp2.add(jbLogin);
        jp2.add(jbGo);

        jf.add(jp1, BorderLayout.NORTH);
        jf.add(jp2, BorderLayout.SOUTH);
        jf.setVisible(true);
        jfUserName.setBackground(new Color(249, 235, 223));
        jp1.setBackground(new Color(100, 93, 215));
        jp2.setBackground(new Color(100, 93, 215));

        jfUserName.setBorder(new LineBorder(new Color(252, 129, 74), 1));
        

        jf.pack();

        jbGo.setEnabled(false);

        jf.setLocationRelativeTo(null);

        jbLogin.addActionListener(this);
        jbGo.addActionListener(this);
        

    }

    public void actionPerformed(ActionEvent e) {                              // Get UserName 
        if (e.getSource() == jbLogin) {
         
            name = jfUserName.getText();
            
            if(name.equals(""))
            { JOptionPane.showMessageDialog(null,"Please enter a Username");
            }else {
            
               jbGo.setEnabled(true);
            
            }
            
            jbLogin.setEnabled(false);
            
           

              

        }else if(e.getSource() == jbGo)
        {   
             try{
             
                setName(name);
                String user = getName();
                myChatHello x = new myChatHello(user);                        //Pass on the userame and start the thread

                Thread t1 = new Thread(x);
                t1.start();

                jf.setVisible(false);             
            
            
            } catch (Exception ef) {
                ef.printStackTrace();
            }
            
                     
            
        } 
        
      }

    // @param String name    
    public void setName(String n) 
    {       
        name = n;
    }

   
    // @return String name  
    public static String getName() 
    {
        return name;
    }


}




