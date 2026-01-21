package thaumcraft;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import org.slf4j.Logger;
import thaumcraft.init.ModBlocks;
import thaumcraft.init.ModItems;
import thaumcraft.init.ModCreativeTabs;
import thaumcraft.init.ModEntities;
import thaumcraft.init.ModEffects;
import thaumcraft.init.ModSounds;
import thaumcraft.init.ModBlockEntities;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.items.casters.FocusInit;

/**
 * Thaumcraft - A mod about discovering the arcane and harnessing the power of magic.
 * Originally created by Azanor.
 * Ported to 1.20.1 from 1.12.2 decompiled source.
 */
@Mod(Thaumcraft.MODID)
public class Thaumcraft {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "thaumcraft";
    public static final String MODNAME = "Thaumcraft";
    public static final String VERSION = "6.2.0";
    
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    
    // Singleton instance
    private static Thaumcraft instance;
    
    public Thaumcraft() {
        instance = this;
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register Deferred Registers to the mod event bus
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlocks.BLOCK_ITEMS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModEffects.MOB_EFFECTS.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        // ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ThaumcraftConfig.SPEC);
    }
    
    public static Thaumcraft getInstance() {
        return instance;
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("Thaumcraft common setup");
        
        // Initialize network system
        PacketHandler.init();
        
        // Perform setup that must happen after all registry events
        event.enqueueWork(() -> {
            // Initialize focus system
            FocusInit.registerFoci();
            LOGGER.info("Registered {} focus elements", FocusInit.getAllFocusKeys().length);
            
            // Initialize aspect registry (item/block -> aspects mapping)
            // ConfigAspects.init();
            
            // Initialize research system
            // ConfigResearch.init();
            
            // Initialize recipe system
            // ConfigRecipes.init();
            
            LOGGER.info("Thaumcraft setup complete!");
        });
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Register commands
        // CommandThaumcraft.register(event.getServer().getCommands().getDispatcher());
        LOGGER.info("Thaumcraft server starting");
    }

