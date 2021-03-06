package greymerk.roguelike.worldgen.blocks;

import greymerk.roguelike.worldgen.Cardinal;
import greymerk.roguelike.worldgen.Coord;
import greymerk.roguelike.worldgen.MetaBlock;
import greymerk.roguelike.worldgen.WorldGenPrimitive;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.world.World;

public enum Skull {

	SKELETON, WITHER, ZOMBIE, STEVE, CREEPER;
	
	public static void set(World world, Random rand, int x, int y, int z, Cardinal dir, Skull type){
		
		MetaBlock skullBlock = new MetaBlock(Blocks.skull);
		Coord pos = new Coord(x, y, z);
		
		if(!skullBlock.setBlock(world, pos)) return;
		
		TileEntity skullEntity = WorldGenPrimitive.getTileEntity(world, pos);
		
		if(skullEntity == null) return;
		if(!(skullEntity instanceof TileEntitySkull)) return;
		
		TileEntitySkull skull = (TileEntitySkull)skullEntity;
		
		setType(skull, type);
		setRotation(rand, skull, dir);
	}
	
	public static void set(World world, Random rand, Coord cursor, Cardinal dir, Skull type){
		int x = cursor.getX();
		int y = cursor.getY();
		int z = cursor.getZ();
		
		set(world, rand, x, y, z, dir, type);
	}
	
	public static void setType(TileEntitySkull skull, Skull type){		
		skull.setType(getSkullId(type));
	}
	
	public static void setRotation(Random rand, TileEntitySkull skull, Cardinal dir){
		int directionValue = getDirectionValue(dir);
		
		directionValue += -1 + rand.nextInt(3);
		directionValue = directionValue % 16;
		
		skull.setSkullRotation(directionValue);
	}
	
	public static int getSkullId(Skull type){
		switch(type){
		case SKELETON: return 0;
		case WITHER: return 1;
		case ZOMBIE: return 2;
		case STEVE: return 3;
		case CREEPER: return 4;
		default: return 0;
		}
	}
	
	public static int getDirectionValue(Cardinal dir){
		switch(dir){
		case NORTH: return 0;
		case EAST: return 4;
		case SOUTH: return 8;
		case WEST: return 12;
		default: return 0;
		}
	}	
}
