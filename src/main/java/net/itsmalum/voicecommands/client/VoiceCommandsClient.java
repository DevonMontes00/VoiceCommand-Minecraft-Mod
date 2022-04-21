package net.itsmalum.voicecommands.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.effect.StatusEffect;

import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.lwjgl.glfw.GLFW;


public class VoiceCommandsClient implements ClientModInitializer {

    //Initialize Packets for effects
    Identifier Poison_Packet = Registry.POTION.getId(Potion.byId("poison"));
    Identifier Leaping_Packet = Registry.POTION.getId(Potion.byId("leaping"));
    Identifier Slowness_Packet = Registry.POTION.getId(Potion.byId("slowness"));
    Identifier Nausea_Packet = Registry.STATUS_EFFECT.getId(StatusEffect.byRawId(9));
    Identifier Blindness_Packet = Registry.STATUS_EFFECT.getId(StatusEffect.byRawId(15));
    Identifier Hunger_Packet = Registry.STATUS_EFFECT.getId(StatusEffect.byRawId(17));
    Identifier Speed_Packet = Registry.STATUS_EFFECT.getId(StatusEffect.byRawId(1));

    @Override
    public void onInitializeClient() {
        //Set custom keybinds for status effects
        KeyBinding poisonKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("poisonKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_P, "Voice Commands Mod"));
        KeyBinding leapingKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("leapingKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_PERIOD, "Voice Commands Mod"));
        KeyBinding slownessKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("slownessKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_L, "Voice Commands Mod"));
        KeyBinding nauseaKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("nauseaKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_COMMA, "Voice Commands Mod"));
        KeyBinding blindnessKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("blindnessKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_O, "Voice Commands Mod"));
        KeyBinding hungerKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("hungerKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_K, "Voice Commands Mod"));
        KeyBinding speedKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("speedKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_M, "Voice Commands Mod"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(poisonKB.wasPressed()){
                ClientPlayNetworking.send(Poison_Packet, PacketByteBufs.empty());
            }

            else if(leapingKB.wasPressed())
            {
                ClientPlayNetworking.send(Leaping_Packet, PacketByteBufs.empty());
            }

            else if(slownessKB.wasPressed())
            {
                ClientPlayNetworking.send(Slowness_Packet, PacketByteBufs.empty());
            }

            else if(nauseaKB.wasPressed())
            {
                ClientPlayNetworking.send(Nausea_Packet, PacketByteBufs.empty());
            }

            else if(blindnessKB.wasPressed())
            {
                ClientPlayNetworking.send(Blindness_Packet, PacketByteBufs.empty());
            }

            else if(hungerKB.wasPressed())
            {
                ClientPlayNetworking.send(Hunger_Packet, PacketByteBufs.empty());
            }

            else if(speedKB.wasPressed())
            {
                ClientPlayNetworking.send(Speed_Packet, PacketByteBufs.empty());
            }


        });
    }
}
