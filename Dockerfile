FROM postgres:16

ENV POSTGRES_USER=admin
ENV POSTGRES_PASSWORD=admin
ENV POSTGRES_DB=desafio

# Copia um script SQL para inicialização
COPY init.sql /docker-entrypoint-initdb.d/

EXPOSE 5432

CMD ["postgres"]
