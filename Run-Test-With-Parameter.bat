@echo off
cls

call mvn clean verify -DBROWSER=CHROME -DENVIRONMENT=TEST 

@pause

