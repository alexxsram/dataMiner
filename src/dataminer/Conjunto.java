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
import java.text.DecimalFormat;
import java.io.File;
import java.util.regex.Pattern;

import code.attributes;
import code.data;
import code.data_set;
import code.missing_values;
import java.util.Arrays;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author ALEJO SALGADO
 */
public class Conjunto extends javax.swing.JFrame {
    data_set ds; ///donde cargamos el nombre del conjunto de datos
    attributes att; ///donde cargamos los datos necesarios de los atributos
    missing_values mv;
    data dt;
    
    ArrayList<attributes> atrib;
    ArrayList<String> cadenas;
    ArrayList<ArrayList<String>> datos;
    
    String ruta_original = "";
    String aux = "", aux_esc = "";
    String comentarios = "", nombre = "";
    String[] atributos, registros;
    String valoresFaltantes = "";
    String cadena = ""; ///usada para concatenar cadenas
    
    boolean abierto;
    
    DefaultTableModel dtm_atributos = new DefaultTableModel();
    DefaultTableModel dtm_datos = new DefaultTableModel();
    
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
        tblDatos = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        lblValoresFaltantes = new javax.swing.JLabel();
        btnEliminarAtributo = new javax.swing.JButton();
        btnEliminarInstancia = new javax.swing.JButton();
        btnAgregarAtributo = new javax.swing.JButton();
        btnAgregarInstancia = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Conjunto de datos");

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

        tblAtributos.setModel(dtm_atributos);
        jScrollPane2.setViewportView(tblAtributos);

        jLabel1.setText("Nombre Conjunto: ");

        lblNombreConjunto.setText("...");

        jLabel2.setText("Núm. Atributos:");

        lblNumeroAtributos.setText("...");

        jLabel3.setText("Núm. Instancias:");

        lblNumeroInstancias.setText("...");

        tblDatos.setModel(dtm_datos);
        jScrollPane1.setViewportView(tblDatos);

