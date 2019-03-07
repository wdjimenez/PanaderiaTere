/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.view;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pos.model.Producto;
import pos.model.ItemVentas;
import pos.model.Ventas;

/**
 *
 * @author Darío Jiménez
 */
public class VentasView extends javax.swing.JFrame {

    private TextAutoCompleter ac;
    
    JButton []arrPanes = new JButton[5];//arreglo de botones
    
    List<Producto> masvendidos;
    
    /**
     * Creates new form PanaderiaTere
     */
    public VentasView() {
        initComponents();
        initAutoCompleter();
        initProductsTable();
        initPanelBotones();
        calculaTotalVenta();        
        
        setLocationRelativeTo(null);
    
    }
    
    private void initAutoCompleter(){
        ac = new TextAutoCompleter(textBuscar);
        ac.setMode(0);
        Producto.all().forEach((producto) -> {
            ac.addItem(producto);
        });
    }
    
    public void refreshAutoCompleter(){
        this.ac.removeAllItems();
        
        Producto.all().forEach((producto) -> {
            System.out.println("Producto " + producto.getNombre() + " Precio " + producto.getPrecio());
            this.ac.addItem(producto);
        });
    }

    private void initProductsTable() {
//        DefaultTableModel modelo = (DefaultTableModel) tableItems.getModel();
//        tableItems.getColumn("Id").setMaxWidth(50);
//        // Nota: Vaciar tabla antes de insertar renglones
//        Producto.all().forEach((producto) -> {
//            modelo.addRow(new Object[]{
//                producto.getId(),
//                producto.getNombre(),
//                producto.getPrecio(),
//                producto.getStock()
//            });
//        });
    }
    
