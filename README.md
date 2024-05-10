# Getting Started

---

### Spring Stream Upload


#### 기본 파일업로드
    path = /basic

#### 파일 분할 업로드
    path = /chunkBasic

#### 파일 이어서 업로드
    path = /chunk

#### 파일 분할 업로드 tus protocol

    https://tus.io/
    https://tus.io/protocols/resumable-upload

ref) https://github.com/terrischwartz/tus_servlet
https://github.com/ckaratzas/tus-server-implementation
https://github.com/tomdesair/tus-java-server
클라이언트 라이브러리) https://github.com/tus/tus-js-client

#### 썸네일, 영상 길이 추출

    extractor 패키지 내용을 추가함 
    implementation 'org.jcodec:jcodec-javase:0.2.5'

#### FFmpeg 설치

    https://ffmpeg.org/

    implementation 'net.bramp.ffmpeg:ffmpeg:0.7.0'

    ex) mac os에서 설치하는 방법

        brew install ffmpeg

        ffmpeg가 설치된 위치를 확인

        which ffmpeg

#### 스트리밍

#### HLS protocol
 * HTTP Live Streaming (HLS)는 Apple에서 개발한 스트리밍 프로토콜로, 인터넷에서 비디오를 스트리밍하기 위해 사용된다.
 * HLS는 HTTP기반의 스트리밍 프로토콜 이다.
 * HLS는 비디오를 작은 청크로 나누어 각 청크를 개별적으로 다운로드하고 재생한다.첫 번째 청크를 다운로드하고, 다음 청크를 다운로드하기 시작하기 전에 현재 재생 중인 비디오의 버퍼링을 계속 유지하여 지연 없이 실시간으로 비디오를 제공한다.
 * HLS를 재생하기위한 MIME 타입은 application/x-mpegURL 이다.
 * Apple에서 만든 프로토콜 이라서 safari 브라우저에서는 기본으로 동작한다.
 * HTML의 기본 video태그에서는 재생이 불가능 하다.
 * 크롬브라우저도 기본적으로 동작하지 않는다.   
 * m3u8
    - m3u8 파일은 HLS(HTTP Live Streaming) 방식으로 인코딩된 비디오나 오디오를 재생하기 위한 플레이리스트 파일이다.
      각 청크된 데이터 재생하기 위한 메타데이터가 저장되어있다.
    - TS 파일은 MPEG transport stream 포맷으로 인코딩된 청크 파일이다.
      TS 파일은 보통 2-10초 정도의 길이로 자르고, 비디오 및 오디오 데이터를 포함한다.
      TS 파일은 m3u8 파일을 통해 클라이언트에게 제공, 클라이언트는 이 파일들을 다운로드하고 병합하여 비디오를 재생한다. 
 

    todo-list) executor.createJob(builder).run();
               progress를 사용시에 time 오류가 발생한다. 

#### 자동화질조절


#### 라이브 스트리밍
      dockfile 폴더에서 Dockfile 사용하여 빌드한다.
      docker build -t nginx-rtmp .
      docker run -d -p 1935:1935 -p 8080:8080 --name nginx-rtmp nginx-rtmp 





ref) https://velog.io/@haerong22/%EC%98%81%EC%83%81-%EC%8A%A4%ED%8A%B8%EB%A6%AC%EB%B0%8D-4.-%EB%B6%84%ED%95%A0-%EC%97%85%EB%A1%9C%EB%93%9C-tus-protocol
https://github.com/haerong22/blog
https://github.com/haerong22/blog/tree/main/upload