# Installez Docker sur votre système. Vous pouvez le télécharger depuis docker.com.
Une fois l’installation terminée, vérifiez la version de Docker pour vous assurer que tout fonctionne correctement en exécutant 
# docker --version 
dans votre terminal.

# Création d’un Dockerfile: Étapes Essentielles
La création d’un Dockerfile suit généralement ces étapes clés:

#### FROM: Spécifiez l’image de base à utiliser.
# RUN: Ajoute des commandes qui seront exécutées dans votre conteneur.
# COPY ou ADD: Permet de copier des fichiers de votre système de fichiers local vers l’image
# CMD: Indique la commande à exécuter lorsque le conteneur démarre.
Ces commandes forment la base de votre Dockerfile, mais il y a beaucoup plus à explorer pour optimiser et sécuriser vos images Docker.



Ici on utilise une base de données mysql et spring security pour l'authentification 

Démarrer les services dans un fichier docker-compose.yml
docker-compose up -d
Arrêter et supprimer les services et leurs volumes

docker-compose down -v
