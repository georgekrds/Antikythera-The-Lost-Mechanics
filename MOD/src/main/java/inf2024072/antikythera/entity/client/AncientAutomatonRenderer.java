package inf2024072.antikythera.entity.client;

// 1. ΕΙΣΑΓΩΓΕΣ
import inf2024072.antikythera.AntikytheraForge;
import inf2024072.antikythera.entity.AncientAutomatonEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

// 2. ΚΛΑΣΗ
public class AncientAutomatonRenderer extends MobRenderer<AncientAutomatonEntity, AncientAutomatonModel> {

    // 2.1. ΚΑΤΑΣΚΕΥΑΣΤΗΣ
    public AncientAutomatonRenderer(EntityRendererProvider.Context context) {
        super(context, new AncientAutomatonModel(context.bakeLayer(AntikytheraForge.AUTOMATON_LAYER)), 0.5f);
    }

    // 2.2. ΥΦΗ
    @Override
    public ResourceLocation getTextureLocation(AncientAutomatonEntity entity) {
        return ResourceLocation.fromNamespaceAndPath("antikythera", "textures/entity/ancient_automaton.png");
    }
}