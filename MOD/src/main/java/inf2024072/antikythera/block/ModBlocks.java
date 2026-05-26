package inf2024072.antikythera.block;

// 1. ΕΙΣΑΓΩΓΕΣ
import inf2024072.antikythera.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import java.util.function.Supplier;
import inf2024072.antikythera.block.custom.OxidizedBronzeOreBlock;

// 2. ΚΛΑΣΗ
public class ModBlocks {

    // 2.1. ΜΗΤΡΩΟ
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "antikythera");

    // 2.2. ΚΥΒΟΙ
    public static final RegistryObject<Block> OXIDIZED_BRONZE_ORE = registerBlock("oxidized_bronze_ore", () -> new OxidizedBronzeOreBlock(BlockBehaviour.Properties.of().destroyTime(4.0f).requiresCorrectToolForDrops()));

    // 2.3. ΕΓΓΡΑΦΗ
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    // 2.4. ΑΝΤΙΚΕΙΜΕΝΑ
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    // 2.5. ΑΡΧΙΚΟΠΟΙΗΣΗ
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}