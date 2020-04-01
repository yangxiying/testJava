
拉取镜像:
`docker pull zookeeper:3.6`

创建一个自定义网格

```
docker network create --driver bridge --subnet=172.18.0.0/16 --gateway=172.18.0.1 my_net1
```

