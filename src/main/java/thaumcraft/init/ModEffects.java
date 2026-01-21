package thaumcraft.init;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import thaumcraft.Thaumcraft;

/**
 * Registry for all Thaumcraft mob effects (potions).
 * Uses DeferredRegister for 1.20.1 Forge.
 * 
 * TODO: Port all potions from Registrar.java
 */
public class ModEffects {
    
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = 
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Thaumcraft.MODID);
    
    /*
     * Effects to port:
     * - Flux Taint
     * - Vis Exhaust
     * - Infectious Vis Exhaust
     * - Unnatural Hunger
     * - Warp Ward
     * - Death Gaze
     * - Blurred Vision
     * - Sun Scorned
     * - Thaumarhia
     */
}
