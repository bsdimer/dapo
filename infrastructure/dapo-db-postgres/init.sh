#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE ROLE dapodbuser WITH LOGIN PASSWORD 'P@ssp0rt';
    CREATE DATABASE dapodb;
    GRANT ALL PRIVILEGES ON DATABASE dapodb TO dapodbuser;
EOSQL