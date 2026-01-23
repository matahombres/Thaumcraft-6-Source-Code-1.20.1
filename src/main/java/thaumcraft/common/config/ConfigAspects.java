package thaumcraft.common.config;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.AspectRegistryEvent;

public class ConfigAspects {

    public static void init() {
        registerItemAspects();
        registerEntityAspects();
        
        AspectRegistryEvent are = new AspectRegistryEvent();
        MinecraftForge.EVENT_BUS.post(are);
    }

    
    private static void registerEntityAspects() {
        ThaumcraftApi.registerEntityTag("minecraft:zombie", new AspectList().add(Aspect.UNDEAD, 20).add(Aspect.MAN, 10).add(Aspect.EARTH, 5));
        ThaumcraftApi.registerEntityTag("minecraft:husk", new AspectList().add(Aspect.UNDEAD, 20).add(Aspect.MAN, 10).add(Aspect.FIRE, 5));
        ThaumcraftApi.registerEntityTag("minecraft:giant", new AspectList().add(Aspect.UNDEAD, 25).add(Aspect.MAN, 15).add(Aspect.EARTH, 10));
        ThaumcraftApi.registerEntityTag("minecraft:skeleton", new AspectList().add(Aspect.UNDEAD, 20).add(Aspect.MAN, 5).add(Aspect.EARTH, 5));
        ThaumcraftApi.registerEntityTag("minecraft:wither_skeleton", new AspectList().add(Aspect.UNDEAD, 25).add(Aspect.MAN, 5).add(Aspect.ENTROPY, 10));
        ThaumcraftApi.registerEntityTag("minecraft:creeper", new AspectList().add(Aspect.PLANT, 15).add(Aspect.FIRE, 15));
        // ThaumcraftApi.registerEntityTag("minecraft:creeper", new AspectList().add(Aspect.PLANT, 15).add(Aspect.FIRE, 15).add(Aspect.ENERGY, 15), new ThaumcraftApi.EntityTagsNBT("powered", 1));
        ThaumcraftApi.registerEntityTag("minecraft:horse", new AspectList().add(Aspect.BEAST, 15).add(Aspect.EARTH, 5).add(Aspect.AIR, 5));
        ThaumcraftApi.registerEntityTag("minecraft:donkey", new AspectList().add(Aspect.BEAST, 15).add(Aspect.EARTH, 5).add(Aspect.AIR, 5));
        ThaumcraftApi.registerEntityTag("minecraft:mule", new AspectList().add(Aspect.BEAST, 15).add(Aspect.EARTH, 5).add(Aspect.AIR, 5));
        ThaumcraftApi.registerEntityTag("minecraft:skeleton_horse", new AspectList().add(Aspect.BEAST, 5).add(Aspect.UNDEAD, 10).add(Aspect.EARTH, 5).add(Aspect.AIR, 5));
        ThaumcraftApi.registerEntityTag("minecraft:zombie_horse", new AspectList().add(Aspect.BEAST, 10).add(Aspect.UNDEAD, 5).add(Aspect.EARTH, 5).add(Aspect.AIR, 5));
        ThaumcraftApi.registerEntityTag("minecraft:pig", new AspectList().add(Aspect.BEAST, 10).add(Aspect.EARTH, 10).add(Aspect.DESIRE, 5));
        ThaumcraftApi.registerEntityTag("minecraft:experience_orb", new AspectList().add(Aspect.MIND, 10));
        ThaumcraftApi.registerEntityTag("minecraft:sheep", new AspectList().add(Aspect.BEAST, 10).add(Aspect.EARTH, 10));
        ThaumcraftApi.registerEntityTag("minecraft:cow", new AspectList().add(Aspect.BEAST, 15).add(Aspect.EARTH, 15));
        ThaumcraftApi.registerEntityTag("minecraft:mooshroom", new AspectList().add(Aspect.BEAST, 15).add(Aspect.PLANT, 15).add(Aspect.EARTH, 15));
        ThaumcraftApi.registerEntityTag("minecraft:snow_golem", new AspectList().add(Aspect.COLD, 10).add(Aspect.MAN, 5).add(Aspect.MECHANISM, 5).add(Aspect.MAGIC, 5));
        ThaumcraftApi.registerEntityTag("minecraft:ocelot", new AspectList().add(Aspect.BEAST, 10).add(Aspect.ENTROPY, 10));
        ThaumcraftApi.registerEntityTag("minecraft:chicken", new AspectList().add(Aspect.BEAST, 5).add(Aspect.FLIGHT, 5).add(Aspect.AIR, 5));
        ThaumcraftApi.registerEntityTag("minecraft:squid", new AspectList().add(Aspect.BEAST, 5).add(Aspect.WATER, 10));
        ThaumcraftApi.registerEntityTag("minecraft:wolf", new AspectList().add(Aspect.BEAST, 15).add(Aspect.EARTH, 10).add(Aspect.AVERSION, 5));
        ThaumcraftApi.registerEntityTag("minecraft:bat", new AspectList().add(Aspect.BEAST, 5).add(Aspect.FLIGHT, 5).add(Aspect.DARKNESS, 5));
        ThaumcraftApi.registerEntityTag("minecraft:spider", new AspectList().add(Aspect.BEAST, 10).add(Aspect.ENTROPY, 10).add(Aspect.TRAP, 10));
        ThaumcraftApi.registerEntityTag("minecraft:slime", new AspectList().add(Aspect.LIFE, 10).add(Aspect.WATER, 10).add(Aspect.ALCHEMY, 5));
        ThaumcraftApi.registerEntityTag("minecraft:ghast", new AspectList().add(Aspect.UNDEAD, 15).add(Aspect.FIRE, 15));
        ThaumcraftApi.registerEntityTag("minecraft:zombified_piglin", new AspectList().add(Aspect.UNDEAD, 15).add(Aspect.FIRE, 15).add(Aspect.BEAST, 10));
        ThaumcraftApi.registerEntityTag("minecraft:enderman", new AspectList().add(Aspect.ELDRITCH, 10).add(Aspect.MOTION, 15).add(Aspect.DESIRE, 5));
        ThaumcraftApi.registerEntityTag("minecraft:cave_spider", new AspectList().add(Aspect.BEAST, 5).add(Aspect.DEATH, 10).add(Aspect.TRAP, 10));
        ThaumcraftApi.registerEntityTag("minecraft:silverfish", new AspectList().add(Aspect.BEAST, 5).add(Aspect.EARTH, 10));
        ThaumcraftApi.registerEntityTag("minecraft:blaze", new AspectList().add(Aspect.ELDRITCH, 5).add(Aspect.FIRE, 15).add(Aspect.FLIGHT, 5));
        ThaumcraftApi.registerEntityTag("minecraft:magma_cube", new AspectList().add(Aspect.WATER, 5).add(Aspect.FIRE, 10).add(Aspect.ALCHEMY, 5));
        ThaumcraftApi.registerEntityTag("minecraft:ender_dragon", new AspectList().add(Aspect.ELDRITCH, 50).add(Aspect.BEAST, 30).add(Aspect.ENTROPY, 50).add(Aspect.FLIGHT, 10));
        ThaumcraftApi.registerEntityTag("minecraft:wither", new AspectList().add(Aspect.UNDEAD, 50).add(Aspect.ENTROPY, 25).add(Aspect.FIRE, 25));
        ThaumcraftApi.registerEntityTag("minecraft:witch", new AspectList().add(Aspect.MAN, 15).add(Aspect.MAGIC, 5).add(Aspect.ALCHEMY, 10));
        ThaumcraftApi.registerEntityTag("minecraft:villager", new AspectList().add(Aspect.MAN, 15));
        ThaumcraftApi.registerEntityTag("minecraft:iron_golem", new AspectList().add(Aspect.METAL, 15).add(Aspect.MAN, 5).add(Aspect.MECHANISM, 5).add(Aspect.MAGIC, 5));
        ThaumcraftApi.registerEntityTag("minecraft:end_crystal", new AspectList().add(Aspect.ELDRITCH, 15).add(Aspect.AURA, 15).add(Aspect.LIFE, 15));
        // ThaumcraftApi.registerEntityTag("minecraft:item_frame", new AspectList().add(Aspect.SENSES, 5).add(Aspect.CRAFT, 5));
        // ThaumcraftApi.registerEntityTag("minecraft:painting", new AspectList().add(Aspect.SENSES, 10).add(Aspect.CRAFT, 5));
        ThaumcraftApi.registerEntityTag("minecraft:guardian", new AspectList().add(Aspect.BEAST, 10).add(Aspect.ELDRITCH, 10).add(Aspect.WATER, 10));
        ThaumcraftApi.registerEntityTag("minecraft:elder_guardian", new AspectList().add(Aspect.BEAST, 10).add(Aspect.ELDRITCH, 15).add(Aspect.WATER, 15));
        ThaumcraftApi.registerEntityTag("minecraft:rabbit", new AspectList().add(Aspect.BEAST, 5).add(Aspect.EARTH, 5).add(Aspect.MOTION, 5));
        ThaumcraftApi.registerEntityTag("minecraft:endermite", new AspectList().add(Aspect.BEAST, 5).add(Aspect.ELDRITCH, 5).add(Aspect.MOTION, 5));
        ThaumcraftApi.registerEntityTag("minecraft:polar_bear", new AspectList().add(Aspect.BEAST, 15).add(Aspect.COLD, 10));
        ThaumcraftApi.registerEntityTag("minecraft:shulker", new AspectList().add(Aspect.ELDRITCH, 10).add(Aspect.TRAP, 5).add(Aspect.FLIGHT, 5).add(Aspect.PROTECT, 5));
        ThaumcraftApi.registerEntityTag("minecraft:evoker", new AspectList().add(Aspect.ELDRITCH, 5).add(Aspect.MAGIC, 5).add(Aspect.MAN, 10));
        ThaumcraftApi.registerEntityTag("minecraft:vindicator", new AspectList().add(Aspect.AVERSION, 5).add(Aspect.MAGIC, 5).add(Aspect.MAN, 10));
        ThaumcraftApi.registerEntityTag("minecraft:illusioner", new AspectList().add(Aspect.SENSES, 5).add(Aspect.MAGIC, 5).add(Aspect.MAN, 10));
        ThaumcraftApi.registerEntityTag("minecraft:llama", new AspectList().add(Aspect.BEAST, 15).add(Aspect.WATER, 5));
        ThaumcraftApi.registerEntityTag("minecraft:parrot", new AspectList().add(Aspect.BEAST, 5).add(Aspect.FLIGHT, 5).add(Aspect.SENSES, 5));
        ThaumcraftApi.registerEntityTag("minecraft:stray", new AspectList().add(Aspect.UNDEAD, 20).add(Aspect.MAN, 5).add(Aspect.TRAP, 5));
        ThaumcraftApi.registerEntityTag("minecraft:vex", new AspectList().add(Aspect.ELDRITCH, 5).add(Aspect.FLIGHT, 5).add(Aspect.MAGIC, 5).add(Aspect.MAN, 5));
        
        // Thaumcraft entities
        ThaumcraftApi.registerEntityTag("thaumcraft:flux_rift", new AspectList().add(Aspect.FLUX, 20).add(Aspect.ELDRITCH, 20).add(Aspect.AURA, 20));
        ThaumcraftApi.registerEntityTag("thaumcraft:firebat", new AspectList().add(Aspect.BEAST, 5).add(Aspect.FLIGHT, 5).add(Aspect.FIRE, 10));
        ThaumcraftApi.registerEntityTag("thaumcraft:pech", new AspectList().add(Aspect.MAN, 10).add(Aspect.AURA, 5).add(Aspect.EXCHANGE, 10));
        ThaumcraftApi.registerEntityTag("thaumcraft:thaumic_slime", new AspectList().add(Aspect.LIFE, 5).add(Aspect.WATER, 5).add(Aspect.FLUX, 5).add(Aspect.ALCHEMY, 5));
        ThaumcraftApi.registerEntityTag("thaumcraft:brainy_zombie", new AspectList().add(Aspect.UNDEAD, 20).add(Aspect.MAN, 10).add(Aspect.MIND, 5).add(Aspect.AVERSION, 5));
        ThaumcraftApi.registerEntityTag("thaumcraft:giant_brainy_zombie", new AspectList().add(Aspect.UNDEAD, 25).add(Aspect.MAN, 15).add(Aspect.MIND, 5).add(Aspect.AVERSION, 10));
        ThaumcraftApi.registerEntityTag("thaumcraft:taintacle", new AspectList().add(Aspect.FLUX, 15).add(Aspect.BEAST, 10));
        ThaumcraftApi.registerEntityTag("thaumcraft:taint_seed", new AspectList().add(Aspect.FLUX, 20).add(Aspect.AURA, 10).add(Aspect.PLANT, 5));
        ThaumcraftApi.registerEntityTag("thaumcraft:taint_seed_prime", new AspectList().add(Aspect.FLUX, 25).add(Aspect.AURA, 15).add(Aspect.PLANT, 5));
        ThaumcraftApi.registerEntityTag("thaumcraft:taintacle_small", new AspectList().add(Aspect.FLUX, 5).add(Aspect.BEAST, 5));
        ThaumcraftApi.registerEntityTag("thaumcraft:taint_swarm", new AspectList().add(Aspect.FLUX, 15).add(Aspect.AIR, 5));
        ThaumcraftApi.registerEntityTag("thaumcraft:mind_spider", new AspectList().add(Aspect.FLUX, 5).add(Aspect.FIRE, 5));
        ThaumcraftApi.registerEntityTag("thaumcraft:eldritch_guardian", new AspectList().add(Aspect.ELDRITCH, 20).add(Aspect.DEATH, 20).add(Aspect.UNDEAD, 20));
        ThaumcraftApi.registerEntityTag("thaumcraft:cultist_knight", new AspectList().add(Aspect.ELDRITCH, 5).add(Aspect.MAN, 15).add(Aspect.AVERSION, 5));
        ThaumcraftApi.registerEntityTag("thaumcraft:cultist_cleric", new AspectList().add(Aspect.ELDRITCH, 5).add(Aspect.MAN, 15).add(Aspect.AVERSION, 5));
        ThaumcraftApi.registerEntityTag("thaumcraft:eldritch_crab", new AspectList().add(Aspect.ELDRITCH, 10).add(Aspect.BEAST, 10).add(Aspect.TRAP, 10));
        ThaumcraftApi.registerEntityTag("thaumcraft:inhabited_zombie", new AspectList().add(Aspect.ELDRITCH, 10).add(Aspect.UNDEAD, 10).add(Aspect.MAN, 5));
        ThaumcraftApi.registerEntityTag("thaumcraft:eldritch_warden", new AspectList().add(Aspect.ELDRITCH, 40).add(Aspect.DEATH, 40).add(Aspect.UNDEAD, 40));
        ThaumcraftApi.registerEntityTag("thaumcraft:eldritch_golem", new AspectList().add(Aspect.ELDRITCH, 40).add(Aspect.ENERGY, 40).add(Aspect.MECHANISM, 40));
        ThaumcraftApi.registerEntityTag("thaumcraft:cultist_leader", new AspectList().add(Aspect.ELDRITCH, 40).add(Aspect.AVERSION, 40).add(Aspect.MAN, 40));
        ThaumcraftApi.registerEntityTag("thaumcraft:taintacle_giant", new AspectList().add(Aspect.ELDRITCH, 40).add(Aspect.BEAST, 40).add(Aspect.FLUX, 40));
        ThaumcraftApi.registerEntityTag("thaumcraft:golem", new AspectList().add(Aspect.MECHANISM, 10).add(Aspect.MAN, 10).add(Aspect.MOTION, 10));
        ThaumcraftApi.registerEntityTag("thaumcraft:wisp", new AspectList().add(Aspect.AURA, 10).add(Aspect.FLIGHT, 5));
    }
    
