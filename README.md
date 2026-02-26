<!-- =============================== -->
<!--           HEADER               -->
<!-- =============================== -->

<h1 align="center"> On-Premise Deep Learning Inference Infrastructure for Wafer Defect Classification</h1>

<p align="center">
반도체 웨이퍼 불량 분류를 위한 딥러닝 추론 서비스를
멀티노드 k3s 환경에서 안정적으로 운영하기 위한
GitOps 기반 인프라 설계 플랫폼
</p>

<p align="center">

<img src="https://img.shields.io/badge/Domain-Semiconductor-blueviolet?style=for-the-badge" />
<img src="https://img.shields.io/badge/AI-9%20Defect%20Classes-success?style=for-the-badge" />
<img src="https://img.shields.io/badge/Cluster-k3s-blue?style=for-the-badge" />
<img src="https://img.shields.io/badge/GitOps-ArgoCD-orange?style=for-the-badge" />
<img src="https://img.shields.io/badge/Architecture-Microservice-purple?style=for-the-badge" />
<img src="https://img.shields.io/badge/Deployment-Helm-informational?style=for-the-badge" />
<img src="https://img.shields.io/badge/Observability-Prometheus%20%7C%20Grafana-red?style=for-the-badge" />
<img src="https://img.shields.io/badge/Security-Zero%20Trust-critical?style=for-the-badge" />

</p>

---

## 🚀 프로젝트 개요

> 반도체 웨이퍼 이미지를 업로드하면, <br>
> 딥러닝 기반 이미지 분류 모델이 **9가지 불량 유형을 자동 분류**하고  
> 결과를 저장 및 시각화하는 추론 서비스입니다.
> 
> 해당 서비스를 **멀티노드 k3s 기반 온프레미스 환경에서 안정적으로 운영**하기 위해  
> GitOps 중심 인프라 아키텍처로 설계되었습니다.

<br>
(데모 스크린샷 추가 예정 1.사용자 업로드 화면  2.딥러닝 추론 결과  3.Grafana 모니터링)
<br>
<br>

---

## 🧠 핵심 기능

<br>

- 📷 웨이퍼 이미지 업로드 및 저장
- 🤖 딥러닝 모델 기반 9종 불량 자동 분류
- 📊 추론 결과 대시보드 시각화
- 📦 추론 이력 저장 및 조회 API 제공




<br>

---

## 🧱 기술 스택
<br>

### 🖥 Backend
<p>
<img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>
</p>

### 🎨 Frontend
<p>
<img src="https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB"/>
</p>

### 🤖 AI Serving
<p>
<img src="https://img.shields.io/badge/FastAPI-009688?style=for-the-badge&logo=fastapi&logoColor=white"/>
</p>

### ☁ Infrastructure & GitOps
<p>
<img src="https://img.shields.io/badge/k3s-FFC61C?style=for-the-badge&logo=kubernetes&logoColor=black"/>
<img src="https://img.shields.io/badge/Helm-0F1689?style=for-the-badge&logo=helm&logoColor=white"/>
<img src="https://img.shields.io/badge/ArgoCD-FE5D26?style=for-the-badge&logo=argo&logoColor=white"/>
<img src="https://img.shields.io/badge/MinIO-C72E49?style=for-the-badge&logo=minio&logoColor=white"/>
<img src="https://img.shields.io/badge/GitLab%20CI-FCA121?style=for-the-badge&logo=gitlab&logoColor=white"/>
</p>

### 📊 Observability
<p>
<img src="https://img.shields.io/badge/Prometheus-E6522C?style=for-the-badge&logo=prometheus&logoColor=white"/>
<img src="https://img.shields.io/badge/Grafana-F46800?style=for-the-badge&logo=grafana&logoColor=white"/>
</p>

### 🔐 Reliability & Security
<p>
<img src="https://img.shields.io/badge/Resilience4j-Circuit%20Breaker-yellow?style=for-the-badge"/>
<img src="https://img.shields.io/badge/Sealed%20Secrets-Encrypted-blue?style=for-the-badge"/>
</p>

<br>

---

## 🛠️ AI Model Integration (Self-Hosted Inference)

> 본 시스템은 외부 API(OpenAI, Cloud Vision 등)에 의존하지 않고, **클러스터 내부에서 직접 딥러닝 모델을 서빙**합니다. <br> 이를 통해 공정 데이터의 외부 유출을 원천 차단하고 오프라인 환경에서도 안정적인 추론을 보장합니다. 

<br>

