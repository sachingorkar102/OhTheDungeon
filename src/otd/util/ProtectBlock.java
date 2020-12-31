/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.util;

import org.bukkit.Material;

/**
 *
 * @author
 */
public class ProtectBlock {
    public static boolean isProtectedType(Material type) {
        return type == Material.SPAWNER || type == Material.CHEST || type == Material.END_PORTAL || type == Material.END_PORTAL_FRAME;
    }
}
