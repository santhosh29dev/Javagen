@echo off
REM Install javagen from GitHub Releases (uses built-in curl.exe)
REM Usage: install-curl.bat

setlocal enabledelayedexpansion

set INSTALL_DIR=%USERPROFILE%\.javagen
set JAR_NAME=javagen.jar

echo Installing javagen...

if not exist "%INSTALL_DIR%" mkdir "%INSTALL_DIR%"

echo Downloading javagen.jar from GitHub Releases...
curl.exe -fsSL -o "%INSTALL_DIR%\%JAR_NAME%" "https://github.com/santhosh29dev/Javagen/releases/latest/download/javagen-1.0.0.jar"

if not exist "%INSTALL_DIR%\%JAR_NAME%" (
    echo ERROR: Download failed. Check internet connection.
    pause
    exit /b 1
)

echo Creating javagen.bat wrapper...
(
echo @echo off
echo java -jar "%%~dp0javagen.jar" %%*
) > "%INSTALL_DIR%\javagen.bat"

echo Adding to user PATH...
set "EXISTING_PATH="
for /f "tokens=2*" %%a in ('reg query "HKCU\Environment" /v Path 2^>nul ^| findstr /i "Path"') do set "EXISTING_PATH=%%b"

if not defined EXISTING_PATH (
    setx PATH "%INSTALL_DIR%" >nul
) else (
    echo !EXISTING_PATH! | findstr /i "%INSTALL_DIR%" >nul 2>&1
    if errorlevel 1 (
        setx PATH "!EXISTING_PATH!;%INSTALL_DIR%" >nul
    )
)

echo.
echo ========================================
echo  javagen installed successfully!
echo ========================================
echo.
echo  Restart your terminal, then run:
echo    javagen create web --name myproject
echo.
pause
