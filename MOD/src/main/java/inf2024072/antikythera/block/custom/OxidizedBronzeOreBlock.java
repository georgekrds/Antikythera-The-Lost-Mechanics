package inf2024072.antikythera.block.custom;

// 1. ΕΙΣΑΓΩΓΕΣ
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

// 2. ΚΛΑΣΗ
public class OxidizedBronzeOreBlock extends Block {

    // 2.1. ΚΑΤΑΣΚΕΥΑΣΤΗΣ
    public OxidizedBronzeOreBlock(Properties properties) {
        super(properties);
    }

    // 2.2. ΓΡΑΦΙΚΑ
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        // 2.3. ΕΛΕΓΧΟΣ
        if (random.nextInt(2) == 0) {
            // 2.4. ΘΕΣΗ
            double x = (double)pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 1.2;
            double y = (double)pos.getY() + 0.5 + (random.nextDouble() - 0.5) * 1.2;
            double z = (double)pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 1.2;
            // 2.5. ΣΩΜΑΤΙΔΙΑ
            if (random.nextBoolean()) {
                level.addParticle(ParticleTypes.PORTAL, x, y, z, 0.0, 0.0, 0.0);
            } else {
                level.addParticle(ParticleTypes.SCRAPE, x, y, z, 0.0, 0.0, 0.0);
            }
        }
    }
}