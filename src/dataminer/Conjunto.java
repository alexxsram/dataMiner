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

import code.DataSet;
import code.Attribute;
import code.MissingValue;
import code.RowColor;

/**
 *
 * @author ALEJO SALGADO
 */
public class Conjunto extends javax.swing.JFrame {
    DataSet dataset; //donde cargamos el nombre del conjunto de datos
    Attribute attribute; //donde cargamos los datos necesarios de los atributos
    MissingValue missingvalue; //donde cargamos el valor faltante del conjunto
    
    ArrayList<Attribute> attributeList; //Lista de atributos
    ArrayList<ArrayList<String>> instanceList; //Lista de instancias
    ArrayList<String> dataList; //Arreglo para los datos
    
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
    
    public Conjunto() {
        initComponents();
        this.setLocationRelativeTo(Conjunto.this);
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGuardar = new javax.swing.JButton();
        btnGuardarComo = new javax.swing.JButton();
        btnCargarArchivo = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAtributos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lblNombreConjunto = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblNumeroAtributos = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblNumeroInstancias = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInstancias = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        lblValoresFaltantes = new javax.swing.JLabel();
        btnEliminarAtributo = new javax.swing.JButton();
        btnEliminarInstancia = new javax.swing.JButton();
        btnAgregarAtributo = new javax.swing.JButton();
        btnAgregarInstancia = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        cbFiltro = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblErrorAtributo = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Conjunto de datos");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnGuardarComo.setText("Guardar como...");
        btnGuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarComoActionPerformed(evt);
            }
        });

        btnCargarArchivo.setText("Cargar archivo");
        btnCargarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarArchivoActionPerformed(evt);
            }
        });

        tblAtributos.setModel(dtmAtributos);
        jScrollPane2.setViewportView(tblAtributos);

        jLabel1.setText("Nombre Conjunto: ");

        lblNombreConjunto.setText("...");

        jLabel2.setText("Núm. Atributos:");

        lblNumeroAtributos.setText("...");

        jLabel3.setText("Núm. Instancias:");

        lblNumeroInstancias.setText("...");

        tblInstancias.setModel(dtmInstancias);
        tblInstancias.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblInstancias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblInstanciasKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tblInstancias);

        jLabel4.setText("% de valores faltantes:");

        lblValoresFaltantes.setText("...");

        btnEliminarAtributo.setText("Eliminar atributo");
        btnEliminarAtributo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarAtributoActionPerformed(evt);
            }
        });

        btnEliminarInstancia.setText("Eliminar instancia");
        btnEliminarInstancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarInstanciaActionPerformed(evt);
            }
        });

        btnAgregarAtributo.setText("Agregar atributo");
        btnAgregarAtributo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarAtributoActionPerformed(evt);
            }
        });

        btnAgregarInstancia.setText("Agregar instancia");
        btnAgregarInstancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarInstanciaActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel5.setText("Buscar dato por:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tblErrorAtributo.setAutoCreateRowSorter(true);
        tblErrorAtributo.setModel(dtmErrores);
        jScrollPane3.setViewportView(tblErrorAtributo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEliminarAtributo)
                        .addGap(5, 5, 5)
                        .addComponent(btnAgregarAtributo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNombreConjunto))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNumeroInstancias))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNumeroAtributos)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblValoresFaltantes))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCargarArchivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardarComo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 958, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEliminarInstancia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregarInstancia)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGuardar)
                        .addComponent(btnGuardarComo)
                        .addComponent(btnSalir)
                        .addComponent(btnCargarArchivo))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblNombreConjunto)
                            .addComponent(jLabel4)
                            .addComponent(lblValoresFaltantes))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(lblNumeroAtributos))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(lblNumeroInstancias)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminarAtributo)
                            .addComponent(btnAgregarAtributo))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarInstancia)
                    .addComponent(btnAgregarInstancia))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        RowColor rowcolor = new RowColor();
        tblAtributos.setDefaultRenderer(Object.class, rowcolor);
    }
    
    public void mostrarDatos() {
        mostrarAtributos();
        
        lblNombreConjunto.setText(dataset.getNombre());
        lblNumeroAtributos.setText(Integer.toString(attributeList.size()));
        lblNumeroInstancias.setText(Integer.toString(instanceList.size()));
        
        cbFiltro.removeAllItems();
        
        dtmInstancias = new DefaultTableModel();
        dtmInstancias.addColumn("#");
        for(int i = 0; i < attributeList.size(); i++) {
            dtmInstancias.addColumn(attributeList.get(i).getNombreAtributo());
            cbFiltro.addItem(attributeList.get(i).getNombreAtributo());
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
                    filaInstancia[k + 1] = "valor invalido";
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
        Formatter formato = new Formatter();
        lblValoresFaltantes.setText(formato.format("%.2f", porcentajeFaltante) + "%");
        tblInstancias.setModel(dtmInstancias);
        RowColor rowcolor = new RowColor();
        tblInstancias.setDefaultRenderer(Object.class, rowcolor);
        
        obtenerPorcentajeErrores();
    }
    
    public void obtenerPorcentajeErrores() {
        dtmErrores = new DefaultTableModel();
        dtmErrores.addColumn("Atributo");
        dtmErrores.addColumn("% de errores");
        Object[] filaError = new Object[2];
        int valorError = 0;
        double porcentajeError = 0;
        for(int i = 0; i < dataList.size(); i++) {
            filaError[0] = attributeList.get(i).getNombreAtributo();
            for(int j = 0; j < instanceList.size(); j++) {
                if(Pattern.matches("[" + missingvalue.getValorFaltante() + "]", instanceList.get(j).get(i))) {
                    valorError = 0;
                }
                else if(!Pattern.matches(attributeList.get(i).getExpresionRegular(), instanceList.get(j).get(i))) {
                    valorError++;
                }
            }
            porcentajeError = ((valorError * 100.0) / instanceList.size());
            Formatter formato = new Formatter();
            filaError[1] = formato.format("%.2f", porcentajeError);
            dtmErrores.addRow(filaError);
            if(valorError > 0) {
                valorError = 0;
            }
        }
        tblErrorAtributo.setModel(dtmErrores);
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
        int index = cbFiltro.getSelectedIndex();
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
    
    private void btnCargarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarArchivoActionPerformed
        cargarArchivo();        
    }//GEN-LAST:event_btnCargarArchivoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(abierto == true) {
            guardarArchivo();
        }
        else {
            JOptionPane.showMessageDialog(this, "No se puede guardar si no se ha cargado un DataSet." , "¡Aviso!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarComoActionPerformed
        if(abierto == true) {
            guardarComoArchivo();
        }
        else {
            JOptionPane.showMessageDialog(this, "No se puede guardar un nuevo archivo si no se ha cargado un DataSet." , "¡Aviso!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarComoActionPerformed

    private void btnEliminarAtributoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarAtributoActionPerformed
        if(abierto == true) {
            borrarAtributos();
        }
        else {
            JOptionPane.showMessageDialog(this, "No se puede eliminar atributos si no hay un DataSet cargado.", "¡Aviso!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarAtributoActionPerformed

    private void btnEliminarInstanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarInstanciaActionPerformed
        if(abierto == true) {
            borrarInstancias();
        }
        else {
            JOptionPane.showMessageDialog(this, "No se puede eliminar instancias si no hay un DataSet cargado.", "¡Aviso!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarInstanciaActionPerformed

    private void btnAgregarAtributoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarAtributoActionPerformed
        if(abierto == true) {
            agregarAtributo();
        }
        else {
            JOptionPane.showMessageDialog(this, "No se puede agregar atributos si no hay un DataSet cargado.", "¡Aviso!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarAtributoActionPerformed

    private void btnAgregarInstanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarInstanciaActionPerformed
        if(abierto == true) {
            agregarInstancia();
        }
        else {
            JOptionPane.showMessageDialog(this, "No se puede eliminar instancias si no hay un DataSet cargado.", "¡Aviso!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarInstanciaActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        salirAplicacion();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void tblInstanciasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblInstanciasKeyTyped
        
    }//GEN-LAST:event_tblInstanciasKeyTyped

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        int conteo = cbFiltro.getItemCount(); 
        if(conteo > 0) { //Si hay elementos para filtrar la busqueda
            buscarDato();
        }
        else {
            JOptionPane.showMessageDialog(this, "No hay filtro de búsqueda.\nSe debe cargar un DataSet.", "¡Aviso!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

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
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCargarArchivo;
    private javax.swing.JButton btnEliminarAtributo;
    private javax.swing.JButton btnEliminarInstancia;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarComo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cbFiltro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblNombreConjunto;
    private javax.swing.JLabel lblNumeroAtributos;
    private javax.swing.JLabel lblNumeroInstancias;
    private javax.swing.JLabel lblValoresFaltantes;
    private javax.swing.JTable tblAtributos;
    private javax.swing.JTable tblErrorAtributo;
    private javax.swing.JTable tblInstancias;
    // End of variables declaration//GEN-END:variables
}