- **내장형 추론 엔진**: CNN 기반 웨이퍼 결함 분류 모델을 FastAPI 컨테이너에 직접 로드하여 실행 
- **데이터 보안성**: 모든 이미지 처리 및 분석이 클러스터 내부(On-Premise)에서 완결되어 외부 통신 발생 안 함 
- **최적화된 아키텍처**: Apple Silicon(ARM64) 환경에 최적화된 TensorFlow/Keras 모델 포맷(H5) 사용 
- **고성능 비동기 처리**: FastAPI와 비동기 추론 로직을 결합하여 초저지연 결과 반환 (Avg. < 200ms) 

<br>

---

## ⚡ 핵심 설계 포인트

<br>

- 🎯 **워크로드 격리**: Node Affinity + Taints/Tolerations로 AI 추론 전용 노드 배치
- 🩺 **자가 치유 & 고가용성**: Liveness/Readiness Probe + HPA 기반 동적 스케일링
- 🔐 **Zero-Trust 보안 구조**: Sealed Secrets + NetworkPolicy 기반 통신 최소화
- 🧯 **장애 전파 방지**: Resilience4j Circuit Breaker + Fallback 전략 적용

<br>

---


## 🧩 하드웨어 토폴로지

<br>

| 노드 유형 | 역할 | 주요 워크로드 |
|------------|------------|----------------|
| Control Plane | 클러스터 관리 및 메인 애플리케이션 | <img src="https://img.shields.io/badge/k3s-FFC61C?style=flat-square&logo=kubernetes&logoColor=black"/> <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=springboot&logoColor=white"/> <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white"/> |
| Worker Node | 스토리지 및 관측 | <img src="https://img.shields.io/badge/MinIO-C72E49?style=flat-square&logo=minio&logoColor=white"/> <img src="https://img.shields.io/badge/Prometheus-E6522C?style=flat-square&logo=prometheus&logoColor=white"/> <img src="https://img.shields.io/badge/Grafana-F46800?style=flat-square&logo=grafana&logoColor=white"/> |
| AI Worker | AI 추론 전용 노드 | <img src="https://img.shields.io/badge/FastAPI-009688?style=flat-square&logo=fastapi&logoColor=white"/> <img src="https://img.shields.io/badge/Taints%20%26%20Tolerations-Isolated-orange?style=flat-square"/> |




📖 상세 하드웨어 사양 및 리소스 구성은 **[개발 환경](https://github.com/msp-architect-2026/kim-minji/wiki/%EA%B0%9C%EB%B0%9C-%ED%99%98%EA%B2%BD)** 에 정리되어 있습니다.

<br>

---

## 🏗️ 아키텍처

### 📡 시스템 아키텍처

<img width="1584" height="1063" src="https://github.com/user-attachments/assets/96727804-3bd2-48c1-baa9-d8f516e42605" />

<br>
<br>

### 🔄 애플리케이션 흐름

<img width="1980" height="998" src="https://github.com/user-attachments/assets/e75e5d05-6183-46eb-8942-942e7a19f3bb" />

---

## 📚 문서 안내

<br>

| 문서 | 바로가기 |
|------|----------|
| 📖 Wiki 전체 문서 | [Wiki 이동](https://github.com/msp-architect-2026/kim-minji/wiki) |
| 🔗 API 명세 | [API 명세 보기](https://github.com/msp-architect-2026/kim-minji/wiki/API-%EB%AA%85%EC%84%B8) |
| 🗂 데이터 모델 및 ERD | [데이터 모델 보기](https://github.com/msp-architect-2026/kim-minji/wiki/%EB%8D%B0%EC%9D%B4%ED%84%B0-%EB%AA%A8%EB%8D%B8-%EB%B0%8F-ERD) |
| 🛠 트러블슈팅 | [트러블슈팅 보기](https://github.com/msp-architect-2026/kim-minji/wiki/%ED%8A%B8%EB%9F%AC%EB%B8%94%EC%8A%88%ED%8C%85) |

<br>

---

## ⚙ Quick Start
<br>

```bash
# 1. k3s 클러스터 준비
# 2. ArgoCD 애플리케이션 등록
# 3. Helm 기반 자동 배포
```



📖 전체 설치 및 검증 절차는 **[설치 가이드](https://github.com/msp-architect-2026/kim-minji/wiki/%EC%84%A4%EC%B9%98-%EA%B0%80%EC%9D%B4%EB%93%9C)** 에 정리되어 있습니다.

<br>