    private static void registerItemAspects() {
        // Basic Ores - Using forge tags for compatibility
        ThaumcraftApi.registerObjectTag("forge:ores/lapis", new AspectList().add(Aspect.EARTH, 5).add(Aspect.SENSES, 15));
        ThaumcraftApi.registerObjectTag("forge:ores/diamond", new AspectList().add(Aspect.EARTH, 5).add(Aspect.DESIRE, 15).add(Aspect.CRYSTAL, 15));
        ThaumcraftApi.registerObjectTag("forge:gems/diamond", new AspectList().add(Aspect.CRYSTAL, 15).add(Aspect.DESIRE, 15));
        ThaumcraftApi.registerObjectTag("forge:ores/redstone", new AspectList().add(Aspect.EARTH, 5).add(Aspect.ENERGY, 15));
        ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.REDSTONE_ORE), new AspectList().add(Aspect.EARTH, 5).add(Aspect.ENERGY, 15));
        ThaumcraftApi.registerObjectTag("forge:ores/emerald", new AspectList().add(Aspect.EARTH, 5).add(Aspect.DESIRE, 10).add(Aspect.CRYSTAL, 15));
        ThaumcraftApi.registerObjectTag("forge:gems/emerald", new AspectList().add(Aspect.CRYSTAL, 15).add(Aspect.DESIRE, 10));
        ThaumcraftApi.registerObjectTag("forge:ores/quartz", new AspectList().add(Aspect.EARTH, 5).add(Aspect.CRYSTAL, 10));
        ThaumcraftApi.registerObjectTag("forge:gems/quartz", new AspectList().add(Aspect.CRYSTAL, 5));
        ThaumcraftApi.registerObjectTag("forge:ores/iron", new AspectList().add(Aspect.EARTH, 5).add(Aspect.METAL, 15));
        ThaumcraftApi.registerObjectTag("forge:dusts/iron", new AspectList().add(Aspect.METAL, 15).add(Aspect.ENTROPY, 1));
        ThaumcraftApi.registerObjectTag("forge:ingots/iron", new AspectList().add(Aspect.METAL, 15));
        ThaumcraftApi.registerObjectTag("forge:ores/gold", new AspectList().add(Aspect.EARTH, 5).add(Aspect.METAL, 10).add(Aspect.DESIRE, 10));
        ThaumcraftApi.registerObjectTag("forge:dusts/gold", new AspectList().add(Aspect.METAL, 10).add(Aspect.DESIRE, 10).add(Aspect.ENTROPY, 1));
        ThaumcraftApi.registerObjectTag("forge:ingots/gold", new AspectList().add(Aspect.METAL, 10).add(Aspect.DESIRE, 10));
        ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.COAL_ORE), new AspectList().add(Aspect.EARTH, 5).add(Aspect.ENERGY, 15).add(Aspect.FIRE, 15));
        ThaumcraftApi.registerObjectTag(new ItemStack(Items.COAL), new AspectList().add(Aspect.ENERGY, 10).add(Aspect.FIRE, 10));
        ThaumcraftApi.registerObjectTag("forge:dusts/redstone", new AspectList().add(Aspect.ENERGY, 10));
        ThaumcraftApi.registerObjectTag("forge:dusts/glowstone", new AspectList().add(Aspect.SENSES, 5).add(Aspect.LIGHT, 10));
        ThaumcraftApi.registerObjectTag("forge:ingots/copper", new AspectList().add(Aspect.METAL, 10).add(Aspect.EXCHANGE, 5));
        ThaumcraftApi.registerObjectTag("forge:dusts/copper", new AspectList().add(Aspect.METAL, 10).add(Aspect.ENTROPY, 1).add(Aspect.EXCHANGE, 5));
        ThaumcraftApi.registerObjectTag("forge:ores/copper", new AspectList().add(Aspect.METAL, 10).add(Aspect.EARTH, 5).add(Aspect.EXCHANGE, 5));
        
        // Basic Blocks
        ThaumcraftApi.registerObjectTag("forge:stone", new AspectList().add(Aspect.EARTH, 5));
        ThaumcraftApi.registerObjectTag("forge:cobblestone", new AspectList().add(Aspect.EARTH, 5).add(Aspect.ENTROPY, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.BEDROCK), new AspectList().add(Aspect.VOID, 25).add(Aspect.ENTROPY, 25).add(Aspect.EARTH, 25).add(Aspect.DARKNESS, 25));
        ThaumcraftApi.registerObjectTag("forge:dirt", new AspectList().add(Aspect.EARTH, 5));
        ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.PODZOL), new AspectList().add(Aspect.EARTH, 5).add(Aspect.PLANT, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.FARMLAND), new AspectList().add(Aspect.EARTH, 5).add(Aspect.WATER, 2).add(Aspect.ORDER, 2));
        ThaumcraftApi.registerObjectTag("forge:sand", new AspectList().add(Aspect.EARTH, 5).add(Aspect.ENTROPY, 5));
        ThaumcraftApi.registerObjectTag("minecraft:grass", new AspectList().add(Aspect.EARTH, 5).add(Aspect.PLANT, 2)); // Not sure if this tag exists
        ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.GRASS_BLOCK), new AspectList().add(Aspect.EARTH, 5).add(Aspect.PLANT, 2));
        ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.DIRT_PATH), new AspectList().add(Aspect.EARTH, 5).add(Aspect.PLANT, 2).add(Aspect.ORDER, 2));
        ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.END_STONE), new AspectList().add(Aspect.EARTH, 5).add(Aspect.DARKNESS, 5));
        ThaumcraftApi.registerObjectTag("forge:gravel", new AspectList().add(Aspect.EARTH, 5).add(Aspect.ENTROPY, 2));
        ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.MYCELIUM), new AspectList().add(Aspect.EARTH, 5).add(Aspect.PLANT, 1).add(Aspect.FLUX, 1));
        
        // Clay
        ThaumcraftApi.registerObjectTag(new ItemStack(Items.CLAY_BALL), new AspectList().add(Aspect.WATER, 5).add(Aspect.EARTH, 5));
        
        // Nether
        ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.NETHERRACK), new AspectList().add(Aspect.EARTH, 5).add(Aspect.FIRE, 2));
        ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.SOUL_SAND), new AspectList().add(Aspect.EARTH, 3).add(Aspect.TRAP, 1).add(Aspect.SOUL, 3));
        
        // Obsidian
        ThaumcraftApi.registerObjectTag("forge:obsidian", new AspectList().add(Aspect.EARTH, 5).add(Aspect.FIRE, 5).add(Aspect.DARKNESS, 5));
        
        // Wood
        ThaumcraftApi.registerObjectTag("minecraft:logs", new AspectList().add(Aspect.PLANT, 20));
        ThaumcraftApi.registerObjectTag("minecraft:saplings", new AspectList().add(Aspect.PLANT, 15).add(Aspect.LIFE, 5));
        ThaumcraftApi.registerObjectTag("minecraft:leaves", new AspectList().add(Aspect.PLANT, 5));
        
        // Other
        ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.ICE), new AspectList().add(Aspect.COLD, 20));
        ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.PACKED_ICE), new AspectList().add(Aspect.COLD, 15).add(Aspect.ORDER, 5));
        ThaumcraftApi.registerObjectTag(new ItemStack(Items.SNOWBALL), new AspectList().add(Aspect.COLD, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(Items.SLIME_BALL), new AspectList().add(Aspect.WATER, 5).add(Aspect.LIFE, 5).add(Aspect.ALCHEMY, 1));
        ThaumcraftApi.registerObjectTag("forge:leather", new AspectList().add(Aspect.BEAST, 5).add(Aspect.PROTECT, 5));
        ThaumcraftApi.registerObjectTag(new ItemStack(Items.ROTTEN_FLESH), new AspectList().add(Aspect.MAN, 5).add(Aspect.LIFE, 5).add(Aspect.ENTROPY, 5));
        ThaumcraftApi.registerObjectTag(new ItemStack(Items.FEATHER), new AspectList().add(Aspect.FLIGHT, 5).add(Aspect.AIR, 5));
        ThaumcraftApi.registerObjectTag(new ItemStack(Items.BONE), new AspectList().add(Aspect.DEATH, 5).add(Aspect.LIFE, 5));
        ThaumcraftApi.registerObjectTag(new ItemStack(Items.EGG), new AspectList().add(Aspect.LIFE, 5).add(Aspect.BEAST, 5));
        ThaumcraftApi.registerObjectTag(new ItemStack(Items.GUNPOWDER), new AspectList().add(Aspect.FIRE, 10).add(Aspect.ENTROPY, 10).add(Aspect.ALCHEMY, 5));
    }
}
