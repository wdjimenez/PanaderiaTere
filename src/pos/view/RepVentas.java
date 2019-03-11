/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.view;

import java.awt.Component;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import pos.model.Ventas;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author wdjimenez
 */
public class RepVentas extends javax.swing.JFrame {

    /**
     * Creates new form RepVentas2
     */
    
    private Font default_font = new Font("Tahoma", Font.BOLD, 18);
    
    public RepVentas() {
        initComponents();          
        
        tableVentas.getColumnModel().getColumn(5).setCellRenderer((TableCellRenderer) new DecimalFormatRenderer());
        tableVentas.getColumnModel().getColumn(6).setCellRenderer((TableCellRenderer) new DecimalFormatRenderer());
        
        tableDescuentos.getColumnModel().getColumn(3).setCellRenderer((TableCellRenderer) new DecimalFormatRenderer());
        
        Date d = new Date();
        
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        
//        textFechaFrom.setText(sf.format(d));
//        
//        textFechaTo.setText(sf.format(d));
        textFechaFrom.setValue(d);
        textFechaTo.setValue(d);
        
        setLocationRelativeTo(null);
    }
    
    
    private void calculaTotalVenta(){
        DefaultTableModel dtm = (DefaultTableModel) tableVentas.getModel();
        int nRow = dtm.getRowCount();
        float total=0, descuento  = 0;
                
        for (int i = 0 ; i < nRow ; i++){
            total += (float)dtm.getValueAt(i,6);
        }
        
        dtm = (DefaultTableModel) tableDescuentos.getModel();
        nRow = dtm.getRowCount();
        for (int i = 0 ; i < nRow ; i++){
            descuento += (float)dtm.getValueAt(i,3);
        }
        
        total = total - descuento;
        
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tableVentas = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        textFechaFrom = new javax.swing.JFormattedTextField();
        textTotal = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDescuentos = new javax.swing.JTable();
        textFechaTo = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte de Ventas");
        setResizable(false);

        tableVentas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tableVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Hora", "Nota", "Producto", "Cantidad", "Precio", "Importe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableVentas);
        if (tableVentas.getColumnModel().getColumnCount() > 0) {
            tableVentas.getColumnModel().getColumn(3).setMinWidth(10);
        }

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        textFechaFrom.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        textFechaFrom.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        textFechaFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFechaFromActionPerformed(evt);
            }
        });

        textTotal.setEditable(false);
        textTotal.setForeground(new java.awt.Color(255, 51, 0));
        textTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        textTotal.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Venta Total");

        tableDescuentos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tableDescuentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Hora", "Nota", "Descuento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableDescuentos);

        textFechaTo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        textFechaTo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Rango de fechas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(textFechaFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(textFechaTo, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1012, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(textTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(363, 363, 363))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(textFechaFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFechaTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        buscaVentas();
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void textFechaFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFechaFromActionPerformed
        buscaVentas();
    }//GEN-LAST:event_textFechaFromActionPerformed

    private void buscaVentas() {
        Date from = (Date)textFechaFrom.getValue();
        Date to = (Date)textFechaTo.getValue();
        JLabel label = new JLabel();
                        
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        
        label.setFont(default_font);
        
        //Verificamos que el rango sea correcto from <= to
        if(from.after(to)){
            label.setText("La fecha final es menor que la fecha inicial, invierta las fechas");
            JOptionPane.showMessageDialog(this, label);
            return;
        }
        
        DefaultTableModel modelo_ventas = (DefaultTableModel) tableVentas.getModel();                       
        
        modelo_ventas.setRowCount(0);
        
        Ventas.repVentas(sf.format(from), sf.format(to)).forEach((ventas)->{
           modelo_ventas.addRow(new Object[]{
               ventas.getFecha(),
               ventas.getHora(),
               ventas.getIdVenta(),
               ventas.getNombre(),
               ventas.getCantidad(),
               ventas.getPrecio(),
               ventas.getImporte()
           });
        });
        
        
        DefaultTableModel modelo_descuento = (DefaultTableModel) tableDescuentos.getModel();
        modelo_descuento.setRowCount(0);
        Ventas.getDiscounts(sf.format(from), sf.format(to)).forEach((ventas)->{
           modelo_descuento.addRow(new Object[]{
               ventas.getFecha(),
               ventas.getHora(),
               ventas.getIdVenta(),
               ventas.getDescuento()
           });
        });
        
        calculaTotalVenta();
    }

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
//            java.util.logging.Logger.getLogger(RepVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(RepVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(RepVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(RepVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new RepVentas().setVisible(true);
//            }
//        });
//    }
    
    static class DecimalFormatRenderer extends DefaultTableCellRenderer {
        private static final DecimalFormat formatter = new DecimalFormat( "#.00" );

        public Component getTableCellRendererComponent(
           JTable table, Object value, boolean isSelected,
           boolean hasFocus, int row, int column) {

           // First format the cell value as required

           value = formatter.format((Number)value);

              // And pass it on to parent class

           return super.getTableCellRendererComponent(
              table, value, isSelected, hasFocus, row, column );
        }
     }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableDescuentos;
    private javax.swing.JTable tableVentas;
    private javax.swing.JFormattedTextField textFechaFrom;
    private javax.swing.JFormattedTextField textFechaTo;
    private javax.swing.JFormattedTextField textTotal;
    // End of variables declaration//GEN-END:variables
}
