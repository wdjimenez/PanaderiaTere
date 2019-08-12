/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.view;

import com.mxrck.autocompleter.AutoCompleterCallback;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import pos.model.Producto;
import pos.model.ItemVentas;
import pos.model.PasswordUtils;
import pos.model.Usuario;
import pos.model.Ventas;

/**
 *
 * @author Darío Jiménez
 */
public class VentasView extends javax.swing.JFrame {

    private TextAutoCompleter ac;
    private Font default_font = new Font("Tahoma", Font.BOLD, 18);
    
    private static final String strProducto = "Producto";
    private static final String strEfectivo = "Efectivo";
    private static final String strCambio = "Cambio";
    private static final String strCantidad = "Cantidad";
    
    private static final int VENTA = 0;
    private static final int EFECTIVO = 1;
    private static final int CAMBIO = 2;
    private static final int CANTIDAD = 3;
    
    private Producto pan_selected;
            
    private int estado; //EN espera de producto
    
    
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
        
        getContentPane().setBackground(new java.awt.Color(242,242,242));
        
        //Image img = icon.getImage("/pos/images/descuento.png") ;  
        //Image newimg = img.getScaledInstance( NEW_WIDTH, NEW_HEIGHT,  java.awt.Image.SCALE_SMOOTH ) ;  
        //icon = new ImageIcon( newimg );
        //btn_adddesc.setIcon(new ImageIcon(((new ImageIcon("/pos/images/descuento.png")).getImage()).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
        //btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pos/images/descuento.png"))); // NOI18N
        
        
        textTotal.setValue(new Double(0));
        textDescuento.setValue(new Double(0));
        
        tableItems.getTableHeader().setFont(default_font);
        tableItems.setFont(default_font);
        
        calculaTotalVenta();        
        
        setLocationRelativeTo(null);
        
        
//        //Acceso directo para Boton Cobrar
//        Action cobrarAction = new AbstractAction("Cobrar"){
//            @Override
//            public void actionPerformed(ActionEvent e){
//                cobrarVenta();
//            }
//        };
//        
//        btnCobrar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F8"), "Cobrar");
//        
//        btnCobrar.getActionMap().put("Cobrar", cobrarAction);
//        
//        //Acceso directo para Boton Remover producto
//        Action removerAction = new AbstractAction("Remover"){
//            @Override
//            public void actionPerformed(ActionEvent e){
//                removerProducto();
//            }
//        };
//        
//        btnRemover.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F5"), "Remover");
//        
//        btnRemover.getActionMap().put("Remover", removerAction);
//        
//        
//        //Acceso directo para Reinicar Venta
//        Action reiniciarAction = new AbstractAction("Reiniciar"){
//            @Override
//            public void actionPerformed(ActionEvent e){
//                reiniciarVenta(1);  
//            }
//        };
//        
//        btnLimpiar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F6"), "Reiniciar");
//        
//        btnLimpiar.getActionMap().put("Reiniciar", reiniciarAction);
        
