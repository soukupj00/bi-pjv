package cz.cvut.fit.pjv.alsa.common.persistence.factory;

import cz.cvut.fit.pjv.alsa.common.persistence.Database;
import cz.cvut.fit.pjv.alsa.common.persistence.FileDatabase;
import cz.cvut.fit.pjv.alsa.common.persistence.InMemoryDatabase;

public class InMemoryDatabaseFactory implements DatabaseFactory{

    @Override
    public Database createDatabase() {
        return new InMemoryDatabase();
    }

}
