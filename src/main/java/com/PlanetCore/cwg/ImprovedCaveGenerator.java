package com.PlanetCore.cwg;

import com.PlanetCore.blocks.BlocksBase;
import com.PlanetCore.blocks.HotRocks;
import com.PlanetCore.init.ModBlocks;
import io.github.opencubicchunks.cubicchunks.api.util.Coords;
import io.github.opencubicchunks.cubicchunks.api.util.CubePos;
import io.github.opencubicchunks.cubicchunks.api.world.ICube;
import io.github.opencubicchunks.cubicchunks.api.worldgen.CubePrimer;
import io.github.opencubicchunks.cubicchunks.api.worldgen.structure.ICubicStructureGenerator;
import io.github.opencubicchunks.cubicchunks.cubicgen.StructureGenUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.function.Predicate;

import static com.PlanetCore.blocks.BlocksBase.VARIANT_SUPERCOMPRESSED;
import static com.PlanetCore.blocks.HotRocks.VARIANT_HOTROCKS;
import static io.github.opencubicchunks.cubicchunks.api.util.Coords.cubeToMinBlock;
import static io.github.opencubicchunks.cubicchunks.api.util.Coords.localToBlock;
import static io.github.opencubicchunks.cubicchunks.cubicgen.StructureGenUtil.normalizedDistance;
import static java.lang.Math.max;
import static net.minecraft.util.math.MathHelper.*;

public class ImprovedCaveGenerator implements ICubicStructureGenerator {

    //=============================================
    //Possibly configurable values
    //=============================================

    /**
     * 1 in CAVE_RARITY attempts will result in generating any caves at all
     * <p>
     * Vanilla value: 7 Multiply by 16 and divide by 8: there are 16 cubes in a vanilla chunk, but only one cube per 8
     * has caves generated
     */
    private static final int CAVE_RARITY = 16 * 7 / (2 * 2 * 2);

    /**
     * Maximum amount of starting nodes
     */
    private static final int MAX_INIT_NODES = 7;

    /**
     * 1 in LARGE_NODE_RARITY initial attempts will result in large node
     */
    private static final int LARGE_NODE_RARITY = 4;

    /**
     * The maximum amount of additional branches after generating large node. Random value between 0 and
     * LARGE_NODE_MAX_BRANCHES is chosen.
     */
    private static final int LARGE_NODE_MAX_BRANCHES = 5;

    /**
     * 1 in BIG_CAVE_RARITY branches will start bigger than usual
     */
    private static final int BIG_CAVE_RARITY = 10;

    /**
     * Value added to the size of the cave (radius)
     */
    private static double CAVE_SIZE_ADD = 1.5D;

    /**
     * In 1 of STEEP_STEP_RARITY steps, cave will be flattened using STEEPER_FLATTEN_FACTOR instead of FLATTEN_FACTOR
     */
    private static final int STEEP_STEP_RARITY = 6;

    /**
     * After each step the Y direction component will be multiplied by this value, unless steeper cave is allowed
     */
    private static final float FLATTEN_FACTOR = 0.7f;

    /**
     * If steeper cave is allowed - this value will be used instead of FLATTEN_FACTOR
     */
    private static final float STEEPER_FLATTEN_FACTOR = 0.92f;

    /**
     * Each step cave direction angles will be changed by this fraction of values that specify how direction changes
     */
    private static final float DIRECTION_CHANGE_FACTOR = 0.1f;

    /**
     * This fraction of the previous value that controls horizontal direction changes will be used in next step
     */
    private static final float PREV_HORIZ_DIRECTION_CHANGE_WEIGHT = 0.75f;

    /**
     * This fraction of the previous value that controls vertical direction changes will be used in next step
     */
    private static final float PREV_VERT_DIRECTION_CHANGE_WEIGHT = 0.9f;

    /**
     * Maximum value by which horizontal cave direction randomly changes each step, lower values are much more likely.
     */
    private static final float MAX_ADD_DIRECTION_CHANGE_HORIZ = 4.0f;

    /**
     * Maximum value by which vertical cave direction randomly changes each step, lower values are much more likely.
     */
    private static final float MAX_ADD_DIRECTION_CHANGE_VERT = 2.0f;

    /**
     * 1 in this amount of steps will actually carve any blocks,
     */
    private static final int CARVE_STEP_RARITY = 4;

    private static final float VERT_ANGLE_FACTOR = 1.0f/3.0f;

    private static final float INITIAL_VERT_ANGLE_FACTOR = 2.0F / 8.0F;

