package com.piinut.taicraft.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.piinut.taicraft.Main;
import com.piinut.taicraft.container.SteamerContainer;
import net.minecraft.client.gui.screen.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class SteamerScreen extends ContainerScreen<SteamerContainer> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/gui/steamer.png");
    private boolean widthTooNarrow;

    public SteamerScreen(SteamerContainer container, PlayerInventory inventory, ITextComponent text) {
        super(container, inventory, text);
    }

    @Override
    public void init() {
        super.init();
        this.widthTooNarrow = this.width < 379;
        this.leftPos = (this.width-this.imageWidth)/2;
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
    public void render(MatrixStack stack, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        this.renderBackground(stack);
        if (this.widthTooNarrow) {
            this.renderBg(stack, p_230430_4_, p_230430_2_, p_230430_3_);
        } else {
            super.render(stack, p_230430_2_, p_230430_3_, p_230430_4_);
        }

        this.renderTooltip(stack, p_230430_2_, p_230430_3_);
    }

    @Override
    protected void renderBg(MatrixStack stack, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(TEXTURE);
        int i = this.leftPos;
        int j = this.topPos;
        this.blit(stack, i, j, 0, 0, this.imageWidth, this.imageHeight);
        if (this.menu.isLit()) {
            int k = this.menu.getLitProgress();
            this.blit(stack, i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        int l = this.menu.getBurnProgress();
        this.blit(stack, i + 79, j + 34, 176, 14, l + 1, 16);
    }
}
