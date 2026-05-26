package inf2024072.antikythera.item;

// 1. ΕΙΣΑΓΩΓΕΣ
import inf2024072.antikythera.entity.ModEntities;
import inf2024072.antikythera.item.custom.ChronosDialItem;
import inf2024072.antikythera.item.custom.CelestialTrackerItem;
import inf2024072.antikythera.block.ModBlocks;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

// 2. ΚΛΑΣΗ
public class ModItems {

    // 2.1. ΜΗΤΡΩΟ
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "antikythera");

    // 2.2. ΑΝΤΙΚΕΙΜΕΝΑ
    public static final RegistryObject<Item> ANCIENT_GEAR = ITEMS.register("ancient_gear", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ANTIKYTHERA_CORE = ITEMS.register("antikythera_core", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CHRONOS_DIAL = ITEMS.register("chronos_dial", () -> new ChronosDialItem(new Item.Properties().durability(9)));
    public static final RegistryObject<Item> CELESTIAL_TRACKER = ITEMS.register("celestial_tracker", () -> new CelestialTrackerItem(new Item.Properties().durability(4)));
    public static final RegistryObject<Item> AUTOMATON_SPAWN_EGG = ITEMS.register("automaton_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.ANCIENT_AUTOMATON, 0x7b8b8b, 0x3b4d4d, new Item.Properties()));

    // 2.3. ΕΓΓΡΑΦΗ
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    // 2.4. ΚΑΡΤΕΛΑ
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == net.minecraft.world.item.CreativeModeTabs.INGREDIENTS) {
            event.accept(ANCIENT_GEAR);
            event.accept(ANTIKYTHERA_CORE);
            event.accept(CHRONOS_DIAL);
            event.accept(CELESTIAL_TRACKER);
            event.accept(AUTOMATON_SPAWN_EGG);
            event.accept(ModBlocks.OXIDIZED_BRONZE_ORE);
        }
    }
}