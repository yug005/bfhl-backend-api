# bfhl-backend-api

REST API built with Spring Boot for the Bajaj Finserv Health challenge.

## what it does

- `GET /health` → health check, always returns `200`
- `POST /bfhl` → accepts one operation at a time:

| key | input | output |
|-----|-------|--------|
| `fibonacci` | `[n]` | first n fibonacci numbers |
| `prime` | `[2, 4, 7, 9, 11]` | filters only primes → `[2, 7, 11]` |
| `lcm` | `[12, 18, 24]` | LCM of all → `72` |
| `hcf` | `[24, 36, 60]` | HCF of all → `12` |
| `AI` | `"What is the capital of India?"` | one-word answer via Gemini → `"Delhi"` |

## testing with postman

1. Open Postman
2. Set method to `POST`, URL to `http://localhost:8080/bfhl`
3. Go to **Body** → select **raw** → set type to **JSON**
4. Paste any of these:

```json
{"prime": [2, 4, 7, 9, 11]}
```
```json
{"fibonacci": [7]}
```
```json
{"AI": "What is the capital of India?"}
```

5. Hit **Send**

**success response:**

```json
{
  "is_success": true,
  "official_email": "yug2328.be23@chitkara.edu.in",
  "data": [2, 7, 11],
  "error": null
}
```

**error response (bad request):**

```json
{
  "is_success": false,
  "official_email": "yug2328.be23@chitkara.edu.in",
  "data": null,
  "error": "Exactly one key must be present"
}
```

6. Go to **Tests** tab and add:

```javascript
pm.test("Status is OK", () => {
  pm.response.to.have.status(200);
});
```

## tech

- Java 17
- Spring Boot 3.2.2
- Maven
- Google Gemini API (gemini-2.5-flash)

## project structure

```
com.yug.bfhl
├── controller/       → HealthController, BfhlController
├── model/            → ApiResponse
├── service/          → GeminiService
├── util/             → MathUtils, RequestValidator
├── exception/        → InvalidRequestException, GlobalExceptionHandler
└── BfhlApplication.java
```

## setup

```bash
git clone https://github.com/yug005/bfhl-backend-api.git
cd bfhl-backend-api

# set gemini key (optional, has fallback)
set GEMINI_API_KEY=your-key-here

mvn spring-boot:run
```

hits `http://localhost:8080`

## validation rules

- request body must have exactly **one** key
- allowed keys: `fibonacci`, `prime`, `lcm`, `hcf`, `AI`
- no nulls, no empty arrays, no negative numbers
- wrong key or bad format → `400` with error message

---

built by [yug](https://github.com/yug005)
