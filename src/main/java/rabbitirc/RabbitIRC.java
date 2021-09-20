package rabbitirc;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;

/**
 *
 * @author andrewtaylor
 */
public class RabbitIRC extends javax.swing.JFrame {

    private Socket socket;
    private String server;
    private int port;
    private PrintWriter writer;
    private Thread readThread;
    private Thread pingThread;
    private JFileChooser fileChooser;
    private File currentFile;
    private Color foregroundColor, backgroundColor;
    
    /**
     * Creates new form IRCCliente
     */
    public RabbitIRC() {
        initComponents();
        connectionDialog.setSize(connectionDialog.getPreferredSize());
        fileChooser = new JFileChooser();
    }
    
    private void read(Socket socket) {
        readThread = new Thread(() -> {
           try {
               BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
               while (socket.isConnected()) {
                   String line = reader.readLine();
                   if (line != null)
                        chatWindow.append(line + "\n");
               }
               Thread.sleep(10);
           } catch (IOException | InterruptedException e) {
               System.err.println(e);
           } 
        });
        readThread.start();
    }
    
    private void ping(Socket socket) {
        pingThread = new Thread(() -> {
            while (socket.isConnected()) {
                String msg = "PING " + server + "\n";
                writer.println(msg);
                chatWindow.append(msg);
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
        });
        pingThread.start();
    }
    
    private void connect(String server, int port) {
        try {
            socket = new Socket(server, port);
            writer = new PrintWriter(socket.getOutputStream(), true);
            read(socket);  
            ping(socket);
        } catch (UnknownHostException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
        System.out.println("Connected to " + server + ":" + port);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        connectionDialog = new javax.swing.JDialog();
        serverLabel = new javax.swing.JLabel();
        serverTextField = new javax.swing.JTextField();
        portLabel = new javax.swing.JLabel();
        portTextField = new javax.swing.JTextField();
        messageLabel = new javax.swing.JLabel();
        connectButton = new javax.swing.JButton();
        chatWindowScrollPane = new javax.swing.JScrollPane();
        chatWindow = new javax.swing.JTextArea();
        messageBoxScrollPane = new javax.swing.JScrollPane();
        messageBox = new javax.swing.JTextArea();
        sendButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        connectMenuItem = new javax.swing.JMenuItem();
        disconnectMenuItem = new javax.swing.JMenuItem();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        colorsMenu = new javax.swing.JMenu();
        selectForegroundColor = new javax.swing.JMenuItem();
        selectBackgroundColor = new javax.swing.JMenuItem();
        purpleWhiteColor = new javax.swing.JMenuItem();
        tealWhiteColor = new javax.swing.JMenuItem();
        grayBlueColor = new javax.swing.JMenuItem();

        serverLabel.setText("Server:");

        portLabel.setText("Port:");

        messageLabel.setText("Create a TCP/IP connection with a server and port");

        connectButton.setText("Connect");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout connectionDialogLayout = new javax.swing.GroupLayout(connectionDialog.getContentPane());
        connectionDialog.getContentPane().setLayout(connectionDialogLayout);
        connectionDialogLayout.setHorizontalGroup(
            connectionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(connectionDialogLayout.createSequentialGroup()
                .addGroup(connectionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(connectionDialogLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(connectionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(messageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(connectionDialogLayout.createSequentialGroup()
                                .addGroup(connectionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(serverLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(portLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(connectionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(serverTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(portTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(connectionDialogLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(connectButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        connectionDialogLayout.setVerticalGroup(
            connectionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(connectionDialogLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(messageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(connectionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(serverTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(connectionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(connectButton)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        chatWindow.setColumns(20);
        chatWindow.setRows(5);
        chatWindowScrollPane.setViewportView(chatWindow);

        messageBox.setColumns(20);
        messageBox.setRows(5);
        messageBoxScrollPane.setViewportView(messageBox);

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        fileMenu.setText("File");
        fileMenu.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                fileMenuMenuSelected(evt);
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
        });

        connectMenuItem.setText("Connect");
        connectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(connectMenuItem);

        disconnectMenuItem.setText("Disconnect");
        disconnectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(disconnectMenuItem);

        openMenuItem.setText("Open");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        colorsMenu.setText("Colors");

        selectForegroundColor.setText("Select foreground color");
        selectForegroundColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectForegroundColorActionPerformed(evt);
            }
        });
        colorsMenu.add(selectForegroundColor);

        selectBackgroundColor.setText("Select background color");
        selectBackgroundColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectBackgroundColorActionPerformed(evt);
            }
        });
        colorsMenu.add(selectBackgroundColor);

        purpleWhiteColor.setText("Purple white");
        purpleWhiteColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purpleWhiteColorActionPerformed(evt);
            }
        });
        colorsMenu.add(purpleWhiteColor);

        tealWhiteColor.setText("Teal white");
        tealWhiteColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tealWhiteColorActionPerformed(evt);
            }
        });
        colorsMenu.add(tealWhiteColor);

        grayBlueColor.setText("Gray blue");
        grayBlueColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grayBlueColorActionPerformed(evt);
            }
        });
        colorsMenu.add(grayBlueColor);

        menuBar.add(colorsMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(messageBoxScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(chatWindowScrollPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chatWindowScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(messageBoxScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectMenuItemActionPerformed
        connectionDialog.setVisible(true);
    }//GEN-LAST:event_connectMenuItemActionPerformed

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
       server = serverTextField.getText();
       port = Integer.parseInt(portTextField.getText());
       connect(server, port);
       connectionDialog.setVisible(false);
    }//GEN-LAST:event_connectButtonActionPerformed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        String text = messageBox.getText();
        writer.println(text);
        chatWindow.append(text + "\n");
        messageBox.setText("");
    }//GEN-LAST:event_sendButtonActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        int opt = fileChooser.showOpenDialog(this);
        
        if (opt == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(currentFile))) {
                String text = reader.lines().reduce((partial, s) -> partial + s).toString();
                chatWindow.setText(text);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        int opt = fileChooser.showSaveDialog(this);
        
        if (opt == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            try (PrintWriter writer = new PrintWriter(new FileWriter(currentFile), true)) {
                String text = chatWindow.getText();
                writer.print(text);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void selectForegroundColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectForegroundColorActionPerformed
        Color c = JColorChooser.showDialog(this, "Select a foreground color", foregroundColor);
        if (c != null) {
            setBackgroundColor(c);
        }
    }//GEN-LAST:event_selectForegroundColorActionPerformed

    private void selectBackgroundColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectBackgroundColorActionPerformed
        Color c = JColorChooser.showDialog(this, "Select a background color", backgroundColor);
        if (c != null) {
            setForegroundColor(c);
        }
            
    }//GEN-LAST:event_selectBackgroundColorActionPerformed

    private void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
        chatWindow.setForeground(foregroundColor);
        messageBox.setForeground(foregroundColor);
    }
    
    private void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        chatWindow.setBackground(backgroundColor);
        messageBox.setBackground(backgroundColor);
    }
    
    private void setColors(Color foregroundColor, Color backgroundColor) {
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
        chatWindow.setForeground(foregroundColor);
        chatWindow.setBackground(backgroundColor);
        messageBox.setForeground(foregroundColor);
        messageBox.setBackground(backgroundColor);
    }
    
    private void tealWhiteColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tealWhiteColorActionPerformed
        setColors(Color.WHITE, new Color(0, 153, 153, 255));
    }//GEN-LAST:event_tealWhiteColorActionPerformed

    private void purpleWhiteColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purpleWhiteColorActionPerformed
        setColors(Color.WHITE, new Color(153, 0, 153, 255));
    }//GEN-LAST:event_purpleWhiteColorActionPerformed

    private void grayBlueColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grayBlueColorActionPerformed
        setColors(new Color(0, 0, 204, 255), new Color(204, 204, 204, 255));
    }//GEN-LAST:event_grayBlueColorActionPerformed

    private void disconnectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectMenuItemActionPerformed
        try {
            socket.close();
            socket = null;
            chatWindow.append("Disconnected from " + server + ":" + port);
        } catch (IOException e) {
            System.err.println(e);
        }
    }//GEN-LAST:event_disconnectMenuItemActionPerformed

    private void fileMenuMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_fileMenuMenuSelected
        if (socket != null) {
            connectMenuItem.setEnabled(false);
            disconnectMenuItem.setEnabled(true);
        }
        else {
            connectMenuItem.setEnabled(true);
            disconnectMenuItem.setEnabled(false);
        }
    }//GEN-LAST:event_fileMenuMenuSelected

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RabbitIRC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RabbitIRC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RabbitIRC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RabbitIRC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RabbitIRC().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatWindow;
    private javax.swing.JScrollPane chatWindowScrollPane;
    private javax.swing.JMenu colorsMenu;
    private javax.swing.JButton connectButton;
    private javax.swing.JMenuItem connectMenuItem;
    private javax.swing.JDialog connectionDialog;
    private javax.swing.JMenuItem disconnectMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem grayBlueColor;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTextArea messageBox;
    private javax.swing.JScrollPane messageBoxScrollPane;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JLabel portLabel;
    private javax.swing.JTextField portTextField;
    private javax.swing.JMenuItem purpleWhiteColor;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JMenuItem selectBackgroundColor;
    private javax.swing.JMenuItem selectForegroundColor;
    private javax.swing.JButton sendButton;
    private javax.swing.JLabel serverLabel;
    private javax.swing.JTextField serverTextField;
    private javax.swing.JMenuItem tealWhiteColor;
    // End of variables declaration//GEN-END:variables
}
