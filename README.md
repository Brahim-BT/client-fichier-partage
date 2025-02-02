# README - Client du Système de Partage de Fichiers

## Introduction
Ce projet est un client pour le serveur de partage de fichiers qui utilise l'API Google Drive. Il permet aux utilisateurs de se connecter au serveur pour télécharger, uploader, lister et supprimer des fichiers stockés sur Google Drive.

## Prérequis
Avant de commencer, assurez-vous d'avoir les éléments suivants :
- Java JDK (version 8 ou supérieure)
- Maven pour la gestion des dépendances
- L'adresse IP ou le nom d'hôte du serveur où le serveur de partage de fichiers est en cours d'exécution.

## Installation

### Étapes d'Installation
1. **Clonez le projet :**
   ```bash
   git clone <URL_DU_PROJET>
   cd client-fichier-partage
   ```
2. **Compiler le projet :**
   Exécutez les commandes suivantes pour compiler le projet :
   ```bash
   mvn clean install
   ```

## Exécution du Client

### Lancer le client :
Naviguez vers le répertoire du client et exécutez la classe Main :
```bash
java -cp target/client-fichier-partage-1.0-SNAPSHOT.jar Main
```

### Se connecter au serveur :
Une fois le client lancé, il tentera de se connecter au serveur RMI. Assurez-vous que le serveur est en cours d'exécution sur le port 1099.

## Utilisation

### Menu des Options
Après la connexion réussie au serveur, un menu apparaîtra avec les options suivantes :

1. **Lister les fichiers :**
   Affiche la liste des fichiers disponibles sur Google Drive.
2. **Uploader un fichier :**
   Permet de spécifier un fichier local à uploader sur le serveur.
3. **Télécharger un fichier :**
   Affiche la liste des fichiers disponibles et permet de télécharger un fichier en spécifiant son numéro.
4. **Supprimer un fichier :**
   Affiche la liste des fichiers disponibles et permet de supprimer un fichier en spécifiant son numéro.
5. **Quitter :**
   Ferme l'application.

### Instructions d'Utilisation
- Pour lister les fichiers, sélectionnez l'option 1 dans le menu.
- Pour uploader un fichier, sélectionnez l'option 2 et entrez le chemin local du fichier que vous souhaitez uploader.
- Pour télécharger un fichier, sélectionnez l'option 3, choisissez le fichier dans la liste et spécifiez le chemin local où vous souhaitez le sauvegarder.
- Pour supprimer un fichier, sélectionnez l'option 4, choisissez le fichier que vous souhaitez supprimer, puis confirmez l'opération.

## Dépannage

### Problèmes de connexion au serveur :
Assurez-vous que le serveur est en cours d'exécution et accessible depuis le client. Vérifiez également l'adresse IP et le numéro de port.

### Erreurs lors de l'exécution :
Vérifiez que vous utilisez la version correcte de Java JDK et que Maven est installé.
