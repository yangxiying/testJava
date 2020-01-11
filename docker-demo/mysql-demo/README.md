# docker 创建mysql

- 直接命名用hub中已经存在的mysql镜像。

- 在目录 `mysql` 中创建`Dockerfile`， 使用5.7，创建 使用自定义的 `my.cnf`

- 创建本地文件夹 `mysql_data`，把sql数据文件挂载到本地。
  
- 要根目录创建 `docker-compose.yml`
  - 创建数据库、用户密码等信息

- 在根目录执行 `docker-compose up`

- 启动成功之后，可以通过 3310端口，root/123456，可以连接到创建的数据库
