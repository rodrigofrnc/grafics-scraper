#!/usr/bin/env bash

docker-compose -f infra/docker-compose-arm.yaml down
docker-compose -f infra/docker-compose-arm.yaml up -d