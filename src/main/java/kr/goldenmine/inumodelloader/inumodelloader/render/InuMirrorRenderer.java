package kr.goldenmine.inumodelloader.inumodelloader.render;

//public class InuMirrorRenderer extends InuSignTileEntityRenderer{
//    @Override
//    public void render(InuSignTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
//        String signType = tileEntityIn.getSignType();
//        SignInfo info = SignSet.getSignInfo(signType);
//
//        if(info != null) {
//            matrixStackIn.push();
//
//            renderModel(tileEntityIn, partialTicks, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
//
//            matrixStackIn.push();
//            matrixStackIn.translate(0.0D, 0.33333334D, 0.046666667D); // 그냥 표지판 앞면에 쓰기
//            renderText(tileEntityIn, partialTicks, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, new RepositionModelDefaultHalf());
//            matrixStackIn.pop();
//
//            matrixStackIn.push();
//            matrixStackIn.translate(0.0D, 0.33333334D, -0.046666667D); // 뒷면에 쓰기 (z가 마이너스)
//            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180)); // 180도 회전하기
//            renderText(tileEntityIn, partialTicks, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, new RepositionModelFlipedHalf());
//            matrixStackIn.pop();
//
//            matrixStackIn.pop();
//        }
//    }
//}