    // Entity attribute registration
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEvents {
        @SubscribeEvent
        public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
            // Monster entities
            event.put(ModEntities.WISP.get(), 
                    thaumcraft.common.entities.monster.EntityWisp.createAttributes().build());
            event.put(ModEntities.FIRE_BAT.get(), 
                    thaumcraft.common.entities.monster.EntityFireBat.createAttributes().build());
            event.put(ModEntities.BRAINY_ZOMBIE.get(), 
                    thaumcraft.common.entities.monster.EntityBrainyZombie.createAttributes().build());
            event.put(ModEntities.MIND_SPIDER.get(), 
                    thaumcraft.common.entities.monster.EntityMindSpider.createAttributes().build());
            event.put(ModEntities.THAUMIC_SLIME.get(), 
                    thaumcraft.common.entities.monster.EntityThaumicSlime.createAttributes().build());
            event.put(ModEntities.GIANT_BRAINY_ZOMBIE.get(), 
                    thaumcraft.common.entities.monster.EntityGiantBrainyZombie.createAttributes().build());
            event.put(ModEntities.INHABITED_ZOMBIE.get(), 
                    thaumcraft.common.entities.monster.EntityInhabitedZombie.createAttributes().build());
            
            event.put(ModEntities.ELDRITCH_CRAB.get(), 
                    thaumcraft.common.entities.monster.EntityEldritchCrab.createAttributes().build());
            event.put(ModEntities.SPELL_BAT.get(), 
                    thaumcraft.common.entities.monster.EntitySpellBat.createAttributes().build());
            
            // Tainted entities
            event.put(ModEntities.TAINT_CRAWLER.get(), 
                    thaumcraft.common.entities.monster.tainted.EntityTaintCrawler.createAttributes().build());
            event.put(ModEntities.TAINT_SWARM.get(), 
                    thaumcraft.common.entities.monster.tainted.EntityTaintSwarm.createAttributes().build());
            event.put(ModEntities.TAINTACLE.get(), 
                    thaumcraft.common.entities.monster.tainted.EntityTaintacle.createAttributes().build());
            event.put(ModEntities.TAINTACLE_SMALL.get(), 
                    thaumcraft.common.entities.monster.tainted.EntityTaintacleSmall.createAttributes().build());
            event.put(ModEntities.TAINT_SEED.get(), 
                    thaumcraft.common.entities.monster.tainted.EntityTaintSeed.createAttributes().build());
            event.put(ModEntities.TAINT_SEED_PRIME.get(), 
                    thaumcraft.common.entities.monster.tainted.EntityTaintSeedPrime.createAttributes().build());
            
            // Eldritch entities
            event.put(ModEntities.ELDRITCH_GUARDIAN.get(), 
                    thaumcraft.common.entities.monster.EntityEldritchGuardian.createAttributes().build());
            
            // Cult entities
            event.put(ModEntities.CULTIST.get(), 
                    thaumcraft.common.entities.monster.cult.EntityCultist.createAttributes().build());
            event.put(ModEntities.CULTIST_KNIGHT.get(), 
                    thaumcraft.common.entities.monster.cult.EntityCultistKnight.createAttributes().build());
            event.put(ModEntities.CULTIST_CLERIC.get(), 
                    thaumcraft.common.entities.monster.cult.EntityCultistCleric.createAttributes().build());
            event.put(ModEntities.CULTIST_PORTAL_LESSER.get(), 
                    thaumcraft.common.entities.monster.cult.EntityCultistPortalLesser.createAttributes().build());
            
            // Boss entities
            event.put(ModEntities.CULTIST_LEADER.get(), 
                    thaumcraft.common.entities.monster.boss.EntityCultistLeader.createAttributes().build());
            event.put(ModEntities.TAINTACLE_GIANT.get(), 
                    thaumcraft.common.entities.monster.boss.EntityTaintacleGiant.createAttributes().build());
            event.put(ModEntities.CULTIST_PORTAL_GREATER.get(), 
                    thaumcraft.common.entities.monster.boss.EntityCultistPortalGreater.createAttributes().build());
            event.put(ModEntities.ELDRITCH_GOLEM.get(), 
                    thaumcraft.common.entities.monster.boss.EntityEldritchGolem.createAttributes().build());
            event.put(ModEntities.ELDRITCH_WARDEN.get(), 
                    thaumcraft.common.entities.monster.boss.EntityEldritchWarden.createAttributes().build());
            
            // Pech
            event.put(ModEntities.PECH.get(), 
                    thaumcraft.common.entities.monster.EntityPech.createAttributes().build());
            
            // Construct entities
            event.put(ModEntities.TURRET_CROSSBOW.get(), 
                    thaumcraft.common.entities.construct.EntityTurretCrossbow.createAttributes().build());
            event.put(ModEntities.TURRET_CROSSBOW_ADVANCED.get(), 
                    thaumcraft.common.entities.construct.EntityTurretCrossbowAdvanced.createAttributes().build());
            
            LOGGER.info("Registered Thaumcraft entity attributes");
        }
    }
    
    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("Thaumcraft client setup");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
            
            // Register entity renderers
            // ProxyEntities.setupEntityRenderers();
            
            // Register block entity renderers
            // ProxyTESR.setupTESR();
            
            // Register key bindings
            // KeyHandler.registerKeyBindings();
            
            // Register color handlers
            // ColorHandler.registerColourHandlers();
        }
    }
    
    /**
     * Get the client world (client side only)
     */
    public static Level getClientWorld() {
        return Minecraft.getInstance().level;
    }
    
    /**
     * Check if shift key is pressed (client side only)
     */
    public static boolean isShiftKeyDown() {
        return net.minecraft.client.gui.screens.Screen.hasShiftDown();
    }
}
