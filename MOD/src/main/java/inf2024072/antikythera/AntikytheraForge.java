package inf2024072.antikythera;

// 1. ΕΙΣΑΓΩΓΕΣ
import inf2024072.antikythera.block.ModBlocks;
import inf2024072.antikythera.item.ModItems;
import inf2024072.antikythera.entity.ModEntities;
import inf2024072.antikythera.entity.AncientAutomatonEntity;
import inf2024072.antikythera.entity.client.AncientAutomatonModel;
import inf2024072.antikythera.entity.client.AncientAutomatonRenderer;
import inf2024072.antikythera.sound.ModSounds;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// 2. ΚΛΑΣΗ
@Mod("antikythera")
public class AntikytheraForge {

    // 2.1. ΕΠΙΠΕΔΟ
    public static final ModelLayerLocation AUTOMATON_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("antikythera", "ancient_automaton"), "main");

    // 2.2. ΚΑΤΑΣΚΕΥΑΣΤΗΣ
    public AntikytheraForge() {
        // 2.3. ΔΙΑΥΛΟΣ
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // 2.4. ΕΓΓΡΑΦΗ
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntities.register(modEventBus);
        ModSounds.register(modEventBus);
        modEventBus.addListener(ModItems::addCreative);
    }

    // 3. ΓΕΓΟΝΟΤΑ
    @Mod.EventBusSubscriber(modid = "antikythera", bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEvents {

        // 3.1. ΙΔΙΟΤΗΤΕΣ
        @SubscribeEvent
        public static void entityAttributes(EntityAttributeCreationEvent event) {
            event.put(ModEntities.ANCIENT_AUTOMATON.get(), AncientAutomatonEntity.createAttributes().build());
        }
    }

    // 4. ΓΡΑΦΙΚΑ
    @Mod.EventBusSubscriber(modid = "antikythera", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientEvents {

        // 4.1. ΜΟΝΤΕΛΟ
        @SubscribeEvent
        public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(AUTOMATON_LAYER, AncientAutomatonModel::createBodyLayer);
        }

        // 4.2. ΑΠΕΙΚΟΝΙΣΗ
        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(ModEntities.ANCIENT_AUTOMATON.get(), AncientAutomatonRenderer::new);
        }
    }
    // 5. ΚΟΣΜΟΣ
    @Mod.EventBusSubscriber(modid = "antikythera", bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeEvents {

        // 5.1. ΤΟΠΟΘΕΤΗΣΗ
        @SubscribeEvent
        public static void onBlockPlace(net.minecraftforge.event.level.BlockEvent.EntityPlaceEvent event) {
            // 5.2. ΕΠΙΠΕΔΟ
            net.minecraft.world.level.LevelAccessor level = event.getLevel();

            // 5.3. ΔΙΑΚΟΜΙΣΤΗΣ
            if (!level.isClientSide() && level instanceof net.minecraft.server.level.ServerLevel serverLevel) {
                // 5.4. ΚΑΤΑΣΤΑΣΗ
                net.minecraft.world.level.block.state.BlockState state = event.getPlacedBlock();

                // 5.5. ΕΛΕΓΧΟΣ
                if (state.is(net.minecraft.world.level.block.Blocks.REDSTONE_BLOCK)) {
                    // 5.6. ΘΕΣΗ
                    net.minecraft.core.BlockPos pos = event.getPos();
                    // 5.7. ΜΠΛΟΚ
                    net.minecraft.world.level.block.Block copper = net.minecraft.world.level.block.Blocks.COPPER_BLOCK;

                    // 5.8. ΑΞΟΝΑΣ_Χ
                    boolean isX = level.getBlockState(pos.below()).is(copper) &&
                            level.getBlockState(pos.below(2)).is(copper) &&
                            level.getBlockState(pos.below().east()).is(copper) &&
                            level.getBlockState(pos.below().west()).is(copper);

                    // 5.9. ΑΞΟΝΑΣ_Ζ
                    boolean isZ = level.getBlockState(pos.below()).is(copper) &&
                            level.getBlockState(pos.below(2)).is(copper) &&
                            level.getBlockState(pos.below().north()).is(copper) &&
                            level.getBlockState(pos.below().south()).is(copper);

                    // 5.10. ΕΠΙΒΕΒΑΙΩΣΗ
                    if (isX || isZ) {
                        // 5.11. ΔΙΑΓΡΑΦΗ
                        level.setBlock(pos, net.minecraft.world.level.block.Blocks.AIR.defaultBlockState(), 3);
                        level.setBlock(pos.below(), net.minecraft.world.level.block.Blocks.AIR.defaultBlockState(), 3);
                        level.setBlock(pos.below(2), net.minecraft.world.level.block.Blocks.AIR.defaultBlockState(), 3);

                        if (isX) {
                            level.setBlock(pos.below().east(), net.minecraft.world.level.block.Blocks.AIR.defaultBlockState(), 3);
                            level.setBlock(pos.below().west(), net.minecraft.world.level.block.Blocks.AIR.defaultBlockState(), 3);
                        } else {
                            level.setBlock(pos.below().north(), net.minecraft.world.level.block.Blocks.AIR.defaultBlockState(), 3);
                            level.setBlock(pos.below().south(), net.minecraft.world.level.block.Blocks.AIR.defaultBlockState(), 3);
                        }

                        // 5.12. ΔΗΜΙΟΥΡΓΙΑ
                        inf2024072.antikythera.entity.AncientAutomatonEntity talos = inf2024072.antikythera.entity.ModEntities.ANCIENT_AUTOMATON.get().create(serverLevel);

                        // 5.13. ΕΜΦΑΝΙΣΗ
                        if (talos != null) {
                            talos.moveTo((double)pos.getX() + 0.5D, (double)pos.getY() - 2.0D, (double)pos.getZ() + 0.5D, 0.0F, 0.0F);
                            serverLevel.addFreshEntity(talos);
                        }
                    }
                }
            }
        }
    }
}