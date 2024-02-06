package wtf.expensive.modules.impl.combat;

import net.minecraft.network.play.client.CPlayerTryUseItemPacket;
import net.minecraft.util.Hand;
import wtf.expensive.events.Event;
import wtf.expensive.events.impl.game.EventMouseTick;
import wtf.expensive.modules.Function;
import wtf.expensive.modules.FunctionAnnotation;
import wtf.expensive.modules.Type;

@FunctionAnnotation(name = "FastBow", type = Type.Combat)
public class FastBow extends Function {

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventMouseTick mouseTick) {
            if (mouseTick.getButton() == 1) { // Right mouse button
                useBow();
            }
        }
    }

    /**
     * Use the bow quickly by sending the appropriate packet.
     */
    private void useBow() {
        if (!mc.player.getCooldownTracker().hasCooldown(mc.player.getHeldItemMainhand().getItem())) {
            // Send packet to try using item in main hand (bow)
            mc.player.connection.sendPacket(new CPlayerTryUseItemPacket(Hand.MAIN_HAND));
        }
    }
}
