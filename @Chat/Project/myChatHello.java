import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

/* @author Manal Irfan Khalil, Reem Al Mulla, Ahmed AbdelAziz, Saeed Ghafli
 * Description : The client GUI
 * 20th April, 2019
 * @Version 1.4
 */
public class myChatHello implements Runnable {
    private JTextField tx;
    private JTextArea ta;
    private String login;
    private BufferedWriter writer;
    private BufferedReader reader;
    private ArrayList<String> names = new ArrayList<String>();                // Decalare the global variables


    public myChatHello(String l) {                                            // Constructor to pass on the parameter
                                                                              // @param login name 
        login = l;
        names.add(login);
        JPanel jpTop = new JPanel();
        JLabel logo = new JLabel();
        ImageIcon icon = new ImageIcon("image/Icon2.png");
        logo.setIcon(icon);
        jpTop.add(logo);

        JFrame jf = new JFrame("@Chat");
        jf.setSize(500, 500);

        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());

        JPanel p2 = new JPanel();
        p2.setLayout(new BorderLayout());

        tx = new JTextField();
        p1.add(tx, BorderLayout.CENTER);

        JButton b1 = new JButton("Send");
        p1.add(b1, BorderLayout.EAST);

        ta = new JTextArea();
        p2.add(ta, BorderLayout.CENTER);
        p2.add(p1, BorderLayout.SOUTH);
        p2.add(jpTop, BorderLayout.NORTH);
        ta.setBackground(new Color(153, 200, 227));
        ta.setFont(new Font("Venus Rising", Font.PLAIN,14));
        ta.setForeground(new Color(55,39,114));
        ta.setEditable(false); 
        jf.setContentPane(p2);
        jf.setLocationRelativeTo(null);

        try {                                                                                   // open the socket here 

            Socket socketClient = new Socket(InetAddress.getLocalHost(), 16789);
            writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Cannot Connet");
            System.out.println("Cannot Connect");
        }

        tx.addKeyListener(new KeyListener() {                                                   // send message by pressing enter
                                     public void keyPressed(KeyEvent ev) {
                                     
                                     if(ev.getKeyCode() == KeyEvent.VK_ENTER)
                                     {          
                                       String s = login + " : " + tx.getText();
                                       tx.setText("");
                                       try {
                                           //encrypt string first
                                           s = EncryptDecryptUtil.encrypt(s);
                                           writer.write(s);
                                           writer.write("\r\n");
                                           writer.flush();
                                       } catch (Exception e) {
                                           e.printStackTrace();
                                       }
                                    }  
                                 }
                                 
                                 public void keyTyped(KeyEvent g){}
                                 public void keyReleased(KeyEvent h){}
                                 
                               }  
        );
        b1.addActionListener(new ActionListener() {                                          // send message by clickiing the button send 
                                     public void actionPerformed(ActionEvent e) {
                                     
                                     if(e.getSource() == b1)
                                     {          
                                       String s = login + " : " + tx.getText();
                                       tx.setText("");
                                       try {
                                           //encrypt string first
                                           s = EncryptDecryptUtil.encrypt(s);
                                           writer.write(s);
                                           writer.write("\r\n");
                                           writer.flush();
                                       } catch (Exception ef) {
                                           ef.printStackTrace();
                                       }
                                    }  
                                 }
                                 
                                                                  
                               }  
        );


        jf.setVisible(true);
        jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);

    } // end of constructor 


    public void run() {                                                    // keep reading the messages through run method
        try {
            String serverMsg = "";
            ta.append(login + " has connected \n");
            while ((serverMsg = reader.readLine()) != null) {
                //decrypt message first
                System.out.println("from Server: " + serverMsg);
                serverMsg = EncryptDecryptUtil.decrypt(serverMsg);         // encrypt the message 
                ta.append(serverMsg + "\n");
            }

        } catch (Exception e) {
            System.out.println("Error in run method client");
            System.out.println(e.getMessage());
        }

    }  // end of run method         

}   
 
   
   
 

   
