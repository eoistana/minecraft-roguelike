package greymerk.roguelike.catacomb.segment.part;

import greymerk.roguelike.catacomb.CatacombLevel;
import greymerk.roguelike.catacomb.segment.IAlcove;
import greymerk.roguelike.catacomb.segment.alcove.SilverfishNest;
import greymerk.roguelike.catacomb.theme.ITheme;
import greymerk.roguelike.worldgen.Cardinal;
import greymerk.roguelike.worldgen.Coord;
import greymerk.roguelike.worldgen.MetaBlock;
import greymerk.roguelike.worldgen.WorldGenPrimitive;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class SegmentSilverfish extends SegmentBase {
	
	@Override
	protected void genWall(World world, Random rand, CatacombLevel level, Cardinal dir, ITheme theme, int x, int y, int z) {
		
		MetaBlock air = new MetaBlock(Blocks.air);
		MetaBlock stair = theme.getSecondaryStair();
		
		Coord cursor = new Coord(x, y, z);
		Coord start;
		Coord end;
		
		Cardinal[] orth = Cardinal.getOrthogonal(dir);
		
		cursor.add(dir, 2);
		start = new Coord(cursor);
		start.add(orth[0], 1);
		end = new Coord(cursor);
		end.add(orth[1], 1);
		end.add(Cardinal.UP, 2);
		WorldGenPrimitive.fillRectSolid(world, rand, start, end, air, true, true);
		
		// front wall
		start.add(dir, 1);
		end.add(dir, 1);
		WorldGenPrimitive.fillRectSolid(world, rand, start, end, theme.getPrimaryWall(), false, true);

		// stairs
		cursor.add(Cardinal.UP, 2);
		for(Cardinal d : orth){
			Coord c = new Coord(cursor);
			c.add(d, 1);
			WorldGenPrimitive.blockOrientation(stair, Cardinal.reverse(d), true);
			WorldGenPrimitive.setBlock(world, rand, c, stair, true, true);
		}
		
		stair = theme.getPrimaryStair();
		
		cursor = new Coord(x, y, z);
		cursor.add(dir, 3);
		WorldGenPrimitive.blockOrientation(stair, Cardinal.reverse(dir), false);
		stair.setBlock(world, cursor);
		cursor.add(Cardinal.UP);
		air.setBlock(world, cursor);
		cursor.add(Cardinal.UP);
		WorldGenPrimitive.blockOrientation(stair, Cardinal.reverse(dir), true);
		stair.setBlock(world, cursor);
		
		IAlcove nest = new SilverfishNest();
		if(nest.isValidLocation(world, x, y, z, dir)){
			nest.generate(world, rand, level.getSettings(), x, y, z, dir);
			return;
		}
	}	
}
