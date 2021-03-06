package greymerk.roguelike.worldgen;

import greymerk.roguelike.util.JsonNBT;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

import com.google.gson.JsonObject;

public class Spawnable {

	private boolean equip;
	private String name;
	private NBTTagCompound meta;
	
	public Spawnable(JsonObject data){
		name = data.get("name").getAsString();
		equip = data.has("equip") ? data.get("equip").getAsBoolean() : true;
		if(data.has("meta")){
			JsonObject metadata = data.get("meta").getAsJsonObject();
			this.meta = JsonNBT.jsonToCompound(metadata);
		}
	}
		
	public void generate(World world, Random rand, Coord cursor, int level){
		
		if(!WorldGenPrimitive.setBlock(world, cursor, new MetaBlock(Blocks.mob_spawner))) return;
		
		TileEntityMobSpawner spawner = (TileEntityMobSpawner) WorldGenPrimitive.getTileEntity(world, cursor);

		if (spawner == null) return;
		
		MobSpawnerBaseLogic logic = spawner.getSpawnerBaseLogic();
		logic.setEntityName(this.name);
		
		if (meta == null){
			if (equip) Spawner.setRoguelike(logic, level, this.name);
			return;
		}
		
		Spawner.setMeta(logic, meta);
	}
}
