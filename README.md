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

## Tech Stack (역할/종류/선정 근거)

### 인프라
| Layer | Tech | 책임 | 선정 근거 |
|---|---|---|---|
| Orchestration | ![k3s](https://img.shields.io/badge/k3s-326CE5?style=flat-square&logo=kubernetes&logoColor=white) | 멀티노드 Kubernetes 클러스터 구성/운영 | 경량 배포로 온프레미스 VM에서 멀티노드 구성에 적합 |
| CNI / Network | ![Calico](https://img.shields.io/badge/Calico-EE0000?style=flat-square&logo=calico&logoColor=white) | Pod 네트워크 + NetworkPolicy 기반 트래픽 제어 | 멀티노드 환경에서 정책 기반 제어 지원에 유리 |
| Ingress | ![NGINX Ingress](https://img.shields.io/badge/NGINX_Ingress-009639?style=flat-square&logo=nginx&logoColor=white) | 외부 트래픽 진입점(라우팅), TLS Termination | K8s Ingress 운영 표준에 가깝고 리버스 프록시 성능이 우수 |
| Autoscaling | ![HPA](https://img.shields.io/badge/HPA-326CE5?style=flat-square&logo=kubernetes&logoColor=white) ![metrics-server](https://img.shields.io/badge/metrics--server-326CE5?style=flat-square&logo=kubernetes&logoColor=white) | 부하 기반 replica 자동 확장/축소 | 운영형 스케일링 검증을 위한 기본 구성요소 |

<br/>

### 배포
| Layer | Tech | 책임 | 선정 근거 |
|---|---|---|---|
| Packaging | ![Helm](https://img.shields.io/badge/Helm-0F1689?style=flat-square&logo=helm&logoColor=white) | 배포 템플릿(Chart) + 환경별 values 관리 | 재현성과 유지보수성을 높이는 배포 표준화 |
| GitOps CD | ![Argo CD](https://img.shields.io/badge/Argo_CD-EF7B4D?style=flat-square&logo=argo&logoColor=white) | Git 기준 선언형 배포, Auto Sync/롤백 흐름 | GitOps 표준 도구로 운영 자동화에 적합 |
| CI | ![GitLab CI](https://img.shields.io/badge/GitLab_CI-FC6D26?style=flat-square&logo=gitlab&logoColor=white) ![Runner](https://img.shields.io/badge/Runner-FC6D26?style=flat-square&logo=gitlab&logoColor=white) | 빌드/테스트/이미지 푸시 자동화 | Pipeline as Code로 배포 흐름 표준화 가능 |
| Registry | ![GitLab Registry](https://img.shields.io/badge/GitLab_Registry-FC6D26?style=flat-square&logo=gitlab&logoColor=white) | 컨테이너 이미지 저장/배포 연계 | CI/CD와 통합된 사설 레지스트리 운영에 적합 |

<br/>

### 관측
| Layer | Tech | 책임 | 선정 근거 |
|---|---|---|---|
| Metrics | ![Prometheus](https://img.shields.io/badge/Prometheus-E6522C?style=flat-square&logo=prometheus&logoColor=white) | 노드/Pod/서비스 메트릭 수집 | K8s 환경 표준에 가까운 Pull 기반 수집/PromQL |
| Visualization | ![Grafana](https://img.shields.io/badge/Grafana-F46800?style=flat-square&logo=grafana&logoColor=white) | 대시보드 시각화 및 알림 통합 | 실시간 대시보드 구성과 운영 공유에 유리 |

<br/>

### 데이터
| Layer | Tech | 책임 | 선정 근거 |
|---|---|---|---|
| Object Storage | ![MinIO](https://img.shields.io/badge/MinIO-C72E49?style=flat-square&logo=minio&logoColor=white) | 이미지/아티팩트 객체 저장(S3 호환) | 온프레미스에서 S3 호환 객체 스토리지를 경량으로 운영 |
| DB | ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white) | 추론 결과/메타데이터 영속 저장 | 트랜잭션 안정성과 Spring Boot(JPA) 연동이 용이 |

<br/>

### 애플리케이션
| Layer | Tech | 책임 | 선정 근거 |
|---|---|---|---|
| AI Serving | ![FastAPI](https://img.shields.io/badge/FastAPI-009688?style=flat-square&logo=fastapi&logoColor=white) | CNN 추론 API 서빙(멀티 레플리카) | 경량 서빙에 적합, 배포 단위 분리에 유리 |
| Backend | ![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white) | 품질검사 흐름 오케스트레이션/API | 서비스 구조화 및 운영 관점 확장에 유리 |
| Frontend | ![React](https://img.shields.io/badge/React_(Vite)-61DAFB?style=flat-square&logo=react&logoColor=black) | 결과 조회/시각화 대시보드 UI | SPA/컴포넌트 기반으로 실시간 UI 확장에 유리 |

<br/>

### 보안
| Layer | Tech | 책임 | 선정 근거 |
|---|---|---|---|
| Secret 관리 | ![K8s Secret](https://img.shields.io/badge/K8s_Secret-326CE5?style=flat-square&logo=kubernetes&logoColor=white) ![Sealed Secrets](https://img.shields.io/badge/Sealed_Secrets-326CE5?style=flat-square&logo=kubernetes&logoColor=white) | 민감정보 GitOps 운영(암호화/복호화 흐름) | GitOps 환경에서 시크릿을 안전하게 버전관리하기 위함 |
| Access Control | ![RBAC](https://img.shields.io/badge/RBAC-Kubernetes-326CE5?style=flat-square&logo=kubernetes&logoColor=white) | 최소권한 기반 리소스 접근 제어 | Kubernetes 표준 접근제어로 운영 통제에 적합 |
| TLS | ![TLS](https://img.shields.io/badge/TLS-NGINX_Ingress-009639?style=flat-square&logo=nginx&logoColor=white) | 외부 트래픽 암호화(셀프사인 → 확장 가능) | 실습/운영 난이도가 낮고 온프레미스에 현실적 |


<br>

---

## Architecture

### Infra Architecture
<img width="1584" height="1063" alt="image" src="https://github.com/user-attachments/assets/e5421a8f-724f-43a6-88eb-552674a9ba7b" />


<br/>

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

## Repository Structure



### FastAPI (AI Serving)

```bash
fastapi-ai-serving/
├── app/
│   ├── main.py                  # FastAPI 엔트리포인트
│   ├── api/
│   │   ├── predict.py           # POST /predict
│   │   └── health.py            # GET /health, /health/ready
│   ├── core/
│   │   ├── config.py            # 환경변수 (MinIO URL 등)
│   │   └── metrics.py           # Prometheus 메트릭
│   ├── model/
│   │   ├── loader.py            # CNN 모델 로딩 (startup 시)
│   │   └── predict.py           # 추론 로직
│   ├── schema/
│   │   ├── request.py           # ImageUrlRequest
│   │   └── response.py          # PredictionResponse
│   └── util/
│       └── image_downloader.py  # MinIO 이미지 다운로드
├── data/
│   └── wafer_defect_model.h5    # 모델 파일
├── Dockerfile
├── requirements.txt
└── tests/
```

---

### Spring Boot (Application Layer)

```bash
wafer-backend/
├── src/main/java/com/wafer/backend/
│   ├── WaferApplication.java
│   ├── config/
│   │   ├── CorsConfig.java
│   │   ├── RestTemplateConfig.java
│   │   ├── WebSocketConfig.java          # STOMP WebSocket 설정
│   │   ├── MinioConfig.java              # MinIO 클라이언트 빈
│   │   └── ResilienceConfig.java         # CircuitBreaker/Retry 설정
│   ├── controller/
│   │   ├── AiPredictionController.java   # POST /api/images
│   │   ├── AiRecordController.java       # GET/DELETE /api/images/**
│   │   ├── StatsController.java          # GET /api/images/stats/**
│   │   └── HealthController.java         # GET /api/health
│   ├── dto/
│   │   ├── request/
│   │   │   ├── PredictRequest.java       # FastAPI 호출용
│   │   │   └── ImageUploadRequest.java
│   │   └── response/
│   │       ├── AiPredictionResponse.java # FastAPI 응답 매핑
│   │       ├── AiRecordResponseDto.java  # 클라이언트 응답
│   │       ├── StatsResponse.java
│   │       ├── DailyStatsResponse.java
│   │       ├── DefectDistributionResponse.java
│   │       └── ErrorResponse.java        # 통합 에러 형식
│   ├── entity/
│   │   └── WaferImageEntity.java
│   ├── enums/
│   │   ├── DefectType.java               # 9-class enum
│   │   ├── UploadSource.java             # MANUAL/CRONJOB/SIMULATOR
│   │   └── ProcessingStatus.java         # COMPLETED/PENDING/FAILED
│   ├── exception/
│   │   ├── AiRequestSendException.java
│   │   ├── AiServerException.java
│   │   ├── FileProcessingException.java
│   │   ├── InvalidAiResponseException.java
│   │   └── GlobalExceptionHandler.java   # @RestControllerAdvice
│   ├── repository/
│   │   └── WaferImageRepository.java     # JPA + 통계 쿼리
│   ├── service/
│   │   ├── AiAnalysisService.java        # FastAPI 호출 + CB
│   │   ├── WaferImageService.java        # 오케스트레이션 로직
│   │   ├── StatsService.java             # 통계 집계
│   │   ├── NotificationService.java      # WebSocket + Slack
│   │   └── StorageService.java           # MinIO 업/다운로드
│   └── util/
│       ├── ImageFileValidator.java
│       └── MinIOUploader.java               
├── src/main/resources/
│   ├── application.yml
│   └── application-k8s.yml               # k8s 프로파일
├── Dockerfile
└── build.gradle
```

---

### Helm Charts / GitOps Repo

```bash
helm-charts/
├── wafer-system/
│   └── Chart.yaml
├── fastapi-ai/
│   ├── Chart.yaml
│   ├── values.yaml
│   └── templates/
│       ├── deployment.yaml      # taint/toleration, 3 replicas
│       ├── service.yaml
│       ├── hpa.yaml
│       ├── networkpolicy.yaml   # application namespace만 허용
│       └── secret.yaml
├── backend/
│   ├── Chart.yaml
│   ├── values.yaml
│   └── templates/
│       ├── deployment.yaml
│       ├── service.yaml
│       ├── ingress.yaml
│       └── configmap.yaml
├── frontend/
│   ├── Chart.yaml
│   ├── values.yaml
│   └── templates/
│       ├── deployment.yaml
│       ├── service.yaml
│       └── ingress.yaml
├── mysql/
│   ├── Chart.yaml
│   ├── values.yaml
│   └── templates/
│       ├── statefulset.yaml
│       ├── pvc.yaml
│       ├── service.yaml
│       ├── secret.yaml
│       └── networkpolicy.yaml
├── minio/
│   └── templates/
├── monitoring/
│   ├── prometheus/
│   └── grafana/
└── argocd/
    └── applications/            # Argo CD Application CRDs
        ├── fastapi-ai.yaml
        ├── backend.yaml
        ├── frontend.yaml
        ├── mysql.yaml
        ├── minio.yaml
        └── monitoring.yaml
```

<br>

---

## Getting Started

### Hardware (ARM64 / Apple Silicon)

| Node | Spec | Role |
|---|---|---|
| **VM-1** | M5 / 32GB / Ubuntu VM | Control Plane + Core Apps (Backend/Frontend/MySQL/ArgoCD) |
| **VM-2** | M5 / 32GB / Ubuntu VM | Worker (MinIO/Monitoring/GitLab Runner) |
| **VM-3** | M2 / 16GB / Ubuntu VM | AI Worker (FastAPI 전용) |

> 💡 All nodes are **ARM64**. On x86, adjust image builds with `--platform`.

### Software

| Tool | Version | Purpose |
|---|---:|---|
| k3s | v1.29+ | Kubernetes cluster |
| kubectl | v1.29+ | Cluster CLI |
| Helm | v3.14+ | Package manager |
| Argo CD | v2.10+ | GitOps CD |
| Calico | v3.27+ | CNI / NetworkPolicy |
| Docker | v24+ | Image build |
| GitLab | Self-hosted | CI/CD + Registry |

### Accounts & Tokens

- ✅ GitLab Personal Access Token (Registry push 권한)
- ✅ (Optional) Slack Incoming Webhook URL
- ✅ TLS 인증서 (Self-signed 또는 발급)


> **Upload → Inference → Result** 최소 파이프라인을 빠르게 구동

### 1) k3s Cluster (VM-1 → VM-2/3 Join)

```bash
# VM-1 (Control Plane)
curl -sfL https://get.k3s.io | INSTALL_K3S_EXEC="server --cluster-init --flannel-backend=none --disable=traefik --tls-san=<VM1_IP>" sh -
sudo cat /var/lib/rancher/k3s/server/node-token
mkdir -p ~/.kube && sudo cp /etc/rancher/k3s/k3s.yaml ~/.kube/config && sudo chown $(id -u):$(id -g) ~/.kube/config

# VM-2, VM-3 (Worker)
curl -sfL https://get.k3s.io | K3S_URL="https://<VM1_IP>:6443" K3S_TOKEN="<NODE_TOKEN>" sh -
```

### 2) Calico CNI

```bash
kubectl apply -f https://raw.githubusercontent.com/projectcalico/calico/v3.27.0/manifests/calico.yaml
kubectl get nodes -w
```

### 3) AI Node Isolation (VM-3)

```bash
kubectl label nodes vm-3 node-role=ai-serving
kubectl taint nodes vm-3 ai-serving=true:NoSchedule
```

### 4) Namespaces

```bash
kubectl create ns infra
kubectl create ns storage
kubectl create ns ai-serving
kubectl create ns application
kubectl create ns monitoring
kubectl create ns gitops
```

### 5) Storage (MySQL / MinIO)

```bash
kubectl create secret generic mysql-secret -n storage \
  --from-literal=root-password=<ROOT_PW> \
  --from-literal=database=wafer_db \
  --from-literal=username=wafer_user \
  --from-literal=password=<USER_PW>

kubectl create secret generic minio-secret -n storage \
  --from-literal=root-user=minioadmin \
  --from-literal=root-password=<MINIO_PW>

helm install mysql ./helm-charts/mysql -n storage
helm install minio ./helm-charts/minio -n storage
kubectl get pods -n storage -w
```

### 6) Build & Deploy (FastAPI / Backend / Frontend)

```bash
# FastAPI (AI Serving)
cd fastapi-ai-serving
docker build -t <REGISTRY>/fastapi-ai:latest .
docker push <REGISTRY>/fastapi-ai:latest
helm install fastapi-ai ./helm-charts/fastapi-ai -n ai-serving

# Spring Boot (Backend)
cd ../wafer-backend
docker build -t <REGISTRY>/backend:latest .
docker push <REGISTRY>/backend:latest
helm install backend ./helm-charts/backend -n application

# React (Frontend)
cd ../frontend
docker build -t <REGISTRY>/frontend:latest .
docker push <REGISTRY>/frontend:latest
helm install frontend ./helm-charts/frontend -n application
```

### 7) Ingress (TLS + ingress-nginx)

```bash
openssl req -x509 -nodes -days 365 -newkey rsa:2048 \
  -keyout tls.key -out tls.crt -subj "/CN=wafer.local/O=wafer"

kubectl create secret tls wafer-tls -n infra --cert=tls.crt --key=tls.key

helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm install ingress-nginx ingress-nginx/ingress-nginx -n infra
```

### 8) Smoke Test

```bash
curl -k https://wafer.local/api/health
curl -k -X POST https://wafer.local/api/images -F "file=@sample_wafer.png" -F "source=MANUAL"
curl -k https://wafer.local/api/images?page=0&size=5
```

<br>

---


## API Spec

### Architecture Overview

| Layer | Service | Responsibility |
|-------|----------|----------------|
| AI Serving | ![FastAPI](https://img.shields.io/badge/FastAPI-009688?style=flat-square&logo=fastapi&logoColor=white) | CNN 9-class inference |
| Application | ![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white) | Upload, Orchestration, Statistics |
| Storage | ![MinIO](https://img.shields.io/badge/MinIO-C72E49?style=flat-square&logo=minio&logoColor=white) | Image Object Storage |
| Database | ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white) | Inference Result Persistence |
| Frontend | ![React](https://img.shields.io/badge/React-61DAFB?style=flat-square&logo=react&logoColor=black) | Dashboard UI |

---

### FastAPI (AI Serving - Internal Only)

![FastAPI](https://img.shields.io/badge/FastAPI-AI_Serving-009688?style=flat-square&logo=fastapi&logoColor=white)
![Internal Only](https://img.shields.io/badge/Access-Internal_Only-red?style=flat-square)

<br>

- Namespace: `ai-serving`  
- External Access: ❌ (Spring Boot only)

<br>

#### Endpoints

| Method | Path | Purpose |
|--------|------|----------|
| ![POST](https://img.shields.io/badge/POST-0052CC?style=flat-square&logoColor=white) | `/predict` | Run inference |
| ![GET](https://img.shields.io/badge/GET-28A745?style=flat-square&logoColor=white) | `/health` | Liveness probe |
| ![GET](https://img.shields.io/badge/GET-28A745?style=flat-square&logoColor=white) | `/health/ready` | Model readiness |
| ![GET](https://img.shields.io/badge/GET-28A745?style=flat-square&logoColor=white) | `/metrics` | Prometheus metrics |

<br>

#### POST `/predict`

<br>

#### Request
```json
{
  "imageUrl": "string",
  "imageId": "uuid",
  "requestId": "optional"
}
```

<br>

#### Response (200)
```json
{
  "prediction": "Edge-Ring",
  "confidence": 0.95,
  "modelVersion": "wafer-cnn-v1.0.0",
  "inferenceTimeMs": 127
}
```

<br>

#### Error Codes

| Code | Meaning |
|------|----------|
| ![400](https://img.shields.io/badge/400-Bad_Request-red?style=flat-square) | Invalid request |
| ![422](https://img.shields.io/badge/422-Unprocessable-yellow?style=flat-square) | Image processing failed |
| ![500](https://img.shields.io/badge/500-Internal_Error-darkred?style=flat-square) | Internal error |
| ![503](https://img.shields.io/badge/503-Service_Unavailable-orange?style=flat-square) | Model not ready |

---

### Spring Boot (Application Layer)

![Spring Boot](https://img.shields.io/badge/Spring_Boot-Application_Layer-6DB33F?style=flat-square&logo=springboot&logoColor=white)

<br>

- Base Path: `/api/*`

<br>

Core Endpoints

| Method | Path | Description |
|--------|------|-------------|
| ![POST](https://img.shields.io/badge/POST-0052CC?style=flat-square) | `/api/images` | Upload + Inference |
| ![GET](https://img.shields.io/badge/GET-28A745?style=flat-square) | `/api/images` | List results |
| ![GET](https://img.shields.io/badge/GET-28A745?style=flat-square) | `/api/images/{id}` | Detail |
| ![DELETE](https://img.shields.io/badge/DELETE-DC3545?style=flat-square) | `/api/images/{id}` | Delete record |
| ![GET](https://img.shields.io/badge/GET-28A745?style=flat-square) | `/api/images/stats` | Summary statistics |
| ![GET](https://img.shields.io/badge/GET-28A745?style=flat-square) | `/api/health` | Service health |

<br>

### POST `/api/images`

<br>

- Content-Type: `multipart/form-data`

<br>

#### Request Fields

| Field | Type | Required |
|-------|------|----------|
| file | MultipartFile | Yes |
| source | string | No |
| lotId | string | No |

<br>

#### Processing Flow

```
Validate → Upload(MinIO) → FastAPI → Save(MySQL)
→ WebSocket Push → Slack Alert (confidence ≥ 0.90)
```

<br>

#### Status Codes

| Code | Meaning |
|------|----------|
| ![201](https://img.shields.io/badge/201-Created-brightgreen?style=flat-square) | Success |
| ![400](https://img.shields.io/badge/400-Bad_Request-red?style=flat-square) | Invalid file |
| ![413](https://img.shields.io/badge/413-Payload_Too_Large-yellow?style=flat-square) | File too large |
| ![422](https://img.shields.io/badge/422-Unprocessable-yellow?style=flat-square) | Processing failed |
| ![502](https://img.shields.io/badge/502-Bad_Gateway-orange?style=flat-square) | AI response error |
| ![503](https://img.shields.io/badge/503-Service_Unavailable-orange?style=flat-square) | AI unavailable |

---

### GET `/api/images`

Supports pagination and filtering:

```
?page=0
&size=20
&prediction=Edge-Ring
&defectDetected=true
&startDate=ISO8601
```

---

### GET `/api/images/stats`

Returns:

- totalInspected  
- totalDefectDetected  
- defectRate  
- avgConfidence  
- avgInferenceTimeMs  

---

### WebSocket Events

![WebSocket](https://img.shields.io/badge/WebSocket-STOMP-blue?style=flat-square)

<br>

- Endpoint: `ws://<domain>/ws`  
- Protocol: STOMP over WebSocket  

<br>

| Topic | Description |
|--------|-------------|
| `/topic/predictions` | New inference result |
| `/topic/alerts` | High-confidence defect |
| `/topic/stats` | Statistics update |
| `/topic/system` | System events |

---

### Defect Classes (9-Class)

![9-Class](https://img.shields.io/badge/Defect-9_Class-6C757D?style=flat-square)

<br>

none · Center · Donut · Edge-Loc · Edge-Ring · Loc · Near-full · Random · Scratch

---

### Standard Error Format

```json
{
  "timestamp": "...",
  "status": 400,
  "code": "INVALID_FILE_FORMAT",
  "message": "...",
  "path": "/api/images",
  "requestId": "..."
}
```

---


## References
<br>
추가 예정
