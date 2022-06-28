/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.view;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pos.model.Producto;
import pos.model.ItemVentas;
import pos.model.Usuario;
import pos.model.Ventas;
import pos.util.Config;
import pos.util.PrintTicket;

/**
 *
 * @author Darío Jiménez
 */
public class VentasView extends javax.swing.JFrame {

    private TextAutoCompleter ac;
    private Font default_font = new Font("Roboto", Font.BOLD, 18);

    private static final String strProducto = "Producto";
    private static final String strEfectivo = "Efectivo";
    private static final String strCambio = "Cambio";
    private static final String strCantidad = "Cantidad";

    //Estados del campo de captura
    private static final int VENTA = 0;
    private static final int EFECTIVO = 1;
    private static final int CAMBIO = 2;
    private static final int CANTIDAD = 3;

    private Producto pan_selected;

    private Usuario sesion;

    private int estado; //EN espera de producto

//    private String colorContent = "#DFDCE3";
//    private String colorElement = "#85AFC9"; //"#D2D904";
    JButton[] arrPanes = new JButton[5];//arreglo de botones

    List<Producto> masvendidos;

    /**
     * Creates new form PanaderiaTere
     */
    public VentasView(Usuario s) {
        sesion = s;

        initComponents();
        initAutoCompleter();
        initProductsTable();
        initPanelBotones();

        labelUsuario.setText(sesion.getNombre() + " " + sesion.getApellido());

        //getContentPane().setBackground(new java.awt.Color(242,242,242));
        getContentPane().setBackground(Color.decode(Config.ColorContent));

        if (!sesion.isAdmin()) {
            menuItemNuevo.setEnabled(false);
            menuItemEditar.setEnabled(false);
            menuItemEliminar.setEnabled(false);
            menuItemRepVentas.setEnabled(false);
            menuItemCUser.setEnabled(false);
            menuItemModUser.setEnabled(false);
            menuItemRepMov.setEnabled(false);
            menuItemDesactivar.setEnabled(false);
        }

        panelPanes.setBackground(Color.decode(Config.ColorContent));

        btnRemover.setBackground(Color.decode(Config.ColorElement));
        btnRemover.setForeground(Color.decode(Config.ColorText));
        btn_adddesc.setBackground(Color.decode(Config.ColorElement));
        btn_adddesc.setForeground(Color.decode(Config.ColorText));
        btn_remdesc.setBackground(Color.decode(Config.ColorElement));
        btn_remdesc.setForeground(Color.decode(Config.ColorText));
        btnCobrar.setBackground(Color.decode(Config.ColorElement));
        btnCobrar.setForeground(Color.decode(Config.ColorText));
        btnLimpiar.setBackground(Color.decode(Config.ColorElement));
        btnLimpiar.setForeground(Color.decode(Config.ColorText));

        textTotal.setValue(new Double(0));
        textDescuento.setValue(new Double(0));

        tableItems.getTableHeader().setFont(default_font);
        tableItems.setFont(default_font);

        calculaTotalVenta();

        setLocationRelativeTo(null);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                new PrincipalView().setVisible(true);
            }
        });


        estado = VENTA;

    }

    private void initAutoCompleter() {
        ac = new TextAutoCompleter(textBuscar, Producto.all().toArray());
        ac.setMode(0);

//        Producto.all().forEach((producto) -> {
//            ac.addItem(producto);
//        });
    }

    public void refreshAutoCompleter() {
        //Refresca la lista de elementos que aparecen en el campo de texto
        
        this.ac.removeAllItems();

        Producto.all().forEach((producto) -> {
            //System.out.println("Producto " + producto.getNombre() + " Precio " + producto.getPrecio());
            this.ac.addItem(producto);
        });
    }

    private void initProductsTable() {

    }

    private void calculaTotalVenta() {
        DefaultTableModel dtm = (DefaultTableModel) tableItems.getModel();
        int nRow = dtm.getRowCount();
        float total = 0, descuento = 0;

        try {
            for (int row = 0; row < tableItems.getRowCount(); row++) {
                int rowHeight = tableItems.getRowHeight();

                for (int column = 0; column < tableItems.getColumnCount(); column++) {
                    Component comp = tableItems.prepareRenderer(tableItems.getCellRenderer(row, column), row, column);
                    rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
                }

                tableItems.setRowHeight(row, rowHeight);
            }
        } catch (ClassCastException e) {
        }

        for (int i = 0; i < nRow; i++) 
            total += (float) dtm.getValueAt(i, 3);
        
        //Restamos el valor del descuento
        descuento = ((Double) textDescuento.getValue()).floatValue();
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
        labelUsuario = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        menuItemNuevo = new javax.swing.JMenuItem();
        menuItemEditar = new javax.swing.JMenuItem();
        menuItemDesactivar = new javax.swing.JMenuItem();
        menuItemEliminar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuItemRepVentas = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        menuItemRepMov = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        menuItemCUser = new javax.swing.JMenuItem();
        menuItemModUser = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Panaderia Tere");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocationByPlatform(true);

        textBuscar.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        textBuscar.setToolTipText("Introduzca el producto aquí");
        textBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBuscarActionPerformed(evt);
            }
        });

        tableItems.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
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
        btnCobrar.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnCobrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pos/iconos/currency_dollar_sign.png"))); // NOI18N
        btnCobrar.setText("Cobrar");
        btnCobrar.setToolTipText("Genera la venta");
        btnCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrarActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(114, 151, 166));
        btnLimpiar.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pos/iconos/reload.png"))); // NOI18N
        btnLimpiar.setText("Reiniciar venta");
        btnLimpiar.setToolTipText("Borra la lista de productos");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
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
        btnRemover.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pos/iconos/delete.png"))); // NOI18N
        btnRemover.setText("Producto");
        btnRemover.setToolTipText("Remueve el producto seleccionado");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        labelAccion.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        labelAccion.setText("Producto");

        textDescuento.setEditable(false);
        textDescuento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        textDescuento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel3.setText("Descuento");

        btn_adddesc.setBackground(new java.awt.Color(114, 151, 166));
        btn_adddesc.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btn_adddesc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pos/iconos/add.png"))); // NOI18N
        btn_adddesc.setText("Descuento");
        btn_adddesc.setToolTipText("Agregar descuento a la venta");
        btn_adddesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_adddescActionPerformed(evt);
            }
        });

        btn_remdesc.setBackground(new java.awt.Color(114, 151, 166));
        btn_remdesc.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btn_remdesc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pos/iconos/delete.png"))); // NOI18N
        btn_remdesc.setText("Descuento");
        btn_remdesc.setToolTipText("Eliminar descuento de la venta");
        btn_remdesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_remdescActionPerformed(evt);
            }
        });

        labelUsuario.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jMenu2.setText("Productos");
        jMenu2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N

        menuItemNuevo.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        menuItemNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pos/iconos/document.png"))); // NOI18N
        menuItemNuevo.setText("Nuevo");
        menuItemNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemNuevoActionPerformed(evt);
            }
        });
        jMenu2.add(menuItemNuevo);

        menuItemEditar.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        menuItemEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pos/iconos/edit.png"))); // NOI18N
        menuItemEditar.setText("Editar");
        menuItemEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemEditarActionPerformed(evt);
            }
        });
        jMenu2.add(menuItemEditar);

        menuItemDesactivar.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        menuItemDesactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pos/iconos/edit.png"))); // NOI18N
        menuItemDesactivar.setText("Desactivar");
        menuItemDesactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemDesactivarActionPerformed(evt);
            }
        });
        jMenu2.add(menuItemDesactivar);

        menuItemEliminar.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        menuItemEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pos/iconos/trash_can.png"))); // NOI18N
        menuItemEliminar.setText("Eliminar");
        menuItemEliminar.setToolTipText("");
        menuItemEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemEliminarActionPerformed(evt);
            }
        });
        jMenu2.add(menuItemEliminar);
        jMenu2.add(jSeparator1);

        jMenuItem7.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pos/iconos/dispatch.png"))); // NOI18N
        jMenuItem7.setText("Entrada de mercancias");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Reportes");
        jMenu3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N

        menuItemRepVentas.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        menuItemRepVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pos/iconos/money_bag_dollar.png"))); // NOI18N
        menuItemRepVentas.setText("Ventas");
        menuItemRepVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRepVentasActionPerformed(evt);
            }
        });
        jMenu3.add(menuItemRepVentas);

        jMenuItem6.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pos/iconos/list.png"))); // NOI18N
        jMenuItem6.setText("Stock");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        menuItemRepMov.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        menuItemRepMov.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pos/iconos/dispatch.png"))); // NOI18N
        menuItemRepMov.setText("Movimientos");
        menuItemRepMov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRepMovActionPerformed(evt);
            }
        });
        jMenu3.add(menuItemRepMov);

        jMenuBar1.add(jMenu3);
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Usuarios");
        jMenu5.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N

        menuItemCUser.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        menuItemCUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pos/iconos/user_16.png"))); // NOI18N
        menuItemCUser.setText("Crear usuario");
        menuItemCUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCUserActionPerformed(evt);
            }
        });
        jMenu5.add(menuItemCUser);

        menuItemModUser.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        menuItemModUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pos/iconos/edit.png"))); // NOI18N
        menuItemModUser.setText("Modificar usuarios");
        menuItemModUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemModUserActionPerformed(evt);
            }
        });
        jMenu5.add(menuItemModUser);

        jMenuItem4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pos/iconos/password.png"))); // NOI18N
        jMenuItem4.setText("Cambiar contraseña");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem4);

        jMenuItem8.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pos/iconos/door.png"))); // NOI18N
        jMenuItem8.setText("Cerrar sesión");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem8);

        jMenuBar1.add(jMenu5);

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
                        .addComponent(labelAccion)
                        .addGap(18, 18, 18)
                        .addComponent(textBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelUsuario))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRemover)
                                .addGap(18, 18, 18)
                                .addComponent(btn_adddesc)
                                .addGap(18, 18, 18)
                                .addComponent(btn_remdesc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 394, Short.MAX_VALUE)
                                .addComponent(btnLimpiar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(textDescuento)
                                .addComponent(textTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                            .addComponent(btnCobrar, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAccion)
                    .addComponent(textBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUsuario))
                .addGap(18, 18, 18)
                .addComponent(panelPanes, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
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

    private void menuItemNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemNuevoActionPerformed

//        boolean valido = autenticarUsuario();
//        
//        if (valido) {
        ProductosView vProd = new ProductosView(this, true);
        vProd.setVisible(true);

        vProd.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("jdialog window closed event received Close 1");

                refreshAutoCompleter();

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("jdialog window closing event received Close 2");
            }
        });
