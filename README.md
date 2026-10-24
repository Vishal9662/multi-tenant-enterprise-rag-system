# ContextHub - Multi-Tenant Enterprise RAG System

Skeleton for a production-oriented, multi-tenant enterprise RAG platform.

## Current scope

This first skeleton intentionally contains only:
- Java 21
- Spring Boot 4.1.1
- Spring Cloud Gateway 2025.1.3
- API Gateway
- RAG service
- Basic RAG chat endpoint
- Basic document ingestion endpoint
- Basic document deletion endpoint
- Controller-only placeholders; business logic, persistence, vector DB, LLM and authentication are intentionally left for later iterations

## Services

```text
Client
  |
  v
API Gateway :8080
  |
  | /api/v1/rag/**
  v
RAG Service :8081
  |
  +-- Chat Controller
  +-- Document Ingestion Controller
  +-- Document Deletion Controller
```

## Endpoints through gateway

- `POST /api/v1/rag/chat`
- `POST /api/v1/rag/documents` (multipart field: `file`)
- `DELETE /api/v1/rag/documents/{documentId}`

## Security direction

The intended production security model is:
1. OIDC/OAuth2 identity provider (Keycloak, Auth0, Azure Entra ID, etc.)
2. JWT access tokens
3. Gateway authentication/token validation
4. RAG service authorization as defense in depth
5. `tenant_id` derived from trusted JWT claims, not from a client-supplied tenant ID
6. RBAC/permissions such as `TENANT_ADMIN`, `USER`, and later finer-grained document permissions
7. Tenant filtering enforced at every data-access boundary, especially vector retrieval
8. Service-to-service authentication between gateway and internal services
9. TLS, secret management, rate limiting, audit logging, and security headers

### Important tenant rule

Do not design APIs such as `GET /documents?tenantId=abc` as the security boundary. A user must not be able to switch tenants by changing a request parameter. The authenticated identity should establish the tenant context, and downstream code should enforce that context.

## Next architecture iterations

- Auth / identity service or external IdP integration
- Tenant and user model
- Tenant context propagation
- Document storage (object storage)
- Parsing and chunking pipeline
- Embedding service
- PostgreSQL + pgvector
- Retrieval with mandatory tenant filter
- LLM integration
- Redis/cache
- asynchronous ingestion with Kafka/RabbitMQ/SQS
- observability with metrics, tracing and structured audit logs
