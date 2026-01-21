package thaumcraft.api.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

/**
 * Helper class for managing rechargeable item charge levels.
 */
public class RechargeHelper {
    
    private static final String CHARGE_KEY = "tc_charge";
    
    /**
     * Get the current charge level of a rechargeable item.
     */
    public static int getCharge(ItemStack stack) {
        if (stack.isEmpty() || !(stack.getItem() instanceof IRechargable)) {
            return 0;
        }
        CompoundTag tag = stack.getTag();
        return tag != null ? tag.getInt(CHARGE_KEY) : 0;
    }
    
    /**
     * Get the current charge as a ratio (0.0 to 1.0).
     */
    public static float getChargeRatio(ItemStack stack, LivingEntity entity) {
        if (stack.isEmpty() || !(stack.getItem() instanceof IRechargable rechargable)) {
            return 0.0f;
        }
        int max = rechargable.getMaxCharge(stack, entity);
        if (max <= 0) return 0.0f;
        return (float) getCharge(stack) / max;
    }
    
    /**
     * Set the charge level of a rechargeable item.
     */
    public static void setCharge(ItemStack stack, int charge, LivingEntity entity) {
        if (stack.isEmpty() || !(stack.getItem() instanceof IRechargable rechargable)) {
            return;
        }
        int max = rechargable.getMaxCharge(stack, entity);
        charge = Math.max(0, Math.min(charge, max));
        stack.getOrCreateTag().putInt(CHARGE_KEY, charge);
    }
    
    /**
     * Add charge to a rechargeable item.
     * @return The amount actually added
     */
    public static int addCharge(ItemStack stack, int amount, LivingEntity entity) {
        if (stack.isEmpty() || !(stack.getItem() instanceof IRechargable rechargable)) {
            return 0;
        }
        int current = getCharge(stack);
        int max = rechargable.getMaxCharge(stack, entity);
        int newCharge = Math.min(current + amount, max);
        int added = newCharge - current;
        
        if (added > 0) {
            setCharge(stack, newCharge, entity);
        }
        return added;
    }
    
    /**
     * Consume charge from a rechargeable item.
     * @return True if the charge was successfully consumed
     */
    public static boolean consumeCharge(ItemStack stack, LivingEntity entity, int amount) {
        if (stack.isEmpty() || !(stack.getItem() instanceof IRechargable)) {
            return false;
        }
        int current = getCharge(stack);
        if (current < amount) {
            return false;
        }
        setCharge(stack, current - amount, entity);
        return true;
    }
    
    /**
     * Check if the item has enough charge.
     */
    public static boolean hasCharge(ItemStack stack, int amount) {
        return getCharge(stack) >= amount;
    }
    
    /**
     * Fully recharge an item.
     */
    public static void fullRecharge(ItemStack stack, LivingEntity entity) {
        if (stack.isEmpty() || !(stack.getItem() instanceof IRechargable rechargable)) {
            return;
        }
        setCharge(stack, rechargable.getMaxCharge(stack, entity), entity);
    }
}
