package thaumcraft.common.lib.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import thaumcraft.Thaumcraft;

/**
 * PacketHandler - Manages network communication for Thaumcraft.
 * Uses Forge's SimpleChannel system for packet registration and handling.
 * 
 * Ported to 1.20.1
 */
public class PacketHandler {
    
    private static final String PROTOCOL_VERSION = "1";
    
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Thaumcraft.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );
    
    private static int packetId = 0;
    
    private static int nextId() {
        return packetId++;
    }
    
    /**
     * Register all packets
     * Called during mod initialization
     */
    public static void init() {
        Thaumcraft.LOGGER.info("Registering Thaumcraft network packets");
        
        // Player data sync packets (server -> client)
        // INSTANCE.messageBuilder(PacketSyncKnowledge.class, nextId(), NetworkDirection.PLAY_TO_CLIENT)
        //         .encoder(PacketSyncKnowledge::encode)
        //         .decoder(PacketSyncKnowledge::decode)
        //         .consumerMainThread(PacketSyncKnowledge::handle)
        //         .add();
        
        // INSTANCE.messageBuilder(PacketSyncWarp.class, nextId(), NetworkDirection.PLAY_TO_CLIENT)
        //         .encoder(PacketSyncWarp::encode)
        //         .decoder(PacketSyncWarp::decode)
        //         .consumerMainThread(PacketSyncWarp::handle)
        //         .add();
        
        // Aura sync packets (server -> client)
        // INSTANCE.messageBuilder(PacketAuraToClient.class, nextId(), NetworkDirection.PLAY_TO_CLIENT)
        //         .encoder(PacketAuraToClient::encode)
        //         .decoder(PacketAuraToClient::decode)
        //         .consumerMainThread(PacketAuraToClient::handle)
        //         .add();
        
        // Tile entity sync packets (server -> client)
        // INSTANCE.messageBuilder(PacketTileToClient.class, nextId(), NetworkDirection.PLAY_TO_CLIENT)
        //         .encoder(PacketTileToClient::encode)
        //         .decoder(PacketTileToClient::decode)
        //         .consumerMainThread(PacketTileToClient::handle)
        //         .add();
        
        // Tile entity update packets (client -> server)
        // INSTANCE.messageBuilder(PacketTileToServer.class, nextId(), NetworkDirection.PLAY_TO_SERVER)
        //         .encoder(PacketTileToServer::encode)
        //         .decoder(PacketTileToServer::decode)
        //         .consumerMainThread(PacketTileToServer::handle)
        //         .add();
        
        // FX packets (server -> client)
        // These will be registered as needed when implementing visual effects
        
        // Research packets (client -> server)
        // INSTANCE.messageBuilder(PacketSyncProgressToServer.class, nextId(), NetworkDirection.PLAY_TO_SERVER)
        //         .encoder(PacketSyncProgressToServer::encode)
        //         .decoder(PacketSyncProgressToServer::decode)
        //         .consumerMainThread(PacketSyncProgressToServer::handle)
        //         .add();
        
        // Focus packets (client -> server)
        // INSTANCE.messageBuilder(PacketFocusNodesToServer.class, nextId(), NetworkDirection.PLAY_TO_SERVER)
        //         .encoder(PacketFocusNodesToServer::encode)
        //         .decoder(PacketFocusNodesToServer::decode)
        //         .consumerMainThread(PacketFocusNodesToServer::handle)
        //         .add();
        
        // Misc event packets
        // INSTANCE.messageBuilder(PacketMiscEvent.class, nextId(), NetworkDirection.PLAY_TO_CLIENT)
        //         .encoder(PacketMiscEvent::encode)
        //         .decoder(PacketMiscEvent::decode)
        //         .consumerMainThread(PacketMiscEvent::handle)
        //         .add();
        
        Thaumcraft.LOGGER.info("Thaumcraft network packets registered");
    }
    
    /**
     * Send a packet to a specific player
     * @param packet the packet to send
     * @param player the player to send to
     */
    public static <MSG> void sendToPlayer(MSG packet, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), packet);
    }
    
    /**
     * Send a packet to all players
     * @param packet the packet to send
     */
    public static <MSG> void sendToAll(MSG packet) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), packet);
    }
    
    /**
     * Send a packet to all players tracking a specific entity
     * @param packet the packet to send
     * @param entity the entity being tracked
     */
    public static <MSG> void sendToAllTracking(MSG packet, net.minecraft.world.entity.Entity entity) {
        INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), packet);
    }
    
    /**
     * Send a packet to the server
     * @param packet the packet to send
     */
    public static <MSG> void sendToServer(MSG packet) {
        INSTANCE.sendToServer(packet);
    }
    
    /**
     * Send a packet to all players near a point
     * @param packet the packet to send
     * @param targetPoint the point to check distance from
     */
    public static <MSG> void sendToNear(MSG packet, PacketDistributor.TargetPoint targetPoint) {
        INSTANCE.send(PacketDistributor.NEAR.with(() -> targetPoint), packet);
    }
    
    /**
     * Send a packet to all players in a dimension
     * @param packet the packet to send
     * @param dimension the dimension key
     */
    public static <MSG> void sendToDimension(MSG packet, net.minecraft.resources.ResourceKey<net.minecraft.world.level.Level> dimension) {
        INSTANCE.send(PacketDistributor.DIMENSION.with(() -> dimension), packet);
    }
}
