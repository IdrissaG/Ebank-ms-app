<div align="center">

# 🏦 EBank Microservices App

**Application bancaire moderne construite en architecture microservices**

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.x-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Angular](https://img.shields.io/badge/Angular-21-DD0031?style=for-the-badge&logo=angular&logoColor=white)](https://angular.dev)
[![Spring AI](https://img.shields.io/badge/Spring_AI-2.0.1-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-ai)
[![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white)](https://getbootstrap.com)

</div>

---

## 📖 À propos

EBank est une application bancaire **full-stack** qui illustre une architecture microservices complète. Elle permet la gestion des clients et des comptes bancaires, expose des API REST documentées, et intègre un **bot Discord intelligent** capable de répondre à des questions en langage naturel grâce à **OpenAI GPT-4o** et au protocole **MCP**.

---

## 🏗️ Architecture

```
╔══════════════════════════════════════════════════════════╗
║               🌐  Angular Frontend  :4200                ║
╚═══════════════════════════╤══════════════════════════════╝
                            │ HTTP
╔═══════════════════════════▼══════════════════════════════╗
║            🔀  Gateway Service  :9999                    ║
║          Spring Cloud Gateway (WebFlux)                  ║
╚══════════╤════════════════════════════╤═════════════════╝
           │ Eureka                     │ Eureka
╔══════════▼═══════════╗   ╔════════════▼══════════════╗
║  👤 Customer Service  ║   ║  💳 EBank Service         ║
║      :8056            ║   ║      :8057                ║
║  JPA · H2 · MCP ✦    ║   ║  JPA · H2 · MCP ✦        ║
╚══════════════════════╝   ║  Feign → Customer Service  ║
                           ╚═══════════════════════════╝
                                      │
╔═════════════════════════════════════▼═══════════════════╗
║           🔍  Discovery Service (Eureka)  :8761          ║
╚═════════════════════════════════════════════════════════╝

╔═════════════════════════════════════════════════════════╗
║            🤖  EBank Bot  :8058                         ║
║   Spring AI (GPT-4o)  ·  Discord  ·  MCP Client        ║
║     ├─ MCP → customer-service :8056                    ║
║     └─ MCP → ebank-service    :8057                    ║
╚═════════════════════════════════════════════════════════╝
```

---

## 🧩 Services

<div align="center">

| # | Service | Port | Rôle |
|---|---|:---:|---|
| 🔍 | `discovery-service` | **8761** | Registre Eureka — détection automatique des services |
| 🔀 | `gateway-service` | **9999** | API Gateway — point d'entrée unique (WebFlux) |
| 👤 | `customer-service` | **8056** | CRUD clients — exposé comme MCP Server |
| 💳 | `ebank-service` | **8057** | CRUD comptes bancaires — Feign + Resilience4j + MCP Server |
| 🤖 | `ebank-bot` | **8058** | Bot Discord IA (GPT-4o) — MCP Client |
| 🌐 | `angular-front` | **4200** | Interface web Angular 21 |

</div>

---

## 🛠️ Stack Technique

<table>
<tr>
<td valign="top" width="50%">

### ☕ Backend
- **Java 21**
- **Spring Boot 4.x**
- **Spring Cloud 2025.1.3**
  - 🔍 Netflix Eureka (Service Discovery)
  - 🔀 Spring Cloud Gateway (WebFlux)
  - ⚙️ Spring Cloud Config
  - 🔗 OpenFeign (appels inter-services)
  - 🛡️ Resilience4j (Circuit Breaker)
- **Spring AI 2.0.1**
  - 🧠 OpenAI GPT-4o
  - 🔌 MCP Server & Client
- **Spring Data JPA** + **H2** (in-memory)
- **Lombok** · **SpringDoc OpenAPI 3**

</td>
<td valign="top" width="50%">

### 🌐 Frontend
- **Angular 21** (standalone components)
- **Bootstrap 5.3** + Bootstrap Icons
- **ngx-markdown** + marked
- **RxJS** (observables)

### 🤖 Bot Discord
- `spring-boot-starter-discord` v5
- Spring AI — OpenAI GPT-4o
- MCP Client (customer + ebank)
- Variables d'env via `spring-dotenv`

</td>
</tr>
</table>

---

## 📦 Structure du projet

```
ebank-ms-app/
│
├── 🔍 discovery-service/       ← Eureka Server
├── 🔀 gateway-service/         ← API Gateway (port 9999)
├── 👤 customer-service/        ← Service clients (port 8056)
├── 💳 ebank-service/           ← Service comptes (port 8057)
├── 🤖 ebank-bot/               ← Bot Discord IA (port 8058)
├── 🌐 angular-front/           ← Frontend Angular (port 4200)
│
├── .env                        ← 🔑 Clés API (non commité)
└── pom.xml                     ← POM parent Maven
```

---

## ⚙️ Configuration

### 🔑 Variables d'environnement

Créer un fichier `.env` à la racine du projet :

```env
DISCORD_TOKEN=your_discord_bot_token
OPEN_AI_KEY=your_openai_api_key
```

> [!CAUTION]
> Ne **jamais** committer ce fichier. Il contient vos clés API secrètes. Il est déjà listé dans `.gitignore`.

### 🌐 URLs & Ports

| Interface | URL |
|---|---|
| 🌐 Angular App | http://localhost:4200 |
| 🔍 Eureka Dashboard | http://localhost:8761 |
| 🔀 API Gateway | http://localhost:9999 |
| 📘 Swagger — Customer | http://localhost:8056/swagger-ui.html |
| 📘 Swagger — EBank | http://localhost:8057/swagger-ui.html |
| 📘 Swagger — Bot | http://localhost:8058/swagger-ui.html |
| 🗄️ H2 Console — Customers | http://localhost:8056/h2-console |
| 🗄️ H2 Console — Accounts | http://localhost:8057/h2-console |

---

## 🚀 Lancement

### Prérequis

| Outil | Version minimale |
|---|---|
| ☕ Java (JDK) | 21+ |
| 📦 Maven | 3.9+ |
| 🟩 Node.js | 18+ |
| 📦 npm | 11+ |
| 🔑 Clé API OpenAI | — |
| 🤖 Token Discord Bot | — |

---

### ▶️ Ordre de démarrage

> [!IMPORTANT]
> Respecter **impérativement** cet ordre — Eureka doit être en ligne avant les autres services.

**① Discovery Service** — à démarrer en premier

```bash
cd discovery-service
./mvnw spring-boot:run
# ✅ Eureka disponible sur http://localhost:8761
```

---

**② Customer Service & EBank Service** — en parallèle (2 terminaux)

```bash
# Terminal 1
cd customer-service
./mvnw spring-boot:run
```

```bash
# Terminal 2
cd ebank-service
./mvnw spring-boot:run
```

---

**③ Gateway Service**

```bash
cd gateway-service
./mvnw spring-boot:run
# ✅ Gateway disponible sur http://localhost:9999
```

---

**④ EBank Bot** *(optionnel — nécessite les clés API)*

```bash
cd ebank-bot
./mvnw spring-boot:run
```

---

**⑤ Frontend Angular**

```bash
cd angular-front
npm install
npm install marked        # peer dependency requise de ngx-markdown
ng serve
# ✅ App disponible sur http://localhost:4200
```

---

## 🤖 EBank Bot — Intelligence Artificielle

Le bot Discord utilise **Spring AI** avec le modèle **GPT-4o** d'OpenAI. Il accède en temps réel aux données des microservices via le protocole **MCP (Model Context Protocol)** :

```
Utilisateur Discord
       │  message en langage naturel
       ▼
   EBank Bot (GPT-4o)
       ├──── MCP ────► customer-service  (liste clients, recherche...)
       └──── MCP ────► ebank-service     (comptes, soldes, transactions...)
```

> [!NOTE]
> Le bot peut répondre à des questions comme *"Quel est le solde du compte de Jean Dupont ?"* en interrogeant directement les microservices.

---

## 🔗 Communication inter-services

```
Angular  ──HTTP──►  Gateway :9999
                       ├── /CUSTOMER-SERVICE/** ──Eureka──► customer-service :8056
                       └── /EBANK-SERVICE/**    ──Eureka──► ebank-service    :8057

ebank-service  ──OpenFeign──►  customer-service   (enrichissement des comptes)
ebank-service  ──Resilience4j──  (circuit breaker si customer-service tombe)

ebank-bot  ──MCP──►  customer-service :8056/mcp
ebank-bot  ──MCP──►  ebank-service    :8057/mcp
ebank-bot  ──HTTPS──► OpenAI API (GPT-4o)
```

---

## 📝 Notes de développement

> [!TIP]
> Points importants à garder en tête lors du développement :

- 💾 Les bases H2 sont **en mémoire** — les données sont perdues à chaque redémarrage
- 🔌 Ajouter `provideHttpClient()` dans `app.config.ts` pour les appels HTTP Angular
- 🌍 Toujours préfixer les URLs HTTP : `http://localhost:9999/...` (pas juste `localhost:...`)
- 📦 `marked` doit être installé séparément (`npm install marked`) — peer dep de `ngx-markdown`
- 🧩 Angular utilise les **standalone components** — pas de `NgModule`

---

<div align="center">

**Made with ❤️ — Spring Boot · Angular · Spring AI**

</div>
