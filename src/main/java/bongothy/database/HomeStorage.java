package bongothy.database;

import bongothy.DatabaseManager;
import org.bukkit.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class HomeStorage {
    private final DatabaseManager dbManager;

    public HomeStorage(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public void saveHome(UUID playerUUID, Location location) throws SQLException {
        String query = "REPLACE INTO player_homes (player_uuid, world, x, y, z) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, playerUUID.toString());
            stmt.setString(2, location.getWorld().getName());
            stmt.setDouble(3, location.getX());
            stmt.setDouble(4, location.getY());
            stmt.setDouble(5, location.getZ());
            stmt.executeUpdate();
        }
    }

    public Location getHome(UUID playerUUID) throws SQLException {
        String query = "SELECT world, x, y, z FROM player_homes WHERE player_uuid = ?";
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, playerUUID.toString());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String world = rs.getString("world");
                double x = rs.getDouble("x");
                double y = rs.getDouble("y");
                double z = rs.getDouble("z");
                return new Location(org.bukkit.Bukkit.getWorld(world), x, y, z);
            }
        }
        return null;
    }
}
