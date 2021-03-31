/* 
 * Copyright (C) 2021 shadow
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
