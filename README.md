# MockAPI Service

MockAPI is a lightweight Spring Boot service that generates synthetic data and exposes mock REST endpoints. It’s designed for fast prototyping, UI development, and integration testing—no real backend or databases required.

## What it does
 - Serves fully functional CRUD APIs 
 - Generates sample data for dashboards, tables, search, and analytics 
 - Lets frontends develop independently using predictable mock responses 
 - Includes OpenAPI/Swagger UI for quick testing

## Tech
 - Spring Boot 
 - Java 17 
 - Springdoc OpenAPI 
 - In-memory data simulation

## Why use it
 - MockAPI helps teams build, iterate, and demo features at lightning speed without waiting for backend systems, schemas, or databases to exist.
---

## Features

### 1. **Dashboard**
A centralized analytics endpoint showing:
- Total and active devices
- Connected / Disconnected trends
- Devices by project
- Warranty status split
- Regional distribution
- Recent alerts

### 2. **Projects**
Simulates project management and device grouping:
- CRUD operations on project data
- 100+ sample records (`projects.json`)
- Includes metadata like service plan, order numbers, expiry, etc.

### 3. **Devices**
Represents edge IoT devices:
- CRUD endpoints
- Linked with projects
- Includes IMEI, ICCID, firmware version, connection status, and warranty

### 4. **Alerts**
Live monitoring feed:
- Supports real-time alert simulation
- Filter by severity (`Critical`, `Warning`, `Info`)
- “Acknowledge” toggle to mark issues as reviewed
- Chart summary endpoint for severity breakdowns

### 5. **Universal Search**
Cross-entity search API powering the Plasmic global search bar:
- Searches across Projects, Devices, and Alerts
- Returns grouped results by domain
- Lightweight and front-end ready

---

## Tech Stack

| Layer | Tech |
|-------|------|
| Backend | Spring Boot 3.5.4 (Java 17) |
| Data Source | Static JSON (classpath resources) |
| API Docs | SpringDoc OpenAPI (Swagger UI) |
| UI Integration | Plasmic + React + Recharts |
| Mock Simulation | In-memory repositories + random generators |

---

Note: After creating the repo please use the use `mvn clean install` to build the package.

---

## API Endpoints

### Dashboard
| Method | Endpoint | Description |
|--------|-----------|-------------|
| GET | `/api/dashboard` | Complete dashboard response |
| GET | `/api/dashboard/summary` | Summary metrics only |
| GET | `/api/dashboard/trend` | Device connection trends |
| GET | `/api/dashboard/projects` | Devices by project |
| GET | `/api/dashboard/regions` | Region-wise distribution |
| GET | `/api/dashboard/warranty` | Warranty pie data |
| GET | `/api/dashboard/alerts` | Recent alerts |

---

### Projects
| Method | Endpoint | Description |
|--------|-----------|-------------|
| GET | `/api/projects` | List all projects |
| GET | `/api/projects/{id}` | Fetch by ID |
| POST | `/api/projects` | Add a new project |
| PUT | `/api/projects/{id}` | Update existing project |
| DELETE | `/api/projects/{id}` | Remove project |

---

### Devices
| Method | Endpoint | Description |
|--------|-----------|-------------|
| GET | `/api/devices` | List all devices |
| GET | `/api/devices/{id}` | Fetch by ID |
| POST | `/api/devices` | Add new device |
| PUT | `/api/devices/{id}` | Update device |
| DELETE | `/api/devices/{id}` | Delete device |

---

### Alerts
| Method | Endpoint | Description |
|--------|-----------|-------------|
| GET | `/api/alerts` | List all alerts (supports `?severity=`) |
| PATCH | `/api/alerts/{id}` | Toggle or set acknowledgement |
| POST | `/api/alerts/simulate` | Inject a random live alert |
| GET | `/api/alerts/summary` | Return counts by severity for charts |

---

### Universal Search
| Method | Endpoint | Description |
|--------|-----------|-------------|
| GET | `/api/search?q=fleet` | Search across Projects, Devices, Alerts |
| GET | `/api/search?q=critical` | Returns grouped search results |
| Response | JSON grouped by domain | `{ "projects": [...], "devices": [...], "alerts": [...] }` |

---

## Sample Data

| File | Description |
|------|--------------|
| `dashboard.json` | 100-day trend analytics data |
| `projects.json` | 100 sample projects with metadata |
| `devices.json` | (optional) Linked device inventory |
| `alerts.json` | 150 alerts with random generation support |


---

## Real-Time Demo Magic

The system supports live simulation through the **Alerts** module.

**Example:**
```bash
# Trigger new random alert every 10 seconds
POST /api/alerts/simulate
Use this with a small front-end polling job or WebSocket to simulate live system monitoring.
setInterval(() => fetch("/api/alerts/simulate", { method: "POST" }), 10000);
Recharts on the Plasmic dashboard can automatically update the alert count by severity in real-time.
````
---
## Universal Search Example
```bash
GET /api/search?q=fleet
```
```json
{
  "projects": [
    { "projectId": "P100001", "projectName": "Fleet Automation #2" }
  ],
  "devices": [
    { "imei": "900000000156180", "deviceName": "MySimplifi-900000000156180" }
  ],
  "alerts": [
    { "id": "ALT091", "severity": "Critical", "message": "Device disconnected for >30 minutes" }
  ]
}
```
---
## Quick Start

```bash
# 1. Build
mvn clean package

# 2. Run
mvn spring-boot:run

# 3. Access Swagger UI
http://localhost:9090/api/swagger-ui/index.html
```

---
## Demo Flow for Presentation 
 - Start from /api/dashboard → show summary metrics. 
 - Navigate to /api/projects → highlight CRUD capabilities. 
 - Go to /api/devices → show live device data. 
 - Switch to /api/alerts → simulate new alert injection. 
 - Use /api/search?q= → show unified, cross-entity search results. 
 - Highlight integration in Plasmic UI → emphasize speed, composability, and developer agility.



---
