package greymerk.roguelike.catacomb.theme;

import greymerk.roguelike.worldgen.MetaBlock;
import net.minecraft.block.BlockQuartz;
import net.minecraft.init.Blocks;

public class ThemeQuartz extends ThemeBase{

	public ThemeQuartz(){
	
		MetaBlock walls = new MetaBlock(Blocks.quartz_block);
		
		MetaBlock stair = new MetaBlock(Blocks.quartz_stairs);
		MetaBlock pillar = new MetaBlock(Blocks.quartz_block);
		pillar.withProperty(BlockQuartz.VARIANT_PROP, BlockQuartz.EnumType.LINES_Y);
		
		this.primary = new BlockSet(walls, stair, pillar);
		
		MetaBlock SegmentWall = new MetaBlock(Blocks.quartz_block);
		pillar.withProperty(BlockQuartz.VARIANT_PROP, BlockQuartz.EnumType.CHISELED);
		
		this.secondary =  new BlockSet(SegmentWall, stair, pillar);

	}
}
