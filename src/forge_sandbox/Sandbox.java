/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forge_sandbox;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import otd.Main;

/**
 *
 * @author
 */
public class Sandbox {
    public final static List<String> mods;
    static {
        mods = new ArrayList<>();
        mods.add("roguelike");
        mods.add("doomlike");
        mods.add("dungeons2");
    }
    public static void mkdir() {
        File config = Main.instance.getDataFolder();
        if(!config.exists()) config.mkdir();
        
        File sandbox = new File(config.toString() + File.separator + "forge_sandbox");
        if(!sandbox.exists()) sandbox.mkdir();
        
        int len = mods.size();
        for(int i = 0; i < len; i++) {
            File sub = new File(config.toString() + File.separator + "forge_sandbox" + File.separator + mods.get(i));
            if(!sub.exists()) sub.mkdir();
        }
    }
}