        estado = VENTA;
                       
    }
    
    private void initAutoCompleter(){
        ac = new TextAutoCompleter(textBuscar, Producto.all().toArray());
        ac.setMode(0);
        
        
//        Producto.all().forEach((producto) -> {
//            ac.addItem(producto);
//        });
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
        float total=0, descuento = 0;
       
        for (int i = 0 ; i < nRow ; i++){
//            for (int j = 0 ; j < nCol ; j++){
//                item = (ItemVentas)dtm.getValueAt(i,j);
                total += (float)dtm.getValueAt(i,3);
//            }
        }
        //Restamos el valor del descuento
        descuento = ((Double)textDescuento.getValue()).floatValue();
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

        textBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableItems = new javax.swing.JTable();
        btnCobrar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        textTotal = new javax.swing.JFormattedTextField();
        btnRemover = new javax.swing.JButton();
        labelAccion = new javax.swing.JLabel();
        panelPanes = new javax.swing.JPanel();
        textDescuento = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        btn_adddesc = new javax.swing.JButton();
        btn_remdesc = new javax.swing.JButton();
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
        setBackground(new java.awt.Color(242, 242, 242));
        setResizable(false);

        textBuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        textBuscar.setToolTipText("Introduzca el producto aquí");
        textBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBuscarActionPerformed(evt);
            }
        });

        tableItems.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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

        btnCobrar.setBackground(new java.awt.Color(114, 151, 166));
        btnCobrar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCobrar.setText("Cobrar");
        btnCobrar.setToolTipText("Genera la venta");
        btnCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrarActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(114, 151, 166));
        btnLimpiar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLimpiar.setText("Reiniciar venta");
        btnLimpiar.setToolTipText("Borra la lista de productos");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
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

        btnRemover.setBackground(new java.awt.Color(114, 151, 166));
        btnRemover.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnRemover.setText("Remover producto");
        btnRemover.setToolTipText("Remueve el producto seleccionado");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        labelAccion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelAccion.setText("Producto");

        textDescuento.setEditable(false);
        textDescuento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        textDescuento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Descuento");

        btn_adddesc.setBackground(new java.awt.Color(114, 151, 166));
        btn_adddesc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_adddesc.setText("Agregar descuento");
        btn_adddesc.setToolTipText("Agregar descuento a la venta");
        btn_adddesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_adddescActionPerformed(evt);
            }
        });

        btn_remdesc.setBackground(new java.awt.Color(114, 151, 166));
        btn_remdesc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_remdesc.setText("Eliminar descuento");
        btn_remdesc.setToolTipText("Eliminar descuento de la venta");
        btn_remdesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_remdescActionPerformed(evt);
            }
        });

        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jMenu2.setText("Productos");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem1.setText("Nuevo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem2.setText("Editar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem3.setText("Eliminar");
        jMenuItem3.setToolTipText("");
        jMenu2.add(jMenuItem3);
        jMenu2.add(jSeparator1);

        jMenuItem7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem7.setText("Entrada de mercancias");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("Edición");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jMenuItem4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem4.setText("Cambiar contraseña maestra");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Reportes");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jMenuItem5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem5.setText("Ventas");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelPanes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelAccion)
                                .addGap(18, 18, 18)
                                .addComponent(textBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(btnRemover)
                                .addGap(18, 18, 18)
                                .addComponent(btn_adddesc)
                                .addGap(18, 18, 18)
                                .addComponent(btn_remdesc)
                                .addGap(421, 421, 421)
                                .addComponent(btnLimpiar)
                                .addGap(31, 31, 31)
                                .addComponent(btnCobrar)))
                        .addGap(0, 38, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textDescuento)
                            .addComponent(textTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAccion)
                    .addComponent(textBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panelPanes, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCobrar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnRemover)
                    .addComponent(btn_adddesc)
                    .addComponent(btn_remdesc))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        
        boolean valido = autenticarUsuario();
        
        if (valido) {
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
        }                              
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void textBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBuscarActionPerformed
        // TODO add your handling code here:
//        Producto pNuevo;
        
//        pNuevo = (Producto)ac.getItemSelected();        

