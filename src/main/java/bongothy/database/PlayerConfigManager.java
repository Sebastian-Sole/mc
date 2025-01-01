package bongothy.database;

import bongothy.DatabaseManager;

import java.sql.*;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerConfigManager {

    private final DatabaseManager databaseManager;
    private final Map<UUID, PlayerConfigData> playerCache = new ConcurrentHashMap<>();

    public PlayerConfigManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void savePlayerConfig(PlayerConfigData data) throws SQLException {
        String query = "INSERT INTO player_config (uuid, diamonds_enabled) VALUES (?, ?) " +
                "ON DUPLICATE KEY UPDATE diamonds_enabled = ?";
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, data.playerUUID().toString());
            stmt.setBoolean(2, data.diamondsEnabled());
            stmt.setBoolean(3, data.diamondsEnabled());
            stmt.executeUpdate();

            playerCache.put(data.playerUUID(), data);
        }
    }

    public PlayerConfigData loadPlayerConfigFromDB(UUID playerUUID) throws SQLException {
        String query = "SELECT uuid, diamonds_enabled FROM player_config WHERE uuid = ?";
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, playerUUID.toString());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                var data =  new PlayerConfigData(
                        playerUUID,
                        rs.getBoolean("diamondsEnabled")
                );
                playerCache.put(playerUUID, data);
                return data;
            }
        }
        return null;
    }

    public PlayerConfigData loadPlayerConfigFromCache(UUID playerUUID) {
        return playerCache.get(playerUUID);
    }

    public boolean isDiamondsEnabled(UUID playerUUID) {
        PlayerConfigData data = playerCache.get(playerUUID);
        if(data == null) {
            try {
                data = loadPlayerConfigFromDB(playerUUID);
                playerCache.put(playerUUID, data);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return data != null && data.diamondsEnabled();
    }

    public void updateDiamondsEnabled(UUID playerUUID, boolean diamondsEnabled) throws SQLException {
        String query = "UPDATE player_config SET diamonds_enabled = ? WHERE uuid = ?";
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setBoolean(1, diamondsEnabled);
            stmt.setString(2, playerUUID.toString());
            stmt.executeUpdate();

            PlayerConfigData data = new PlayerConfigData(playerUUID, diamondsEnabled);
            playerCache.put(playerUUID, data);
        }
    }

    public PlayerConfigData createDefaultPlayerConfig(UUID playerUUID) {
        return new PlayerConfigData(playerUUID, false);
    }

    public void loadConfigOnJoin(UUID playerUUID) {
        PlayerConfigData data;
        try {
            data = loadPlayerConfigFromDB(playerUUID);
        } catch (SQLException e) {
            data = loadPlayerConfigFromCache(playerUUID);
        }
        if (data == null) {
            data = createDefaultPlayerConfig(playerUUID);
            try {
                savePlayerConfig(data);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
