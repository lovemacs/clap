clap
====

a clojure application

1.win-r cmd
mvn archetype:create -DgroupId=com.example.clap -DartifactId=clap

cd clap/

git init

git add .

git commit -am "Project created: mvn archetype:create -DgroupId=com.example.clap -DartifactId=clap"

mvn package

java -cp target/clap-1.0-SNAPSHOT.jar com.example.clap.App
输出:
Java World


2.

mvn package appassembler:assemble

C:\Users\liango\clap>.\target\appassembler\bin\app.bat
输出:
Java World!
Hello World!
Java main called clojure function with args:

