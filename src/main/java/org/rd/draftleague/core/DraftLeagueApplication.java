package org.rd.draftleague.core;

import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.rd.draftleague.core.dao.PlayerDAO;
import org.rd.draftleague.core.model.Player;
import org.rd.draftleague.core.resources.PlayersResource;

public class DraftLeagueApplication extends Application<DraftLeagueApiConfiguration> {

    public static void main(String[] args) throws Exception {
        new DraftLeagueApplication().run(args);
    }

    private final HibernateBundle<DraftLeagueApiConfiguration> hibernateBundle
            = new HibernateBundle<DraftLeagueApiConfiguration>(
            Player.class
    ) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(DraftLeagueApiConfiguration draftLeagueApiConfiguration) {
            return draftLeagueApiConfiguration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<DraftLeagueApiConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(DraftLeagueApiConfiguration configuration, Environment environment) {
        final PlayerDAO playerDAO = new PlayerDAO(hibernateBundle.getSessionFactory());

        environment.jersey().register(new PlayersResource(playerDAO));
    }
}
