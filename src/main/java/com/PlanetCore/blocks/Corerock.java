package com.PlanetCore.blocks;

import com.PlanetCore.init.ModBlocks;
import com.PlanetCore.init.ModItems;
import com.PlanetCore.init.blocks.item.ItemBlockVariants;
import com.PlanetCore.util.IMetaName;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Corerock extends BlockBase implements IMetaName {

	public static final PropertyEnum<Corerock.EnumType> VARIANT = PropertyEnum.create("variant", Corerock.EnumType.class);

	public Corerock(String name, Material material) {
		super(name, material);

		setSoundType(SoundType.METAL);
		setHardness(-1.0F);
		setResistance(100.0F);
		setHarvestLevel("pickaxe", 3);
		setLightLevel(1.0F);
		setTickRandomly(true);

	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (Corerock.EnumType corerock$enumtype : Corerock.EnumType.values()) {
			items.add(new ItemStack(this, 1, corerock$enumtype.getMeta()));
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT, Corerock.EnumType.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(VARIANT).getMeta();
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this),1, getMetaFromState(world.getBlockState(pos)));
	}


	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, VARIANT);
	}




	public enum EnumType implements IStringSerializable
	{
		CORE(0, "corestone"),
		INNERCORE(1, "innercorestone"),
		CENTERCORE(2, "centercorestone");

		private static final Corerock.EnumType[] META_LOOKUP = new Corerock.EnumType[]{CORE,INNERCORE,CENTERCORE};
		private final int meta;
		private final String name;

		EnumType(int meta, String name)
		{
			this.meta=meta;
			this.name=name;
		}

		@Override
		public String getName() {
			return this.name;
		}

		public int getMeta()
		{
			return this.meta;
		}

		@Override
		public String toString()
		{
			return this.name;
		}

		public static Corerock.EnumType byMetadata(int meta)
		{
			return META_LOOKUP[meta];
		}

		static {
			for(Corerock.EnumType corerock$enumtype : values())
			{
				META_LOOKUP[corerock$enumtype.getMeta()] = corerock$enumtype;
			}
		}
	}

	@Override
	public String getSpecialName(ItemStack stack)
	{
		return Corerock.EnumType.values()[stack.getItemDamage()].getName();
	}

	@Override
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer entity, boolean willHarvest) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		if (!world.isRemote) {
			for (int i = 0; i < 4; i++) {
				world.spawnEntity(new EntityXPOrb(world, x, y, z, 1));
			}
		}
		return super.removedByPlayer(state, world, pos, entity, willHarvest);
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		System.out.println(meta);
		return this.getDefaultState().withProperty(VARIANT, Corerock.EnumType.byMetadata(meta));
	}




	/*@Override
	public Item getItemDropped(IBlockState state, Random random, int l)
	{
		Block block = this;

		if (block == ModBlocks.CORESTONE_ONYX || block == ModBlocks.INNERCORESTONE_ONYX || block == ModBlocks.CENTERCORESTONE_ONYX)
		{return new ItemStack(ModItems.ONYX, 1, 0).getItem();}
		else if (block == ModBlocks.CORESTONE_AMAZONITE || block == ModBlocks.INNERCORESTONE_AMAZONITE || block == ModBlocks.CENTERCORESTONE_AMAZONITE)
		{return new ItemStack(ModItems.AMAZONITE, 1, 0).getItem();}
		else if (block == ModBlocks.CORESTONE_JADE || block == ModBlocks.INNERCORESTONE_JADE || block == ModBlocks.CENTERCORESTONE_JADE)
		{return new ItemStack(ModItems.JADE, 1, 0).getItem();}
		else if (block == ModBlocks.CORESTONE_DIAMOND || block == ModBlocks.INNERCORESTONE_DIAMOND || block == ModBlocks.CENTERCORESTONE_DIAMOND)
		{return new ItemStack(ModItems.DIAMOND, 1, 0).getItem();}
		else if (block == ModBlocks.CORESTONE_TOPAZ || block == ModBlocks.INNERCORESTONE_TOPAZ || block == ModBlocks.CENTERCORESTONE_TOPAZ)
		{return new ItemStack(ModItems.TOPAZ, 1, 0).getItem();}
		else if (block == ModBlocks.CORESTONE_RUBY || block == ModBlocks.INNERCORESTONE_RUBY || block == ModBlocks.CENTERCORESTONE_RUBY)
		{return new ItemStack(ModItems.RUBY, 1, 0).getItem();}
		else if (block == ModBlocks.CORESTONE_SAPPHIRE || block == ModBlocks.INNERCORESTONE_SAPPHIRE || block == ModBlocks.CENTERCORESTONE_SAPPHIRE)
		{return new ItemStack(ModItems.SAPPHIRE, 1, 0).getItem();}
		else if (block == ModBlocks.CORESTONE_EMERALD || block == ModBlocks.INNERCORESTONE_EMERALD || block == ModBlocks.CENTERCORESTONE_EMERALD)
		{return new ItemStack(Items.EMERALD, 1, 0).getItem();}
		else if (block == ModBlocks.CORESTONE_LAPIS || block == ModBlocks.INNERCORESTONE_LAPIS || block == ModBlocks.CENTERCORESTONE_LAPIS)
		{return Items.DYE;}
		else if (block == ModBlocks.CORESTONE_REDSTONE || block == ModBlocks.INNERCORESTONE_REDSTONE || block == ModBlocks.CENTERCORESTONE_REDSTONE)
		{return new ItemStack(Items.REDSTONE, 1, 0).getItem();}
		else if (block == ModBlocks.CORESTONE_COAL || block == ModBlocks.INNERCORESTONE_COAL || block == ModBlocks.CENTERCORESTONE_COAL)
		{return new ItemStack(Items.COAL, 1, 0).getItem();}
		else if (block == ModBlocks.CORESTONE_SULFUR || block == ModBlocks.INNERCORESTONE_SULFUR || block == ModBlocks.CENTERCORESTONE_SULFUR)
		{return new ItemStack(ModItems.SULFUR, 1, 0).getItem();}
		else
		{return new ItemStack(block, 1, 0).getItem();}
	}



	@Override
	public int quantityDropped(Random random) {
		Block block = this;

		if (block == ModBlocks.CORESTONE_REDSTONE)
		{
			return 12 + random.nextInt(10);
		}
		else if (block == ModBlocks.INNERCORESTONE_REDSTONE)
		{
			return 15 + random.nextInt(12);
		}
		else if (block == ModBlocks.CENTERCORESTONE_REDSTONE)
		{
			return 18 + random.nextInt(14);
		}
		else if (block == ModBlocks.CORESTONE_COAL || block == ModBlocks.CORESTONE_SULFUR)
		{
			return 5;
		}
		else if (block == ModBlocks.INNERCORESTONE_COAL || block == ModBlocks.INNERCORESTONE_SULFUR)
		{
			return 7;
		}
		else if (block == ModBlocks.CENTERCORESTONE_COAL || block == ModBlocks.CENTERCORESTONE_SULFUR)
		{
			return 9;
		}
		else
		{
			return 1;
		}

	}



	@Override
	public int damageDropped(IBlockState state) {
		Block block = this;
		if (block == ModBlocks.CORESTONE_LAPIS || block == ModBlocks.INNERCORESTONE_LAPIS || block == ModBlocks.CENTERCORESTONE_LAPIS) {
			return 4;
		}
		else
		{
			return getMetaFromState(state);
		}
	}

*/

	public static void burnEntities(World world, int x, int y, int z, int radius) {
		float f = radius;
		HashSet hashset = new HashSet();
		int i;
		int j;
		int k;
		double d5;
		double d6;
		double d7;
		double wat = radius/** 2 */
				;

		boolean isOccupied = false;

		// bombStartStrength *= 2.0F;
		i = MathHelper.floor(x - wat - 1.0D);
		j = MathHelper.floor(x + wat + 1.0D);
		k = MathHelper.floor(y - wat - 1.0D);
		int i2 = MathHelper.floor(y + wat + 1.0D);
		int l = MathHelper.floor(z - wat - 1.0D);
		int j2 = MathHelper.floor(z + wat + 1.0D);
		AxisAlignedBB bb = new AxisAlignedBB(i,k,l,j,i2,j2);
		List list = world.getEntitiesWithinAABBExcludingEntity(null, bb);
		Vec3d vec3 = new Vec3d(x, y, z);

		for (int i1 = 0; i1 < list.size(); ++i1) {
			Entity entity = (Entity) list.get(i1);
			double d4 = entity.getDistance(x, y, z) / radius;

			if (d4 <= 1.0D) {
				d5 = entity.posX - x;
				d6 = entity.posY + entity.getEyeHeight() - y;
				d7 = entity.posZ - z;
				BlockPos pos1 = new BlockPos(x,y,z);
				BlockPos pos2 = new BlockPos(entity.posX,entity.posY + entity.getEyeHeight(),entity.posZ);
				double d9 = MathHelper.sqrt(d5 * d5 + d6 * d6 + d7 * d7);

				if (d9 < wat) {
					d5 /= d9;
					d6 /= d9;
					d7 /= d9;
					double d11 = (1.0D - d4);// * d10;
					if (!(entity instanceof EntityPlayerMP) || (entity instanceof EntityPlayerMP
							&& !((EntityPlayerMP) entity).isCreative())) {
						entity.attackEntityFrom(DamageSource.ON_FIRE, 6);
						entity.setFire(10);
					}
				}
			}
		}
		radius = (int) f;
	}




	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
	{
		Block block = this;
		if (!entityIn.isImmuneToFire() && entityIn instanceof EntityLivingBase && !EnchantmentHelper.hasFrostWalkerEnchantment((EntityLivingBase)entityIn))
		{
			if (block == ModBlocks.CORESTONE)
			{
				entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 20.0F);
			}
		}

		super.onEntityWalk(worldIn, pos, entityIn);
	}

	@SideOnly(Side.CLIENT)
	public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return 15728880;
	}

	private void particleEffects(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		int X = pos.getX();
		int Y = pos.getY();
		int Z = pos.getZ();
		if((worldIn.isRaining() && this == ModBlocks.CORESTONE)) {

			worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, X, Y+1, Z, 0, 0, 0);
		}
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	{
		super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
		//thermalEffects(worldIn, pos, state);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		BlockPos blockpos = pos.up();
		IBlockState iblockstate = worldIn.getBlockState(blockpos);

		if (iblockstate.getBlock() == Blocks.WATER || iblockstate.getBlock() == Blocks.FLOWING_WATER)
		{
			worldIn.setBlockToAir(blockpos);
			worldIn.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);

			if (worldIn instanceof WorldServer)
			{
				((WorldServer)worldIn).spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.25D, (double)blockpos.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D);
			}
		}
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
		particleEffects(stateIn, worldIn, pos, rand);
	}



	public boolean canEntitySpawn(IBlockState state, Entity entityIn)
	{
		return entityIn.isImmuneToFire();
	}

}




	

