# helios

> Test Technique   
> Kotlin / Maven / PosgreSQL 

Ce test comporte deux exercices :
- Fizz Buzz
- Xspeeedlt

## Fizz Buzz
#### Fizz Buzz exercice 1
Pour les nombres *i* allant de 1 à *limit*, ce web service retourne une liste de string contenant :
- *i* si celui si n'est ni multiple de *int1*, ni de *int2*
- *str1* si *i* est uniquement multiple de *int1*
- *str2* si *i* est uniquement multiple de *int2*
- *str1str2* si *i* est multiple de *int1* et *int2*
```
curl --location --request GET 'localhost:8080/api/v1/fizzBuzz?int1=3&int2=5&limit=17&str1=Fizz&str2=Buzz'
```
#### Fizz Buzz exercice 2
Retourne la requête la plus appellée
```
curl --location --request GET 'localhost:8080/api/v1/fizzBuzz/statistics'
```

## Xspeeedlt

## Lancer l'application local

``` bash
# Démarrer la base de données : lancer le docker compose
docker compose - up 
ou 
depuis le projet dans votre IDE

# Démarrer le project Spring : 
Dans votre IDE, ajouter une nouvelle configuration SpringBoot
Choisir la class main : helios.HeliosApplication
Choisri le SDK : Java 12
Choisir active profiles : local
Via votre IDE lancer le projet, il démarrera sur le port localhost:8080
```


## Environnements :

- [Helios local](http://localhost:8080)

