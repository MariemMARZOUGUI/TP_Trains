# TP_Trains

Ce code représente une simulation de trains circulant sur une voie ferrée constituée de différentes sections et stations. 

## Diagramme de classes final : 
![Diagramme.png](Diagramme.png)

Voici une brève explication de chaque composant du code.


## Train (Train.java)##
La classe Train représente un train avec un nom et une position sur la voie ferrée. Un train peut se déplacer vers le prochain élément de la voie ferrée. La simulation est gérée dans un thread à l'aide de l'interface Runnable.

## Élément (Element.java)##
La classe abstraite Element représente les différents types d'éléments sur la voie ferrée, tels que les stations et les sections. Elle gère la logique du déplacement des trains entre les éléments.

## Section (Section.java) ##
La classe Section, une sous-classe concrète de la classe Element, représente une section de voie ferrée. Elle garde la trace de l'occupation de la section par les trains et du nombre de trains présents.

## Station (Station.java)##
La classe Station, une sous-classe concrète de la classe Element,  représente une station sur la voie ferrée. Elle a une capacité maximale de trains qu'elle peut accueillir et gère l'entrée et la sortie des trains.

## Position (Position.java)##
La classe Position représente la position d'un train sur la voie ferrée, caractérisée par un élément et une direction (de gauche à droite ou de droite à gauche). Elle permet au train de se déplacer vers le prochain élément.

## Direction (Direction.java) ##
L'énumération Direction représente la direction d'un train, soit de gauche à droite (LR) soit de droite à gauche (RL).

## Voie ferrée (Railway.java)##
La classe Railway représente le circuit complet de la voie ferrée, composé d'éléments tels que des stations et des sections. Elle gère également le suivi du nombre de trains dans chaque direction.

## BadPositionForTrainException (BadPositionForTrainException.java) ##
Cette classe est une exception personnalisée qui est lancée lorsqu'un train est mal positionné sur la voie ferrée.