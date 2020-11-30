/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.util;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.lang.reflect.Field;
import java.util.UUID;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.inventory.meta.SkullMeta;

/**
 *
 * @author shadow
 */
public class Skull {    
    public static SkullMeta applyHead(String uuid, String textures, SkullMeta headMeta) {
        GameProfile profile = new GameProfile(UUID.fromString(uuid), null);
        profile.getProperties().put("textures", new Property("textures", textures));
        Field profileField;
        try {
            profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (NullPointerException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            //e1.printStackTrace();
            Bukkit.getLogger().log(Level.SEVERE, "[Oh The Dungeons] Dice is not working properly");
        }
        
        return headMeta;
    }
}
