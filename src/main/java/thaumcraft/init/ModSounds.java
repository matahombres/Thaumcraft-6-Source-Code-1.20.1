package thaumcraft.init;

import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import thaumcraft.Thaumcraft;

/**
 * Registry for all Thaumcraft sound events.
 * Uses DeferredRegister for 1.20.1 Forge.
 * 
 * TODO: Port all sounds from SoundsTC.java
 */
public class ModSounds {
    
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = 
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Thaumcraft.MODID);
    
    /*
     * Sounds to port from SoundsTC.java:
     * - Wand sounds
     * - Focus sounds  
     * - Crafting sounds
     * - Research sounds
     * - Infusion sounds
     * - Entity sounds
     * - Ambient sounds
     * - etc.
     */
}
