

https://blog.csdn.net/liudongdong19/article/details/79590241


https://www.jianshu.com/p/b92c4a5fb351 
这个里面有 docker-compose 的配置 


https://www.jianshu.com/p/e7d4eaaecbec


# 安装percona/percona-xtradb-cluster
参考：https://blog.csdn.net/sunt2018/article/details/91958220

## docker仓库下载安装
docker pull percona/percona-xtradb-cluster
- 本地镜像安装
docker load < /home/soft/pxc.tar.gz


## 创建内部网络

- 搭建5节点的sql集群
- 出于安全考虑，需要给PXC集群实例创建docker内部网络

- 创建网段
docker network create net1
- 查看相关信息
docker network inspect net1
- 删除网段
docker network rm net1

- 如，执行
docker network create --subnet=172.18.0.0/24 net1 



## 创建docker卷
docker volume create v4

## 创建mysql集群容器
```yaml
# 创建第一个容器
# --privileged表示最高权限
# 设置一些环境变量，如mysql的root登陆密码、集群名、集群同步的密码
# 创建完成后，等待几分钟，最好用mysql连接一下，成功后创建接下来的几个集群
docker run -d -p 3310:3306 -e MYSQL_ROOT_PASSWORD=123456 -e CLUSTER_NAME=PXC -e XTRABACKUP_PASSWORD=123456 -v v1:/var/lib/mysql --privileged --name=node1 --net=net1 --ip 172.18.0.2 pxc

# 创建第二个容器
# CLUSTER_JOIN指定加入的集群的节点名
# 因为是在同一台宿主机上创建的几个PXC容器，所以端口不能重复
# 还有就是一些像卷组、节点名、ip地址需要变化
docker run -d -p 3311:3306 -e MYSQL_ROOT_PASSWORD=123456 -e CLUSTER_NAME=PXC -e XTRABACKUP_PASSWORD=123456 -e CLUSTER_JOIN=node1 -v v2:/var/lib/mysql --privileged --name=node2 --net=net1 --ip 172.18.0.3 pxc

# 创建第三个容器
docker run -d -p 3312:3306 -e MYSQL_ROOT_PASSWORD=123456 -e CLUSTER_NAME=PXC -e XTRABACKUP_PASSWORD=123456 -e CLUSTER_JOIN=node1 -v v3:/var/lib/mysql --privileged --name=node3 --net=net1 --ip 172.18.0.4 pxc

```
- 使用 docker-compose ，报错
```yaml
version: '3'

services: 
    pxc_v1: 
        image: pxc
        container_name: pxc_v1
        environment: 
            MYSQL_ROOT_PASSWORD: 123456
            CLUSTER_NAME: PXC
            XTRABACKUP_PASSWORD: 123456
        volumes: 
            # - v4:/var/lib/mysql
            - ./mysql_v4:/var/lib/mysql
        networks:
            my_net: 
                ipv4_address: 172.19.0.2
        ports: 
            - "3315:3306"
        privileged: true
        tty: true

networks:
    my_net: 
        driver: bridge
        ipam:
            driver: default
            config:
                - subnet: 172.19.0.0/24

```

