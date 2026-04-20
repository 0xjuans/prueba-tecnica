# Carga variables desde .env en el proceso actual y ejecuta Spring Boot.
# Uso (desde la raíz del repo):  pwsh -File scripts/run-local.ps1

$ErrorActionPreference = "Stop"
# Raíz del repo = carpeta padre de scripts/
$root = Split-Path $PSScriptRoot -Parent
if (-not (Test-Path (Join-Path $root ".env"))) {
    Write-Host "No existe .env en la raíz del proyecto. Copia .env.example a .env y ajusta los valores." -ForegroundColor Yellow
    exit 1
}

Set-Location $root

Get-Content (Join-Path $root ".env") | ForEach-Object {
    $line = $_.Trim()
    if ($line -eq "" -or $line.StartsWith("#")) { return }
    $idx = $line.IndexOf("=")
    if ($idx -lt 1) { return }
    $key = $line.Substring(0, $idx).Trim()
    $val = $line.Substring($idx + 1).Trim()
    [Environment]::SetEnvironmentVariable($key, $val, "Process")
}

& mvn @("spring-boot:run")
