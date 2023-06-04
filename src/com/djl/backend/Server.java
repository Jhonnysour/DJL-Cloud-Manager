
package com.djl.backend;


import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import static jdk.nashorn.internal.objects.NativeString.substring;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class Server extends Thread {
    
    // Array list para guardar informacion sobre los archivos que se reciben
    static ArrayList<Archivo> archivo = new ArrayList<>();
    static ArrayList<Archivo> archivos_compartidos = new ArrayList<>();
    static ArrayList<Archivo> archivos_compartidos_amigo = new ArrayList<>();
    static String downloadPath = System.getProperty("user.dir")+"\\Download";
    static File downloadFolder;
    static ArrayList<Usuario> usuarios= new ArrayList<>();
    static Usuario juan;
    static String carpetaUsuario;
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");
    static long [] tiempo=new long[1];
    static String actualUser;
    static int fileId;
    static File registro=new File("./registro.txt");
    public static void main(String[] args) throws IOException {
        juan=new Usuario();
        readUsersTxt();
        tiempo[0]=0;
        fileId=0;
        // Crea un server socket donde el servidor esperara requests.
        ServerSocket serverSocket = new ServerSocket(2069);
        //Obtiene los datos del server para actualizarlos al cliente
        
        // Este while loop correra para siempre en el servidor a menos que la aplicación sea cerrada.
        while (true) {

            try {
                
                // Esperar a que el cliente se conecte y cuando lo haga crear un socket para comunicarse con el.
                Socket socket = serverSocket.accept();

                // Stream para recibir data del cliente a traves del socket.
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                int instruccion=dataInputStream.readInt();
                // Sistema de comando donde cada numero representa una accion 
                // a realizar por el servidor 
                switch(instruccion){
                    case 1: //envia la lista de archivos
                        enviarLista();
                        break;
                    case 2: //elimina un archivo
                        eliminar(dataInputStream.readUTF());
                        break;
                    case 3: //manda el archivo seleccionado
                        compartirArchivo(dataInputStream.readUTF());
                        break;
                    case 4: //iniciar sesion
                        if(verificarUsuario(dataInputStream.readUTF())){ //verifica el inicio de sesion
                            
                            downloadFolder=new File(downloadPath);
                            findUserFolder(downloadFolder);
                            findAllFilesInFolder(downloadFolder);
                            leerCompartidos(new File(downloadFolder+"\\compartido\\compartido.txt"));
                            if(!archivo.isEmpty())
                                fileId = archivo.get(archivo.size()-1).getId();}
                        break;
                    case 5: //registrar usuario
                        if(registrarUsuario(dataInputStream.readUTF())){
                            downloadFolder=new File(downloadPath);
                            findUserFolder(downloadFolder);
                            findAllFilesInFolder(downloadFolder);
                            fileId = 1;
                            
                            //escribirRegistro();
                            writeUsersTXT();
                        };
                        
                        break;
                    case 6: //cerrar sesion
                        dataInputStream.readUTF();
                        archivo.clear();
                        archivos_compartidos.clear();
                        archivos_compartidos_amigo.clear();
                        if(!actualUser.equals("admin")){
                            double tiempo1=(((System.currentTimeMillis()-tiempo[0])/60000)+juan.getTiempo());
                            juan.setTiempo(tiempo1);
                            tiempo[0]=0;
                            writeUsersTXT();}
                        break;
                    case 7: //compartir archivo con un usuario
                        String datos=dataInputStream.readUTF();
                        compartir_A_Usuario(datos);
                        break;
                    case 8: //envia la lista de usuarios disponibles
                        dataInputStream.readUTF();
                        enviarUsuarios();
                    case 9: //cambiar contrasena
                        String pass=dataInputStream.readUTF();
                        for(Usuario x:usuarios){
                            if(x.getUserName().equals(actualUser)){
                                x.setPassword(pass);
                            }
                        }
                        writeUsersTXT();
                        break;
                    case 10:  //eliminar usuario de archivo compartido
                        eliminarUsuarioCompartido(dataInputStream.readUTF());
                        
                        break;
                    case 11:
                        setPrivacidad(dataInputStream.readUTF());
                        break;
                    case 12:
                        modificar(dataInputStream.readUTF());
                        break;
                    case 13:
                        restringir(dataInputStream.readUTF());
                        break;
                    case 14:
                        changeUser(dataInputStream.readUTF());
                        break;
                    case 15:
                        changePass(dataInputStream.readUTF());
                        break;
                    default:
                        // Leer el tamaño del nombre del archivo para saber cuando parar de leer.
                    int fileNameLength = dataInputStream.readInt();
                    // Si el archivo existe.
                    if (fileNameLength > 0) {
                    // Byte array para guardar el nombre del archivo.
                    byte[] fileNameBytes = new byte[fileNameLength];     
                    // Lee desde el input stream hacia el byte array.
                    dataInputStream.readFully(fileNameBytes, 0, fileNameBytes.length);
                    // Crea el nombre del archivo desde el array de bytes.
                    String fileName = new String(fileNameBytes);
                    
                    boolean exist=false; //verifica si ya existe ese archivo
                    for(Archivo x:archivo){
                        if(x.getName().equals(fileName))
                            exist=true;
                    }
                    if(!exist){
                        
                        // Lee cuanda data esperar del contenido en si del archivo.
                        int fileContentLength = dataInputStream.readInt();

                        // Si el archivo existe.
                        if (fileContentLength > 0) {
                            
                            // Array para contener toda la data.
                            byte[] fileContentBytes = new byte[fileContentLength];
                            // Lee del input stream hacia el array de fileContentBytes.
                            dataInputStream.readFully(fileContentBytes, 0, fileContentBytes.length);
                            Date ultima=new Date();
                            
                            // Añade el nuevo file al ArrayList que contiene todo nuestra data.
                            archivo.add(new Archivo(fileId, fileName, fileContentLength, getFileExtension(fileName),simpleDateFormat.format(ultima),carpetaUsuario+"\\"+fileName,true));
                            // Incrementa el fileId para el siguiente Archivo a ser recibido.
                            fileId++;
                            try (FileWriter fw = new FileWriter(new File(carpetaUsuario+"\\seguridad.txt"),true)) {
                                fw.write(fileName+"`p\n");
                            }
                            
                            guardarArchivo(fileName,fileContentBytes);
                            enviarLista();
                            }
                        }
                    }
                } 
            }catch (IOException e) {}
                
        }
    }

    // Retorna el tipo de extencion del archivo
    public static String getFileExtension(String fileName) {
        // Toma el tipo de archivo usando la ultima ocurrencia de . (por ejemplo readme.txt returns txt).
        // Tendrá problemas con los archivos tipo myFile.tar.gaz.
        int i = fileName.lastIndexOf('.');
        // Si hay una extensión.
        if (i > 0) {
            // Set la extensión a la extension del nombre del archivo.
            return fileName.substring(i + 1);
        } else {
            return "Extension no encontrada.";
        }
    }
    
    // encuentra la carpeta del usuario y la asigna a downloadFolder
    public static void findUserFolder(File folder){
        int i=1;
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                if(juan.getUserName().equals(file.getName())){
                    downloadFolder=file;
                    return;
                }
                i++;
            }
	}
    }
    
    // metodo que llena la lista de archivos
    public static long findAllFilesInFolder(File folder) throws IOException {
        int i=1;
        
        long tamano=0;
            for (File file : folder.listFiles()) {
                
                if(!(file.getName().equals("seguridad.txt"))){
                    if (!file.isDirectory()) {
                        Date lastModifiedDate = new Date(file.lastModified());
                        tamano+=file.length();
                        Archivo newArchivo = new Archivo(i, file.getName(), (float) file.length(), getFileExtension(file.getName()),simpleDateFormat.format(lastModifiedDate),file.getAbsolutePath(),esPublico(file.getAbsolutePath()));
                        archivo.add(newArchivo);
                        i++;
                    } else if(!file.getName().equals("compartido")) {
                        findAllFilesInFolder(file);
                    }
            }}
            return tamano;
    }
    public static boolean esPublico(String ruta) throws FileNotFoundException{
        String[] aux;
        File file=new File(ruta);
        if(file.isDirectory())
            return true;
        if(file.exists()){
        String carpeta=ruta.replaceAll(file.getName(), "");
        File txt=new File(carpeta+"\\seguridad.txt");
        try (Scanner c = new Scanner(txt)) {
            while(c.hasNextLine()){
                String linea=c.nextLine();
                aux=linea.split("`");
                File file1=new File(aux[0]);
                if(ruta.equals(file1.getAbsolutePath())){
                    return aux[1].charAt(0) != 's';
                }
            }
        }
        }else
            return false;
        
        return true;
    }
    // Escribe el contenido del archivo enviado en un nuevo archivo del 
    // lado del servidor para ser guardado
    public static void guardarArchivo(String fileName,byte[] data){
        File fileToDownload = new File(carpetaUsuario+"\\"+fileName);
        try {
            // Crea un stream para escribir data al archivo
            FileOutputStream fileOutputStream = new FileOutputStream(fileToDownload);
            // Escribe el contenido al archivo
            fileOutputStream.write(data);
            // Cierra el stream
            fileOutputStream.close();
        } catch (IOException ex) {}
    }
    public static void leerCompartidos(File txt) throws FileNotFoundException, IOException{
        archivos_compartidos.clear();
        try (Scanner c = new Scanner(txt)) {
            while(c.hasNextLine()){
                String linea=c.nextLine();
                File archiv=new File(linea);
                if(esPublico(archiv.getAbsolutePath())){
                    if(archiv.exists()&&!archiv.isDirectory()){
                        Date lastModifiedDate = new Date( archiv.lastModified() );
                        Archivo temp=new Archivo(0, archiv.getName(), (float)archiv.length(), getFileExtension(archiv.getName()), simpleDateFormat.format(lastModifiedDate),archiv.getAbsolutePath(),true);
                        archivos_compartidos.add(temp);
                    }else if(archiv.exists()&&archiv.isDirectory()){
                        Date lastModifiedDate = new Date( archiv.lastModified() );
                        Archivo temp=new Archivo(0, archiv.getName(), folderSize(archiv), "Carpeta compartida", simpleDateFormat.format(lastModifiedDate),archiv.getAbsolutePath(),true);
                        archivos_compartidos.add(temp);
                    }
                }
            }
        }
        escribirCompartidos(txt);
    }
    public static void enviarLista() throws IOException{
        
        Socket socket1 = new Socket("localhost", 2070);
        ObjectOutputStream dataOut=new ObjectOutputStream(socket1.getOutputStream());
        dataOut.writeObject(archivo);
        dataOut.writeObject(archivos_compartidos);
        dataOut.flush();
        dataOut.close();
        socket1.close();
    }
    
    // Verifica que el usuario exista 
    public static boolean verificarUsuario(String nombreYpassword) throws IOException{
        
        String[] user=nombreYpassword.split("`");
        String respuesta="";
        boolean existeUsuario=false;
        boolean contrasenaCorrecta=false;
        boolean bool=false;
        if(user[0].equals("admin")){
            if(user[1].equals("admin")){
                admin();
                Server.actualUser="admin";
                return false;
            }
            
        }
            // Si el nombre y contraseña del usuario existen 
            for(Usuario x:usuarios){
                
                if(x.getUserName().toLowerCase().equals(user[0].toLowerCase())){
                    existeUsuario=true;
                    if(x.getPassword().equals(user[1])){
                        contrasenaCorrecta=true;
                        respuesta="todo fino, adelante";
                        actualUser=user[0];
                        juan=x;
                        //guarda la variable del momento cuando inicio el usuario
                        tiempo[0]=System.currentTimeMillis();
                        // se le asigna a downloadFolder la carpeta del usuario
                        carpetaUsuario=downloadPath+"\\"+juan.getUserName();
                        downloadFolder=new File(carpetaUsuario);
                        bool=true;
                    }
                }
            }

            if(!existeUsuario)
                respuesta="usuario no existe";
            else if(!contrasenaCorrecta)
                respuesta="invalid password";
        
        // Envia la respuesta del metodo al cliente
        Socket socket1 = new Socket("localhost", 2070);
        DataOutputStream dataOut=new DataOutputStream(socket1.getOutputStream());
        dataOut.writeUTF(respuesta);
        dataOut.flush();
        dataOut.close();
        socket1.close();
        return bool;
    }
    
    // Registra el usuario en el sistema
    public static boolean registrarUsuario(String nombreYpassword) throws IOException{
        String[] user=nombreYpassword.split("`");
        String respuesta="";
        user[0]=user[0].replaceAll(" "," ");
        boolean bool=false;
        // Checkea que el usuario exista para el boton de registrar
        if(user[0].equals("admin")){
            respuesta="Usuario ya existe";
        }else
            for(Usuario x:usuarios){
                if(x.getUserName().toLowerCase().equals(user[0].toLowerCase())){
                    respuesta="Usuario ya existe";
                }
            }
        // Si el usuario no existe, se crea una carpeta nueva con su nombre en 
        // el directorio de "downloadPath"
        if(respuesta.equals("")){
            carpetaUsuario=downloadPath+"\\"+user[0];
            File carpeta =new File(carpetaUsuario);
            carpeta.mkdir();
            
            tiempo[0]=System.currentTimeMillis();
            juan=new Usuario(user[0],user[1]);
            usuarios.add(juan);
            downloadFolder=carpeta;
            
            
            File compartido=new File(carpetaUsuario+"\\compartido");
            compartido.mkdir();
            File txt=new File(compartido.getAbsolutePath()+"\\compartido.txt");
            txt.createNewFile();
            txt=new File(carpetaUsuario+"\\seguridad.txt");
            txt.createNewFile();
            
            actualUser=user[0];
            bool=true;
        }
        // Manda la informacion al servidor de los datos del cliente. Si existe
        // se manda el mensaje "Usuario ya existe", de lo contrario se envia
        // se envia el respuesta vacio y se da permiso de acceder al cliente
        Socket socket1 = new Socket("localhost", 2070);
        DataOutputStream dataOut=new DataOutputStream(socket1.getOutputStream());
        dataOut.writeUTF(respuesta);
        dataOut.flush();
        dataOut.close();
        socket1.close();
        return bool;
    }
    public static void admin() throws IOException{
        Socket socket1 = new Socket("localhost", 2070);
        DataOutputStream dataOut=new DataOutputStream(socket1.getOutputStream());
        dataOut.writeUTF("administrador");
        dataOut.flush();
        dataOut.close();
        socket1.close();
        for(Usuario x:usuarios){
            x.setEspacioUtilizado(findAllFilesInFolder(new File(downloadPath+"\\"+x.getUserName())));
        }
        enviarUsuarios();
    }
    public static void enviarUsuarios() throws IOException{
        try (Socket socket1 = new Socket("localhost", 2065)) {
            ObjectOutputStream out=new ObjectOutputStream(socket1.getOutputStream());
            out.writeObject(usuarios);
            out.close();
        }
    }
    // Elimina un archivo especificado de la carpeta del usuario
    public static void eliminar(String nombre){
        for(Archivo x:archivo){
            if(x.getName().equals(nombre)){
                archivo.remove(x);
                break;
            }
        }
        File del=new File(carpetaUsuario+"\\"+nombre);
        del.delete();
    }
    
    // Envia el archivo al cliente para que este pueda ser descargado por el usuario
    public static void compartirArchivo(String nombre) throws FileNotFoundException, IOException{
        File file=new File(carpetaUsuario+"\\"+nombre);
        boolean zip=false;
        if(!file.exists()){
            for (Archivo x:archivos_compartidos){
                if (x.getName().equals(nombre)){
                    file=new File(x.getPath());
                    break;
                }
            }
        }
        if(file.isDirectory()){
            comprimir(Paths.get(file.getAbsolutePath()),Paths.get(downloadPath+"\\"+file.getName()+".zip"));
            file=new File(downloadPath+"\\"+file.getName()+".zip");
            zip=true;
        }
        DataOutputStream output;
        try (FileInputStream input = new FileInputStream(file)) {
            // Establece conexion con el socket para enviar el archivo
            Socket socket1 = new Socket("localhost", 2070);
            output = new DataOutputStream(socket1.getOutputStream());
            byte[] fileBytes = new byte[(int)file.length()];
            input.read(fileBytes);
            // Envia el archivo al cliente
            output.writeInt(fileBytes.length);
            output.write(fileBytes);
        }
        output.flush();
        output.close();
        if(zip)
            file.delete();
    }
    
    public static void compartir_A_Usuario(String data) throws IOException{
        String[] datos=data.split("`"); //Formato: usuario dueño del archivo, archivo, usuario a compartir
        File archivoACompartir;
        String respuesta="error";
        boolean carpeta=false;
        if(datos[1].equals("carpeta compartida")){
            archivoACompartir=new File(carpetaUsuario);
            carpeta=true;
        }
        else
            archivoACompartir=new File(downloadPath+"\\"+datos[0]+"\\"+datos[1]);
        File txt=new File(downloadPath+"\\"+datos[2]+"\\compartido\\compartido.txt");
        
        leerCompartidos(txt);
        if(esPublico(archivoACompartir.getAbsolutePath())||carpeta){
            
            for(Archivo x:archivos_compartidos){
                if(x.getName().equals(archivoACompartir.getName())){
                    return;
                }
            }
            Date lastModifiedDate = new Date( archivoACompartir.lastModified() );
            Archivo temp;
            if(!archivoACompartir.isDirectory())
                temp=new Archivo(0, archivoACompartir.getName(), (float)archivoACompartir.length(), getFileExtension(archivoACompartir.getName()), simpleDateFormat.format(lastModifiedDate),archivoACompartir.getAbsolutePath(),true);
            else
                temp=new Archivo(0, archivoACompartir.getName(), folderSize(archivoACompartir), "carpeta compartida", simpleDateFormat.format(lastModifiedDate),archivoACompartir.getAbsolutePath(),true);
            archivos_compartidos.add(temp);
            try (FileWriter fw = new FileWriter(txt,true)) {
                fw.write(temp.getPath()+"\n");
            }
            respuesta="Compartido con exito";
            
        }else{
            System.out.println("nel mano, este archivo es privado");
            respuesta="este archivo es privado, no se puede compartir";
        }
        /*
        DataOutputStream dataOut1;
        try (Socket socket2 = new Socket("localhost", 2072)) {
            dataOut1 = new DataOutputStream(socket2.getOutputStream());
            System.out.println("Try");
            dataOut1.writeInt(3);
            //dataOut1.writeUTF(respuesta);
            dataOut1.flush();
            System.out.println("Enviado "+respuesta);
        }
        dataOut1.close();*/
    }
    public static float folderSize(File directory) {
    float length = 0;
    for (File file : directory.listFiles()) {
        if (file.isFile())
            length += file.length();
        else
            length += folderSize(file);
    }
    return length;
}
    public static void escribirCompartidos(File txt) throws IOException{
        
        FileWriter fw = new FileWriter(txt);
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            for(Archivo x:archivos_compartidos){
                
                if(x.isPublico() && esPublico(x.getPath()))
                    bw.write(x.getPath()+"\n");
            }
        }
    }
    public static void writeXmlFile() {

    try {

        DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
        DocumentBuilder build = dFact.newDocumentBuilder();
        Document doc = build.newDocument();

        Element root = doc.createElement("UsersInfo");
        doc.appendChild(root);
        for (Usuario dtl : usuarios) {
            Element Details = doc.createElement("User");
            root.appendChild(Details);

            Element name = doc.createElement("UserName");
            name.appendChild(doc.createTextNode(String.valueOf(dtl.getUserName())));
            Details.appendChild(name);
            
            Element pass = doc.createElement("pass");
            pass.appendChild(doc.createTextNode(String.valueOf(dtl.getPassword())));
            Details.appendChild(pass);
            
            Element time = doc.createElement("time");
            time.appendChild(doc.createTextNode(String.valueOf(dtl.getTiempo())));
            Details.appendChild(time);
            
        }

        TransformerFactory tranFactory = TransformerFactory.newInstance();
        Transformer aTransformer = tranFactory.newTransformer();

        aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

        aTransformer.setOutputProperty(
                "{http://xml.apache.org/xslt}indent-amount", "4");
        aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        try {
            // location and name of XML file you can change as per need
            FileWriter fos = new FileWriter("./registro.xml");
            StreamResult result = new StreamResult(fos);
            aTransformer.transform(source, result);

        } catch (IOException e) {
        }

    } catch (TransformerException ex) {
        System.out.println("Error outputting document");

    } catch (ParserConfigurationException ex) {
        System.out.println("Error building document");
    }
}
    
    public static void readUsersTxt() throws FileNotFoundException, IOException{
        if(!registro.exists()){
            registro.createNewFile();
            return;
        }
        try (Scanner c = new Scanner(registro)) {
            while(c.hasNextLine()){
                String[] linea=c.nextLine().replace("\n", "").split("`");
                Usuario temp=new Usuario(linea[0], linea[1]);
                usuarios.add(temp);
            }
        }
    }
    public static void writeUsersTXT() throws IOException{
        PrintWriter pw = new PrintWriter(registro);
        
            for(Usuario x:usuarios){
                pw.println(x.getUserName()+"`"+x.getPassword());
            }
            pw.close();
        }
    
    
    public static void readXmlFile(){
        try   
            {  
            //creating a constructor of file class and parsing an XML file  
            File file = new File("./registro.xml");
            if(!file.exists()){
                writeUsersTXT();
                System.out.println("No existia XML");
                return;
            }
            //an instance of factory that gives a document builder
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //an instance of builder to parse the specified xml file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("User");
            // nodeList is not iterable, so we are using for looparchivo.clear();
            for (int itr = 0; itr < nodeList.getLength(); itr++)
            {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    System.out.println("Leyyendo nodo");
                    Element eElement = (Element) node;  
                    String username=eElement.getElementsByTagName("UserName").item(0).getTextContent();
                    File carpeta = new File(downloadPath+"\\"+username);
                    Usuario x=new Usuario(username,eElement.getElementsByTagName("pass").item(0).getTextContent(),Double.parseDouble(eElement.getElementsByTagName("time").item(0).getTextContent()),findAllFilesInFolder(carpeta));
                    usuarios.add(x);
                    
                }
            }
            archivo.clear();
            }
                catch (IOException | NumberFormatException | ParserConfigurationException | DOMException | SAXException e)
            {
            }
        }
    
    public static void comprimir(final Path folder, final Path zipFilePath) throws IOException {
    try (
            FileOutputStream fos = new FileOutputStream(zipFilePath.toFile());
            ZipOutputStream zos = new ZipOutputStream(fos);
            
    ) {
        Files.walkFileTree(folder, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                zos.putNextEntry(new ZipEntry(folder.relativize(file).toString()));
                Files.copy(file, zos);
                zos.closeEntry();
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                zos.putNextEntry(new ZipEntry(folder.relativize(dir).toString() + "/"));
                zos.closeEntry();
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
    private static void eliminarUsuarioCompartido(String usuario) throws IOException {
        File txt=new File(downloadPath+"\\"+usuario+"\\compartido\\compartido.txt");
        archivos_compartidos_amigo.clear();
        try (Scanner c = new Scanner(txt)) {
            while(c.hasNextLine()){
                String linea=c.nextLine();
                File o1=new File(linea);
                if(!o1.getName().equals(actualUser)){
                    if(o1.exists() && !o1.isDirectory()){
                        Date lastModifiedDate = new Date( o1.lastModified() );
                        Archivo temp=new Archivo(0, o1.getName(), (float)o1.length(), getFileExtension(o1.getName()), simpleDateFormat.format(lastModifiedDate),o1.getAbsolutePath(),true);
                        archivos_compartidos_amigo.add(temp);
                    }else if(o1.isDirectory()){
                        Date lastModifiedDate = new Date( o1.lastModified() );
                        Archivo temp=new Archivo(0, o1.getName(), folderSize(o1), "carpeta", simpleDateFormat.format(lastModifiedDate),o1.getAbsolutePath(),true);
                        archivos_compartidos_amigo.add(temp);
                    }
                }
            }
        }
        
        FileWriter fw = new FileWriter(txt);
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            for(Archivo x:archivos_compartidos_amigo){
                bw.write(x.getPath()+"\n");
            }
        }
    }
    private static void escribirSeguridad() throws IOException{
        try (FileWriter fw = new FileWriter(new File(carpetaUsuario+"\\seguridad.txt"))) {
            char a='s';
            for(Archivo x:archivo){
                
                if(x.isPublico())
                    a='p';
                fw.write(x.getPath()+"`"+a+"\n");
            }
            
            }
    }
    private static void setPrivacidad(String data) throws IOException {
        String[] datos=data.split("`"); //0->nombre del archivo... 1->privacidad (s o p)
        for(Archivo x:archivo){
            if(x.getName().equals(datos[0])){
                char a=datos[1].charAt(0);
                if(a=='p')
                    x.setPublico(true);
                else
                    x.setPublico(false);
                break;
            }
        }
        escribirSeguridad();
    }
    public static void modificar(String data) throws IOException{
        String[] datos=data.split("`"); //0->nombre original, 1->nombre 2
        File a = new File(carpetaUsuario+"\\"+datos[0]);
        File b=new File(carpetaUsuario+"\\"+datos[1]);
        boolean x=a.renameTo(b);
    }
    
    public static void restringir(String data) throws FileNotFoundException, IOException{
        String[] datos=data.split("`"); //0->usuario a restringir, 1->archivo
        File txt=new File(downloadPath+"\\"+datos[0]+"\\compartido\\compartido.txt");
        String filePath=carpetaUsuario+"\\"+datos[1];
        String linea="";
        try (Scanner c = new Scanner(txt)) {
            while(c.hasNextLine()){
                String aux=c.nextLine();
                if(aux.equals(filePath)){
                }else
                    linea+=aux;
            }
        }
        FileWriter fw = new FileWriter(txt);
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(linea);
        }
    }
    
    public static void changeUser(String name) throws FileNotFoundException, IOException{
        File carpeta=new File(carpetaUsuario);
        
        carpeta.renameTo(new File(downloadPath+"\\"+name));
        carpetaUsuario=carpeta.getAbsolutePath();
        
        for(Usuario x:usuarios){
            if(x.getUserName().equals(actualUser)){
                x.setUserName(name);
                break;
            }
        }
        
        writeUsersTXT();
        
        actualUser=name;
    }
    
    public static void changePass(String pass) throws IOException{
        
        for(Usuario x:usuarios){
            if(x.getUserName().equals(actualUser)){
                x.setPassword(pass);
                break;
            }
        }
        writeUsersTXT();
    }
    }

