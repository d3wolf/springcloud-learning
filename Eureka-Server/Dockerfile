FROM java:8

ENV ROOT_PATH /home/work/data/www
ENV PROJECT_NAME {{service}}

RUN mkdir -p ROOT_PATH/$PROJECT_NAME \
&& /bin/chown -R work:work /home/work

COPY . $ROOT_PATH/$PROJECT_NAME

EXPOSE 8080

WORKDIR /home/work/data/www/{{service}}

CMD ["java", "-jar", "-Dspring.profiles.active={{env}}", "ROOT.jar"]



# 该镜像需要依赖的基础镜像
FROM java:8
# 将当前目录下的jar包复制到docker容器的/目录下
ADD mall-tiny-docker-file-0.0.1-SNAPSHOT.jar /mall-tiny-docker-file.jar
# 运行过程中创建一个mall-tiny-docker-file.jar文件
RUN bash -c 'touch /mall-tiny-docker-file.jar'
# 声明服务运行在8080端口
EXPOSE 8080
# 指定docker容器启动时运行jar包
ENTRYPOINT ["java", "-jar","/mall-tiny-docker-file.jar"]