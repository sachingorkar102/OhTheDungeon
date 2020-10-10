/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zhehe.maze;

import zhehe.com.timvisee.dungeonmaze.populator.maze.structure.OasisChunkPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.structure.LibraryRoomPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.structure.GravelPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.structure.EmptyRoom;
import zhehe.com.timvisee.dungeonmaze.populator.maze.structure.MassiveRoomPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.structure.SanctuaryPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.structure.GreatFurnaceRoomPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.structure.WaterWellRoomPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.structure.ArmoryRoomPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.structure.StrutPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.structure.AbandonedDefenceCastleRoomPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.structure.TopTurveRoomPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.structure.EntrancePopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.spawner.BossRoomInsanePopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.spawner.BossRoomHardPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.spawner.BossRoomEasyPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.spawner.CreeperSpawnerRoomPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.spawner.BlazeSpawnerRoomPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.decoration.BrokenWallsPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.decoration.WebPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.decoration.SoulsandPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.decoration.MushroomPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.decoration.TorchPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.decoration.VinePopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.decoration.LavaOutOfWallPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.decoration.MossPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.decoration.IronBarPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.decoration.WaterOutOfWallPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.decoration.ChestPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.decoration.NetherrackPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.decoration.SlabPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.decoration.PumpkinPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.decoration.LadderPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.decoration.SkullPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.decoration.CoalOrePopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.decoration.CrackedStoneBrickPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.decoration.CobblestonePopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.decoration.PoolPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.decoration.LanternPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.MazeLayerBlockPopulator;
import zhehe.com.timvisee.dungeonmaze.populator.maze.MazeRoomBlockPopulator;
import zhehe.util.RandomCollection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author
 */
