============================================
  javagen Installer
  Java Ecosystem Scaffolding CLI
============================================

INSTALL
-------
Double-click: install-javagen.bat

Or from CMD:
  install-javagen.bat

This will:
  1. Check Java 21+ is installed
  2. Download javagen.jar from GitHub Releases
  3. Install to: %USERPROFILE%\.javagen\
  4. Add to your user PATH
  5. Verify installation

After install, restart your terminal and run:
  javagen --help
  javagen create web --name myproject

UNINSTALL
---------
Double-click: uninstall-javagen.bat

MANUAL DOWNLOAD
---------------
1. Download javagen-1.0.0.jar from:
   https://github.com/santhosh29dev/Javagen/releases/latest

2. Place it in a folder (e.g. C:\tools\javagen\)

3. Create javagen.bat in the same folder:
   @echo off
   java -jar "%~dp0javagen.jar" %*

4. Add that folder to your system PATH

REQUIREMENTS
------------
- Java 21 or later
- Windows 10/11

============================================
  https://github.com/santhosh29dev/Javagen
============================================
