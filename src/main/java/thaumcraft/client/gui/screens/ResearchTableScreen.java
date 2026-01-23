package thaumcraft.client.gui.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import thaumcraft.Thaumcraft;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;
import thaumcraft.common.menu.ResearchTableMenu;
import thaumcraft.common.tiles.crafting.TileResearchTable;

import java.util.List;

/**
 * ResearchTableScreen - Client-side GUI for the Research Table.
 * 
 * Displays theorycraft cards, inspiration meter, and category progress.
 * Allows players to start theories, select cards, and complete research.
 */
public class ResearchTableScreen extends AbstractContainerScreen<ResearchTableMenu> {
    
    private static final ResourceLocation TEXTURE = 
            new ResourceLocation(Thaumcraft.MODID, "textures/gui/gui_researchtable.png");
    
    // Buttons
    private Button startTheoryBtn;
    private Button finishTheoryBtn;
    private Button abandonBtn;
    private Button[] cardButtons = new Button[3];
    
    public ResearchTableScreen(ResearchTableMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 256;
        this.imageHeight = 256;
        this.inventoryLabelY = this.imageHeight - 94;
    }
    
    @Override
    protected void init() {
        super.init();
        
        int x = this.leftPos;
        int y = this.topPos;
        
        // Start Theory button
        startTheoryBtn = addRenderableWidget(Button.builder(
                Component.translatable("gui.thaumcraft.research.start"), 
                btn -> startTheory())
                .bounds(x + 80, y + 130, 96, 20)
                .build());
        
        // Finish Theory button
        finishTheoryBtn = addRenderableWidget(Button.builder(
                Component.translatable("gui.thaumcraft.research.finish"),
                btn -> finishTheory())
                .bounds(x + 180, y + 130, 60, 20)
                .build());
        
        // Abandon button
        abandonBtn = addRenderableWidget(Button.builder(
                Component.translatable("gui.thaumcraft.research.abandon"),
                btn -> abandonTheory())
                .bounds(x + 16, y + 130, 60, 20)
                .build());
        
        // Card selection buttons (3 cards)
        for (int i = 0; i < 3; i++) {
            final int cardIndex = i;
            cardButtons[i] = addRenderableWidget(Button.builder(
                    Component.literal("Card " + (i + 1)),
                    btn -> selectCard(cardIndex))
                    .bounds(x + 16 + i * 78, y + 40, 74, 80)
                    .build());
        }
        
        updateButtonStates();
    }
    
