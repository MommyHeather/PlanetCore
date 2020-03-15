package com.PlanetCore.blocks;


import com.PlanetCore.Main;
import com.PlanetCore.init.ModBlocks;
import com.PlanetCore.init.ModItems;
import com.PlanetCore.util.IHasModel;
import com.google.common.base.Predicate;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMaterialMatcher;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Random;


public class BlockBase extends Block implements IHasModel {
	private BlockPattern snowmanPattern;
	private BlockPattern golemBasePattern;
	private BlockPattern golemPattern;
	private static final Predicate<IBlockState> IS_PUMPKIN = new Predicate<IBlockState>() {
		public boolean apply(@Nullable IBlockState p_apply_1_) {
			return p_apply_1_ != null && (p_apply_1_.getBlock() == Blocks.PUMPKIN || p_apply_1_.getBlock() == Blocks.LIT_PUMPKIN);
		}
	};

	public BlockBase(String name, Material material) {
		super(material);
		setTranslationKey(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		setSoundType(SoundType.METAL);
	}

	Random rand = new Random();

	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		float PressureLevel = (pos.getY() / -23808 * 100.0F) * (pos.getY() / -23808 * 100.0F) * (pos.getY() / -23808 * 100.0F);
		if (pos.getY() < 0) {
			if (Math.random() <= (pos.getY() / -47616.0F) && !(this instanceof GemBase) && !(this instanceof CoreRockGemBase) && !(this instanceof BlocksBase)) {
				for (EnumFacing side : EnumFacing.values()) {
					BlockPos movedPos = pos.offset(side);
					IBlockState movedState = worldIn.getBlockState(movedPos);
					if (movedState == Blocks.AIR.getDefaultState() || movedState.getBlock().getExplosionResistance(null) < PressureLevel
							|| movedState.getMaterial() == Material.WATER || movedState.getMaterial() == Material.LAVA || movedState.getBlock() == Blocks.ANVIL)
						continue; // you can add more blocks to this check to exclude them
					//
					EnumFacing[] sides = Arrays.stream(EnumFacing.VALUES)
							.filter(s -> !movedPos.offset(s).equals(pos) && worldIn.isAirBlock(movedPos.offset(s)))
							.toArray(EnumFacing[]::new);
					if (sides.length == 0) continue;
					worldIn.setBlockState(movedPos, this.getDefaultState());
					worldIn.setBlockState(pos.offset(sides[rand.nextInt(sides.length)]), movedState);
				}
			}
		}
	}

	public static void unstableBlock(World world, int x, int y, int z, IBlockState state) {
		Iterable<BlockPos> it = BlockPos.getAllInBox(x + 3, y, z - 3, x - 3, y, z + 3);
	}


