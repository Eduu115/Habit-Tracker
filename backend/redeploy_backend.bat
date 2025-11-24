@echo off
echo ==========================================
echo   REDEPLOY BACKEND - HABITTRACKER
echo ==========================================

echo.
echo Deteniendo todos los contenedores...
docker stop %%(docker ps -q)%% >nul 2>&1

echo Eliminando contenedores...
docker rm %%(docker ps -aq)%% >nul 2>&1

echo Eliminando imagen anterior si existe...
docker rmi habittracker-backend -f >nul 2>&1

echo.
echo Construyendo nueva imagen...
docker build -t habittracker-backend .

echo.
echo Iniciando contenedor...
docker run --name habittracker-backend -p 8080:8080 habittracker-backend

echo.
echo ==========================================
echo   BACKEND REDEPLOY COMPLETADO
echo ==========================================
pause
