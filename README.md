# k3s 멀티노드 GitOps 기반 반도체 웨이퍼 결함 검출 시스템
**On-Prem Semiconductor Wafer Defect Detection System (k3s Multi-node + GitOps)**


CNN 기반 반도체 웨이퍼 결함 검출의 E2E 품질검사 파이프라인(검출 → 저장 → 시각화)을 구현하고, <br>
멀티노드 k3s + GitOps(Helm/ArgoCD)로 자동 배포/운영 가능한 온프레미스 인프라 PoC로 구성했습니다.


<br>

### 🚀 Orchestration / GitOps
![Kubernetes](https://img.shields.io/badge/k3s-326CE5?style=for-the-badge&logo=kubernetes&logoColor=white)
![Helm](https://img.shields.io/badge/Helm-0F1689?style=for-the-badge&logo=helm&logoColor=white)
![ArgoCD](https://img.shields.io/badge/ArgoCD-EF7B4D?style=for-the-badge&logo=argo&logoColor=white)

### 🔁 CI/CD
![GitLab](https://img.shields.io/badge/GitLab_CI-FC6D26?style=for-the-badge&logo=gitlab&logoColor=white)
![GitLab Runner](https://img.shields.io/badge/GitLab_Runner-FC6D26?style=for-the-badge&logo=gitlab&logoColor=white)

### 📊 Observability
![Prometheus](https://img.shields.io/badge/Prometheus-E6522C?style=for-the-badge&logo=prometheus&logoColor=white)
![Grafana](https://img.shields.io/badge/Grafana-F46800?style=for-the-badge&logo=grafana&logoColor=white)

### 🌐 Networking
![Calico](https://img.shields.io/badge/Calico-EE0000?style=for-the-badge&logo=calico&logoColor=white)
![NGINX](https://img.shields.io/badge/NGINX_Ingress-009639?style=for-the-badge&logo=nginx&logoColor=white)

### 💾 Data / Storage
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![MinIO](https://img.shields.io/badge/MinIO-C72E49?style=for-the-badge&logo=minio&logoColor=white)

### 🧩 Application
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![FastAPI](https://img.shields.io/badge/FastAPI-009688?style=for-the-badge&logo=fastapi&logoColor=white)
![React](https://img.shields.io/badge/React_(Vite)-61DAFB?style=for-the-badge&logo=react&logoColor=black)
<br>
<br>

---

## Overview

### 문제 정의
반도체 제조 공정에서 웨이퍼 표면 결함 검출은 품질과 수율에 직접적인 영향을 줍니다.  
하지만 현장에서는 모델 성능뿐 아니라 **배포 재현성**, **결과 영속화/시각화**, **장애 대응**, **보안 통제**까지 포함한 운영 가능한 품질검사 시스템이 필요합니다.

특히 온프레미스 환경은 망/보안 제약과 제한된 인프라 자원 때문에 **수작업 배포**, **운영 불안정**, **원인 분석 지연**이 반복되기 쉽습니다.

<br/>

### 해결 방식
본 프로젝트는 **CNN 결함 검출 → 결과 저장 → 대시보드 시각화**로 이어지는 E2E 품질검사 흐름을  
멀티노드 k3s 위에 구성하고, 운영 수준으로 재현하는 데 초점을 맞췄습니다.

- GitOps(Helm + Argo CD)로 **변경 이력 기반 배포/롤백** 흐름 표준화  
- GitLab CI로 **빌드/테스트/이미지 푸시** 자동화  
- HPA 기반 **자동 확장**, Prometheus/Grafana 기반 **관측 체계** 구성  
- Ingress 기반 **외부 진입점 통제**, 네트워크/보안 정책 반영  
- MySQL/MinIO 기반 **결과/메타데이터 영속화**로 서비스 연속성 확보  

<br/>

### 기대 효과
- **자동배포/재현성**: Git 기반 변경 관리로 배포 표준화 및 환경 차이 오류 감소  
- **확장성**: 부하 변화에 따라 HPA로 자동 확장/축소하여 성능 저하 완화  
- **관측/가시성**: 지표 기반 모니터링으로 병목/장애 원인 빠른 파악  
- **장애내성**: 멀티노드 분산 및 자동 복구로 서비스 연속성 강화  
- **보안/통제**: 접근 통제와 시크릿/권한 관리로 운영 리스크 절감  
<br>



---


## Dataset & Model

![Dataset](https://img.shields.io/badge/Dataset-WM--811K_(LSWMD)-4C8BF5?style=flat-square)
![Input](https://img.shields.io/badge/Input-26×26×3_RGB-555?style=flat-square)
![Classes](https://img.shields.io/badge/Classes-9--Class-27AE60?style=flat-square)
![Framework](https://img.shields.io/badge/Framework-TensorFlow%20%2F%20Keras-FF6F00?style=flat-square)
![Artifact](https://img.shields.io/badge/Model_Artifact-H5-6C757D?style=flat-square)

<br>

### Dataset
| Item | Value |
|---|---|
| Name | **WM-811K (LSWMD)** |
| Why | 실물 웨이퍼/장비 부재 환경에서 **디지털 트윈 기반 PoC**를 재현하기 위해 공개 데이터셋 활용 |
| Size | **811K wafer maps** |
| Labels | **9-class**: `Center`, `Donut`, `Edge-Loc`, `Edge-Ring`, `Loc`, `Near-full`, `Random`, `Scratch`, `None` |
| Input Format | **26×26×3 (RGB)** (원형 유지, 실시간 추론 적합) |
| Notes | 실제 Fab 데이터가 아닌 **공개 데이터 기반 PoC**이며, 운영/배포 재현성 검증에 초점 |

<br>

### Model
| Item | Value |
|---|---|
| Task | Wafer defect **multi-class classification (9-class)** |
| Architecture | **CNN** (small image 최적화) |
| Framework | **TensorFlow / Keras** |
| Output | 9-class probability distribution |
| Artifact | **H5** (컨테이너 내부 내장 배포) |
| Serving | FastAPI `POST /predict` (cluster internal only) |
| Versioning | `model_version` (DB 저장 + 추론 결과와 함께 관리) |





<!-- Source basis: WM-811K(LSWMD) 활용, 26×26×3 입력, 9-class 분류, TF/Keras, H5 포맷 근거 -->
<!-- :contentReference[oaicite:0]{index=0} -->

<br>

---

## Key Features
1) **GitOps 기반 자동 배포 & 롤백**  
   Git 변경사항을 단일 기준으로 Helm 차트를 배포하고, Argo CD 동기화로 배포 상태를 일관되게 관리합니다.  
   문제 발생 시 이전 버전으로 롤백 가능한 운영 흐름을 제공합니다.

2) **AI 서빙 오토스케일(HPA)로 부하 대응**  
   AI 추론 서비스는 멀티 레플리카로 구성하고, 부하 변화에 따라 HPA로 자동 확장/축소되도록 설계했습니다.  
   피크 트래픽 상황에서도 안정적인 응답을 목표로 합니다.

3) **장애 대응: Kubernetes 기반 자동 복구(Self-healing)**  
   파드/노드 이슈 등 런타임 문제 발생 시 재시작·재스케줄링 메커니즘을 통해 가용성을 유지합니다.  
   장애 상황은 모니터링 지표와 이벤트로 확인할 수 있도록 설계합니다.

4) **실시간 관측 및 알림(Observability)**  
   Prometheus/Grafana 기반으로 핵심 지표(자원 사용률, 요청/지연, 오류, 스케일링 이벤트 등)를 시각화하고,  
   운영자가 이상 징후를 빠르게 감지할 수 있는 기반을 마련했습니다.

5) **멀티노드 역할 분리 & 전용 노드 스케줄링**  
   워크로드를 역할별로 분리하고, AI 워크로드는 전용 노드에만 스케줄링되도록 라벨/테인트 전략을 적용해  
   자원 간섭을 줄였습니다.

6) **보안 기본기 적용(Secret/RBAC/TLS/NetworkPolicy)**  
   시크릿 관리, 최소권한(RBAC), 통신 보호(TLS), 네트워크 접근 제어(NetworkPolicy)로  
   온프레미스 환경에서 요구되는 기본 보안 통제를 반영했습니다.
<br>


---

## Demo Screenshots

추가 예정


<br>

---


## Architecture

### Infra Architecture
<img width="1584" height="1063" alt="image" src="https://github.com/user-attachments/assets/e5421a8f-724f-43a6-88eb-552674a9ba7b" />


<br/>

<br>

### Application Architecture
<img width="1980" height="998" alt="image" src="https://github.com/user-attachments/assets/57011b1a-5ebf-4aa7-9902-e0e1c3895718" />

<br>

---

## Cluster Topology & Node Roles

### Node Roles
| Node | 역할 | 리소스 | 담당 컴포넌트 | 운영 의도 |
|---|---|---|---|---|
| Node 1 (Control Plane + Worker) | 클러스터 제어 + 핵심 앱 구동 | vCPU / RAM | ![k3s Server](https://img.shields.io/badge/k3s_Server-326CE5?style=flat-square&logo=kubernetes&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white) ![React](https://img.shields.io/badge/React-61DAFB?style=flat-square&logo=react&logoColor=black) ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white) ![ArgoCD](https://img.shields.io/badge/Argo_CD-EF7B4D?style=flat-square&logo=argo&logoColor=white) | 배포/제어 + 핵심 서비스 통합으로 운영 단순화 |
| Node 2 (Worker) | 스토리지/모니터링/CI 실행 전담 | vCPU / RAM | ![MinIO](https://img.shields.io/badge/MinIO-C72E49?style=flat-square&logo=minio&logoColor=white) ![Prometheus](https://img.shields.io/badge/Prometheus-E6522C?style=flat-square&logo=prometheus&logoColor=white) ![Grafana](https://img.shields.io/badge/Grafana-F46800?style=flat-square&logo=grafana&logoColor=white) ![GitLab Runner](https://img.shields.io/badge/GitLab_Runner-FC6D26?style=flat-square&logo=gitlab&logoColor=white) | I/O·관측·빌드 부하를 앱과 분리해 안정성 확보 |
| Node 3 (AI Serving Server) | 추론 전용 노드 | vCPU / RAM | ![FastAPI](https://img.shields.io/badge/FastAPI-009688?style=flat-square&logo=fastapi&logoColor=white) ![HPA](https://img.shields.io/badge/HPA-326CE5?style=flat-square&logo=kubernetes&logoColor=white) | 추론 워크로드 격리로 성능/안정성 확보 |

<br/>

### CNI / CIDR / Ingress
| 항목 | 선택/방식 | 의도 |
|---|---|---|
| CNI | ![Calico](https://img.shields.io/badge/Calico-EE0000?style=flat-square&logo=calico&logoColor=white) | 멀티노드 Pod 네트워크 안정성 확보, 노드 간 통신 보장 |
| Pod CIDR | ![Pod CIDR](https://img.shields.io/badge/Pod_CIDR-k3s-326CE5?style=flat-square&logo=kubernetes&logoColor=white) | k3s 설정과 CNI 설정에서 CIDR 일치가 필수 |
| Service CIDR | ![Service CIDR](https://img.shields.io/badge/Service_CIDR-ClusterIP-326CE5?style=flat-square&logo=kubernetes&logoColor=white) | 서비스는 ClusterIP 중심으로 내부 통신 유지 |\

<br>

---


## Repository Structure (Summary)

<br>

![Frontend](https://img.shields.io/badge/Frontend-React-61DAFB?style=flat-square&logo=react&logoColor=black)
![Backend](https://img.shields.io/badge/Backend-Spring_Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![AI](https://img.shields.io/badge/AI-FastAPI-009688?style=flat-square&logo=fastapi&logoColor=white)
![GitOps](https://img.shields.io/badge/GitOps-Helm%20%2B%20ArgoCD-0F1689?style=flat-square&logo=helm&logoColor=white)

<br>

| Directory | Badge | Description |
|---|---|---|
| `frontend/` | ![React](https://img.shields.io/badge/React-UI-61DAFB?style=flat-square&logo=react&logoColor=black) | Dashboard (UI) |
| `wafer-backend/` | ![Spring](https://img.shields.io/badge/Spring_Boot-API-6DB33F?style=flat-square&logo=springboot&logoColor=white) | Upload / Inference Orchestration / Stats |
| `fastapi-ai-serving/` | ![FastAPI](https://img.shields.io/badge/FastAPI-AI_Serving-009688?style=flat-square&logo=fastapi&logoColor=white) | Predict / Health / Metrics |
| `helm-charts/` | ![ArgoCD](https://img.shields.io/badge/ArgoCD-Apps-EF7B4D?style=flat-square&logo=argo&logoColor=white) ![Helm](https://img.shields.io/badge/Helm-Charts-0F1689?style=flat-square&logo=helm&logoColor=white) | Helm Charts + ArgoCD Applications |

<br>

### Wiki (Detailed Docs)
| Topic | Link |
|---|---|
| 📁 Repository Structure | [Open](../../wiki/Repository-Structure) |
| 🧩 Architecture & Components | [Open](../../wiki/Architecture-&-Components) |
| 🧾 API Specification | [Open](../../wiki/API-Specification) |
| 🛠️ Troubleshooting | [Open](../../wiki/Troubleshooting) |

<br>

---

## Getting Started (Quick Start)

![QuickStart](https://img.shields.io/badge/Quick_Start-Upload→Inference→Result-2F80ED?style=flat-square)
![GitOps](https://img.shields.io/badge/Deploy-GitOps(ArgoCD)-EF7B4D?style=flat-square&logo=argo&logoColor=white)
![k3s](https://img.shields.io/badge/Kubernetes-k3s-326CE5?style=flat-square&logo=kubernetes&logoColor=white)

> **Upload → Inference → Result** 최소 파이프라인을 빠르게 확인합니다.  
> 상세 설치/운영은 Wiki로 분리했습니다.

<br>

### Wiki (Full Guide)
| Topic | Link |
|---|---|
| 🧭 Getting Started (Full) | [Open](../../wiki/Getting-Started-(Full)) |
| 🧰 Manual Deploy (Helm) | [Open](../../wiki/Manual-Deploy-(Helm)) |
| 🔁 GitOps Deploy (ArgoCD) | [Open](../../wiki/GitOps-Deploy-(ArgoCD)) |

<br>

### Prerequisites (Checklist)
- [ ] k3s multi-node cluster + `kubectl` access
- [ ] Ingress Nginx ready + domain/host decided
- [ ] GitLab CI/Registry ready + Runner online
- [ ] MySQL / MinIO Secrets prepared

<br>

### Quick Steps (GitOps)
| Step | Action | Output |
|---:|---|---|
| 1 | GitLab CI로 Backend/Frontend/AI **이미지 빌드 & 푸시** | Registry에 이미지 업로드 |
| 2 | GitOps Repo에서 Helm values의 **image tag/version 갱신 커밋** | Desired State 업데이트 |
| 3 | ArgoCD **Auto-Sync(권장)** 또는 수동 Sync | k3s에 배포 반영 |
| 4 | Ingress 엔드포인트로 **UI/API 접속 확인** | 외부 접근 정상 |
| 5 | **Smoke Test**: Health → Upload → List/Stats | E2E 동작 확인 |

<br>


### Endpoints

<!-- Method Badges -->
![GET](https://img.shields.io/badge/GET-2F80ED?style=flat-square)
![POST](https://img.shields.io/badge/POST-27AE60?style=flat-square)
![DELETE](https://img.shields.io/badge/DELETE-EB5757?style=flat-square)
![WS](https://img.shields.io/badge/WS-9B51E0?style=flat-square)
![INTERNAL](https://img.shields.io/badge/INTERNAL-333333?style=flat-square)


#### External (via Ingress)
| Component | Base URL | Notes |
|---|---|---|
| ![React](https://img.shields.io/badge/React_Dashboard-61DAFB?style=flat-square&logo=react&logoColor=black) | `https://<domain>/` | UI Entry |
| ![Spring](https://img.shields.io/badge/Spring_Boot_API-6DB33F?style=flat-square&logo=springboot&logoColor=white) | `https://<domain>/api` | Public API Base |



#### Spring Boot (Public APIs)
| Method | Endpoint | Purpose |
|---|---|---|
| ![GET](https://img.shields.io/badge/GET-2F80ED?style=flat-square) | `/api/health` | Health check |
| ![POST](https://img.shields.io/badge/POST-27AE60?style=flat-square) | `/api/images` | Upload → Inference orchestration |
| ![GET](https://img.shields.io/badge/GET-2F80ED?style=flat-square) | `/api/images` | List records (paging/filter) |
| ![GET](https://img.shields.io/badge/GET-2F80ED?style=flat-square) | `/api/images/{id}` | Get record detail |
| ![DELETE](https://img.shields.io/badge/DELETE-EB5757?style=flat-square) | `/api/images/{id}` | Delete record (DB + object) |
| ![GET](https://img.shields.io/badge/GET-2F80ED?style=flat-square) | `/api/images/stats` | Overall stats |
| ![GET](https://img.shields.io/badge/GET-2F80ED?style=flat-square) | `/api/images/stats/daily` | Daily stats |
| ![GET](https://img.shields.io/badge/GET-2F80ED?style=flat-square) | `/api/images/stats/defect-distribution` | Defect distribution |



#### WebSocket (Realtime)
| Method | Endpoint | Topics | Notes |
|---|---|---|---|
| ![WS](https://img.shields.io/badge/WS-9B51E0?style=flat-square) | `ws://<domain>/ws` | `/topic/predictions`, `/topic/alerts`, `/topic/stats`, `/topic/system` | STOMP, SockJS fallback |



#### Internal Only (Cluster)
| Scope | Component | Base URL | Endpoints |
|---|---|---|---|
| ![INTERNAL](https://img.shields.io/badge/INTERNAL-333333?style=flat-square) | ![FastAPI](https://img.shields.io/badge/FastAPI_AI-009688?style=flat-square&logo=fastapi&logoColor=white) | `http://fastapi-service.ai-serving:8000` | ![POST](https://img.shields.io/badge/POST-27AE60?style=flat-square) `/predict` · ![GET](https://img.shields.io/badge/GET-2F80ED?style=flat-square) `/health` · ![GET](https://img.shields.io/badge/GET-2F80ED?style=flat-square) `/health/ready` · ![GET](https://img.shields.io/badge/GET-2F80ED?style=flat-square) `/metrics` |
| ![INTERNAL](https://img.shields.io/badge/INTERNAL-333333?style=flat-square) | ![Spring](https://img.shields.io/badge/Spring_Boot_Internal-6DB33F?style=flat-square&logo=springboot&logoColor=white) | `http://backend-service.application:8080` | (Cluster internal service-to-service) |
