# Użyj oficjalnego obrazu PostgreSQL
FROM postgres:latest

# Ustaw zmienną środowiskową dla hasła użytkownika postgres
ENV POSTGRES_PASSWORD sekret

# Ustaw zmienną środowiskową dla nazwy bazy danych
ENV POSTGRES_DB ai-header-generator