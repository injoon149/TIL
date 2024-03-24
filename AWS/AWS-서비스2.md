## S3 이벤트 알림
- IAM 권한이 필요하다.
- 이벤트 알림 타깃: SQS, SNS, 람다 함수 : 그곳레서 리소스 허용 정책을 명시해 주어야 한다.
- EventBridge 활용 가능 : 추가된 버킷에서 모든 이벤트를 EventBridge로 보낸다.

## S3의 기준 성능
1. Multi-part upload : 100MB가 넘는 파일에 대해 적용, 업로드를 병렬화하여 전송 속도를 
높여 대역폭을 최대화할 수 있다.
2. S3 전송 가속화 : 파일을 AWS Edge Location으로 전송해서 전송 속도를 높이고 데이터를 
대상 리전에 있는 S3 버킷으로 전달한다.
3. S3 Byte 범위 가져오기 : 파일에서 특정 바이트 범위를 가져와서 Get 요청을 병렬화한다.
다운로드 속도를 높이거나 파일의 일부만 검색할 때에 사용한다.

## S3 Select & Glacier Select
- S3 파일에서 검색할 때 검색한 후에 필터링하면 너무 많은 데이터를 검색(서버 측 필터링)
- Amazon S3 Select를 이용해서 클라이언트 측의 CPU 비용과 검색 비용을 감소시킨다.

## S3 Batch Operation
- 단일 요청으로 기존 S3 객체에서 대량 작업을 수행하는 서비스.
- 한 번에 많은 S3 객체의 메타데이터와 프로퍼티를 수정할 수 있고, 배치 작업으로 S3 버킷 간에 객체를 복사할 수 있다.
- `S3 버킷 내 암호화되지 않은 모든 객체를 암호화할 수 있다.`
- `S3 Inventory라는 기능을 사용해 객체 목록을 가져오고, S3 Select를 통해 객체를 필터링할 수 있다.`
-  S3 Inventory -> S3 Select -> S3 Batch Operation

## Content Delivery Network(CDN)
- 웹사이트의 콘텐츠를 서로 다른 엣지 로케이션에 미리 캐싱하여 읽기 성능을 높인다.
- 전세계의 사용자들이 낮은 Latency로 접근하게 할 수 있고, 디도스 공격에서 보호할 수 있다.
- S3 버킷, ALB, EC2 인스턴스 등과 조합하여 사용.

## Amazon FSX
- AWS에서 완전 관리형 서비스로, 타사 고성능 파일 시스템을 실행시킨다.
- FSX에서 Lustre, Windows File Server를 실행시킬 수 있다.

## FSX의 파일 시스템 배포 옵션
1. 스크래치 파일 시스템 : 임시 스토리지로 데이터복제X, 기저 서버가 오작동하면 파일이 모두 유실된다. 그러나 성능은 좋음
=> `단기 처리 데이터에 쓰이며 데이터 복제가 없어 비용 최적화 방안에 사용.`
2. 영구 파일 시스템 : 장기 스토리지로 동일한 가용 영역에 데이터가 복제된다. 동일한 AZ 내에서만 복제된다. 
=> `민감한 데이터의 장기 처리 및 스토리지`

## Amazon FSX For NETAPP ONTAP
- 온프레미스 시스템의 ONTAP이나 NAS에서 실행 중인 워크로드를 AWS로 옮길 수 있다.
- 지정 시간 복제 기능 : 새 워크로드 등을 테스트할 때 유용하다.

## Amazon FSX For OpenZFS
- AWS의 관리형 OpenZFS 파일 시스템으로, 여러 버전에서의 NFS 프로토콜과 호환이 가능하다.
- ZFS에서 실행되는 워크로드를 내부적으로 AWS로 옮길 때 사용한다.
- 데이터 중복 제거기능X, 지정 시간 복제기능ㅇ

## AWS Storage Gateway
- 온프레미스 데이터와 클라우드 데이터 간의 다리 역할을 한다.
- ex) 재해 복구 - 백업 : 파일 액세스 시간 줄이기 위해 AWS Storage Gateway를 온프레미스 캐시로 사용.
- 종류는 S3 File Gateway, FSX File, Volume, Tape 등이 있다.
1. `S3 File Gateway`
- S3 파일 게이트웨이로 구성한 모든 버킷은 NFS 및 SMB 프로토콜을 사용하여 액세스할 수 있고,
사용된 데이터는 파일 게이트웨이에 캐시로 저장된다.
- 버킷에 액세스하려면 각 파일 게이트웨이마다 IAM 역할을 생성해야 한다.

