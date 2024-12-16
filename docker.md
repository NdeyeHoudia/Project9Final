##### Démarrer les services dans un fichier docker-compose.yml
    utilisation des arguments -d et -p lors du démarrage d'un conteneur ;
    docker-compose up -d
    Arrêter et supprimer les services et leurs volumes
    
    docker-compose down -v
    
    démarrage d'un conteneur avec un docker run ;
    
    récupération d'une image depuis une registry avec la commande docker pull ;
    
    nettoyage du système avec  docker system prune

##### Pour créer une image Docker, vous savez utiliser les instructions suivantes :

        FROM qui vous permet de définir l'image source ;
        
        RUN qui vous permet d’exécuter des commandes dans votre conteneur ;
        
        ADD qui vous permet d'ajouter des fichiers dans votre conteneur ;
        
        WORKDIR qui vous permet de définir votre répertoire de travail ;
        
        EXPOSE qui permet de définir les ports d'écoute par défaut ;
        
        VOLUME qui permet de définir les volumes utilisables ;
        
        CMD qui permet de définir la commande par défaut lors de l’exécution de vos


##### les commandes principales pour utiliser une stack Docker Compose. Voici les commandes les plus importantes :
        docker-compose up -d vous permettra de démarrer l'ensemble des conteneurs en arrière-plan ;
        
        docker-compose ps vous permettra de voir le statut de l'ensemble de votre stack ;
        
        docker-compose logs -f --tail 5 vous permettra d'afficher les logs de votre stack ;
        
        docker-compose stop vous permettra d'arrêter l'ensemble des services d'une stack ;
        
        docker-compose down vous permettra de détruire l'ensemble des ressources d'une stack ;

docker-compose config vous permettra de valider la syntaxe de votre fichier docker-compose.yml  .
