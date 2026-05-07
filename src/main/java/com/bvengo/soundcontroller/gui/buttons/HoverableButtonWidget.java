package com.bvengo.soundcontroller.gui.buttons;

import com.bvengo.soundcontroller.SoundController;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.resources.Identifier;

/**
 * Custom button widget that is used as a trigger rather than a toggle.
 * i.e. it is only active while the button is being pressed.
 */
@Environment(EnvType.CLIENT)
public class HoverableButtonWidget extends Button {
    protected boolean isPressed = false;

    protected final Identifier ON_TEXTURE;
    protected final Identifier OFF_TEXTURE;
    protected final Identifier ON_HOVER_TEXTURE;
    protected final Identifier OFF_HOVER_TEXTURE;

    String buttonId;

    public HoverableButtonWidget(String buttonId, int x, int y, int width, int height, OnPress onPress) {
        super(x, y, width, height, CommonComponents.EMPTY, onPress, DEFAULT_NARRATION);

        this.buttonId = buttonId;

        this.ON_TEXTURE = Identifier.fromNamespaceAndPath(SoundController.MOD_ID, buttonId + "_button_on");
        this.OFF_TEXTURE = Identifier.fromNamespaceAndPath(SoundController.MOD_ID, buttonId + "_button_off");
        this.ON_HOVER_TEXTURE = Identifier.fromNamespaceAndPath(SoundController.MOD_ID, buttonId + "_button_on_hovered");
        this.OFF_HOVER_TEXTURE = Identifier.fromNamespaceAndPath(SoundController.MOD_ID, buttonId + "_button_off_hovered");
    }

    protected Identifier getTextureIdentifier() {
        return this.isPressed ? (this.isHovered ? this.ON_HOVER_TEXTURE : this.ON_TEXTURE)
                : (this.isHovered ? this.OFF_HOVER_TEXTURE : this.OFF_TEXTURE);
    }

    @Override
    public void extractContents(GuiGraphicsExtractor context, int mouseX, int mouseY, float delta) {
        Identifier texture = this.getTextureIdentifier();
        context.blitSprite(RenderPipelines.GUI_TEXTURED, texture, this.getX(), this.getY(), this.width, this.height);
    }
}
