/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import otd.Main;
import org.bukkit.Bukkit;

/**
 *
 * @author
 */
public class AsyncLog {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    private static final String logfile = Main.instance.getDataFolder().toString() + File.separator + "log.txt";

    public static void logMessage(String message)
    {
//        Bukkit.getLogger().log(Level.SEVERE, message);
        Bukkit.getScheduler().runTaskAsynchronously(Main.instance, () -> {
            try (FileWriter writer = new FileWriter(logfile, true)) {
                writer.write(dateFormat.format(new Date()) + " " + message);
                writer.write("\n");
            }
            catch(IOException e)
            {
            }
        });
    }
}
