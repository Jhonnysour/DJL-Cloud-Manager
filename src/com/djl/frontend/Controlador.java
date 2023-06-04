
package com.djl.frontend;

import com.djl.backend.Archivo;
import com.djl.backend.Usuario;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Controlador extends Thread
{    
    private final File[] fileToSend = new File[1];
    private final String host="localhost";
    private final int port=2069;
    private File ultimaCarpeta;
    private ArrayList<Archivo> lista=new ArrayList();
    private ArrayList<Archivo> lista_compartidos=new ArrayList();
    private ArrayList<Usuario> usuarios=new ArrayList();
    private JTable defaultTable;
    private String ActualUser;
    public Controlador(JTable tabla) {
        this.ultimaCarpeta = new File("");
        this.defaultTable=tabla;
    }
    
    // Abre un JFileChooser para seleccionar el archivo a subir.
    public void escogerArchivo(JLabel jlFileName) 
    {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Abrir");
        jFileChooser.setCurrentDirectory(ultimaCarpeta);
        if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            // Obtener el archivo seleccionado.
            
            this.fileToSend[0] = jFileChooser.getSelectedFile();
            ultimaCarpeta=jFileChooser.getCurrentDirectory();
            
            // Cambiar el nombre del JLabel para confirmar
            jlFileName.setText(fileToSend[0].getName());
        }
    }

    // Accion del boton SUBIR
    public Thread subirArchivo(JLabel jlFileName) throws ClassNotFoundException, IOException, InterruptedException 
    {
        
        final PipedOutputStream output = new PipedOutputStream();
        final PipedInputStream  input  = new PipedInputStream(output);
        
        
        Thread frame=new Thread(new Runnable() {
            @Override
            public void run() {
                Cargando carga=new Cargando();
                carga.setAlwaysOnTop(true);
                carga.setVisible(false);
                carga.setLocationRelativeTo(null);
                carga.setResizable(false);
                try {
                    int enviando=input.read();
                    if(enviando==1){
                        carga.setVisible(true);
                    }
                    
                    enviando=input.read();
                    carga.dispose();
                } catch (IOException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }});
        Thread load=new Thread(new Runnable() {
            @Override
            public void run() {
        if (fileToSend[0] == null) 
        {
            jlFileName.setText("Seleccione un archivo a subir primero");
        }
        else 
        {
            int i=-1;
            Iterator<Archivo> it=lista.iterator();
            while(it.hasNext()){
                Archivo x=it.next();
                if(x.getName().equals(fileToSend[0].getName())){
                    i=JOptionPane.showConfirmDialog(null, "Ese archivo ya existe, Desea reemplazarlo?");
                    jlFileName.setText("Escoja un archivo");
                }
            }
            if(i==JOptionPane.YES_OPTION)
                eliminar(fileToSend[0].getName());
            
            else if(i==JOptionPane.NO_OPTION)
                return;
            try {
                File aux=fileToSend[0];
                fileToSend[0]=null;
                jlFileName.setText("Escoja un archivo");
                output.write(1);
                    // Crea un input stream en el archivo que se mandará.
                    FileInputStream fileInputStream = new FileInputStream(aux.getAbsolutePath());
                    // Crea la conexión socket con el servidor.
                    Socket socket = new Socket(host, port);
                    // Crea un output stream para escribir al servidor a traves del socket 
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    dataOutputStream.writeInt(0); //Envia la instruccion de recibir el archivo
                    // Toma el nombre del archivo a mandar y lo guardalo en fileName
                    String fileName = aux.getName();
                    // Convierte el nombre del archivo en un array de bytes para mandarlo al servidor.
                    byte[] fileNameBytes = fileName.getBytes();
                    // Crea un array de bytes del tamaño del archivo para no mandar ni mucha ni poca data al servidor.
                    byte[] fileBytes = new byte[(int) aux.length()];
                    // Coloca el contenido del archivo en un array de bytes para mandado al servidor
                    fileInputStream.read(fileBytes);
                    // Manda la longitud del nombre del archivo para que el servidor sepa cuando parar de leer.
                    dataOutputStream.writeInt(fileNameBytes.length);
                    // Manda el nombre del archivo.
                    dataOutputStream.write(fileNameBytes);
                    // Manda la longitud del array de bytes para que el servidor sepa cuando para de leer.
                    dataOutputStream.writeInt(fileBytes.length);
                    // Manda el archivo como tal.
                    dataOutputStream.write(fileBytes);
                    
                    fileInputStream.close();
                    dataOutputStream.close();
                    socket.close();
                    output.write(0);
            } catch (IOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            }});
        
        frame.start();
        load.start();
        return load;
    }
    
    
    // Metodo para el manejo de las tablas presentes en el cliente
    public void update(JTable tabla1,JTable tablac) throws IOException, ClassNotFoundException{
        tabla1=defaultTable;
        DefaultTableModel tabla2=new DefaultTableModel();
        DecimalFormat formato1= new DecimalFormat("#.00");
        tabla2=(DefaultTableModel) tabla1.getModel();
        
        try{
            Socket socket = new Socket(host, port);
            
            // Se manda el comando 1 al servidor para que ejecute la accion especifica
            try (DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {
                dataOutputStream.writeInt(1);
                dataOutputStream.flush();
                dataOutputStream.close();
            }
            
            // Crea un socket para recibir informacion del servidor
            ServerSocket serverSocket1 = new ServerSocket(2070);
            
            try{
                // Obtiene el arraylist de archivos registrados en el servidor
                Socket socket1 = serverSocket1.accept();
                InputStream in = socket1.getInputStream();
                ObjectInputStream input = new ObjectInputStream(in);
                // Castea el input stream a un ArrayList de archivos
                this.lista=(ArrayList<Archivo>)input.readObject();
                this.lista_compartidos=(ArrayList<Archivo>)input.readObject();
                in.close();
                serverSocket1.close();   
            }catch(IOException e) {}
            
            // Añade las filas correspondientes a cada uno de los archivos en la lista
            tabla2.setRowCount(0);
            for(Archivo x:lista){
                tabla2.addRow(new Object[]{
                    x.getName(),
                    formato1.format((float)x.getSize()/1048576)+"MB",
                    x.getType(),
                    x.getFecha()
                });
            }
        
        DefaultTableModel tabla3=new DefaultTableModel();
        tabla3=(DefaultTableModel) tablac.getModel();
        tabla3.setRowCount(0);
            for(Archivo x:lista_compartidos){
                tabla3.addRow(new Object[]{
                    x.getName(),
                    formato1.format((float)x.getSize()/1048576)+"MB",
                    x.getType(),
                    x.getFecha()
                });
            }
        
        socket.close();
            
        }catch(IOException e) {}

    }
    
    // Accion del boton ELIMINAR
    public void eliminar(String fileName){
        comando(2,fileName);
    }
    
    // Accion del boton registar/iniciar sesion
    public int enviarUsuario(String userName,String password,int instruccion) throws IOException, ClassNotFoundException{
        String unido=userName+"`"+password;
        if(userName.equals("")||password.equals("")){
            JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios");
            return 0;
        }
        if(userName.contains(" ")){
            JOptionPane.showMessageDialog(null, "No puede haber espacios en el nombre de usuario");
            return 0;
        }
        comando(instruccion,unido); //instruccion manda lo que deseemos realizar(registrar usuario o iniciar sesion),
        // como vamos a enviar los mismos datos, solo cambiaremos lo que el server haga con ellos
        // si instruccion==4 inicia sesion, si instruccion ==5 el servidor registra al usuario
        
        String respuesta;
        ServerSocket serverSocket = new ServerSocket(2070);
        Socket socket = serverSocket.accept();
        DataInputStream input = new DataInputStream(socket.getInputStream());
        respuesta=input.readUTF();
        input.close();
        serverSocket.close();
        socket.close();
        switch(respuesta){
            case "usuario no existe":
                JOptionPane.showMessageDialog(null, "Usuario no existe");
                break;
                
            case "invalid password":
                JOptionPane.showMessageDialog(null, "La contraseña es incorrecta");
                break;
                
            case "Usuario ya existe":
                JOptionPane.showMessageDialog(null, "Ese usuario ya está registrado");
                break;
            case "administrador":
                recibir_usuarios();
                
                return 2;
                
            default:
                this.ActualUser=userName;
                return 1;
        }
        
        return 0;
    }
    public void tablaAdmin(JTable table){
        
        JTable tabla = table;
        tabla.getColumnModel().getColumn(0).setHeaderValue("Usuario");
        tabla.getColumnModel().getColumn(1).setHeaderValue("tiempo en la app");
        tabla.getColumnModel().getColumn(2).setHeaderValue("Espacio utilizado");
        
        DefaultTableModel tabla2=new DefaultTableModel();
        DecimalFormat formato1= new DecimalFormat("#.00");
        tabla2=(DefaultTableModel) tabla.getModel();
        tabla2.setRowCount(0);
            for(Usuario x:usuarios){
                
                tabla2.addRow(new Object[]{
                    x.getUserName(),
                    x.getTiempo()+"Minutos",
                    x.getEspacioUtilizado()/1024+"KiloBytes"
                });
            }
    }
    // Metodo que envia el comando de la accion a realizar al servidor
    public void comando(int instruccion,String fileName){
        
        try{
            Socket socket = new Socket(host, port);
            
            try (DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {
                dataOutputStream.writeInt(instruccion);
                dataOutputStream.writeUTF(fileName);
                dataOutputStream.flush();
                dataOutputStream.close();
            }
        }catch (IOException ex) {}
    }
    
    // Accion del item del popup menu DESCARGAR
    public void descargarArchivo(String nombre) throws IOException, InterruptedException{
        
        comando(3,nombre);
        final PipedOutputStream output = new PipedOutputStream();
        final PipedInputStream  input  = new PipedInputStream(output);
        
        Thread frame=new Thread(new Runnable() {
            @Override
            public void run() {
                Cargando carga=new Cargando();
                carga.setAlwaysOnTop(true);
                carga.setVisible(false);
                carga.setLocationRelativeTo(null);
                carga.setResizable(false);
                try {
                    int enviando=input.read();
                    if(enviando==1){
                        carga.setVisible(true);
                    }
                    
                    enviando=input.read();
                    carga.dispose();
                } catch (IOException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }});
                
        Thread load=new Thread(new Runnable() {
            @Override
            public void run() {
                try (ServerSocket serverSocket = new ServerSocket(2070)) {
            
                // Abre un JFileChooser para seleccionar el path de la descarga
                JFileChooser carpeta = new JFileChooser();
                carpeta.setDialogTitle("Seleccione la ubicación de descarga");
                carpeta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                carpeta.setCurrentDirectory(ultimaCarpeta);
                carpeta.setAcceptAllFileFilterUsed(false);

                if(carpeta.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                    output.write(1);
                    File selectedPath=carpeta.getSelectedFile();
                    // acepta la conexion con el socket del servidor
                    Socket socket = serverSocket.accept();
                    DataInputStream input = new DataInputStream(socket.getInputStream());
                    // obitiene la informacion del archivo
                    byte[] fileBytes=new byte[input.readInt()];
                    input.readFully(fileBytes);
                    ultimaCarpeta=selectedPath;
                    int i = nombre.lastIndexOf('.');
                    // Si hay una extensión.
                    File file;
                    if (i <= 0) 
                        file = new File(ultimaCarpeta+"\\"+nombre+".zip");
                    else 
                        file = new File(ultimaCarpeta+"\\"+nombre);
                    FileOutputStream file1 = new FileOutputStream(file);
                    file1.write(fileBytes);
                    file1.close();
                    input.close();
                    socket.close();

                    output.write(0);
                }

            }       catch (IOException ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }});
        
        load.start();
        frame.start();
    }
    
    public void mostrar_usuarios(JTable users) throws IOException, ClassNotFoundException{
        comando(8,"");
        DefaultTableModel tabla2=new DefaultTableModel();
        tabla2=(DefaultTableModel) users.getModel();
        tabla2.setRowCount(0);
        recibir_usuarios();
        
            for(Usuario x:usuarios){
                if(!x.getUserName().equals(ActualUser))
                    tabla2.addRow(new Object[]{
                        x.getUserName()
                    });
            }
        
    }
    
    public void compartir(String archivo,String amigo) throws IOException{
        String envio=ActualUser+"`"+archivo+"`"+amigo;
        comando(7,envio);
        /*
        ServerSocket serverSocket = new ServerSocket(2072);
        Socket socket = serverSocket.accept();
        DataInputStream input = new DataInputStream(socket.getInputStream());
        int i=input.readInt();
        //String respuesta=input.readUTF();
        System.out.println(i);
        
        */
    }
    
    public void cerrarSesion(){
        comando(6," ");
    }

    private void recibir_usuarios() throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(2065);
               
        Socket socket = serverSocket.accept(); 
            ObjectInputStream in =new ObjectInputStream(socket.getInputStream());
            this.usuarios=(ArrayList<Usuario>)in.readObject();
            serverSocket.close();
            socket.close();
    }
    
    public void cambiar_password(String pass){
        comando(9,pass);
        
    }
    public void eliminarUsuarioCompartido(String usuario){
        comando(10,usuario);
    }

    public void setPrivacidad(String archivo, char c) {
        comando(11,archivo+"`"+c);
    }
    
        public int toDec(String s){
        String digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXY";  
        int value=0;
        for (char c:s.toCharArray()){
            int d=digits.indexOf(c);
            value=(34*value)+d;
        }
        return value+32;
    }
    
    public String to34(int decimal){
        int rem=0;
        decimal-=32;
        String hex="";
        String digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXY";
        char[] hexchars=digits.toCharArray();
         while(decimal>0)  
        {  
          rem=decimal%34;   
          hex=hexchars[rem]+hex;   
          decimal=decimal/34;  
        }  
        return hex;
    }
    
    public void crear_enlace(String archivo){
        String datos=ActualUser+"`"+archivo;
        byte[] vector=datos.getBytes();
        String enlace="";
        for(byte x:vector){
            enlace+=to34(x)+"Z";
        }
        String[] opciones={"Copiar","Aceptar"};
        int x=JOptionPane.showOptionDialog(null,"Enlace:\n"+enlace,"Enlace",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,opciones,opciones[0]);
        switch (x){
            case 0:
                StringSelection stringSelection = new StringSelection(enlace);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
                break;
        }
        

        System.out.println(enlace);
    }
    
    public void leer_enlace(String enlace){
        String letra0="";
        String traducido="";
        for (char c: enlace.toCharArray ()){
            if(c!='Z'){
                letra0+= Character.toString(c);
            }else{
                traducido+= (char)toDec(letra0);  
                letra0="";
            }
        }
        
        comando(7,traducido+"`"+ActualUser);
        
    }
    
    public void modificar(String archivo){
        String nombre=JOptionPane.showInputDialog("Introduzca el nuevo nombre del archivo");
        comando(12,archivo+"`"+nombre);
        for(Archivo x:lista){
            if(x.getName().equals(archivo))
                x.setName(nombre);
        }
    }
    
    public void restringir(String user,String archivo){
        comando(13,user+"`"+archivo);
        
    }
    
    public void ChangeUserName(JLabel label){
        String nombre=JOptionPane.showInputDialog("Introduzca el nuevo nombre de usuario");
        comando(14,nombre);
        for(Usuario x:usuarios){
            if(x.getUserName().equals(ActualUser)){
                x.setUserName(nombre);
                break;
            }
        }
        label.setText("Bienvenido "+nombre);
        ActualUser=nombre;
    }
    
    public void changePass(){
        String pass=JOptionPane.showInputDialog("Introduzca la nueva contraseña");
        comando(15,pass);
        for(Usuario x:usuarios){
            if(x.getUserName().equals(ActualUser)){
                x.setPassword(pass);
                break;
            }
        }
    }
}