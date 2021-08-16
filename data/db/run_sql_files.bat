@echo off

::==========================================================
:: Main.
::==========================================================
:main
	:: Verify the existence of the mysql command.
	mysql --version
	if %errorlevel% equ 9009 echo [ERROR] Add MYSQL_HOME/bin to the environment variable PATH. && pause && exit /b

	setlocal
	cd %~dp0

	:: Verify the existence of application.properties.
	set PROPERTY_FILE=..\..\src\main\resources\application.properties
	if not exist %PROPERTY_FILE% echo application.properties not found. && pause && exit /b

	:: Get database connection parameters from application.properties.
	call :get_property %PROPERTY_FILE% "jdbc.host" HOST
	call :get_property %PROPERTY_FILE% "jdbc.database" DATABASE
	call :get_property %PROPERTY_FILE% "jdbc.username" USER
	call :get_property %PROPERTY_FILE% "jdbc.password" PASS

	:: Find the SQL file and run it using the mysql command.
	for /R %%f in (*.sql) do (
		echo %%f
		mysql -u %USER% -p%PASS% -h %HOST% %DATABASE% <%%f
	)
	pause
	endlocal
	exit /b

::==========================================================
:: Subroutine to get parameters from property file.
:: @param 1 property file path.
:: @param 2 property name.
:: @param 3 variable name.
::==========================================================
:get_property
	setlocal
	set PROPERTY_FILE=%~1
	set PROPERTY_NAME=%~2

	:: Get parameters from property file.
	:: - Get the line from the properties file whose beginning of the line matches the parameter name.
	:: - Split with "=" to get the second parameter.
	:: - Replace spaces with empty string.
	for /f "usebackq tokens=2 delims==" %%A in (`findstr /B %PROPERTY_NAME% %PROPERTY_FILE%`) do set PROPERTY_VALUE=%%A
	set PROPERTY_VALUE=%PROPERTY_VALUE: =%

	:: Declare the global variable with the third argument as the variable name and set the value.
	endlocal && set %3=%PROPERTY_VALUE%
	exit /b
