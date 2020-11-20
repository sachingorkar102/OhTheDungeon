package otd.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import otd.Main;

public class ResourceExport {
    public final static void export(String jar_file, File out) {
        try(InputStream in = Main.instance.getResource(jar_file);
           OutputStream writer = new BufferedOutputStream(
               new FileOutputStream(out, false))) {
            // Step 3
            byte[] buffer = new byte[1024 * 4];
            int length;
            while((length = in.read(buffer)) >= 0) {
                writer.write(buffer, 0, length);
            }
        } catch(Exception ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Exporting {0} error...", jar_file);
        }
    }
}
