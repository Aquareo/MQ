# 在 Codespace 中使用 Docker 操作 MySQL

本说明文件将指导您如何在 GitHub Codespace 中使用 Docker 启动和操作 MySQL 数据库，并在 Spring Boot 项目中配置连接。

---

## 1. 启动 MySQL 容器

在终端中运行以下命令以启动 MySQL 容器：

```bash
docker run --name mysql -e MYSQL_ROOT_PASSWORD=你的密码 -p 3306:3306 -d mysql:latest
```

- `--name mysql`：为容器命名为 `mysql`。
- `-e MYSQL_ROOT_PASSWORD=你的密码`：设置 MySQL 的 root 用户密码。
- `-p 3306:3306`：将主机的 3306 端口映射到容器的 3306 端口。
- `-d mysql:latest`：后台运行最新版本的 MySQL。

---

## 2. 检查容器状态

运行以下命令查看 MySQL 容器是否正常运行：

```bash
docker ps
```

输出示例：
```
CONTAINER ID   IMAGE          COMMAND                  CREATED          STATUS          PORTS                   NAMES
c0782dfc9719   mysql:latest   "docker-entrypoint.s…"   10 seconds ago   Up 9 seconds    0.0.0.0:3306->3306/tcp  mysql
```

---

## 3. 登录 MySQL

使用以下命令登录到 MySQL：

```bash
docker exec -it mysql mysql -u root -p
```

系统会提示输入密码，输入您在启动容器时设置的 `MYSQL_ROOT_PASSWORD`。

---

## 4. 创建数据库

登录 MySQL 后，可以创建一个新的数据库：

```sql
CREATE DATABASE springboot_example;
```

查看所有数据库：
```sql
SHOW DATABASES;
```

选择数据库：
```sql
USE springboot_example;
```

---

## 5. 创建表

根据您的实体类 `User`，创建一个对应的表：

```sql
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);
```

查看表结构：
```sql
DESCRIBE user;
```

---

## 6. 配置 Spring Boot 连接 MySQL

在 Spring Boot 项目中，您需要在 `application.properties` 或 `application.yml` 文件中配置数据库连接信息。

### 使用 `application.properties`

在 `src/main/resources/application.properties` 文件中添加以下内容：

```properties
# 数据库连接配置
spring.datasource.url=jdbc:mysql://localhost:3306/springboot_example?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=你的密码

# JDBC 驱动
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# MyBatis 配置
mybatis.configuration.map-underscore-to-camel-case=true
```

### 使用 `application.yml`

在 `src/main/resources/application.yml` 文件中添加以下内容：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/springboot_example?useSSL=false&serverTimezone=UTC
    username: root
    password: 你的密码
    driver-class-name: com.mysql.cj.jdbc.Driver

mybatis:
  configuration:
    map-underscore-to-camel-case: true
```

---

## 7. 使用 MyBatis 操作数据库

在 Spring Boot 项目中，您可以通过 MyBatis 的 `UserMapper` 接口操作数据库。例如：

```java
@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user(username, password) VALUES(#{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUserById(int id);
}
```

---

## 8. 停止和删除容器

如果需要停止 MySQL 容器，可以运行以下命令：

```bash
docker stop mysql
```

如果需要删除容器：

```bash
docker rm mysql
```

---

## 9. 重新启动容器

如果容器已存在但未运行，可以通过以下命令重新启动：

```bash
docker start mysql
```

---

## 10. 查看容器日志

如果遇到问题，可以查看容器日志以排查错误：

```bash
docker logs mysql
```

---

通过以上步骤，您可以在 Codespace 中使用 Docker 操作 MySQL 数据库，并在 Spring Boot 项目中成功连接到数据库。如果有其他问题，请随时联系开发团队。