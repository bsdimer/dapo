FROM postgres
MAINTAINER bsdimer@gmail.com
ENV POSTGRES_PASSWORD P@ssp0rt
ADD init.sh /docker-entrypoint-initdb.d/init-user-db.sh
RUN chmod 755 /docker-entrypoint-initdb.d/init-user-db.sh
EXPOSE 5432
