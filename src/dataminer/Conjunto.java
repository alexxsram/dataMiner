package dataminer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.JOptionPane;
import java.io.File;
import java.util.regex.Pattern;
import java.util.Arrays;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.util.Formatter;

import org.jfree.chart.ChartFrame;

import code.DataSet;
import code.Attribute;
import code.MissingValue;
import code.RowColor;
import code.UtileriaExtra;
import javax.swing.table.TableColumnModel;


/**
 *
 * @author ALEJO SALGADO
 */
public class Conjunto extends javax.swing.JFrame {
    DataSet dataset; //donde cargamos el nombre del conjunto de datos
    Attribute attribute; //donde cargamos los datos necesarios de los atributos
    MissingValue missingvalue; //donde cargamos el valor faltante del conjunto
    UtileriaExtra utileria = new UtileriaExtra();
    
    ArrayList<Attribute> attributeList; //Lista de atributos
    ArrayList<ArrayList<String>> instanceList; //Lista de instancias
    ArrayList<String> dataList; //Arreglo para los datos
    
    ArrayList<ArrayList<String>> newInstanceList; //nueva lista de instancias para graficar
    ArrayList<String> nominalList; //
    ArrayList<Integer> numericList; //
    
    boolean abierto; //usada para definir que se abrió el dataset
    String rutaOriginal = ""; //usada para obtener la ruta de donde se abrió el archivo
    String auxLectura = "", auxEscritura = "";
    String comentarios = "", nombre = "";
    String[] atributos;
    String[] instancias;
    String valorFaltante = "";
    
    DefaultTableModel dtmAtributos = new DefaultTableModel();
    DefaultTableModel dtmInstancias = new DefaultTableModel();
    DefaultTableModel dtmErrores = new DefaultTableModel();
    
    DefaultTableModel dtmNumericos = new DefaultTableModel();
    
