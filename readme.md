1. 도커 설치(docker -v) brew cask install docker
2. 깃허브 로컬에 클론
3. 도커 파일 생성 FROM python:3.7-slim COPY ./requirements.txt ./ RUN pip install -r ./requirements.txt
4. requirements.txt 생성 docker images
5. 파이참에서 도커로 실행

docker exec -it contenier-id bash
