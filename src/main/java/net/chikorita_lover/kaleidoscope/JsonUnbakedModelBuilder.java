package net.chikorita_lover.kaleidoscope;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Either;
import net.chikorita_lover.kaleidoscope.mixin.client.JsonUnbakedModelAccessor;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.render.model.json.ModelElement;
import net.minecraft.client.render.model.json.ModelOverride;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public final class JsonUnbakedModelBuilder {
    private final ImmutableList.Builder<ModelElement> elements = ImmutableList.builder();
    private final Map<String, Either<SpriteIdentifier, String>> textureMap = new HashMap<>();
    private final ImmutableList.Builder<ModelOverride> overrides = ImmutableList.builder();
    @Nullable
    private Identifier parent;
    @Nullable
    private Boolean ambientOcclusion = null;
    @Nullable
    private JsonUnbakedModel.GuiLight guiLight = null;
    private ModelTransformation transformations = ModelTransformation.NONE;

    public static JsonUnbakedModelBuilder create() {
        return new JsonUnbakedModelBuilder();
    }

    public static JsonUnbakedModelBuilder copy(JsonUnbakedModel model) {
        JsonUnbakedModelBuilder builder = create();
        JsonUnbakedModelAccessor accessor = (JsonUnbakedModelAccessor) model;
        builder.parent(accessor.getParentId());
        accessor.chicory$elements().forEach(builder::element);
        accessor.getTextureMap().forEach(builder::texture);
        builder.ambientOcclusion(accessor.chicory$ambientOcclusion());
        builder.guiLight(accessor.chicory$guiLight());
        builder.transformations(accessor.chicory$transformations());
        model.getOverrides().forEach(builder::override); // overrides are the only RAW value publicly accessible
        return builder;
    }

    public JsonUnbakedModelBuilder parent(@Nullable Identifier parent) {
        this.parent = parent;
        return this;
    }

    public JsonUnbakedModelBuilder element(ModelElement element) {
        this.elements.add(element);
        return this;
    }

    public JsonUnbakedModelBuilder texture(String key, SpriteIdentifier sprite) {
        return this.texture(key, Either.left(sprite));
    }

    public JsonUnbakedModelBuilder texture(String key, String sprite) {
        return this.texture(key, Either.right(sprite));
    }

    private JsonUnbakedModelBuilder texture(String key, Either<SpriteIdentifier, String> sprite) {
        this.textureMap.put(key, sprite);
        return this;
    }

    public JsonUnbakedModelBuilder texture(SpriteIdentifier sprite) {
        return this.texture(Either.left(sprite));
    }

    public JsonUnbakedModelBuilder texture(String sprite) {
        return this.texture(Either.right(sprite));
    }

    private JsonUnbakedModelBuilder texture(Either<SpriteIdentifier, String> sprite) {
        int i = -1;
        while (this.textureMap.containsKey("layer" + ++i)) {
        }
        this.textureMap.put("layer" + i, sprite);
        return this;
    }

    public JsonUnbakedModelBuilder ambientOcclusion(@Nullable Boolean ambientOcclusion) {
        this.ambientOcclusion = ambientOcclusion;
        return this;
    }

    public JsonUnbakedModelBuilder guiLight(@Nullable JsonUnbakedModel.GuiLight guiLight) {
        this.guiLight = guiLight;
        return this;
    }

    public JsonUnbakedModelBuilder transformations(ModelTransformation transformations) {
        this.transformations = transformations;
        return this;
    }

    public JsonUnbakedModelBuilder override(ModelOverride override) {
        this.overrides.add(override);
        return this;
    }

    public JsonUnbakedModel build() {
        return new JsonUnbakedModel(this.parent, this.elements.build(), this.textureMap, this.ambientOcclusion, this.guiLight, this.transformations, this.overrides.build());
    }
}
