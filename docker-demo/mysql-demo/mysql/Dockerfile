FROM mysql:5.7.28
COPY my.cnf /etc/my.cnf
# #RUN -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=my-docker-test -e TZ=Asia/Shanghai -e restart=always -p 3310:3306 -v ./mysql_data:/var/lib/mysql -d
# RUN -e MYSQL_ROOT_PASSWORD=123456 \
#     && -e MYSQL_DATABASE=my-docker-test \
#     && -e TZ=Asia/Shanghai \
#     && -e restart=always \
#     # && -p 3310:3306 \
#     # && -v ./mysql_data:/var/lib/mysql \
#     # && -d