//        if (pNuevo != null) {
//            try{
//                agregarProductoVenta(pNuevo);
//            }catch(ParseException ex){
//                
//            };
//                               
//        }
//        
//        calculaTotalVenta();
//        textBuscar.setText("");           
        int num_panes;
        double efectivo;
        
        switch(estado){
            case VENTA:
                pan_selected = (Producto)ac.getItemSelected();    
                
                if(pan_selected != null)
                    estado = CANTIDAD;
//                else{
//                    textBuscar.setText("");
//                    ac = new TextAutoCompleter(textBuscar, Producto.all().toArray());
//                    ac.setMode(-1);
//                }
                    
                break;
            case CANTIDAD:
                //recuperamos la cantidad de panes     

                try{
                    num_panes = Integer.parseInt(textBuscar.getText());
                    
                    agregarProductoVenta(pan_selected, num_panes);
                    
                    calculaTotalVenta();
                    
                    //Liberamos el pan agregado
                    pan_selected = null;
                    //Cambiamos el estatus de la pantalla
                    estado = VENTA;
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(this, "Solo se aceptan valores numericos", "Mensaje",JOptionPane.ERROR_MESSAGE);
                }catch (ParseException ex) {
                    JOptionPane.showMessageDialog(this, "Se presento un problema para agregar el producto", "Mensaje",JOptionPane.ERROR_MESSAGE);
                }
                break;
            case EFECTIVO:
                try{
                    efectivo = Float.parseFloat(textBuscar.getText());
                    
                    if(efectivo >= (Double)textTotal.getValue())
                        estado = CAMBIO;
                    else
                        JOptionPane.showMessageDialog(this, "El efectivo es menor al total de la venta", "Mensaje",JOptionPane.ERROR_MESSAGE);
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(this, "Solo se aceptan valores numericos", "Mensaje",JOptionPane.ERROR_MESSAGE);
                }                
                
                break;
            
            case CAMBIO:
                reiniciarVenta(0);
                estado = VENTA;
                break;
        }
                
        actualizaElementosPantalla(estado);

    }//GEN-LAST:event_textBuscarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:        
        reiniciarVenta(1);                
        
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void textTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textTotalActionPerformed

    private void btnCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrarActionPerformed
        
        cobrarVenta();        
          
    }//GEN-LAST:event_btnCobrarActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        Stock stock = new Stock(this, true);
        stock.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        RepStock rep = new RepStock();
        rep.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        boolean valido = autenticarUsuario();
        
        if (valido) {
            RepVentas rep = new RepVentas();
            rep.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        boolean valido = autenticarUsuario();
        
        if (valido) {
        
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
        }
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        boolean valido = autenticarUsuario();
        
        if (valido) {
            ActPass nuevopass = new ActPass(this, true);
            nuevopass.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void btn_adddescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_adddescActionPerformed
        float descuento = 0;
        JLabel label = new JLabel();        
        
        label.setFont(default_font);
        
        boolean valido = autenticarUsuario();
        
        if (valido) {
            boolean flag;
            do{
                flag = true;
                try{
                    label.setText("Descuento");
                    descuento = Float.parseFloat(JOptionPane.showInputDialog(this, label));

                    if(descuento > (Double)textTotal.getValue()){
                        label.setText("El descuento es mayor al importe total");
                        JOptionPane.showMessageDialog(this, label);
                        flag = false;
                    }                     
                }catch(NumberFormatException e){
                    flag = false;
                }

            }while(!flag);
            
            textDescuento.setValue(new Double(descuento));
            
            calculaTotalVenta();
            
        }
    }//GEN-LAST:event_btn_adddescActionPerformed

    private void btn_remdescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_remdescActionPerformed
        textDescuento.setValue(new Double(0));
        calculaTotalVenta();
    }//GEN-LAST:event_btn_remdescActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        removerProducto();

    }//GEN-LAST:event_btnRemoverActionPerformed

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
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btn_adddesc;
    private javax.swing.JButton btn_remdesc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JLabel labelAccion;
    private javax.swing.JPanel panelPanes;
    public javax.swing.JTable tableItems;
    public javax.swing.JTextField textBuscar;
    private javax.swing.JFormattedTextField textDescuento;
    private javax.swing.JFormattedTextField textTotal;
    // End of variables declaration//GEN-END:variables

    private void reiniciarVenta(int confirm) {
        DefaultTableModel modelo = (DefaultTableModel) tableItems.getModel();
        
        int i = modelo.getRowCount();
        
        if(confirm == 1){
            if(i>0){
                int dialogResult = JOptionPane.showConfirmDialog (null, "¿Desea eliminar los registros de la tabla?");
                
                if(dialogResult == JOptionPane.YES_OPTION){
                    textTotal.setValue(new Double(0));
                    textDescuento.setValue(new Double(0));
                    modelo.setRowCount(0);
                }                        
            }
        }else{
            textTotal.setValue(new Double(0));
            textDescuento.setValue(new Double(0));
            modelo.setRowCount(0);
        }
        
        calculaTotalVenta();
    }

    private void initPanelBotones() {
        Producto p;
        JButton btemp;
        
        //Recuperamos la lista de los más vendidos
        masvendidos = Producto.bestSelled();
        
        //Eliminamos los panes mostrados anteriormente, la última venta puede cambiar el orden
        panelPanes.removeAll();
        
        //Recuperamos la lista de panes a mostrar en pantalla
        int size = masvendidos.size();
        
        for(int i = 0; i < arrPanes.length && i < size ; i++){//ciclo para crear, añadir, establecer propiedades a los botones
            //Agregamos cada pan al panel
            p = masvendidos.get(i);
            btemp = new JButton(p.getNombre());
            btemp.setFont(default_font);
            arrPanes[i] = btemp;
            panelPanes.add(arrPanes[i]);
            arrPanes[i].setMargin(new Insets(35, 35, 35, 35));
            
            arrPanes[i].addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    for(int i = 0; i < arrPanes.length ; i++){
                        if (arrPanes[i] == evt.getSource()){
                            
                            pan_selected = masvendidos.get(i);
                            estado = CANTIDAD;
                            actualizaElementosPantalla(estado);
//                            try{
//                                agregarProductoVenta(masvendidos.get(i));
//                                calculaTotalVenta();
                                
                                textBuscar.grabFocus();
                                break;
//                            }catch(ParseException ex){
//                                
//                            }                        
                            
                        }
                    }
                    
                }
            });            
        }//fin ciclo
                
    }

    private void agregarProductoVenta(Producto prod, int cantidad) throws ParseException {
        Producto pOld;        
        JLabel label = new JLabel();        
        JFormattedTextField inputNum;
        ItemVentas item;
//        int nPanes = 0, 
        int consumo = 0, index = -1, stock;
        boolean flag;       
        
        inputNum = new javax.swing.JFormattedTextField();
        inputNum.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        
        
        inputNum.setFont(default_font);
        inputNum.setFocusable(true);
        label.setFont(default_font);
              
//        do{
//            flag = false;
//            
//            try{
//                label.setText("¿Cuántos panes desea agregar?");            
//                nPanes = Integer.parseInt(JOptionPane.showInputDialog(this, label));            
//            }catch(NumberFormatException e){
//                flag = true;
//
//            }      
//        }while(flag);
        

        DefaultTableModel modelo = (DefaultTableModel) tableItems.getModel();

        //Verificamos que haya Stock disponible
        int nRow = modelo.getRowCount();

//        consumo = nPanes;
        consumo = cantidad;
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
            label.setText("No hay suficiente stock de este producto");
            JOptionPane.showMessageDialog(null, label);       
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

    private boolean autenticarUsuario() { 
        JLabel label = new JLabel();
        Usuario user = Usuario.find("Admin");
        JPasswordField pf = new JPasswordField();
        String myPass;
        int okCxl;
        
        pf.setFont(default_font);        
        
        do{      
            okCxl = JOptionPane.showConfirmDialog(null, pf, "Contraseña", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            myPass = String.valueOf(pf.getPassword());
            if (okCxl == JOptionPane.OK_OPTION && PasswordUtils.verifyUserPassword(myPass, user.getPass(), user.getSalt())){                
                return true;
            }else if(okCxl == JOptionPane.CANCEL_OPTION)
                return false;
            else{
                label.setText("Contraseña incorrecta, intente nuevamente");
                JOptionPane.showMessageDialog(this, label);
            }
            
        }while(true);
    }

    private void cobrarVenta() {
        DefaultTableModel modelo = (DefaultTableModel) tableItems.getModel();
        int nRow = modelo.getRowCount(), cantidad;
        Producto p = null;
        List<ItemVentas> items = new ArrayList<>();
        ItemVentas iv = null;
        
        JLabel label = new JLabel();

        label.setFont(default_font);
                
        int i = modelo.getRowCount();
        
        if(i>0){
            //Recuperamos la lista de panes para mandarlos a la BD
            for (int x = 0 ; x < nRow ; x++){
                p = (Producto)modelo.getValueAt(x, 0);
                cantidad = (int)modelo.getValueAt(x, 2);
                iv = new ItemVentas(p, cantidad);
                items.add(iv);
            }
            
            //Si la venta se genera exitosamente procedemos a realizar el cobro
            if(Ventas.generaVenta(((Double)textTotal.getValue()).floatValue(), ((Double)textDescuento.getValue()).floatValue(), items)){
                estado = EFECTIVO;
                
                actualizaElementosPantalla(estado);
                
//                boolean flag;
//                double cambio;
//                do{
//                    flag = true;
//                    try{
//                        label.setText("Efectivo");
//                        
//                        float efectivo = Float.parseFloat(JOptionPane.showInputDialog(this, label));
//                                                                        
//                        cambio = efectivo - (Double)textTotal.getValue();
//                        
//                        if(cambio < 0){          
//                            label.setText("El efectivo es menor al monto total");
//                            JOptionPane.showMessageDialog(this, label);
//                            flag = false;
//                        }else{
//                            label.setText("Cambio $" + cambio);
//                            JOptionPane.showMessageDialog(this, label);
//                        }
//                        
//                    }catch(NumberFormatException e){
//                        flag = false;
//                    }
//                    
//                }while(!flag);
//                
//                //JOptionPane.showMessageDialog(null, "Venta generada");
//                
//                reiniciarVenta(0);
//                initPanelBotones();
                
            }else{
                label.setText("Se presento un problema para generar la venta");
                JOptionPane.showMessageDialog(null, label);
            }
        }else{
            label.setText("No se ha agregado ningun producto");
            JOptionPane.showMessageDialog(null, label);
        }      
    }

    private void removerProducto() {
        JLabel label = new JLabel();        
                
        int i = tableItems.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) tableItems.getModel();
        
        label.setFont(default_font);
        
        if (i >= 0){
            modelo.removeRow(i);
        }else{
            label.setText("No se selecciono ningun registro para eliminar");
            JOptionPane.showMessageDialog(null, label);
        }
        
        calculaTotalVenta();
    }

//    private void cambiaEstado(boolean isVenta) {
//        switch(estado){
//            case VENTA:
//
//                labelAccion.setText(strEfectivo);   
//                
//                if(isVenta)
//                    estado = EFECTIVO;
//                else
//                    estado = CANTIDAD;
//                
//                break;
//            case EFECTIVO:
//
//                labelAccion.setText(strCambio);
//                estado = CAMBIO;
//                break;
//            case CAMBIO:
//
//                labelAccion.setText(strProducto);
//                estado = VENTA;
//                break;
//        }            
//        
//    }

    private void actualizaElementosPantalla(int estado) {
        switch(estado){
            case VENTA:
                labelAccion.setText(strProducto);
                tableItems.setEnabled(true);
                btnRemover.setEnabled(true);
                btn_adddesc.setEnabled(true);
                btn_remdesc.setEnabled(true);
                btnLimpiar.setEnabled(true);
                btnCobrar.setEnabled(true);
                                
                textBuscar.setText("");
                
                initAutoCompleter();
                                
                initPanelBotones();
                
                break;
            case CANTIDAD:
                labelAccion.setText(strCantidad);
                //Deshabilitamos los elementos en pantalla
                tableItems.setEnabled(false);
                btnRemover.setEnabled(false);
                btn_adddesc.setEnabled(false);
                btn_remdesc.setEnabled(false);
                btnLimpiar.setEnabled(false);
                btnCobrar.setEnabled(false);
                
                //Removemos los elementos del buscador
                ac.removeAllItems();
                
                //Removemos todos los panes del panel
                panelPanes.removeAll();

                //Limpiamos el campo de texto
                textBuscar.setText("");
                                
                //Desactivamos el panel de panes
                panelPanes.setEnabled(false);
                
                break;
                
            case EFECTIVO:
                labelAccion.setText(strEfectivo);
                //Deshabilitamos los elementos en pantalla
                tableItems.setEnabled(false);
                btnRemover.setEnabled(false);
                btn_adddesc.setEnabled(false);
                btn_remdesc.setEnabled(false);
                btnLimpiar.setEnabled(false);
                btnCobrar.setEnabled(false);
                
                //Removemos los elementos del buscador
                ac.removeAllItems();
                
                //Removemos todos los panes del panel
                panelPanes.removeAll();

                //Limpiamos el campo de texto
                textBuscar.setText("");
                                
                //Desactivamos el panel de panes
                panelPanes.setEnabled(false);
                
                break;
            case CAMBIO:
                labelAccion.setText(strCambio);
                //Deshabilitamos los elementos en pantalla
                tableItems.setEnabled(false);
                btnRemover.setEnabled(false);
                btn_adddesc.setEnabled(false);
                btn_remdesc.setEnabled(false);
                btnLimpiar.setEnabled(false);
                btnCobrar.setEnabled(false);
                
                //Removemos los elementos del buscador
                ac.removeAllItems();
                
                //Removemos todos los panes del panel
                panelPanes.removeAll();
                
                //Desactivamos el panel de panes
                panelPanes.setEnabled(false);                

//                //Limpiamos el campo de texto
//                textBuscar.setText("");
                textBuscar.setText(Float.toString(calculaCambio()));

                                
                break;
            default: break;
                
        }
    }

    private float calculaCambio() {
        double efectivo, total;
        
        efectivo = Double.parseDouble(textBuscar.getText());
        total = (Double)textTotal.getValue();
//        System.out.println("Efectivo: " + Double.toString(efectivo));
//        System.out.println("Total: " + Double.toString(total));
        return (float) (efectivo - total);
    }

}
