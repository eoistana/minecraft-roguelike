package greymerk.roguelike.catacomb.segment.part;

import greymerk.roguelike.catacomb.CatacombLevel;
import greymerk.roguelike.catacomb.segment.ISegment;
import greymerk.roguelike.catacomb.theme.ITheme;
import greymerk.roguelike.worldgen.Cardinal;
import greymerk.roguelike.worldgen.Coord;
import greymerk.roguelike.worldgen.WorldGenPrimitive;

import java.util.Random;

import net.minecraft.world.World;

public abstract class SegmentBase implements ISegment {


	
	@Override
	public void generate(World world, Random rand, CatacombLevel level, Cardinal dir, ITheme theme, int x, int y, int z) {
		
		if(level.hasNearbyNode(x, z)) return;
		
		if(isValidWall(world, dir, x, y, z)){
			genWall(world, rand, level, dir, theme, x, y, z);
		}
	}
	
	protected abstract void genWall(World world, Random rand, CatacombLevel level, Cardinal dir, ITheme theme, int x, int y, int z);

	protected boolean isValidWall(World world, Cardinal wallDirection, int x, int y, int z) {
		
		switch(wallDirection){
		case NORTH:
			if(WorldGenPrimitive.isAirBlock(world, new Coord(x - 1, y + 1, z - 2))) return false;
			if(WorldGenPrimitive.isAirBlock(world, new Coord(x + 1, y + 1, z - 2))) return false;
			break;
		case SOUTH:
			if(WorldGenPrimitive.isAirBlock(world, new Coord(x - 1, y + 1, z + 2))) return false;
			if(WorldGenPrimitive.isAirBlock(world, new Coord(x + 1, y + 1, z + 2))) return false;
			break;
		case EAST:
			if(WorldGenPrimitive.isAirBlock(world, new Coord(x + 2, y + 1, z - 1))) return false;
			if(WorldGenPrimitive.isAirBlock(world, new Coord(x + 2, y + 1, z + 1))) return false;
			break;
		case WEST:
			if(WorldGenPrimitive.isAirBlock(world, new Coord(x - 2, y + 1, z - 1))) return false;
			if(WorldGenPrimitive.isAirBlock(world, new Coord(x - 2, y + 1, z + 1))) return false;
			break;
		}
		
		return true;
	}
	

}
