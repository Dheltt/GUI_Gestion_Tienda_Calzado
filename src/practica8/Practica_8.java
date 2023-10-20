package practica8;
import com.jtattoo.plaf.mcwin.McWinLookAndFeel;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.plaf.multi.MultiLookAndFeel;
public final class Practica_8 extends JFrame implements ActionListener {
    //declaramos los objetos que usaremos como globales
    JMenu operaciones;
    JMenuItem proveedores;
    JMenuItem marcas;
    JMenuItem tipos;
    JMenuItem inventario;
    JMenuItem ventas;
    //variables que usaremos para la conexion con la base de datos
    int[] clicktabla= {0,1};
    String Clave1;
    Connection cn; // Variable para almacenar la conexión
    String Driver = "com.mysql.cj.jdbc.Driver"; // Controlador JDBC para MySQL
    String usuario = "David"; // Nombre de usuario de la base de datos
    String password = "12345678"; // Contraseña de la base de datos
    String url = "jdbc:mysql://localhost:3306/tienda_calzado_tap"; // URL de la base de datos        
    DefaultTableModel modelx;
    //este metodo es para hacer la conexion con la base de datos
    public Connection Conectar() throws SQLException  {  
        try {
            Class.forName(Driver); // Cargar el controlador JDBC
            cn = DriverManager.getConnection(url, usuario, password); // Establecer la conexión
        } catch (ClassNotFoundException | SQLException ex) {
            java.util.logging.Logger.getLogger(Practica_8.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return cn; // Devolver la conexión establecida
    }
    public void Desconectar() {
        try {
            cn.close(); // Cerrar la conexión
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Practica_8.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    //metodo constructor
    public Practica_8() {
        //Configuramos la ventana principal
        super("Control de Ventas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        //invocamos al metodo que unicializara los componentes
        InicializarComponentes();
    }
    //metodo para inicializar los componentes
    public void InicializarComponentes(){
        //Creamos la barra de menú y las opciones
        JMenuBar menuBar = new JMenuBar();
        JMenu archivo = new JMenu("Archivo");
        JMenuItem salir = new JMenuItem("Salir");
        JMenu ayuda = new JMenu("Ayuda");
        JMenuItem contenido = new JMenuItem("Contenido");
        JMenuItem acercade = new JMenuItem("Acercade");
        operaciones = new JMenu("Operaciones");
        proveedores = new JMenuItem("Proveedores");
        inventario = new JMenuItem("Inventario");
        marcas = new JMenuItem("Marcas");
        tipos = new JMenuItem("Tipos");
        ventas = new JMenuItem("Ventas");
        //agregamos los componentes creados al panel
        operaciones.add(proveedores);
        operaciones.add(marcas);
        operaciones.add(tipos);
        operaciones.add(inventario);
        operaciones.add(ventas);
        menuBar.add(archivo);
        menuBar.add(operaciones);
        menuBar.add(ayuda);
        archivo.add(salir);
        ayuda.add(contenido);
        ayuda.add(acercade);
        setJMenuBar(menuBar);
        //invocamos  a los metodos que inicilizaran los componentes de cada opcion
        Contenido(contenido);
        Acercade(acercade);
        Proveedores(proveedores);
        Marcas(marcas);
        Tipos(tipos);
        Inventario(inventario);
        Ventas(ventas);
        Salir(salir);    
    }
    public void Salir(JMenuItem salir){
        //con este action listener le decimos que al darle click en este opcion realize lo que esta demtro del metodo
        salir.addActionListener((ActionEvent e) -> {System.exit(0);});     
    }
    public void Acercade(JMenuItem acercade){   
        //con este action listenes le decimos que al darle click en este opcion realize lo que esta dentro del metodo
        acercade.addActionListener((ActionEvent e) -> {
            // Creamos un botón personalizado para la ventana emergente
            JButton customButton = new JButton("Aceptar");
            
            // Creamos un objeto JOptionPane con un mensaje personalizado y nuestro botón personalizado
            Object[] options = { customButton };
            JOptionPane optionPane = new JOptionPane("Programador:David Fuerte Ramirez", JOptionPane.PLAIN_MESSAGE, JOptionPane.YES_NO_OPTION, null, options, customButton);
            
            // Creamos un JDialog para mostrar la ventana emergente
            JDialog dialog = optionPane.createDialog("Aceptar");
            
            // Asignamos un ActionListener al botón personalizado
            customButton.addActionListener((ActionEvent e1) -> {
                // Cerrar la ventana emergente al hacer clic en el botón personalizado
                dialog.dispose();
            });
            // Mostramos la ventana emergente
            dialog.setVisible(true);
        });      
    }
    public void Contenido(JMenuItem contenido){
        //con este action listener le decimos que al darle click en este opcion realize lo que esta detro del metodo
        contenido.addActionListener((ActionEvent e) -> {
            // Crear el panel
            JPanel pContent = new JPanel();
            pContent.setSize(300, 200);
            pContent.setLayout(new BorderLayout());
            pContent.setBackground(Color.LIGHT_GRAY);
            // Crear el contenido del panel
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setText("""
                    Menu archivo
                    Opcion salir - permite salir de la aplicación
                    Menu Opciones
                    Opcion Proveedores - permite insertar, modificar, borrar y consultar proveedores
                    Opcion Marcas - permite insertar, modificar, borrar y consultar marcas de productos
                    Opcion Tipos - permite insertar, modificar, borrar y consultar tipos de productos
                    Opcion Inventario - permite insertar, modificar, borrar y consultar productos en inventario
                    Opcion Ventas - permite insertar, modificar, borrar y consultar ventas realizadas
                    Menu Ayuda
                    Opcion Contenido - permite obtener información de ayuda de la aplicación
                    Opcion Acerca de - permite obtener información acerca del programador
                             """);
            
            JScrollPane scrollPane = new JScrollPane(textArea);
            
            // Crear el botón de cerrar
            JButton closeButton = new JButton("Cerrar");
            
            // Agregar componentes al panel
            pContent.add(scrollPane, BorderLayout.CENTER);
            pContent.add(closeButton, BorderLayout.SOUTH);
            
            // Crear un JDialog para mostrar el panel
            JDialog dialog = new JDialog();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setTitle("Contenido");
            dialog.getContentPane().add(pContent);
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
            
            closeButton.addActionListener((ActionEvent e1) -> {
                // Cerrar el diálogo al hacer clic en el botón
                dialog.dispose();
            });
        });
    }
    public void Proveedores(JMenuItem ventas){
        proveedores.addActionListener((ActionEvent e) -> {
                //creamos el panel y su contenido
                JPanel p1 = new JPanel();
                p1.setLayout(null);
                DefaultTableModel dtm = new DefaultTableModel(); // Crear modelo de tabla vacío
                dtm.addColumn("Clave");
                dtm.addColumn("Nombre");
                dtm.addColumn("Direccion");
                dtm.addColumn("Telefono");
                dtm.addColumn("Email");
                dtm.addColumn("Fecha_de_convenio");
                // Obtener los datos de la tabla proveedores desde la base de datos
                String query = "SELECT clave_p, nombre, direccion, telefono, email, fecha_convenio FROM proveedores";
                try { 
                    System.out.println("todo bien aqui");
                    Statement statement = cn.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    // Recorrer los resultados del ResultSet y agregarlos al modelo de la tabla
                    while (resultSet.next()) {
                        System.out.println("agregando");
                        Object[] rowData = new Object[6]; // 6 columnas en la tabla
                        rowData[0] = resultSet.getString("clave_p");
                        rowData[1] = resultSet.getString("nombre");
                        rowData[2] = resultSet.getString("direccion");
                        rowData[3] = resultSet.getString("telefono");
                        rowData[4] = resultSet.getString("email");
                        rowData[5] = resultSet.getString("fecha_convenio");
                        dtm.addRow(rowData); // Agregar la fila al modelo de la tabla
                    }
                } catch (SQLException ex) {
                    System.out.println("hay pedillos");
                }
                //creamos la tabla y le agregamos el defaultmodel
                JTable t1 = new JTable(dtm);
                JScrollPane scp = new JScrollPane(t1);
                scp.setBounds(80, 140, 400, 200);
                
                p1.setSize(600, 400);
                p1.setLocation(20, 20);
                
                JButton closeButton = new JButton("Salir de opción");
                closeButton.setBounds(300, 50, 120, 30);
                JLabel titulo = new JLabel("Opción Proveedores");
                titulo.setBounds(10, 20, 150, 50);
                JButton btnuevo = new JButton("Nuevo");
                btnuevo.setBounds(150, 50, 100, 30);
                JButton btborrar = new JButton("Borrar");
                JButton btmodificar = new JButton("Modificar");
                btborrar.setBounds(300, 350, 100, 30);
                btmodificar.setBounds(450, 350, 100, 30);
                //JScrollPane scrollPane = new JScrollPane(t1); // Agregar la tabla a un JScrollPane
                p1.add(scp);
                p1.add(titulo);
                p1.add(closeButton);
                p1.add(btnuevo);
                p1.add(btmodificar);
                p1.add(btborrar);
                //boton para cerrar el panel
                btborrar.addActionListener((ActionEvent e1) -> {
                    // Obtener la fila seleccionada de la tabla
                    int filaSeleccionada = t1.getSelectedRow();
                    if (filaSeleccionada >= 0) {
                        // Obtener el valor de la clave de la fila seleccionada
                        String clave = dtm.getValueAt(filaSeleccionada, 0).toString();

                        // Eliminar la fila seleccionada de la tabla
                        dtm.removeRow(filaSeleccionada);

                        // Eliminar los datos de la fila seleccionada de la base de datos
                        String deleteQuery = "DELETE FROM proveedores WHERE clave_p = ?";
                        try {
                            PreparedStatement statement = cn.prepareStatement(deleteQuery);
                            statement.setString(1, clave);
                            statement.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Fila eliminada correctamente");
                            statement.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al eliminar la fila");
                        }
                    }
                });
                closeButton.addActionListener((ActionEvent e1) -> {
                    // Cerrar el panel al hacer clic en el botón
                    p1.setVisible(false);
                });
                //este apartado es para que muestre un nuevo panel con su respectivo contenido
                btnuevo.addActionListener((ActionEvent e1) -> {
                    p1.setVisible(false);
                    JPanel P1 = new JPanel(null);
                    JPanel P2 = new JPanel(null);
                    P1.setBounds(20,40,600,450);
                    P2.setSize(500,300);
                    P2.setLocation(25, 65);
                    // creamos los componentes
                    JLabel jltitulo = new JLabel("Opcion Ventas-Nuevo registro");
                    JLabel jlclave = new JLabel("clave");
                    JLabel jlnombre = new JLabel("Nombre");
                    JLabel jldireccion = new JLabel("Dirreccion");
                    JLabel jltelefono = new JLabel("Telefono");
                    JLabel jlemail = new JLabel("Email");
                    JLabel jlfecha = new JLabel("Fecha de convenio");
                    JTextField txtclave = new JTextField(40);
                    JTextField txtnombre = new JTextField(40);
                    JTextField txtdireccion = new JTextField(40);
                    JTextField txttelefono = new JTextField(40);
                    JTextField txtemail= new JTextField(40);
                    JTextField txtfecha= new JTextField(40);
                    // establecemos las cordenadas y las dimensiones de los componentes
                    jltitulo.setBounds(5,5,200,20);
                    jlclave.setBounds(129, 40, 100, 20);
                    jlnombre.setBounds(116, 80, 100, 20);
                    jldireccion.setBounds(100, 120, 100, 20);
                    jltelefono.setBounds(108, 160, 100, 20);
                    jlemail.setBounds(129, 200, 100, 20);
                    jlfecha.setBounds(51, 240, 150, 20);
                    txtclave.setBounds(163,40,80,20);
                    txtnombre.setBounds(163,80,200,20);
                    txtdireccion.setBounds(163,120,200,20);
                    txttelefono.setBounds(163,160,80,20);
                    txtemail.setBounds(163,200,200,20);
                    txtfecha.setBounds(163,240,80 ,20);
                    //agregamos los componentes al panel
                    P2.add(jlclave);
                    P2.add(jldireccion);
                    P2.add(jlemail);
                    P2.add(jlfecha);
                    P2.add(jlnombre);
                    P2.add(jltelefono);
                    P2.add(txtclave);
                    P2.add(txtnombre);
                    P2.add(txtdireccion);
                    P2.add(txtemail);
                    P2.add(txtfecha);
                    P2.add(txttelefono);
                    P1.add(jltitulo);
                   
                    JButton closeButton1 = new JButton("Regresar");
                    closeButton1.setBounds(350, 400, 100, 30);
                    JButton Guardar = new JButton("Guardar");
                    Guardar.setBounds(200,400, 100,30);
                    P1.add(closeButton1);
                    P1.add(Guardar);
                    //boton para cerrar el panel
                    closeButton1.addActionListener((ActionEvent e2) -> {
                        // Cerrar el panel al hacer clic en el botón
                        P1.setVisible(false);
                        P2.setVisible(false);
                        p1.setVisible(true);
                    });
                    //agregamos los componentes al panel
                    P1.add(P2);
                    add(P1,P2);
                    P2.setVisible(true);
                    P1.setVisible(true);
                    Guardar.addActionListener((ActionEvent e3)->{
                            // Obtener los nuevos valores ingresados en los campos de texto
                        String nuevaClave = txtclave.getText();
                        String nuevoNombre = txtnombre.getText();
                        String nuevaDireccion = txtdireccion.getText();
                        String nuevoTelefono = txttelefono.getText();
                        String nuevoEmail = txtemail.getText();
                        String nuevaFechaConvenio = txtfecha.getText();

                        // Agregar una nueva fila al modelo de la tabla
                        Object[] nuevaFila = {nuevaClave, nuevoNombre, nuevaDireccion, nuevoTelefono, nuevoEmail, nuevaFechaConvenio};
                        dtm.addRow(nuevaFila);

                        // Insertar los datos de la nueva fila en la base de datos
                        String insertQuery = "INSERT INTO proveedores (clave_p, nombre, direccion, telefono, email, fecha_convenio) VALUES (?, ?, ?, ?, ?, ?)";
                        try {
                            PreparedStatement statement = cn.prepareStatement(insertQuery);
                            statement.setString(1, nuevaClave);
                            statement.setString(2, nuevoNombre);
                            statement.setString(3, nuevaDireccion);
                            statement.setString(4, nuevoTelefono);
                            statement.setString(5, nuevoEmail);
                            statement.setString(6, nuevaFechaConvenio);
                            statement.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Nueva fila agregada correctamente");
                            statement.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al agregar la nueva fila");
                        }                    
                    });
                     
                });
                //este apartado es para que muestre un nuevo panel con su respectivo contenido
                btmodificar.addActionListener((ActionEvent e1) -> {
                    p1.setVisible(false);
                    JPanel P1 = new JPanel(null);
                    JPanel P2 = new JPanel(null);
                    P1.setBounds(20,40,600,450);
                    P2.setSize(500,300);
                    P2.setLocation(25, 65);
                    // creamos los componentes
                    JLabel jltitulo = new JLabel("Opcion Proveedores-Nuevo registro");
                    JLabel jlclave = new JLabel("clave");
                    JLabel jlnombre = new JLabel("Nombre");
                    JLabel jldireccion = new JLabel("Dirreccion");
                    JLabel jltelefono = new JLabel("Telefono");
                    JLabel jlemail = new JLabel("Email");
                    JLabel jlfecha = new JLabel("Fecha de convenio");
                    JTextField txtclave = new JTextField(40);
                    JTextField txtnombre = new JTextField(40);
                    JTextField txtdireccion = new JTextField(40);
                    JTextField txttelefono = new JTextField(40);
                    JTextField txtemail= new JTextField(40);
                    JTextField txtfecha= new JTextField(40);
                    // establecemos las cordenadas y las dimensiones de los componentes
                    jltitulo.setBounds(5,5,200,20);
                    jlclave.setBounds(129, 40, 100, 20);
                    jlnombre.setBounds(116, 80, 100, 20);
                    jldireccion.setBounds(100, 120, 100, 20);
                    jltelefono.setBounds(108, 160, 100, 20);
                    jlemail.setBounds(129, 200, 100, 20);
                    jlfecha.setBounds(51, 240, 150, 20);
                    txtclave.setBounds(163,40,80,20);
                    txtnombre.setBounds(163,80,200,20);
                    txtdireccion.setBounds(163,120,200,20);
                    txttelefono.setBounds(163,160,80,20);
                    txtemail.setBounds(163,200,200,20);
                    txtfecha.setBounds(163,240,80 ,20);
                    //agregamos los componentes al panel
                    P2.add(jlclave);
                    P2.add(jldireccion);
                    P2.add(jlemail);
                    P2.add(jlfecha);
                    P2.add(jlnombre);
                    P2.add(jltelefono);
                    P2.add(txtclave);
                    P2.add(txtnombre);
                    P2.add(txtdireccion);
                    P2.add(txtemail);
                    P2.add(txtfecha);
                    P2.add(txttelefono);
                    P1.add(jltitulo);
                    JButton closeButton1 = new JButton("Regresar");
                    closeButton1.setBounds(350, 400, 100, 30);
                    JButton Guardar = new JButton("Guardar");
                    Guardar.setBounds(200,400, 100,30);
                    P1.add(closeButton1);
                    P1.add(Guardar);
                    // Obtener los datos de la fila seleccionada de la tabla
                    int filaSeleccionada = t1.getSelectedRow();
                    if (filaSeleccionada >= 0) {
                        //obtenemos los atributos respectivos
                        String clave = dtm.getValueAt(filaSeleccionada, 0).toString();
                        String nombre = dtm.getValueAt(filaSeleccionada, 1).toString();
                        String direccion = dtm.getValueAt(filaSeleccionada, 2).toString();
                        String telefono = dtm.getValueAt(filaSeleccionada, 3).toString();
                        String email = dtm.getValueAt(filaSeleccionada, 4).toString();
                        String fechaConvenio = dtm.getValueAt(filaSeleccionada, 5).toString();

                        // Establecer los valores de la fila seleccionada en los campos de texto
                        txtclave.setText(clave);
                        txtnombre.setText(nombre);
                        txtdireccion.setText(direccion);
                        txttelefono.setText(telefono);
                        txtemail.setText(email);
                        txtfecha.setText(fechaConvenio);
                        Guardar.addActionListener((ActionEvent x) -> {
                            // Obtener los nuevos valores ingresados en los campos de texto
                            String nuevoNombre = txtnombre.getText();
                            String nuevaDireccion = txtdireccion.getText();
                            String nuevoTelefono = txttelefono.getText();
                            String nuevoEmail = txtemail.getText();
                            String nuevaFechaConvenio = txtfecha.getText();

                            // Actualizar los datos en la base de datos
                            String updateQuery = "UPDATE proveedores SET nombre = ?, direccion = ?, telefono = ?, email = ?, fecha_convenio = ? WHERE clave_p = ?";
                            try {
                                PreparedStatement statement = cn.prepareStatement(updateQuery);
                                statement.setString(1, nuevoNombre);
                                statement.setString(2, nuevaDireccion);
                                statement.setString(3, nuevoTelefono);
                                statement.setString(4, nuevoEmail);
                                statement.setString(5, nuevaFechaConvenio);
                                statement.setString(6, clave);
                                statement.executeUpdate();
                                JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
                                statement.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Error al actualizar los datos");
                            }

                            // Cerrar el panel al hacer clic en el botón
                            P1.setVisible(false);
                            P2.setVisible(false);
                            p1.setVisible(true);
                                });
                    } else {
                        JOptionPane.showMessageDialog(null, "Seleccione una fila para modificar");
                    }
                    closeButton1.addActionListener((ActionEvent e2) -> {
                        // Cerrar el panel al hacer clic en el botón
                        P1.setVisible(false);
                        P2.setVisible(false);
                        p1.setVisible(true);
                    });
                    //agregamos los componentes al panel
                    P1.add(P2);
                    add(P1,P2);
                    P2.setVisible(true);
                    P1.setVisible(true);
                });
                add(p1);
        });
    }
    public void Marcas(JMenuItem marcas){
        //agregamos los compomnetes a la ventana
        marcas.addActionListener((ActionEvent e) -> {
            JPanel p2 = new JPanel();
            p2.setLayout(null);
            p2.setSize(600, 400);
            p2.setLocation(20, 20);
            //cargamos los datos
            String[] campos={"Clave","Nombre","Tipo"};
            String[] registros= new String[3];
            String sql="SELECT * FROM marcas";
            modelx = new DefaultTableModel(null,campos);
            try{
                Statement st =(Statement)cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    registros[0]= rs.getString("clave_marca");
                    registros[1]= rs.getString("nombre");
                    registros[2]= rs.getString("tipo");
                    modelx.addRow(registros);
                }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "ex");
            }
            //creamos la tabla
            JTable t2 = new JTable(modelx);
            JScrollPane scp2 = new JScrollPane(t2); 
            scp2.setBounds(80,140,400,200);
            //inicilizamos los componentes
            JButton closeButton = new JButton("Salir de opcion");
            closeButton.setBounds(300, 50, 120,30);
            JLabel titulo = new JLabel("Opcion Marcas");
            titulo.setBounds(10,20,150,50);
            JButton btnuevo = new JButton("Nuevo");
            btnuevo.setBounds(150,50,100,30);
            JButton btborrar = new JButton("Borrar");
            JButton btmodificar = new JButton("Modificar");
            btborrar.setBounds(300,350,100,30);
            btmodificar.setBounds(450,350,100,30);
            
            p2.add(scp2);
            p2.add(titulo);
            p2.add(closeButton);
            p2.add(btnuevo);
            p2.add(btmodificar);
            p2.add(btborrar);
            
            closeButton.addActionListener((ActionEvent e1) -> {
                // Cerrar el panel al hacer clic en el botón
                p2.setVisible(false);
            });
             btborrar.addActionListener((ActionEvent e1) -> {
                    // Obtener la fila seleccionada de la tabla
                    int filaSeleccionada = t2.getSelectedRow();
                    if (filaSeleccionada >= 0) {
                        // Obtener el valor de la clave de la fila seleccionada
                        String clave = modelx.getValueAt(filaSeleccionada, 0).toString();

                        // Eliminar la fila seleccionada de la tabla
                        modelx.removeRow(filaSeleccionada);

                        // Eliminar los datos de la fila seleccionada de la base de datos
                        String deleteQuery = "DELETE FROM proveedores WHERE clave_p = ?";
                        try {
                            PreparedStatement statement = cn.prepareStatement(deleteQuery);
                            statement.setString(1, clave);
                            statement.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Fila eliminada correctamente");
                            statement.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al eliminar la fila");
                        }
                    }
                });
            //este apartado es para que muestre un nuevo panel con su respectivo contenido
            btnuevo.addActionListener((ActionEvent e1) -> {
                p2.setVisible(false);
                JPanel P1 = new JPanel(null);
                JPanel P2 = new JPanel(null);
                P1.setBounds(20,40,600,450);
                P1.setBackground(Color.GRAY);
                P2.setSize(500,300);
                P2.setLocation(25, 65);
                // creamos los componentes
                JLabel jltitulo = new JLabel("Opcion Ventas-Nuevo registro");
                JLabel jlclave = new JLabel("clave");
                JLabel jlnombre = new JLabel("Nombre");
                JLabel jltipo = new JLabel("Tipo");
                JTextField txtclave = new JTextField(40);
                JTextField txtnombre = new JTextField(40);
                // Crear un arreglo de opciones para el JComboBox
                String[] opciones = {"Opción 1",""};
                // Crear un JComboBox y pasarle las opciones
                JComboBox<String> comboBox = new JComboBox<>(opciones);
                // establecemos las cordenadas y las dimensiones de los componentes
                jltitulo.setBounds(5,5,200,20);
                jlclave.setBounds(129, 40, 100, 20);
                jlnombre.setBounds(116, 80, 100, 20);
                jltipo.setBounds(120, 120, 100, 20);
                txtclave.setBounds(170,40,80,20);
                txtnombre.setBounds(170,80,200,20);
                comboBox.setBounds(170,120,200,20);
                //agregamos los componentes al panel
                P2.add(jlclave);
                P2.add(jltipo);
                P2.add(jlnombre);
                P2.add(txtclave);
                P2.add(txtnombre);
                P2.add(comboBox);
                P1.add(jltitulo);
                JButton closeButton1 = new JButton("Regresar");
                JButton Guardar = new JButton("Guardar");
                closeButton1.setBounds(350, 400, 100, 30);
                Guardar.setBounds(200,400, 100,30);
                // Crear un arreglo de opciones para el JComboBox
                String[] opciones1 = {"N", "E"};
                // Crear un JComboBox y pasarle las opciones
                JComboBox<String> Tipo = new JComboBox<>(opciones1);
                P1.add(closeButton1);
                P1.add(Guardar);
                closeButton1.addActionListener((ActionEvent e2) -> {
                    // Cerrar el panel al hacer clic en el botón
                    P1.setVisible(false);
                    P2.setVisible(false);
                    p2.setVisible(true);
                });
                P1.add(P2);
                add(P1,P2);
                P2.setVisible(true);
                P1.setVisible(true);
                Guardar.addActionListener((ActionEvent e3)->{
                            // Obtener los nuevos valores ingresados en los campos de texto
                        String nuevaClave = txtclave.getText();
                        String nuevoNombre = txtnombre.getText();
                        String nuevoTipo= Tipo.getSelectedItem().toString();

                        // Agregar una nueva fila al modelo de la tabla
                        Object[] nuevaFila = {nuevaClave, nuevoNombre, nuevoTipo};
                        modelx.addRow(nuevaFila);

                        // Insertar los datos de la nueva fila en la base de datos
                        String insertQuery = "INSERT INTO marcas (clave_marca, nombre, tipo) VALUES (?, ?, ?)";
                        try {
                            PreparedStatement statement = cn.prepareStatement(insertQuery);
                            statement.setString(1, nuevaClave);
                            statement.setString(2, nuevoNombre);
                            statement.setString(3, nuevoTipo);
                            statement.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Nueva fila agregada correctamente");
                            statement.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al agregar la nueva fila");
                        }                    
                    });

            });
            //este apartado es para que muestre un nuevo panel con su respectivo contenido
            btmodificar.addActionListener((ActionEvent e1) -> {
                //occultamos el panel principal
                p2.setVisible(false);
                JPanel P1 = new JPanel(null);
                JPanel P2 = new JPanel(null);
                P1.setBounds(20,40,600,450);
                P1.setBackground(Color.GRAY);
                P2.setSize(500,300);
                P2.setLocation(25, 65);
                // creamos los componentes
                JLabel jltitulo = new JLabel("Opcion Marcas -Nuevo registro");
                JLabel jlclave = new JLabel("clave");
                JLabel jlnombre = new JLabel("Nombre");
                JLabel jltipo = new JLabel("Tipo");
                JTextField txtclave = new JTextField(40);
                JTextField txtnombre = new JTextField(40);
                // Crear un arreglo de opciones para el JComboBox
                String[] opciones = {"N", "E"};
                // Crear un JComboBox y pasarle las opciones
                JComboBox<String> Tipo = new JComboBox<>(opciones);
                // establecemos las cordenadas y las dimensiones de los componentes
                jltitulo.setBounds(5,5,200,20);
                jlclave.setBounds(129, 40, 100, 20);
                jlnombre.setBounds(116, 80, 100, 20);
                jltipo.setBounds(120, 120, 100, 20);
                txtclave.setBounds(170,40,80,20);
                txtnombre.setBounds(170,80,200,20);
                Tipo.setBounds(170,120,200,20);
                //agregamos los componentes al panel
                P2.add(jlclave);
                P2.add(jltipo);
                P2.add(jlnombre);
                P2.add(txtclave);
                P2.add(txtnombre);
                P2.add(Tipo);
                P1.add(jltitulo);
                JButton closeButton1 = new JButton("Regresar");
                JButton Guardar = new JButton("Guardar");
                closeButton1.setBounds(350, 400, 100, 30);
                Guardar.setBounds(200,400, 100,30);
                P1.add(closeButton1);
                P1.add(Guardar);
                // Obtener los datos de la fila seleccionada de la tabla
                    int filaSeleccionada = t2.getSelectedRow();
                    if (filaSeleccionada >= 0) {
                        //obtenemos los atributos respectivos
                        String clave_m = modelx.getValueAt(filaSeleccionada, 0).toString();
                        String nombre = modelx.getValueAt(filaSeleccionada, 1).toString();
                        String tipo = modelx.getValueAt(filaSeleccionada, 2).toString();

                        // Establecer los valores de la fila seleccionada en los campos de texto
                        txtclave.setText(clave_m);
                        txtnombre.setText(nombre);
                        Tipo.setActionCommand(tipo);
                        
                        
                        Guardar.addActionListener((ActionEvent x) -> {
                            // Obtener los nuevos valores ingresados en los campos de texto
                            String nuevaNombre = txtnombre.getText();
                            String nuevoTipo= Tipo.getSelectedItem().toString();
                            // Actualizar los datos en la base de datos
                            String updateQuery = "UPDATE marcas SET  nombre = ?, tipo = ?  WHERE clave_marca = ?";
                            try {
                                PreparedStatement statement = cn.prepareStatement(updateQuery);                               
                                statement.setString(1, nuevaNombre);
                                statement.setString(2, nuevoTipo);
                                statement.setString(3, clave_m);
                                statement.executeUpdate();
                                JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
                                statement.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Error al actualizar los datos");
                            }

                            // Cerrar el panel al hacer clic en el botón
                            P1.setVisible(false);
                            P2.setVisible(false);
                            p2.setVisible(true);
                                });
                    } else {
                        JOptionPane.showMessageDialog(null, "Seleccione una fila para modificar");
                    }
                closeButton1.addActionListener((ActionEvent e2) -> {
                    // Cerrar el panel al hacer clic en el botón
                    P1.setVisible(false);
                    P2.setVisible(false);
                    p2.setVisible(true);
                });
                //ocultamos los subpaneles 
                P1.add(P2);
                add(P1,P2);
                P2.setVisible(true);
                P1.setVisible(true);
            });
            add(p2);
        });
    }
    public void Tipos(JMenuItem tipos){
        //agregamos los compomnetes a la ventana
        tipos.addActionListener((ActionEvent e) -> {
        JPanel p3 = new JPanel();
        p3.setLayout(null);
        p3.setSize(600, 400);
        p3.setLocation(20, 20);
        //hacemos la conexion con la base de datos
        String[] campos={"Clave","Nombre"};
        String[] registros= new String[2];
        String sql="SELECT * FROM tipos_calzado";
        modelx = new DefaultTableModel(null,campos);
        try{
            Statement st =(Statement)cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                registros[0]= rs.getString("clave_tc");
                registros[1]= rs.getString("nombre");
                modelx.addRow(registros);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ex");
        }
        //inicilizamos los componentes
        JTable t3 = new JTable(modelx);
        JScrollPane scp3 = new JScrollPane(t3); 
        scp3.setBounds(80,140,400,200);
        JButton closeButton = new JButton("Salir de opcion");
        closeButton.setBounds(300, 50, 120,30);
        JLabel titulo = new JLabel("Opcion Tipos");
        titulo.setBounds(10,20,150,50);
        JButton btnuevo = new JButton("Nuevo");
        btnuevo.setBounds(150,50,100,30);
        JButton btborrar = new JButton("Borrar");
        JButton btmodificar = new JButton("Modificar");
        btborrar.setBounds(300,350,100,30);
        btmodificar.setBounds(450,350,100,30);
        p3.add(titulo);  
        p3.add(closeButton);
        p3.add(btnuevo);
        p3.add(btmodificar);
        p3.add(btborrar);
        p3.add(scp3);
        //boton para cerrar el panel
        closeButton.addActionListener((ActionEvent e1) -> {
            // Cerrar el panel al hacer clic en el botón
            p3.setVisible(false);
        });
        //en este apartado agregamos el boton de borrar
        btborrar.addActionListener((ActionEvent e1) -> {
            // Obtener la fila seleccionada de la tabla
            int filaSeleccionada = t3.getSelectedRow();
            if (filaSeleccionada >= 0) {
                // Obtener el valor de la clave de la fila seleccionada
                String clave = modelx.getValueAt(filaSeleccionada, 0).toString();

                // Eliminar la fila seleccionada de la tabla
                modelx.removeRow(filaSeleccionada);

                // Eliminar los datos de la fila seleccionada de la base de datos
                String deleteQuery = "DELETE FROM proveedores WHERE clave_p = ?";
                try {
                    PreparedStatement statement = cn.prepareStatement(deleteQuery);
                    statement.setString(1, clave);
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Fila eliminada correctamente");
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al eliminar la fila");
                }
            }
        });
        //este apartado es para que muestre un nuevo panel con su respectivo contenido
        btnuevo.addActionListener((ActionEvent e1) -> {
            //ocultamos el panel actual
            p3.setVisible(false);
            //creamos los nuevos paneles
            JPanel P1 = new JPanel(null);
            JPanel P2 = new JPanel(null);
            //los  configurmaos
            P1.setBounds(20,40,600,450);
            P1.setBackground(Color.GRAY);
            P2.setSize(500,300);
            P2.setLocation(25, 65);
            // creamos los componentes
            JLabel jltitulo = new JLabel("Opcion Ventas-Nuevo registro");
            JLabel jlclave = new JLabel("clave");
            JLabel jlnombre = new JLabel("Nombre");
            JTextField txtclave = new JTextField(40);
            JTextField txtnombre = new JTextField(40);
            // establecemos las cordenadas y las dimensiones de los componentes
            jltitulo.setBounds(5,5,200,20);
            jlclave.setBounds(129, 40, 100, 20);
            jlnombre.setBounds(116, 80, 100, 20);
            txtclave.setBounds(163,40,80,20);
            txtnombre.setBounds(163,80,200,20);
            //agregamos los componentes al panel
            P2.add(jlclave);
            P2.add(jlnombre);
            P2.add(txtclave);
            P2.add(txtnombre);
            P1.add(jltitulo);
            JButton closeButton1 = new JButton("Regresar");
            closeButton1.setBounds(350, 400, 100, 30);
            JButton Guardar = new JButton("Guardar");
            Guardar.setBounds(200,400, 100,30);
            P1.add(closeButton1);
            P1.add(Guardar);
            Guardar.addActionListener((ActionEvent e3)->{
                    // Obtener los nuevos valores ingresados en los campos de texto
                String nuevaClave = txtclave.getText();
                String nuevoNombre = txtnombre.getText();
                //String nuevoTipo= Tipo.getSelectedItem().toString();

                // Agregar una nueva fila al modelo de la tabla
                Object[] nuevaFila = {nuevaClave, nuevoNombre};
                modelx.addRow(nuevaFila);

                // Insertar los datos de la nueva fila en la base de datos
                String insertQuery = "INSERT INTO tipos_calzado (clave_tc, nombre) VALUES (?, ?)";
                try {
                    PreparedStatement statement = cn.prepareStatement(insertQuery);
                    statement.setString(1, nuevaClave);
                    statement.setString(2, nuevoNombre);
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Nueva fila agregada correctamente");
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al agregar la nueva fila");
                }                    
            });
            closeButton1.addActionListener((ActionEvent e2) -> {
            // Cerrar el panel al hacer clic en el botón
            P1.setVisible(false);
            P2.setVisible(false);
            p3.setVisible(true);
            });
                P1.add(P2);
                add(P1,P2);
                P2.setVisible(true);
                P1.setVisible(true);
            });
            //este apartado es para que muestre un nuevo panel con su respectivo contenido
            btmodificar.addActionListener((ActionEvent e1) -> {
            //ocultamos el panel actual
            p3.setVisible(false);
            //creamos los nuevos paneles
            JPanel P1 = new JPanel(null);
            JPanel P2 = new JPanel(null);
            //los  configurmaos
            P1.setBounds(20,40,600,450);
            P2.setSize(500,300);
            P2.setLocation(25, 65);
            // creamos los componentes
            JLabel jltitulo = new JLabel("Opcion Ventas-Nuevo registro");
            JLabel jlclave = new JLabel("clave");
            JLabel jlnombre = new JLabel("Nombre");
            JTextField txtclave = new JTextField(40);
            JTextField txtnombre = new JTextField(40);
            // establecemos las cordenadas y las dimensiones de los componentes
            jltitulo.setBounds(5,5,200,20);
            jlclave.setBounds(129, 40, 100, 20);
            jlnombre.setBounds(116, 80, 100, 20);
            txtclave.setBounds(163,40,80,20);
            txtnombre.setBounds(163,80,200,20);
            //agregamos los componentes al panel
            
            P2.add(jlclave);
            P2.add(jlnombre);
            P2.add(txtclave);
            P2.add(txtnombre);
            P1.add(jltitulo);
            JButton closeButton1 = new JButton("Regresar");
            closeButton1.setBounds(350, 400, 100, 30);
            JButton Guardar = new JButton("Guardar");
            Guardar.setBounds(200,400, 100,30);
            P1.add(closeButton1);
            P1.add(Guardar);
            // Obtener los datos de la fila seleccionada de la tabla
            int filaSeleccionada = t3.getSelectedRow();
            if (filaSeleccionada >= 0) {
                //obtenemos los atributos respectivos
                String clave = modelx.getValueAt(filaSeleccionada, 0).toString();
                String nombre = modelx.getValueAt(filaSeleccionada, 1).toString();
                // Establecer los valores de la fila seleccionada en los campos de texto
                txtclave.setText(clave);
                txtnombre.setText(nombre);
                
                Guardar.addActionListener((ActionEvent x) -> {
                    // Obtener los nuevos valores ingresados en los campos de texto
                    String nuevoNombre = txtnombre.getText();
                    //String nuevaclave = txtnombre.getText();

                    // Actualizar los datos en la base de datos
                    String updateQuery = "UPDATE tipos_calzado SET nombre = ? WHERE clave_tc = ?";
                    try {
                        PreparedStatement statement = cn.prepareStatement(updateQuery);
                        statement.setString(1, nuevoNombre);
                        statement.setString(2, clave);
                        statement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
                        statement.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al actualizar los datos");
                    }

                    // Cerrar el panel al hacer clic en el botón
                    P1.setVisible(false);
                    P2.setVisible(false);
                    p3.setVisible(true);
                        });
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para modificar");
            }
            closeButton1.addActionListener((ActionEvent e2) -> {
            // Cerrar el panel al hacer clic en el botón
            P1.setVisible(false);
            P2.setVisible(false);
            p3.setVisible(true);
            });
            //ocultamos los subpaneles
            P1.add(P2);
            add(P1,P2);
                P2.setVisible(true);
                P1.setVisible(true);
            });
            add(p3);
            });
        }
    public void Inventario(JMenuItem inventario){
        //agregamos los compomnetes a la ventana
        inventario.addActionListener((ActionEvent e) -> {
            //creamos el panel y sus componentes
            JPanel p4 = new JPanel();
            p4.setSize(700, 600);
            p4.setLocation(20, 20);
            //conexion de la base de datos
            
            String[] campos={"clave","Medida","Cantidad","Estatus"};
            String[] registros= new String[4];
            String sql="SELECT * FROM inventario";
            modelx = new DefaultTableModel(null,campos);
            try{
                Statement st =(Statement)cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    registros[0]= rs.getString("clave_m");
                    registros[1]= rs.getString("medida");
                    registros[2]= rs.getString("cantidad");
                    registros[3]= rs.getString("estatus");
                    modelx.addRow(registros);
                }
                
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "ex");
            }
            JTable t4 = new JTable();
            t4.setModel(modelx);
            JScrollPane scp4 = new JScrollPane(t4);
            t4.setBounds(80, 140, 300, 200);
            //inicilizamos los componentes
            JButton closeButton = new JButton("Salir de opcion");
            JLabel titulo = new JLabel("Opcion Inventario");
            JButton btnuevo = new JButton("Nuevo");
            JButton btborrar = new JButton("Borrar");
            JButton btmodificar = new JButton("Modificar");
            
            titulo.setBounds(10,20,150,50);
            btnuevo.setBounds(360,50,100,30);
            btborrar.setBounds(200,350,100,30);
            btmodificar.setBounds(350,350,100,30);
            closeButton.setBounds(468, 50, 120,30);
            p4.add(scp4);  
            p4.add(titulo);  
            p4.add(closeButton);
            p4.add(btnuevo);
            p4.add(btmodificar);
            p4.add(btborrar);
            
            //p1.add(closeButton, BorderLayout.SOUTH);
            closeButton.addActionListener((ActionEvent e1) -> {
                // Cerrar el panel al hacer clic en el botón
                p4.setVisible(false);
            });
            //en este apartado agregamos el boton de borrar
            btborrar.addActionListener((ActionEvent e1) -> {
                // Obtener la fila seleccionada de la tabla
                int filaSeleccionada = t4.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    // Obtener el valor de la clave de la fila seleccionada
                    String clave = modelx.getValueAt(filaSeleccionada, 0).toString();

                    // Eliminar la fila seleccionada de la tabla
                    modelx.removeRow(filaSeleccionada);

                    // Eliminar los datos de la fila seleccionada de la base de datos
                    String deleteQuery = "DELETE FROM proveedores WHERE clave_p = ?";
                    try {
                        PreparedStatement statement = cn.prepareStatement(deleteQuery);
                        statement.setString(1, clave);
                        statement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Fila eliminada correctamente");
                        statement.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al eliminar la fila");
                    }
                }
            });
            //este apartado es para que muestre un nuevo panel con su respectivo contenido
            btnuevo.addActionListener((ActionEvent e1) -> {
                //ocultamos el panel actual
                p4.setVisible(false);
                //creamos los nuevos paneles
                JPanel P1 = new JPanel(null);
                JPanel P2 = new JPanel(null);
                P1.setBounds(20,40,600,450);
                P2.setSize(500,300);
                P2.setLocation(25, 65);
                // creamos los componentes
                JLabel jltitulo = new JLabel("Opcion Ventas-Nuevo registro");
                JLabel jlclave = new JLabel("clave");
                JLabel jlnombre = new JLabel("Nombre");
                JLabel jltipo = new JLabel("tipo");
                JLabel jlmarca = new JLabel("Marca");
                JLabel jlmedida = new JLabel("Medida");
                JLabel jlcantidad = new JLabel("Cantidad ");
                JLabel jlprecio= new JLabel("Precio");
                JLabel jlfecha= new JLabel("Fecha de ingreso");
                JTextField txtclave = new JTextField(40);
                JTextField txtnombre = new JTextField(40);
                JTextField txtmedida = new JTextField(40);
                JTextField txtcantidad = new JTextField(40);
                JTextField txtprecio= new JTextField(40);
                JTextField txtfecha= new JTextField(40);
                // Crear un arreglo de opciones para el JComboBox
                String[] opciones = {"Opción 1", "Opción 2", "Opción 3"};
                // Crear un JComboBox y pasarle las opciones
                JComboBox<String> tipos1 = new JComboBox<>(opciones);
                // establecemos las cordenadas y las dimensiones de los componentes
                // Crear un arreglo de opciones para el JComboBox
                String[] opciones2 = {"Opción 1", "Opción 2", "Opción 3"};
                // Crear un JComboBox y pasarle las opciones
                JComboBox<String> marcas1 = new JComboBox<>(opciones);
                // establecemos las cordenadas y las dimensiones de los componentes
                jltitulo.setBounds(5,5,200,20);
                jlclave.setBounds(129, 40, 100, 20);
                jlnombre.setBounds(116, 70, 100, 20);
                jltipo.setBounds(135, 100, 100, 20);
                jlmarca.setBounds(125, 130, 100, 20);
                jlmedida.setBounds(119, 160, 100, 20);
                jlcantidad.setBounds(120, 190, 150, 20);
                jlprecio.setBounds(121, 220, 150, 20);
                jlfecha.setBounds(60, 250, 150, 20);
                txtclave.setBounds(163,40,80,20);
                txtnombre.setBounds(163,70,200,20);
                tipos1.setBounds(163, 100, 200, 20);
                marcas1.setBounds(163, 130, 80, 20);
                txtmedida.setBounds(163,160,200,20);
                txtcantidad.setBounds(163,190,80 ,20);
                txtprecio.setBounds(163,220,80 ,20);
                txtfecha.setBounds(163,250,80 ,20);
                //agregamos los componentes al panel
                P2.add(jlclave);
                P2.add(jlnombre);
                P2.add(jltipo);
                P2.add(jlmarca);
                P2.add(jlprecio);
                P2.add(jlmedida);
                P2.add(jlcantidad);
                P2.add(jlfecha);
                P2.add(txtclave);
                P2.add(txtnombre);
                P2.add(tipos1);
                P2.add(marcas1);
                P2.add(txtfecha);
                P2.add(txtprecio);
                P2.add(txtcantidad);
                P2.add(txtmedida);
                P1.add(jltitulo);
                JButton closeButton1 = new JButton("Regresar");
                closeButton1.setBounds(350, 400, 100, 30);
                JButton Guardar = new JButton("Guardar");
                Guardar.setBounds(200,400, 100,30);
                P1.add(closeButton1);
                P1.add(Guardar);
                Guardar.addActionListener((ActionEvent e3)->{
                    // Obtener los nuevos valores ingresados en los campos de texto
                    String nuevaClave = txtclave.getText();
                    String nuevaMedida = txtnombre.getText();
                    String nuevaCantidad = txtnombre.getText();
                    String nuevoEstatus = txtnombre.getText();

                    // Agregar una nueva fila al modelo de la tabla
                    Object[] nuevaFila = {nuevaClave, nuevaMedida,nuevaCantidad,nuevoEstatus};
                    modelx.addRow(nuevaFila);

                    // Insertar los datos de la nueva fila en la base de datos
                    String insertQuery = "INSERT INTO marcas (clave_m, medida,cantidad,estatus) VALUES (?, ?, ?,?)";
                    try {
                        PreparedStatement statement = cn.prepareStatement(insertQuery);
                        statement.setString(1, nuevaClave);
                        //statement.setString(2, nuevoNombre);
                       // statement.setString(3, nuevoTipo);
                        statement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Nueva fila agregada correctamente");
                        statement.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al agregar la nueva fila");
                    }                    
                });
                closeButton1.addActionListener((ActionEvent e2) -> {
                    // Cerrar el panel al hacer clic en el botón
                    P1.setVisible(false);
                    P2.setVisible(false);
                    p4.setVisible(true);
                });
                P1.add(P2);
                add(P1,P2);
                P2.setVisible(true);
                P1.setVisible(true);
            });
            //este apartado es para que muestre un nuevo panel con su respectivo contenido
            btmodificar.addActionListener((ActionEvent e1) -> {
                //ocultamos el panel actual
                p4.setVisible(false);
                //creamos los nuevos paneles
                JPanel P1 = new JPanel(null);
                JPanel P2 = new JPanel(null);
                P1.setBounds(20,40,600,450);
                P2.setSize(500,300);
                P2.setLocation(25, 65);
                // creamos los componentes
                JLabel jltitulo = new JLabel("Opcion Ventas-Nuevo registro");
                JLabel jlclave = new JLabel("clave");
                JLabel jlnombre = new JLabel("Nombre");
                JLabel jltipo = new JLabel("tipo");
                JLabel jlmarca = new JLabel("Marca");
                JLabel jlmedida = new JLabel("Medida");
                JLabel jlcantidad = new JLabel("Cantidad ");
                JLabel jlprecio= new JLabel("Precio");
                JLabel jlfecha= new JLabel("Fecha de ingreso");
                JTextField txtclave = new JTextField(40);
                JTextField txtnombre = new JTextField(40);
                JTextField txtmedida = new JTextField(40);
                JTextField txtcantidad = new JTextField(40);
                JTextField txtprecio= new JTextField(40);
                JTextField txtfecha= new JTextField(40);
                // Crear un arreglo de opciones para el JComboBox
                String[] opciones = {"Opción 1", "Opción 2", "Opción 3"};
                // Crear un JComboBox y pasarle las opciones
                JComboBox<String> tipos1 = new JComboBox<>(opciones);
                // establecemos las cordenadas y las dimensiones de los componentes
                // Crear un arreglo de opciones para el JComboBox
                String[] opciones2 = {"Opción 1", "Opción 2", "Opción 3"};
                // Crear un JComboBox y pasarle las opciones
                JComboBox<String> marcas1 = new JComboBox<>(opciones);
                // establecemos las cordenadas y las dimensiones de los componentes
                jltitulo.setBounds(5,5,200,20);
                jlclave.setBounds(129, 40, 100, 20);
                jlnombre.setBounds(116, 70, 100, 20);
                jltipo.setBounds(135, 100, 100, 20);
                jlmarca.setBounds(125, 130, 100, 20);
                jlmedida.setBounds(119, 160, 100, 20);
                jlcantidad.setBounds(120, 190, 150, 20);
                jlprecio.setBounds(121, 220, 150, 20);
                jlfecha.setBounds(60, 250, 150, 20);
                txtclave.setBounds(163,40,80,20);
                txtnombre.setBounds(163,70,200,20);
                tipos1.setBounds(163, 100, 200, 20);
                marcas1.setBounds(163, 130, 80, 20);
                txtmedida.setBounds(163,160,200,20);
                txtcantidad.setBounds(163,190,80 ,20);
                txtprecio.setBounds(163,220,80 ,20);
                txtfecha.setBounds(163,250,80 ,20);
                //agregamos los componentes al panel
                P2.add(jlclave);
                P2.add(jlnombre);
                P2.add(jltipo);
                P2.add(jlmarca);
                P2.add(jlprecio);
                P2.add(jlmedida);
                P2.add(jlcantidad);
                P2.add(jlfecha);
                P2.add(txtclave);
                P2.add(txtnombre);
                P2.add(tipos1);
                P2.add(marcas1);
                P2.add(txtfecha);
                P2.add(txtprecio);
                P2.add(txtcantidad);
                P2.add(txtmedida);
                P1.add(jltitulo);
                JButton closeButton1 = new JButton("Regresar");
                closeButton1.setBounds(350, 400, 100, 30);
                JButton Guardar = new JButton("Guardar");
                Guardar.setBounds(200,400, 100,30);
                P1.add(closeButton1);
                P1.add(Guardar);
                closeButton1.addActionListener((ActionEvent e2) -> {
                    // Cerrar el panel al hacer clic en el botón
                    P1.setVisible(false);
                    P2.setVisible(false);
                    p4.setVisible(true);
                });
                P1.add(P2);
                add(P1,P2);
                P2.setVisible(true);
                P1.setVisible(true);
            });
            add(p4);
        });
    }
    public void Ventas(JMenuItem ventas){
        //agregamos los compomnetes a la ventana
        ventas.addActionListener((ActionEvent e) -> {
            JPanel p5 = new JPanel();
            p5.setLayout(null);
            p5.setSize(600, 400);
            p5.setLocation(20, 20);
            //hacemos la conexion y carga de datos de la base de datos
            String[] campos={"Clave de Venta","Precio Total","Fecha de venta"};
            String[] registros= new String[3];
            String sql="SELECT * FROM ventas";
            modelx = new DefaultTableModel(null,campos);
            try{
                Statement st =(Statement)cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    registros[0]= rs.getString("clave_v");
                    registros[1]= rs.getString("precio_s_i");
                    registros[2]= rs.getString("fecha_venta");
                    modelx.addRow(registros);
                }    
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "ex");
            }
            //inicilizamos los componentes
            JTable t5 = new JTable(modelx);
            JScrollPane scp5 = new JScrollPane(t5); 
            scp5.setBounds(80,140,400,200);
            JButton btDetalleVenta = new JButton("Detalle de Venta");
            JButton closeButton = new JButton("Salir de opcion");
            JLabel titulo = new JLabel("Opcion Ventas");
            JButton btnuevo = new JButton("Nuevo");
            JButton btborrar = new JButton("Borrar");
            JButton btmodificar = new JButton("Modificar");
            btDetalleVenta.setBounds(290,50,130,30);
            btnuevo.setBounds(180,50,100,30);
            titulo.setBounds(10,20,150,50);
            closeButton.setBounds(430, 50, 120,30);
            btborrar.setBounds(300,350,100,30);
            btmodificar.setBounds(450,350,100,30);
            p5.add(titulo);  p5.add(closeButton);
            p5.add(btnuevo);p5.add(btmodificar);
            p5.add(btborrar);p5.add(btDetalleVenta);
            p5.add(scp5);
            closeButton.addActionListener((ActionEvent e1) -> {
                // Cerrar el panel al hacer clic en el botón
                p5.setVisible(false);
            });
            //en este apartado agregamos el boton de borrar
            btborrar.addActionListener((ActionEvent e1) -> {
                // Obtener la fila seleccionada de la tabla
                int filaSeleccionada = t5.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    // Obtener el valor de la clave de la fila seleccionada
                    String clave = modelx.getValueAt(filaSeleccionada, 0).toString();

                    // Eliminar la fila seleccionada de la tabla
                    modelx.removeRow(filaSeleccionada);

                    // Eliminar los datos de la fila seleccionada de la base de datos
                    String deleteQuery = "DELETE FROM ventas WHERE clave_v = ?";
                    try {
                        PreparedStatement statement = cn.prepareStatement(deleteQuery);
                        statement.setString(1, clave);
                        statement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Fila eliminada correctamente");
                        statement.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al eliminar la fila");
                    }
                }
            });
            //este apartado es para que muestre un nuevo panel con su respectivo contenido
            btnuevo.addActionListener((ActionEvent e1) -> {
                //ocultamos el panel principal
                p5.setVisible(false);
                //creamos los nuevos paneles que se mostraran
                JPanel P1 = new JPanel(null);
                JPanel P2 = new JPanel(null);
                //configuramos los paneles
                P1.setBounds(20,40,600,450);
                P2.setSize(500,300);
                P2.setLocation(25, 65);
                P2.setBackground(Color.ORANGE);
                // creamos los componentes
                JLabel jltitulo = new JLabel("Opcion Ventas-Nuevo registro");
                JLabel jlclave = new JLabel("clave");
                JLabel jlprecio = new JLabel("Precio total");
                JLabel jlfecha = new JLabel("Fecha de venta");
                JTextField txtclave = new JTextField(40);
                JTextField txtprecio = new JTextField(40);
                JTextField txtfecha = new JTextField(40);
                // Crear un arreglo de opciones para el JComboBox
                String[] opciones = {"Opción 1", "Opción 2", "Opción 3"};
                // Crear un JComboBox y le pasamos por parametro  las opciones
                JComboBox<String> articulos = new JComboBox<>(opciones);
                // establecemos las cordenadas y las dimensiones de los componentes
                jltitulo.setBounds(5,5,200,20);
                jlclave.setBounds(129, 40, 100, 20);
                jlprecio.setBounds(116, 80, 100, 20);
                jlfecha.setBounds(110, 130, 100, 20);
                txtclave.setBounds(163, 40, 100, 20);
                txtprecio.setBounds(163,80,80,20);
                txtfecha.setBounds(163,130,80,20);
                //agregamos los componentes al panel
                P2.add(jlclave);
                P2.add(jlprecio);
                P2.add(jlfecha);
                P2.add(txtclave);
                P2.add(txtprecio);
                P2.add(txtfecha);
                P1.add(jltitulo);
                JButton closeButton1 = new JButton("Regresar");
                closeButton1.setBounds(350, 400, 100, 30);
                JButton Guardar = new JButton("Guardar");
                Guardar.setBounds(200,400, 100,30);
                P1.add(closeButton1);
                P1.add(Guardar);
                Guardar.addActionListener((ActionEvent e3)->{
                            // Obtener los nuevos valores ingresados en los campos de texto
                        String nuevaClave = txtclave.getText();
                        String nuevoPrecio = txtprecio.getText();
                        String nuevaFecha = txtfecha.getText();
               
          

                        // Agregar una nueva fila al modelo de la tabla
                        Object[] nuevaFila = {nuevaClave, nuevoPrecio,nuevaFecha};
                        modelx.addRow(nuevaFila);

                        // Insertar los datos de la nueva fila en la base de datos
                        String insertQuery = "INSERT INTO ventas (clave_v,precio_s_i,fecha_venta) VALUES (?, ?, ?)";
                        try {
                            PreparedStatement statement = cn.prepareStatement(insertQuery);
                            statement.setString(1, nuevaClave);
                            statement.setString(2, nuevoPrecio);
                            statement.setString(3, nuevaFecha);
                            statement.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Nueva fila agregada correctamente");
                            statement.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al agregar la nueva fila");
                        }                    
                    });
                closeButton1.addActionListener((ActionEvent e2) -> {
                    // Cerrar el panel al hacer clic en el botón
                    P1.setVisible(false);
                    P2.setVisible(false);
                    p5.setVisible(true);
                });
                P1.add(P2);
                add(P1,P2);
                P2.setVisible(true);
                P1.setVisible(true);
            });
            //este apartado es para que muestre un nuevo panel con su respectivo contenido
            btmodificar.addActionListener((ActionEvent e1) -> {
                //ocultamos el panel principal
                p5.setVisible(false);
                //creamos los nuevos paneles que se mostraran
                JPanel P1 = new JPanel(null);
                JPanel P2 = new JPanel(null);
                //configuramos los paneles
                P1.setBounds(20,40,600,450);
                P2.setSize(500,300);
                P2.setLocation(25, 65);
                // creamos los componentes
                JLabel jltitulo = new JLabel("Opcion Detalle de Ventas-Modificar registro");
                JLabel jlclave = new JLabel("clave");
                JLabel jlPrecio = new JLabel("Precio total");
                JLabel jlFecha = new JLabel("Fecha de Venta");
                JTextField txtclave = new JTextField(40);
                JTextField txtprecio = new JTextField(40);
                JTextField txtfecha = new JTextField(40);
                
                jltitulo.setBounds(5,5,200,20);
                jlclave.setBounds(129, 40, 100, 20);
                jlPrecio.setBounds(116, 80, 100, 20);
                jlFecha.setBounds(110, 130, 100, 20);
                
                txtclave.setBounds(163,40,80,20);
                txtprecio.setBounds(163,80,100,20);
                txtfecha.setBounds(163,130,100,20);
                //agregamos los componentes al panel
                P2.add(jlclave);
                P2.add(jlPrecio);
                P2.add(jlFecha);
                P2.add(txtclave);
                P2.add(txtprecio);
                P2.add(txtfecha);
                P1.add(jltitulo);
                JButton closeButton1 = new JButton("Regresar");
                closeButton1.setBounds(350, 400, 100, 30);
                JButton Guardar = new JButton("Guardar");
                Guardar.setBounds(200,400, 100,30);
                P1.add(closeButton1);
                P1.add(Guardar);
                // Obtener los datos de la fila seleccionada de la tabla
                int filaSeleccionada = t5.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    //obtenemos los atributos respectivos
                    String clave = modelx.getValueAt(filaSeleccionada, 0).toString();
                    String Precio = modelx.getValueAt(filaSeleccionada, 1).toString();
                    String Fecha_venta = modelx.getValueAt(filaSeleccionada, 2).toString();
                    // Establecer los valores de la fila seleccionada en los campos de texto
                    txtclave.setText(clave);
                    txtprecio.setText(Precio);
                    txtfecha.setText(Fecha_venta);

                    Guardar.addActionListener((ActionEvent x) -> {
                        // Obtener los nuevos valores ingresados en los campos de texto
                        String nuevoPrecio = txtprecio.getText();
                        String nuevoFecha = txtfecha.getText();
                        //String nuevaclave = txtnombre.getText();

                        // Actualizar los datos en la base de datos
                        String updateQuery = "UPDATE ventas SET precio_s_i = ?,fecha_venta WHERE clave_v = ?";
                        try {
                            PreparedStatement statement = cn.prepareStatement(updateQuery);
                            statement.setString(1, nuevoPrecio);
                            statement.setString(2, nuevoFecha);
                            statement.setString(3, clave);
                            statement.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
                            statement.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al actualizar los datos");
                        }

                        // Cerrar el panel al hacer clic en el botón
                        P1.setVisible(false);
                        P2.setVisible(false);
                        p5.setVisible(true);
                            });
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una fila para modificar");
                }
                closeButton1.addActionListener((ActionEvent e2) -> {
                    // Cerrar el panel al hacer clic en el botón
                    P1.setVisible(false);
                    P2.setVisible(false);
                    p5.setVisible(true);
                });
                P1.add(P2);
                add(P1,P2);
                P2.setVisible(true);
                P1.setVisible(true);
            });
            //este apartado es para que muestre un nuevo panel con su respectivo contenido
            btDetalleVenta.addActionListener((ActionEvent e1) -> {
                p5.setVisible(false);
                JPanel p6 = new JPanel();
                p6.setLayout(null);
                //hacemos la conexion y carga de datos de la base de datos
                String[] campos1={"Clave de Venta","Articulo","Cantidad","Subtotal"};
                String[] registros1= new String[4];
                String sql1="SELECT * FROM detalles_ventas";
                modelx = new DefaultTableModel(null,campos1);
                try{
                    Statement st =(Statement)cn.createStatement();
                    ResultSet rs = st.executeQuery(sql1);
                    while(rs.next()){
                        registros1[0]= rs.getString("clave_v");
                        registros1[1]= rs.getString("clave_m");
                        registros1[2]= rs.getString("cantidad");
                        registros1[3]= rs.getString("subtotal");
                        modelx.addRow(registros1);
                    }
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, "error papu");
                }
                //inicilizamos los componentes
                JTable t5_2 = new JTable(modelx);
                JScrollPane scp5_2 = new JScrollPane(t5); 
                scp5_2.setBounds(80,140,400,200);
                JButton closeButton1 = new JButton("Salir de opcion");
                JLabel titulo1 = new JLabel("Opcion Detalle de Ventas");
                JButton btnuevo1 = new JButton("Nuevo");
                JButton btborrar2 = new JButton("Borrar");
                JButton btmodificar1 = new JButton("Modificar");
                p6.setSize(600, 400);
                p6.setLocation(20, 20);
                btnuevo1.setBounds(180, 50, 100, 30);
                titulo1.setBounds(10, 20, 150, 50);
                closeButton1.setBounds(430, 50, 120, 30);
                btborrar2.setBounds(300, 350, 100, 30);
                btmodificar1.setBounds(450, 350, 100, 30);
                p6.add(titulo1);
                p6.add(closeButton1);
                p6.add(btnuevo1);
                p6.add(btmodificar1);
                p6.add(btborrar2);
                p6.add(scp5_2);
                btborrar2.addActionListener((ActionEvent e2) -> {
                // Obtener la fila seleccionada de la tabla
                int filaSeleccionada = t5.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    //Obtener el valor de la clave de la fila seleccionada
                    String clave = modelx.getValueAt(filaSeleccionada, 0).toString();

                    // Eliminar la fila seleccionada de la tabla
                    modelx.removeRow(filaSeleccionada);

                    // Eliminar los datos de la fila seleccionada de la base de datos
                    String deleteQuery = "DELETE FROM detalles_ventas WHERE clave_v = ?";
                    try {
                        PreparedStatement statement = cn.prepareStatement(deleteQuery);
                        statement.setString(1, clave);
                        statement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Fila eliminada correctamente");
                           statement.close();
                       } catch (SQLException ex) {
                           JOptionPane.showMessageDialog(null, "Error al eliminar la fila");
                       }
                   }
               });
                btnuevo1.addActionListener((ActionEvent e2) -> {
                    //ocultamos el panel principal
                    p5.setVisible(false);
                    p6.setVisible(false);
                    //creamos los nuevos paneles que se mostraran
                    JPanel P1 = new JPanel(null);
                    JPanel P2 = new JPanel(null);
                    //configuramos los paneles
                    P1.setBounds(20,40,600,450);
                    P1.setBackground(Color.GRAY);
                    P2.setSize(500,300);
                    P2.setLocation(25, 65);
                    P2.setBackground(Color.ORANGE);
                    // creamos los componentes
                    JLabel jltitulo = new JLabel("Opcion Ventas-Nuevo registro");
                    JLabel jlclave = new JLabel("clave");
                    JLabel jlarticulo = new JLabel("Articulo");
                    JLabel jlcantidad = new JLabel("Cantidad");
                    JTextField txtclave = new JTextField(40);
                    JTextField txtcantidad = new JTextField(40);
                   
                    // establecemos las cordenadas y las dimensiones de los componentes
                    jltitulo.setBounds(5,5,200,20);
                    jlclave.setBounds(129, 40, 100, 20);
                    jlarticulo.setBounds(116, 80, 100, 20);
                    jlcantidad.setBounds(110, 130, 100, 20);
                    
                    txtclave.setBounds(163,40,80,20);
                    txtcantidad.setBounds(163,130,100,20);
                    //agregamos los componentes al panel
                    P2.add(jlclave);
                    P2.add(jlclave);
                    P2.add(txtcantidad);
                    P2.add(txtclave);
                    P2.add(jlarticulo);
                    P2.add(jlcantidad);
                    P1.add(jltitulo);
                    JButton closeButton2 = new JButton("Regresar");
                    closeButton2.setBounds(350, 400, 100, 30);
                    JButton Guardar = new JButton("Guardar");
                    Guardar.setBounds(200,400, 100,30);
                    P1.add(closeButton2);
                    P1.add(Guardar);
                    Guardar.addActionListener((ActionEvent e3)->{
                            // Obtener los nuevos valores ingresados en los campos de texto
                        String nuevaClave = txtclave.getText();
                       // String nuevoPrecio = txtprecio.getText();
                       //String nuevaFecha = txtfecha.getText();

                        // Agregar una nueva fila al modelo de la tabla
                      //  Object[] nuevaFila = {nuevaClave, nuevoPrecio,nuevaFecha};
                        //modelx.addRow(nuevaFila);

                        // Insertar los datos de la nueva fila en la base de datos
                        String insertQuery = "INSERT INTO ventas (clave_v,precio_s_i,fecha_venta) VALUES (?, ?, ?)";
                        try {
                            PreparedStatement statement = cn.prepareStatement(insertQuery);
                            statement.setString(1, nuevaClave);
                          //  statement.setString(2, nuevoPrecio);
                           // statement.setString(3, nuevaFecha);
                            statement.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Nueva fila agregada correctamente");
                            statement.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al agregar la nueva fila");
                        }                    
                    });
                    closeButton2.addActionListener((ActionEvent e3) -> {
                        // Cerrar el panel al hacer clic en el botón
                        P1.setVisible(false);
                        P2.setVisible(false);
                        p6.setVisible(true);
                    });
                    P1.add(P2);
                    add(P1,P2);
                    P2.setVisible(true);
                    P1.setVisible(true);
                });
                closeButton1.addActionListener((ActionEvent e4) -> {
                    // Cerrar el panel al hacer clic en el botón
                    p6.setVisible(false);
                    p5.setVisible(true);
                });
                add(p6);
                p6.setVisible(true);
                btmodificar1.addActionListener((ActionEvent e2) -> {
                    
                    //ocultamos el panel principal
                    p5.setVisible(false);
                    //creamos los nuevos paneles que se mostraran
                    JPanel P1 = new JPanel(null);
                    JPanel P2 = new JPanel(null);
                    //configuramos los paneles
                    P1.setBounds(20,40,600,450);
                    P2.setSize(500,300);
                    P2.setLocation(25, 65);
                    P2.setBackground(Color.ORANGE);
                    // creamos los componentes
                    JLabel jltitulo = new JLabel("Opcion Detalle de Ventas-Modificar registro");
                    JLabel jlclave = new JLabel("clave");
                    JLabel jlarticulo = new JLabel("Articulo");
                    JLabel jlcantidad = new JLabel("Cantidad");

                    JTextField txtclave = new JTextField(40);
                    JTextField txtcantidad = new JTextField(40);
                     // Crear un arreglo de opciones para el JComboBox
                        int columnum= t5.getColumnCount();
                        String[] opciones =new String[columnum];
                        for(int i=0;i<columnum;i++){
                        opciones[i]=t5.getColumnName(columnum);
                        }

                        // Crear un JComboBox y le pasamos por parametro  las opciones
                        JComboBox<String> Articulos = new JComboBox<>(opciones);


                    // Obtener el valor seleccionado del JComboBox
                    String art = Articulos.getSelectedItem().toString();

                    jltitulo.setBounds(5,5,200,20);
                    jlclave.setBounds(110, 40, 100, 20);
                    jlarticulo.setBounds(110, 130, 100, 20);   
                    jlcantidad.setBounds(110, 190, 100, 20); 
                    Articulos.setBounds(116, 130, 100, 20);           
                    txtcantidad.setBounds(130,190,100,20);
                    txtclave.setBounds(130,40,80,20);
                    //agregamos los componentes al panel
                    P2.add(jlclave);
                    P2.add(jlarticulo);
                    P2.add(jlcantidad);
                    P2.add(txtclave);
                    P2.add(txtcantidad);
                    P2.add(Articulos);
                    P1.add(jltitulo);
                    JButton closeButton2 = new JButton("Regresar");
                    closeButton1.setBounds(350, 400, 100, 30);
                    JButton Guardar = new JButton("Guardar");
                    Guardar.setBounds(200,400, 100,30);
                    P1.add(closeButton1);
                    P1.add(Guardar);
                    // Obtener los datos de la fila seleccionada de la tabla
                    int filaSeleccionada = t5.getSelectedRow();
                    if (filaSeleccionada >= 0) {
                        //obtenemos los atributos respectivos
                        String clave = modelx.getValueAt(filaSeleccionada, 0).toString();
                        String  articulo= modelx.getValueAt(filaSeleccionada, 1).toString();
                        String cantidad = modelx.getValueAt(filaSeleccionada, 2).toString();
                        // Establecer los valores de la fila seleccionada en los campos de texto
                        txtclave.setText(clave);
                        txtcantidad.setText(cantidad);
                        Articulos.setActionCommand(articulo);
                        Guardar.addActionListener((ActionEvent x) -> {
                            // Obtener los nuevos valores ingresados en los campos de texto
                            String nuevocantidad = txtcantidad.getText();
                            String nuevoArticulo= Articulos.getSelectedItem().toString();
                            //String nuevaclave = txtnombre.getText();

                            // Actualizar los datos en la base de datos
                            String updateQuery = "UPDATE  detalles_ventas SET clave_m = ?,fecha_venta WHERE clave_v = ?";
                            try {
                                PreparedStatement statement = cn.prepareStatement(updateQuery);
                                statement.setString(1, nuevoArticulo);
                                statement.setString(2, nuevocantidad);
                                statement.setString(3, clave);
                                statement.executeUpdate();
                                JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
                                statement.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Error al actualizar los datos");
                            }

                            // Cerrar el panel al hacer clic en el botón
                            P1.setVisible(false);
                            P2.setVisible(false);
                            p5.setVisible(true);
                                });
                    } else {
                        JOptionPane.showMessageDialog(null, "Seleccione una fila para modificar");
                    }
                closeButton1.addActionListener((ActionEvent e3) -> {
                    // Cerrar el panel al hacer clic en el botón
                    P1.setVisible(false);
                    P2.setVisible(false);
                    p5.setVisible(true);
                });
                P1.add(P2);
                add(P1,P2);
                P2.setVisible(true);
                P1.setVisible(true);
            });

            });
            add(p5);
        });      
    }     
    public static void main(String[] args) {
        //inicializamos el objeto de la clase
        Practica_8 app = new Practica_8();
        //este bloque es para agregar una libreria para la mejora visualde del programa
        try {
            UIManager.setLookAndFeel(new McWinLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Practica_8.class.getName()).log(Level.SEVERE, null, ex);
        }
        //aqui hacemos la conexion a la base de datos con el metodo Conectar();
        try {
            app.Conectar();    
        } catch (SQLException ex) {
            Logger.getLogger(Practica_8.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void actionPerformed(ActionEvent click){}
}