package fr.florianpal.hypercraftjoinmessage.queries;

import fr.florianpal.hypercraftjoinmessage.HypercraftJoinMessage;
import fr.florianpal.hypercraftjoinmessage.IDatabaseTable;
import fr.florianpal.hypercraftjoinmessage.managers.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FirstJoinQueries implements IDatabaseTable {

    private static final String ADD_PLAYER = "INSERT INTO first_join (playerUuid) VALUES(?)";
    private static final String GET_VOTES = "SELECT * FROM first_join where playerUuid=?";
    private DatabaseManager databaseManager;
    private HypercraftJoinMessage plugin;

    public FirstJoinQueries(HypercraftJoinMessage plugin) {
        this.plugin = plugin;
        this.databaseManager = plugin.getDatabaseManager();
    }

    public void addPlayer(final String playerUUID) {
        plugin.getProxy().getScheduler().runAsync(plugin, new Runnable() {
            @Override
            public void run() {
                PreparedStatement statement = null;
                try (Connection connection = databaseManager.getConnection()) {
                    statement = connection.prepareStatement(ADD_PLAYER);
                    statement.setString(1, playerUUID);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (statement != null) {
                            statement.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    public String getPlayer(String playerUuid){
        String uuid = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try (Connection connection = databaseManager.getConnection()) {
            statement = connection.prepareStatement(GET_VOTES);
            statement.setString(1, playerUuid);
            result = statement.executeQuery();
            if(result.next()) {
                uuid = result.getString(1);
            } else {
                uuid = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return uuid;
    }


    @Override
    public String[] getTable() {
        return new String[]{"first_join",
                "`playerUuid` VARCHAR(36) NOT NULL, " +
                        "PRIMARY KEY (`playerUuid`)",
                "DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci"};
    }
}