    /**
     * Relative "height" if depth floor
     * <p>
     * -1 results in round cave without flat floor 1 will completely fill the cave 0 will result in lower half of the
     * cave to be filled with stone
     */
    private static final double CAVE_FLOOR_DEPTH = -0.7;
    private static final int RANGE = 8;

    private static int OUTER_CAVE_THICKNESS = 2;

    private static boolean REPLACE_WITH_AIR;

    public enum CaveType {

        CAVE0(ModBlocks.AIR_NO_PRESSURE, Blocks.LAVA,1.0f, -10000,10000, 0),
        CAVE1(ModBlocks.SUPERCOMPRESSED_COAL, Blocks.LAVA,0.1f, -200,-15, 0),
        CAVE2(ModBlocks.SUPERCOMPRESSED_IRON, ModBlocks.IRON_LAVA_FLUID,0.1f, -200,-100, 0),
        CAVE3(ModBlocks.SUPERCOMPRESSED_IRON, ModBlocks.IRON_LAVA_FLUID,0.1f, -400,-200, 1),
        CAVE4(ModBlocks.SUPERCOMPRESSED_IRON, ModBlocks.IRON_LAVA_FLUID,0.1f, -900,-400, 2),
        CAVE5(ModBlocks.SUPERCOMPRESSED_IRON, ModBlocks.IRON_LAVA_FLUID,0.1f, -1000000,-900, 3),
        CAVE6(ModBlocks.GRANITE, Blocks.LAVA,0.1f, -400,-200, 0),
        CAVE7(ModBlocks.GRANITE, Blocks.LAVA,0.2f, -900,-400, 1),
        CAVE8(ModBlocks.GRANITE, Blocks.LAVA,0.3f, -1000000,-900, 2),
        CAVE9(ModBlocks.DIORITE, Blocks.LAVA,0.1f, -400,-200, 0),
        CAVE10(ModBlocks.DIORITE, Blocks.LAVA,0.2f, -900,-400, 1),
        CAVE11(ModBlocks.DIORITE, Blocks.LAVA,0.3f, -1000000,-900, 2),
        CAVE12(ModBlocks.ANDESITE, Blocks.LAVA,0.1f, -400,-50, 0),
        CAVE13(ModBlocks.ANDESITE, Blocks.LAVA,0.2f, -900,-400, 1),
        CAVE14(ModBlocks.ANDESITE, Blocks.LAVA,0.3f, -1000000,-900, 2),
        CAVE15(ModBlocks.SUPERCOMPRESSED_REDSTONE, ModBlocks.REDSTONE_LAVA_FLUID,0.008f, -400,-200, 0),
        CAVE16(ModBlocks.SUPERCOMPRESSED_REDSTONE, ModBlocks.REDSTONE_LAVA_FLUID,0.008f, -900,-400, 1),
        CAVE17(ModBlocks.SUPERCOMPRESSED_REDSTONE, ModBlocks.REDSTONE_LAVA_FLUID,0.008f, -1500,-900, 2),
        CAVE18(ModBlocks.SUPERCOMPRESSED_REDSTONE, ModBlocks.REDSTONE_LAVA_FLUID,0.008f, -1000000,-1500, 3),
        CAVE19(ModBlocks.SUPERCOMPRESSED_SILVER, ModBlocks.SILVER_LAVA_FLUID,0.008f, -400,-100, 0),
        CAVE20(ModBlocks.SUPERCOMPRESSED_SILVER, ModBlocks.SILVER_LAVA_FLUID,0.008f, -900,-400, 1),
        CAVE21(ModBlocks.SUPERCOMPRESSED_SILVER, ModBlocks.SILVER_LAVA_FLUID,0.008f, -1500,-900, 2),
        CAVE22(ModBlocks.SUPERCOMPRESSED_SILVER, ModBlocks.SILVER_LAVA_FLUID,0.008f, -1000000,-1500, 3),
        CAVE23(ModBlocks.SUPERCOMPRESSED_GOLD, ModBlocks.GOLD_LAVA_FLUID,0.008f, -400,-200, 0),
        //CAVE24(ModBlocks.SUPERCOMPRESSED_GOLD, ModBlocks.GOLD_LAVA_FLUID,0.01f, -900,-400, 1),
        //CAVE25(ModBlocks.SUPERCOMPRESSED_GOLD, ModBlocks.GOLD_LAVA_FLUID,0.01f, -1500,-900, 2),
        //CAVE26(ModBlocks.SUPERCOMPRESSED_GOLD, ModBlocks.GOLD_LAVA_FLUID,0.01f, -1000000,-1500, 3),
        CAVE27(ModBlocks.SUPERCOMPRESSED_DIAMOND, ModBlocks.DIAMOND_LAVA_FLUID,0.005f, -900,-200, 0),
        //CAVE28(ModBlocks.SUPERCOMPRESSED_DIAMOND, ModBlocks.DIAMOND_LAVA_FLUID,0.005f, -1500,-900, 1),
        //CAVE29(ModBlocks.SUPERCOMPRESSED_DIAMOND, ModBlocks.DIAMOND_LAVA_FLUID,0.01f, -2000,-1500, 2),
        CAVE30(ModBlocks.SUPERCOMPRESSED_DIAMOND, ModBlocks.DIAMOND_LAVA_FLUID,0.005f, -1000000,-2000, 3),
        CAVE31(ModBlocks.SUPERCOMPRESSED_EMERALD, Blocks.LAVA,0.005f, -900,-300, 0),
        //CAVE32(ModBlocks.SUPERCOMPRESSED_EMERALD, Blocks.LAVA,0.005f, -1500,-900, 1),
        //CAVE33(ModBlocks.SUPERCOMPRESSED_EMERALD, Blocks.LAVA,0.01f, -2000,-1500, 2),
        //CAVE34(ModBlocks.SUPERCOMPRESSED_EMERALD, Blocks.LAVA,0.02f, -1000000,-2000, 3),
        CAVE35(ModBlocks.SUPERCOMPRESSED_LAPIS, Blocks.LAVA,0.005f, -900,-500, 0),
        //CAVE36(ModBlocks.SUPERCOMPRESSED_LAPIS, Blocks.LAVA,0.0015f, -1500,-900, 1),
        //CAVE37(ModBlocks.SUPERCOMPRESSED_LAPIS, Blocks.LAVA,0.002f, -2000,-1500, 2),
        //CAVE38(ModBlocks.SUPERCOMPRESSED_LAPIS, Blocks.LAVA,0.0025f, -1000000,-2000, 3),
        CAVE39(ModBlocks.SUPERCOMPRESSED_TITANIUM, ModBlocks.TITANIUM_LAVA_FLUID,0.2f, -900,-600, 0),
        CAVE40(ModBlocks.SUPERCOMPRESSED_TITANIUM, ModBlocks.TITANIUM_LAVA_FLUID,0.2f, -1500,-900, 1),
        CAVE41(ModBlocks.SUPERCOMPRESSED_TITANIUM, ModBlocks.TITANIUM_LAVA_FLUID,0.2f, -2000,-1500, 2),
        CAVE42(ModBlocks.SUPERCOMPRESSED_TITANIUM, ModBlocks.TITANIUM_LAVA_FLUID,0.2f, -1000000,-2000, 3),
        CAVE43(ModBlocks.SUPERCOMPRESSED_URANIUM, ModBlocks.URANIUM_LAVA_FLUID,0.2f, -900,-700, 0),
        CAVE44(ModBlocks.SUPERCOMPRESSED_URANIUM, ModBlocks.URANIUM_LAVA_FLUID,0.2f, -1500,-900, 1),
        CAVE45(ModBlocks.SUPERCOMPRESSED_URANIUM, ModBlocks.URANIUM_LAVA_FLUID,0.2f, -2000,-1500, 2),
        CAVE46(ModBlocks.SUPERCOMPRESSED_URANIUM, ModBlocks.URANIUM_LAVA_FLUID,0.2f, -1000000,-2000, 3),
        CAVE47(ModBlocks.SUPERCOMPRESSED_TUNGSTEN, ModBlocks.TUNGSTEN_LAVA_FLUID,0.2f, -1200,-800, 0),
        CAVE48(ModBlocks.SUPERCOMPRESSED_TUNGSTEN, ModBlocks.TUNGSTEN_LAVA_FLUID,0.2f, -1500,-1200, 1),
        CAVE49(ModBlocks.SUPERCOMPRESSED_TUNGSTEN, ModBlocks.TUNGSTEN_LAVA_FLUID,0.2f, -2000,-1500, 2),
        CAVE50(ModBlocks.SUPERCOMPRESSED_TUNGSTEN, ModBlocks.TUNGSTEN_LAVA_FLUID,0.2f, -1000000,-2000, 3),
        CAVE51(ModBlocks.SUPERCOMPRESSED_RUBY, ModBlocks.RUBY_LAVA_FLUID,0.08f, -1500,-1000, 0),
        CAVE52(ModBlocks.SUPERCOMPRESSED_RUBY, ModBlocks.RUBY_LAVA_FLUID,0.08f, -2000,-1500, 1),
        CAVE53(ModBlocks.SUPERCOMPRESSED_RUBY, ModBlocks.RUBY_LAVA_FLUID,0.08f, -2300,-2000, 2),
        CAVE54(ModBlocks.SUPERCOMPRESSED_RUBY, ModBlocks.RUBY_LAVA_FLUID,0.08f, -1000000,-2300, 3),
        CAVE55(ModBlocks.SUPERCOMPRESSED_SAPPHIRE, ModBlocks.SAPPHIRE_LAVA_FLUID,0.07f, -1500,-1200, 0),
        CAVE56(ModBlocks.SUPERCOMPRESSED_SAPPHIRE, ModBlocks.SAPPHIRE_LAVA_FLUID,0.07f, -2000,-1500, 1),
        CAVE57(ModBlocks.SUPERCOMPRESSED_SAPPHIRE, ModBlocks.SAPPHIRE_LAVA_FLUID,0.07f, -2300,-2000, 2),
        CAVE58(ModBlocks.SUPERCOMPRESSED_SAPPHIRE, ModBlocks.SAPPHIRE_LAVA_FLUID,0.07f, -1000000,-2300, 3),
        CAVE59(ModBlocks.SUPERCOMPRESSED_MAJORITE, ModBlocks.MAJORITE_LAVA_FLUID,0.06f, -25000,-1500, 0),
        CAVE60(ModBlocks.SUPERCOMPRESSED_AMAZONITE, ModBlocks.AMAZONITE_LAVA_FLUID,0.06f, -25000,-2000, 0),
        CAVE61(ModBlocks.SUPERCOMPRESSED_ONYX, ModBlocks.ONYX_LAVA_FLUID,0.05f, -25000,-2300, 0),
        CAVE62(ModBlocks.SUPERCOMPRESSED_PAINITE, ModBlocks.PAINITE_LAVA_FLUID,0.04f, -25000,-2300, 0);

