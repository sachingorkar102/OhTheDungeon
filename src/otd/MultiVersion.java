/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd;

/**
 *
 * @author
 */
public class MultiVersion {
    public static enum Version {
        V1_16_R3,
        V1_16_R2,
        V1_16_R1,
        V1_15_R1,
        V1_14_R1,
        UNKNOWN,
    };
    public static boolean is116R3() {
        try {
            Class clazz = Class.forName("net.minecraft.server.v1_16_R3.NBTTagCompound");
            return clazz != null;
        } catch (ClassNotFoundException ex) {
            return false;
        }
    }
    public static boolean is116R2() {
        try {
            Class clazz = Class.forName("net.minecraft.server.v1_16_R2.NBTTagCompound");
            return clazz != null;
        } catch (ClassNotFoundException ex) {
            return false;
        }
    }
    public static boolean is116R1() {
        try {
            Class clazz = Class.forName("net.minecraft.server.v1_16_R1.NBTTagCompound");
            return clazz != null;
        } catch (ClassNotFoundException ex) {
            return false;
        }
    }
    public static boolean is115() {
        try {
            Class clazz = Class.forName("net.minecraft.server.v1_15_R1.NBTTagCompound");
            return clazz != null;
        } catch (ClassNotFoundException ex) {
            return false;
        }
    }
    public static boolean is114() {
        try {
            Class clazz = Class.forName("net.minecraft.server.v1_14_R1.NBTTagCompound");
            return clazz != null;
        } catch (ClassNotFoundException ex) {
            return false;
        }
    }
}
