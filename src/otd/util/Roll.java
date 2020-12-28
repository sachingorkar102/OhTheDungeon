/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.SkullMeta;
import otd.Main;
import otd.listener.MobListener;
import static otd.util.Skull.applyHead;
import otd.config.WorldConfig;

/**
 *
 * @author
 */
public class Roll {
    
    public final static String DICE = "DICE_TAG";

    public static ItemStack getDice() {
        return createHead(
                WorldConfig.wc.diceUUID,
                WorldConfig.wc.diceTexture
        );
    }
    
    public static ItemStack createHead(final String uuid, final String textures) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        headMeta.setDisplayName(I18n.instance.Dice);
        List<String> lores = new ArrayList<>();
        lores.add(I18n.instance.DiceContent);
        lores.add(0, DICE);
        headMeta.setLore(lores);
        headMeta = applyHead(uuid, textures, headMeta);
        head.setItemMeta(headMeta);
        return head;
    }

    
    public static void registerRecipe() {
        ShapedRecipe recipe = new ShapedRecipe( new NamespacedKey(Main.instance, "dice"), getDice() );
        recipe.shape( " X ", "XSX", " X " );
        recipe.setIngredient( 'X', Material.IRON_INGOT );
        recipe.setIngredient( 'S', Material.CRAFTING_TABLE );
        Bukkit.getServer().addRecipe( recipe );
    }
    
    private static Random random = new Random();
    
    public static void roll(Player p) {
        String name = p.getName();
        Long stamp = System.currentTimeMillis() / 1000;
        if(!MobListener.roll_cool_down.containsKey(name)) {
            MobListener.roll_cool_down.put(name, 0L);
        }
        long ts = MobListener.roll_cool_down.get(name);
        if(stamp - ts <= WorldConfig.wc.rollCoolDownInSecond) return;
        int range = WorldConfig.wc.rollRange;
        int r = random.nextInt(100);
        String str = name + "'s roll: " + r + "/100";
        for (Entity entity : p.getNearbyEntities(range, range, range)) {
            if (entity.getType() == EntityType.PLAYER) {
                Player player = (Player) entity;
                player.sendMessage(str);
            }
        }
        p.sendMessage(str);
        MobListener.roll_cool_down.put(name, stamp);
    }
}
