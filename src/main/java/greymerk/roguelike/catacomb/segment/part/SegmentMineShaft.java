package greymerk.roguelike.catacomb.segment.part;

import greymerk.roguelike.catacomb.CatacombLevel;
import greymerk.roguelike.catacomb.theme.ITheme;
import greymerk.roguelike.worldgen.Cardinal;
import greymerk.roguelike.worldgen.Coord;
import greymerk.roguelike.worldgen.IBlockFactory;
import greymerk.roguelike.worldgen.MetaBlock;
import greymerk.roguelike.worldgen.WorldGenPrimitive;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class SegmentMineShaft extends SegmentBase {
	
	@Override
	protected void genWall(World world, Random rand, CatacombLevel level, Cardinal dir, ITheme theme, int x, int y, int z) {
		
		MetaBlock air = new MetaBlock(Blocks.air);
		
		IBlockFactory wall = theme.getSecondaryWall();
		
		Cardinal[] orth = Cardinal.getOrthogonal(dir);
		
		Coord cursor = new Coord(x, y, z);
		Coord start;
		Coord end;

		start = new Coord(cursor);
		start.add(dir, 2);
		end = new Coord(start);
		start.add(orth[0]);
		end.add(orth[1]);
		end.add(Cardinal.UP, 3);
		WorldGenPrimitive.fillRectSolid(world, rand, start, end, air, true, true);
		
		cursor.add(Cardinal.UP, 3);
		cursor.add(orth[0]);
		WorldGenPrimitive.fillRectSolid(world, rand, start, end, air, true, true);
		
		start = new Coord(x, y, z);
		start.add(dir, 2);
		end = new Coord(start);
		end.add(Cardinal.UP, 3);
		WorldGenPrimitive.fillRectSolid(world, rand, start, end, wall, true, true);
		start = new Coord(end);
		cursor = new Coord(end);
		start.add(orth[0]);
		end.add(orth[1]);
		WorldGenPrimitive.fillRectSolid(world, rand, start, end, wall, true, true);
		
		start = new Coord(cursor);
		end = new Coord(cursor);
		end.add(Cardinal.reverse(dir), 2);
		WorldGenPrimitive.fillRectSolid(world, rand, start, end, wall, true, true);
	}	
}
