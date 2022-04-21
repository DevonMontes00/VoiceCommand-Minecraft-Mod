package net.itsmalum.voicecommands.server;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.network.MessageType;
import net.minecraft.potion.Potion;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class VoiceCommandsServer implements DedicatedServerModInitializer {

    @Override
    public void onInitializeServer() {
        MinecraftClient mc = MinecraftClient.getInstance();
        System.out.println("Server Initialize Success");
        mc.inGameHud.addChatMessage(MessageType.SYSTEM, Text.of("Server Initialize Success"), mc.player.getUuid());
        Identifier Poison_Packet = Registry.POTION.getId(Potion.byId("poison"));
        ServerPlayNetworking.registerGlobalReceiver(Poison_Packet, (server, player, handler, buf, responseSender) -> {
            server.execute(() -> {
                        System.out.println("Success Server Side");
                        player.setStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 200, 0), null);
            });
        });
    }


}
