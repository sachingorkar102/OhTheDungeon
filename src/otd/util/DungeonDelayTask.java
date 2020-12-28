/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otd.util;

import forge_sandbox.greymerk.roguelike.dungeon.IDungeon;
import forge_sandbox.greymerk.roguelike.dungeon.settings.ISettings;
import forge_sandbox.greymerk.roguelike.dungeon.tasks.IDungeonTask;
import forge_sandbox.greymerk.roguelike.worldgen.IWorldEditor;
import java.util.Random;

/**
 *
 * @author
 */
public class DungeonDelayTask implements IDungeonTask {
        int max_commit_num = 10000;
//        int count = 0;
	@Override
	public boolean execute(IWorldEditor editor, Random rand, IDungeon dungeon, ISettings settings, int index) {
//            count = count + max_commit_num;
//            Bukkit.getLogger().log(Level.SEVERE, Integer.toString(count));
            return editor.commit(max_commit_num);
//            return true;
	}
}
