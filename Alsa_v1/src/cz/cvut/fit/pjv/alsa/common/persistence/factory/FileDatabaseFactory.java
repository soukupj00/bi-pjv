package cz.cvut.fit.pjv.alsa.common.persistence.factory;

import cz.cvut.fit.pjv.alsa.common.persistence.Database;
import cz.cvut.fit.pjv.alsa.common.persistence.FileDatabase;

public class FileDatabaseFactory implements DatabaseFactory{

    @Override
    public Database createDatabase() {
        return new FileDatabase();
    }

}