        jLabel4.setText("Valores faltantes:");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCargarArchivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardarComo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNumeroInstancias))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNumeroAtributos))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNombreConjunto))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEliminarAtributo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregarAtributo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1023, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblValoresFaltantes))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEliminarInstancia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAgregarInstancia))
                            .addComponent(btnSalir))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCargarArchivo)
                    .addComponent(btnGuardar)
                    .addComponent(btnGuardarComo)
                    .addComponent(btnSalir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblNombreConjunto)
                    .addComponent(jLabel4)
                    .addComponent(lblValoresFaltantes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblNumeroAtributos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblNumeroInstancias))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminarAtributo)
                            .addComponent(btnAgregarAtributo)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarInstancia)
                    .addComponent(btnAgregarInstancia))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void cargarArchivo() {
        boolean bandera = false;
        int seleccionado;
        JFileChooser directorio = new JFileChooser();
        directorio.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter filtroArchivo = new FileNameExtensionFilter("*.arff", "arff");
        directorio.setFileFilter(filtroArchivo);
        seleccionado = directorio.showOpenDialog(this);
        if(seleccionado == JFileChooser.APPROVE_OPTION) {
            abierto = true;
            registros = null;
            ds = new data_set();
            mv = new missing_values();
            atrib = new ArrayList<attributes>();
            datos = new ArrayList<ArrayList<String>>();
            try {
                ruta_original = directorio.getSelectedFile().getAbsolutePath();
                FileReader archivo_aux = new FileReader(ruta_original);
                BufferedReader lector = new BufferedReader(archivo_aux);
                while((aux = lector.readLine()) != null) {
                    if(bandera == true) {
                        aux = aux.replace(" ", "");
                        registros = aux.split(",");
                        cadenas = new ArrayList<String>();
                        for(int i = 0; i < registros.length; i++) {
                            cadenas.add(registros[i]);
                        }
                        datos.add(cadenas);
                    }
                    else if(aux.startsWith("%")) {
                        comentarios += aux + "\n";
                        ds.setComentarios(comentarios);                        
                    }
                    else if(aux.startsWith("@relation")) {
                        nombre = aux.substring(10);
                        ds.setNombre(nombre);
                    }
                    else if(aux.startsWith("@attribute")) {
                        atributos = aux.split(" ");
                        att = new attributes(atributos[1], atributos[2], atributos[3]);
                        atrib.add(att);
                    }
                    else if(aux.startsWith("@missingValue")) {
                        valoresFaltantes = aux.substring(14);
                        mv.setValoresFaltantes(valoresFaltantes);
                    }
                    else if(aux.startsWith("@data")) {
                        bandera = true;
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
        File archivo = new File(ruta_original);
        try {
            FileWriter archivo_aux = new FileWriter(archivo);
            BufferedWriter escritor = new BufferedWriter(archivo_aux);
            if(ds.getComentarios() != "") {
                escritor.write(ds.getComentarios() + "\n");                 
            }
            if(ds.getNombre() != "") {
                escritor.append("@relation " + ds.getNombre() + "\n\n");
            }
            if(atrib != null) {
                for(int i = 0; i < atrib.size(); i++) {
                    aux_esc = atrib.get(i).getNombre() + " " + atrib.get(i).getValor() + " " + atrib.get(i).getExpresionRegular();
                    escritor.append("@attribute " + aux_esc + "\n");
                }
                aux_esc = "";
            }
            if(mv.getValoresFaltantes() != "") {
                escritor.append("@missingValues" + mv.getValoresFaltantes() + "\n\n");
            }
            escritor.append("@data\n");
            if(datos != null) {
                for(int j = 0; j < datos.size(); j++) {
                    for(int k = 0; k < cadenas.size(); k++) {
                        aux_esc += datos.get(j).get(k).concat(", ");
                    }
                    aux_esc = aux_esc.substring(0, aux_esc.length() - 2);
                    escritor.append(aux_esc + "\n");
                    aux_esc = "";
                }
            }
            escritor.close();
            JOptionPane.showMessageDialog(this, "El archivo guardado con exito en la ruta \n-> " + ruta_original);
        } catch (IOException ex) {
            Logger.getLogger(Conjunto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardarComoArchivo() {
        int seleccionado;
        JFileChooser directorio = new JFileChooser();
        seleccionado = directorio.showOpenDialog(this);
        if(seleccionado == JFileChooser.APPROVE_OPTION) {
            try {
                FileWriter archivo_aux = new FileWriter(directorio.getSelectedFile().getAbsolutePath());
                BufferedWriter escritor = new BufferedWriter(archivo_aux);
                if(ds.getComentarios() != "") {
                    escritor.write(ds.getComentarios() + "\n");                 
                }
                if(ds.getNombre() != "") {
                    escritor.append("@relation " + ds.getNombre() + "\n\n");
                }
                if(atrib != null) {
                    for(int i = 0; i < atrib.size(); i++) {
                        aux_esc = atrib.get(i).getNombre() + " " + atrib.get(i).getValor() + " " + atrib.get(i).getExpresionRegular();
                        escritor.append("@attribute " + aux_esc + "\n");
                    }
                    aux_esc = "";
                }
                if(mv.getValoresFaltantes() != "") {
                    escritor.append("@missingValues" + mv.getValoresFaltantes() + "\n\n");
                }
                escritor.append("@data\n");
                if(datos != null) {
                    for(int j = 0; j < datos.size(); j++) {
                        for(int k = 0; k < cadenas.size(); k++) {
                            aux_esc += datos.get(j).get(k).concat(", ");
                        }
                        aux_esc = aux_esc.substring(0, aux_esc.length() - 2);
                        escritor.append(aux_esc + "\n");
                        aux_esc = "";
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
        dtm_atributos = new DefaultTableModel();
        dtm_atributos.addColumn("Nombre atributo");
        dtm_atributos.addColumn("Tipo de dato");
        dtm_atributos.addColumn("Expresión regular");
        Object[] fila = new Object[3];
        for(int i = 0; i < atrib.size(); i++) {
            fila[0] = atrib.get(i).getNombre();
            fila[1] = atrib.get(i).getValor();
            fila[2] = atrib.get(i).getExpresionRegular();
            dtm_atributos.addRow(fila);
        }
        dtm_atributos.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(e.getType() == TableModelEvent.UPDATE) {
                    int columna = e.getColumn();
                    int fila = e.getFirstRow();
                    switch(columna) { 
                        case 0: ///Si es la columna del nombre del atributo
                            String cadn = "Nuevo valor -> " + tblAtributos.getValueAt(fila, columna).toString();
                            cadn += "\nValor anterior del nombre del atributo -> " + atrib.get(fila).getNombre();
                            JOptionPane.showMessageDialog(null, cadn);
                            atrib.get(fila).setNombre(tblAtributos.getValueAt(fila, columna).toString());
                        break;
                        case 1: ///Si es la columna del tipo de dato o valor
                            String cadv = "Nuevo valor -> " + tblAtributos.getValueAt(fila, columna).toString();
                            cadv += "\nValor anterior el tipo de dato -> " + atrib.get(fila).getValor();
                            JOptionPane.showMessageDialog(null, cadv);
                            atrib.get(fila).setValor(tblAtributos.getValueAt(fila, columna).toString());
                        break;
                        case 2: ///Si es la columna de la expresión regular
                            String cade = "Nuevo valor -> " + tblAtributos.getValueAt(fila, columna).toString();
                            cade += "\nValor anterior de la expresión regular -> " + atrib.get(fila).getExpresionRegular();
                            JOptionPane.showMessageDialog(null, cade);
                            atrib.get(fila).setExpresionRegular(tblAtributos.getValueAt(fila, columna).toString());
                        break;
                    }
                    mostrarDatos();
                }
            }
        });
        tblAtributos.setModel(dtm_atributos);
    }
    
    public void mostrarDatos() {
        mostrarAtributos();
        
        int cantidadValoresFaltantes = 0;
        ///double cantidadPorcentaje = 0.0, porcentaje = 0.0; 
        lblNombreConjunto.setText(ds.getNombre());
        lblNumeroAtributos.setText(Integer.toString(atrib.size()));
        lblNumeroInstancias.setText(Integer.toString(datos.size()));
        
        dtm_datos = new DefaultTableModel();
        dtm_datos.addColumn("Instancia #");
        for(int j = 0; j < atrib.size(); j++) {
            dtm_datos.addColumn(atrib.get(j).getNombre());
        }
        Object[] fila = new Object[cadenas.size() + 1];
        for(int k = 0; k < datos.size(); k++) {
            ///fila[0] = k + 1;
            fila[0] = k;
            for(int l = 0; l < cadenas.size(); l++) {
                if(Pattern.matches(atrib.get(l).getExpresionRegular(), datos.get(k).get(l))) {
                    fila[l + 1] = datos.get(k).get(l);
                }
                else if(Pattern.matches("[?]", datos.get(k).get(l))){ ///Si llegan a ser valores con el simbolo "?" se contabilizan
                    fila[l + 1] = datos.get(k).get(l);
                    cantidadValoresFaltantes++;
                }
                else if(!Pattern.matches(atrib.get(l).getExpresionRegular(), datos.get(k).get(l))){
                    fila[l + 1] = "error " + datos.get(k).get(l) + " no valido";
                }
            }
            dtm_datos.addRow(fila);
        }
        dtm_datos.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(e.getType() == TableModelEvent.UPDATE) {
                    int columna = e.getColumn();
                    int fila = e.getFirstRow();
                    if(columna != -1 && fila != -1) {
                        if(columna > 0) {
                            String cad = "Nuevo valor -> " + tblDatos.getValueAt(fila, columna).toString();
                            cad += "\nAnterior valor -> " + datos.get(fila).get(columna - 1);
                            JOptionPane.showMessageDialog(null, cad);
                            datos.get(fila).set((columna - 1), tblDatos.getValueAt(fila, columna).toString());
                        }
                    }
                }
            }
        });
        ///porcentaje = ((cantidadValoresFaltantes * 100) / (datos.size() * cadenas.size()));
        ///DecimalFormat formato = new DecimalFormat("#.00");
        lblValoresFaltantes.setText(Integer.toString(cantidadValoresFaltantes));
        tblDatos.setModel(dtm_datos);
    }
    
    public void borrarAtributo() {
        int opcion = JOptionPane.showConfirmDialog(this, "¿Deseas eliminar el/los atributo(s)?", "Eliminar", JOptionPane.YES_NO_CANCEL_OPTION);
        if(opcion == JOptionPane.YES_OPTION) {
            int fila = tblAtributos.getSelectedRow();
            if(fila >= 0) {
                int[] filas = tblAtributos.getSelectedRows();
                Arrays.sort(filas);
                for(int i = filas.length - 1; i >= 0; i--) {
                    dtm_atributos.removeRow(filas[i]);
                    atrib.remove(filas[i]);
                    for(int j = 0; j < datos.size(); j++) {
                        datos.get(j).remove(filas[i]);                        
                    }
                }
                JOptionPane.showMessageDialog(this, "Atributo(s) eliminado(s)");
            }
            else {
                JOptionPane.showMessageDialog(this, "No selecciono ninguna fila", "Aviso", JOptionPane.ERROR_MESSAGE);
            }   
            mostrarDatos();
        }
    }   
    
    public void agregarAtributo() {
        String nombre_atributo = "example", tipo_dato = "numeric", expresion_regular = "[0-9]+", valor = "1600";
        String[] fila = new String[3];        
        fila[0] = nombre_atributo;
        fila[1] = tipo_dato;
        fila[2] = expresion_regular;
        att = new attributes(fila[0], fila[1], fila[2]);
        atrib.add(att);
        for(int i = 0; i < datos.size(); i++) {
            datos.get(i).add(valor);
        }        
        mostrarDatos();
    }
    
    public void borrarInstancia() { 
        int opcion = JOptionPane.showConfirmDialog(this, "¿Deseas eliminar la(s) instancia(s)?", "Eliminar", JOptionPane.YES_NO_CANCEL_OPTION);
        if(opcion == JOptionPane.YES_OPTION) {
            int fila = tblDatos.getSelectedRow();
            if(fila >= 0) {
                int[] filas = tblDatos.getSelectedRows();
                Arrays.sort(filas);
                for(int i = filas.length - 1; i >= 0; i--) {
                    dtm_datos.removeRow(filas[i]);
                    datos.remove(filas[i]);
                }
                JOptionPane.showMessageDialog(this, "Instancia(s) eliminada(s).");
            }
            else {
                JOptionPane.showMessageDialog(this, "No Selecciono Ninguna Fila", "Aviso", JOptionPane.ERROR_MESSAGE);
            }
            
            mostrarDatos();
        }
    }
    
    public void agregarInstancia() {
        String[] fila = new String[cadenas.size()];
        cadenas = new ArrayList<String>();
        for(int i = 0; i < fila.length; i++) {
            fila[i] = atrib.get(i).getNombre();
            cadenas.add(fila[i]);
            ///JOptionPane.showMessageDialog(this, "El tamaño de la fila es -> " + (fila.length - 1) + "\nInserte en " + i + " --> " + fila[i] + "\nTamaño de cadenas -> " + (cadenas.size() - 1));
        }
        datos.add(cadenas);
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
                JOptionPane.showMessageDialog(this, "No se puede guardar archivo que no este cargado, unicamente salir.");
            }
        }
        else if(opcion == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }
    
    public void buscarDato() {
        ////ESTO NADA MÁS Y TERMINO, MAÑANA LO DETALLO Y HAGO PRUEBAS DE DEPURACIÓN....
    }
    
    private void btnCargarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarArchivoActionPerformed
        cargarArchivo();        
    }//GEN-LAST:event_btnCargarArchivoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(abierto == true) {
            guardarArchivo();
        }
        else {
            JOptionPane.showMessageDialog(this, "No se puede guardar si no se ha cargado un DataSet.");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarComoActionPerformed
        if(abierto == true) {
            guardarComoArchivo();
        }
        else {
            JOptionPane.showMessageDialog(this, "No se puede guardar si no se ha cargado un DataSet.");
        }
    }//GEN-LAST:event_btnGuardarComoActionPerformed

    private void btnEliminarAtributoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarAtributoActionPerformed
        borrarAtributo();
    }//GEN-LAST:event_btnEliminarAtributoActionPerformed

    private void btnEliminarInstanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarInstanciaActionPerformed
        borrarInstancia();
    }//GEN-LAST:event_btnEliminarInstanciaActionPerformed

    private void btnAgregarAtributoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarAtributoActionPerformed
        if(abierto == true) {
            agregarAtributo();
        }
        else {
            JOptionPane.showMessageDialog(this, "No se puede agregar atributos si no hay un DataSet cargado.");
        }
    }//GEN-LAST:event_btnAgregarAtributoActionPerformed

    private void btnAgregarInstanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarInstanciaActionPerformed
        if(abierto == true) {
            agregarInstancia();
        }
        else {
            JOptionPane.showMessageDialog(this, "No se puede agregar instancias si no hay un DataSet cargado.");
        }
    }//GEN-LAST:event_btnAgregarInstanciaActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        salirAplicacion();
    }//GEN-LAST:event_btnSalirActionPerformed

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
    private javax.swing.JButton btnCargarArchivo;
    private javax.swing.JButton btnEliminarAtributo;
    private javax.swing.JButton btnEliminarInstancia;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarComo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNombreConjunto;
    private javax.swing.JLabel lblNumeroAtributos;
    private javax.swing.JLabel lblNumeroInstancias;
    private javax.swing.JLabel lblValoresFaltantes;
    private javax.swing.JTable tblAtributos;
    private javax.swing.JTable tblDatos;
    // End of variables declaration//GEN-END:variables
}