        private final IBlockState block;
        private final IBlockState lavaMaterial;
        private final float probability;
        private final double minHeight;
        private final double maxHeight;
        private final int meta;


        CaveType(Block block, Block lavaMaterial, float probability,
                 double minHeight, double maxHeight, int meta) {
            this.block = block.getDefaultState();
            this.lavaMaterial = lavaMaterial.getDefaultState();
            this.probability = probability;
            this.minHeight = minHeight;
            this.maxHeight = maxHeight;
            this.meta = meta;
        }

        public IBlockState block() {
            return block;
        }

        public double probability() {
            return probability;
        }

        public double minHeight() {
            return minHeight;
        }
        public double maxHeight() {
            return maxHeight;
        }

        public int meta()
        {
            return meta;
        }
    }

    /**
     * Controls which blocks can be replaced by cave
     */
    private static Predicate<IBlockState> isBlockReplaceable = (state ->
            state.getMaterial() == Material.ROCK || state.getMaterial() == Material.IRON || state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.GLASS);
    @Override public void generate(World world, CubePrimer cube, CubePos cubePos) {

            this.generate(world, cube, cubePos, this::generate, RANGE, RANGE, 1, 1);
    }
    protected void generate(World world, Random rand, CubePrimer cube,
                            int cubeXOrigin, int cubeYOrigin, int cubeZOrigin, CubePos generatedCubePos) {
        /**
         * Loop over all of the blocks inside the enum list
         * Each caves with unique blocks contain a max Y and min Y
         * TODO: Each caves type must have unique seed.
         */
        if (rand.nextInt(CAVE_RARITY) != 0) {
            return;
        }

        for (CaveType cave : CaveType.values()) {

            double minBlockY = cave.minHeight;
            double maxBlockY = cave.maxHeight;

            if ((cubeYOrigin > maxBlockY / 16) || (cubeYOrigin < minBlockY / 16)) {
                continue;
            }
            double probability = cave.probability;
            if (Math.random() > probability) {
                continue;
            }

            isBlockReplaceable = (state -> state.getMaterial() == Material.ROCK || state.getMaterial() == Material.IRON || state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.GRASS);
            //very low probability of generating high number
            int nodes;
            if (cave != CaveType.CAVE0) {
                if (cave.probability < 0.01) nodes = rand.nextInt(rand.nextInt(rand.nextInt(MAX_INIT_NODES - 2) + 1) + 1);
                else nodes = rand.nextInt(rand.nextInt(rand.nextInt(MAX_INIT_NODES - 1) + 1) + 1);
            }
            else
            {
                nodes = rand.nextInt(rand.nextInt(rand.nextInt(MAX_INIT_NODES + 1) + 1) + 1);
            }
            int lavaHeight = Coords.cubeToCenterBlock(cubeYOrigin) + (int) relativeFillY(rand, cubeXOrigin, cubeYOrigin, cubeZOrigin, cave);

            for (int node = 0; node < nodes; ++node) {

                double branchStartX = localToBlock(cubeXOrigin, rand.nextInt(ICube.SIZE));
                double branchStartY = localToBlock(cubeYOrigin, rand.nextInt(ICube.SIZE));
                double branchStartZ = localToBlock(cubeZOrigin, rand.nextInt(ICube.SIZE));
                int subBranches = 1;


                if (rand.nextInt(LARGE_NODE_RARITY) == 0) {

                    if (cave != CaveType.CAVE0) {
                        OUTER_CAVE_THICKNESS = 2;
                        REPLACE_WITH_AIR = false;
                        this.generateLargeNode(cube, cave, rand, rand.nextLong(), generatedCubePos,
                                branchStartX, branchStartY, branchStartZ, lavaHeight);
                        subBranches += rand.nextInt(LARGE_NODE_MAX_BRANCHES);
                    }
                    OUTER_CAVE_THICKNESS = 0;
                    REPLACE_WITH_AIR = true;
                    this.generateLargeNode(cube, cave, rand, rand.nextLong(), generatedCubePos,
                            branchStartX, branchStartY, branchStartZ, lavaHeight);
                    subBranches += rand.nextInt(LARGE_NODE_MAX_BRANCHES);
                }

                for (int branch = 0; branch < subBranches; ++branch) {

                    float horizDirAngle = rand.nextFloat() * (float) Math.PI * 2.0F;
                    float vertDirAngle = (rand.nextFloat() - 0.5F) * INITIAL_VERT_ANGLE_FACTOR;
                    float baseHorizSize = rand.nextFloat() * 2.0F * rand.nextFloat() + rand.nextFloat();

                    if (rand.nextInt(4) == 0) {
                        baseHorizSize *= rand.nextFloat() * rand.nextFloat() * 3.0F + 1.0F;
                    }
                    int startWalkedDistance = 0;
                    int maxWalkedDistance = 0;
                    double vertCaveSizeMod = 1.0 * (rand.nextDouble()+0.5);
                    if (cave != CaveType.CAVE0)
                    {
                        OUTER_CAVE_THICKNESS = 2;
                        REPLACE_WITH_AIR = false;
                        this.generateNode(cube, cave, rand.nextLong(), generatedCubePos,
                                branchStartX, branchStartY, branchStartZ,
                                baseHorizSize, horizDirAngle, vertDirAngle,
                                startWalkedDistance, maxWalkedDistance, vertCaveSizeMod, lavaHeight);
                    }
                    OUTER_CAVE_THICKNESS = 0;
                    REPLACE_WITH_AIR = true;
                    this.generateNode(cube, cave, rand.nextLong(), generatedCubePos,
                            branchStartX, branchStartY, branchStartZ,
                            baseHorizSize, horizDirAngle, vertDirAngle,
                            startWalkedDistance, maxWalkedDistance, vertCaveSizeMod, lavaHeight);
                }
            }
        }
    }

