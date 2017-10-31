package org.rd.draftleague.core;

import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.rd.draftleague.core.dao.*;
import org.rd.draftleague.core.model.*;
import org.rd.draftleague.core.resources.*;

public class DraftLeagueApplication extends Application<DraftLeagueApiConfiguration> {

    public static void main(String[] args) throws Exception {
        new DraftLeagueApplication().run(args);
    }

    private final HibernateBundle<DraftLeagueApiConfiguration> hibernateBundle
            = new HibernateBundle<DraftLeagueApiConfiguration>(
            Player.class, League.class, Draft.class, CardList.class, Card.class
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
        final LeagueDAO leagueDAO = new LeagueDAO(hibernateBundle.getSessionFactory());
        final DraftDAO draftDAO = new DraftDAO(hibernateBundle.getSessionFactory());
        final CardListDAO cardListDAO = new CardListDAO(hibernateBundle.getSessionFactory());
        final CardDAO cardDAO = new CardDAO(hibernateBundle.getSessionFactory());

        environment.jersey().register(new PlayersResource(playerDAO));
        environment.jersey().register(new LeaguesResource(leagueDAO));
        environment.jersey().register(new DraftsResource(draftDAO));
        environment.jersey().register(new CardListsResource(cardListDAO));
        environment.jersey().register(new CardsResource(cardDAO));
    }
}
