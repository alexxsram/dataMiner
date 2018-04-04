package dataminer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import code.attributes;
import code.adult;
import code.data_set;
import code.missing_values;
import java.util.ArrayList;

/**
 *
 * @author ALEJO SALGADO
 */
public class Conjunto extends javax.swing.JFrame {
    data_set ds = new data_set(); ///donde cargamos el nombre del conjunto de datos
    attributes att; ///donde cargamos los datos necesarios de los atributos
    missing_values mv = new missing_values();
    adult dt;
    
    ArrayList<attributes> atrib = new ArrayList<attributes>(); 
    ArrayList<adult> data = new ArrayList<adult>();
    
    String aux = ""; ///usado para leer/escribir en el archivo
    String comentarios = "", nombre = "";
    String[] atributos, registros;
    String valoresFaltantes = "";
    String cadena = ""; ///usada para concatenar cadenas
    
    public Conjunto() {
        initComponents();
        this.setLocationRelativeTo(Conjunto.this);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCargar = new javax.swing.JButton();
        btnGuardarComo = new javax.swing.JButton();
        btnCargarArchivo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaResultado = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnCargar.setText("Guardar");

        btnGuardarComo.setText("Guardar como...");

        btnCargarArchivo.setText("Cargar archivo");
        btnCargarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarArchivoActionPerformed(evt);
            }
        });

        txtAreaResultado.setEditable(false);
        txtAreaResultado.setColumns(20);
        txtAreaResultado.setRows(5);
        jScrollPane1.setViewportView(txtAreaResultado);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCargarArchivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCargar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardarComo)))
                .addContainerGap(590, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCargarArchivo)
                    .addComponent(btnCargar)
                    .addComponent(btnGuardarComo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                .addContainerGap())
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
            try {
                File archivo = directorio.getSelectedFile();
                FileReader archivo_aux = new FileReader(archivo.getAbsolutePath());
                BufferedReader lector = new BufferedReader(archivo_aux);
                while((aux = lector.readLine()) != null) {
                    if(bandera == true) {
                        aux = aux.replace(" ", "");
                        registros = aux.split(",");
                        dt = new adult(registros[0], registros[1], registros[2], registros[3], registros[4], registros[5], registros[6], registros[7], registros[8], registros[9], registros[10], registros[11], registros[12], registros[13], registros[14]);
                        data.add(dt);
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
                        att = new attributes();
                        att.setNombre(atributos[1]); //inserto el nombre del atributO                       
                        att.setValor(atributos[2]); //inserto el tipo de dato
                        att.setExpresionRegular(atributos[3]); //inserto la expresión regular
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
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Conjunto.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Conjunto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ///mostrarDatos();
    }
    
    private void mostrarDatos() {
        /*
        cadena = "Base de datos: " + ds.getNombre() + "\n";
        cadena += "Atributo\t|\tTipo de dato\t|\tExpresión Regular\n";
        for(int i = 0; i < atrib.size(); i++) {
            cadena += atrib.get(i).getNombre() + "\t|\t" + atrib.get(i).getValor() + "\t|\t" + atrib.get(i).getExpresionRegular() + "\n";
        }
        for(int j = 0; j < data.size(); j++) {
            cadena += data.get(j).getAge() + ", " + data.get(j).getWorkclass() + ", " + data.get(j).getFnlwgt() + "\n";
        }
        cadena += "Valores faltantes: " + mv.getValoresFaltantes() + "\n";
        cadena += "Número de atributos: " + atrib.size();
        txtAreaResultado.append(cadena);
        */
        
    }
    
    private void btnCargarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarArchivoActionPerformed
        cargarArchivo();
    }//GEN-LAST:event_btnCargarArchivoActionPerformed
        
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
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnCargarArchivo;
    private javax.swing.JButton btnGuardarComo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtAreaResultado;
    // End of variables declaration//GEN-END:variables
}