    private double relativeFillY(Random rand, int originX, int originY, int originZ, CaveType caveType) {
        // value of 0 means a cave system is filled from the middle down
        // value of -10 means it's going to be filled from 10 blocks below the middle of it
        return (double) ((rand.nextDouble() * 2 - 1) * 32) - 64 + (-0.0256 * originY * 16);
    }

    /**
     * Generates a flattened cave "room", usually more caves split off it
     */
    private void generateLargeNode(CubePrimer cube, CaveType caveType, Random rand, long seed, CubePos generatedCubePos,
                                   double x, double y, double z, int lavaHeight) {
        float baseHorizSize = 1.0F + rand.nextFloat() * 6.0F;
        float horizDirAngle = 0;
        float vertDirAngle = 0;

        int startWalkedDistance = -1;
        int maxWalkedDistance = -1;
        double vertCaveSizeMod = 1.0;
        this.generateNode(cube, caveType, seed, generatedCubePos, x, y, z,
                baseHorizSize, horizDirAngle, vertDirAngle,
                startWalkedDistance, maxWalkedDistance, vertCaveSizeMod, lavaHeight);
    }

    /**
     * Recursively generates a node in the current cave system tree.
     *
     * @param cube block buffer to modify
     * @param seed random seed to use
     * @param generatedCubePos position of the cube to modify
     * @param caveX starting x coordinate of the cave
     * @param caveY starting Y coordinate of the cave
     * @param caveZ starting Z coordinate of the cave
     * @param baseCaveSize initial value for cave size, size decreases as cave goes further
     * @param horizDirAngle horizontal direction angle
     * @param vertCaveSizeMod vertical direction angle
     * @param startWalkedDistance the amount of steps the cave already went forwards, used in recursive step. -1 means
     * that there will be only one step
     * @param maxWalkedDistance maximum distance the cave can go forwards, <= 0 to use default
     * @param vertDirAngle changes vertical size of the cave, values < 1 result in flattened caves, > 1 result in
     * vertically stretched caves
     */
    private void generateNode(CubePrimer cube, CaveType caveType, long seed,
                              CubePos generatedCubePos,
                              double caveX, double caveY, double caveZ,
                              float baseCaveSize, float horizDirAngle, float vertDirAngle,
                              int startWalkedDistance, int maxWalkedDistance, double vertCaveSizeMod,
                              int lavaHeight) {
        Random rand = new Random(seed);

        //store by how much the horizontal and vertical direction angles will change each step
        float horizDirChange = 0.0F;
        float vertDirChange = 0.0F;
        if (maxWalkedDistance <= 0) {
            int maxBlockRadius = cubeToMinBlock(RANGE - 1);
            maxWalkedDistance = maxBlockRadius - rand.nextInt(maxBlockRadius / 4);
        }
        //if true - this branch won't generate new sub-branches
        boolean finalStep = false;
        int walkedDistance;
        if (startWalkedDistance == -1) {
            //generate a cave "room"
            //start at half distance towards the end = max cave size
            walkedDistance = maxWalkedDistance / 2;
            finalStep = true;
        } else {
            walkedDistance = startWalkedDistance;
        }
        int splitPoint = rand.nextInt(maxWalkedDistance / 2) + maxWalkedDistance / 4;
        for (; walkedDistance < maxWalkedDistance; ++walkedDistance) {
            float fractionWalked = walkedDistance / (float) maxWalkedDistance;
            //horizontal and vertical size of the cave
            //size starts small and increases, then decreases as cave goes further
            double caveSizeHoriz = OUTER_CAVE_THICKNESS + CAVE_SIZE_ADD + sin(fractionWalked * (float) Math.PI) * baseCaveSize;
            double caveSizeVert = OUTER_CAVE_THICKNESS + ((caveSizeHoriz-OUTER_CAVE_THICKNESS) * vertCaveSizeMod);
            //Walk forward a single step:
            //from sin(alpha)=y/r and cos(alpha)=x/r ==> x = r*cos(alpha) and y = r*sin(alpha)
            //always moves by one block in some direction
            //here x is xzDirectionFactor, y is yDirectionFactor
            float xzDirectionFactor = cos(vertDirAngle);
            float yDirectionFactor = sin(vertDirAngle);
            //here y is directionZ and x is directionX
            caveX += cos(horizDirAngle) * xzDirectionFactor;
            caveY += yDirectionFactor;
            caveZ += sin(horizDirAngle) * xzDirectionFactor;
            if (rand.nextInt(STEEP_STEP_RARITY) == 0) {
                vertDirAngle *= STEEPER_FLATTEN_FACTOR;
            } else {
                vertDirAngle *= FLATTEN_FACTOR;
            }
            //change the direction
            vertDirAngle += vertDirChange * DIRECTION_CHANGE_FACTOR;
            horizDirAngle += horizDirChange * DIRECTION_CHANGE_FACTOR;
            //update direction change angles
            vertDirChange *= PREV_VERT_DIRECTION_CHANGE_WEIGHT;
            horizDirChange *= PREV_HORIZ_DIRECTION_CHANGE_WEIGHT;
            vertDirChange += (rand.nextFloat() - rand.nextFloat()) * rand.nextFloat() * MAX_ADD_DIRECTION_CHANGE_VERT;
            horizDirChange += (rand.nextFloat() - rand.nextFloat()) * rand.nextFloat() * MAX_ADD_DIRECTION_CHANGE_HORIZ;
            //if we reached split point - try to split
            //can split only if it's not final branch and the cave is still big enough (>1 block radius)
            if (!finalStep && walkedDistance == splitPoint && baseCaveSize > 1.0F) {
                this.generateNode(cube, caveType, rand.nextLong(),
                        generatedCubePos, caveX, caveY, caveZ,
                        rand.nextFloat() * 0.5F + 0.5F,//base cave size
                        horizDirAngle - ((float) Math.PI / 2F),//horiz. angle - subtract 90 degrees
                        vertDirAngle * VERT_ANGLE_FACTOR, walkedDistance, maxWalkedDistance,
                        1.0D, lavaHeight);
                this.generateNode(cube, caveType, rand.nextLong(), generatedCubePos, caveX, caveY, caveZ,
                        rand.nextFloat() * 0.5F + 0.5F,//base cave size
                        horizDirAngle + ((float) Math.PI / 2F),//horiz. angle - add 90 degrees
                        vertDirAngle  * VERT_ANGLE_FACTOR, walkedDistance, maxWalkedDistance,
                        1.0D, lavaHeight);
                return;
            }

            //carve blocks only on some percentage of steps, unless this is the final branch
            if (rand.nextInt(CARVE_STEP_RARITY) == 0 && !finalStep) {
                continue;
            }
            double xDist = caveX - generatedCubePos.getXCenter();
            double yDist = caveY - generatedCubePos.getYCenter();
            double zDist = caveZ - generatedCubePos.getZCenter();
            double maxStepsDist = maxWalkedDistance - walkedDistance;
            //CHANGE: multiply max(1, vertCaveSizeMod)
            double maxDistToCube = baseCaveSize * max(1, vertCaveSizeMod) + CAVE_SIZE_ADD + ICube.SIZE;
            //can this cube be reached at all?
            //if even after going max distance allowed by remaining steps, it's still too far - stop
            //TODO: does it make any performance difference?
            if (xDist * xDist + yDist * yDist + zDist * zDist - maxStepsDist * maxStepsDist > maxDistToCube * maxDistToCube) {
                return;
            }

            tryCarveBlocks(cube, caveType, generatedCubePos,
                    caveX, caveY, caveZ,
                    caveSizeHoriz, caveSizeVert, lavaHeight);
            if (finalStep) {
                return;
            }
        }
    }

