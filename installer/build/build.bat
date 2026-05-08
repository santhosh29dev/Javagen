@echo off
REM ============================================================
REM  javagen Installer Build Script
REM  Downloads latest JAR and builds the NSIS installer
REM ============================================================

setlocal

set REPO=santhosh29dev/Javagen
set VERSION=1.0.3
set NSIS="C:\Program Files (x86)\NSIS\makensis.exe"

echo.
echo  Building javagen installer...
echo.

REM Download latest JAR
echo  [..] Downloading javagen.jar from GitHub Releases...
curl.exe -fsSL -o javagen.jar "https://github.com/%REPO%/releases/download/v%VERSION%/javagen-1.0.0.jar"

if not exist javagen.jar (
    echo  [ERROR] Download failed!
    pause
    exit /b 1
)

echo  [OK] Downloaded javagen.jar

REM Build installer
echo  [..] Building installer with NSIS...
%NSIS% javagen-setup.nsi

if not exist javagen-setup.exe (
    echo  [ERROR] Build failed!
    pause
    exit /b 1
)

echo  [OK] Installer built: javagen-setup.exe
echo.
echo  Done! Copy javagen-setup.exe to the installer folder.
echo.

pause
