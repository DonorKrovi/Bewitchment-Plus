package dev.mrsterner.bewitchmentplus.client.renderlayer;

import dev.mrsterner.bewitchmentplus.BewitchmentPlus;
import dev.mrsterner.bewitchmentplus.client.shader.BWPShader;
import dev.mrsterner.bewitchmentplus.mixin.RenderLayerAccessor;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.block.entity.EndPortalBlockEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.function.Function;

public class BWPRenderLayers extends RenderLayer {
    public BWPRenderLayers(String name, VertexFormat vertexFormat, VertexFormat.DrawMode drawMode, int expectedBufferSize, boolean hasCrumbling, boolean translucent, Runnable startAction, Runnable endAction) {
        super(name, vertexFormat, drawMode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
    }

    private static RenderLayer makeLayer(String name, VertexFormat format, VertexFormat.DrawMode mode, int bufSize, boolean hasCrumbling, boolean sortOnUpload, RenderLayer.MultiPhaseParameters glState) {
        return RenderLayerAccessor.of(name, format, mode, bufSize, hasCrumbling, sortOnUpload, glState);
    }

    public static final Function<Identifier, RenderLayer> RUNE_LAYER = Util.memoize(texture -> {
        RenderLayer.MultiPhaseParameters glState = RenderLayer.MultiPhaseParameters.builder()
        .shader(new RenderPhase.Shader(BWPShader::rune))
        .texture(Textures.create().add(texture, false, false).add(EndPortalBlockEntityRenderer.PORTAL_TEXTURE, false, false).build())
        .transparency(TRANSLUCENT_TRANSPARENCY)
        .cull(DISABLE_CULLING)
        .lightmap(ENABLE_LIGHTMAP)
        .build(true);
        return makeLayer(BewitchmentPlus.MODID + "bw_rune", VertexFormats.POSITION_COLOR_TEXTURE_LIGHT_NORMAL, VertexFormat.DrawMode.QUADS, 256, true, true, glState);
    });
}
