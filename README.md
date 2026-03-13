<!-- =============================== -->
<!--           HEADER               -->
<!-- =============================== -->

<h1 align="center"> On-Premise Deep Learning Inference Infrastructure for Wafer Defect Classification</h1>


<br>
<p align="center">
<img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
<img src="https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB"/>
<img src="https://img.shields.io/badge/FastAPI-009688?style=for-the-badge&logo=fastapi&logoColor=white"/>
<img src="https://img.shields.io/badge/TensorFlow-FF6F00?style=for-the-badge&logo=tensorflow&logoColor=white"/>
<img src="https://img.shields.io/badge/k3s-FFC61C?style=for-the-badge&logo=kubernetes&logoColor=black"/>
<img src="https://img.shields.io/badge/Helm-0F1689?style=for-the-badge&logo=helm&logoColor=white"/>
<br>
<img src="https://img.shields.io/badge/ArgoCD-FE5D26?style=for-the-badge&logo=argo&logoColor=white"/>
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>
<img src="https://img.shields.io/badge/MinIO-C72E49?style=for-the-badge&logo=minio&logoColor=white"/>
<img src="https://img.shields.io/badge/GitLabCI-FCA121?style=for-the-badge&logo=gitlab&logoColor=white"/>
<img src="https://img.shields.io/badge/Prometheus-E6522C?style=for-the-badge&logo=prometheus&logoColor=white"/>
<img src="https://img.shields.io/badge/Grafana-F46800?style=for-the-badge&logo=grafana&logoColor=white"/>
</p>

---

## ▍프로젝트 개요

> 반도체 웨이퍼 이미지를 업로드하면, <br>
> 딥러닝 기반 이미지 분류 모델이 **9가지 불량 유형을 자동 분류**하고  
> 결과를 저장 및 시각화하는 추론 서비스입니다.
>
> 해당 서비스를 **멀티노드 k3s 기반 온프레미스 환경에서 안정적으로 운영**하기 위해  
> GitOps 중심 인프라 아키텍처로 설계되었습니다.

---

## ▍핵심 기능

### 1. 🤖 딥러닝 모델 기반 웨이퍼 결함 자동 분류
WM-811K 데이터셋 기반으로 **9종 웨이퍼 패턴 결함 분류**를 수행합니다.

| none | scratch | random |
|------|---------|--------|
| <img src="https://github.com/user-attachments/assets/c031f298-ae7f-4d50-9ed4-65650c780f91" width="250"/> | <img src="https://github.com/user-attachments/assets/acacd5a2-510b-4341-a6ff-5562c0f581e6" width="250"/> | <img src="https://github.com/user-attachments/assets/be2e48a0-634f-4f22-ab43-d936e4950c7e" width="250"/> |

| loc | donut | near-full |
|-----|-------|-----------|
| <img src="https://github.com/user-attachments/assets/51864da6-016b-4b4d-8809-b110a397dd8b" width="250"/> | <img src="https://github.com/user-attachments/assets/ab4940bb-7843-42af-832d-856b4e518898" width="250"/> | <img src="https://github.com/user-attachments/assets/7a04f608-c9c7-4881-90e6-b4732247d4e5" width="250"/> |

| center | edge-loc | edge-ring |
|--------|----------|-----------|
| <img src="https://github.com/user-attachments/assets/1b73faca-8462-4dcb-a312-44ef1c2af535" width="250"/> | <img src="https://github.com/user-attachments/assets/e6b8d7f2-a9e3-4110-ab03-3c56e304ac5f" width="250"/> | <img src="https://github.com/user-attachments/assets/d1a2fe6c-3042-494b-9fba-d28902837c8d" width="250"/> |

<br>

### 2. 🖥 통합 운영 대시보드

시스템 전체 분석 현황을 한눈에 확인할 수 있는 **통합 관제 화면**을 제공합니다.  
총 검사 수, 결함 수, 정상 수, 평균 신뢰도 등 핵심 KPI를 실시간으로 확인할 수 있습니다.

<p align="center">
  <img src="https://github.com/user-attachments/assets/528a50c3-a73c-42ac-980a-80a9237098c5" width="720"/>
</p>

### 3. 📊 실시간 분석 대시보드
KPI 카드, 결함 분포, 일별 추이 차트를 통해 분석 현황을 실시간으로 확인할 수 있습니다.

<p align="center">
  <img src="https://github.com/user-attachments/assets/70e48e28-f20f-4eac-9b41-fd6dc42535db" width="520"/>
</p>

