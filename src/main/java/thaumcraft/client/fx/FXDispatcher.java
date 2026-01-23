package thaumcraft.client.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import thaumcraft.client.fx.beams.FXArc;
import thaumcraft.client.fx.beams.FXBolt;
import thaumcraft.client.fx.particles.FXBlockRunes;
import thaumcraft.client.fx.particles.FXFireMote;
import thaumcraft.client.fx.particles.FXGeneric;
import thaumcraft.client.fx.particles.FXGenericP2P;
import thaumcraft.client.fx.particles.FXSmokeSpiral;
import thaumcraft.client.fx.particles.FXVent;
import thaumcraft.client.fx.particles.FXVisSparkle;
import thaumcraft.client.fx.particles.FXWisp;
import thaumcraft.init.ModSounds;

import java.awt.Color;
import java.util.Random;

/**
 * FXDispatcher - Central particle effect dispatcher for Thaumcraft.
 * 
 * This is a stub implementation that provides the interface used by
 * the rest of the mod without crashing. Real particle effects will
 * be incrementally added.
 * 
 * For now, most methods use vanilla particles as placeholders.
 */
@OnlyIn(Dist.CLIENT)
public class FXDispatcher {
    
    public static FXDispatcher INSTANCE = new FXDispatcher();
    
    private final Random rand = new Random();
    
    public Level getWorld() {
        return Minecraft.getInstance().level;
    }

    /**
     * Get the client level, required for spawning custom particles
     */
    private ClientLevel getClientLevel() {
        return Minecraft.getInstance().level;
    }

    /**
     * Add a custom particle to the particle engine
     */
    private void addParticle(net.minecraft.client.particle.Particle particle) {
        if (particle != null) {
            Minecraft.getInstance().particleEngine.add(particle);
        }
    }
    
    // ==================== Fire/Alumentum Effects ====================
    
    public void drawFireMote(float x, float y, float z, float vx, float vy, float vz, 
            float r, float g, float b, float alpha, float scale) {
        ClientLevel level = getClientLevel();
        if (level != null) {
            FXFireMote particle = new FXFireMote(level, x, y, z, vx, vy, vz, r, g, b, scale);
            particle.setAlpha(alpha);
            addParticle(particle);
        }
    }
    
    public void drawAlumentum(float x, float y, float z, float vx, float vy, float vz, 
            float r, float g, float b, float alpha, float scale) {
        ClientLevel level = getClientLevel();
        if (level != null) {
            // Alumentum uses fire motes but with layer 1 (additive blending)
            FXFireMote particle = new FXFireMote(level, x, y, z, vx, vy, vz, r, g, b, scale, 1);
            particle.setAlpha(alpha);
            addParticle(particle);
        }
    }
    
    // ==================== Taint Effects ====================
    
