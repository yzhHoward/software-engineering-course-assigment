#!/bin/sh

#  startFarbric.sh
#  
#  Copyright LW-Ricarido(李威) All Rights Reserved
#  Created by 李威 on 2018/5/26.
#  SPDX-License-Identifier: Apache-2.0
#  Exit on first error
set -e
#  Get docker images for fabric, you can change the tag whatever you want. If you have downloaded these images,please remove this command.
# curl -sSL https://goo.gl/6wtTN5 | bash -s 1.1.0

export MSYS_NO_PATHCONV=1

#  set chaincode path for chainService
CC_SRC_PATH=github.com

docker network prune 


# launch network; create channel and join peer to channel
cd basic-network/
./start.sh
# Now launch the CLI container in order to install, instantiate chaincode
docker-compose -f ./docker-compose.yml up -d cli

docker exec -e "CORE_PEER_LOCALMSPID=Org1MSP" -e "CORE_PEER_MSPCONFIGPATH=/opt/gopath/src/github.com/hyperledger/fabric/peer/crypto/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp" cli peer chaincode install -n chainService -v 1.0 -p "$CC_SRC_PATH"
docker exec -e "CORE_PEER_LOCALMSPID=Org1MSP" -e "CORE_PEER_MSPCONFIGPATH=/opt/gopath/src/github.com/hyperledger/fabric/peer/crypto/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp" cli peer chaincode instantiate -C mychannel -n chainService  -v 1.0 -c '{"Args":[""]}'
printf "now please use java sdk\n"
