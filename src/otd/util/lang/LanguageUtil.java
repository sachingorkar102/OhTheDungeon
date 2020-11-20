/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.util.lang;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import otd.Main;

public class LanguageUtil {
    public final static List<String> LANG;
    static {
        LANG = new ArrayList<>();
        LANG.add("lang_zhcn.json");
    }
    
    public static void init() {
        File path = new File(Main.instance.getDataFolder().getAbsolutePath() + File.separator + "lang");
        path.mkdir();
        for(String lang : LANG) {
            writeLang(lang);
        }
    }
    
    private static void writeLang(String filename) {
        File out = new File(Main.instance.getDataFolder().getAbsolutePath() + File.separator + "lang" + File.separator, 
                filename);
        try(InputStream in = Main.instance.getResource("lang/" + filename);
           OutputStream writer = new BufferedOutputStream(
               new FileOutputStream(out, false))) {
            // Step 3
            byte[] buffer = new byte[1024 * 4];
            int length;
            while((length = in.read(buffer)) >= 0) {
                writer.write(buffer, 0, length);
            }
        } catch(Exception ex) {
        }
    }
}
