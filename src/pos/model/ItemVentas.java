/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.model;

/**
 *
 * @author Darío Jiménez
 */
public class ItemVentas {
    private Producto prod;
    private int cantidad;
    private float importe;
    
    public ItemVentas(){        
    }
    
    public ItemVentas(Producto p, int n){
        prod = p;
        cantidad = n;
        importe = n * prod.getPrecio();
    }

    public Producto getProd() {
        return prod;
    }
//
//    public void setProd(Producto prod) {
//        this.prod = prod;
//    }
    
    public String getNombreProd(){
        return prod.getNombre();
    }
    
    public float getPrecio(){
        return prod.getPrecio();
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        
        if (this.prod != null) {
            this.importe = this.cantidad * prod.getPrecio();
        }
        
    }

    public float getImporte() {
        return importe;
    }
    
}
