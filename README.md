<!-- =============================== -->
<!--           HEADER               -->
<!-- =============================== -->

<h1 align="center">🧠 On-Premise AI Inference Infrastructure for Wafer Defect Classification</h1>

<p align="center">
반도체 웨이퍼 불량 분류 AI 서비스를 멀티노드 k3s 환경에서
안정적으로 운영하기 위한 GitOps 기반 인프라 설계 플랫폼
</p>

<p align="center">
<img src="https://img.shields.io/badge/Build-Passing-brightgreen?style=for-the-badge" />
<img src="https://img.shields.io/badge/Cluster-k3s-blue?style=for-the-badge" />
<img src="https://img.shields.io/badge/GitOps-ArgoCD-ef7b4d?style=for-the-badge" />
<img src="https://img.shields.io/badge/Architecture-Microservice-purple?style=for-the-badge" />
</p>

---


## 🚀 프로젝트 개요

> 웨이퍼 이미지를 업로드하면 AI 모델이 불량 유형을 자동 분류하고  
> 대시보드에서 실시간 결과를 확인할 수 있는 온프레미스 AI 플랫폼입니다.  
> 멀티노드 환경에서 서비스 분리 및 장애 격리를 고려해 설계되었습니다.


---

## 🖥️ 데모 화면

<br>

추가 예정

<br>


---

## 🧱 기술 스택

<p align="center">

<img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
<img src="https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB"/>
<img src="https://img.shields.io/badge/FastAPI-009688?style=for-the-badge&logo=fastapi&logoColor=white"/>
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>

<br>

<img src="https://img.shields.io/badge/k3s-FFC61C?style=for-the-badge&logo=kubernetes&logoColor=black"/>
<img src="https://img.shields.io/badge/Helm-0F1689?style=for-the-badge&logo=helm&logoColor=white"/>
<img src="https://img.shields.io/badge/ArgoCD-FE5D26?style=for-the-badge&logo=argo&logoColor=white"/>
<img src="https://img.shields.io/badge/MinIO-C72E49?style=for-the-badge&logo=minio&logoColor=white"/>

<br>

<img src="https://img.shields.io/badge/Prometheus-E6522C?style=for-the-badge&logo=prometheus&logoColor=white"/>
<img src="https://img.shields.io/badge/Grafana-F46800?style=for-the-badge&logo=grafana&logoColor=white"/>

</p>

---

## 🏗️ 아키텍처

### 📡 시스템 아키텍처

<img width="1584" height="1063" alt="image" src="https://github.com/user-attachments/assets/96727804-3bd2-48c1-baa9-d8f516e42605" />


<br>


### 🔄 애플리케이션 흐름

<img width="1980" height="998" alt="image" src="https://github.com/user-attachments/assets/e75e5d05-6183-46eb-8942-942e7a19f3bb" />


<br>

---

## ⚡ 핵심 설계 포인트

- 🔐 AI 서버는 외부 노출 없이 Backend를 통해서만 접근
- 🧩 멀티노드 환경에서 서비스 역할 분리
- 🔄 GitOps 기반 자동 배포
- 📈 확장 가능한 구조(HPA 고려)

---

## 📚 문서 안내

| 문서 | 바로가기 |
|------|----------|
| 📖 Wiki 전체 문서 | [Wiki 이동](../../wiki) |
| 🔗 API 명세 | [API 명세 보기](../../wiki/기술-문서/API-명세) |
| 🗂 데이터 모델 및 ERD | [데이터 모델 보기](../../wiki/기술-문서/데이터-모델-및-ERD) |
| 🛠 트러블슈팅 | [트러블슈팅 보기](../../wiki/기술-문서/트러블슈팅) |
| 🚀 설치 가이드 | [설치 가이드 보기](../../wiki/프로젝트/설치-가이드) |

