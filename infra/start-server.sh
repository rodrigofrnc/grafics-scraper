#!/usr/bin/env bash

docker-compose -f infra/docker-compose-arm.yaml up -d
mvn clean package
nohup java -jar target/scraper-* &
