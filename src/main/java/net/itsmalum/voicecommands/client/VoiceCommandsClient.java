package net.itsmalum.voicecommands.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.effect.StatusEffect;

import net.minecraft.network.MessageType;
import net.minecraft.potion.Potion;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.lwjgl.glfw.GLFW;


public class VoiceCommandsClient implements ClientModInitializer {

    //Initialize Packets for effects and mobs
    Identifier Poison_Packet = Registry.POTION.getId(Potion.byId("poison"));
    Identifier Leaping_Packet = Registry.POTION.getId(Potion.byId("leaping"));
    Identifier Slowness_Packet = Registry.POTION.getId(Potion.byId("slowness"));
    Identifier Nausea_Packet = Registry.STATUS_EFFECT.getId(StatusEffect.byRawId(9));
    Identifier Blindness_Packet = Registry.STATUS_EFFECT.getId(StatusEffect.byRawId(15));
    Identifier Hunger_Packet = Registry.STATUS_EFFECT.getId(StatusEffect.byRawId(17));
    Identifier Speed_Packet = Registry.STATUS_EFFECT.getId(StatusEffect.byRawId(1));
    Identifier Spawn_Zombie_Packet = Identifier.tryParse("zombie");
    Identifier Spawn_Creeper_Packet = Identifier.tryParse("creeper");
    Identifier Spawn_Skeleton_Packet = Identifier.tryParse("skeleton");
    Identifier Spawn_Spider_Packet = Identifier.tryParse("spider");
    Identifier Spawn_Ghast_Packet = Identifier.tryParse("ghast");
    Identifier Spawn_Dragon_Packet = Identifier.tryParse("dragon");
    Identifier Spawn_Blaze_Packet = Identifier.tryParse("blaze");
    Identifier Spawn_Wither_Packet = Identifier.tryParse("wither");
    Identifier Spawn_Silverfish_Packet = Identifier.tryParse("silver");
    Identifier Spawn_Pillager_Packet = Identifier.tryParse("pillager");



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
        KeyBinding spawnZombiesKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("spawnZombiesKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_I, "Voice Commands Mod"));
        KeyBinding spawnCreeperKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("spawnCreeperKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_J, "Voice Commands Mod"));
        KeyBinding spawnSkeletonKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("spawnSkeletonKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_N, "Voice Commands Mod"));
        KeyBinding spawnSpiderKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("spawnSpiderKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_U, "Voice Commands Mod"));
        KeyBinding spawnGhastKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("spawnGhastKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_H, "Voice Commands Mod"));
        KeyBinding spawnDragonKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("spawnDragonKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_B, "Voice Commands Mod"));
        KeyBinding spawnBlazeKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("spawnBlazeKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F11, "Voice Commands Mod"));
        KeyBinding spawnWitherKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("spawnWitherKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F12, "Voice Commands Mod"));
        KeyBinding spawnSilverKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("spawnSilverfishKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F13, "Voice Commands Mod"));
        KeyBinding spawnPillagerKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("spawnPillagerKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F14, "Voice Commands Mod"));


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

            else if(spawnZombiesKB.wasPressed())
            {
                ClientPlayNetworking.send(Spawn_Zombie_Packet, PacketByteBufs.empty());
            }

            else if(spawnZombiesKB.wasPressed())
            {
                ClientPlayNetworking.send(Spawn_Zombie_Packet, PacketByteBufs.empty());
            }

            else if(spawnCreeperKB.wasPressed())
            {
                ClientPlayNetworking.send(Spawn_Creeper_Packet, PacketByteBufs.empty());
            }

            else if(spawnSkeletonKB.wasPressed())
            {
                ClientPlayNetworking.send(Spawn_Skeleton_Packet, PacketByteBufs.empty());
            }

            else if(spawnSpiderKB.wasPressed())
            {
                ClientPlayNetworking.send(Spawn_Spider_Packet, PacketByteBufs.empty());
            }

            else if(spawnGhastKB.wasPressed())
            {
                ClientPlayNetworking.send(Spawn_Ghast_Packet, PacketByteBufs.empty());
            }

            else if(spawnDragonKB.wasPressed())
            {
                ClientPlayNetworking.send(Spawn_Dragon_Packet, PacketByteBufs.empty());
            }

            else if(spawnBlazeKB.wasPressed())
            {
                ClientPlayNetworking.send(Spawn_Blaze_Packet, PacketByteBufs.empty());
            }

            else if(spawnWitherKB.wasPressed())
            {
                ClientPlayNetworking.send(Spawn_Wither_Packet, PacketByteBufs.empty());
            }
            else if(spawnSilverKB.wasPressed())
            {
                ClientPlayNetworking.send(Spawn_Silverfish_Packet, PacketByteBufs.empty());
            }

            else if(spawnPillagerKB.wasPressed())
            {
                ClientPlayNetworking.send(Spawn_Pillager_Packet, PacketByteBufs.empty());
            }

        });
    }
}
