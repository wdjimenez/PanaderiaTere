/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.view;

import java.awt.Color;
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
import pos.util.Config;


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
        
        getContentPane().setBackground(Color.decode(Config.ColorContent));
        jButton2.setBackground(Color.decode(Config.ColorElement));
        
        tableVentas.getColumnModel().getColumn(5).setCellRenderer((TableCellRenderer) new DecimalFormatRenderer());
        tableVentas.getColumnModel().getColumn(6).setCellRenderer((TableCellRenderer) new DecimalFormatRenderer());
        
        tableDescuentos.getColumnModel().getColumn(3).setCellRenderer((TableCellRenderer) new DecimalFormatRenderer());
        
        Date d = new Date();
        
//        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        
//        textFechaFrom.setText(sf.format(d));
//        
//        textFechaTo.setText(sf.format(d));
//        textFechaFrom.setValue(d);
//        textFechaTo.setValue(d);        
        
        dateFrom.setDate(d);
        dateTo.setDate(d);
        
        setLocationRelativeTo(null);
    }
    
    
    private void calculaTotalVenta(){
        DefaultTableModel dtm = (DefaultTableModel) tableVentas.getModel();
        int nRow = dtm.getRowCount();
        float total=0, descuento  = 0;

	ajustaAnchoFilas(tableVentas);       
        ajustaAnchoFilas(tableDescuentos);
                
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
        textTotal = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDescuentos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dateFrom = new com.toedter.calendar.JDateChooser();
        dateTo = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte de Ventas");
        setResizable(false);

        tableVentas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tableVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Hora", "Nota", "Producto", "Cantidad", "Precio", "Importe", "Usuario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Float.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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

        jButton2.setBackground(new java.awt.Color(114, 151, 166));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Ventas");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Descuentos");

        dateFrom.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        dateTo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Fechas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(textTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(363, 363, 363))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1012, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(906, 906, 906)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(dateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2)
                    .addComponent(dateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

    private void buscaVentas() {
        //Date from = (Date)textFechaFrom.getValue();
        //Date to = (Date)textFechaTo.getValue();
        
        Date from = dateFrom.getDate();
        Date to = dateTo.getDate();
        
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
               ventas.getImporte(),
               ventas.getUsuario()
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

    private void ajustaAnchoFilas(JTable tablegen) {
        try {
            for (int row=0; row<tablegen.getRowCount(); row++) {
                int rowHeight = tablegen.getRowHeight();

                for (int column=0; column<tablegen.getColumnCount(); column++) {
                    Component comp = tablegen.prepareRenderer(tablegen.getCellRenderer(row, column), row, column);
                    rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
                }

                tablegen.setRowHeight(row, rowHeight);
            }
        } catch(ClassCastException e) { }
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
            java.util.logging.Logger.getLogger(RepVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RepVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RepVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RepVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RepVentas().setVisible(true);
            }
        });
    }
    
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
    private com.toedter.calendar.JDateChooser dateFrom;
    private com.toedter.calendar.JDateChooser dateTo;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableDescuentos;
    private javax.swing.JTable tableVentas;
    private javax.swing.JFormattedTextField textTotal;
    // End of variables declaration//GEN-END:variables
}
