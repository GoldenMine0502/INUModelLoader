package kr.goldenmine.inumodelloader.inumodelloader.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import kr.goldenmine.inumodelloader.inumodelloader.sign.SignSet;
import kr.goldenmine.inumodelloader.inumodelloader.util.Align;
import kr.goldenmine.inumodelloader.inumodelloader.util.SignInfo;
import kr.goldenmine.inumodelloader.inumodelloader.util.SignText;
import net.minecraft.block.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InuSignTileEntityRenderer extends TileEntityRenderer<InuSignTileEntity> {
    private final SignTileEntityRenderer.SignModel model = new SignTileEntityRenderer.SignModel();

    private Minecraft mc = Minecraft.getInstance();

    public InuSignTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }


    @Override
    public void render(InuSignTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.push();

        renderModel(tileEntityIn, partialTicks, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);

        matrixStackIn.push();
        matrixStackIn.translate(0.0D, 0.33333334D, 0.046666667D); // 그냥 표지판 앞면에 쓰기
        renderText(tileEntityIn, partialTicks, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, new RepositionModelDefault());
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.translate(0.0D, 0.33333334D, -0.046666667D); // 뒷면에 쓰기 (z가 마이너스)
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180)); // 180도 회전하기
        renderText(tileEntityIn, partialTicks, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, new RepositionModelFliped());
        matrixStackIn.pop();

        matrixStackIn.pop();
    }

    public void renderModel(InuSignTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        BlockState blockstate = tileEntityIn.getBlockState();

        final float modelMatrixMultiplier = 0.6666667F;

        if (blockstate.getBlock() instanceof StandingSignBlock) {
            matrixStackIn.translate(0.5D, 0.5D, 0.5D);
            float f1 = -((float) (blockstate.get(StandingSignBlock.ROTATION) * 360) / 16.0F);
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f1));
            this.model.signStick.showModel = true;
        } else {
            matrixStackIn.translate(0.5D, 0.5D, 0.5D);
            float f4 = -blockstate.get(WallSignBlock.FACING).getHorizontalAngle();
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f4));
            matrixStackIn.translate(0.0D, -0.3125D, -0.4375D);
            this.model.signStick.showModel = false;
        }

        matrixStackIn.push();
        matrixStackIn.scale(modelMatrixMultiplier, -modelMatrixMultiplier, -modelMatrixMultiplier);
        RenderMaterial rendermaterial = getMaterial(blockstate.getBlock());
        IVertexBuilder ivertexbuilder = rendermaterial.getBuffer(bufferIn, this.model::getRenderType);
        this.model.signBoard.render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
        this.model.signStick.render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
        matrixStackIn.pop();
    }

    public void renderText(InuSignTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn, RepositionModel repositionModel) {
        FontRenderer fontrenderer = this.renderDispatcher.getFontRenderer();

        String signType = tileEntityIn.getSignType();
        SignInfo info = SignSet.getSignInfo(signType);

        if (info != null) {
            List<SignText> texts = info.getTexts();

            for (int i = 0; i < texts.size(); i++) {

                SignText signText = texts.get(i);

                IReorderingProcessor processor = fontrenderer
                        .trimStringToWidth(new StringTextComponent(signText.getText()), 1000)
                        .stream()
                        .findFirst()
                        .orElse(IReorderingProcessor.field_242232_a);

                double x = signText.getPoint().getX() - 48; // (0, 0)을 왼쪽 위로 하기 위해 -48과 -24
                double y = signText.getPoint().getY() - 24;
                int color = signText.getColor();

                // (거의) 뒤집기를 위한 좌표 adapt
                x = repositionModel.adaptX(x);
                y = repositionModel.adaptY(y);
                Align align = repositionModel.adaptAlign(signText.getAlign());

                // 중간 기준에서 배율이 바뀌기 때문에 그만큼 x,y좌표를 조정해준다.
                x /= signText.getMultiplier();
                y /= signText.getMultiplier();

                float textWidth = (float) fontrenderer.func_243245_a(processor);

                switch (align) {
                    case LEFT:
                        // continue
                        break;
                    case CENTER:
                        x -= textWidth / 2;
                        break;
                    case RIGHT:
                        x -= textWidth;
                        break;
                }

                matrixStackIn.push();
                float textMatrixInnerMultiplier = 0.010416667F * signText.getMultiplier();
                matrixStackIn.scale(textMatrixInnerMultiplier, -textMatrixInnerMultiplier, textMatrixInnerMultiplier);

                fontrenderer.drawEntityText(processor, (float) x, (float) y, color, false, matrixStackIn.getLast().getMatrix(), bufferIn, false, 0, combinedLightIn);
                matrixStackIn.pop();
            }
        }
    }

    public void renderDebugText(InuSignTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        FontRenderer fontrenderer = this.renderDispatcher.getFontRenderer();
        float textMatrixMultiplier = 0.010416667F;
        matrixStackIn.translate(0.0D, 0.33333334D, 0.046666667D);
        matrixStackIn.scale(textMatrixMultiplier, -textMatrixMultiplier, textMatrixMultiplier);

        //0 x: -9.0, y: -20.0
        //1 x: -30.0, y: -10.0
        //2 x: -48.0, y: 0.0 // 이게 거의 끝과 끝 정도
        //3 x: -12.0, y: 10.0
        //왼쪽끝: x=-48 y=-20
        //중간: x=-24, y=-5

        int color = getMultipliedColor(tileEntityIn.getTextColor().getTextColor(), 0.4D);
        int yBias = 20;

        StringTextComponent stringTextComponent1 = new StringTextComponent("100");
        StringTextComponent stringTextComponent2 = new StringTextComponent("1234567890");
        StringTextComponent stringTextComponent3 = new StringTextComponent("1234567890123456");
        StringTextComponent stringTextComponent4 = new StringTextComponent("12345678901234567890");

        List<StringTextComponent> texts = new ArrayList<>(Arrays.asList(stringTextComponent1, stringTextComponent2, stringTextComponent3, stringTextComponent4));
//        StringTextComponent stringTextComponent3 = new StringTextComponent("test3");
//        StringTextComponent stringTextComponent4 = new StringTextComponent("test4");

//        fontrenderer.trimStringToWidth(stringTextComponent1, 1000);

        for (int k1 = 0; k1 < 4; ++k1) {
            IReorderingProcessor ireorderingprocessor = fontrenderer.trimStringToWidth(texts.get(k1), 200).stream().findFirst().orElse(IReorderingProcessor.field_242232_a);
//            IReorderingProcessor ireorderingprocessor = tileEntityIn.reorderText(k1, (textComponent) -> {
//                // 아마 첫번째 파라미터는 그냥 String 텍스트고 두번째 파라미터는 최대길이인듯
//                List<IReorderingProcessor> list = fontrenderer.trimStringToWidth(textComponent, 90);
//                return list.isEmpty() ? IReorderingProcessor.field_242232_a : list.get(0);
//            });
            if (ireorderingprocessor != null) {
                // 아마도 글씨 길이 구하는 함수일듯. 여기에 -하고 /2하면 가운데 정렬시 시작위치 구할 수 있음
                float f3 = (float) (-fontrenderer.func_243245_a(ireorderingprocessor) / 2);

//                System.out.println(k1 + " x: " + f3 + ", y: " + (float)(k1 * 10 - yBias));
                //public int drawEntityText(IReorderingProcessor processor,
                // float x,
                // float y,
                // int color,
                // boolean dropShadow,
                // Matrix4f matrix,
                // IRenderTypeBuffer buffer,
                // boolean transparent,
                // int colorBackground,
                // int packedLight
                // ) {


                fontrenderer.drawEntityText(ireorderingprocessor, // processor
                        f3, // x
                        (float) (k1 * 10 - yBias), // y
                        color, // color
                        false,
                        matrixStackIn.getLast().getMatrix(),
                        bufferIn,
                        false,
                        0,
                        combinedLightIn
                );
            }
        }
    }

    public static int getMultipliedColor(int color, double colorMultiplier) {
//        int originalColor = tileEntityIn.getTextColor().getTextColor();
//        double colorMultiplier = 0.4D;
        int colorRed = (int) ((double) NativeImage.getRed(color) * colorMultiplier);
        int colorGreen = (int) ((double) NativeImage.getGreen(color) * colorMultiplier);
        int colorBlue = (int) ((double) NativeImage.getBlue(color) * colorMultiplier);

        return NativeImage.getCombined(0, colorBlue, colorGreen, colorRed);
    }

    public static RenderMaterial getMaterial(Block blockIn) {
        WoodType woodtype;
        if (blockIn instanceof AbstractSignBlock) {
            woodtype = ((AbstractSignBlock) blockIn).getWoodType();
        } else {
            woodtype = WoodType.OAK;
        }

        return Atlases.SIGN_MATERIALS.get(woodtype);
    }

    public interface RepositionModel {
        double adaptX(double x);
        double adaptY(double y);

        Align adaptAlign(Align align);
    }

    public static class RepositionModelDefault implements RepositionModel {
        @Override
        public double adaptX(double x) {
            return x;
        }

        @Override
        public double adaptY(double y) {
            return y;
        }

        @Override
        public Align adaptAlign(Align align) {
            return align;
        }
    }

    public static class RepositionModelFliped implements RepositionModel {
        @Override
        public double adaptX(double x) {
            return -x;
        }

        @Override
        public double adaptY(double y) {
            return y;
        }

        @Override
        public Align adaptAlign(Align align) {
            return align.getFliped();
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static final class SignModel extends Model {
        public final ModelRenderer signBoard = new ModelRenderer(64, 32, 0, 0);
        public final ModelRenderer signStick;

        public SignModel() {
            super(RenderType::getEntityCutoutNoCull);
            this.signBoard.addBox(-12.0F, -14.0F, -1.0F, 24.0F, 12.0F, 2.0F, 0.0F);
            this.signStick = new ModelRenderer(64, 32, 0, 14);
            this.signStick.addBox(-1.0F, -2.0F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F);
        }

        public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
            this.signBoard.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            this.signStick.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        }
    }
}