//        }                              
    }//GEN-LAST:event_menuItemNuevoActionPerformed

    private void textBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBuscarActionPerformed
        
        int num_panes;
        double efectivo;

        switch (estado) {
            case VENTA:
                pan_selected = (Producto) ac.getItemSelected();

                if (pan_selected != null) {
                    estado = CANTIDAD;
                }

                break;
            case CANTIDAD:
                //recuperamos la cantidad de panes     

                try {
                    num_panes = Integer.parseInt(textBuscar.getText());

                    agregarProductoVenta(pan_selected, num_panes);

                    calculaTotalVenta();

                    //Liberamos el pan agregado
                    pan_selected = null;
                    //Cambiamos el estatus de la pantalla
                    estado = VENTA;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Solo se aceptan valores numericos", "Mensaje", JOptionPane.ERROR_MESSAGE);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(this, "Se presento un problema para agregar el producto", "Mensaje", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case EFECTIVO:
                try {
                    efectivo = Float.parseFloat(textBuscar.getText());

                    if (efectivo >= (Double) textTotal.getValue()) {
                        estado = CAMBIO;
                    } else {
                        JOptionPane.showMessageDialog(this, "El efectivo es menor al total de la venta", "Mensaje", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Solo se aceptan valores numericos", "Mensaje", JOptionPane.ERROR_MESSAGE);
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
        Stock stock = new Stock(this, true, sesion);
        stock.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        RepStock rep = new RepStock();
        rep.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void menuItemRepVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRepVentasActionPerformed
//        boolean valido = autenticarUsuario();
//        
//        if (valido) {
        RepVentas rep = new RepVentas();
        rep.setVisible(true);
//        }
    }//GEN-LAST:event_menuItemRepVentasActionPerformed

    private void menuItemEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemEditarActionPerformed

        ActProducto actualizar = new ActProducto(this, true);
        actualizar.setVisible(true);

        actualizar.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                System.out.println("jdialog window closed event received Close 1");

                refreshAutoCompleter();

            }

            public void windowClosing(WindowEvent e) {
                System.out.println("jdialog window closing event received Close 2");
            }
        });

    }//GEN-LAST:event_menuItemEditarActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
