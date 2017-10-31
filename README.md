# MTG Draft Leagues

This will be an API for managing MTG Draft Leagues. The data for leagues, drafts, players, matches, and elo will all be stored in a database & accessed via the api. 

# Dropwizard App

To build:

`mvn package`

To run:

`java -jar target/draft-league-api-1.0.0-SNAPSHOT.jar server sql-hibernate.config.yml`