### 4. 📦 이력 관리 및 CSV 내보내기
검색, 페이징, 날짜·결함 유형 필터를 지원하며 분석 이력을 CSV로 내보낼 수 있습니다.

<p align="center">
  <img src="https://github.com/user-attachments/assets/e2cd6dae-b5f5-4023-a170-146092f67a2c" width="520"/>
</p>



### 5. 🔍 통합 모니터링
Prometheus + Grafana 기반으로 **클러스터 상태, 애플리케이션 메트릭, 운영 로그**를 통합적으로 모니터링합니다.

| 구분 | 화면 |
|------|------|
| **클러스터 모니터링** | <img src="https://github.com/user-attachments/assets/43bc87e9-7dd0-4e7c-bb94-7313614ac96e" width="520"/> |
| **애플리케이션 메트릭** | <img src="https://github.com/user-attachments/assets/b75f66ed-9697-4a8e-b895-7796306a32a1" width="400"/> |
| **로그 모니터링** | <img src="https://github.com/user-attachments/assets/0bd09915-aea4-4b8e-8d68-d66227ddab53" width="520"/> |



---
## ▍문서 안내

<br>

| 문서 | 바로가기 |
|------|----------|
| 📖 Wiki 전체 문서 | [Wiki 이동](https://github.com/msp-architect-2026/kim-minji/wiki) |
| 📋 칸반보드 | [칸반보드 이동](https://github.com/orgs/msp-architect-2026/projects/41) |
| 🖥️ 개발 환경 | [개발 환경 보기](https://github.com/msp-architect-2026/kim-minji/wiki/Development-Environment) |
| 🔗 API 명세 | [API 명세 보기](https://github.com/msp-architect-2026/kim-minji-wiki/wiki/API-Specification-%E2%80%90-final) |
| 🗂 데이터 모델 및 ERD | [데이터 모델 보기](https://github.com/msp-architect-2026/kim-minji-wiki/wiki/Data-Model-and-ERD-%E2%80%90-after) |
| 🛠 트러블슈팅 | [트러블슈팅 보기](https://github.com/msp-architect-2026/kim-minji/wiki/Troubleshooting) |



---



## ▍Repository Structure

본 프로젝트는 **서비스별 역할을 명확히 분리한 멀티 레포 구조**로 구성되어 있습니다.

| Repository | 설명 |
|------------|------|
| 🧱 [kim-minji-infra](https://github.com/msp-architect-2026/kim-minji-infra) | k3s 클러스터 및 GitOps 인프라 구성 |
| 🖥 [kim-minji-frontend](https://github.com/msp-architect-2026/kim-minji-frontend) | React 기반 웹 대시보드 |
| ⚙️ [kim-minji-backend](https://github.com/msp-architect-2026/kim-minji-backend) | Spring Boot API 서버 |
| 🧠 [kim-minji-ai](https://github.com/msp-architect-2026/kim-minji-ai) | FastAPI 기반 AI 추론 서비스 |
| ⛵ [kim-minji-helm](https://github.com/msp-architect-2026/kim-minji-helm) | Kubernetes Helm Chart |





---

## ▍아키텍처

### 시스템 아키텍처

<img width="1581" height="834" alt="시스템 아키텍처" src="https://github.com/user-attachments/assets/6b511c56-7154-4a21-be4c-bfb2aadc49b3" />

<br>
<br>

### 애플리케이션 흐름

<img width="1980" height="998" alt="소프트웨어 아키텍처" src="https://github.com/user-attachments/assets/3fa4ec55-b050-4478-b43d-045c1625759d" />

---

## ▍AI Model Integration (Self-Hosted Inference)

> 본 시스템은 외부 API(OpenAI, Cloud Vision 등)에 의존하지 않고, **클러스터 내부에서 직접 딥러닝 모델을 서빙**합니다. <br> 이를 통해 공정 데이터의 외부 유출을 원천 차단하고 오프라인 환경에서도 안정적인 추론을 보장합니다.

<p align="center">
  <img src="https://github.com/user-attachments/assets/e37f679b-106c-47f8-a7c7-8876c58e53a6" width="80%" />
</p>

- **내장형 추론 엔진**: CNN 기반 웨이퍼 결함 분류 모델을 FastAPI 컨테이너에 직접 로드하여 실행
- **데이터 보안성**: 모든 이미지 처리 및 분석이 클러스터 내부(On-Premise)에서 완결되어 외부 통신 발생 안 함
- **최적화된 아키텍처**: Apple Silicon(ARM64) 환경에 최적화된 TensorFlow/Keras 모델 포맷(H5) 사용
- **고성능 비동기 처리**: FastAPI와 비동기 추론 로직을 결합하여 초저지연 결과 반환 (Avg. < 200ms)

---

## ▍핵심 설계 포인트

- **워크로드 격리**: nodeSelector로 AI 추론 전용 노드(k3s-ai2) 고정 배치
- **자가 치유 & 고가용성**: Liveness/Readiness Probe + HPA 기반 동적 스케일링 (최대 4 replicas)
- **Zero-Trust 보안 구조**: Sealed Secrets + Calico NetworkPolicy 기반 네임스페이스 간 통신 최소화
- **장애 전파 방지**: Resilience4j Circuit Breaker + 타임아웃 10s Fast Fail 전략 적용
- **Cold Start 개선**: FastAPI lifespan 기반 모델 preload + Readiness Probe 강화로 미준비 pod 트래픽 차단

---

## ▍하드웨어 토폴로지

<br>

| 노드 | IP | 역할 | 주요 워크로드 |
|------|-----|------|----------------|
| k3s-cp1 (Control Plane) | 192.168.0.57 | 클러스터 관리 | <img src="https://img.shields.io/badge/k3s-FFC61C?style=flat-square&logo=kubernetes&logoColor=black"/> <img src="https://img.shields.io/badge/ArgoCD-FE5D26?style=flat-square&logo=argo&logoColor=white"/> <img src="https://img.shields.io/badge/Nginx%20Ingress-009639?style=flat-square&logo=nginx&logoColor=white"/> |
| k3s-w1 (Worker) | 192.168.0.157 | 애플리케이션 & 스토리지 | <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=springboot&logoColor=white"/> <img src="https://img.shields.io/badge/React-20232A?style=flat-square&logo=react&logoColor=61DAFB"/> <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white"/> <img src="https://img.shields.io/badge/MinIO-C72E49?style=flat-square&logo=minio&logoColor=white"/> <img src="https://img.shields.io/badge/Prometheus-E6522C?style=flat-square&logo=prometheus&logoColor=white"/> <img src="https://img.shields.io/badge/Grafana-F46800?style=flat-square&logo=grafana&logoColor=white"/> |
| k3s-ai2 (AI Worker) | 192.168.0.240 | AI 추론 전용 | <img src="https://img.shields.io/badge/FastAPI-009688?style=flat-square&logo=fastapi&logoColor=white"/> |

---

## ▍네임스페이스 구성

| 네임스페이스 | 워크로드 |
|-------------|---------|
| `application` | frontend (React), backend (Spring Boot) |
| `ai-serving` | ai-serving (FastAPI), HPA |
| `storage` | MySQL, MinIO |
| `infra` | Nginx Ingress Controller |
| `gitops` | ArgoCD |
| `monitoring` | Prometheus, Grafana, Loki, Promtail |

---

## ▍Quick Start

전체 설치 및 검증 절차는 [설치 가이드](https://github.com/msp-architect-2026/kim-minji/wiki/Installation-Guide)에 상세히 정리되어 있습니다. 아래는 빠른 배포를 위한 요약 명령어입니다.

> ⚠️ **사전 조건**: k3s 설치 시 Flannel 비활성화 + Calico CNI 구성이 필요합니다. 자세한 내용은 설치 가이드를 참고하세요.

```bash
# 1. Infrastructure Repository Clone
$ git clone https://github.com/msp-architect-2026/kim-minji-infra.git
$ cd kim-minji-infra

# 2. k3s 클러스터 준비 (Control Plane 기준, Calico CNI 사용)
$ curl -sfL https://get.k3s.io | sh -s - server \
  --node-ip=192.168.0.57 \
  --cluster-cidr=10.42.0.0/16 \
  --service-cidr=10.43.0.0/16 \
  --disable=traefik \
  --flannel-backend=none \
  --disable-network-policy
$ kubectl get nodes

# 3. ArgoCD 설치
$ kubectl create namespace gitops
$ kubectl apply -n gitops \
  -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml
$ kubectl get pods -n gitops

# 4. GitOps Application 배포
$ kubectl apply -f argocd/application.yaml
$ kubectl get applications -n gitops
$ kubectl get pods -A

# 5. 서비스 접속 (맥북 /etc/hosts에 아래 항목 추가 필요)
# 192.168.0.57  app.wafer.local api.wafer.local ai.wafer.local grafana.wafer.local
# 192.168.0.157 gitlab.local

Frontend : http://app.wafer.local:32088
Backend  : http://api.wafer.local:32088
AI API   : http://ai.wafer.local:32088
Grafana  : http://grafana.wafer.local:32088
```

<br>
