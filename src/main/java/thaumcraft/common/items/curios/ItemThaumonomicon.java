package thaumcraft.common.items.curios;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import thaumcraft.common.items.ItemTC;

import javax.annotation.Nullable;
import java.util.List;

/**
 * The Thaumonomicon - the player's guide to all things Thaumcraft.
 * Opens the research GUI when used.
 */
public class ItemThaumonomicon extends ItemTC {

    public ItemThaumonomicon() {
        super(new Properties()
                .stacksTo(1)
                .rarity(Rarity.UNCOMMON));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (level.isClientSide()) {
            // Client: Open the Thaumonomicon GUI
            openThaumonomiconGui(player);
            return InteractionResultHolder.success(stack);
        }

        // Server: Could sync research data here if needed
        return InteractionResultHolder.consume(stack);
    }

    /**
     * Open the Thaumonomicon GUI on the client.
     */
    private void openThaumonomiconGui(Player player) {
        // TODO: Open the research browser GUI
        // Minecraft.getInstance().setScreen(new GuiResearchBrowser());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.thaumcraft.thaumonomicon.desc"));
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.translatable("item.thaumcraft.thaumonomicon");
    }
}