2. `Amazon FSX File Gateway`
- 빈번하게 액세스하는 데이터에 대한 로컬 캐시가 가능하다.
- SMB Client과 Amazon FSX For Windows Server 간의 중간 다리 역할을 한다.

3. `볼륨 게이트웨이`
- 블록 스토리지로, Amazon S3가 백업하는 ISCSI 프로토콜을 사용한다.
- 볼륨이 EBS 스냅샷으로 저장되어, 필요에 따라 온프레미스 볼륨 복구 가능.
- 캐시 볼륨 : 최근 데이터 액세스 시 지연시간이 짧다.
- 저장 볼륨 : 전체 데이터 세트가 온프레미스에 있으며 주기적으로 Amazon S3를 백업한다.

4. `Tape Gateway`
- 백업에 물리적 테이프 대신 클라우드를 사용하여 데이터를 백업한다.
- ISCSI 프로토콜을 이용하여 백업한다.

## AWS Snowball Edge
- 테라바이트나 페타바이트 용량의 데이터를 AWS와 교환하기 위해 사용한다.
- Snowball Edge 안의 ㅇ니터페이스는 블록 스토리지나 Amazon S3 호환 객체 스토리지를 제공한다.
1. Snowball Edge Storage Optimized
- 80TB 용량의 하드디스크 용량이 제공되고 블록 볼륨이나 S3 호환 객체 스토리지로 사용한다.
2. Snowball Edge Compute Optimized
- 용량은 42TB 또는 28TB이고, 활용 사례로는 대규모 데이터의 클라우드 마이그레이션, 데이터센터 폐지, 재난 복구 등이 있다.

## AWS SnowCone
- Edge Computing, Storage, 데이터 전송에 사용된다.
- 오프라인으로 데이터를 전송하거나 AWS Datasync 서비스를 사용해서 데이터를 다시 AWS에 전송한다.

## Edge Computing
- Edge Location에서 데이터를 생성하는 중에 그 데이터를 처리하는 것
- 활용 사례: 데이터 전처리, 미디어의 사진 트랜스코딩
- SnowCone & Snowcone SSD 를 사용한다.

## AWS Transfer Family
- Amazon S3 또는 EFS의 안팎으로 데이터를 전송하려고 하는데 FTP 프로토콜만 사용하려는 경우
- FTP, FTPS, SFTP 프로토콜을 사용한다.

## Datasync
- 데이터를 동기화하여 이를 통해 대용량의 데이터를 한 곳에서 다른 곳으로 옮길 수 있다.
- 모든 Amazon S3나 스토리지 클래스에 동기화 가능, Amazon EFS로 네트워크 파일 시스템에 저장 가능, Amazon FSX 사용 가능.
- 파일 권한과 메타데이터 저장 기능이 있어서, 보안과 관련되어 NFS Posix 파일 시스템, 그리고 SMB 권한을 준수한다.
- `파일을 한 곳에서 다른 곳으로 옮길 때 이를 이용하여 파일의 메타데이터를 보존할 수 있다.`
- Datasync를 통해 서로 다른 AWS 스토리지 서비스 간 동기화도 가능하다.

## Amazon SQS 
1. 가시성 시각 타임아웃
- 기본적으로는 30초이다, 메시지가 받아지면 Visibillity Timeout 기간 동안 보이지 않게 된다.
- 애플리케이션의 상황에 맞게 이 시간 다시 설정 가능

2. Long Polling
- 소비자가 대기열에 메시지를 요청하는 데 대기열에 아무것도 없으면 메시지 도착을 기다린다 : Long Polling
- 지연 시간을 줄이고 SQS로 보내는 API 호출 숫자를 줄이기 위해서이다. wait time을 정할 수 있음.

3. SQS with ASG
- 쓰기 대상 데이터베이스에서 SQS를 버퍼로 사용한다.
- 모든 트랜잭션이 데이터베이스에 쓰이게 확신할 수 있고, 급격히 증가한 로드나 신속한 스케일링이 필요한 경우에 사용.

## Amazon SNS
- 해당 주제에 관련하여 구독자에게 주제에 관련된 메시지를 보낸다.

1. SNS + SQS = Fan Out
- SNS 주제에 메시지를 전송한 후 원하는 수의 SQS 대기열이 이 SNS 주제를 구독하게 한다.
- 리전 간 전달 가능, 장애 조치 가능

2. Kinesis data Firehose를 통해 SNS에서 Amazon S3로 직접 데이터를 전송할 수 있다.

3. 메시지 필터링
- SNS 주제를 구독할 때 전송되는 메시지를 필터링할 때 사용하는 JSON 정책