//        boolean valido = autenticarUsuario();
//        
//        if (valido) {
        ActPass nuevopass = new ActPass(this, true, sesion);
        nuevopass.setVisible(true);
//        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void btn_adddescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_adddescActionPerformed
        float descuento = 0;
        JLabel label = new JLabel();

        label.setFont(default_font);

        boolean valido = autenticarUsuario();

        if (valido) {
            boolean flag;
            do {
                flag = true;
                try {
                    label.setText("Descuento");
                    String input = JOptionPane.showInputDialog(this, label);
                    if (input != null) {
                        descuento = Float.parseFloat(input);

                        if (descuento > (Double) textTotal.getValue()) {
                            label.setText("El descuento es mayor al importe total");
                            JOptionPane.showMessageDialog(this, label);
                            flag = false;
                        }
                    } else {
                        flag = true;
                    }
                } catch (NumberFormatException e) {
                    flag = false;
                }

            } while (!flag);

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

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        new PrincipalView().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void menuItemEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuItemEliminarActionPerformed

    private void menuItemCUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCUserActionPerformed
        CrearUsuarioView vUser = new CrearUsuarioView(this, true);//Modificar
        vUser.setVisible(true);
    }//GEN-LAST:event_menuItemCUserActionPerformed

    private void menuItemModUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemModUserActionPerformed
        ActUsuarios vUser = new ActUsuarios(this, true);//Visualizar
        vUser.setVisible(true);

    }//GEN-LAST:event_menuItemModUserActionPerformed

    private void menuItemRepMovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRepMovActionPerformed
        RepMovimientosView rep = new RepMovimientosView();
        rep.setVisible(true);
    }//GEN-LAST:event_menuItemRepMovActionPerformed

    private void menuItemDesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemDesactivarActionPerformed
        
        DesactivarProductos desProd = new DesactivarProductos(this, true);
        desProd.setVisible(true);

        desProd.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                System.out.println("jdialog window closed event received Close 1");

                refreshAutoCompleter();
                initPanelBotones();

            }

            public void windowClosing(WindowEvent e) {
                System.out.println("jdialog window closing event received Close 2");
            }
        });
    }//GEN-LAST:event_menuItemDesactivarActionPerformed

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
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel labelAccion;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JMenuItem menuItemCUser;
    private javax.swing.JMenuItem menuItemDesactivar;
    private javax.swing.JMenuItem menuItemEditar;
    private javax.swing.JMenuItem menuItemEliminar;
    private javax.swing.JMenuItem menuItemModUser;
    private javax.swing.JMenuItem menuItemNuevo;
    private javax.swing.JMenuItem menuItemRepMov;
    private javax.swing.JMenuItem menuItemRepVentas;
    private javax.swing.JPanel panelPanes;
    public javax.swing.JTable tableItems;
    public javax.swing.JTextField textBuscar;
    private javax.swing.JFormattedTextField textDescuento;
    private javax.swing.JFormattedTextField textTotal;
    // End of variables declaration//GEN-END:variables

    private void reiniciarVenta(int confirm) {
        DefaultTableModel modelo = (DefaultTableModel) tableItems.getModel();

        int i = modelo.getRowCount();

        if (confirm == 1) {
            if (i > 0) {
                int dialogResult = JOptionPane.showConfirmDialog(null, "¿Desea eliminar todos los productos de esta venta?");

                if (dialogResult == JOptionPane.YES_OPTION) {
                    textTotal.setValue(new Double(0));
                    textDescuento.setValue(new Double(0));
                    modelo.setRowCount(0);
                }
            }
        } else {
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
        panelPanes.repaint();

        //Recuperamos la lista de panes a mostrar en pantalla
        int size = masvendidos.size();

        for (int i = 0; i < arrPanes.length && i < size; i++) {//ciclo para crear, añadir, establecer propiedades a los botones
            //Agregamos cada pan al panel
            p = masvendidos.get(i);
            if (p != null) {                            
                btemp = new JButton(p.getNombre());
                btemp.setFont(default_font);//(default_font);
                btemp.setForeground(Color.decode(Config.ColorText));
                btemp.setBackground(Color.decode(Config.ColorElement));
                btemp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pos/iconos/bread_32.png")));


                arrPanes[i] = btemp;
                panelPanes.add(arrPanes[i]);
                arrPanes[i].setMargin(new Insets(20, 20, 20, 20));

                arrPanes[i].addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        for (int i = 0; i < arrPanes.length; i++) {
                            if (arrPanes[i] == evt.getSource()) {
                                pan_selected = masvendidos.get(i);
                                estado = CANTIDAD;
                                actualizaElementosPantalla(estado);
                                textBuscar.grabFocus();
                                break;

                            }
                        }

                    }
                });
            }
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

        DefaultTableModel modelo = (DefaultTableModel) tableItems.getModel();

        //Verificamos que haya Stock disponible
        int nRow = modelo.getRowCount();

        consumo = cantidad;
        for (int i = 0; i < nRow; i++) {
            pOld = (Producto) modelo.getValueAt(i, 0);

//            System.out.println("Producto anterior " + pOld.getId() + " " + pOld.getNombre());
//            System.out.println("Producto nuevo " + prod.getId() + " " + prod.getNombre());
            if (pOld.getId() == prod.getId()) {//Se trata del mismo producto, incrementamos el stock
                index = i;
                consumo += (int) modelo.getValueAt(i, 2);
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

        if (index >= 0) {//Actualizamos un elemento ya existente

            modelo.setValueAt(consumo, index, 2);

            modelo.setValueAt(consumo * prod.getPrecio(), index, 3);

        } else {//Nuevo elemento                 

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
//        JLabel label = new JLabel();
        //Usuario user = Usuario.find("Admin");

        AutenticarAdminView autenticar = new AutenticarAdminView(this, true);

        autenticar.setVisible(true);

        if (autenticar.isAdmin()) {
            return true;
        } else {
            return false;
        }

    }

    private void cobrarVenta() {
        boolean validEntry = false;
        double importePago = 0, cambio = 0;
        JLabel labelImportePagado = new JLabel("Ingrese el importe con el que se pago");
        JLabel labelErrorVentaI = new JLabel("El total de la venta es mayor a la cantidad ingresada, intente nuevamente");
        JLabel labelErrorVentaII = new JLabel("Se presento un problema para generar la venta");
        JLabel labelErrorVentaIII = new JLabel("No se ha agregado ningun producto");
        JLabel labelCambio = null;
        
        DefaultTableModel modelo = (DefaultTableModel) tableItems.getModel();                        
        int nRow = modelo.getRowCount(), cantidad;
        int nticket;
        Producto p = null;
        List<ItemVentas> items = new ArrayList<>();
        ItemVentas iv = null;

        JLabel label = new JLabel();

        label.setFont(default_font);

        if (nRow > 0) { //Si la cantidad de elementos es mayor a 0
            //Recuperamos la lista de panes para mandarlos a la BD
            for (int x = 0; x < nRow; x++) {
                p = (Producto) modelo.getValueAt(x, 0);
                cantidad = (int) modelo.getValueAt(x, 2);
                iv = new ItemVentas(p, cantidad);
                items.add(iv);
            }

            //Si la venta se genera exitosamente procedemos a realizar el cobro
            nticket = Ventas.generaVenta(((Double) textTotal.getValue()).floatValue(), ((Double) textDescuento.getValue()).floatValue(), sesion.getUser(), items);
            if ( nticket > 0) {
                //estado = EFECTIVO;
                //actualizaElementosPantalla(estado);                                
                
                labelImportePagado.setFont(default_font);
                labelErrorVentaI.setFont(default_font);
                
                do{
                    try{
                        importePago = Double.parseDouble(JOptionPane.showInputDialog(null, labelImportePagado));
                        
                        if (importePago < (Double) textTotal.getValue())
                            JOptionPane.showMessageDialog(this, labelErrorVentaI, "Mensaje", JOptionPane.ERROR_MESSAGE);
                        else                        
                            validEntry = true;
                    }catch(HeadlessException | NumberFormatException e){
                        
                    }catch(NullPointerException e){                        
                        break;
                    }
                    
                }while(!validEntry);
                
                if (validEntry) {                                       
                    cambio = importePago - (Double) textTotal.getValue();
                    
                    labelCambio = new JLabel("Cambio: " + cambio);
                    labelCambio.setFont(default_font);
                    
                    JOptionPane.showMessageDialog(this, labelCambio, "Cambio", JOptionPane.INFORMATION_MESSAGE);
                    //mandamos a imprimir el ticket
                    labelCambio = new JLabel("¿Desea reimprimir el ticket?");
                    labelCambio.setFont(default_font);
                    boolean reimprimir = true;
                    
                    do{
                        imprimirTicket(nticket);
                        
                        if (!(JOptionPane.YES_OPTION == ( JOptionPane.showConfirmDialog(this, labelCambio))))
                            reimprimir = false;                        
                    }while(reimprimir);
                
                    reiniciarVenta(0);
                    estado = VENTA;      
                    textBuscar.requestFocus();
                }                                

            } else {
                labelErrorVentaII.setFont(default_font);
                                                
                JOptionPane.showMessageDialog(null, labelErrorVentaII);
            }
        } else {
            labelErrorVentaIII.setFont(default_font);            
            JOptionPane.showMessageDialog(null, labelErrorVentaIII);
        }
    }

    private void removerProducto() {
        JLabel label = new JLabel();

        int i = tableItems.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) tableItems.getModel();

        label.setFont(default_font);

        if (i >= 0) {
            modelo.removeRow(i);
        } else {
            label.setText("No se selecciono ningun registro para eliminar");
            JOptionPane.showMessageDialog(null, label);
        }

        calculaTotalVenta();
    }

    private void actualizaElementosPantalla(int estado) {
        switch (estado) {
            case VENTA:
                labelAccion.setText(strProducto);
                tableItems.setEnabled(true);
                btnRemover.setEnabled(true);
                btn_adddesc.setEnabled(true);
                btn_remdesc.setEnabled(true);
                btnLimpiar.setEnabled(true);
                btnCobrar.setEnabled(true);
                textBuscar.setEditable(true);

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
                textBuscar.setEditable(true);

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
                textBuscar.setEditable(true);
                

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
                textBuscar.setEditable(false);

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
            default:
                break;

        }
    }

    private float calculaCambio() {
        double efectivo, total;

        efectivo = Double.parseDouble(textBuscar.getText());
        total = (Double) textTotal.getValue();
//        System.out.println("Efectivo: " + Double.toString(efectivo));
//        System.out.println("Total: " + Double.toString(total));
        return (float) (efectivo - total);
    }
    
    private void imprimirTicket(int ticket){
        double total = 0;        
        JLabel label = null;
        
        List<Ventas> ticketItems = Ventas.getTicketInfo(ticket);
        
        if (ticketItems == null){
            label = new JLabel("No se encontro información del ticket");
            label.setFont(default_font);
            JOptionPane.showMessageDialog(null, label);
            return;
        }
        
        
        PrintTicket p = new PrintTicket();        

        p.resetAll();
        p.initialize();        
        //p.feedBack((byte) 2);
        //p.color(0);
        p.emphasized(true);
        p.alignCenter();
        p.setText("Panaderia D' Alejandra");
        p.emphasized(false);
        p.newLine();
        p.setText("Galena 1 esq. martires");
        p.newLine();
        p.setText("de Tacubaya");
        p.newLine();
        p.setText("Primera Seccion");
        p.newLine();
        p.setText("Tlacolula de Matamoros");
        p.newLine();
        p.addLineSeperator();    
        p.alignLeft();
        p.newLine();
        //4 + 20 + 8
        p.setText("Cant. Producto        Importe");
        p.newLine();
        p.addLineSeperator();
        
        int MAX_NOMBRE = 20;
        int MAX_CANTIDAD = 4;
        int MAX_IMPORTE = 8;
        char[] nombre;// = new char[20];
        char[] cantidad;// = new char[4];
        char[] importe;// = new char[8];
        String linea = "";
        
        for( Ventas v : ticketItems){
            nombre = v.getNombre().toCharArray();
            cantidad = Integer.toString(v.getCantidad()).toCharArray();
            importe = Double.toString(v.getImporte()).toCharArray();

            //p.setText(cantidad);// + nombre.toString() + importe.toString());
            
            for(int i = 0; i < MAX_CANTIDAD; i++){                
                if(i < cantidad.length)
                    linea += cantidad[i];
                else
                    linea += " ";
            }
            
            for(int i = 0; i < MAX_NOMBRE; i++)
                if(i < nombre.length)
                    linea += nombre[i];
                else
                    linea += " ";
            
            for(int i = 0; i < MAX_IMPORTE; i++)
                if(i < importe.length)
                    linea += importe[i];
                else
                    linea += " ";
            
            p.setText(linea);
            
            linea = "";
                
            p.newLine();            
            
            total += v.getImporte();
        }
        
//        p.setText("1" + "\t" + "Capricho" + "\t\t" + "10.00");
//        p.newLine();
//        p.setText("2" + "\t" + "Cazuela" + "\t\t" + "20.00");
//        p.newLine();
//        p.setText("10" + "\t" + "Cuernos" + "\t\t" + "30.00");        
//        p.newLine();
        p.newLine();
        p.setText("Total: " + total);        
        p.newLine();        
        p.feed((byte) 3);
        //p.finit();

        PrintTicket.feedPrinter(p.finalCommandSet().getBytes());                                
    }

}
