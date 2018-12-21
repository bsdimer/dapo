#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE ROLE dapo WITH LOGIN PASSWORD 'dapo';
    CREATE DATABASE dapo;
    GRANT ALL PRIVILEGES ON DATABASE dapo TO dapo;
EOSQL