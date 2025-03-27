FROM postgres:16

RUN apt-get update &&\
	apt-get install -y postgis postgresql-16-postgis-3 &&\
	rm -rf /var/lib/apt/lists/*

RUN echo "shared_preload_libraries = 'postgis-3'" >> /usr/share/postgresql/postgresql.conf.sample