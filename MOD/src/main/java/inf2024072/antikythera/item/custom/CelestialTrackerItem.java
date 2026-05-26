package inf2024072.antikythera.item.custom;

// 1. ΕΙΣΑΓΩΓΕΣ
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

// 2. ΚΛΑΣΗ
public class CelestialTrackerItem extends Item {

    // 2.1. ΚΑΤΑΣΚΕΥΑΣΤΗΣ
    public CelestialTrackerItem(Properties properties) {
        super(properties);
    }

    // 2.2. ΧΡΗΣΗ
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        // 2.3. ΗΧΟΣ
        player.playSound(net.minecraft.sounds.SoundEvents.LODESTONE_COMPASS_LOCK, 1.0F, 1.0F);
        // 2.4. ΚΟΣΜΟΣ
        if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
            // 2.5. ΜΗΝΥΜΑ
            int x = ((int)player.getX() / 10) * 10;
            int z = ((int)player.getZ() / 10) * 10;
            player.displayClientMessage(Component.literal("Αρχαία ερείπια εντοπίστηκαν κοντά στις συντεταγμένες X: " + x + " Z: " + z), true);
            // 2.6. ΦΘΟΡΑ
            ItemStack stack = player.getItemInHand(hand);
            stack.hurtAndBreak(1, player, net.minecraft.world.entity.EquipmentSlot.MAINHAND);

            // 2.7. ΕΜΦΑΝΙΣΗ
            if (stack.isEmpty()) {
                inf2024072.antikythera.entity.AncientAutomatonEntity talos = inf2024072.antikythera.entity.ModEntities.ANCIENT_AUTOMATON.get().create(serverLevel);
                if (talos != null) {
                    talos.moveTo(player.getX(), player.getY(), player.getZ(), 0.0F, 0.0F);
                    serverLevel.addFreshEntity(talos);
                }
            }
        }

        // 2.8. ΕΠΙΣΤΡΟΦΗ
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}