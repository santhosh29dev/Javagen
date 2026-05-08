@echo off
REM ============================================================
REM  javagen Uninstaller
REM  Removes javagen from your system
REM ============================================================

setlocal enabledelayedexpansion

title javagen Uninstaller

set INSTALL_DIR=%USERPROFILE%\.javagen

echo.
echo  ============================================
echo    javagen Uninstaller
echo  ============================================
echo.

if not exist "%INSTALL_DIR%" (
    echo  [INFO] javagen is not installed at:
    echo         %INSTALL_DIR%
    echo.
    pause
    exit /b 0
)

echo  Found javagen at: %INSTALL_DIR%
echo.

choice /c YN /m "Are you sure you want to uninstall javagen?"
if errorlevel 2 (
    echo  Cancelled.
    pause
    exit /b 0
)

REM Remove from PATH
echo  [..] Removing from PATH...
set "EXISTING_PATH="
for /f "tokens=2*" %%a in ('reg query "HKCU\Environment" /v Path 2^>nul ^| findstr /i "Path"') do set "EXISTING_PATH=%%b"

if defined EXISTING_PATH (
    set "NEW_PATH=!EXISTING_PATH:%INSTALL_DIR%=!"
    set "NEW_PATH=!NEW_PATH:;;=;!"
    if "!NEW_PATH!"=="" (
        reg delete "HKCU\Environment" /v Path /f >nul 2>&1
    ) else (
        setx PATH "!NEW_PATH!" >nul 2>&1
    )
    echo  [OK] Removed from PATH
)

REM Delete install directory
echo  [..] Deleting install directory...
rmdir /s /q "%INSTALL_DIR%" 2>nul
if exist "%INSTALL_DIR%" (
    echo  [WARN] Could not fully delete: %INSTALL_DIR%
    echo         You may need to delete it manually.
) else (
    echo  [OK] Deleted: %INSTALL_DIR%
)

echo.
echo  ============================================
echo    javagen has been uninstalled.
echo  ============================================
echo.
pause
