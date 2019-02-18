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
public class ComboItem
{
    private String key;
    private int idProd;

    public ComboItem(String key, int id)
    {
        this.key = key;
        this.idProd = id;
    }

    @Override
    public String toString()
    {
        return key;
    }

    public String getKey()
    {
        return key;
    }

    public int getIdProd()
    {
        return idProd;
    }
}