    //returns true if cave generation should be continued
    private void tryCarveBlocks(@Nonnull CubePrimer cube, @Nonnull CaveType caveType, @Nonnull CubePos generatedCubePos,
                                double caveX, double caveY, double caveZ,
                                double caveSizeHoriz, double caveSizeVert, int lavaHeight) {
        double genCubeCenterX = generatedCubePos.getXCenter();
        double genCubeCenterY = generatedCubePos.getYCenter();
        double genCubeCenterZ = generatedCubePos.getZCenter();
        //Can current step position affect currently modified cube?
        //TODO: is multiply by 2 needed?

            if (caveX < genCubeCenterX - ICube.SIZE - caveSizeHoriz * 2.0D ||
                    caveY < genCubeCenterY - ICube.SIZE - caveSizeVert * 2.0D ||
                    caveZ < genCubeCenterZ - ICube.SIZE - caveSizeHoriz * 2.0D ||
                    caveX > genCubeCenterX + ICube.SIZE + caveSizeHoriz * 2.0D ||
                    caveY > genCubeCenterY + ICube.SIZE + caveSizeVert * 2.0D ||
                    caveZ > genCubeCenterZ + ICube.SIZE + caveSizeHoriz * 2.0D) {
                return;
            }
        int minLocalX = floor(caveX - caveSizeHoriz) - generatedCubePos.getMinBlockX() - 1;
        int maxLocalX = floor(caveX + caveSizeHoriz) - generatedCubePos.getMinBlockX() + 1;
        int minLocalY = floor(caveY - caveSizeVert) - generatedCubePos.getMinBlockY() - 1;
        int maxLocalY = floor(caveY + caveSizeVert) - generatedCubePos.getMinBlockY() + 1;
        int minLocalZ = floor(caveZ - caveSizeHoriz) - generatedCubePos.getMinBlockZ() - 1;
        int maxLocalZ = floor(caveZ + caveSizeHoriz) - generatedCubePos.getMinBlockZ() + 1;
        //skip is if everything is outside of that cube
        if (maxLocalX <= 0 || minLocalX >= ICube.SIZE ||
                maxLocalY <= 0 || minLocalY >= ICube.SIZE ||
                maxLocalZ <= 0 || minLocalZ >= ICube.SIZE) {
            return;
        }
        StructureBoundingBox boundingBox = new StructureBoundingBox(minLocalX, minLocalY, minLocalZ, maxLocalX, maxLocalY, maxLocalZ);
        StructureGenUtil.clampBoundingBoxToLocalCube(boundingBox);
        boolean hitAir = StructureGenUtil.scanWallsForBlock(cube, boundingBox,
                (b) -> b.getMaterial() == Material.AIR);
        carveBlocks(cube, caveType, generatedCubePos, caveX, caveY, caveZ, caveSizeHoriz, caveSizeVert, boundingBox, lavaHeight);

    }

