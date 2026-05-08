;============================================================
;  javagen Installer - NSIS Setup Script
;  Bundles javagen.jar into a professional Windows installer
;============================================================

!include "MUI2.nsh"
!include "LogicLib.nsh"

;------------------------------------------------------------
;  General
;------------------------------------------------------------
Name "javagen"
OutFile "javagen-setup.exe"
InstallDir "$PROGRAMFILES64\javagen"
InstallDirRegKey HKLM "Software\javagen" "InstallDir"
RequestExecutionLevel admin

;------------------------------------------------------------
;  Version Info (shown in file properties)
;------------------------------------------------------------
VIProductVersion "1.0.0.0"
VIAddVersionKey "ProductName" "javagen"
VIAddVersionKey "CompanyName" "santhosh29dev"
VIAddVersionKey "FileDescription" "Java Ecosystem Scaffolding CLI - Installer"
VIAddVersionKey "FileVersion" "1.0.0"
VIAddVersionKey "ProductVersion" "1.0.0"
VIAddVersionKey "LegalCopyright" "MIT License"

;------------------------------------------------------------
;  Interface Settings
;------------------------------------------------------------
!define MUI_ABORTWARNING
!define MUI_ICON "${NSISDIR}\Contrib\Graphics\Icons\modern-install.ico"
!define MUI_UNICON "${NSISDIR}\Contrib\Graphics\Icons\modern-uninstall.ico"

;------------------------------------------------------------
;  Pages
;------------------------------------------------------------
!insertmacro MUI_PAGE_WELCOME
!insertmacro MUI_PAGE_LICENSE "LICENSE.txt"
!insertmacro MUI_PAGE_DIRECTORY
!insertmacro MUI_PAGE_INSTFILES
!insertmacro MUI_PAGE_FINISH

!insertmacro MUI_UNPAGE_CONFIRM
!insertmacro MUI_UNPAGE_INSTFILES

;------------------------------------------------------------
;  Language
;------------------------------------------------------------
!insertmacro MUI_LANGUAGE "English"

;------------------------------------------------------------
;  Installer Section
;------------------------------------------------------------
Section "Install" SecInstall

    SetOutPath "$INSTDIR"

    ; Copy bundled JAR file
    File "javagen.jar"

    ; Create javagen.bat wrapper
    FileOpen $0 "$INSTDIR\javagen.bat" w
    FileWrite $0 "@echo off$\r$\n"
    FileWrite $0 "java -jar $\"%~dp0javagen.jar$\" %*$\r$\n"
    FileClose $0

    ; Create uninstaller
    WriteUninstaller "$INSTDIR\uninstall.exe"

    ; Add to system PATH
    ReadRegStr $0 HKLM "SYSTEM\CurrentControlSet\Control\Session Manager\Environment" "Path"
    WriteRegExpandStr HKLM "SYSTEM\CurrentControlSet\Control\Session Manager\Environment" "Path" "$0;$INSTDIR"

    ; Broadcast environment change so new terminals pick it up
    SendMessage ${HWND_BROADCAST} ${WM_WININICHANGE} 0 "STR:Environment" /TIMEOUT=5000

    ; Registry entries for Add/Remove Programs
    WriteRegStr HKLM "Software\javagen" "InstallDir" "$INSTDIR"
    WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\javagen" \
        "DisplayName" "javagen"
    WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\javagen" \
        "UninstallString" "$\"$INSTDIR\uninstall.exe$\""
    WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\javagen" \
        "DisplayIcon" "$INSTDIR\javagen.bat,0"
    WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\javagen" \
        "Publisher" "santhosh29dev"
    WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\javagen" \
        "URLInfoAbout" "https://github.com/santhosh29dev/Javagen"
    WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\javagen" \
        "NoModify" 1
    WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\javagen" \
        "NoRepair" 1

SectionEnd

;------------------------------------------------------------
;  Uninstaller Section
;------------------------------------------------------------
Section "Uninstall"

    ; Remove files
    Delete "$INSTDIR\javagen.jar"
    Delete "$INSTDIR\javagen.bat"
    Delete "$INSTDIR\uninstall.exe"
    RMDir "$INSTDIR"

    ; Remove from system PATH
    ReadRegStr $0 HKLM "SYSTEM\CurrentControlSet\Control\Session Manager\Environment" "Path"
    StrLen $1 "$INSTDIR"
    StrLen $2 "$0"
    IntOp $3 $2 - $1
    StrCpy $4 "$0" $3
    WriteRegExpandStr HKLM "SYSTEM\CurrentControlSet\Control\Session Manager\Environment" "Path" "$4"

    ; Broadcast environment change
    SendMessage ${HWND_BROADCAST} ${WM_WININICHANGE} 0 "STR:Environment" /TIMEOUT=5000

    ; Remove registry entries
    DeleteRegKey HKLM "Software\javagen"
    DeleteRegKey HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\javagen"

SectionEnd

;------------------------------------------------------------
;  Check Java on init
;------------------------------------------------------------
Function .onInit

    nsExec::ExecToStack 'java -version'
    Pop $0
    ${If} $0 != 0
        MessageBox MB_OKCANCEL|MB_ICONEXCLAMATION \
            "Java 21 or later is required but was not found.$\r$\n$\r$\n\
            Please install Java first from:$\r$\n\
            https://adoptium.net/" \
            IDOK +2
            Abort
        ExecShell "open" "https://adoptium.net/"
        Abort
    ${EndIf}

FunctionEnd
