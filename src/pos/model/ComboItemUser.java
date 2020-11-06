/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.model;
/**
 *
 * @author wdjimenez
 */
public class ComboItemUser
{
    private String user;
    private String nombre;
    private String apellido;
    private int valid;

    public ComboItemUser(String user, String nombre, String apellido, int valid)
    {
        this.user = user;
        this.nombre = nombre;
        this.apellido = apellido;
        this.valid = valid;
    }

    @Override
    public String toString()
    {
        return user + " - " + nombre + " " + apellido;
    }

    public String getUser()
    {
        return user;
    }    
    
    public int getValid(){
        return valid;
    }
}
