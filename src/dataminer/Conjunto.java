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
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

import code.attributes;
import code.adult;
import code.data_set;
import code.missing_values;

/**
 *
 * @author ALEJO SALGADO
 */
public class Conjunto extends javax.swing.JFrame {
    data_set ds; ///donde cargamos el nombre del conjunto de datos
    attributes att; ///donde cargamos los datos necesarios de los atributos
    missing_values mv;
    adult dt;
    ArrayList<attributes> atrib; 
    
    String aux = ""; ///usado para leer en el archivo
    String comentarios = "", nombre = "";
    String[] atributos, registros;
    String valoresFaltantes = "";
    String cadena = ""; ///usada para concatenar cadenas
    int numeroInstancias = 0;
    
    DefaultTableModel dtm = new DefaultTableModel();
    
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAtributos = new javax.swing.JTable();
        btnRegresar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblNombreConjunto = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblNumeroAtributos = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblNumeroInstancias = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Conjunto de datos");

        btnCargar.setText("Guardar");

        btnGuardarComo.setText("Guardar como...");

        btnCargarArchivo.setText("Cargar archivo");
        btnCargarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarArchivoActionPerformed(evt);
            }
        });

        tblAtributos.setModel(dtm);
        jScrollPane2.setViewportView(tblAtributos);

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre Conjunto: ");

        lblNombreConjunto.setText("...");

        jLabel2.setText("Núm. Atributos:");

        lblNumeroAtributos.setText("...");

        jLabel3.setText("Núm. Instancias:");

        lblNumeroInstancias.setText("...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegresar)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnCargarArchivo)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnCargar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnGuardarComo)))
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
                                .addComponent(lblNombreConjunto)))))
                .addContainerGap(885, Short.MAX_VALUE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblNombreConjunto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblNumeroAtributos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblNumeroInstancias))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 210, Short.MAX_VALUE)
                .addComponent(btnRegresar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void cargarArchivo() {
        ds = new data_set();
        mv = new missing_values();
        atrib = new ArrayList<attributes>();
        boolean bandera = false;
        int seleccionado;
        JFileChooser directorio = new JFileChooser();
        directorio.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter filtroArchivo = new FileNameExtensionFilter("*.arff", "arff");
        directorio.setFileFilter(filtroArchivo);
        seleccionado = directorio.showOpenDialog(this);
        if(seleccionado == JFileChooser.APPROVE_OPTION) {
            try {
                FileReader archivo_aux = new FileReader(directorio.getSelectedFile().getAbsolutePath());
                BufferedReader lector = new BufferedReader(archivo_aux);
                while((aux = lector.readLine()) != null) {
                    if(bandera == true) {
                        aux = aux.replace(" ", "");
                        registros = aux.split(",");
                        numeroInstancias++;
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
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Conjunto.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Conjunto.class.getName()).log(Level.SEVERE, null, ex);
            }
            mostrarAtributos();
        }
    }
    
    private void mostrarAtributos() {
        lblNombreConjunto.setText(ds.getNombre());
        lblNumeroAtributos.setText(Integer.toString(atrib.size()));
        lblNumeroInstancias.setText(Integer.toString(numeroInstancias));
        dtm.addColumn("Nombre atributo");
        dtm.addColumn("Tipo de dato");
        dtm.addColumn("Expresión regular");
        Object[] fila = new Object[3];
        for(int i = 0; i < atrib.size(); i++) {
            fila[0] = atrib.get(i).getNombre();
            fila[1] = atrib.get(i).getValor();
            fila[2] = atrib.get(i).getExpresionRegular();
            dtm.addRow(fila);
        }
    }
    
    private void btnCargarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarArchivoActionPerformed
        cargarArchivo();
    }//GEN-LAST:event_btnCargarArchivoActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        Inicio inicio = new Inicio();
        inicio.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnRegresarActionPerformed
        
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
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNombreConjunto;
    private javax.swing.JLabel lblNumeroAtributos;
    private javax.swing.JLabel lblNumeroInstancias;
    private javax.swing.JTable tblAtributos;
    // End of variables declaration//GEN-END:variables
}