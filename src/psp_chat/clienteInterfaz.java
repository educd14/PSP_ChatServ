/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psp_chat;

import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static psp_chat.serverInterfaz.selecPuerto;

/**
 *
 * @author dam2
 */
public class clienteInterfaz extends javax.swing.JFrame {

    private static DataInputStream entradaServ;
    private static DataOutputStream salidaServ;
    private static Socket clienteSocket;
    private static String usuario = "usuario";
    private static String conecta;
    static boolean conectado = true;
    private static String mensaje = "/bye";
    private static String mensaje2 = "";

    public clienteInterfaz(Socket cliente) {
        initComponents();
        this.clienteSocket = cliente;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cuerpoChat = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        cuerpoMensaje = new javax.swing.JTextArea();
        btnEnviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        cuerpoChat.setEditable(false);
        cuerpoChat.setBackground(new java.awt.Color(241, 241, 241));
        cuerpoChat.setColumns(20);
        cuerpoChat.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        cuerpoChat.setRows(5);
        jScrollPane1.setViewportView(cuerpoChat);

        cuerpoMensaje.setBackground(new java.awt.Color(241, 241, 241));
        cuerpoMensaje.setColumns(20);
        cuerpoMensaje.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        cuerpoMensaje.setRows(5);
        cuerpoMensaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cuerpoMensajeKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(cuerpoMensaje);

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnEnviar)
                        .addGap(0, 46, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        mensaje = cuerpoMensaje.getText();
        if (!mensaje.equals("/bye")) {

            try {
                salidaServ.writeUTF(mensaje);
                salidaServ.flush();
            } catch (IOException ex) {
                Logger.getLogger(clienteInterfaz.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                salidaServ.writeUTF(mensaje);
                salidaServ.flush();
                conectado = false;
                close();
            } catch (IOException ex) {
                Logger.getLogger(clienteInterfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cuerpoMensaje.setText("");
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void cuerpoMensajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cuerpoMensajeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            mensaje = cuerpoMensaje.getText();

            if (!mensaje.equals("/bye")) {

                try {
                    salidaServ.writeUTF(mensaje);
                    salidaServ.flush();
                } catch (IOException ex) {
                    Logger.getLogger(clienteInterfaz.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                try {
                    salidaServ.writeUTF(mensaje);
                    salidaServ.flush();
                    conectado = false;
            conecta = usuario + " abandonó este chat.";
            salidaServ.writeUTF(conecta);
            salidaServ.flush();
                    close();
                } catch (IOException ex) {
                    Logger.getLogger(clienteInterfaz.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            cuerpoMensaje.setText("");
        }
    }//GEN-LAST:event_cuerpoMensajeKeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            salidaServ.writeUTF("/bye");
            close();
            conecta = usuario + " abandonó este chat.";
            salidaServ.writeUTF(conecta);
            salidaServ.flush();
        } catch (IOException ex) {
            Logger.getLogger(clienteInterfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            
            System.out.println("Creando socket cliente");
            Socket clienteSocket = new Socket();
            System.out.println("Estableciendo la conexion");

            String ip = JOptionPane.showInputDialog("Indique IP donde alojar su servidor");
            if (ip == null){
                System.exit(0);
            }
            int port = selecPuerto();

            InetSocketAddress addr = new InetSocketAddress(ip, port);
            clienteSocket.connect(addr);

            usuario = JOptionPane.showInputDialog("Indique nombre de usuario");
            if (usuario == null){
                System.exit(0);
            }
            
            //inicializamos interfaz
            clienteInterfaz chat = new clienteInterfaz(clienteSocket);
            chat.setVisible(true);
            chat.setDefaultCloseOperation(3);
            entradaServ = new DataInputStream(clienteSocket.getInputStream());
            salidaServ = new DataOutputStream(clienteSocket.getOutputStream());

            salidaServ.writeUTF(usuario);
            salidaServ.flush();
            salidaServ.writeUTF(ip);
            salidaServ.flush();
            salidaServ.writeInt(port);
            salidaServ.flush();

            cuerpoChat.append("Conectado a la sala de chat" + "\n");
            String conecta = usuario + " acaba de conectarse a este chat";
            salidaServ.writeUTF(conecta);
            salidaServ.flush();

            while (conectado) {
                try {
                    mensaje2 = entradaServ.readUTF();
                } catch (Exception ex) {               
                    System.out.println("Conexión con el servidor cerrada");
                    conectado = false;
                }
                cuerpoChat.append(mensaje2 + "\n");
            }
            if (!conectado) {
                System.out.println("Te has desconectado");
                if(!clienteSocket.isClosed()){
                close();
                }
                System.exit(0);

            }
        } catch (IOException ex) {
            Logger.getLogger(clienteInterfaz.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void close() {
        try {
            System.out.println("Desconectando...");
            //Mensaje de desconexión
            System.out.println(clienteSocket.toString());
            entradaServ.close();
            salidaServ.close();
            clienteSocket.close();
            System.exit(0);
        } catch (IOException ex) {
            Logger.getLogger(clienteInterfaz.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al cerrar");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private static javax.swing.JTextArea cuerpoChat;
    private static javax.swing.JTextArea cuerpoMensaje;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
