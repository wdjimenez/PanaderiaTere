/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.view;

import java.awt.Color;
import javax.swing.JOptionPane;
import pos.model.ComboItem;
import pos.model.Producto;
import pos.util.Config;

/**
 *
 * @author wdjimenez
 */
public class ActProducto extends javax.swing.JDialog {

    /**
     * Creates new form ActProducto
     */
    public ActProducto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        initComponents();
        llenarComboBox();
        
        getContentPane().setBackground(Color.decode(Config.ColorContent));
        jButton1.setBackground(Color.decode(Config.ColorElement));
        
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        comboProductos = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        textNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        inactivo = new javax.swing.JCheckBox();
        textPrecio = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar producto");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Producto");

        comboProductos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        comboProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProductosActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Nombre");

        textNombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Precio");

        jButton1.setBackground(new java.awt.Color(114, 151, 166));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pos/iconos/edit.png"))); // NOI18N
        jButton1.setText("Actualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        inactivo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inactivo.setText("Inactivo");

        textPrecio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        textPrecio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inactivo)
                            .addComponent(textPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(textPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(inactivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProductosActionPerformed
        Producto p = null;
        float precio = 0;
        String nombre = "";
        
        //Recuperamos el id seleccionado
        ComboItem item = (ComboItem)comboProductos.getSelectedItem(); 
        
        
        if(item.getIdProd() ==  -1){ //falso producto
            //Limpiamos la pantalla
            textNombre.setText(nombre);
            textPrecio.setValue(new Double(precio));
            inactivo.setSelected(false);
            return;
        }
        
        //Recuperamos la información del producto
        p = Producto.find(item.getIdProd());
        textNombre.setText(p.getNombre());
        textPrecio.setValue(new Double(p.getPrecio()));
        
        if(p.getInactivo() == 1)
            inactivo.setSelected(true);
        else
            inactivo.setSelected(false);
    }//GEN-LAST:event_comboProductosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        Producto p = null;
        float precio_old, precio_new;
        int inac = 0;
        
        //Recuperamos el id seleccionado
        ComboItem item = (ComboItem)comboProductos.getSelectedItem(); 
        
        if(item.getIdProd() ==  -1){ //falso producto
            JOptionPane.showMessageDialog(this, "Seleccione un producto de la lista para actualizar");
            return;
        }                
        if(inactivo.isSelected())
            inac = 1;
        
        p = Producto.find(item.getIdProd());
        
        precio_old = p.getPrecio();
        //precio_new = ((Long)textPrecio.getValue()).floatValue();
        precio_new = ((Number)textPrecio.getValue()).floatValue();
        //Verificamos que se hayan hecho cambios
        if(textNombre.getText().compareTo(p.getNombre()) != 0 ||
            precio_new != precio_old ||
           inac != p.getInactivo()){
            
            p.setNombre(textNombre.getText());
            p.setPrecio(precio_new);
            p.setInactivo(inac);
            if(p.update()){
                JOptionPane.showMessageDialog(this, "Se actualizó el producto satisfactoriamente");
                
                Producto.saveLogPrice(p.getId(), precio_old, precio_new);
                
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this, "Se presento un problema al actualizar el producto");
            }            
        }else{
            JOptionPane.showMessageDialog(this, "No se realizaron modificaciones al producto");
            return;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ActProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ActProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ActProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ActProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ActProducto().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<ComboItem> comboProductos;
    private javax.swing.JCheckBox inactivo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField textNombre;
    private javax.swing.JFormattedTextField textPrecio;
    // End of variables declaration//GEN-END:variables

    
    private void llenarComboBox() {
        comboProductos.addItem(new ComboItem("Seleccionar producto", -1));
        
        Producto.allProd().forEach((Producto producto) -> {
            comboProductos.addItem(new ComboItem(producto.getNombre(), producto.getId()));
        });        
    }    
}
