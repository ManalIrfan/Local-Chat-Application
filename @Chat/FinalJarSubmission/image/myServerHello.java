import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/* @author Manal Irfan Khalil, Reem Al Mulla, Ahmed Abdelaziz, Saeed Ghafli
 * Description : The Server
 * 4th April, 2019
 * @Version 1.4
 */
public class myServerHello implements Runnable, ActionListener {
   
    private static Vector<BufferedWriter> clients = new Vector<BufferedWriter>();
    private static JTextArea serverPane = new JTextArea();
    private static startClient x;
    private static String userName;
    private Socket sockConnection;
    private ArrayList<String> names = new ArrayList<String>();
    private JFrame jf = new JFrame();
    private JLabel title = new JLabel("@Chat Server",JLabel.CENTER);
    private JButton exit = new JButton("End Server");                                     //Global Varibles


    public myServerHello()                                                                // Server GUI constructor 
    {
      serverPane.setEditable(false);
      jf.setLocationRelativeTo(null);
      jf.setSize(300,300);
      jf.setForeground(Color.RED);
      title.setForeground(new Color(55,39,114));
      title.setFont(new Font("Times New Roman",Font.BOLD,24));
      jf.setTitle("Server");
      jf.add(title, BorderLayout.NORTH);
      jf.add(serverPane,BorderLayout.CENTER);
      jf.add(exit,BorderLayout.SOUTH);  
      jf.setVisible(true);  
      exit.addActionListener(this);
    
    }
    
    public void actionPerformed(ActionEvent e)                             // Action Performed Method
    {
      if(e.getSource() == exit)
      {
         System.exit(0);
      
      }
      
    }
    
   

    public myServerHello(Socket s) {                                    // constructor @param Socket s
       
    
        try {
            sockConnection = s;                                         //Keep checking for incoming connection 
        } catch (Exception e) {
            System.out.println("Error is in the server constructor");
        }


    }


    public void run() {                                              // Run method to send the messages to all the clients connected to the server
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(sockConnection.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(sockConnection.getOutputStream()));

            clients.add(writer);

            while (true) {
                String data1 = reader.readLine().trim();
                serverPane.append("Recieved " + data1 + "\n");
                System.out.println("Recieved : " + data1);

                for (int i = 0; i < clients.size(); i++) {
                    try {

                        BufferedWriter bw = (BufferedWriter) clients.get(i);
                        bw.write(data1); // this shows the userName + msg
                        bw.write("\r\n");
                        bw.flush();

                    } catch (Exception e) {                                                        // Error Handling 
                        System.out.println("Error in for loop run method");
                    }

                }

            }

        } catch (Exception e) {
            serverPane.append("Client has disconnected \n");
            System.out.println("Client has disconnected");
        }

    }

    public static void main(String[] args) throws Exception {                             // Main method to run the server 
        
        myServerHello x = new myServerHello(); 
        
        serverPane.append("Threaded Chat Server is Running..... \n");
        System.out.println("Threaded Chat Server is Running ...");
        ServerSocket mySocket = new ServerSocket(16789);

        while (true) {
            Socket sock = mySocket.accept();
            myServerHello server = new myServerHello(sock);
            Thread serverThread = new Thread(server);
            serverThread.start();
        }


    }

}