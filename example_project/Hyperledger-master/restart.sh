#!/bin/sh

#  restart.sh
#  
#
#  Created by 李威 on 2018/6/7.
#
#  When stop network, run this script to restart network.
docker start cli peer0.org1.example.com couchdb orderer.example.com ca.example.com
