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

	//Initializing number of times an effect is used / mob is spawned
	static int numPoison = 0;
	static int numLeaping = 0;
	static int numSlowness = 0;
	static int numNausea = 0;
	static int numBlindness = 0;
	static int numHunger = 0;
	static int numSpeed = 0;
	static int numZombie = 0;
	static int numCreeper = 0;
	static int numSkeleton = 0;
	static int numSpider = 0;
	static  int numGhast = 0;
	static int numDragon = 0;
	static int numBlaze = 0;
	static int numWither = 0;
	static int numSilver = 0;
	static int numPillager = 0;
	static int numRavager = 0;
	static int numVex = 0;
	static int numBrute = 0;
	static int numPiglin = 0;
	static int numGiant = 0;


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

	//Register Packets
	@Override
	public void onInitialize() {
	 	// set variables to 0 on initialize
		numPoison = -1;
	 	numLeaping = -1;
		numSlowness = -1;
		numNausea = -1;
		numBlindness = -1;
		numHunger = -1;
		numSpeed = -1;
		numZombie = 1;
		numCreeper = 1;
		numSkeleton = 1;
		numSpider = 1;
		numGhast = 1;
		numDragon = 1;
		numWither = 1;
		numBlaze = 1;
		numSilver = 1;
		numPillager = 1;
		numRavager = 1;
		numVex = 1;
		numBrute = 1;
		numPiglin = 1;
		numGiant = 1;

		//Initialize Packets to handle functions
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
		ServerPlayNetworking.registerGlobalReceiver(Spawn_Ghast_Packet, VoiceCommands::handleGhast);
		ServerPlayNetworking.registerGlobalReceiver(Spawn_Dragon_Packet, VoiceCommands::handleDragon);
		ServerPlayNetworking.registerGlobalReceiver(Spawn_Blaze_Packet, VoiceCommands::handleBlaze);
		ServerPlayNetworking.registerGlobalReceiver(Spawn_Wither_Packet, VoiceCommands::handleWither);
		ServerPlayNetworking.registerGlobalReceiver(Spawn_Silverfish_Packet, VoiceCommands::handleSilver);
		ServerPlayNetworking.registerGlobalReceiver(Spawn_Pillager_Packet, VoiceCommands::handlePillager);
		ServerPlayNetworking.registerGlobalReceiver(Spawn_Ravager_Packet, VoiceCommands::handleRavager);
		ServerPlayNetworking.registerGlobalReceiver(Spawn_Vex_Packet, VoiceCommands::handleVex);
		ServerPlayNetworking.registerGlobalReceiver(Spawn_Brute_Packet, VoiceCommands::handleBrute);
		ServerPlayNetworking.registerGlobalReceiver(Spawn_Piglin_Packet, VoiceCommands::handlePiglin);
		ServerPlayNetworking.registerGlobalReceiver(Spawn_Giant_Packet, VoiceCommands::handleGiant);
	}

	//handle status effect functions
	public static void handlePoison(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			player.setStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 200, numPoison), null);
		});
		numPoison += 1;
	}

	public static void handleLeaping(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			player.setStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 200, numLeaping), null);
		});
		numLeaping += 1;
	}

	public static void handleSlowness(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			player.setStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, numSlowness), null);
		});
		numSlowness += 1;
	}

	public static void handleNausea(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			player.setStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, numNausea), null);
		});
		numNausea += 1;
	}

	public static void handleBlindness(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			player.setStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 200, numBlindness), null);
		});
		numBlindness += 1;
	}

	public static void handleHunger(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			player.setStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 200, numHunger), null);
		});
		numHunger += 1;
	}

	public static void handleSpeed(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			player.setStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, numSpeed), null);
		});
		numSpeed += 1;
	}

	public static void handleZombie(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			int max = 5 * numZombie;
			int i;
			double x_cord = player.getPos().x;
			double y_cord = player.getPos().y;
			double z_cord = player.getPos().z;
			CommandManager commandManager = player.getServer().getCommandManager();
			for(i = 1; i <= max; i++){
				x_cord = player.getPos().x;
				y_cord = player.getPos().y;
				z_cord = player.getPos().z;

				if(i % 4 == 0)
				{
					x_cord += 5;
				}

				else if(i % 3 == 0)
				{
					z_cord += 5;
				}

				else if(i % 2 == 0)
				{
					x_cord -= 5;
				}

				else
				{
					z_cord -= 5;
				}
				commandManager.execute(server.getCommandSource().withEntity(player), "summon zombie " + x_cord + " " + y_cord + " " + z_cord);
			}
			numZombie += 1;
		});
	}

	public static void handleCreeper(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			int max = 5 * numCreeper;
			int i;
			double x_cord = player.getPos().x;
			double y_cord = player.getPos().y;
			double z_cord = player.getPos().z;
			CommandManager commandManager = player.getServer().getCommandManager();
			for(i = 1; i <= max; i++){
				commandManager.execute(server.getCommandSource().withEntity(player), "summon creeper " + x_cord + " " + y_cord + " " + z_cord);
			}
			numCreeper += 1;
		});
	}

	public static void handleSkeleton(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			int max = 5 * numSkeleton;
			int i;
			double x_cord = player.getPos().x;
			double y_cord = player.getPos().y;
			double z_cord = player.getPos().z;
			CommandManager commandManager = player.getServer().getCommandManager();
			for(i = 1; i <= max; i++){
				commandManager.execute(server.getCommandSource().withEntity(player), "summon skeleton " + x_cord + " " + y_cord + " " + z_cord);
			}
			numSkeleton += 1;
		});
	}

	public static void handleSpider(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			int max = 5 * numSpider;
			int i;
			double x_cord = player.getPos().x;
			double y_cord = player.getPos().y;
			double z_cord = player.getPos().z;
			CommandManager commandManager = player.getServer().getCommandManager();
			for(i = 1; i <= max; i++){
				commandManager.execute(server.getCommandSource().withEntity(player), "summon spider " + x_cord + " " + y_cord + " " + z_cord);
			}
			numSpider += 1;
		});
	}

	public static void handleGhast(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			int max = 2 * numGhast;
			int i;
			double x_cord = player.getPos().x;
			double y_cord = player.getPos().y;
			double z_cord = player.getPos().z;
			CommandManager commandManager = player.getServer().getCommandManager();
			for(i = 1; i <= max; i++){
				commandManager.execute(server.getCommandSource().withEntity(player), "summon ghast " + x_cord + " " + y_cord + " " + z_cord);
			}
			numGhast += 1;
		});
	}

	public static void handleDragon(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			int max = 1 + numDragon -1;
			int i;
			double x_cord = player.getPos().x;
			double y_cord = player.getPos().y;
			double z_cord = player.getPos().z;
			CommandManager commandManager = player.getServer().getCommandManager();
			for(i = 1; i <= max; i++){
				commandManager.execute(server.getCommandSource().withEntity(player), "summon ender_dragon " + x_cord + " " + y_cord + " " + z_cord);
			}
			numDragon += 1;
		});
	}

	public static void handleBlaze(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			int max = 3 * numBlaze;
			int i;
			double x_cord = player.getPos().x;
			double y_cord = player.getPos().y;
			double z_cord = player.getPos().z;
			CommandManager commandManager = player.getServer().getCommandManager();
			for(i = 1; i <= max; i++){
				commandManager.execute(server.getCommandSource().withEntity(player), "summon blaze " + x_cord + " " + y_cord + " " + z_cord);
			}
			numBlaze += 1;
		});
	}

	public static void handleWither(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			int max = 1 + numWither - 1;
			int i;
			double x_cord = player.getPos().x;
			double y_cord = player.getPos().y;
			double z_cord = player.getPos().z;
			CommandManager commandManager = player.getServer().getCommandManager();
			for(i = 1; i <= max; i++){
				commandManager.execute(server.getCommandSource().withEntity(player), "summon wither " + x_cord + " " + y_cord + " " + z_cord);
			}
			numWither += 1;
		});
	}

	public static void handleSilver(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			int max = 5 * numSilver;
			int i;
			double x_cord = player.getPos().x;
			double y_cord = player.getPos().y;
			double z_cord = player.getPos().z;
			CommandManager commandManager = player.getServer().getCommandManager();
			for(i = 1; i <= max; i++){
				commandManager.execute(server.getCommandSource().withEntity(player), "summon silverfish " + x_cord + " " + y_cord + " " + z_cord);
			}
			numWither += 1;
		});
	}

	public static void handlePillager(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			int max = 4 * numPillager;
			int i;
			double x_cord = player.getPos().x;
			double y_cord = player.getPos().y;
			double z_cord = player.getPos().z;
			String vindicator = "vindicator";
			String evoker = "evoker";
			String pillager = "pillager";
			String illusioner = "illusioner";

			CommandManager commandManager = player.getServer().getCommandManager();
			for(i = 1; i <= max; i++){
				commandManager.execute(server.getCommandSource().withEntity(player), "summon pillager " + x_cord + " " + y_cord + " " + z_cord);
			}
			numPillager += 1;
		});
	}

	public static void handleRavager(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			int max = 1 + numRavager - 1;
			int i;
			double x_cord = player.getPos().x;
			double y_cord = player.getPos().y;
			double z_cord = player.getPos().z;
			CommandManager commandManager = player.getServer().getCommandManager();
			for(i = 1; i <= max; i++){
				commandManager.execute(server.getCommandSource().withEntity(player), "summon ravager " + x_cord + " " + y_cord + " " + z_cord);
			}
			numRavager += 1;
		});
	}

	public static void handleVex(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			int max = 4 * numVex;
			int i;
			double x_cord = player.getPos().x;
			double y_cord = player.getPos().y;
			double z_cord = player.getPos().z;
			CommandManager commandManager = player.getServer().getCommandManager();
			for(i = 1; i <= max; i++){
				commandManager.execute(server.getCommandSource().withEntity(player), "summon vex " + x_cord + " " + y_cord + " " + z_cord);
			}
			numVex += 1;
		});
	}

	public static void handleBrute(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			int max = 2 * numBrute;
			int i;
			double x_cord = player.getPos().x;
			double y_cord = player.getPos().y;
			double z_cord = player.getPos().z;
			CommandManager commandManager = player.getServer().getCommandManager();
			for(i = 1; i <= max; i++){
				commandManager.execute(server.getCommandSource().withEntity(player), "summon piglin_brute " + x_cord + " " + y_cord + " " + z_cord);
			}
			numBrute += 1;
		});
	}

	public static void handlePiglin(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			int max = 5 * numPiglin;
			int i;
			double x_cord = player.getPos().x;
			double y_cord = player.getPos().y;
			double z_cord = player.getPos().z;
			CommandManager commandManager = player.getServer().getCommandManager();
			for(i = 1; i <= max; i++){
				commandManager.execute(server.getCommandSource().withEntity(player), "summon piglin " + x_cord + " " + y_cord + " " + z_cord);
			}
			numPiglin += 1;
		});
	}

	public static void handleGiant(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		server.execute(() -> {
			int max = 1 + numGiant - 1;
			int i;
			double x_cord = player.getPos().x;
			double y_cord = player.getPos().y;
			double z_cord = player.getPos().z;
			CommandManager commandManager = player.getServer().getCommandManager();
			for(i = 1; i <= max; i++){
				commandManager.execute(server.getCommandSource().withEntity(player), "summon giant " + x_cord + " " + y_cord + " " + z_cord);
			}
			numGiant += 1;
		});
	}
}