public class SmoofyDungeon {
    public static List<MazeRoomBlockPopulator> decoration;
    static {
        decoration = new ArrayList<>();
        {
            BrokenWallsPopulator p = new BrokenWallsPopulator();
            decoration.add(p);
        }
        {
            ChestPopulator p = new ChestPopulator();
            decoration.add(p);
        }
        {
            CoalOrePopulator p = new CoalOrePopulator();
            decoration.add(p);
        }
        {
            CobblestonePopulator p = new CobblestonePopulator();
            decoration.add(p);
        }
        {
            CrackedStoneBrickPopulator p = new CrackedStoneBrickPopulator();
            decoration.add(p);
        }
        {
//            ExplosionPopulator p = new ExplosionPopulator();
//            decoration.add(0.1, p);
        }
//        {
//            GravePopulator p = new GravePopulator();
//            decoration.add(p);
//        }
        {
            IronBarPopulator p = new IronBarPopulator();
            decoration.add(p);
        }
        {
            LadderPopulator p = new LadderPopulator();
            decoration.add(p);
        }
        {
            LanternPopulator p = new LanternPopulator();
            decoration.add(p);
        }
        {
            LavaOutOfWallPopulator p = new LavaOutOfWallPopulator();
            decoration.add(p);
        }
        {
            MossPopulator p = new MossPopulator();
            decoration.add(p);
        }
        {
            MushroomPopulator p = new MushroomPopulator();
            decoration.add(p);
        }
        {
            NetherrackPopulator p = new NetherrackPopulator();
            decoration.add(p);
        }
        {
//            OresInGroundPopulator p = new OresInGroundPopulator();
//            decoration.add(0.1, p);
        }
        {
            PoolPopulator p = new PoolPopulator();
            decoration.add(p);
        }
        {
            PumpkinPopulator p = new PumpkinPopulator();
            decoration.add(p);
        }
        {
            SkullPopulator p = new SkullPopulator();
            decoration.add(p);
        }
        {
            SlabPopulator p = new SlabPopulator();
            decoration.add(p);
        }
        {
            SoulsandPopulator p = new SoulsandPopulator();
            decoration.add(p);
        }
        {
            TorchPopulator p = new TorchPopulator();
            decoration.add(p);
        }
        {
            VinePopulator p = new VinePopulator();
            decoration.add(p);
        }
        {
            WaterOutOfWallPopulator p = new WaterOutOfWallPopulator();
            decoration.add(p);
        }
        {
            WebPopulator p = new WebPopulator();
            decoration.add(p);
        }
    }
    public static Map<Integer, RandomCollection<MazeRoomBlockPopulator>> ROOMS;
    static {
        ROOMS = new HashMap<>();
        {
            BlazeSpawnerRoomPopulator p = new BlazeSpawnerRoomPopulator();
            int min = p.getMinimumLayer();
            int max = p.getMaximumLayer();
            for(int i = min; i <= max; i++) {
                if(!ROOMS.containsKey(i)) ROOMS.put(i, new RandomCollection<>());
                RandomCollection<MazeRoomBlockPopulator> c = ROOMS.get(i);
                c.add(0.1, p);
            }
        }
        {
            BossRoomEasyPopulator p = new BossRoomEasyPopulator();
            int min = p.getMinimumLayer();
            int max = p.getMaximumLayer();
            for(int i = min; i <= max; i++) {
                if(!ROOMS.containsKey(i)) ROOMS.put(i, new RandomCollection<>());
                RandomCollection<MazeRoomBlockPopulator> c = ROOMS.get(i);
                c.add(0.1, p);
            }
        }
        {
            CreeperSpawnerRoomPopulator p = new CreeperSpawnerRoomPopulator();
            int min = p.getMinimumLayer();
            int max = p.getMaximumLayer();
            for(int i = min; i <= max; i++) {
                if(!ROOMS.containsKey(i)) ROOMS.put(i, new RandomCollection<>());
                RandomCollection<MazeRoomBlockPopulator> c = ROOMS.get(i);
                c.add(0.1, p);
            }
        }
//        {
//            SilverfishBlockPopulator p = new SilverfishBlockPopulator();
//            int min = p.getMinimumLayer();
//            int max = p.getMaximumLayer();
//            for(int i = min; i <= max; i++) {
//                if(!ROOMS.containsKey(i)) ROOMS.put(i, new RandomCollection<>());
//                RandomCollection<MazeRoomBlockPopulator> c = ROOMS.get(i);
//                c.add(0.1, p);
//            }
//        }
//        {
//            SpawnerPopulator p = new SpawnerPopulator();
//            int min = p.getMinimumLayer();
//            int max = p.getMaximumLayer();
//            for(int i = min; i <= max; i++) {
//                if(!ROOMS.containsKey(i)) ROOMS.put(i, new RandomCollection<>());
//                RandomCollection<MazeRoomBlockPopulator> c = ROOMS.get(i);
//                c.add(0.1, p);
//            }
//        }
        {
            AbandonedDefenceCastleRoomPopulator p = new AbandonedDefenceCastleRoomPopulator();
            int min = p.getMinimumLayer();
            int max = p.getMaximumLayer();
            for(int i = min; i <= max; i++) {
                if(!ROOMS.containsKey(i)) ROOMS.put(i, new RandomCollection<>());
                RandomCollection<MazeRoomBlockPopulator> c = ROOMS.get(i);
                c.add(0.1, p);
            }
        }
        {
            ArmoryRoomPopulator p = new ArmoryRoomPopulator();
            int min = p.getMinimumLayer();
            int max = p.getMaximumLayer();
            for(int i = min; i <= max; i++) {
                if(!ROOMS.containsKey(i)) ROOMS.put(i, new RandomCollection<>());
                RandomCollection<MazeRoomBlockPopulator> c = ROOMS.get(i);
                c.add(0.1, p);
            }
        }
        {
//            FloodedRoomPopulator p = new FloodedRoomPopulator();
//            int min = p.getMinimumLayer();
//            int max = p.getMaximumLayer();
//            for(int i = min; i <= max; i++) {
//                if(!ROOMS.containsKey(i)) ROOMS.put(i, new RandomCollection<>());
//                RandomCollection<MazeRoomBlockPopulator> c = ROOMS.get(i);
//                c.add(0.1, p);
//            }
        }
        {
            GravelPopulator p = new GravelPopulator();
            int min = p.getMinimumLayer();
            int max = p.getMaximumLayer();
            for(int i = min; i <= max; i++) {
                if(!ROOMS.containsKey(i)) ROOMS.put(i, new RandomCollection<>());
                RandomCollection<MazeRoomBlockPopulator> c = ROOMS.get(i);
                c.add(0.1, p);
            }
        }
        {
            GreatFurnaceRoomPopulator p = new GreatFurnaceRoomPopulator();
            int min = p.getMinimumLayer();
            int max = p.getMaximumLayer();
            for(int i = min; i <= max; i++) {
                if(!ROOMS.containsKey(i)) ROOMS.put(i, new RandomCollection<>());
                RandomCollection<MazeRoomBlockPopulator> c = ROOMS.get(i);
                c.add(0.1, p);
            }
        }
        {
//            HighRoomPopulator p = new HighRoomPopulator();
//            int min = p.getMinimumLayer();
//            int max = p.getMaximumLayer();
//            for(int i = min; i <= max; i++) {
//                if(!ROOMS.containsKey(i)) ROOMS.put(i, new RandomCollection<>());
//                RandomCollection<MazeRoomBlockPopulator> c = ROOMS.get(i);
//                c.add(0.1, p);
//            }
        }
        {
            LibraryRoomPopulator p = new LibraryRoomPopulator();
            int min = p.getMinimumLayer();
            int max = p.getMaximumLayer();
            for(int i = min; i <= max; i++) {
                if(!ROOMS.containsKey(i)) ROOMS.put(i, new RandomCollection<>());
                RandomCollection<MazeRoomBlockPopulator> c = ROOMS.get(i);
                c.add(0.1, p);
            }
        }
        {
            MassiveRoomPopulator p = new MassiveRoomPopulator();
            int min = p.getMinimumLayer();
            int max = p.getMaximumLayer();
            for(int i = min; i <= max; i++) {
                if(!ROOMS.containsKey(i)) ROOMS.put(i, new RandomCollection<>());
                RandomCollection<MazeRoomBlockPopulator> c = ROOMS.get(i);
                c.add(0.1, p);
            }
        }
//        {
//            RailPopulator p = new RailPopulator();
//            int min = p.getMinimumLayer();
//            int max = p.getMaximumLayer();
//            for(int i = min; i <= max; i++) {
//                if(!ROOMS.containsKey(i)) ROOMS.put(i, new RandomCollection<>());
//                RandomCollection<MazeRoomBlockPopulator> c = ROOMS.get(i);
//                c.add(0.1, p);
//            }
//        }
//        {
//            RuinsPopulator p = new RuinsPopulator();
//            int min = p.getMinimumLayer();
//            int max = p.getMaximumLayer();
//            for(int i = min; i <= max; i++) {
//                if(!ROOMS.containsKey(i)) ROOMS.put(i, new RandomCollection<>());
//                RandomCollection<MazeRoomBlockPopulator> c = ROOMS.get(i);
//                c.add(0.1, p);
//            }
//        }
        {
            SanctuaryPopulator p = new SanctuaryPopulator();
            int min = p.getMinimumLayer();
            int max = p.getMaximumLayer();
            for(int i = min; i <= max; i++) {
                if(!ROOMS.containsKey(i)) ROOMS.put(i, new RandomCollection<>());
                RandomCollection<MazeRoomBlockPopulator> c = ROOMS.get(i);
                c.add(0.1, p);
            }
        }
//        {
//            SandPopulator p = new SandPopulator();
//            int min = p.getMinimumLayer();
//            int max = p.getMaximumLayer();
//            for(int i = min; i <= max; i++) {
//                if(!ROOMS.containsKey(i)) ROOMS.put(i, new RandomCollection<>());
//                RandomCollection<MazeRoomBlockPopulator> c = ROOMS.get(i);
//                c.add(0.1, p);
//            }
//        }
//        {
//            SpawnChamberPopulator p = new SpawnChamberPopulator();
//            int min = p.getMinimumLayer();
//            int max = p.getMaximumLayer();
//            for(int i = min; i <= max; i++) {
//                if(!ROOMS.containsKey(i)) ROOMS.put(i, new RandomCollection<>());
//                RandomCollection<MazeRoomBlockPopulator> c = ROOMS.get(i);
//                c.add(0.1, p);
//            }
//        }
//        {
//            StairsPopulator p = new StairsPopulator();
//            int min = p.getMinimumLayer();
//            int max = p.getMaximumLayer();
//            for(int i = min; i <= max; i++) {
//                if(!ROOMS.containsKey(i)) ROOMS.put(i, new RandomCollection<>());
//                RandomCollection<MazeRoomBlockPopulator> c = ROOMS.get(i);
//                c.add(0.1, p);
//            }
//        }
        {
            StrutPopulator p = new StrutPopulator();
            int min = p.getMinimumLayer();
            int max = p.getMaximumLayer();
            for(int i = min; i <= max; i++) {
                if(!ROOMS.containsKey(i)) ROOMS.put(i, new RandomCollection<>());
                RandomCollection<MazeRoomBlockPopulator> c = ROOMS.get(i);
                c.add(0.1, p);
            }
        }
        {
            TopTurveRoomPopulator p = new TopTurveRoomPopulator();
            int min = p.getMinimumLayer();
            int max = p.getMaximumLayer();
            for(int i = min; i <= max; i++) {
                if(!ROOMS.containsKey(i)) ROOMS.put(i, new RandomCollection<>());
                RandomCollection<MazeRoomBlockPopulator> c = ROOMS.get(i);
                c.add(0.1, p);
            }
        }
        {
            WaterWellRoomPopulator p = new WaterWellRoomPopulator();
            int min = p.getMinimumLayer();
            int max = p.getMaximumLayer();
            for(int i = min; i <= max; i++) {
                if(!ROOMS.containsKey(i)) ROOMS.put(i, new RandomCollection<>());
                RandomCollection<MazeRoomBlockPopulator> c = ROOMS.get(i);
                c.add(0.1, p);
            }
        }
    }
    public static RandomCollection<MazeLayerBlockPopulator> layer;
    static {
        layer = new RandomCollection<>();
        {
            BossRoomHardPopulator p = new BossRoomHardPopulator();
            layer.add(p.getLayerChance(), p);
        }
        {
            BossRoomInsanePopulator p = new BossRoomInsanePopulator();
            layer.add(p.getLayerChance(), p);
        }
    }
    public static EntrancePopulator entry = new EntrancePopulator();
    public static OasisChunkPopulator oasis = new OasisChunkPopulator();
    public static EmptyRoom empty = new EmptyRoom();
}