    private void startTheory() {
        if (this.minecraft != null && this.minecraft.gameMode != null) {
            this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 0);
        }
    }
    
    private void finishTheory() {
        if (this.minecraft != null && this.minecraft.gameMode != null) {
            this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 1);
        }
    }
    
    private void abandonTheory() {
        if (this.minecraft != null && this.minecraft.gameMode != null) {
            this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 2);
        }
    }
    
    private void selectCard(int index) {
        if (this.minecraft != null && this.minecraft.gameMode != null) {
            this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 10 + index);
        }
    }
    
    private void updateButtonStates() {
        boolean hasTheory = menu.hasActiveTheory();
        boolean hasTools = menu.hasScribingTools();
        boolean hasPaper = menu.hasPaper();
        
        startTheoryBtn.active = !hasTheory && hasTools && hasPaper;
        startTheoryBtn.visible = !hasTheory;
        
        finishTheoryBtn.active = hasTheory && menu.getInspiration() <= 0;
        finishTheoryBtn.visible = hasTheory;
        
        abandonBtn.active = hasTheory;
        abandonBtn.visible = hasTheory;
        
        // Update card buttons
        TileResearchTable tile = menu.getBlockEntity();
        ResearchTableData data = tile != null ? tile.getResearchData() : null;
        
        for (int i = 0; i < 3; i++) {
            if (hasTheory && data != null && i < data.cardChoices.size()) {
                ResearchTableData.CardChoice choice = data.cardChoices.get(i);
                cardButtons[i].visible = true;
                cardButtons[i].active = !choice.selected && choice.card.getInspirationCost() <= data.inspiration;
                cardButtons[i].setMessage(Component.translatable(choice.card.getLocalizedName()));
            } else {
                cardButtons[i].visible = hasTheory;
                cardButtons[i].active = false;
                cardButtons[i].setMessage(Component.literal("---"));
            }
        }
    }
    
    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        updateButtonStates();
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTick);
        this.renderTooltip(graphics, mouseX, mouseY);
        
        // Render card tooltips
        renderCardTooltips(graphics, mouseX, mouseY);
    }
    
    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        int x = this.leftPos;
        int y = this.topPos;
        
        // Draw main background
        graphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);
        
        // Draw inspiration bar
        if (menu.hasActiveTheory()) {
            int inspiration = menu.getInspiration();
            int maxInspiration = menu.getInspirationStart();
            if (maxInspiration > 0) {
                int barWidth = (int)(150.0f * inspiration / maxInspiration);
                graphics.fill(x + 53, y + 155, x + 53 + barWidth, y + 163, 0xFF4488FF);
            }
        }
    }
    
    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        // Title
        graphics.drawString(this.font, this.title, 8, 6, 0x404040, false);
        
        // Theory status
        if (menu.hasActiveTheory()) {
            int inspiration = menu.getInspiration();
            int maxInspiration = menu.getInspirationStart();
            
            String inspirationText = "Inspiration: " + inspiration + " / " + maxInspiration;
            graphics.drawCenteredString(this.font, inspirationText, 128, 155, 0xFFFFFF);
            
            // Category progress
            TileResearchTable tile = menu.getBlockEntity();
            ResearchTableData data = tile != null ? tile.getResearchData() : null;
            if (data != null) {
                int yPos = 10;
                graphics.drawString(this.font, "Progress:", 180, yPos, 0xFFFFFF, false);
                yPos += 12;
                
                for (var entry : data.categoryTotals.entrySet()) {
                    String catName = entry.getKey();
                    if (catName.length() > 8) catName = catName.substring(0, 8);
                    String text = catName + ": " + entry.getValue();
                    graphics.drawString(this.font, text, 180, yPos, 0xCCCCCC, false);
                    yPos += 10;
                    if (yPos > 120) break;
                }
            }
        } else {
            // Instructions when no theory active
            graphics.drawCenteredString(this.font, 
                    Component.translatable("gui.thaumcraft.research.instructions").getString(), 
                    128, 60, 0x888888);
            
            // Show required materials status
            String toolsStatus = menu.hasScribingTools() ? "\u2713" : "\u2717";
            String paperStatus = menu.hasPaper() ? "\u2713" : "\u2717";
            int toolsColor = menu.hasScribingTools() ? 0x00FF00 : 0xFF0000;
            int paperColor = menu.hasPaper() ? 0x00FF00 : 0xFF0000;
            
            graphics.drawString(this.font, toolsStatus + " Scribing Tools", 80, 90, toolsColor, false);
            graphics.drawString(this.font, paperStatus + " Paper", 80, 102, paperColor, false);
        }
    }
    
    private void renderCardTooltips(GuiGraphics graphics, int mouseX, int mouseY) {
        TileResearchTable tile = menu.getBlockEntity();
        ResearchTableData data = tile != null ? tile.getResearchData() : null;
        if (data == null || !menu.hasActiveTheory()) return;
        
        for (int i = 0; i < Math.min(3, data.cardChoices.size()); i++) {
            if (cardButtons[i].isHovered()) {
                ResearchTableData.CardChoice choice = data.cardChoices.get(i);
                TheorycraftCard card = choice.card;
                
                List<Component> tooltip = List.of(
                        Component.translatable(card.getLocalizedName()),
                        Component.translatable(card.getLocalizedText()),
                        Component.literal("Cost: " + card.getInspirationCost() + " inspiration")
                                .withStyle(style -> style.withColor(0xAAAAFF))
                );
                
                graphics.renderTooltip(this.font, tooltip, java.util.Optional.empty(), mouseX, mouseY);
                break;
            }
        }
    }
    
    @Override
    protected void containerTick() {
        super.containerTick();
        updateButtonStates();
    }
}
