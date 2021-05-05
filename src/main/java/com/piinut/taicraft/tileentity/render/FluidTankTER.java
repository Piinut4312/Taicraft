package com.piinut.taicraft.tileentity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.piinut.taicraft.tileentity.FluidTankTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class FluidTankTER extends TileEntityRenderer<FluidTankTileEntity> {

    public FluidTankTER(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    private void add(IVertexBuilder builder, MatrixStack matrix, float x, float y, float z, float a, float r, float g, float b, float u, float v, int packedLight){
        builder.vertex(matrix.last().pose(), x, y, z)
                .color(r, g, b, a)
                .uv(u, v)
                .uv2(packedLight)
                .normal(0, 1, 0)
                .endVertex();
    }

    private int getLightLevel(World world, BlockPos pos){
        int bLight = world.getBrightness(LightType.BLOCK, pos);
        int sLight = world.getBrightness(LightType.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }

    private void renderBlockFace(IVertexBuilder builder, MatrixStack matrix, double[] translation, float[] scale
            , Quaternion rotation, int color, TextureAtlasSprite sprite, int light){
        float alpha = (float)(color >> 24 & 255) / 255.0F;
        float r = (float)(color >> 16 & 255) / 255.0F;
        float g = (float)(color >> 8 & 255) / 255.0F;
        float b = (float)(color & 255) / 255.0F;
        float u0 = sprite.getU0();
        float v0 = sprite.getV0();
        float u1 = sprite.getU1();
        float v1 = sprite.getV1();
        matrix.pushPose();
        matrix.translate(translation[0], translation[1], translation[2]);
        matrix.scale(scale[0], scale[1], scale[2]);
        matrix.mulPose(rotation);
        this.add(builder, matrix, 1, 0, 0, alpha, r, g, b, u1, v0, light);
        this.add(builder, matrix, 0, 0, 0, alpha, r, g, b, u0, v0, light);
        this.add(builder, matrix, 0, 0, 1, alpha, r, g, b, u0, v1, light);
        this.add(builder, matrix, 1, 0, 1, alpha, r, g, b, u1, v1, light);
        matrix.popPose();
    }

    private void renderItem(MatrixStack matrix, IRenderTypeBuffer buffer, ItemStack stack, double[] translation
            , float[] scale, Quaternion rotation, int light, int overlay){
        if(!stack.isEmpty()){
            matrix.pushPose();
            matrix.translate(translation[0], translation[1], translation[2]);
            matrix.scale(scale[0], scale[1], scale[2]);
            matrix.mulPose(rotation);
            IBakedModel itemModel = Minecraft.getInstance().getItemRenderer().getModel(stack, null, null);
            Minecraft.getInstance().getItemRenderer().render(stack, ItemCameraTransforms.TransformType.GROUND, true, matrix, buffer, light, overlay, itemModel);
            matrix.popPose();
        }
    }

    @Override
    public void render(FluidTankTileEntity tank, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int light, int overlay) {

        boolean hasFluid = tank.hasFluid();
        float itemY;
        if(hasFluid){
            itemY = 0.875f;
        }else{
            itemY = 0.125f;
        }

        if(hasFluid){
            TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(AtlasTexture.LOCATION_BLOCKS)
                    .apply(tank.getFluid().getAttributes().getStillTexture());
            IVertexBuilder renderer = buffer.getBuffer(RenderType.translucent());
            int color = tank.getFluid().getAttributes().getColor();
            double[] translation = new double[]{0.0625, 0.875, 0.0625};
            float[] scale = new float[]{0.875f, 1f, 0.875f};
            Quaternion rotation = Vector3f.YP.rotationDegrees(0);
            int lightLevel = this.getLightLevel(tank.getLevel(), tank.getBlockPos().above());
            renderBlockFace(renderer, matrixStack, translation, scale, rotation, color, sprite, lightLevel);
        }
        matrixStack.pushPose();
        matrixStack.translate(0.25, itemY, 0.4);
        float[] scale = new float[]{0.6f, 0.6f, 0.6f};
        Quaternion rotation = Vector3f.XP.rotationDegrees(-90);
        for(int i = 0; i < 6; i++){
            ItemStack stack = tank.inventory.getStackInSlot(i);
            double[] translation = new double[]{(i%3)*0.25, 0, (i/3)/3.0};
            renderItem(matrixStack, buffer, stack, translation, scale, rotation, light, overlay);
        }
        matrixStack.popPose();
    }
}
