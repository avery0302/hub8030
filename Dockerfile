# 使用基础镜像
FROM openjdk:17-jdk-alpine

# 设置工作目录
WORKDIR /app

# 将 jar 文件复制到容器中
COPY target/hub3010-0.0.1-SNAPSHOT.jar /app/app.jar

# 暴露应用程序的端口
EXPOSE 8030

# 启动 Spring Boot 应用
ENTRYPOINT ["java", "-jar", "app.jar"]