package net.itsmalum.voicecommands;

import net.fabricmc.api.ModInitializer;
//import net.itsmalum.voicecommands.item.ModItems;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.itsmalum.voicecommands.server.VoiceCommandsServer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.network.MessageType;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.potion.Potion;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.command.SummonCommand;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VoiceCommands implements ModInitializer {
	public static final String MOD_ID="voicecommands";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// Initialize Status Packets
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

	//Register Packets
	@Override
	public void onInitialize() {
		ServerPlayNetworking.registerGlobalReceiver(Poison_Packet, VoiceCommands::handlePoison);
		ServerPlayNetworking.registerGlobalReceiver(Leaping_Packet, VoiceCommands::handleLeaping);
		ServerPlayNetworking.registerGlobalReceiver(Slowness_Packet, VoiceCommands::handleSlowness);
		ServerPlayNetworking.registerGlobalReceiver(Nausea_Packet, VoiceCommands::handleNausea);
		ServerPlayNetworking.registerGlobalReceiver(Blindness_Packet, VoiceCommands::handleBlindness);
		ServerPlayNetworking.registerGlobalReceiver(Hunger_Packet, VoiceCommands::handleHunger);
		ServerPlayNetworking.registerGlobalReceiver(Speed_Packet, VoiceCommands::handleSpeed);
		ServerPlayNetworking.registerGlobalReceiver(Spawn_Zombie_Packet, VoiceCommands::handleZombie);
		ServerPlayNetworking.registerGlobalReceiver(Spawn_Creeper_Packet, VoiceCommands::handleCreeper);
		ServerPlayNetworking.registerGlobalReceiver(Spawn_Skeleton_Packet, VoiceCommands::handleSkeleton);
		ServerPlayNetworking.registerGlobalReceiver(Spawn_Spider_Packet, VoiceCommands::handleSpider);
	}

	//handle status effect functions
	public static void handlePoison(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			player.setStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 200, 0), null);
		});
	}

	public static void handleLeaping(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			player.setStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 200, 4), null);
		});
	}

	public static void handleSlowness(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			player.setStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0), null);
		});
	}

	public static void handleNausea(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			player.setStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 0), null);
		});
	}

	public static void handleBlindness(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			player.setStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 200, 0), null);
		});
	}

	public static void handleHunger(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			player.setStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 200, 0), null);
		});
	}

	public static void handleSpeed(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			player.setStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 3), null);
		});
	}

	public static void handleZombie(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			int max = 9;
			int i = 0;
			double x_cord = player.getPos().x;
			double y_cord = player.getPos().y;
			double z_cord = player.getPos().z;
			CommandManager commandManager = player.getServer().getCommandManager();
			for(i = 0; i < max; i++){
				commandManager.execute(server.getCommandSource().withEntity(player), "summon zombie " + x_cord + " " + y_cord + " " + z_cord);
			}
		});
	}

	public static void handleCreeper(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			int max = 9;
			int i = 0;
			double x_cord = player.getPos().x;
			double y_cord = player.getPos().y;
			double z_cord = player.getPos().z;
			CommandManager commandManager = player.getServer().getCommandManager();
			for(i = 0; i < max; i++){
				commandManager.execute(server.getCommandSource().withEntity(player), "summon creeper " + x_cord + " " + y_cord + " " + z_cord);
			}
		});
	}

	public static void handleSkeleton(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			int max = 9;
			int i = 0;
			double x_cord = player.getPos().x;
			double y_cord = player.getPos().y;
			double z_cord = player.getPos().z;
			CommandManager commandManager = player.getServer().getCommandManager();
			for(i = 0; i < max; i++){
				commandManager.execute(server.getCommandSource().withEntity(player), "summon skeleton " + x_cord + " " + y_cord + " " + z_cord);
			}
		});
	}

	public static void handleSpider(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			int max = 9;
			int i = 0;
			double x_cord = player.getPos().x;
			double y_cord = player.getPos().y;
			double z_cord = player.getPos().z;
			CommandManager commandManager = player.getServer().getCommandManager();
			for(i = 0; i < max; i++){
				commandManager.execute(server.getCommandSource().withEntity(player), "summon spider " + x_cord + " " + y_cord + " " + z_cord);
			}
		});
	}
}
