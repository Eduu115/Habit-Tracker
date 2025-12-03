# Comandos utiles
cd docker (En caso de estar en docker, no hacer)

# Modo Desarrollo (con hot-reload en puerto 5173)
docker-compose --profile dev up -d

# Modo Producción (build optimizado en puerto 80)
docker-compose --profile prod up -d

# Ver logs
docker-compose logs -f

# Detener todo
docker-compose --profile dev down
docker-compose --profile prod down

# Reconstruir contenedores
docker-compose build --no-cache