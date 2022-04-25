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
import java.util.Timer;
import java.util.TimerTask;


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
    Identifier Spawn_Ravager_Packet = Identifier.tryParse("ravager");
    Identifier Spawn_Vex_Packet = Identifier.tryParse("vex");
    Identifier Spawn_Brute_Packet = Identifier.tryParse("brute");
    Identifier Spawn_Piglin_Packet = Identifier.tryParse("piglin");
    Identifier Spawn_Giant_Packet = Identifier.tryParse("giant");
    int numInvert;


    @Override
    public void onInitializeClient() {
        numInvert = 1;

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
        KeyBinding spawnBlazeKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("spawnBlazeKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F15, "Voice Commands Mod"));
        KeyBinding spawnWitherKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("spawnWitherKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F12, "Voice Commands Mod"));
        KeyBinding spawnSilverKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("spawnSilverfishKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F13, "Voice Commands Mod"));
        KeyBinding spawnPillagerKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("spawnPillagerKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F14, "Voice Commands Mod"));
        KeyBinding spawnRavagerKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("spawnRavagerKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F16, "Voice Commands Mod"));
        KeyBinding spawnVexKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("spawnVexKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F17, "Voice Commands Mod"));
        KeyBinding spawnBruteKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("spawnBruteKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F18, "Voice Commands Mod"));
        KeyBinding spawnPiglinKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("spawnPiglinKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F19, "Voice Commands Mod"));
        KeyBinding spawnGiantKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("spawnGiantKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F20, "Voice Commands Mod"));
        KeyBinding invertedMouseKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("invertMouseKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F21, "Voice Commands Mod"));
        KeyBinding mouseSensKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("mouseSensKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F22, "Voice Commands Mod"));
        KeyBinding fovKB = KeyBindingHelper.registerKeyBinding(new KeyBinding("fovKB", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F23, "Voice Commands Mod"));


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

            else if(spawnRavagerKB.wasPressed())
            {
                ClientPlayNetworking.send(Spawn_Ravager_Packet, PacketByteBufs.empty());
            }

            else if(spawnVexKB.wasPressed())
            {
                ClientPlayNetworking.send(Spawn_Vex_Packet, PacketByteBufs.empty());
            }

            else if(spawnBruteKB.wasPressed())
            {
                ClientPlayNetworking.send(Spawn_Brute_Packet, PacketByteBufs.empty());
            }

            else if(spawnPiglinKB.wasPressed())
            {
                ClientPlayNetworking.send(Spawn_Piglin_Packet, PacketByteBufs.empty());
            }

            else if(spawnGiantKB.wasPressed())
            {
                ClientPlayNetworking.send(Spawn_Giant_Packet, PacketByteBufs.empty());
            }

            else if(invertedMouseKB.wasPressed())
            {
                client.options.invertYMouse = true;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        client.options.invertYMouse = false;
                        numInvert += 1;
                    }
                },numInvert*60*1000);

            }
        });


    }
}
