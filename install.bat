@echo off
REM Install javagen from GitHub Releases
REM Usage: Double-click this file, or run from CMD: install.bat

setlocal enabledelayedexpansion

set REPO=santhosh29dev/Javagen
set INSTALL_DIR=%USERPROFILE%\.javagen
set JAR_NAME=javagen.jar

echo Installing javagen...

REM Create install directory
if not exist "%INSTALL_DIR%" mkdir "%INSTALL_DIR%"

REM Download JAR using PowerShell (available on all modern Windows)
echo Downloading javagen.jar...
powershell -NoProfile -Command ^
    "$r = Invoke-RestMethod 'https://api.github.com/repos/%REPO%/releases/latest';" ^
    "$a = $r.assets | Where-Object { $_.name -like 'javagen*.jar' } | Select-Object -First 1;" ^
    "Invoke-WebRequest -Uri $a.browser_download_url -OutFile '%INSTALL_DIR%\%JAR_NAME%'"

if not exist "%INSTALL_DIR%\%JAR_NAME%" (
    echo ERROR: Download failed. Make sure you have internet access.
    pause
    exit /b 1
)

REM Create javagen.bat wrapper
(
echo @echo off
echo java -jar "%%~dp0javagen.jar" %%*
) > "%INSTALL_DIR%\javagen.bat"

REM Add to user PATH
echo Adding to PATH...
set "CURRENT_PATH="
for /f "tokens=2*" %%a in ('reg query "HKCU\Environment" /v Path 2^>nul ^| findstr /i "Path"') do set "CURRENT_PATH=%%b"

if not defined CURRENT_PATH (
    setx PATH "%INSTALL_DIR%"
) else (
    echo !CURRENT_PATH! | findstr /i "%INSTALL_DIR%" >nul 2>&1
    if errorlevel 1 (
        setx PATH "!CURRENT_PATH!;%INSTALL_DIR%"
    )
)

echo.
echo javagen installed!
echo Restart your terminal, then run: javagen create web --name myproject
echo.
pause
