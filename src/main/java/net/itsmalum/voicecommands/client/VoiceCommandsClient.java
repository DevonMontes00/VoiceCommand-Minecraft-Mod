package net.itsmalum.voicecommands.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Potion;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.lwjgl.glfw.GLFW;


public class VoiceCommandsClient implements ClientModInitializer {
    Identifier Poison_Packet = Registry.POTION.getId(Potion.byId("poison"));
    @Override
    public void onInitializeClient() {
        KeyBinding binding1 = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.fabric-key-binding-api-v1-testmod.test_keybinding_1", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_PERIOD, "key.category.first.test"));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(binding1.wasPressed()){
                ClientPlayNetworking.send(Poison_Packet, PacketByteBufs.empty());
                client.player.sendMessage(new LiteralText("Success - P"), false);
            }
        });
    }
}
