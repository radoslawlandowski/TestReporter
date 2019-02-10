#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE testresults;
    CREATE DATABASE attachments;
    ALTER USER postgres PASSWORD 'postgres';
EOSQL