    public Conjunto() {
        initComponents();
        this.setLocationRelativeTo(Conjunto.this);
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblNombreConjunto = new javax.swing.JLabel();
        lblNumeroAtributos = new javax.swing.JLabel();
        lblNumeroInstancias = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        btnGuardarComoArchivo = new javax.swing.JButton();
        btnGuardarArchivo = new javax.swing.JButton();
        btnCargarArchivo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAtributos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblErrorAtributo = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblInstancias = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        lblPorcentajeFaltantes = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxFiltro = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        btnAgregarAtributo = new javax.swing.JButton();
        btnEliminarAtributos = new javax.swing.JButton();
        btnAgregarInstancia = new javax.swing.JButton();
        btnEliminarInstancias = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cbxAtributoUniv = new javax.swing.JComboBox<>();
        btnAnalisisUnivariable = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblNumericos = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cbxAtributoBiv1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbxAtributoBiv2 = new javax.swing.JComboBox<>();
        btnAnalisisBivariable = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Datos");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setText("Nombre:");

        jLabel2.setText("Núm. atributos:");

        jLabel3.setText("Núm. instancias:");

        lblNombreConjunto.setText("...");

        lblNumeroAtributos.setText("...");

        lblNumeroInstancias.setText("...");

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnGuardarComoArchivo.setText("Guardar como...");
        btnGuardarComoArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarComoArchivoActionPerformed(evt);
            }
        });

        btnGuardarArchivo.setText("Guardar");
        btnGuardarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarArchivoActionPerformed(evt);
            }
        });

        btnCargarArchivo.setText("Cargar archivo");
        btnCargarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarArchivoActionPerformed(evt);
            }
        });

        tblAtributos.setFont(new java.awt.Font("Candara", 0, 13)); // NOI18N
        tblAtributos.setModel(dtmAtributos);
        tblAtributos.setFocusable(false);
        tblAtributos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblAtributos);

        tblErrorAtributo = new javax.swing.JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblErrorAtributo.setFont(new java.awt.Font("Candara", 0, 13)); // NOI18N
        tblErrorAtributo.setModel(dtmErrores);
        tblErrorAtributo.setFocusable(false);
        tblErrorAtributo.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblErrorAtributo);

        tblInstancias.setFont(new java.awt.Font("Candara", 0, 13)); // NOI18N
        tblInstancias.setModel(dtmInstancias);
        tblInstancias.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblInstancias.setFocusable(false);
        tblInstancias.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblInstancias);

        jLabel4.setText("% de valores faltantes:");

        lblPorcentajeFaltantes.setText("...");

        jLabel5.setText("Buscar dato por:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnAgregarAtributo.setText("Agregar atributo");
        btnAgregarAtributo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarAtributoActionPerformed(evt);
            }
        });

        btnEliminarAtributos.setText("Eliminar atributos");
        btnEliminarAtributos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarAtributosActionPerformed(evt);
            }
        });

        btnAgregarInstancia.setText("Agregar instancia");
        btnAgregarInstancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarInstanciaActionPerformed(evt);
            }
        });

        btnEliminarInstancias.setText("Eliminar instancias");
        btnEliminarInstancias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarInstanciasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNombreConjunto))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNumeroAtributos))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNumeroInstancias))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAgregarAtributo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarAtributos)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPorcentajeFaltantes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 519, Short.MAX_VALUE)
                        .addComponent(btnCargarArchivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardarArchivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardarComoArchivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnAgregarInstancia)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnEliminarInstancias))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbxFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscar)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane3))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSalir)
                        .addComponent(btnGuardarComoArchivo)
                        .addComponent(btnGuardarArchivo)
                        .addComponent(btnCargarArchivo))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblNombreConjunto)
                            .addComponent(jLabel4)
                            .addComponent(lblPorcentajeFaltantes))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(lblNumeroAtributos))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(lblNumeroInstancias)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbxFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnBuscar)
                                .addComponent(jLabel5)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAgregarAtributo)
                            .addComponent(btnEliminarAtributos))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarInstancia)
                    .addComponent(btnEliminarInstancias))
                .addGap(13, 13, 13))
        );

        jTabbedPane1.addTab("Conjunto de datos", jPanel2);

        jLabel6.setText("Obtener análisis de:");

        btnAnalisisUnivariable.setText("Realizar análisis");
        btnAnalisisUnivariable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalisisUnivariableActionPerformed(evt);
            }
        });

        tblNumericos = new javax.swing.JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblNumericos.setFont(new java.awt.Font("Candara", 0, 13)); // NOI18N
        tblNumericos.setModel(dtmNumericos);
        tblNumericos.setFocusable(false);
        tblNumericos.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblNumericos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxAtributoUniv, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnalisisUnivariable)))
                .addContainerGap(838, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxAtributoUniv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAnalisisUnivariable))
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(438, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Análisis Univariable", jPanel3);

        jLabel7.setText("Obtener análisis de:");

        jLabel8.setText("contra:");

        btnAnalisisBivariable.setText("Realizar análisis");
        btnAnalisisBivariable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalisisBivariableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxAtributoBiv1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxAtributoBiv2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAnalisisBivariable)
                .addContainerGap(642, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxAtributoBiv2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAnalisisBivariable))
                    .addComponent(cbxAtributoBiv1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addContainerGap(566, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Análisis Bivariable", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
     
    public void cargarArchivo() {
        boolean cargarDatos = false;
        JFileChooser directorio = new JFileChooser();
        directorio.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter filtroArchivo = new FileNameExtensionFilter("*.arff", "arff");
        directorio.setFileFilter(filtroArchivo);
        int seleccionado = directorio.showOpenDialog(this);
        if(seleccionado == JFileChooser.APPROVE_OPTION) {
            abierto = true;
            comentarios = "";
            instancias = null;
            dataset = new DataSet();
            attributeList = new ArrayList<Attribute>();
            missingvalue = new MissingValue();
            instanceList = new ArrayList<ArrayList<String>>();
            try {
                rutaOriginal = directorio.getSelectedFile().getAbsolutePath();
                FileReader archivoAux = new FileReader(rutaOriginal);
                BufferedReader lector = new BufferedReader(archivoAux);
                while((auxLectura = lector.readLine()) != null) {
                    if(cargarDatos == true) {
                        auxLectura = auxLectura.replace(" ", "");
                        instancias = auxLectura.split(",");
                        dataList = new ArrayList<String>();
                        for(int i = 0; i < instancias.length; i++) {
                            dataList.add(instancias[i]);
                        }
                        instanceList.add(dataList);
                    }
                    else if(auxLectura.startsWith("%")) {
                        comentarios += auxLectura + "\n";
                        dataset.setComentarios(comentarios);                        
                    }
                    else if(auxLectura.startsWith("@relation")) {
                        nombre = auxLectura.substring(10);
                        dataset.setNombre(nombre);
                    }
                    else if(auxLectura.startsWith("@attribute")) {
                        atributos = auxLectura.split(" ");
                        if(atributos.length < 4) {
                            attribute = new Attribute(atributos[1], atributos[2], "");
                        }
                        else {
                            attribute = new Attribute(atributos[1], atributos[2], atributos[3]);
                        }
                        attributeList.add(attribute);
                    }
                    else if(auxLectura.startsWith("@missingValue")) {
                        valorFaltante = auxLectura.substring(13, 15);
                        missingvalue.setValorFaltante(valorFaltante);
                    }
                    else if(auxLectura.startsWith("@data")) {
                        cargarDatos = true;
                    }
                }
                lector.close();
            } catch(FileNotFoundException ex) {
                Logger.getLogger(Conjunto.class.getName()).log(Level.SEVERE, null, ex);
            } catch(IOException ex) {
                Logger.getLogger(Conjunto.class.getName()).log(Level.SEVERE, null, ex);
            }
            mostrarDatos();
        }
        else {
            abierto = false;
        }
    }
    
    public void guardarArchivo() {
        File archivo = new File(rutaOriginal);
        try {
            FileWriter archivoAux = new FileWriter(archivo);
            BufferedWriter escritor = new BufferedWriter(archivoAux);
            if(dataset.getComentarios() != "") {
                escritor.write(dataset.getComentarios() + "\n");                 
            }
            if(dataset.getNombre() != "") {
                escritor.append("@relation " + dataset.getNombre() + "\n\n");
            }
            if(attributeList != null) {
                for(int i = 0; i < attributeList.size(); i++) {
                    auxEscritura = attributeList.get(i).getNombreAtributo() + " " + attributeList.get(i).getTipoDato() + " " + attributeList.get(i).getExpresionRegular();
                    escritor.append("@attribute " + auxEscritura + "\n");
                }
                auxEscritura = "";
            }
            if(missingvalue.getValorFaltante() != "") {
                escritor.append("@missingValue " + missingvalue.getValorFaltante() + "\n\n");
            }
            escritor.append("@data\n");
            if(instanceList != null) {
                for(int j = 0; j < instanceList.size(); j++) {
                    for(int k = 0; k < attributeList.size(); k++) {
                        auxEscritura += instanceList.get(j).get(k).concat(", ");
                    }
                    auxEscritura = auxEscritura.substring(0, auxEscritura.length() - 2);
                    escritor.append(auxEscritura + "\n");
                    auxEscritura = "";
                }
            }
            escritor.close();
            JOptionPane.showMessageDialog(this, "El archivo guardado exitosamente en la ruta\n" + rutaOriginal);
        } catch (IOException ex) {
            Logger.getLogger(Conjunto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardarComoArchivo() {
        JFileChooser directorio = new JFileChooser();
        int seleccionado = directorio.showOpenDialog(this);
        if(seleccionado == JFileChooser.APPROVE_OPTION) {
            try {
                FileWriter archivoAux = new FileWriter(directorio.getSelectedFile().getAbsolutePath());
                BufferedWriter escritor = new BufferedWriter(archivoAux);
                if(dataset.getComentarios() != "") {
                    escritor.write(dataset.getComentarios() + "\n");                 
                }
                if(dataset.getNombre() != "") {
                    escritor.append("@relation " + dataset.getNombre() + "\n\n");
                }
                if(attributeList != null) {
                    for(int i = 0; i < attributeList.size(); i++) {
                        auxEscritura = attributeList.get(i).getNombreAtributo() + " " + attributeList.get(i).getTipoDato() + " " + attributeList.get(i).getExpresionRegular();
                        escritor.append("@attribute " + auxEscritura + "\n");
                    }
                    auxEscritura = "";
                }
                if(missingvalue.getValorFaltante() != "") {
                    escritor.append("@missingValue " + missingvalue.getValorFaltante() + "\n\n");
                }
                escritor.append("@data\n");
                if(instanceList != null) {
                    for(int j = 0; j < instanceList.size(); j++) {
                        for(int k = 0; k < attributeList.size(); k++) {
                            auxEscritura += instanceList.get(j).get(k).concat(", ");
                        }
                        auxEscritura = auxEscritura.substring(0, auxEscritura.length() - 2);
                        escritor.append(auxEscritura + "\n");
                        auxEscritura = "";
                    }
                }
                escritor.close();
            } catch(FileNotFoundException ex) {
                Logger.getLogger(Conjunto.class.getName()).log(Level.SEVERE, null, ex);
            } catch(IOException ex) {
                Logger.getLogger(Conjunto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void mostrarAtributos() {
        dtmAtributos = new DefaultTableModel();
        dtmAtributos.addColumn("Nombre atributo");
        dtmAtributos.addColumn("Tipo de dato");
        dtmAtributos.addColumn("Expresión regular");
        
        Object[] filaAtributo = new Object[3];
        for(int i = 0; i < attributeList.size(); i++) {
            filaAtributo[0] = attributeList.get(i).getNombreAtributo();
            filaAtributo[1] = attributeList.get(i).getTipoDato();
            filaAtributo[2] = attributeList.get(i).getExpresionRegular();
            dtmAtributos.addRow(filaAtributo);
        }
        dtmAtributos.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(e.getType() == TableModelEvent.UPDATE) {
                    int columna = e.getColumn();
                    int fila = e.getFirstRow();
                    switch(columna) { 
                        case 0: ///Si es la columna del nombre del atributo
                            attributeList.get(fila).setNombreAtributo(tblAtributos.getValueAt(fila, columna).toString());
                        break;
                        case 1: ///Si es la columna del tipo de dato o valor
                            attributeList.get(fila).setTipoDato(tblAtributos.getValueAt(fila, columna).toString());
                        break;
                        case 2: ///Si es la columna de la expresión regular
                            attributeList.get(fila).setExpresionRegular(tblAtributos.getValueAt(fila, columna).toString());
                        break;
                    }
                    mostrarDatos();
                }
            }
        });
        tblAtributos.setModel(dtmAtributos);
    }
    
    public void mostrarDatos() {
        mostrarAtributos();
        
        lblNombreConjunto.setText(dataset.getNombre());
        lblNumeroAtributos.setText(Integer.toString(attributeList.size()));
        lblNumeroInstancias.setText(Integer.toString(instanceList.size()));
        
        cbxFiltro.removeAllItems();
        
        dtmInstancias = new DefaultTableModel();
        dtmInstancias.addColumn("#");
        for(int i = 0; i < attributeList.size(); i++) {
            dtmInstancias.addColumn(attributeList.get(i).getNombreAtributo());
            cbxFiltro.addItem(attributeList.get(i).getNombreAtributo());
        }
        
        int valorFaltante = 0;
        double porcentajeFaltante = 0;
        Object[] filaInstancia = new Object[dataList.size() + 1];
        for(int j = 0; j < instanceList.size(); j++) {
            filaInstancia[0] = j + 1;
            for(int k = 0; k < dataList.size(); k++) {
                if(Pattern.matches(attributeList.get(k).getExpresionRegular(), instanceList.get(j).get(k))) {
                    filaInstancia[k + 1] = instanceList.get(j).get(k);
                }
                else if(Pattern.matches("[" + missingvalue.getValorFaltante() + "]", instanceList.get(j).get(k))){
                    filaInstancia[k + 1] = instanceList.get(j).get(k);
                    valorFaltante++;
                }
                else {
                    filaInstancia[k + 1] = "no valido";
                }
            }
            dtmInstancias.addRow(filaInstancia);
        }
        dtmInstancias.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(e.getType() == TableModelEvent.UPDATE) {
                    int columna = e.getColumn();
                    int fila = e.getFirstRow();
                    if((columna != -1) && (fila != -1)) {
                        if(columna > 0) {
                            instanceList.get(fila).set((columna - 1), tblInstancias.getValueAt(fila, columna).toString());
                            mostrarDatos();
                        }
                    }
                }
            }
        });
        
        porcentajeFaltante = ((valorFaltante * 100.0) / instanceList.size());
        if(porcentajeFaltante > 100.0) {
            porcentajeFaltante = 100.0;    
        }
        lblPorcentajeFaltantes.setText(utileria.redondeoDecimales(porcentajeFaltante, 2) + "%");
        tblInstancias.setModel(dtmInstancias);
        RowColor rowcolor = new RowColor();
        tblInstancias.setDefaultRenderer(Object.class, rowcolor);
        
        obtenerPorcentajeErrores();
    }
    
    public void borrarAtributos() {
        int opcion = JOptionPane.showConfirmDialog(this, "¿Deseas eliminar el/los atributo(s)?", "Eliminar", JOptionPane.YES_NO_CANCEL_OPTION);
        if(opcion == JOptionPane.YES_OPTION) {
            int fila = tblAtributos.getSelectedRow();
            if(fila >= 0) {
                int[] filas = tblAtributos.getSelectedRows();
                Arrays.sort(filas);
                for(int i = filas.length - 1; i >= 0; i--) {
                    attributeList.remove(filas[i]);
                    for(int j = 0; j < instanceList.size(); j++) {
                        instanceList.get(j).remove(filas[i]);                        
                    }
                }
                JOptionPane.showMessageDialog(this, "Atributo(s) eliminado(s).");
            }
            else {
                JOptionPane.showMessageDialog(this, "No seleccionó ninguna fila.", "¡Aviso!", JOptionPane.ERROR_MESSAGE);
            }   
            mostrarDatos();
        }
    }   
    
    public void agregarAtributo() {
        String nombreAtributo = "ex-number", tipoDato = "numeric", expresionRegular = "[0-9]+", valor = "?";
        attribute = new Attribute(nombreAtributo, tipoDato, expresionRegular);
        attributeList.add(attribute);
        for(int i = 0; i < instanceList.size(); i++) {
            instanceList.get(i).add(valor);
        }        
        mostrarDatos();
    }
    
    public void borrarInstancias() { 
        int opcion = JOptionPane.showConfirmDialog(this, "¿Deseas eliminar la(s) instancia(s)?", "Eliminar", JOptionPane.YES_NO_CANCEL_OPTION);
        if(opcion == JOptionPane.YES_OPTION) {
            int fila = tblInstancias.getSelectedRow();
            if(fila >= 0) {
                int[] filas = tblInstancias.getSelectedRows();
                Arrays.sort(filas);
                for(int i = filas.length - 1; i >= 0; i--) {
                    instanceList.remove(filas[i]);
                }
                JOptionPane.showMessageDialog(this, "Instancia(s) eliminada(s).");
            }
            else {
                JOptionPane.showMessageDialog(this, "No seleccionó ninguna fila.", "¡Aviso!", JOptionPane.ERROR_MESSAGE);
            }
            mostrarDatos();
        }
    }
    
    public void agregarInstancia() {
        String[] fila = new String[dataList.size()];
        dataList = new ArrayList<String>();
        for(int i = 0; i < fila.length; i++) {
            fila[i] = "?";
            dataList.add(fila[i]);
        }
        instanceList.add(dataList);
        mostrarDatos();
    }
    
    public void salirAplicacion() {
        int opcion = JOptionPane.showConfirmDialog(this, "¿Deseas guardar los cambios del archivo antes de salir?", "Salir", JOptionPane.YES_NO_CANCEL_OPTION);
        if(opcion == JOptionPane.YES_OPTION) {
            if(abierto == true) {
                guardarArchivo();
                System.exit(0);
            }
            else {
                JOptionPane.showMessageDialog(this, "No se puede guardar archivo que no este cargado, solo se puede salir.");
            }
        }
        else if(opcion == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }
    
    public void buscarDato() {
        int index = cbxFiltro.getSelectedIndex();
        String filtro = JOptionPane.showInputDialog(null, "Ingresa el dato de [" + attributeList.get(index).getNombreAtributo()+ "] que desea buscar: ");
        if(index != -1) {
            if(filtro == null) {
                mostrarDatos();
            }
            else if(filtro.length() == 0) {
                mostrarDatos();
            }
            else {
                dtmInstancias = new DefaultTableModel();
                dtmInstancias.addColumn("#");
                for(int i = 0; i < attributeList.size(); i++) {
                    dtmInstancias.addColumn(attributeList.get(i).getNombreAtributo());
                }
                
                Object[] filaInstancia = new Object[dataList.size() + 1];
                for(int j = 0; j < instanceList.size(); j++) {
                    String minusculas = instanceList.get(j).get(index).toLowerCase(), mayusculas = instanceList.get(j).get(index).toUpperCase(), cadenaOriginal = instanceList.get(j).get(index);
                    if((minusculas.equals(filtro)) || (mayusculas.equals(filtro)) || (cadenaOriginal.equals(filtro))) {
                        filaInstancia[0] = j + 1;
                        for(int k = 0; k < dataList.size(); k++) {
                            if(Pattern.matches(attributeList.get(k).getExpresionRegular(), instanceList.get(j).get(k))) {
                                filaInstancia[k + 1] = instanceList.get(j).get(k);
                            }
                            else if(Pattern.matches("[" + missingvalue.getValorFaltante() + "]", instanceList.get(j).get(k))){
                                filaInstancia[k + 1] = instanceList.get(j).get(k);
                            }
                            else if(!Pattern.matches(attributeList.get(k).getExpresionRegular(), instanceList.get(j).get(k))){
                                filaInstancia[k + 1] = "valor invalido";
                            }
                        }
                        dtmInstancias.addRow(filaInstancia);
                    }
                }
                dtmInstancias.addTableModelListener(new TableModelListener() {
                    @Override
                    public void tableChanged(TableModelEvent e) {
                        if(e.getType() == TableModelEvent.UPDATE) {
                            int columna = e.getColumn();
                            int fila = e.getFirstRow();
                            if((fila != -1) && (columna != -1)) {
                                if(columna > 0) {
                                    int opcion = JOptionPane.showConfirmDialog(null, "¿Deseas reemplazar el dato?", "¡Aviso!", JOptionPane.WARNING_MESSAGE);
                                    int fila_n = Integer.parseInt(tblInstancias.getValueAt(fila, 0).toString()) - 1; ///obtengo el nuevo indice filtrado de la busqueda
                                    if(opcion == JOptionPane.YES_OPTION) {
                                        instanceList.get(fila_n).set((columna - 1), tblInstancias.getValueAt(fila, columna).toString());
                                        mostrarDatos();
                                    }
                                }
                            }
                        }
                    }
                });
                tblInstancias.setModel(dtmInstancias);
                RowColor rowcolor = new RowColor();
                tblInstancias.setDefaultRenderer(Object.class, rowcolor);
            }
        }
    }
    
    public void obtenerPorcentajeErrores() {
        dtmErrores = new DefaultTableModel();
        dtmErrores.addColumn("Atributo");
        dtmErrores.addColumn("% de errores");
        dtmErrores.addColumn("% de faltantes");
        Object[] filaError = new Object[3];
        int valorError = 0, valorFalta = 0;
        double porcentajeError = 0.0, porcentajeFaltante = 0.0;
        for(int i = 0; i < dataList.size(); i++) {
            filaError[0] = attributeList.get(i).getNombreAtributo();
            for(int j = 0; j < instanceList.size(); j++) {
                if(Pattern.matches("[" + missingvalue.getValorFaltante() + "]", instanceList.get(j).get(i))) {
                    valorFalta++;
                }
                else if(!Pattern.matches(attributeList.get(i).getExpresionRegular(), instanceList.get(j).get(i))) {
                    valorError++;
                }
            }
            porcentajeError = ((valorError * 100.0) / instanceList.size());
            if(porcentajeError > 100.0) {
                porcentajeError = 100.0;
            }
            porcentajeFaltante = ((valorFalta * 100.0) / instanceList.size());
            if(porcentajeFaltante > 100.0) {
                porcentajeFaltante = 100.0;
            }
            filaError[1] = utileria.redondeoDecimales(porcentajeError, 2);
            filaError[2] = utileria.redondeoDecimales(porcentajeFaltante, 2);
            dtmErrores.addRow(filaError);
            if((valorError > 0) || (valorFalta > 0)) {
                valorError = 0;
                valorFalta = 0;
            }
        }
        tblErrorAtributo.setModel(dtmErrores);
        
        llenarAtributos();
    }
    
    public void llenarAtributos() {
        cbxAtributoUniv.removeAllItems();
        for(int i = 0; i < attributeList.size(); i++) {
            cbxAtributoUniv.addItem(attributeList.get(i).getNombreAtributo());
        }
        
        cbxAtributoBiv1.removeAllItems();
        cbxAtributoBiv2.removeAllItems();
        for(int i = 0; i < attributeList.size(); i++) {
            cbxAtributoBiv1.addItem(attributeList.get(i).getNombreAtributo());
            cbxAtributoBiv2.addItem(attributeList.get(i).getNombreAtributo());
        }
    }
    
    public void analisisUnivariable() {
        int indice = cbxAtributoUniv.getSelectedIndex();
        
        if(attributeList.get(indice).getTipoDato().equals("numeric")) {
            numericList = utileria.getListaNumerica(instanceList, attributeList, indice);
            
            int[] vector = utileria.llenarArreglo(numericList);
            
            dtmNumericos = new DefaultTableModel();
            dtmNumericos.addColumn("Concepto");
            dtmNumericos.addColumn("Resultado");
            
            Object[] fila = new Object[2];
            fila[0] = "Media"; 
            fila[1] = utileria.redondeoDecimales(utileria.calcularMedia(vector), 2);
            dtmNumericos.addRow(fila);
            fila[0] = "Mediana"; 
            fila[1] = utileria.redondeoDecimales(utileria.calcularMediana(vector), 2);
            dtmNumericos.addRow(fila);
            fila[0] = "Moda"; 
            fila[1] = utileria.calcularModa(vector);
            dtmNumericos.addRow(fila);
            fila[0] = "Desviación estándar"; 
            fila[1] = utileria.redondeoDecimales(utileria.calcularDesviacionEstandar(vector), 2);
            dtmNumericos.addRow(fila);
            
            tblNumericos.setModel(dtmNumericos);
            
            ChartFrame frame = utileria.crearBoxPlot("Análisis numérico", attributeList.get(indice).getNombreAtributo(), numericList);
            frame.pack();
            frame.setVisible(true);
            frame.setLocationRelativeTo(this);
        }
        else {
            utileria.limpiarTabla(tblNumericos);
            
            nominalList = utileria.getListaNominal(instanceList, attributeList, indice);
            
            ///si quieres mostrar la grafica de pie, descomentala y comenta la de barras...
            ChartFrame frame = utileria.getGraficaPie("Análisis categórico", attributeList.get(indice).getNombreAtributo(), nominalList);
            ///ChartFrame frame = utileria.getGraficaBarras("Análisis categórico", attributeList.get(indice).getNombreAtributo(), nominalList);
            frame.pack();
            frame.setVisible(true);
            frame.setLocationRelativeTo(this);
        }
    }
    
    public void analisisBivariable() {
        int indice1 = cbxAtributoBiv1.getSelectedIndex();
        int indice2 = cbxAtributoBiv2.getSelectedIndex();
        if(attributeList.get(indice1).getTipoDato().equals("numeric") && attributeList.get(indice2).getTipoDato().equals("numeric")) {
            JOptionPane.showMessageDialog(this, "Pearson. i1: " + indice1 + " i2: " + indice2);
        }
        else if((attributeList.get(indice1).getTipoDato().equals("numeric") && attributeList.get(indice2).getTipoDato().equals("nominal")) || (attributeList.get(indice1).getTipoDato().equals("nominal") && attributeList.get(indice2).getTipoDato().equals("numeric"))) {
            JOptionPane.showMessageDialog(this, "No se puede por ser numerico y nominal. i1: " + indice1 + " i2: " + indice2);
        }
        else if((attributeList.get(indice1).getTipoDato().equals("numeric") && attributeList.get(indice2).getTipoDato().equals("binary")) || (attributeList.get(indice1).getTipoDato().equals("binary") && attributeList.get(indice2).getTipoDato().equals("numeric"))) {
            JOptionPane.showMessageDialog(this, "No se puede por ser numerico y binario. i1: " + indice1 + " i2: " + indice2);
        }
        else {
            JOptionPane.showMessageDialog(this, "Tschuprow. i1: " + indice1 + " i2: " + indice2);
        }
    }
    
    private void btnCargarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarArchivoActionPerformed
        cargarArchivo();
    }//GEN-LAST:event_btnCargarArchivoActionPerformed

    private void btnGuardarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarArchivoActionPerformed
        // TODO add your handling code here:
        if(abierto == true) {
            guardarArchivo();
        }
        else {
            JOptionPane.showMessageDialog(this, "Error, no se puede guardar si no hay un Data Set cargado.", "¡Aviso!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarArchivoActionPerformed

    private void btnGuardarComoArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarComoArchivoActionPerformed
        // TODO add your handling code here:
        if(abierto == true) {
            guardarComoArchivo();
        }
        else {
            JOptionPane.showMessageDialog(this, "Error, no se puede guardar si no hay un Data Set cargado.", "¡Aviso!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarComoArchivoActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int opcion = JOptionPane.showConfirmDialog(this, "¿Deseas guardar los cambios antes de salir?", "Salir", JOptionPane.YES_NO_CANCEL_OPTION);
        if(opcion == JOptionPane.YES_OPTION) {
            if(abierto == true) {
                guardarArchivo();
                System.exit(0);
            }
            else {
                JOptionPane.showMessageDialog(this, "Error, no hay un Data Set cargado, solo se puede salir.", "¡Aviso!", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(opcion == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnAgregarAtributoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarAtributoActionPerformed
        // TODO add your handling code here:
        if(abierto == true) {
            agregarAtributo();
        }
        else {
            JOptionPane.showMessageDialog(this, "No se puede agregar un atributo si no hay un DataSet cargado.", "¡Aviso!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarAtributoActionPerformed

    private void btnEliminarAtributosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarAtributosActionPerformed
        // TODO add your handling code here:
        if(abierto == true) {
            borrarAtributos();
        }
        else {
            JOptionPane.showMessageDialog(this, "No se puede eliminar atributos si no hay un DataSet cargado.", "¡Aviso!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarAtributosActionPerformed

    private void btnAgregarInstanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarInstanciaActionPerformed
        // TODO add your handling code here:
        if(abierto == true) {
            agregarInstancia();
        }
        else {
            JOptionPane.showMessageDialog(this, "No se puede agregar una instancia si no hay un DataSet cargado.", "¡Aviso!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarInstanciaActionPerformed

    private void btnEliminarInstanciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarInstanciasActionPerformed
        // TODO add your handling code here:
        if(abierto == true) {
            borrarInstancias();
        }
        else {
            JOptionPane.showMessageDialog(this, "No se puede eliminar instancias si no hay un DataSet cargado.", "¡Aviso!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarInstanciasActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        int conteo = cbxFiltro.getItemCount();
        if(conteo > 0) {
            buscarDato();
        }
        else {
            JOptionPane.showMessageDialog(this, "No hay filtro de búsqueda.\nSe debe cargar un DataSet.", "¡Aviso!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAnalisisUnivariableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalisisUnivariableActionPerformed
        // TODO add your handling code here:
        int conteo = cbxAtributoUniv.getItemCount();
        if(conteo > 0) {
            analisisUnivariable();
        }
        else {
            JOptionPane.showMessageDialog(this, "No se puede realizar el análisis.\nSe debe cargar un DataSet.", "¡Aviso!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAnalisisUnivariableActionPerformed

    private void btnAnalisisBivariableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalisisBivariableActionPerformed
        // TODO add your handling code here:
        int conteo1 = cbxAtributoBiv1.getItemCount();
        int conteo2 = cbxAtributoBiv2.getItemCount();
        if((conteo1 > 0) && (conteo2 > 0)) {
            analisisBivariable();
        }
        else {
            JOptionPane.showMessageDialog(this, "No se puede realizar el análisis.\nSe debe cargar un DataSet.", "¡Aviso!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAnalisisBivariableActionPerformed
       
    
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Conjunto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Conjunto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarAtributo;
    private javax.swing.JButton btnAgregarInstancia;
    private javax.swing.JButton btnAnalisisBivariable;
    private javax.swing.JButton btnAnalisisUnivariable;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCargarArchivo;
    private javax.swing.JButton btnEliminarAtributos;
    private javax.swing.JButton btnEliminarInstancias;
    private javax.swing.JButton btnGuardarArchivo;
    private javax.swing.JButton btnGuardarComoArchivo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cbxAtributoBiv1;
    private javax.swing.JComboBox<String> cbxAtributoBiv2;
    private javax.swing.JComboBox<String> cbxAtributoUniv;
    private javax.swing.JComboBox<String> cbxFiltro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblNombreConjunto;
    private javax.swing.JLabel lblNumeroAtributos;
    private javax.swing.JLabel lblNumeroInstancias;
    private javax.swing.JLabel lblPorcentajeFaltantes;
    private javax.swing.JTable tblAtributos;
    private javax.swing.JTable tblErrorAtributo;
    private javax.swing.JTable tblInstancias;
    private javax.swing.JTable tblNumericos;
    // End of variables declaration//GEN-END:variables
}