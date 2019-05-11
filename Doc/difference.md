## 不同

### basic-network

#### start.sh

> 第16行，少了docker ps -a

#### docker-compose.yaml

> 第21行，多了-d
>
> 第32行，不一样，
>
> 第55/56行，参数是debug
>
> 108行，参数是debug

#### init.sh

> 多了一行

#### teardown.sh

> 第16行，少了docker rm $(docker ps -aq)