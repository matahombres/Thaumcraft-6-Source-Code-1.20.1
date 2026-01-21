package thaumcraft.api.casters;

import thaumcraft.api.aspects.Aspect;

/**
 * Base class for focus modifiers that split the spell into multiple targets.
 * Examples: Fork (multiple projectiles), Scatter (cone of targets)
 */
public abstract class FocusModSplit extends FocusMod {
    
    /**
     * Get the number of splits this modifier creates.
     */
    public abstract int getSplitCount();
    
    /**
     * Get the angle between split trajectories (in degrees).
     */
    public float getSplitAngle() {
        return 15.0f;
    }
    
    @Override
    public EnumSupplyType[] willSupply() {
        return new EnumSupplyType[] { EnumSupplyType.TRAJECTORY };
    }
    
    @Override
    public Aspect getAspect() {
        return Aspect.ENTROPY; // Split modifiers typically use entropy
    }
}
