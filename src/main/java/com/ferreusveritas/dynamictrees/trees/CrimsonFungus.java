package com.ferreusveritas.dynamictrees.trees;

import com.ferreusveritas.dynamictrees.DynamicTrees;
import com.ferreusveritas.dynamictrees.systems.DirtHelper;
import com.ferreusveritas.dynamictrees.systems.RootyBlockHelper;
import com.ferreusveritas.dynamictrees.systems.featuregen.VineGenFeature;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class CrimsonFungus extends VanillaTreeFamily {

	public class CrimsonSpecies extends Species {

		CrimsonSpecies(TreeFamily treeFamily) {
			this(treeFamily.getName(), treeFamily);

			setupStandardSeedDropping();

			addGenFeature(new VineGenFeature().setVineBlock(Blocks.WEEPING_VINES));
		}
		CrimsonSpecies(ResourceLocation name, TreeFamily family){
			super(name, family);
			setBasicGrowingParameters(0.3f, 14.0f, 0, 4, 1f);

			envFactor(Type.COLD, 0.25f);
			envFactor(Type.WET, 0.75f);
		}

		@Override
		protected void setStandardSoils() {
			addAcceptableSoils(DirtHelper.DIRTLIKE, DirtHelper.NETHERSOILLIKE, DirtHelper.FUNGUSLIKE);
		}

		@Override
		public boolean isBiomePerfect(RegistryKey<Biome> biome) { return isOneOfBiomes(biome, Biomes.CRIMSON_FOREST); }

		@Override
		public boolean canSaplingGrow(World world, BlockPos pos) {
			BlockState soilState = world.getBlockState(pos.down());
			return soilState.getBlock() == Blocks.CRIMSON_NYLIUM || soilState.getBlock() == RootyBlockHelper.getRootyBlock(Blocks.CRIMSON_NYLIUM);
		}

		@Override
		public SoundType getSaplingSound() {
			return SoundType.FUNGUS;
		}

		@Override
		public VoxelShape getSaplingShape() {
			return VoxelShapes.create(new AxisAlignedBB(0.25f, 0.0f, 0.25f, 0.75f, 0.5f, 0.75f));
		}
	}

	public CrimsonFungus() {
		this(DynamicTrees.VanillaWoodTypes.crimson);
		addConnectableVanillaLeaves((state) -> state.getBlock() == Blocks.NETHER_WART_BLOCK);
	}
	public CrimsonFungus(DynamicTrees.VanillaWoodTypes type) {
		super(type);
	}
	
	@Override
	public void createSpecies() {
		setCommonSpecies(new CrimsonSpecies(this));
	}

}