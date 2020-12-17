@del TestResults.txt
@del .class
@rmdir /Q /S docs

@mkdir docs
@javadoc -d docs Birthday_solver.java
@javac BirthdaySolver.java

@echo Creating TestResults file...
echo Test Results >> TestResults.txt

@echo Testing strings...
echo Running Birthday_solver with heeheehee and hoohoohoo as inputs... >> TestResults.txt
java Birthday_solver heeheehee hoohoohoo >> TestResults.txt
echo . >> TestResults.txt

@echo Testing very large values...
echo Running Birthday_solver with 500 and 100000000 as inputs... >> TestResults.txt
java Birthday_solver 500 100000000 >> TestResults.txt
echo . >> TestResults.txt

@echo Testing different values...
echo Running Birthday_solver with 2 and 100... >> TestResults.txt
java Birthday_solver 2 100 >> TestResults.txt
echo Running Birthday_solver with 2 and 1000... >> TestResults.txt
java Birthday_solver 2 1000 >> TestResults.txt
echo Running Birthday_solver with 2 and 100000... >> TestResults.txt
java Birthday_solver 2 10000 >> TestResults.txt
echo Running Birthday_solver with 2 and 5000000... >> TestResults.txt
java Birthday_solver 2 5000000 >> TestResults.txt
echo . >> TestResults.txt

echo Running Birthday_solver with 10 and 100... >> TestResults.txt
java Birthday_solver 10 100 >> TestResults.txt
echo Running Birthday_solver with 10 and 1000... >> TestResults.txt
java Birthday_solver 10 1000 >> TestResults.txt
echo Running Birthday_solver with 10 and 100000... >> TestResults.txt
java Birthday_solver 10 10000 >> TestResults.txt
echo Running Birthday_solver with 10 and 5000000... >> TestResults.txt
java Birthday_solver 10 5000000 >> TestResults.txt
echo . >> TestResults.txt

echo Running Birthday_solver with 65 and 100... >> TestResults.txt
java Birthday_solver 65 100 >> TestResults.txt
echo Running Birthday_solver with 30 and 1000... >> TestResults.txt
java Birthday_solver 65 1000 >> TestResults.txt
echo Running Birthday_solver with 30 and 100000... >> TestResults.txt
java Birthday_solver 65 100000 >> TestResults.txt
echo Running Birthday_solver with 30 and 5000000... >> TestResults.txt
java Birthday_solver 65 5000000 >> TestResults.txt
echo . >> TestResults.txt

echo Running Birthday_solver with 81 and 100... >> TestResults.txt
java Birthday_solver 81 100 >> TestResults.txt
echo Running Birthday_solver with 81 and 1000... >> TestResults.txt
java Birthday_solver 81 1000 >> TestResults.txt
echo Running Birthday_solver with 81 and 100000... >> TestResults.txt
java Birthday_solver 81 100000 >> TestResults.txt
echo Running Birthday_solver with 81 and 5000000... >> TestResults.txt
java Birthday_solver 81 5000000 >> TestResults.txt
echo . >> TestResults.txt

echo Running Birthday_solver with 111 and 100... >> TestResults.txt
java Birthday_solver 111 100 >> TestResults.txt
echo Running Birthday_solver with 111 and 1000... >> TestResults.txt
java Birthday_solver 111 1000 >> TestResults.txt
echo Running Birthday_solver with 111 and 100000... >> TestResults.txt
java Birthday_solver 111 100000 >> TestResults.txt
echo Running Birthday_solver with 111 and 5000000... >> TestResults.txt
java Birthday_solver 111 5000000 >> TestResults.txt
echo . >> TestResults.txt
