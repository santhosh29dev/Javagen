# Install javagen from GitHub Releases (PowerShell)
# Usage: Invoke-WebRequest -Uri "https://github.com/santhosh29dev/Javagen/releases/latest/download/install.ps1" -OutFile "$env:TEMP\install.ps1"; & "$env:TEMP\install.ps1"

$ErrorActionPreference = "Stop"
$Repo = "santhosh29dev/Javagen"
$InstallDir = "$env:USERPROFILE\.javagen"
$JarName = "javagen.jar"

Write-Host "Installing javagen..." -ForegroundColor Cyan

# Get latest release
$Release = Invoke-RestMethod -Uri "https://api.github.com/repos/${Repo}/releases/latest"
$JarAsset = $Release.assets | Where-Object { $_.name -like "javagen*.jar" } | Select-Object -First 1

if (-not $JarAsset) {
    Write-Error "Could not find latest release JAR."
    exit 1
}

# Download
New-Item -ItemType Directory -Force -Path $InstallDir | Out-Null
$JarPath = Join-Path $InstallDir $JarName
Write-Host "Downloading $($JarAsset.name)..." -ForegroundColor Yellow
Invoke-WebRequest -Uri $JarAsset.browser_download_url -OutFile $JarPath

# Create javagen.bat wrapper
$BatPath = Join-Path $InstallDir "javagen.bat"
@"
@echo off
java -jar "%~dp0javagen.jar" %*
"@ | Set-Content -Path $BatPath -Encoding ASCII

# Add to user PATH if not already there
$CurrentPath = [Environment]::GetEnvironmentVariable("Path", "User")
if ($CurrentPath -notlike "*${InstallDir}*") {
    [Environment]::SetEnvironmentVariable("Path", "${CurrentPath};${InstallDir}", "User")
    $env:Path = "${env:Path};${InstallDir}"
    Write-Host "Added ${InstallDir} to user PATH." -ForegroundColor Green
}

Write-Host ""
Write-Host "javagen installed!" -ForegroundColor Green
Write-Host "Restart your terminal, then run: javagen create web --name myproject" -ForegroundColor Cyan
