package net.chikorita_lover.kaleidoscope.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.MathHelper;
import org.joml.Vector3f;

public class FireflyParticle extends SpriteBillboardParticle {
    private static final Vector3f GLOW_COLOR_VECTOR = new Vector3f(0.62F, 0.89F, 0.26F);
    private static final Vector3f DIM_COLOR_VECTOR = new Vector3f(0.16F, 0.18F, 0.16F);

    FireflyParticle(ClientWorld world, SpriteProvider spriteProvider, double x, double y, double z) {
        super(world, x, y - 0.125, z);
        this.setBoundingBoxSpacing(0.01F, 0.01F);
        this.setSprite(spriteProvider);
        this.maxAge = 100;
        this.collidesWithWorld = true;
        this.velocityMultiplier = 1.0F;
        this.gravityStrength = 0.01F;
        this.velocityX = MathHelper.lerp(random.nextFloat(), -0.05, 0.05) * random.nextFloat();
        this.velocityY = MathHelper.lerp(random.nextFloat(), -0.04, 0.04) * random.nextFloat();
        this.velocityZ = MathHelper.lerp(random.nextFloat(), -0.05, 0.05) * random.nextFloat();
        this.scale *= 1.5F;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public int getBrightness(float tint) {
        return 240;
    }

    @Override
    public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickDelta) {
        float f = (float) this.age + tickDelta;
        Vector3f vec3f = new Vector3f(GLOW_COLOR_VECTOR);
        vec3f.lerp(DIM_COLOR_VECTOR, (float) (Math.cos(MathHelper.PI * f / 10.0F) + 1.0F) / 2.0F);
        this.red = vec3f.x();
        this.green = vec3f.y();
        this.blue = vec3f.z();
        super.buildGeometry(vertexConsumer, camera, tickDelta);
    }

    @Environment(value = EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(SimpleParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new FireflyParticle(clientWorld, this.spriteProvider, d, e, f);
        }
    }
}
