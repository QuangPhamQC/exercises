@echo off
cls

call mvn clean verify -DBROWSER=CHROME -DENVIRONMENT=DEV -Dcucumber.options="--tags '@ForgotPassword or @Users or @ChangePassword'"

@pause

