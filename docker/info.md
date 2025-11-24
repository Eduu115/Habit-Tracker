# para iniciar docker con todo


## para iniciar mysql en docker
docker run --name habittracker-db \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=habittracker \
  -v $(pwd)/docker/mysql/init:/docker-entrypoint-initdb.d \
  -v mysql-data:/var/lib/mysql \
  -p 3306:3306 \
  -d mysql:8

# BACK
## para relanzar y no tirar de la snapshot, sino de mi codigo local
docker run --name habittracker-backend \
  -v /ruta/local/backend:/app \
  -p 8080:8080 \
  habittracker-backend
