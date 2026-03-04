# ---------- build stage ----------
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /workspace

# 캐시 효율: gradle 파일 먼저
COPY gradlew settings.gradle build.gradle ./
COPY gradle ./gradle

# 소스 복사
COPY src ./src

# Gradle wrapper 실행 권한 (mac에서 복붙하면 권한 빠지는 경우 많음)
RUN chmod +x ./gradlew

# 테스트는 일단 스킵(원하면 나중에 켜기)
RUN ./gradlew clean bootJar -x test

# ---------- runtime stage ----------
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# 보안: non-root 유저
RUN useradd -m appuser
USER appuser

# jar 복사
COPY --from=build /workspace/build/libs/*.jar app.jar

EXPOSE 8080

# 기본 프로파일/옵션은 환경변수로 override 가능
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0"
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]