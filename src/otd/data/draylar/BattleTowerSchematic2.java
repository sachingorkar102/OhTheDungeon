/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.data.draylar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Type;

/**
 *
 * @author
 */
public class BattleTowerSchematic2 {
    private static class BlockNode {
        public int[] pos;
        public int state;
        public Map<Object, Object> nbt;
    }
    private static class PaletteNode {
        public Map<String, String> Properties = new HashMap<>();
        public String Name;
    }
    private static class Json {
        public int[] size;
        public String[] entities;
        public BlockNode[] blocks;
        public PaletteNode[] palette;
        public String[] blockData;
        public int DataVersion;
    }
    
    private static void handleJson(Json json) {
        json.blockData = new String[json.palette.length];
        for(int i = 0; i < json.palette.length; i++) {
            PaletteNode pn = json.palette[i];
            json.blockData[i] = pn.Name;
            if(pn.Properties != null && pn.Properties.size() > 0) {
                StringBuilder sb = new StringBuilder();
                for(Map.Entry<String, String> entry : pn.Properties.entrySet()) {
                    sb.append(",");
                    sb.append(entry.getKey()).append("=").append(entry.getValue());
                }
                sb.deleteCharAt(0);
                json.blockData[i] = pn.Name + "[" + sb.toString() + "]";
            }
        }
    }
    
    private Json json;
    
    public BattleTowerSchematic2(InputStream stream) {
        try {
            load(stream);
        } catch(IOException e) {
            json = null;
        }
    }
    
    public boolean isValid() {
        return json != null;
    }
    
    private void load(InputStream stream) throws IOException {
        InputStreamReader isr = new InputStreamReader(stream);
        BufferedReader reader = new BufferedReader(isr);
        String line;
        
        StringBuilder stringBuilder = new StringBuilder();
        
        while( (line=reader.readLine())!=null) {
            stringBuilder.append(line);
        }
        reader.close();
        isr.close();
        
        json = (new Gson()).fromJson(stringBuilder.toString(), Json.class);
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader("/tmp/chrome/tower_normal_blacksmith.json"));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
        }
        Json json = (new Gson()).fromJson(stringBuilder.toString(), Json.class);
        handleJson(json);
        
        for(BlockNode bn : json.blocks) {
            if(bn.nbt != null) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(Double.class, (JsonSerializer<Double>) (Double src, Type typeOfSrc, JsonSerializationContext context) -> {
                    Integer value = (int)Math.round(src);
                    return new JsonPrimitive(value);
                });

                Gson gs = gsonBuilder.create();
                System.out.println(gs.toJson(bn.nbt));
            }
        }
    }
}
