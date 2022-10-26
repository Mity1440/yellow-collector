FROM bellsoft/liberica-openjdk-centos:17
ENV PROJECT_ROOT /usr/src/app
WORKDIR $PROJECT_ROOT
EXPOSE 8090
COPY ./general-requester.yaml ./config/requester.yaml
COPY ./general-requester.jar general-requester.jar
CMD java -Xmx2g -Xms2g -jar general-requester.jar --spring.config.name=requester
