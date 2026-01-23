package thaumcraft.client.gui.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import thaumcraft.Thaumcraft;
import thaumcraft.common.menu.FocalManipulatorMenu;

/**
 * FocalManipulatorScreen - Client-side GUI for the Focal Manipulator.
 * 
 * Displays the focus slot and manipulation interface.
 * This is a complex GUI with focus effect editing.
 * TODO: Implement full focus manipulation UI
 */
@OnlyIn(Dist.CLIENT)
public class FocalManipulatorScreen extends AbstractContainerScreen<FocalManipulatorMenu> {
    
    private static final ResourceLocation TEXTURE = 
            new ResourceLocation(Thaumcraft.MODID, "textures/gui/gui_focalmanipulator.png");
    
    public FocalManipulatorScreen(FocalManipulatorMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        // Focal manipulator has a unique large GUI
        this.imageWidth = 234;
        this.imageHeight = 240;
    }
    
    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTick);
        this.renderTooltip(graphics, mouseX, mouseY);
    }
    
    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        int x = this.leftPos;
        int y = this.topPos;
        
        // Draw main background
        graphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);
    }
    
    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        // Custom title position for large GUI
        graphics.drawString(this.font, this.title, 8, 6, 0x404040, false);
    }
}
