/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.util;

import java.awt.Color;
import javax.swing.Icon;
import jiconfont.IconCode;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
/**
 *
 * @author wdjimenez
 */
public class IconUtil {

    public IconUtil() {
    }
    
    
    
    public static Icon cambiarIcono(IconCode icono) {

        IconFontSwing.register(FontAwesome.getIconFont());

        Icon icon = IconFontSwing.buildIcon(icono, 14, new Color(255, 255, 255));

        return icon;

    }

    public static Icon cambiarIcono(IconCode icono, Color c) {

        IconFontSwing.register(FontAwesome.getIconFont());

        Icon icon = IconFontSwing.buildIcon(icono, 14, c);
        return icon;

    }
    
      public static Icon cambiarIcono(IconCode icono, Color c, int talla) {

        IconFontSwing.register(FontAwesome.getIconFont());

        Icon icon = IconFontSwing.buildIcon(icono, talla, c);
        return icon;

    }
    
}
