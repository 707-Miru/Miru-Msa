1. 도커 설치(docker -v) brew cask install docker
2. 깃허브 로컬에 클론
3. 도커 파일 생성 FROM python:3.7-slim COPY ./requirements.txt ./ RUN pip install -r ./requirements.txt
4. requirements.txt 생성 docker images
5. 파이참에서 도커로 실행

docker exec -it contenier-id bash

======
Jenkins
CI/CD 란?
- CI(Continuous Integration, 지속적인 통합): 지속적인 빌드와 테스트 자동화를 의미합니다.
- CD(Continuous Delivery, 지속적인 서비스 제공 또는 지속적인 배포) : 배포 자동화를 의미합니다.

Jenkins 란?
자바 기반 웹 애플리케이션으로 CI/CD을 위한 도구입니다. 즉 빌드, 테스트, 배포를 자동화해 줍니다.

1. Docker 설치

https://docs.docker.com/get-docker/


2. Docker를 통한 Jenkins 설치
docker run -d -u root -p 9090:8080 --name=jenkins -v c:/Users/[userName]/dist:/var/jenkins_home/dist jenkins/jenkins

에러 발생
docker jenkins 설치 오류
error during connect : In the default daemon configuration on Windows, the docker client must be run elevated to connect...
=> wsl2 설치하니까 해결되었다.
일반 cmd 환경에서는 안 됨


3. Jenkins - Gitlab 연결
1) Gitlab Token 발급
Gitlab -> Access Token 발급받고 기억할 수 있는 장소에 기록 해 놓는다.


2) Jenkins 플러그인 설치
DashBoard > Manager Jenkins > Plugin Manager


NodeJS, Blue Ocean, Gitlab에 관련된 것들은 다 설치를 해준다


4. Jenkins에서 gitlab 연동
DashBoard > Manager Jenkins > configure System


Connection name, URL 적절히 작성
Credentials은 GitLab API token -> Add 버튼 클릭

Jenkins 선택



위와 같이 발급받은 Token을 넣어준다

Kind -> GitLab API Token
API token -> 발급받은 Token
Id -> Gitlab 로그인 아이디
Add 클릭


Test Connection 클릭 후 Success 뜨면 성공

맨 하단의 Apply후 저장 클릭하면 Gitlab 연결 완료



5. Nginx 설정
Nginx 정적 웹서버 설치 - Docker
배포될 폴더 dist 생성

mkdir dist
Nginx 설치

docker run --name nginx -d -p 80:80 -v c:/Users/[userName]/dist:/usr/share/nginx/html nginx

6. Pipeline 배포
1) Pipeline 생성


이름 적당히 넣고, Pipeline 선택 후 OK 클릭

Gitlab에서 Jenkins에 트리거를 전송하기 위해서는 Secret Token이 필요하다

Build Trigger -> Build when a change is pushed to GitLab.

고급 버튼 클릭

Generate 클릭하면 Secret Token 생성


2) Gitlab Trigger 생성

Settings > Webhooks

url에 jenkins 작업 item URL 입력
발급받은 Secret Token도 입력


오류 발생
Url is blocked: Requests to localhost are not allowed 발생

localhost라서 보안상 거부
ngrok 를 다운받은후
https://ngrok.com/download
ngrok http 9090 실행
포워딩 주소로 웹혹 걸기


7. Pipeline 작성
구성 -> Pipeline
Definition -> Pipeline script from SCM


Script Path : Jenkinsfile 설정 후
Jenkinsfile 만들어 Gitlab에 푸시

Jenkinsfile은 다음과 같이 일단 기본형태로 구성

pipeline {
    agent any

    stages {
        stage('Hello') {
            steps {
                echo 'Hello World'
            }
        }
    }
}

빌드 성공