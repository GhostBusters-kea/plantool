<!-- 
Author: Jonas Munk
-->

# PlanTool


![GitHub repo size](https://img.shields.io/github/repo-size/jmunkc/plantool)
![GitHub contributors](https://img.shields.io/github/contributors/jmunkc/plantool)
![GitHub stars](https://img.shields.io/github/stars/jmunkc/plantool)
![GitHub forks](https://img.shields.io/github/forks/jmunkc/plantool)

Plantool is a web application tool anyone who wishes to plan a project of any kind, can use to compartmentalize into 
smaller sub-projects and task to make it more manageable. It also provides a rough estimate of the workload pr. day.

This project was developed using the Intellij IDE with Spring Initializer and MySQL Workbench
in java 11 runtime environment and hosted with ClearDB on Heroku at https://plantoool.herokuapp.com/ 

## Prerequisites
#### Coding Languages

* `Java SE 11 JDK (LTS)`
* `MySQL 8.0`
* `HTML 5`
* `CSS 2.1`

#### Dependencies 

* `Spring Boot 2.6.0`
* `Spring Web`
* `Thymeleaf 3.0.13`
* `Maven 4.0.0`
* `JDBC API 8.0.26`
* `Bootstrap 5`

## Running Plantool

To run Plantool, clone this repository into Intellij and run the [`plantool_db.sql`](https://github.com/jmunkc/plantool/blob/master/plantool_db.sql) script in MySQL Workbench. 
Make sure to specify environmental variables values: `url`, `username` and `password` in Intellij

```
Run > Edit Configureations..
```
![img_3.png](img_3.png)

![img_4.png](img_4.png)

Make sure they match your variables in your database

![img_5.png](img_5.png)

Then simply run `src/main/java/com/example/plantool/PlantoolApplication.java`

Go on your browser and type `http://localhost:8080/` and enjoy planning :)

## Hosting Plantool on Heroku with ClearDB
To host your own version of Plantool on Heroku, first sign up at https://signup.heroku.com/

Then click on `New > create new app` and name your app whatever you want (Unless the name is already taken).

Chose your region and click `create app`. Under `Deploy > Deployment method` choose GitHub then `Connect to GitHub`
by following the steps. Choose your account and repository and press connect.
![img_6.png](img_6.png)

Go to `Resources` and search for ClearDB in the `Add-ons` search bar. Pres it and add the Ignite-Free version.

Go to `Settings` and pres `Reveal Config Vars`

The `CLEARDB_DATABASE_URL` KEY will give you somthing like
```
mysql://bd95xxxxxxac0a:8fxxxx97@eu-cdbr-west-02.cleardb.net/heroku_4a8208a40a1afbc?reconnect=true
```
Where

* username = `bd95xxxxxxac0a`
* password = `8fxxxx97`
* url = `eu-cdbr-west-02.cleardb.net/`
* database name = `heroku_4a8208a40a1afbc`

Open MySQL Workbench. Add a new connection and input your variables
![img_7.png](img_7.png)
The database name will be set automatically.

Open a new SQL queries tab and run the [`plantool_db.sql`](https://github.com/jmunkc/plantool/blob/master/plantool_db.sql)

Then go back to `Heruko > Settings > Reveal Config Vars` and paste in your variables.
![img_8.png](img_8.png)

Make sure that the url is appended with the prefix `jdbc:` the suffix with the database name and `&autoReconnect=true`
It should look somthing like this
```
jdbc:mysql://eu-cdbr-west-02.cleardb.net/heroku_4a8208a40a1afbc?reconnect=true&autoReconnect=true
```
Finally, simply press the `Open app` button in the top right corner and enjoy planning online :)
## Contributing to Plantool

To contribute to Plantool, follow these steps:

1. Fork this repository.
2. Create a branch: `git checkout -b <branch_name>`.
3. Make your changes and commit them: `git commit -m '<commit_message>'`
4. Push to the original branch: `git push origin <project_name>/<location>`
5. Create the pull request.

Alternatively see the GitHub documentation on [creating a pull request](https://help.github.com/en/github/collaborating-with-issues-and-pull-requests/creating-a-pull-request).

## Contributors

The following people have contributed to this project:
#### Main Authors
* [@jmunkc](https://github.com/jmunkc)
* [@KevinFHansen](https://github.com/KevinFHansen) 
* [@mrbrogaardkaiser](https://github.com/mrbrogaardkaiser) 
* [@MichaelDyvad](https://github.com/MichaelDyvad) 

## Contact

Contact to the main authors can be done at following e-mails:
* jona04g8@kea.stud.dk
* kevi4777@kea.stud.dk
* lars10m3@kea.stud.dk
* mich4r61@kea.stud.dk