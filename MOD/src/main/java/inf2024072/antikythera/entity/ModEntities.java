package inf2024072.antikythera.entity;

// 1. ΕΙΣΑΓΩΓΕΣ
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// 2. ΚΛΑΣΗ
public class ModEntities {

    // 2.1. ΜΗΤΡΩΟ
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "antikythera");

    // 2.2. ΟΝΤΟΤΗΤΑ
    public static final RegistryObject<EntityType<AncientAutomatonEntity>> ANCIENT_AUTOMATON = ENTITY_TYPES.register("ancient_automaton",
            () -> EntityType.Builder.of(AncientAutomatonEntity::new, MobCategory.MONSTER)
                    .sized(1.0f, 2.0f)
                    .build("ancient_automaton"));

    // 2.3. ΕΓΓΡΑΦΗ
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}