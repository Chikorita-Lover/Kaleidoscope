package net.chikorita_lover.kaleidoscope.client.render;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class KaleidoscopeEntityModelLayers {
    public static final EntityModelLayer CRIMSON_BOAT = registerMain("boat/crimson");
    public static final EntityModelLayer CRIMSON_CHEST_BOAT = registerMain("chest_boat/crimson");
    public static final EntityModelLayer JUKEBOX_MINECART = registerMain("jukebox_minecart");
    public static final EntityModelLayer STRIDER_CHEST = new EntityModelLayer(Identifier.of("strider"), "chest");
    public static final EntityModelLayer WARPED_BOAT = registerMain("boat/warped");
    public static final EntityModelLayer WARPED_CHEST_BOAT = registerMain("chest_boat/warped");

    private static EntityModelLayer registerMain(String path) {
        return register(path, "main");
    }

    private static EntityModelLayer register(String path, String name) {
        return new EntityModelLayer(Kaleidoscope.of(path), name);
    }
}
