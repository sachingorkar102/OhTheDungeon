/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zhehe.util.config;

import java.util.List;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author
 */
public class RoguelikeLootNode extends LootNode {
    public final int level;
    public int weight;
    public boolean each;
    public RoguelikeLootNode(ItemStack item, int weight, int max, int level, boolean each) {
        super(item, 1.0, max, max);
        this.weight = weight;
        this.level = level;
        this.each = each;
    }
}
