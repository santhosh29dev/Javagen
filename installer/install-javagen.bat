@echo off
REM ============================================================
REM  javagen Installer v1.0.2
REM  Downloads javagen from GitHub and installs it globally
REM  Double-click to run, or run from CMD
REM ============================================================

setlocal enabledelayedexpansion

title javagen Installer

set REPO=santhosh29dev/Javagen
set VERSION=1.0.2
set INSTALL_DIR=%USERPROFILE%\.javagen
set JAR_NAME=javagen.jar

echo.
echo  ============================================
echo    javagen Installer
echo    Java Ecosystem Scaffolding CLI
echo  ============================================
echo.

REM Check for Java
java -version >nul 2>&1
if errorlevel 1 (
    echo  [ERROR] Java is not installed or not in PATH.
    echo.
    echo  Please install Java 21 or later:
    echo    https://adoptium.net/
    echo.
    pause
    exit /b 1
)

echo  [OK] Java detected
for /f "tokens=3" %%a in ('java -version 2^>^&1 ^| findstr /i "version"') do echo         Version: %%a

REM Create install directory
if not exist "%INSTALL_DIR%" (
    mkdir "%INSTALL_DIR%"
    echo  [OK] Created install directory: %INSTALL_DIR%
) else (
    echo  [OK] Install directory exists: %INSTALL_DIR%
)

REM Download JAR
echo.
echo  [..] Downloading javagen v%VERSION% from GitHub Releases...
echo       https://github.com/%REPO%/releases/download/v%VERSION%/%JAR_NAME%

curl.exe -fsSL -o "%INSTALL_DIR%\%JAR_NAME%" "https://github.com/%REPO%/releases/download/v%VERSION%/%JAR_NAME%"

if not exist "%INSTALL_DIR%\%JAR_NAME%" (
    echo.
    echo  [ERROR] Download failed!
    echo.
    echo  Check your internet connection, or download manually:
    echo    https://github.com/%REPO%/releases/latest
    echo.
    pause
    exit /b 1
)

echo  [OK] Downloaded %JAR_NAME%

REM Create javagen.bat wrapper
echo  [..] Creating javagen.bat wrapper...
(
echo @echo off
echo java -jar "%%~dp0javagen.jar" %%*
) > "%INSTALL_DIR%\javagen.bat"

echo  [OK] Created javagen.bat

REM Add to user PATH
echo  [..] Adding to user PATH...
set "EXISTING_PATH="
for /f "tokens=2*" %%a in ('reg query "HKCU\Environment" /v Path 2^>nul ^| findstr /i "Path"') do set "EXISTING_PATH=%%b"

if not defined EXISTING_PATH (
    setx PATH "%INSTALL_DIR%" >nul 2>&1
    echo  [OK] Added to PATH: %INSTALL_DIR%
) else (
    echo !EXISTING_PATH! | findstr /i "%INSTALL_DIR%" >nul 2>&1
    if errorlevel 1 (
        setx PATH "!EXISTING_PATH!;%INSTALL_DIR%" >nul 2>&1
        echo  [OK] Added to PATH: %INSTALL_DIR%
    ) else (
        echo  [OK] Already in PATH
    )
)

REM Verify installation
echo.
echo  [..] Verifying installation...
set "TEST_OUTPUT="
for /f "delims=" %%a in ('java -jar "%INSTALL_DIR%\%JAR_NAME%" --version 2^>nul') do set "TEST_OUTPUT=%%a"

if defined TEST_OUTPUT (
    echo  [OK] javagen v!TEST_OUTPUT! is working!
) else (
    echo  [WARN] Could not verify. Try restarting your terminal.
)

REM Done
echo.
echo  ============================================
echo    Installation Complete!
echo  ============================================
echo.
echo  javagen is installed at:
echo    %INSTALL_DIR%
echo.
echo  Restart your terminal, then run:
echo.
echo    javagen --help
echo    javagen create web --name myproject
echo    javagen create desk --name mydesktop
echo    javagen create app --name mymobile
echo.
echo  ============================================
echo.

echo.
echo  Press any key to exit...
pause >nul
