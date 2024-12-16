## Project9Final
### Développez une solution en microservices pour votre client

  ##### Structurer l'appilcation en cinq microservices qui interagissent avec un service gateway
    ---   2 microservices backend avec une base de données mysql et une base de données mongodb
    ---   1 microservices frontend
    ---   1 microservices pour la gestion des risque du patient
    ---   1 microservices pour le gateway
    ---   faire du green code
    ---   créer des images docker 

    # Installez Docker sur votre système. Vous pouvez le télécharger depuis docker.com.
Dans mon cas, j'ai installer docker sur mon pc
## Docker Desktop pour Windows - x86_64 
(https://docs.docker.com/desktop/setup/install/windows-install/)
Une fois l’installation terminée, vérifiez la version de Docker pour vous assurer que tout fonctionne correctement en exécutant 
## docker --version 
dans votre terminal.

## Création d’un Dockerfile: Étapes Essentielles
La création d’un Dockerfile suit généralement ces étapes clés:

Créez un fichier Docker et suivez les instructions pour créer votre image Docker
Exécutez la commande docker build qui construira une image docker

Maintenant que l’image Docker est prête à être utilisée
##### utilisez la commande docker run pour créer des conteneurs
    FROM: Spécifiez l’image de base à utiliser.
    RUN: Ajoute des commandes qui seront exécutées dans votre conteneur.
    COPY ou ADD: Permet de copier des fichiers de votre système de fichiers local vers l’image
    CMD: Indique la commande à exécuter lorsque le conteneur démarre.

Ces commandes forment la base de votre Dockerfile, mais il y a beaucoup plus à explorer pour optimiser et sécuriser vos images Docker.

#### Exécutez la commande docker build qui construira une image docker
Maintenant que l’image Docker est prête à être utilisée
On utilise la commande
#### docker run pour créer des conteneurs
Dans ce projet, on a besoin de deux image de base de données (MYSQL et MONGODB)
    image: mongo:latest
    image: mysql:lts
##### Exemple de docker-compose avec l'image mongo
    version: "3"
    services:
     mongo:
       image: mongo:latest
      environment:
        MONGO_INITDB_ROOT_USERNAME: ?
        MONGO_INITDB_ROOT_PASSWORD: ?
    volumes:
      - /data/db:/data/db
    ports:
      - 27017:27017


Démarrer les services dans un fichier docker-compose.yml
docker-compose up -d
Arrêter et supprimer les services et leurs volumes

docker-compose down -v

Notre application est accessible via le port 8761. Vérifions cela en tapant http://localhost:8082/ sur un navigateur.