## Kinesis Data Stream
- 시스템에서 큰 규모의 데이터 흐름을 샤드를 통해 다루는 서비스
- 데이터 스트림으로 메시지르 전송하면 파티션 키가 추가되고 파티션 키가 같은 메시지는 같은 샤드로 
들어가게 되어 키를 기반으로 데이터를 정렬할 수 있다.
- Provisioned Mode를 통해 프로비저닝할 샤드 수를 정하고 수동으로 조정할 수 있고, On Demand 모드를 통해 용량 관리할 필요x
- IAM 정책을 사용하여 샤드를 생성하거나 샤드에서 읽어들이는 접근 권한을 제어한다.

## Kinesis Data Firehose
- 생산자에서 데이터를 가져올 수 있는 유용한 서비스
- 생산자 : Kinesis Data Stream, Amazon CloudWatch, AWS IOT, 람다함수
- AWS 수신처 : Amazon S3, Amazon Redshift, Amazon ElasticSearch
- 관리가 필요하지 않으며, 자동으로 용량 크기가 조정되고, 서버리스이므로 관리할 서버가 없다.
- 람다를 이용해 데이터 변환 가능하다.
- 사용처 -> Kinesis Data Stream, Amazon Cloudwatch, AWS IOT
- 수신처 -> Amazon S3, Amazon Redshift, Amazon ElasticSearch

## Amazon MQ
- 클라우드로 마이그레이트할 때 기존의 프로토콜을 사용하고 싶을 때 사용
- `RabbitMQ와 ActiveMQ 두 가지 기술을 위한 관리형 메시지 브로커 서비스`

## Amazon ECS(Elastic Container Service)
- AWS에서 컨테이너를 실행하면 ECS 클러스터에 ECS 태스크를 실행한다.

## Amazon Cognito
- 사용자에게 웹 및 모바일 앱과 상호 작용할 수 있는 자격 증명을 부여한다.
1. Cognito 사용자 풀
- 앱 사용자에게 가입 기능을 제공한다. API Gateway 및 애플리케이션 로드 밸런서와 통합된다.
2. Cognito Identity Pool
- 앱에 등록된 사용자에게 임시 자격 증명을 제공해서 일부 AWS 리소스에 직접 액세스할 수 있게 해 주고 Cognito 사용자 풀과 잘 통합된다.
- Cognito는 AWS 외부의 웹 과 모바일 앱 사용자가 대상이다. -> `수백 명의 사용자, 모바일 사용자, SAML을 통한 인증`

## Cognito 자격 증명 풀
- 사용자에게 자격 증명을 제공하지만 API Gateway나 애플리케이션 로드 밸런서를 통해 애플리케이션에 액세스하지 않고
임시 AWS 자격 증명을 사용해 AWS 계정에 직접 액세스한다.
- DynamoDB에 행 수준의 보안 적용 가능.

## Amazon ECS
1. EC2 시작 유형
- Amazon ECS가 여러 EC2 인스턴스로 구성, 인스턴스별로 각각 ECS Agent 실행해야 한다. 도커 컨테이너는 미리 프로비저닝한 Amazon EC2 인스턴스에 위치한다.
2. Fargate 시작 유형(서버리스)
- AWS에 도커 유형을 정의하는 데, 인프라를 프로비저닝하지 않아 관리할 EC2 인스턴스가 없다.

## ECS 태스크의 IAM Role
- ECS 에이전트만이 EC2 인스턴스 프로파일을 사용하며, 그 EC2 인스턴스 프로파일을 이용해 API 호출을 한다.
- 인스턴스가 저장된 ECS 서비스가 Cloudwatch 로그에 API 호출을 해서 컨테이너 로그를 보내고, ECR로부터 도커 이미지를 가져온다.

## ECS - Auto Scaling
- ECS 서비스의 CPU 사용률을 확장할 수 있고, 메모리 사용률, 그리고 ALB 관련 지표인 타겟당 요청 수를 확장할 수 있다.
- 특정 타겟을 추적하는 대상 추적 스케일링이나 단계 스케일링, 예약 스케일링이 있다.

## Amazon ECR(Elastic Container Registry)
- AWS에 도커 이미지를 저장하고 관리하는 데 사용된다.
- 이미지 비공개 또는 퍼블릭 저장 가능.
- ECR은 Amazon ECS와 통합됨. 이미지는 백그라운드에서 Amazon S3에 저장된다.
- `ECR 저장소에 여러 도커 이미지가 있는데 ECS 클러스터의 EC2 인스턴스에 이미지를 끌어오기 위해서는 EC2 인스턴스의 IAM 역할을 지정해야 한다.`



