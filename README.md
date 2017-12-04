# springBootTest
Just a test project to get warmed up in spring boot

This projects needs Maven, jdk 1.8, neo4j installed.

## Importing data to neo4j

Filene som ligger i csv-import-files må flyttes til neo4j's import mappe. 
På windows er dette: %AppData%\Roaming\neo4j-desktop\application\<din-database>\import

Importere csv fil:
USING PERIODIC COMMIT 10000
LOAD CSV WITH HEADERS FROM "file:///recipes.csv" AS row
Create (:Recipe {id: toInteger(row.id), name: row.name, description: row.description, time: toInteger(row.time) })

USING PERIODIC COMMIT 10000
LOAD CSV WITH HEADERS FROM "file:///ingredients.csv" AS row
Create (:Ingredient {id: toInteger(row.id), name: row.name })
