package cz.cvut.fit.pjv.alsa.common.persistence.factory;

import cz.cvut.fit.pjv.alsa.common.persistence.Database;
import cz.cvut.fit.pjv.alsa.common.persistence.ProductMapper;
import cz.cvut.fit.pjv.alsa.common.persistence.SQLiteDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDatabaseFactory implements DatabaseFactory{

    @Override
    public Database createDatabase() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:E:/2. Semestr/PJV/Alsa_v1/sqlite.db");
            SQLiteDatabase database = new SQLiteDatabase(connection, new ProductMapper());
            return database;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

}