    private void calculaTotalVenta(){
        DefaultTableModel dtm = (DefaultTableModel) tableItems.getModel();
        int nRow = dtm.getRowCount();
        float total=0;
        
        textTotal.setText("");
        for (int i = 0 ; i < nRow ; i++){
//            for (int j = 0 ; j < nCol ; j++){
//                item = (ItemVentas)dtm.getValueAt(i,j);
                total += (float)dtm.getValueAt(i,3);
//            }
        }
        
        textTotal.setValue(new Double(total));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableItems = new javax.swing.JTable();
        btnCobrar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        textTotal = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        panelPanes = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Panaderia Tere");
        setResizable(false);

        textBuscar.setToolTipText("Introduzca el producto aquí");
        textBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBuscarActionPerformed(evt);
            }
        });

        tableItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Precio", "Cantidad", "Importe"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableItems);

        btnCobrar.setText("Cobrar");
        btnCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Reiniciar venta");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Total");

        textTotal.setEditable(false);
        textTotal.setForeground(new java.awt.Color(255, 0, 51));
        textTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        textTotal.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        textTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textTotalActionPerformed(evt);
            }
        });

        jButton1.setText("Eliminar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Producto");

        jMenu2.setText("Productos");

        jMenuItem1.setText("Nuevo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Editar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Eliminar");
        jMenuItem3.setToolTipText("");
        jMenu2.add(jMenuItem3);
        jMenu2.add(jSeparator1);

        jMenuItem7.setText("Entrada de mercancias");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("Edición");

        jMenuItem4.setText("Cambiar contraseña maestra");
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Reportes");

        jMenuItem5.setText("Ventas");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem6.setText("Stock");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(textTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139)
                        .addComponent(btnLimpiar)
                        .addGap(31, 31, 31)
                        .addComponent(btnCobrar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(textBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panelPanes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panelPanes, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCobrar)
                    .addComponent(btnLimpiar)
                    .addComponent(jLabel1)
                    .addComponent(textTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        ProductosView vProd = new ProductosView(this, true);
        vProd.setVisible(true);
        
        vProd.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e){
                System.out.println("jdialog window closed event received Close 1");
                
                refreshAutoCompleter();
                
            }

            public void windowClosing(WindowEvent e){
                System.out.println("jdialog window closing event received Close 2");
            }
        });
        
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void textBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBuscarActionPerformed
        // TODO add your handling code here:
        Producto pNuevo;
        
        pNuevo = (Producto)ac.getItemSelected();
        
        if (pNuevo != null) {
            
            agregarProductoVenta(pNuevo);
                   
        }
        
        calculaTotalVenta();
        textBuscar.setText("");           
        
    }//GEN-LAST:event_textBuscarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        
        reiniciarVenta(1);                
        
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void textTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textTotalActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int i = tableItems.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) tableItems.getModel();
        
        if (i >= 0){
            modelo.removeRow(i);
        }else{
            JOptionPane.showMessageDialog(null, "No se selecciono ningun registro para eliminar");
        }
        
        calculaTotalVenta();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrarActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelo = (DefaultTableModel) tableItems.getModel();
        int nRow = modelo.getRowCount(), cantidad;
        Producto p = null;
        List<ItemVentas> items = new ArrayList<>();
        ItemVentas iv = null;
        
        
        int i = modelo.getRowCount();
        
        if(i>0){
            
            for (int x = 0 ; x < nRow ; x++){
                p = (Producto)modelo.getValueAt(x, 0);
                cantidad = (int)modelo.getValueAt(x, 2);
                iv = new ItemVentas(p, cantidad);
                items.add(iv);
            }
            
            
            if(Ventas.generaVenta(textTotal.getText(), items)){
                boolean flag;
                double cambio;
                do{
                    flag = true;
                    try{
                        float efectivo = Float.parseFloat(JOptionPane.showInputDialog(this, "Efectivo"));
                                                                        
                        cambio = efectivo - (Double)textTotal.getValue();
                        
                        if(cambio < 0){
                            JOptionPane.showMessageDialog(this, "El efectivo es menor al monto total");
                            flag = false;
                        }else{
                            JOptionPane.showMessageDialog(this, "Cambio $" + cambio);
                        }
                        
                    }catch(NumberFormatException e){
                        flag = false;
                    }
                    
                }while(!flag);
                
                //JOptionPane.showMessageDialog(null, "Venta generada");
                
                reiniciarVenta(0);
                initPanelBotones();
                
            }
        }else{
            JOptionPane.showMessageDialog(null, "No se ha agregado ningun producto");
        }        
    }//GEN-LAST:event_btnCobrarActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        Stock stock = new Stock(this, true);
        stock.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        RepStock rep = new RepStock();
        rep.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        RepVentas rep = new RepVentas();
        rep.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        ActProducto actualizar = new ActProducto(this, true);
        actualizar.setVisible(true);
        
        actualizar.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e){
                System.out.println("jdialog window closed event received Close 1");
                
                refreshAutoCompleter();
                
            }

            public void windowClosing(WindowEvent e){
                System.out.println("jdialog window closing event received Close 2");
            }
        });
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
//            java.util.logging.Logger.getLogger(VentasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(VentasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(VentasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(VentasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new VentasView().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCobrar;
    public javax.swing.JButton btnLimpiar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPanel panelPanes;
    public javax.swing.JTable tableItems;
    public javax.swing.JTextField textBuscar;
    private javax.swing.JFormattedTextField textTotal;
    // End of variables declaration//GEN-END:variables

    private void reiniciarVenta(int confirm) {
        DefaultTableModel modelo = (DefaultTableModel) tableItems.getModel();
        
        int i = modelo.getRowCount();
        
        if(confirm == 1){
            if(i>0){
                int dialogResult = JOptionPane.showConfirmDialog (null, "¿Desea eliminar los registros de la tabla?");
                
                if(dialogResult == JOptionPane.YES_OPTION){
                    modelo.setRowCount(0);
                }                        
            }
        }else
            modelo.setRowCount(0);
        
        calculaTotalVenta();
    }

    private void initPanelBotones() {
        Producto p;
        masvendidos = Producto.bestSelled();
        panelPanes.removeAll();
        
        int size = masvendidos.size();
        
        for(int i = 0; i < arrPanes.length && i < size ; i++){//ciclo para crear, añadir, establecer propiedades a los botones
            
            p = masvendidos.get(i);
            arrPanes[i] = new JButton(p.getNombre());
            panelPanes.add(arrPanes[i]);
            arrPanes[i].setMargin(new Insets(5, 15, 5, 15));
            
            arrPanes[i].addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    for(int i = 0; i < arrPanes.length ; i++){
                        System.out.println("indice " + i);
                        if (arrPanes[i] == evt.getSource()){
                            agregarProductoVenta(masvendidos.get(i));                           
                            calculaTotalVenta();
                            break;
                        }
                    }
                    
                }
            });            
        }//fin ciclo
    }

    private void agregarProductoVenta(Producto prod) {
        Producto pOld;        
        ItemVentas item;
        int nPanes = 0, consumo = 0, index = -1, stock;
        
        
        try{
            nPanes = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Cuántos panes desea agregar?"));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "No se introdujo ningún número");       
            System.out.println(e.getMessage());

            return;
        }      

        DefaultTableModel modelo = (DefaultTableModel) tableItems.getModel();

        //Verificamos que haya Stock disponible
        int nRow = modelo.getRowCount();

        consumo = nPanes;
        for (int i = 0 ; i < nRow ; i++){
            pOld = (Producto)modelo.getValueAt(i,0);

            System.out.println("Producto anterior " + pOld.getId() + " " + pOld.getNombre());
            System.out.println("Producto nuevo " + prod.getId() + " " + prod.getNombre());
            if (pOld.getId() == prod.getId()){//Se trata del mismo producto, incrementamos el stock
                index = i;
                consumo += (int)modelo.getValueAt(i,2);
//                    modelo.setValueAt(consumo, nRow, 2);
            }
        }

        System.out.println("Consumo total " + consumo);
        System.out.println("Indice " + index);


        //Verificamos que haya suficiente consumo
        stock = Producto.getRealStock(prod.getId());

        System.out.println("Stock real " + stock);

        if (stock < consumo) {
            JOptionPane.showMessageDialog(null, "No hay suficiente stock de este producto");       
            return;
        }

        if(index >= 0){//Actualizamos un elemento ya existente

            modelo.setValueAt(consumo, index, 2);

            modelo.setValueAt(consumo * prod.getPrecio(), index, 3);

        }else{//Nuevo elemento                 

            item = new ItemVentas(prod, consumo);

            modelo.addRow(new Object[]{
                 item.getProd(),
                 item.getPrecio(),
                 item.getCantidad(),
                 item.getImporte()           
            }); 
        }
    }

}
