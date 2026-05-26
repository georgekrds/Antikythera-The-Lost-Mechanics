package inf2024072.antikythera.sound;

// 1. ΕΙΣΑΓΩΓΕΣ
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// 2. ΚΛΑΣΗ
public class ModSounds {

    // 2.1. ΜΗΤΡΩΟ
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "antikythera");

    // 2.2. ΗΧΟΙ
    public static final RegistryObject<SoundEvent> AUTOMATON_ATTACK = registerSoundEvent("automaton_attack");
    public static final RegistryObject<SoundEvent> AUTOMATON_HURT = registerSoundEvent("automaton_hurt");
    public static final RegistryObject<SoundEvent> AUTOMATON_AMBIENT = registerSoundEvent("automaton_ambient");
    public static final RegistryObject<SoundEvent> AUTOMATON_STEP = registerSoundEvent("automaton_step");
    public static final RegistryObject<SoundEvent> AUTOMATON_SPAWN = registerSoundEvent("automaton_spawn");

    // 2.3. ΔΗΜΙΟΥΡΓΙΑ
    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        // 2.4. ΤΟΠΟΘΕΣΙΑ
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath("antikythera", name);
        // 2.5. ΕΠΙΣΤΡΟΦΗ
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    // 2.6. ΕΓΓΡΑΦΗ
    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}