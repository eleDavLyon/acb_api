Alerte sur Compte Bancaire (acb_api - POC pour tester Spring Boot + Spring Batch)
==

ACB est une application qui permet d'envoyer des mails d'alerte sur des comptes bancaireS.
Cette application intègre des fichiers au format CSV contenant des données 
à envoyer aux clients. C'est un cas pratique que nous allons utiliser tester Spring Boot + Spring Batch.

Spring Boot
-

Spring Boot est un framework de Spring qui permet de simplifier le démarrage des applications Web
avec une meilleure gestion des configurations (plus besoin des nombreux fichiers XML de configuration, etc...).
Il embarque aussi un serveur Tomcat et gère mieux les dépendances avec l'import des modules "starter".
Dans ce cas pratique, nous utilisons Spring Boot pour démarrer un programme Spring Batch de traitement d'un fichier CSV.

Architecture de l'application
-

L'application est décomposée en plusieurs couches que sont :
* le façade Web : elle expose un contrôleur qu'on utilisera pour déclencher le traitement Batch.
* le Batch : elle comporte un job de traitement des fichiers CSV.
* la couche métier : elle comporte un bean Service qui implémente l'envoi d'un mail avec Spring Mail.
* le versionning des scripts SQL.
* la base de données (PostgreSQL) : Les données sont stockées dans un SGBD PostgreSQL.

### Le façade Web #


Le façade Web est la classe JobLancherController. Elle expose uniquement un contrôleur REST.
Elle utilise un bean de lancement de job (jobLauncher).Pour construire ce sontrôleur, nous utilisons le framework
[Spring REST](https://projects.spring.io/spring-restdocs/ "link to Spring REST").
Spring REST fournit les annotations pour contruire les façades REST :
* @RestController : elle est présente dans tous les contrôleurs qui exposent des APIs.
* @RequestMapping : Elle permet de faire le mapping entre une URL d'API et la méthode chargée de traiter ladite URL.
* @RequestParam : cette annotation faire un mapping entre un paramètre de l'API et le paramètre de la méthode qui traite l'API.
* etc....

### Le traitement Batch #

Le traitement Batch est dans le package fr.davidson.acb.springbatch.
Il est composé de plusieurs steps. Les steps sont les étapes de traitement des fichiers CSV.
 1. AlerteCompteBancaireItemReader : lit les lignes du fichier et les convertit en Objet Java
 2. AlerteCompteBancaireItemProcessor :  traite chaque objet Java. Il fait appel au bean Service des mails pour pusher le mail correspondant à chaque ligne du fichier.
 3. AlerteCompteBancaireItemWriter : il stocke en BDD les données après l'envoi des mails.  Nous utilison du JdbcTeplate pour être au plus proche de la BDD.

En plus des steps, nous avons : 
 1. JobCompletionNotificationListener (un listener) : il est exécuter à la fin du job en cas de succès. Il renseigne sur le nombre d'alertes stockées en bdd.
 2. BatchAlerteCompteBancaireConfiguration : cette classe configure le batch avec l'enchainement des steps.
 La configuration se fait via les annotations Spring et non les fichiers de configuration XML.  

### Le traitement Métier #

Le traitement métier est dans le package fr.davidson.acb.business
Nous avons un seul service Spring qui envoit des mails (Spring Mail + Mustache)
Le contrat d'interface et l'implémentation sont dans le package. 

### Le versionning des scripts SQL #

Le versionning des scripts SQL est assuré par le framework [flyway](https://flywaydb.org "link to flyway").
Tous les scripts SQL sont dans le module acb-scripts, dans le sous-répertoire des ressources.
Ce module fait partir des dépendances du module acb_server. Au démarrage du serveur Tomcat embarqué, les scripts SQL sont exécutés
et la base de données est mise à jour automatiquement d'éventuels nouveaux scripts SQL.
