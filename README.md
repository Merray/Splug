# BotVouivrarium
Bot pour le serveur Vouivrarium

# Run with Docker

First, prepare your persistent host files dirs:

```
sudo mkdir -p /opt/splug/logs/
```

Set up the `application.properties` config file, replacing `<DISCORD_API_TOKEN_KEY>`:

```
cat << EOF > /opt/splug/application.properties
# Token
app.token=<DISCORD_API_TOKEN_KEY>
# Upsert
app.update.commands=false
# Database
db.url=jdbc:postgresql://localhost:5432/vouivrariumdb
db.user=vouivrariumdb
db.password=vouivrariumdb
EOF
```

Then, build the Docker image:

```
docker build -t splug .
```

Finally, run the container (add the `-d` option for a detached service):

```
sudo docker-compose up
```
