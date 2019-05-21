# Hyperledger

This project is designed to support a transaction project. We developed based on Hyperledger Fabric 1.1. 

## Prerequisites

1. cURL 7.54.0 or greater is required.
2. Docker 17.06.2-ce or greater is required.
3. Docker Compose 1.14.0 or greater is required.
4. Go Programming Language 1.9.x or greater is required.

## How to Start

After starting docker, run startFabric.sh which will automatically lanch network, create channel and join peer to channel. Then you can use Java SDK in BlockChainService to query and invoke. And you can also use `docker exec` command to enter CLI container to debug.

## Chaincode

This chaincode is written by Go language. You can edit it to add any operation you want. Of course, you can write your own chaincode file and use CLI container to install and instantiate it on our network.

## Java SDK

The java SDK provides one  implement so you can use this Blockchain Service. It provides four methods so you can do some querying and inserting jobs with them.