    public void drawTaintParticles(float x, float y, float z, float vx, float vy, float vz, float scale) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.WITCH, x, y, z, vx, vy, vz);
        }
    }
    
    // ==================== Lightning/Spark Effects ====================
    
    public void drawLightningFlash(double x, double y, double z, float r, float g, float b, float alpha, float scale) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.FLASH, x, y, z, 0, 0, 0);
        }
    }
    
    public void spark(double x, double y, double z, float size, float r, float g, float b, float a) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.ELECTRIC_SPARK, x, y, z, 0, 0, 0);
        }
    }
    
    public void sparkle(float x, float y, float z, float r, float g, float b) {
        Level level = getWorld();
        if (level != null && rand.nextInt(6) < 4) {
            level.addParticle(ParticleTypes.END_ROD, x, y, z, 0, 0.01, 0);
        }
    }
    
    // ==================== Generic Particle Drawing ====================
    
    public void drawGenericParticles(double x, double y, double z, double mx, double my, double mz, 
            float r, float g, float b, float alpha, boolean loop, int start, int num, int inc, 
            int age, int delay, float scale, float rot, int layer) {
        ClientLevel level = getClientLevel();
        if (level != null) {
            FXGeneric particle = new FXGeneric(level, x, y, z, mx, my, mz);
            particle.setColor(r, g, b);
            particle.setAlphaF(alpha);
            particle.setLoop(loop);
            particle.setParticles(start, num, inc);
            particle.setMaxAge(age);
            particle.setScale(scale);
            particle.setRotationSpeed(rot);
            particle.setLayer(layer);
            addParticle(particle);
        }
    }
    
    public void drawGenericParticles16(double x, double y, double z, double x2, double y2, double z2, 
            float r, float g, float b, float alpha, boolean loop, int start, int num, int inc, 
            int age, int delay, float scale, float rot, int layer) {
        drawGenericParticles(x, y, z, x2, y2, z2, r, g, b, alpha, loop, start, num, inc, age, delay, scale, rot, layer);
    }
    
    public void drawGenericParticles(double x, double y, double z, double mx, double my, double mz, GenPart part) {
        if (part != null) {
            drawGenericParticles(x, y, z, mx, my, mz, part.redStart, part.greenStart, part.blueStart, 
                    part.alpha, part.loop, part.partStart, part.partNum, part.partInc, 
                    part.age, part.delay, part.scale, part.rot, part.layer);
        }
    }
    
    // ==================== Crucible Effects ====================
    
    public void crucibleBubble(float x, float y, float z, float cr, float cg, float cb) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.BUBBLE, x, y, z, 0, 0.02, 0);
        }
    }
    
    public void crucibleBoil(BlockPos pos, Object tile, int j) {
        Level level = getWorld();
        if (level != null) {
            for (int a = 0; a < 2; a++) {
                double x = pos.getX() + 0.2f + rand.nextFloat() * 0.6f;
                double y = pos.getY() + 0.5f;
                double z = pos.getZ() + 0.2f + rand.nextFloat() * 0.6f;
                level.addParticle(ParticleTypes.BUBBLE_POP, x, y, z, 0, 0.02, 0);
            }
        }
    }
    
    public void crucibleFroth(float x, float y, float z) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.SPLASH, x, y, z, 0, 0, 0);
        }
    }
    
    public void crucibleFrothDown(float x, float y, float z) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.DRIPPING_WATER, x, y, z, 0, 0, 0);
        }
    }
    
    // ==================== Bamf/Teleport Effects ====================
    
    public void drawBamf(BlockPos p, boolean sound, boolean flair, Direction side) {
        drawBamf(p.getX() + 0.5, p.getY() + 0.5, p.getZ() + 0.5, sound, flair, side);
    }
    
    public void drawBamf(BlockPos p, float r, float g, float b, boolean sound, boolean flair, Direction side) {
        drawBamf(p.getX() + 0.5, p.getY() + 0.5, p.getZ() + 0.5, r, g, b, sound, flair, side);
    }
    
    public void drawBamf(BlockPos p, int color, boolean sound, boolean flair, Direction side) {
        Color c = new Color(color);
        drawBamf(p.getX() + 0.5, p.getY() + 0.5, p.getZ() + 0.5, 
                c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f, sound, flair, side);
    }
    
    public void drawBamf(double x, double y, double z, int color, boolean sound, boolean flair, Direction side) {
        Color c = new Color(color);
        drawBamf(x, y, z, c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f, sound, flair, side);
    }
    
    public void drawBamf(double x, double y, double z, boolean sound, boolean flair, Direction side) {
        drawBamf(x, y, z, 0.5f, 0.1f, 0.6f, sound, flair, side);
    }
    
    public void drawBamf(double x, double y, double z, float r, float g, float b, boolean sound, boolean flair, Direction side) {
        Level level = getWorld();
        if (level == null) return;
        
        if (sound) {
            level.playLocalSound(x, y, z, ModSounds.POOF.get(), SoundSource.BLOCKS, 
                    0.4f, 1.0f + (float) rand.nextGaussian() * 0.05f, false);
        }
        
        // Spawn purple smoke particles
        for (int a = 0; a < 8 + rand.nextInt(4); a++) {
            double vx = rand.nextGaussian() * 0.1;
            double vy = rand.nextGaussian() * 0.1;
            double vz = rand.nextGaussian() * 0.1;
            if (side != null) {
                vx += side.getStepX() * 0.1;
                vy += side.getStepY() * 0.1;
                vz += side.getStepZ() * 0.1;
            }
            level.addParticle(ParticleTypes.WITCH, x + vx, y + vy, z + vz, vx, vy, vz);
        }
        
        if (flair) {
            for (int a = 0; a < 3; a++) {
                level.addParticle(ParticleTypes.END_ROD, x, y, z, 
                        rand.nextGaussian() * 0.05, rand.nextGaussian() * 0.05, rand.nextGaussian() * 0.05);
            }
        }
    }
    
    // ==================== Wispy Motes ====================
    
    public void drawWispyMotesOnBlock(BlockPos pp, int age, float grav) {
        Level level = getWorld();
        if (level != null) {
            double x = pp.getX() + rand.nextFloat();
            double y = pp.getY();
            double z = pp.getZ() + rand.nextFloat();
            level.addParticle(ParticleTypes.ENCHANT, x, y, z, 0, 0.05, 0);
        }
    }
    
    public void drawWispyMotes(double x, double y, double z, double vx, double vy, double vz, int age, float grav) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.ENCHANT, x, y, z, vx, vy, vz);
        }
    }
    
    public void drawWispyMotes(double x, double y, double z, double vx, double vy, double vz, 
            int age, float r, float g, float b, float grav) {
        drawWispyMotes(x, y, z, vx, vy, vz, age, grav);
    }
    
    // ==================== Scan Effects ====================
    
    public void scanHighlight(BlockPos p) {
        Level level = getWorld();
        if (level == null) return;
        
        AABB bb = level.getBlockState(p).getShape(level, p).bounds().move(p);
        scanHighlight(bb);
    }
    
    public void scanHighlight(Entity e) {
        scanHighlight(e.getBoundingBox());
    }
    
    public void scanHighlight(AABB bb) {
        Level level = getWorld();
        if (level == null) return;
        
        int num = Mth.ceil(bb.getSize() * 2);
        double cx = (bb.minX + bb.maxX) / 2;
        double cy = (bb.minY + bb.maxY) / 2;
        double cz = (bb.minZ + bb.maxZ) / 2;
        
        for (int a = 0; a < num; a++) {
            double x = cx + rand.nextGaussian() * (bb.maxX - bb.minX) * 0.3;
            double y = cy + rand.nextGaussian() * (bb.maxY - bb.minY) * 0.3;
            double z = cz + rand.nextGaussian() * (bb.maxZ - bb.minZ) * 0.3;
            level.addParticle(ParticleTypes.END_ROD, x, y, z, 0, 0.01, 0);
        }
    }
    
    public void drawBlockSparkles(BlockPos p, Vec3 start) {
        scanHighlight(p);
    }
    
    public void drawSimpleSparkle(Random rand, double x, double y, double z, double x2, double y2, double z2, 
            float scale, float r, float g, float b, int delay, float decay, float grav, int baseAge) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.END_ROD, x, y, z, x2, y2, z2);
        }
    }
    
    public void drawLineSparkle(Random rand, double x, double y, double z, double x2, double y2, double z2, 
            float scale, float r, float g, float b, int delay, float decay, float grav, int baseAge) {
        drawSimpleSparkle(rand, x, y, z, x2, y2, z2, scale, r, g, b, delay, decay, grav, baseAge);
    }
    
    // ==================== Block Mist/Fog ====================
    
    public void drawBlockMistParticles(BlockPos p, int c) {
        Level level = getWorld();
        if (level == null) return;
        
        for (int a = 0; a < 4; a++) {
            double x = p.getX() + rand.nextFloat();
            double y = p.getY() + rand.nextFloat();
            double z = p.getZ() + rand.nextFloat();
            level.addParticle(ParticleTypes.CLOUD, x, y, z, 0, 0.02, 0);
        }
    }
    
    public void drawBlockMistParticlesFlat(BlockPos p, int c) {
        Level level = getWorld();
        if (level == null) return;
        
        for (int a = 0; a < 3; a++) {
            double x = p.getX() + rand.nextFloat();
            double y = p.getY() + 0.1;
            double z = p.getZ() + rand.nextFloat();
            level.addParticle(ParticleTypes.CLOUD, x, y, z, 0, 0.01, 0);
        }
    }
    
    public void drawFocusCloudParticle(double x, double y, double z, double mx, double my, double mz, int c) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.CLOUD, x, y, z, mx, my, mz);
        }
    }
    
    // ==================== Vis/Aura Effects ====================
    
    public void visSparkle(int x, int y, int z, int x2, int y2, int z2, int color) {
        ClientLevel level = getClientLevel();
        if (level != null) {
            Color c = new Color(color);
            FXVisSparkle particle = new FXVisSparkle(level, 
                    x + rand.nextFloat(), y + rand.nextFloat(), z + rand.nextFloat(),
                    x2 + 0.5, y2 + 0.5, z2 + 0.5);
            particle.setColor(c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f);
            addParticle(particle);
        }
    }
    
    public void drawLevitatorParticles(double x, double y, double z, double x2, double y2, double z2) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.ENCHANT, x, y, z, x2, y2, z2);
        }
    }
    
    public void drawStabilizerParticles(double x, double y, double z, double x2, double y2, double z2, int life) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.PORTAL, x, y, z, x2, y2, z2);
        }
    }
    
    public void drawGolemFlyParticles(double x, double y, double z, double x2, double y2, double z2) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.ENCHANT, x, y, z, x2, y2, z2);
        }
    }
    
    public void drawPollutionParticles(BlockPos p) {
        Level level = getWorld();
        if (level == null) return;
        
        double x = p.getX() + 0.2 + rand.nextFloat() * 0.6;
        double y = p.getY() + 0.2 + rand.nextFloat() * 0.6;
        double z = p.getZ() + 0.2 + rand.nextFloat() * 0.6;
        level.addParticle(ParticleTypes.WITCH, x, y, z, 0, 0.02, 0);
    }
    
    // ==================== Essentia Effects ====================
    
    public void essentiaTrailFx(BlockPos p1, BlockPos p2, int count, int color, float scale, int ext) {
        ClientLevel level = getClientLevel();
        if (level == null) return;
        
        Color c = new Color(color);
        // Spawn point-to-point particles
        for (int i = 0; i < count; i++) {
            double startX = p1.getX() + 0.5 + rand.nextGaussian() * 0.1;
            double startY = p1.getY() + 0.5 + rand.nextGaussian() * 0.1;
            double startZ = p1.getZ() + 0.5 + rand.nextGaussian() * 0.1;
            
            FXGenericP2P particle = new FXGenericP2P(level, startX, startY, startZ, 
                    p2.getX() + 0.5, p2.getY() + 0.5, p2.getZ() + 0.5);
            particle.setColor(c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f);
            particle.setScale(scale);
            addParticle(particle);
        }
    }
    
    public void essentiaDropFx(double x, double y, double z, float r, float g, float b, float alpha) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.DRIPPING_OBSIDIAN_TEAR, x, y, z, 0, 0, 0);
        }
    }
    
    public void drawVentParticles(double x, double y, double z, double x2, double y2, double z2, int color) {
        ClientLevel level = getClientLevel();
        if (level != null) {
            FXVent vent = new FXVent(level, x, y, z, x2, y2, z2, color);
            addParticle(vent);
        }
    }
    
    public void drawVentParticles(double x, double y, double z, double x2, double y2, double z2, int color, float scale) {
        ClientLevel level = getClientLevel();
        if (level != null) {
            FXVent vent = new FXVent(level, x, y, z, x2, y2, z2, color);
            vent.setScale(scale);
            addParticle(vent);
        }
    }
    
    public void drawVentParticles2(double x, double y, double z, double x2, double y2, double z2, int color, float scale) {
        drawVentParticles(x, y, z, x2, y2, z2, color, scale);
    }
    
    public void jarSplashFx(double x, double y, double z) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.SPLASH, x, y, z, 0, 0.1, 0);
        }
    }
    
    public void waterTrailFx(BlockPos p1, BlockPos p2, int count, int color, float scale) {
        essentiaTrailFx(p1, p2, count, color, scale, 0);
    }
    
    // ==================== Infusion Effects ====================
    
    public void drawInfusionParticles1(double x, double y, double z, BlockPos pos, ItemStack stack) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.ENCHANT, x, y, z, 
                    (pos.getX() + 0.5 - x) * 0.1, (pos.getY() - 0.5 - y) * 0.1, (pos.getZ() + 0.5 - z) * 0.1);
        }
    }
    
    public void drawInfusionParticles2(double x, double y, double z, BlockPos pos, BlockState state, int md) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.ENCHANT, x, y, z, 
                    (pos.getX() + 0.5 - x) * 0.1, (pos.getY() - 0.5 - y) * 0.1, (pos.getZ() + 0.5 - z) * 0.1);
        }
    }
    
    public void drawInfusionParticles3(double x, double y, double z, int x2, int y2, int z2) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.ENCHANTED_HIT, x, y, z, 0, 0, 0);
        }
    }
    
    public void drawInfusionParticles4(double x, double y, double z, int x2, int y2, int z2) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.HAPPY_VILLAGER, x, y, z, 0, 0, 0);
        }
    }
    
    // ==================== Arc/Lightning ====================
    
    public void arcLightning(double x, double y, double z, double tx, double ty, double tz, float r, float g, float b, float h) {
        ClientLevel level = getClientLevel();
        if (level != null) {
            FXArc arc = new FXArc(level, x, y, z, tx, ty, tz, r, g, b, h);
            addParticle(arc);
        }
    }
    
    public void arcBolt(double x, double y, double z, double tx, double ty, double tz, float r, float g, float b, float width) {
        ClientLevel level = getClientLevel();
        if (level != null) {
            FXBolt bolt = new FXBolt(level, x, y, z, tx, ty, tz, r, g, b, width);
            addParticle(bolt);
        }
    }
    
    // ==================== Beam Effects (stubs) ====================
    
    public Object beamCont(LivingEntity p, double tx, double ty, double tz, int type, int color, boolean reverse, float endmod, Object input, int impact) {
        // Beam effects need custom rendering - return null for now
        return null;
    }
    
    public Object beamBore(double px, double py, double pz, double tx, double ty, double tz, int type, int color, boolean reverse, float endmod, Object input, int impact) {
        // Beam effects need custom rendering - return null for now
        return null;
    }
    
    // ==================== Misc Effects ====================
    
    public void burst(double sx, double sy, double sz, float size) {
        Level level = getWorld();
        if (level == null) return;
        
        for (int i = 0; i < 10; i++) {
            level.addParticle(ParticleTypes.POOF, sx, sy, sz, 
                    rand.nextGaussian() * 0.1, rand.nextGaussian() * 0.1, rand.nextGaussian() * 0.1);
        }
    }
    
    public void excavateFX(BlockPos pos, LivingEntity p, int progress) {
        // Block breaking animation is handled by vanilla
    }
    
    public void blockRunes(double x, double y, double z, float r, float g, float b, int dur, float grav) {
        ClientLevel level = getClientLevel();
        if (level != null) {
            FXBlockRunes runes = new FXBlockRunes(level, x, y, z, r, g, b, dur);
            runes.setGravity(grav);
            addParticle(runes);
        }
    }
    
    public void blockRunes2(double x, double y, double z, float r, float g, float b, int dur, float grav) {
        blockRunes(x, y, z, r, g, b, dur, grav);
    }
    
    public void drawPedestalShield(BlockPos pos) {
        Level level = getWorld();
        if (level != null) {
            for (int i = 0; i < 8; i++) {
                double angle = i * Math.PI / 4;
                double x = pos.getX() + 0.5 + Math.cos(angle) * 0.5;
                double z = pos.getZ() + 0.5 + Math.sin(angle) * 0.5;
                level.addParticle(ParticleTypes.ENCHANT, x, pos.getY() + 1, z, 0, 0.1, 0);
            }
        }
    }
    
    public void blockWard(double x, double y, double z, Direction side, float r, float g, float b) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.ENCHANT, x + 0.5, y + 0.5, z + 0.5, 0, 0, 0);
        }
    }
    
    public void smokeSpiral(double x, double y, double z, float rad, int start, int miny, int color) {
        ClientLevel level = getClientLevel();
        if (level != null) {
            Color c = new Color(color);
            FXSmokeSpiral spiral = new FXSmokeSpiral(level, x, y, z, rad, start, miny);
            spiral.setColor(c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f);
            addParticle(spiral);
        }
    }
    
    public void drawCurlyWisp(double x, double y, double z, double vx, double vy, double vz, 
            float scale, float r, float g, float b, float a, Direction side, int seed, int layer, int delay) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.WITCH, x, y, z, vx, vy, vz);
        }
    }
    
    public void voidStreak(double x, double y, double z, double x2, double y2, double z2, int seed, float scale) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.PORTAL, x, y, z, x2 - x, y2 - y, z2 - z);
        }
    }
    
    public void furnaceLavaFx(int x, int y, int z, int facingX, int facingZ) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.LAVA, 
                    x + 0.5 + facingX * 0.6, y + 0.3, z + 0.5 + facingZ * 0.6, 0, 0, 0);
        }
    }
    
    public void bottleTaintBreak(double x, double y, double z) {
        Level level = getWorld();
        if (level == null) return;
        
        for (int i = 0; i < 8; i++) {
            level.addParticle(ParticleTypes.WITCH, x, y, z, 
                    rand.nextGaussian() * 0.15, rand.nextDouble() * 0.2, rand.nextGaussian() * 0.15);
        }
        level.playLocalSound(x, y, z, SoundEvents.SPLASH_POTION_BREAK, SoundSource.NEUTRAL, 
                1.0f, rand.nextFloat() * 0.1f + 0.9f, false);
    }
    
    public void cultistSpawn(double x, double y, double z, double a, double b, double c) {
        Level level = getWorld();
        if (level != null) {
            for (int i = 0; i < 5; i++) {
                level.addParticle(ParticleTypes.FLAME, x, y, z, a, b, c);
            }
        }
    }
    
    public void pechsCurseTick(double posX, double posY, double posZ) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.WITCH, posX, posY, posZ, 0, 0, 0);
        }
    }
    
    public void wispFXEG(double posX, double posY, double posZ, Entity target) {
        ClientLevel level = getClientLevel();
        if (level != null) {
            FXWisp particle = new FXWisp(level, posX, posY, posZ, target);
            addParticle(particle);
        }
    }
    
    public void drawSlash(double x, double y, double z, double x2, double y2, double z2, int dur) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.SWEEP_ATTACK, (x + x2) / 2, (y + y2) / 2, (z + z2) / 2, 0, 0, 0);
        }
    }
    
    public void boreDigFx(int x, int y, int z, Entity e, BlockState bi, int md, int delay) {
        Level level = getWorld();
        if (level == null) return;
        
        for (int i = 0; i < 3; i++) {
            level.addParticle(ParticleTypes.ENCHANT, 
                    x + rand.nextFloat(), y + rand.nextFloat(), z + rand.nextFloat(),
                    (e.getX() - x) * 0.1, (e.getY() - y) * 0.1, (e.getZ() - z) * 0.1);
        }
    }
    
    public void sonicBoom(double x, double y, double z, Entity source, int duration) {
        Level level = getWorld();
        if (level == null) return;
        
        // Create expanding ring of particles
        for (int i = 0; i < 16; i++) {
            double angle = i * Math.PI * 2 / 16;
            double px = x + Math.cos(angle) * 0.5;
            double pz = z + Math.sin(angle) * 0.5;
            double vx = Math.cos(angle) * 0.3;
            double vz = Math.sin(angle) * 0.3;
            level.addParticle(ParticleTypes.SONIC_BOOM, px, y + 1, pz, vx, 0, vz);
        }
    }
    
    public void boreTrailFx(BlockPos p1, Entity e, int count, int color, float scale, int ext) {
        Level level = getWorld();
        if (level == null) return;
        
        level.addParticle(ParticleTypes.ENCHANT, 
                p1.getX() + 0.5, p1.getY() + 0.5, p1.getZ() + 0.5,
                (e.getX() - p1.getX()) * 0.1, (e.getY() - p1.getY()) * 0.1, (e.getZ() - p1.getZ()) * 0.1);
    }
    
    // ==================== Entity Effects ====================
    
    public void splooshFX(Entity e) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.WITCH, e.getX(), e.getY() + e.getBbHeight() / 2, e.getZ(), 
                    rand.nextGaussian() * 0.1, rand.nextGaussian() * 0.1, rand.nextGaussian() * 0.1);
        }
    }
    
    public void taintsplosionFX(Entity e) {
        Level level = getWorld();
        if (level == null) return;
        
        for (int i = 0; i < 5; i++) {
            level.addParticle(ParticleTypes.WITCH, e.getX(), e.getY() + rand.nextFloat() * e.getBbHeight(), e.getZ(),
                    rand.nextGaussian() * 0.2, rand.nextGaussian() * 0.2, rand.nextGaussian() * 0.2);
        }
    }
    
    public void tentacleAriseFX(Entity e) {
        Level level = getWorld();
        if (level == null) return;
        
        for (int i = 0; i < (int)(2 * e.getBbHeight()); i++) {
            level.addParticle(ParticleTypes.WITCH, 
                    e.getX() + rand.nextGaussian() * 0.3, e.getY(), e.getZ() + rand.nextGaussian() * 0.3,
                    0, 0.1, 0);
        }
    }
    
    public void slimeJumpFX(Entity e, int size) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.WITCH, e.getX(), e.getY() + e.getBbHeight() / 2, e.getZ(), 0, 0.1, 0);
        }
    }
    
    public void taintLandFX(Entity e) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.WITCH, e.getX(), e.getY(), e.getZ(), 0, 0, 0);
        }
    }
    
    public Object swarmParticleFX(Entity targetedEntity, float f1, float f2, float pg) {
        Level level = getWorld();
        if (level != null) {
            level.addParticle(ParticleTypes.WITCH, 
                    targetedEntity.getX() + rand.nextGaussian(), 
                    targetedEntity.getY() + rand.nextGaussian(), 
                    targetedEntity.getZ() + rand.nextGaussian(), 0, 0, 0);
        }
        return null;
    }
    
    // ==================== GUI Effects ====================
    
    public void drawSimpleSparkleGui(Random rand, double x, double y, double x2, double y2, 
            float scale, float r, float g, float b, int delay, float decay, float grav) {
        // GUI particles need special handling - stub for now
    }
    
    /**
     * GenPart - Generic particle configuration class.
     */
    public static class GenPart {
        public int age = 20;
        public float redStart = 1f, greenStart = 1f, blueStart = 1f;
        public float redEnd = 1f, greenEnd = 1f, blueEnd = 1f;
        public float alpha = 1f;
        public boolean loop = false;
        public int partStart = 0, partNum = 1, partInc = 1;
        public float scale = 1f;
        public int layer = 0;
        public float rotstart = 0f, rot = 0f;
        public double slowDown = 1.0;
        public float grav = 0f;
        public int grid = 8;
        public int delay = 0;
    }
}
