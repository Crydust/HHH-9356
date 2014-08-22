@call mvn clean compile test -Phibernate > hibernate-4.3.5.Final.txt 2>&1
@call mvn clean compile test -Peclipselink > eclipselink-2.5.2.txt 2>&1
@call mvn clean compile test -Phibernate,hibernate-4.3.6.Final > hibernate-4.3.6.Final.txt 2>&1
@call mvn clean compile test -Phibernate,hibernate-4-snapshot > hibernate-4-snapshot.txt 2>&1
@call mvn clean compile test -Phibernate,hibernate-5-snapshot > hibernate-5-snapshot.txt 2>&1