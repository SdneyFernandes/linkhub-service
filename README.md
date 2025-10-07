# 🔗 LinkHub: Serviço de Encurtador de URL de Alta Performance

Este projeto é um serviço robusto de encurtamento de URLs, construído com **Java e Spring Boot**. O principal objetivo é demonstrar uma arquitetura de microsserviço completa, focada em **resiliência, performance e automação** DevOps.

**O LinkHub não é apenas um encurtador; é um laboratório prático de Engenharia de Software.**

---

## 🎯 Arquitetura e Stack Técnica

O projeto segue a **Arquitetura Hexagonal (Ports & Adapters)**, garantindo que a lógica de negócio (Core) seja independente das tecnologias de infraestrutura (Adaptadores).

| Categoria | Tecnologia / Padrão | Benefício Demonstrado |
| :--- | :--- | :--- |
| **Linguagem/Framework** | Java 21, Spring Boot 3.x | Desenvolvimento moderno e eficiente de APIs REST. |
| **Arquitetura** | **Hexagonal (Ports & Adapters)** | Isolamento do domínio, alta testabilidade e flexibilidade. |
| **Persistência** | Flyway, H2 (Dev) | Gestão de esquema de banco de dados, garantindo a integridade. |
| **Performance** | **Spring Data Redis** (Cache Distribuído) | Otimização de latência e redução de carga no banco de dados. |
| **Observabilidade** | Prometheus, Grafana, Actuator | Monitoramento em tempo real da saúde e do tráfego da aplicação. |
| **CI/CD** | **GitHub Actions**, Docker | Automação total de testes, build e entrega (Delivery Contínuo). |

---
<img width="1356" height="766" alt="rediss" src="https://github.com/user-attachments/assets/229b7ad2-1034-44d2-afd7-c3fec454da76" />
<img width="1090" height="540" alt="redis" src="https://github.com/user-attachments/assets/77b9dea3-1874-4abb-9859-d733472c4105" />
<img width="1304" height="628" alt="prume" src="https://github.com/user-attachments/assets/0cea849b-8eda-4d45-89b8-dc3c86a52c5f" />
<img width="1281" height="762" alt="promee" src="https://github.com/user-attachments/assets/e1681343-8552-4dc4-9e54-57aae6fa92cd" />
<img width="1090" height="401" alt="prome" src="https://github.com/user-attachments/assets/be1354ec-f3da-4a26-a223-dc60ef18b586" />
<img width="1329" height="743" alt="pipeline" src="https://github.com/user-attachments/assets/4e7a2c15-e3f9-4bfa-af99-addb1e2154bd" />
<img width="1300" height="758" alt="grafana" src="https://github.com/user-attachments/assets/97b85b66-9207-44f7-be36-59339c32d8d0" />
<img width="1102" height="381" alt="fliwai_migraçã" src="https://github.com/user-attachments/assets/3f6b7d54-4323-4f47-97b0-5193758ef54a" />
<img width="1095" height="735" alt="ci_cd" src="https://github.com/user-attachments/assets/e3e15e97-dea8-412c-a3bc-264a96bbb5c4" />

## 🚀 Status de Validação do Sistema

Todos os componentes críticos do sistema foram validados com sucesso através de um roteiro de testes rigoroso.

| Fase | Objetivo Validado | Prova Concreta |
| :--- | :--- | :--- |
| **Fase 0/1** | Inicialização e Funcionalidade Básica | API `POST /links` e `GET /{code}` funcionando com persistência via Flyway. |
| **Fase 2** | Otimização com Cache | Confirmação de **Cache HIT** nos logs após a primeira requisição ao Redis. |
| **Fase 3** | Monitoramento em Tempo Real | Dashboard no Grafana ativo, visualizando métricas do Actuator/Prometheus. |
| **Fase 4** | Automação Total (DevOps) | Pipeline do **GitHub Actions** concluída com sucesso (Testes, Build, Push para Docker Hub). |

---

## ⚙️ Como Subir o Sistema (Do Zero)

### Pré-requisitos
* **Docker e Docker Compose**
* **Java 21+ e Maven**

### 1. Inicializar a Infraestrutura Local
Todos os serviços de infraestrutura (Redis, Prometheus, Grafana) são iniciados via `docker-compose.yml`.

```bash
docker-compose up -d
# Verificação: Você deve ver os contêineres linkhub-redis, linkhub-prometheus e linkhub-grafana rodando.
````

### 2\. Iniciar a Aplicação Spring Boot

Rode a aplicação localmente. Ela se conectará automaticamente aos serviços Docker e executará as migrações do Flyway.

```bash
mvn spring-boot:run
# Verificação: Procure por "Successfully applied 1 migration" nos logs.
```

-----

## 🧪 Comandos de Teste Chave

Use estes comandos para replicar a validação e ver a arquitetura em ação.

### 1\. Testar a Criação e Persistência (POST)

```powershell
# Cria o link e salva no banco (H2)
Invoke-RestMethod -Uri "http://localhost:8080/links" -Method Post `
  -ContentType "application/json" `
  -Body '{"longUrl": "[https://www.linkedin.com/in/sidney-francisco/](https://www.linkedin.com/in/sidney-francisco/)"}'
# Resultado: Retorna a URL curta. Ex: http://localhost:8080/c4fCXHc
```

### 2\. Provar o Cache com Redis (GET)

Use o código curto retornado no passo anterior.

| Ação | Observação no Log (Spring Boot) | O que Prova |
| :--- | :--- | :--- |
| **Acesso 1** | **`Cache miss! Buscando no banco de dados...`** | O sistema foi ao banco de dados e salvou a URL no Redis. |
| **Acesso 2** | **NÃO MOSTRA NADA** | A URL foi servida diretamente do **Redis** (Cache Hit), provando a otimização de performance. |

### 3\. Verificar o Dashboard de Monitoramento

  * **Prometheus:** Acesse `http://localhost:9090`.
  * **Grafana:** Acesse `http://localhost:3000` (Login: `admin`/`admin`). Configure a fonte de dados e crie um painel para visualizar métricas como `http_server_requests_seconds_count` em tempo real.

-----

## 💻 CI/CD e Docker Hub

Toda a entrega é automatizada via GitHub Actions.

  * **Workflow:** `.github/workflows/build-and-publish.yml`
  * **Gatilho:** Qualquer push na branch `main`.
  * **Resultado:** Após o build e testes, a nova imagem é publicada no seu repositório

-----