	//Earthquake event
//Upon destroying the block, by a player or by explosion, the surrounding area for the player have a chance to crumble.
//How this event should work: No description
	public static void unstable(World worldIn, BlockPos pos, IBlockState state) {
		int x;
		int y;
		int z;
		x = pos.getX();
		y = pos.getY();
		z = pos.getZ();
		float counter = 0;
		Random rand = new Random();
		int rand2 = (rand.nextInt(3));
		int rand4 = (rand.nextInt(6));
		Iterable<BlockPos> it = BlockPos.getAllInBox(x - 8, y - 8, z - 8, x + 8, y + 8, z + 8);
		for (BlockPos pos2 : it) {
			IBlockState state2 = worldIn.getBlockState(pos2);
			if (state2.getMaterial() == Material.AIR) {
				counter++;
			}
		}
		float SuperChance = (pos.getY() / (-600000.0F / ((counter + 1F) / 64F)));
		float SuperChance1 = ((-6000 - pos.getY()) / (-1800000.0F / ((counter + 1F) / 64F)));
		if (y <= 0 && y >= -1500 && Math.random() <= SuperChance || (y <= 1500 && y >= -6000 && Math.random() <= SuperChance1)) {
			for (BlockPos pos2 : it) {
				IBlockState state2 = worldIn.getBlockState(pos2);
				if (state2.getMaterial() == Material.AIR) {
					int x1 = pos2.getX();
					int y1 = pos2.getY() + 1;
					int z1 = pos2.getZ();
					BlockPos pos3 = new BlockPos(x1, y1, z1);
					IBlockState state3 = worldIn.getBlockState(pos3);
					if (state3.getMaterial() == Material.ROCK && !worldIn.isRemote) {
						int x2 = pos3.getX();
						int y2 = pos3.getY();
						int z2 = pos3.getZ();
						Iterable<BlockPos> it1 = BlockPos.getAllInBox(x2 - rand4, y2 - rand2, z2 - rand4, x2 + rand4, y2 + rand2, z2 + rand4);
						for (BlockPos pos4 : it1) {
							int x3 = pos4.getX();
							int y3 = pos4.getY();
							int z3 = pos4.getZ();
							EntityFallingBlock entityfallingblock = new EntityFallingBlock(worldIn, x3 + 0.5, y3, z3 + 0.5,  worldIn.getBlockState(pos4));
							entityfallingblock.setHurtEntities(true);
							worldIn.spawnEntity(entityfallingblock);

						}
					}
				}
			}
		}
	}

	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon)
	{
		return this == ModBlocks.IRON_BLOCK || this == ModBlocks.STEEL_BLOCK || this == ModBlocks.SILVER_BLOCK || this ==  ModBlocks.GOLD_BLOCK
				|| this == ModBlocks.PLATINUM_BLOCK || this == ModBlocks.SAPPHIRE_BLOCK || this == ModBlocks.FLAMING_TOPAZ_BLOCK || this == ModBlocks.RUBY_BLOCK
				|| this == ModBlocks.TOPAZ_BLOCK || this == ModBlocks.DIAMOND_BLOCK || this == ModBlocks.JADE_BLOCK || this == ModBlocks.AMAZONITE_BLOCK || this == ModBlocks.AMETHYST_BLOCK || this == ModBlocks.ONYX_BLOCK || this == ModBlocks.AZURITE_BLOCK;
	}
	
	
	private void add(BlockPos pos, IBlockState state) {
		
	}


	@Override
	public void registerModels() 
	{
		Main.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}


	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		Block block = this;
		if (block == ModBlocks.IRON_BLOCK) {
			super.onBlockAdded(worldIn, pos, state);
			this.trySpawnGolem(worldIn, pos);
		}
	}


	private void trySpawnGolem(World worldIn, BlockPos pos)
	{
		BlockPattern.PatternHelper blockpattern$patternhelper = this.getSnowmanPattern().match(worldIn, pos);

		if (blockpattern$patternhelper == null)
		{

			blockpattern$patternhelper = this.getGolemPattern().match(worldIn, pos);

			if (blockpattern$patternhelper != null)
			{
				for (int j = 0; j < this.getGolemPattern().getPalmLength(); ++j)
				{
					for (int k = 0; k < this.getGolemPattern().getThumbLength(); ++k)
					{
						worldIn.setBlockState(blockpattern$patternhelper.translateOffset(j, k, 0).getPos(), Blocks.AIR.getDefaultState(), 2);
					}
				}

				BlockPos blockpos = blockpattern$patternhelper.translateOffset(1, 2, 0).getPos();
				EntityIronGolem entityirongolem = new EntityIronGolem(worldIn);
				entityirongolem.setPlayerCreated(true);
				entityirongolem.setLocationAndAngles((double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.05D, (double)blockpos.getZ() + 0.5D, 0.0F, 0.0F);
				worldIn.spawnEntity(entityirongolem);

				for (EntityPlayerMP entityplayermp1 : worldIn.getEntitiesWithinAABB(EntityPlayerMP.class, entityirongolem.getEntityBoundingBox().grow(5.0D)))
				{
					CriteriaTriggers.SUMMONED_ENTITY.trigger(entityplayermp1, entityirongolem);
				}

				for (int j1 = 0; j1 < 120; ++j1)
				{
					worldIn.spawnParticle(EnumParticleTypes.SNOWBALL, (double)blockpos.getX() + worldIn.rand.nextDouble(), (double)blockpos.getY() + worldIn.rand.nextDouble() * 3.9D, (double)blockpos.getZ() + worldIn.rand.nextDouble(), 0.0D, 0.0D, 0.0D);
				}

				for (int k1 = 0; k1 < this.getGolemPattern().getPalmLength(); ++k1)
				{
					for (int l1 = 0; l1 < this.getGolemPattern().getThumbLength(); ++l1)
					{
						BlockWorldState blockworldstate1 = blockpattern$patternhelper.translateOffset(k1, l1, 0);
						worldIn.notifyNeighborsRespectDebug(blockworldstate1.getPos(), Blocks.AIR, false);
					}
				}
			}
		}
	}



	@Override
	public void onBlockExploded(World world, BlockPos pos, Explosion explosion) {
		super.onBlockExploded(world, pos, explosion);
		//naturalGasExplosion(world, pos, (IBlockState)explosion);
		unstable(world, pos, (IBlockState)explosion);
		//lavaDecompression(world, pos, (IBlockState)explosion);
	}

	@Override
	public void onPlayerDestroy(World worldIn, BlockPos pos, IBlockState state) {
		super.onPlayerDestroy(worldIn, pos, state);
		//naturalGasExplosion(worldIn, pos, state);
		unstable(worldIn, pos, state);
		//lavaDecompression(worldIn, pos, state);
	}
	protected BlockPattern getSnowmanPattern()
	{
		if (this.snowmanPattern == null)
		{
			this.snowmanPattern = FactoryBlockPattern.start().aisle("^", "#", "#").where('^', BlockWorldState.hasState(IS_PUMPKIN)).where('#', BlockWorldState.hasState(BlockStateMatcher.forBlock(Blocks.SNOW))).build();
		}

		return this.snowmanPattern;
	}


	protected BlockPattern getGolemBasePattern()
	{
		if (this.golemBasePattern == null)
		{
			this.golemBasePattern = FactoryBlockPattern.start().aisle("~ ~", "###", "~#~").where('#', BlockWorldState.hasState(BlockStateMatcher.forBlock(ModBlocks.IRON_BLOCK))).where('~', BlockWorldState.hasState(BlockMaterialMatcher.forMaterial(Material.AIR))).build();
		}

		return this.golemBasePattern;
	}


	protected BlockPattern getGolemPattern()
	{
		if (this.golemPattern == null)
		{
			this.golemPattern = FactoryBlockPattern.start().aisle("~^~", "###", "~#~").where('^', BlockWorldState.hasState(IS_PUMPKIN)).where('#', BlockWorldState.hasState(BlockStateMatcher.forBlock(ModBlocks.IRON_BLOCK))).where('~', BlockWorldState.hasState(BlockMaterialMatcher.forMaterial(Material.AIR))).build();
		}

		return this.golemPattern;
	}
	
		

}