    private void carveBlocks(CubePrimer cube, CaveType caveType,
                             CubePos generatedCubePos,
                             double caveX, double caveY, double caveZ,
                             double caveSizeHoriz, double caveSizeVert,
                             StructureBoundingBox boundingBox, int lavaHeight) {
        IBlockState block;
        if (caveType == CaveType.CAVE0 || caveType == CaveType.CAVE1)
        {
            block = caveType.block;
        }
        else if (caveType == CaveType.CAVE6 || caveType == CaveType.CAVE7 || caveType == CaveType.CAVE8 || caveType == CaveType.CAVE9 || caveType == CaveType.CAVE10 || caveType == CaveType.CAVE11 || caveType == CaveType.CAVE12 || caveType == CaveType.CAVE13 || caveType == CaveType.CAVE14) {
            block = caveType.block.withProperty(VARIANT_HOTROCKS, HotRocks.EnumType.byMetadata(caveType.meta));
        } else block = caveType.block.withProperty(VARIANT_SUPERCOMPRESSED, BlocksBase.EnumType.byMetadata(caveType.meta));

        int generatedCubeX = generatedCubePos.getX();
        int generatedCubeY = generatedCubePos.getY();
        int generatedCubeZ = generatedCubePos.getZ();
        int minX = boundingBox.minX;
        int maxX = boundingBox.maxX;
        int minY = boundingBox.minY;
        int maxY = boundingBox.maxY;
        int minZ = boundingBox.minZ;
        int maxZ = boundingBox.maxZ;
        for (int localX = minX; localX < maxX; ++localX) {
            double distX = normalizedDistance(generatedCubeX, localX, caveX, caveSizeHoriz);
            for (int localZ = minZ; localZ < maxZ; ++localZ) {
                double distZ = normalizedDistance(generatedCubeZ, localZ, caveZ, caveSizeHoriz);
                if (distX * distX + distZ * distZ >= 1.0D) {
                    continue;
                }
                for (int localY = minY; localY < maxY; ++localY) {
                    double distY = normalizedDistance(generatedCubeY, localY, caveY, caveSizeVert);
                    IBlockState state = cube.getBlockState(localX, localY, localZ);
                    if (!isBlockReplaceable.test(state)) {
                        continue;
                    }
                    if (shouldCarveBlock(distX, distY, distZ)) {
                        // No lava generation, infinite depth. Lava will be generated differently (or not generated)
                        if (!REPLACE_WITH_AIR) {
                            cube.setBlockState(localX, localY, localZ, block);
                        }
                        if (REPLACE_WITH_AIR) {
                            cube.setBlockState(localX, localY, localZ, ModBlocks.AIR_NO_PRESSURE.getDefaultState());
                            if (Coords.localToBlock(generatedCubeY, localY) <= lavaHeight) {
                                cube.setBlockState(localX, localY, localZ, caveType.lavaMaterial);
                            }
                        }

                    } else if (state.getBlock() == Blocks.DIRT) {
                        //vanilla dirt-grass replacement works by scanning top-down and moving the block
                        //cubic chunks needs to be a bit more hacky about it
                        //instead of keeping track of the encountered grass block
                        //cubic chunks replaces any dirt block (it's before population, no ore-like dirt formations yet)
                        //with grass, if the block above would be deleted by this cave generator step
                        double distYAbove = normalizedDistance(generatedCubeY, localY + 1, caveY, caveSizeVert);
                        if (shouldCarveBlock(distX, distYAbove, distZ)) {
                            cube.setBlockState(localX, localY, localZ, Blocks.GRASS.getDefaultState());
                        }
                    }
                }
            }
        }
    }

    private static boolean shouldCarveBlock(double distX, double distY, double distZ) {
        //distY > CAVE_FLOOR_DEPTH --> flattened floor
        return distY > CAVE_FLOOR_DEPTH && distX * distX + distY * distY + distZ * distZ < 1.0D;
    }
}