package thaumcraft.common.golems.seals;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Seal Placer - Item used to place golem command seals on blocks.
 * Different variants exist for different seal types (gather, guard, use, etc.)
 * 
 * Note: The full seal system requires golem entities and AI.
 * This is a placeholder implementation for the item itself.
 */
public class ItemSealPlacer extends Item {

    private final String sealType;

    public ItemSealPlacer(String sealType) {
        super(new Item.Properties()
                .stacksTo(64));
        this.sealType = sealType;
    }

    public static ItemSealPlacer createBlank() {
        return new ItemSealPlacer("blank");
    }

    public String getSealType() {
        return sealType;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction side = context.getClickedFace();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();

        if (player == null) {
            return InteractionResult.FAIL;
        }

        // Blank seals can't be placed
        if (sealType.equals("blank")) {
            return InteractionResult.PASS;
        }

        // Sneaking bypasses placement
        if (player.isShiftKeyDown()) {
            return InteractionResult.PASS;
        }

        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        // Check if player can edit at this position
        if (!player.mayUseItemAt(pos, side, stack)) {
            return InteractionResult.FAIL;
        }

        // TODO: Check if seal can be placed at this position
        // TODO: Create and place the seal entity
        // For now, just send a message that this isn't implemented
        player.sendSystemMessage(Component.translatable("item.thaumcraft.seal.notimplemented"));
        
        return InteractionResult.PASS;
    }

    @Override
    public boolean doesSneakBypassUse(ItemStack stack, net.minecraft.world.level.LevelReader level, 
                                       BlockPos pos, Player player) {
        return true;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        if (!sealType.equals("blank")) {
            tooltip.add(Component.translatable("seal." + sealType + ".desc")
                    .withStyle(style -> style.withColor(0x808080)));
        }
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
