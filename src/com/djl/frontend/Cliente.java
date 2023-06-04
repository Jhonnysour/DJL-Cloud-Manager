
package com.djl.frontend;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class Cliente extends javax.swing.JFrame {
    
    private final Controlador control;
    private final int[] index=new int[1];
    public int compartirCarpeta=0;
    public Cliente() throws IOException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        
        //Transparencia del grid
            tblDoc.setBackground(new Color(0,0,0,0));
            ((DefaultTableCellRenderer)tblDoc.getDefaultRenderer(Object.class)).setBackground(new Color(0,0,0,0));
            tblDoc.setGridColor(Color.black);
            tblDoc.setForeground(Color.black);
            jScrollPane1.setBackground(new Color(0,0,0,0));
            tblDoc.setOpaque(false);
            ((DefaultTableCellRenderer)tblDoc.getDefaultRenderer(Object.class)).setOpaque(false);
            jScrollPane1.getViewport().setOpaque(false);
            tblDoc.setShowGrid(true);
            
            tabla_compartidos.setBackground(new Color(0,0,0,0));
            ((DefaultTableCellRenderer)tabla_compartidos.getDefaultRenderer(Object.class)).setBackground(new Color(0,0,0,0));
            tabla_compartidos.setGridColor(Color.black);
            tabla_compartidos.setForeground(Color.black);
            jScrollPane_compartidos.setBackground(new Color(0,0,0,0));
            tabla_compartidos.setOpaque(false);
            ((DefaultTableCellRenderer)tabla_compartidos.getDefaultRenderer(Object.class)).setOpaque(false);
            jScrollPane_compartidos.getViewport().setOpaque(false);
            tabla_compartidos.setShowGrid(true);
        //
        Inicio.setIconImage(new ImageIcon(getClass().getResource("/pngapp/Icono.png")).getImage());
        setIconImage(new ImageIcon(getClass().getResource("/pngapp/Icono.png")).getImage());
        cerrar();
        inicioPress.setVisible(false);
        registrarPress.setVisible(false);
        
        Inicio.setResizable(true);
        labelUser.setText("Waiting...");
        control = new Controlador(tblDoc);
        Inicio.setSize(1450, 940);
        Inicio.setVisible(true);
        Inicio.setAlwaysOnTop(true);
        Inicio.setLocationRelativeTo(null);
        
        Registrar.setFocusPainted(false);
        Registrar.setBorderPainted(false);
        Registrar.setContentAreaFilled(false);
        
        InicioSesion.setFocusPainted(false);
        InicioSesion.setBorderPainted(false);
        InicioSesion.setContentAreaFilled(false);
        
        seleccionarbtn.setFocusPainted(false);
        seleccionarbtn.setBorderPainted(false);
        seleccionarbtn.setContentAreaFilled(false);
        
        btnSubir.setFocusPainted(false);
        btnSubir.setBorderPainted(false);
        btnSubir.setContentAreaFilled(false);
        
        cerrarSesion.setFocusPainted(false);
        cerrarSesion.setBorderPainted(false);
        cerrarSesion.setContentAreaFilled(false);
        
        Usuarios.setTitle("Selecciona el usuario");
        Usuarios.setSize(400, 300);
        Usuarios.setLocationRelativeTo(null);
        
        frame_contrasena.setSize(400, 200);
        frame_contrasena.setLocationRelativeTo(null);
        
        frame_enlace.setSize(400, 200);
        frame_enlace.setLocationRelativeTo(null);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        BorrarArchivo = new javax.swing.JMenuItem();
        DescargarArchivo = new javax.swing.JMenuItem();
        Inicio = new javax.swing.JFrame();
        Logo = new javax.swing.JLabel();
        ImagenDerecha = new javax.swing.JLabel();
        InicioSesion = new javax.swing.JButton();
        Registrar = new javax.swing.JButton();
        registrarPress = new javax.swing.JLabel();
        inicioPress = new javax.swing.JLabel();
        IconoCandado = new javax.swing.JLabel();
        Contrasena = new javax.swing.JPasswordField();
        IconoUsuario = new javax.swing.JLabel();
        userName = new javax.swing.JTextField();
        Bienvenido = new javax.swing.JLabel();
        LayerAzul = new javax.swing.JLabel();
        LayerBlanco = new javax.swing.JLabel();
        Layer = new javax.swing.JLabel();
        Usuarios = new javax.swing.JFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        Usuarios_tabla = new javax.swing.JTable();
        frame_contrasena = new javax.swing.JFrame();
        jLabel2 = new javax.swing.JLabel();
        aceptarPass = new javax.swing.JButton();
        passTextField = new javax.swing.JTextField();
        frame_enlace = new javax.swing.JFrame();
        jLabel3 = new javax.swing.JLabel();
        aceptar_enlace = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDoc = new javax.swing.JTable();
        jScrollPane_compartidos = new javax.swing.JScrollPane();
        tabla_compartidos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        labelUser = new javax.swing.JLabel();
        btnSubir = new javax.swing.JButton();
        subirPress = new javax.swing.JLabel();
        seleccionarbtn = new javax.swing.JButton();
        seleccionarPress = new javax.swing.JLabel();
        lblFileStatusC = new javax.swing.JLabel();
        cerrarSesion = new javax.swing.JButton();
        cerrarSesionPress = new javax.swing.JLabel();
        Logotipo = new javax.swing.JLabel();
        LayerApp = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu_archivo = new javax.swing.JMenu();
        menu_archivo_descargar = new javax.swing.JMenuItem();
        menu_archivo_subir = new javax.swing.JMenuItem();
        menu_archivo_modificar = new javax.swing.JMenuItem();
        menu_compartir = new javax.swing.JMenu();
        menu_compartir_enviar = new javax.swing.JMenuItem();
        menu_compartir_enlace = new javax.swing.JMenuItem();
        menu_compartir_leerEnlace = new javax.swing.JMenuItem();
        menu_usuario = new javax.swing.JMenu();
        menu_usuario_contrasena = new javax.swing.JMenuItem();
        menu_usuario_nombre = new javax.swing.JMenuItem();
        menu_usuario_recuperar = new javax.swing.JMenuItem();
        menu_carpeta = new javax.swing.JMenu();
        menu_carpeta_añadir = new javax.swing.JMenuItem();
        menu_carpeta_eliminar = new javax.swing.JMenuItem();
        menu_seguridad = new javax.swing.JMenu();
        menu_seguridad_privado = new javax.swing.JMenuItem();
        menu_seguridad_publico = new javax.swing.JMenuItem();
        menu_seguridad_administrar = new javax.swing.JMenuItem();

        BorrarArchivo.setText("Eliminar archivo");
        BorrarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BorrarArchivoActionPerformed(evt);
            }
        });
        jPopupMenu1.add(BorrarArchivo);

        DescargarArchivo.setText("Descargar");
        DescargarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DescargarArchivoActionPerformed(evt);
            }
        });
        jPopupMenu1.add(DescargarArchivo);

        Inicio.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        Inicio.setTitle("Inicio de sesion");
        Inicio.setBackground(new java.awt.Color(153, 255, 153));
        Inicio.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                InicioWindowOpened(evt);
            }
        });
        Inicio.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/Inicio-de-sesion_0003_logo.png"))); // NOI18N
        Inicio.getContentPane().add(Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 56, 356, 110));

        ImagenDerecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/Inicio-de-sesion_0009_Imagen-derecha.png"))); // NOI18N
        Inicio.getContentPane().add(ImagenDerecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 160, 670, 760));

        InicioSesion.setForeground(new java.awt.Color(0, 153, 255));
        InicioSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/Inicio-de-sesion_0002_Iniciar-Sesión.png"))); // NOI18N
        InicioSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InicioSesionMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                InicioSesionMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                InicioSesionMouseReleased(evt);
            }
        });
        InicioSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InicioSesionActionPerformed(evt);
            }
        });
        Inicio.getContentPane().add(InicioSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 620, 254, 44));

        Registrar.setBackground(new java.awt.Color(0, 204, 255));
        Registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/Inicio-de-sesion_0001_Registrar.png"))); // NOI18N
        Registrar.setBorder(null);
        Registrar.setBorderPainted(false);
        Registrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                RegistrarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                RegistrarMouseReleased(evt);
            }
        });
        Inicio.getContentPane().add(Registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 620, 254, 44));

        registrarPress.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/Inicio-de-sesion_0001_Registrar-presionado.png"))); // NOI18N
        Inicio.getContentPane().add(registrarPress, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 620, -1, -1));

        inicioPress.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/Inicio-de-sesion_0002_Iniciar-Sesión-Presionado.png"))); // NOI18N
        inicioPress.setToolTipText("");
        inicioPress.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Inicio.getContentPane().add(inicioPress, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 620, 280, 50));

        IconoCandado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/Inicio-de-sesion_0007_Icono-candado.png"))); // NOI18N
        Inicio.getContentPane().add(IconoCandado, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 440, 39, 46));

        Contrasena.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Inicio.getContentPane().add(Contrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 420, 505, 77));

        IconoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/Inicio-de-sesion_0006_Icono-persona.png"))); // NOI18N
        Inicio.getContentPane().add(IconoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 46, 54));

        userName.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Inicio.getContentPane().add(userName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, 505, 77));

        Bienvenido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/Inicio-de-sesion_0000_Bienvenido.png"))); // NOI18N
        Inicio.getContentPane().add(Bienvenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 233, 32));

        LayerAzul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/Inicio-de-sesion_0008_Degradado-azul.png"))); // NOI18N
        Inicio.getContentPane().add(LayerAzul, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 727, 940));

        LayerBlanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/Inicio-de-sesion_0010_Cuadro-blanco.png"))); // NOI18N
        Inicio.getContentPane().add(LayerBlanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 0, 730, 940));

        Layer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/Inicio-de-sesion_0005_Layer-3.png"))); // NOI18N
        Inicio.getContentPane().add(Layer, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 1450, 940));

        Usuarios_tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Usuarios:"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Usuarios_tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Usuarios_tablaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Usuarios_tabla);

        javax.swing.GroupLayout UsuariosLayout = new javax.swing.GroupLayout(Usuarios.getContentPane());
        Usuarios.getContentPane().setLayout(UsuariosLayout);
        UsuariosLayout.setHorizontalGroup(
            UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        UsuariosLayout.setVerticalGroup(
            UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        frame_contrasena.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        frame_contrasena.setAlwaysOnTop(true);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Escriba su nueva contraseña");

        aceptarPass.setText("Aceptar");
        aceptarPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarPassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout frame_contrasenaLayout = new javax.swing.GroupLayout(frame_contrasena.getContentPane());
        frame_contrasena.getContentPane().setLayout(frame_contrasenaLayout);
        frame_contrasenaLayout.setHorizontalGroup(
            frame_contrasenaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frame_contrasenaLayout.createSequentialGroup()
                .addGroup(frame_contrasenaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frame_contrasenaLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(frame_contrasenaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(frame_contrasenaLayout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(aceptarPass)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        frame_contrasenaLayout.setVerticalGroup(
            frame_contrasenaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frame_contrasenaLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(passTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(aceptarPass)
                .addContainerGap())
        );

        jLabel3.setText("Introduzca el enlace del archivo");

        aceptar_enlace.setText("Aceptar");
        aceptar_enlace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptar_enlaceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout frame_enlaceLayout = new javax.swing.GroupLayout(frame_enlace.getContentPane());
        frame_enlace.getContentPane().setLayout(frame_enlaceLayout);
        frame_enlaceLayout.setHorizontalGroup(
            frame_enlaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frame_enlaceLayout.createSequentialGroup()
                .addGroup(frame_enlaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frame_enlaceLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(frame_enlaceLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel3))
                    .addGroup(frame_enlaceLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(aceptar_enlace)))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        frame_enlaceLayout.setVerticalGroup(
            frame_enlaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frame_enlaceLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(aceptar_enlace)
                .addContainerGap(162, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("DJL Cloud Manager");
        setForeground(new java.awt.Color(51, 255, 51));
        setMinimumSize(new java.awt.Dimension(1593, 864));
        setPreferredSize(new java.awt.Dimension(1580, 864));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblDoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Tamaño", "Formato", "Ult. Modificación"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDoc.setComponentPopupMenu(jPopupMenu1);
        tblDoc.setGridColor(new java.awt.Color(51, 51, 51));
        tblDoc.setOpaque(false);
        jScrollPane1.setViewportView(tblDoc);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 310, 970, 180));

        tabla_compartidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Tamaño", "Formato", "Ult. Modificación"
            }
        ));
        jScrollPane_compartidos.setViewportView(tabla_compartidos);

        getContentPane().add(jScrollPane_compartidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 640, 970, 170));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Archivos compartidos conmigo:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 600, 290, 40));

        labelUser.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        labelUser.setText("Bienvenidos usuariossssssssssssssssssss");
        getContentPane().add(labelUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 270, 400, 28));

        btnSubir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pngapp/Dentro-del-programa_0004_Boton-subir.png"))); // NOI18N
        btnSubir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSubirMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnSubirMouseReleased(evt);
            }
        });
        btnSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSubir, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 550, 184, 35));

        subirPress.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pngapp/Boton subir copy.png"))); // NOI18N
        getContentPane().add(subirPress, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 550, -1, -1));

        seleccionarbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pngapp/Dentro-del-programa_0005_Boton-seleccionar-archivo.png"))); // NOI18N
        seleccionarbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                seleccionarbtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                seleccionarbtnMouseReleased(evt);
            }
        });
        seleccionarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarbtnActionPerformed(evt);
            }
        });
        getContentPane().add(seleccionarbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, 283, 40));

        seleccionarPress.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pngapp/Boton seleccionar archivo copy.png"))); // NOI18N
        getContentPane().add(seleccionarPress, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 480, -1, -1));

        lblFileStatusC.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblFileStatusC.setForeground(new java.awt.Color(255, 255, 255));
        lblFileStatusC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFileStatusC.setText("Escoja un archivo");
        getContentPane().add(lblFileStatusC, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 420, 420, 50));

        cerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pngapp/Dentro-del-programa_0006_Boton-cerrar-sesion.png"))); // NOI18N
        cerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cerrarSesionMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cerrarSesionMouseReleased(evt);
            }
        });
        cerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarSesionActionPerformed(evt);
            }
        });
        getContentPane().add(cerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 80, 247, 38));

        cerrarSesionPress.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pngapp/Boton cerrar sesion copy.png"))); // NOI18N
        getContentPane().add(cerrarSesionPress, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 80, -1, -1));

        Logotipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pngapp/Dentro-del-programa_0002_logo.png"))); // NOI18N
        Logotipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogotipoMouseClicked(evt);
            }
        });
        getContentPane().add(Logotipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 730, 210));

        LayerApp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pngapp/Dentro-del-programa_0008_Rectangle-1-copy.png"))); // NOI18N
        LayerApp.setPreferredSize(new java.awt.Dimension(1559, 894));
        LayerApp.setRequestFocusEnabled(false);
        getContentPane().add(LayerApp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 1560, -1));

        menu_archivo.setText("Archivos");
        menu_archivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_archivoActionPerformed(evt);
            }
        });

        menu_archivo_descargar.setText("Descargar");
        menu_archivo_descargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_archivo_descargarActionPerformed(evt);
            }
        });
        menu_archivo.add(menu_archivo_descargar);

        menu_archivo_subir.setText("Subir");
        menu_archivo_subir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_archivo_subirActionPerformed(evt);
            }
        });
        menu_archivo.add(menu_archivo_subir);

        menu_archivo_modificar.setText("Modificar");
        menu_archivo_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_archivo_modificarActionPerformed(evt);
            }
        });
        menu_archivo.add(menu_archivo_modificar);

        jMenuBar1.add(menu_archivo);

        menu_compartir.setText("Compartir");

        menu_compartir_enviar.setText("Compartir con un usuario");
        menu_compartir_enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_compartir_enviarActionPerformed(evt);
            }
        });
        menu_compartir.add(menu_compartir_enviar);

        menu_compartir_enlace.setText("Crear enlace del archivo");
        menu_compartir_enlace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_compartir_enlaceActionPerformed(evt);
            }
        });
        menu_compartir.add(menu_compartir_enlace);

        menu_compartir_leerEnlace.setText("Leer enlace");
        menu_compartir_leerEnlace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_compartir_leerEnlaceActionPerformed(evt);
            }
        });
        menu_compartir.add(menu_compartir_leerEnlace);

        jMenuBar1.add(menu_compartir);

        menu_usuario.setText("Usuario");

        menu_usuario_contrasena.setText("Cambiar contraseña");
        menu_usuario_contrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_usuario_contrasenaActionPerformed(evt);
            }
        });
        menu_usuario.add(menu_usuario_contrasena);

        menu_usuario_nombre.setText("Cambiar nombre de usuario");
        menu_usuario_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_usuario_nombreActionPerformed(evt);
            }
        });
        menu_usuario.add(menu_usuario_nombre);

        menu_usuario_recuperar.setText("Recuperar contraseña");
        menu_usuario_recuperar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_usuario_recuperarActionPerformed(evt);
            }
        });
        menu_usuario.add(menu_usuario_recuperar);

        jMenuBar1.add(menu_usuario);

        menu_carpeta.setText("Carpeta");

        menu_carpeta_añadir.setText("Añadir participante");
        menu_carpeta_añadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_carpeta_añadirActionPerformed(evt);
            }
        });
        menu_carpeta.add(menu_carpeta_añadir);

        menu_carpeta_eliminar.setText("Eliminar participante");
        menu_carpeta_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_carpeta_eliminarActionPerformed(evt);
            }
        });
        menu_carpeta.add(menu_carpeta_eliminar);

        jMenuBar1.add(menu_carpeta);

        menu_seguridad.setText("Seguridad");

        menu_seguridad_privado.setText("Definir archivo privado");
        menu_seguridad_privado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_seguridad_privadoActionPerformed(evt);
            }
        });
        menu_seguridad.add(menu_seguridad_privado);

        menu_seguridad_publico.setText("Definir archivo publico");
        menu_seguridad_publico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_seguridad_publicoActionPerformed(evt);
            }
        });
        menu_seguridad.add(menu_seguridad_publico);

        menu_seguridad_administrar.setText("Administrar usuarios con permiso");
        menu_seguridad_administrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_seguridad_administrarActionPerformed(evt);
            }
        });
        menu_seguridad.add(menu_seguridad_administrar);

        jMenuBar1.add(menu_seguridad);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void cerrar(){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                control.cerrarSesion();
                System.exit(0);
            }
        });
        
    }
    private void BorrarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarArchivoActionPerformed
        try {
            // TODO add your handling code here:
            int index1 = tblDoc.getSelectedRow();
            control.eliminar(tblDoc.getValueAt(index1, 0).toString());
            control.update(tblDoc,tabla_compartidos);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BorrarArchivoActionPerformed

    private void DescargarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DescargarArchivoActionPerformed
// TODO add your handling code here:
            index[0] = tblDoc.getSelectedRow();
            try {
                if(index[0]>=0){
                    control.descargarArchivo(tblDoc.getValueAt(index[0], 0).toString());
                }else{
                    index[0] = tabla_compartidos.getSelectedRow();
                    if(index[0]>=0){
                        control.descargarArchivo(tabla_compartidos.getValueAt(index[0], 0).toString());
                    }
                }
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }//GEN-LAST:event_DescargarArchivoActionPerformed

    private void InicioWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_InicioWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_InicioWindowOpened

    private void InicioSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InicioSesionMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_InicioSesionMouseClicked

    private void InicioSesionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InicioSesionMousePressed
        // TODO add your handling code here:
        inicioPress.setVisible(true);
        InicioSesion.setVisible(false);
        
    }//GEN-LAST:event_InicioSesionMousePressed

    private void InicioSesionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InicioSesionMouseReleased
        // TODO add your handling code here:
        inicioPress.setVisible(false);
        InicioSesion.setVisible(true);
        
        try {
            Inicio.setAlwaysOnTop(false);
            int i=control.enviarUsuario(userName.getText(), Contrasena.getText(), 4);
            // TODO add your handling code here:
            if(i==1){
                this.setVisible(true);
                actualizarTabla();
                labelUser.setText("Bienvenido "+userName.getText());
                labelUser.setSize(500,50);
                Inicio.dispose();
            }else if(i==2){
                this.setVisible(true);
                control.tablaAdmin(tblDoc);
                labelUser.setText("Estadisticas");
                labelUser.setSize(500,50);
                Inicio.dispose();
                
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_InicioSesionMouseReleased

    private void RegistrarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegistrarMousePressed
        // TODO add your handling code here:
        Registrar.setVisible(false);
        registrarPress.setVisible(true);
    }//GEN-LAST:event_RegistrarMousePressed

    private void RegistrarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegistrarMouseReleased
        // TODO add your handling code here:
        Registrar.setVisible(true);
        registrarPress.setVisible(false);
        try {
            Inicio.setAlwaysOnTop(false);
            // TODO add your handling code here:
            if(control.enviarUsuario(userName.getText().toLowerCase(), Contrasena.getText(), 5)==1){
                labelUser.setText("Bienvenido "+userName.getText());
                Inicio.dispose();
                this.setVisible(true);
                actualizarTabla();
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_RegistrarMouseReleased

    private void seleccionarbtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarbtnMousePressed
        // TODO add your handling code here:
        seleccionarbtn.setVisible(false);
        seleccionarPress.setVisible(true);
    
    }//GEN-LAST:event_seleccionarbtnMousePressed

    private void seleccionarbtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarbtnMouseReleased
        // TODO add your handling code here:
        
        seleccionarbtn.setVisible(true);
        seleccionarPress.setVisible(false);
        control.escogerArchivo(lblFileStatusC);
        
    }//GEN-LAST:event_seleccionarbtnMouseReleased

    private void cerrarSesionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarSesionMousePressed
        // TODO add your handling code here:
        cerrarSesion.setVisible(false);
        cerrarSesionPress.setVisible(true);
    }//GEN-LAST:event_cerrarSesionMousePressed

    private void cerrarSesionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarSesionMouseReleased
        // TODO add your handling code here:
        cerrarSesion.setVisible(true);
        cerrarSesionPress.setVisible(false);
        DefaultTableModel tabla=(DefaultTableModel) tblDoc.getModel();
        tabla.setRowCount(0);
        userName.setText("");
        Contrasena.setText("");
        Inicio.setVisible(true);
        labelUser.setText("Waiting...");
        control.cerrarSesion();
        this.dispose();
    }//GEN-LAST:event_cerrarSesionMouseReleased

    private void LogotipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogotipoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LogotipoMouseClicked

    private void cerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cerrarSesionActionPerformed

    private void menu_archivo_descargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_archivo_descargarActionPerformed
        index[0] = tblDoc.getSelectedRow();
            try {
                if(index[0]>=0){
                    control.descargarArchivo(tblDoc.getValueAt(index[0], 0).toString());
                }
                else{
                    index[0] = tabla_compartidos.getSelectedRow();
                    if(index[0]>=0){
                        control.descargarArchivo(tabla_compartidos.getValueAt(index[0], 0).toString());
                    }
                }
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }//GEN-LAST:event_menu_archivo_descargarActionPerformed

    private void menu_archivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_archivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menu_archivoActionPerformed

    private void menu_archivo_subirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_archivo_subirActionPerformed
        // TODO add your handling code here:    
        control.escogerArchivo(lblFileStatusC);
        Thread load;
        try {
            // TODO add your handling code here:
            load=control.subirArchivo(lblFileStatusC);
            Thread verificar=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        load.join();
                        actualizarTabla();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }});

                verificar.start();
            } catch (ClassNotFoundException | IOException | InterruptedException ex) {
            }
    }//GEN-LAST:event_menu_archivo_subirActionPerformed

    private void menu_archivo_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_archivo_modificarActionPerformed
        // TODO add your handling code here:
        index[0] = tblDoc.getSelectedRow();
        if(index[0]>=0){
            Thread u=new Thread(new Runnable() {
                @Override
                public void run() {
                    control.modificar(tblDoc.getValueAt(index[0], 0).toString());
                }});
            u.run();
            try {
                u.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            actualizarTabla();
        }
    }//GEN-LAST:event_menu_archivo_modificarActionPerformed

    private void btnSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSubirActionPerformed

    private void btnSubirMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubirMouseReleased
        // TODO add your handling code here:
        btnSubir.setVisible(true);
        subirPress.setVisible(false);
        Thread load;
        try {
            // TODO add your handling code here:
            load=control.subirArchivo(lblFileStatusC);
            Thread verificar=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        load.join();
                        actualizarTabla();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }});

                verificar.start();
            } catch (ClassNotFoundException | IOException | InterruptedException ex) {
            }

    }//GEN-LAST:event_btnSubirMouseReleased

    private void btnSubirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubirMousePressed
        // TODO add your handling code here:
        btnSubir.setVisible(false);
        subirPress.setVisible(true);

    }//GEN-LAST:event_btnSubirMousePressed

    private void seleccionarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarbtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarbtnActionPerformed

    private void menu_compartir_enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_compartir_enviarActionPerformed
        // TODO add your handling code here:
        index[0] = tblDoc.getSelectedRow();
        if(index[0]>=0){
            try {
                control.mostrar_usuarios(Usuarios_tabla);
                this.compartirCarpeta=0;
            } catch (ClassNotFoundException | IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            Usuarios.setAlwaysOnTop(true);
            Usuarios.setVisible(true);
        }
    }//GEN-LAST:event_menu_compartir_enviarActionPerformed

    private void InicioSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InicioSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InicioSesionActionPerformed

    private void Usuarios_tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Usuarios_tablaMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {     try {
            // to detect doble click events
            JTable target = (JTable)evt.getSource();
            int row = target.getSelectedRow(); // select a row
            int column = target.getSelectedColumn(); // select a column
            System.out.println(compartirCarpeta);
            switch (this.compartirCarpeta) {
                
                case 1:
                    control.compartir("carpeta compartida", Usuarios_tabla.getValueAt(row, column).toString());
                    this.compartirCarpeta=0;
                    break;
                case 2:
                    control.eliminarUsuarioCompartido(Usuarios_tabla.getValueAt(row, column).toString());
                    break;
                case 3:
                    System.out.println("Restringido");
                    control.restringir(Usuarios_tabla.getValueAt(row, column).toString(),tblDoc.getValueAt(index[0], 0).toString());
                    break;
                default:
                    control.compartir(tblDoc.getValueAt(index[0], 0).toString(), Usuarios_tabla.getValueAt(row, column).toString());
                    break;
            }
            Usuarios.dispose();
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_Usuarios_tablaMouseClicked

    private void aceptarPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarPassActionPerformed
        // TODO add your handling code here:
        control.cambiar_password(passTextField.getText());
        frame_contrasena.dispose();
    }//GEN-LAST:event_aceptarPassActionPerformed

    private void menu_usuario_contrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_usuario_contrasenaActionPerformed
        // TODO add your handling code here:
        frame_contrasena.setVisible(true);
    }//GEN-LAST:event_menu_usuario_contrasenaActionPerformed

    private void menu_carpeta_añadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_carpeta_añadirActionPerformed
        try {
            // TODO add your handling code here:
            this.compartirCarpeta=1;
            control.mostrar_usuarios(Usuarios_tabla);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        Usuarios.setAlwaysOnTop(true);
        Usuarios.setVisible(true);
    }//GEN-LAST:event_menu_carpeta_añadirActionPerformed

    private void menu_carpeta_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_carpeta_eliminarActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            this.compartirCarpeta=2;
            control.mostrar_usuarios(Usuarios_tabla);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        Usuarios.setAlwaysOnTop(true);
        Usuarios.setVisible(true);
    }//GEN-LAST:event_menu_carpeta_eliminarActionPerformed

    private void menu_seguridad_privadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_seguridad_privadoActionPerformed
        // TODO add your handling code here:
        index[0] = tblDoc.getSelectedRow();
        if(index[0]>=0){
            control.setPrivacidad(tblDoc.getValueAt(index[0], 0).toString(),'s');
        }
    }//GEN-LAST:event_menu_seguridad_privadoActionPerformed

    private void menu_compartir_enlaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_compartir_enlaceActionPerformed
        // TODO add your handling code here:
        index[0] = tblDoc.getSelectedRow();
        if(index[0]>=0){
            control.crear_enlace(tblDoc.getValueAt(index[0], 0).toString());
        }
    }//GEN-LAST:event_menu_compartir_enlaceActionPerformed

    private void aceptar_enlaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptar_enlaceActionPerformed
        // TODO add your handling code here:
        control.leer_enlace(jTextField1.getText());
        frame_enlace.dispose();
        actualizarTabla();
    }//GEN-LAST:event_aceptar_enlaceActionPerformed

    private void menu_compartir_leerEnlaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_compartir_leerEnlaceActionPerformed
        // TODO add your handling code here:
        frame_enlace.setVisible(true);
    }//GEN-LAST:event_menu_compartir_leerEnlaceActionPerformed

    private void menu_seguridad_administrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_seguridad_administrarActionPerformed
        // TODO add your handling code here:
        index[0] = tblDoc.getSelectedRow();
        this.compartirCarpeta=3;
        if(index[0]>=0){
            try {
                
                control.mostrar_usuarios(Usuarios_tabla);
                
            } catch (ClassNotFoundException | IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            Usuarios.setTitle("Seleccione el usuario a quien desea restringir el acceso al archivo");
            Usuarios.setAlwaysOnTop(true);
            Usuarios.setVisible(true);
        }
    }//GEN-LAST:event_menu_seguridad_administrarActionPerformed

    private void menu_seguridad_publicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_seguridad_publicoActionPerformed
        // TODO add your handling code here:
        index[0] = tblDoc.getSelectedRow();
        if(index[0]>=0){
            control.setPrivacidad(tblDoc.getValueAt(index[0], 0).toString(),'p');
        }
    }//GEN-LAST:event_menu_seguridad_publicoActionPerformed

    private void menu_usuario_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_usuario_nombreActionPerformed
        // TODO add your handling code here:
        control.ChangeUserName(labelUser);
    }//GEN-LAST:event_menu_usuario_nombreActionPerformed

    private void menu_usuario_recuperarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_usuario_recuperarActionPerformed
        // TODO add your handling code here:
        control.changePass();
    }//GEN-LAST:event_menu_usuario_recuperarActionPerformed
    private void actualizarTabla(){
        try {
            control.update(tblDoc,tabla_compartidos);
            //control.update_compartidos(tabla_compartidos);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
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
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
               
                new Cliente().setVisible(false);
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Bienvenido;
    private javax.swing.JMenuItem BorrarArchivo;
    private javax.swing.JPasswordField Contrasena;
    private javax.swing.JMenuItem DescargarArchivo;
    private javax.swing.JLabel IconoCandado;
    private javax.swing.JLabel IconoUsuario;
    private javax.swing.JLabel ImagenDerecha;
    private javax.swing.JFrame Inicio;
    private javax.swing.JButton InicioSesion;
    private javax.swing.JLabel Layer;
    private javax.swing.JLabel LayerApp;
    private javax.swing.JLabel LayerAzul;
    private javax.swing.JLabel LayerBlanco;
    private javax.swing.JLabel Logo;
    private javax.swing.JLabel Logotipo;
    private javax.swing.JButton Registrar;
    private javax.swing.JFrame Usuarios;
    private javax.swing.JTable Usuarios_tabla;
    private javax.swing.JButton aceptarPass;
    private javax.swing.JButton aceptar_enlace;
    private javax.swing.JButton btnSubir;
    private javax.swing.JButton cerrarSesion;
    private javax.swing.JLabel cerrarSesionPress;
    private javax.swing.JFrame frame_contrasena;
    private javax.swing.JFrame frame_enlace;
    private javax.swing.JLabel inicioPress;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane_compartidos;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel labelUser;
    private javax.swing.JLabel lblFileStatusC;
    private javax.swing.JMenu menu_archivo;
    private javax.swing.JMenuItem menu_archivo_descargar;
    private javax.swing.JMenuItem menu_archivo_modificar;
    private javax.swing.JMenuItem menu_archivo_subir;
    private javax.swing.JMenu menu_carpeta;
    private javax.swing.JMenuItem menu_carpeta_añadir;
    private javax.swing.JMenuItem menu_carpeta_eliminar;
    private javax.swing.JMenu menu_compartir;
    private javax.swing.JMenuItem menu_compartir_enlace;
    private javax.swing.JMenuItem menu_compartir_enviar;
    private javax.swing.JMenuItem menu_compartir_leerEnlace;
    private javax.swing.JMenu menu_seguridad;
    private javax.swing.JMenuItem menu_seguridad_administrar;
    private javax.swing.JMenuItem menu_seguridad_privado;
    private javax.swing.JMenuItem menu_seguridad_publico;
    private javax.swing.JMenu menu_usuario;
    private javax.swing.JMenuItem menu_usuario_contrasena;
    private javax.swing.JMenuItem menu_usuario_nombre;
    private javax.swing.JMenuItem menu_usuario_recuperar;
    private javax.swing.JTextField passTextField;
    private javax.swing.JLabel registrarPress;
    private javax.swing.JLabel seleccionarPress;
    private javax.swing.JButton seleccionarbtn;
    private javax.swing.JLabel subirPress;
    private javax.swing.JTable tabla_compartidos;
    private javax.swing.JTable tblDoc;
    private javax.swing.JTextField userName;
    // End of variables declaration//GEN-END:variables

}
