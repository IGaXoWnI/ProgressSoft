.PHONY: help build test run stop clean db-up db-down

help:
	@echo "Available commands:"
	@echo "  build     - Build the application"
	@echo "  test      - Run tests with coverage"
	@echo "  run       - Start database and run application"
	@echo "  stop      - Stop all services"
	@echo "  clean     - Clean build artifacts"
	@echo "  db-up     - Start database only"
	@echo "  db-down   - Stop database"

build:
	./mvnw clean compile

test:
	./mvnw clean test jacoco:report

run: db-up
	./mvnw spring-boot:run

stop:
	docker-compose down

clean:
	./mvnw clean
	docker-compose down -v

db-up:
	docker-compose up -d postgres

db-down:
	docker-compose down