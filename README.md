# FX Deals Data Warehouse

Spring Boot app for managing FX deals. Built for Bloomberg to store and analyze foreign exchange transactions.

## What does it do?

Simple REST API that lets you import FX deals into a PostgreSQL database. It checks if the deal already exists before saving and validates all the fields.

## Tech Stack

- Java 17
- Spring Boot 4.0.0
- PostgreSQL 15
- Maven
- Docker
- JUnit & Mockito

## Setup

You need Java 17 and Docker installed.

### Quick Start

Using Makefile:
```bash
make run
```

Or manually:
```bash
docker-compose up -d
./mvnw spring-boot:run
```

App runs on `http://localhost:8888`

## API Usage

### Import a deal

```bash
POST /api/fx-deals/import
```

Example:
```json
{
  "dealUniqueId": "DEAL001",
  "fromCurrency": "USD",
  "toCurrency": "EUR",
  "dealTimestamp": "2024-01-15T10:30:00",
  "dealAmount": 1000.50
}
```

Test it:
```bash
curl -X POST http://localhost:8888/api/fx-deals/import \
  -H "Content-Type: application/json" \
  -d '{"dealUniqueId":"DEAL001","fromCurrency":"USD","toCurrency":"EUR","dealTimestamp":"2024-01-15T10:30:00","dealAmount":1000.50}'
```

## Validation

- Deal ID must be unique
- Currency codes must be 3 characters (like USD, EUR)
- Amount must be positive
- All fields are required

## Database

PostgreSQL running in Docker:
- Database: `postgres`
- Port: `5432`
- User: `postgres`
- Password: `postgres`

Table structure:
```sql
fx_deals (
  deal_unique_id VARCHAR(255) PRIMARY KEY,
  from_currency VARCHAR(3),
  to_currency VARCHAR(3),
  deal_timestamp TIMESTAMP,
  deal_amount NUMERIC(19,4),
  created_at TIMESTAMP
)
```

## Testing

Run tests:
```bash
./mvnw test
```

With coverage:
```bash
./mvnw clean test jacoco:report
```

Check coverage report at `target/site/jacoco/index.html`

## Project Structure

```
src/main/java/
  ├── entity/       - FxDeal entity
  ├── repository/   - Database layer
  ├── service/      - Business logic
  ├── controller/   - REST API
  └── dto/          - Request objects
```

## Notes

- Each deal saves independently (no rollback)
- Duplicates are rejected
- Everything is logged
- Currency codes auto-convert to uppercase

## Makefile Commands

```bash
make run      # Start everything
make test     # Run tests
make stop     # Stop containers
make clean    # Clean up
```

## Troubleshooting

**Port 8888 in use?**
Change it in `application.properties`

**Database not connecting?**
Check if Docker container is running: `docker-compose ps`

**Tests failing?**
Stop the running app first, then run tests

---

Built for ProgressSoft Java Developer assignment