```log
PS E:\docker-demo\mysql-plx> docker-compose up
Creating network "mysql-plx_my_net" with driver "bridge"
Creating pxc_v1 ... done
Attaching to pxc_v1
pxc_v1    | + '[' m = - ']'
pxc_v1    | + CFG=/etc/mysql/node.cnf
pxc_v1    | + wantHelp=
pxc_v1    | + for arg in '"$@"'
pxc_v1    | + case "$arg" in
pxc_v1    | + file_env XTRABACKUP_PASSWORD xtrabackup
pxc_v1    | + set +o xtrace
pxc_v1    | + file_env CLUSTERCHECK_PASSWORD clustercheck
pxc_v1    | + set +o xtrace
pxc_v1    | + '[' -n '' ']'
pxc_v1    | + '[' -n '' ']'
pxc_v1    | + : checking incoming cluster parameters
pxc_v1    | ++ awk ' { print $1 } '
pxc_v1    | ++ hostname -I
pxc_v1    | + NODE_IP=172.19.0.2
pxc_v1    | + sed -r 's|^[#]?wsrep_node_address=.*$|wsrep_node_address=172.19.0.2|' /etc/mysql/node.cnf
pxc_v1    | + sed -r 's|^[#]?wsrep_sst_auth=.*$|wsrep_sst_auth='\''xtrabackup:123456'\''|' /etc/mysql/node.cnf
pxc_v1    | + [[ -n '' ]]
pxc_v1    | + [[ -n PXC ]]
pxc_v1    | + sed -r 's|^[#]?wsrep_cluster_name=.*$|wsrep_cluster_name=PXC|' /etc/mysql/node.cnf
pxc_v1    | + '[' -z '' ']'
pxc_v1    | + '[' mysqld = mysqld -a -z '' ']'
pxc_v1    | + _check_config mysqld
pxc_v1    | + toRun=("$@" --verbose --help --wsrep-provider='none')
pxc_v1    | ++ mysqld --verbose --help --wsrep-provider=none
pxc_v1    | + errors=
pxc_v1    | ++ _get_config datadir mysqld
pxc_v1    | ++ local conf=datadir
pxc_v1    | ++ shift
pxc_v1    | ++ awk '$1 == "datadir" && /^[^ \t]/ { sub(/^[^ \t]+[ \t]+/, ""); print; exit }'
pxc_v1    | +++ mktemp -u
pxc_v1    | ++ mysqld --verbose --help --wsrep-provider=none --log-bin-index=/tmp/tmp.PT7JK8kYiT
pxc_v1    | + DATADIR=/var/lib/mysql/
pxc_v1    | + '[' '!' -d /var/lib/mysql//mysql ']'
pxc_v1    | + file_env MYSQL_ROOT_PASSWORD
pxc_v1    | + set +o xtrace
pxc_v1    | + '[' -z 123456 -a -z '' -a -z '' ']'
pxc_v1    | + rm -rf
pxc_v1    | + mkdir -p /var/lib/mysql/
pxc_v1    | + echo 'Initializing database'
pxc_v1    | Initializing database
pxc_v1    | + mysqld --initialize-insecure --skip-ssl
pxc_v1    | 2020-01-13T10:31:26.009500Z 0 [Warning] TIMESTAMP with implicit DEFAULT value is deprecated. Please use --explicit_defaults_for_timestamp server option (see documentation
for more details).
pxc_v1    | 2020-01-13T10:31:26.009576Z 0 [Warning] WSREP: Node is running in bootstrap/initialize mode. Disabling pxc_strict_mode checks
pxc_v1    | 2020-01-13T10:31:26.060499Z 0 [Warning] InnoDB: Retry attempts for writing partial data failed.
pxc_v1    | 2020-01-13T10:31:26.061590Z 0 [ERROR] InnoDB: Write to file ./ibdata1failed at offset 0, 1048576 bytes should have been written, only 0 were written. Operating system error number 22. Check that your OS and file system support files of this size. Check also that the disk is not full or a disk quota exceeded.
pxc_v1    | 2020-01-13T10:31:26.062186Z 0 [ERROR] InnoDB: Error number 22 means 'Invalid argument'
pxc_v1    | 2020-01-13T10:31:26.062726Z 0 [ERROR] InnoDB: Could not set the file size of './ibdata1'. Probably out of disk space
pxc_v1    | 2020-01-13T10:31:26.063135Z 0 [ERROR] InnoDB: InnoDB Database creation was aborted with error Generic error. You may need to delete the ibdata1 file before trying to start up again.
pxc_v1    | 2020-01-13T10:31:27.168468Z 0 [ERROR] Plugin 'InnoDB' init function returned error.
pxc_v1    | 2020-01-13T10:31:27.170421Z 0 [ERROR] Plugin 'InnoDB' registration as a STORAGE ENGINE failed.
pxc_v1    | 2020-01-13T10:31:27.172593Z 0 [ERROR] Failed to initialize builtin plugins.
pxc_v1    | 2020-01-13T10:31:27.173998Z 0 [ERROR] Aborting

```

> 国外看到解决方案说是和 datadir  所在的分区格式有关。在win下是NTFS，docker内部是EXT4 

--innodb-use-native-aio=0

## 另一种写法

- `docker-compose up pxc_v1`
成功之后连接上库建一个用户 haproxy ，为了后面的 负载使用
create user 'haproxy'@'%' identified by '';
 flush privileges

- `docker-compose up pxc_v2 pxc_v3` 成功之后再
- `docker-compose up h1`



# 安装Haproxy进行高可用与负载均衡


##  编写Haproxy配置文件
haproxy.cfg


## 创建第1个Haproxy负载均衡服务器

- 下面为测试的
-- docker run -it -d -p 4001:8888 -p 4002:3306 -v ./haproxy:/usr/local/etc/haproxy --name h1 --privileged --net=net1 --ip 172.18.0.7 haproxy

-- docker-compose -f docker-compose.haproxy.yml up


## 进入h1容器，启动Haproxy

docker exec -it h1 bash

haproxy -f /usr/local/etc/haproxy/haproxy.cfg

```
PS E:\docker-demo> docker exec -it h1 bash
root@4397d017f89c:/#
```

查看是否启动成功：

访问http://ip:4001/dbs

