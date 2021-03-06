package greymerk.roguelike.catacomb.theme;

import greymerk.roguelike.worldgen.BlockWeightedRandom;
import greymerk.roguelike.worldgen.MetaBlock;
import greymerk.roguelike.worldgen.blocks.Log;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.init.Blocks;

public class ThemeDarkHall extends ThemeBase{

	public ThemeDarkHall(){
		
		MetaBlock cracked = new MetaBlock(Blocks.stonebrick);
		cracked.withProperty(BlockStoneBrick.VARIANT_PROP, BlockStoneBrick.EnumType.CRACKED);
		
		BlockWeightedRandom walls = new BlockWeightedRandom();
		walls.addBlock(new MetaBlock(Blocks.cobblestone), 30);
		walls.addBlock(new MetaBlock(Blocks.mossy_cobblestone), 10);
		walls.addBlock(new MetaBlock(Blocks.stonebrick), 20);
		walls.addBlock(cracked, 10);
		walls.addBlock(new MetaBlock(Blocks.gravel), 5);
		MetaBlock stair = new MetaBlock(Blocks.stone_stairs);
		MetaBlock pillar = new MetaBlock(Blocks.stonebrick);
		pillar.withProperty(BlockStoneBrick.VARIANT_PROP, BlockStoneBrick.EnumType.MOSSY);
		
		MetaBlock walls2 = new MetaBlock(Blocks.planks);
		walls2.withProperty(BlockPlanks.VARIANT_PROP, BlockPlanks.EnumType.DARK_OAK);
		MetaBlock stair2 = new MetaBlock(Blocks.dark_oak_stairs);
		MetaBlock pillar2 = Log.getLog(Log.DARKOAK);
		
		this.primary = new BlockSet(walls, stair, pillar);
		this.secondary = new BlockSet(walls2, stair2, pillar2);

	}
}
