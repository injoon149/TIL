## AWS의 이벤트 처리
1. SQS와 Lambda를 이용한 처리
- 이벤트가 SQS 대기열에 삽입되고, 람다 서비스가 SQS 대기열을 풀링한다.
2. Fan out 패턴
- 다중 SQS 대기열에 데이터를 전송하는 방식
- SQS 대기열과 애플리케이션 사이에 SNS 주제를 넣는다. SQS 대기열은 SNS 주제의 구독자가 되고, SNS 주제에
메시지를 전송할 때마다 메시지가 모든 SQS 대기열에 전달된다.
3. S3 이벤트 알림
- Amazon S3 버킷이 특정 이벤트에만 반응하도록 설정할 수 있어 객체 생성, 복원 등을 할 때 알림.
- 사용 사례: Amazon S3에 업로드된 이미지의 썸네일 생성.

## AWS의 고성능 컴퓨팅(HPC)
- 많은 리소스를 즉각적으로 생산할 수 있다. 작업 완료되면 사용량만큼만 비용을 지불한다.
- 사용 사례: 유전체학, 기상 예측, 머신러닝 등
- 1. 데이터 관리 방식
- - AWS Direct Conncect, Snowball, SnowMobile, AWS DataSync
- 2. 컴퓨팅과 네트워킹
- - EC2 인스턴스, 스팟 풀릿, EC2 Enhanced Networking, EFA(Elastic Fabric Adapter)
- 3. 스토리지
- - EBS, 인스턴스 스토어, Amazon S3, Amazon EFS, FSx for Lustre
- 4. 자동화
- - AWS Batch, AWS ParallelCluster(HPC를 AWS에 배포)

## SSM 기타 서비스
1. 명령 실행 : 모든 업데이트를 SNS로 보낼 수 있다. SSM 에이전트로 관리를 통해 EC2 인스턴스를 쉽게 관리할 수 있다.
2. 패치 관리자: 인스턴스 관리 과정 패칭을 자동화하는 데에 사용된다. 운영체제 및 보안 업데이트 적용
3. 유지 관리 기간: 인스턴스에서 작업을 수행할 일정을 정의하는 데에 사용된다.

## AWS Trusted Advisor
- AWS 계정에 대한 전체적인 평가를 제공하고, 5가지 범주에 대한 권고를 제공한다.
- 5가지 범주 : `비용 최적화, 성능, 보안, 고장 내성, 서비스 한도`
- Business&Enterprise는 AWS Support API를 이용해서 프로그램적으로 액세스할 수 있다.

## 문제 
- 다중 계층 애플리케이션. 동일한 데이터 세트를 계속 반환하라는 호출 때문에 성능 저하. => Amazon Elasticache를 구현하여
대규모 데이터 세트를 캐싱한다.
- Global Accelerator : CloudFront와 달리 애플리케이션의 상태 확인을 기반으로 트래픽 리디렉션을 수행한다.

## Aurora
1. Aurora 복제본 - 오토 스케일링 : 읽기 요청 많아져 리더 엔드포인트 확장.
2. Aurora - Custom Endpoint(사용자 지정 엔드포인트) : 더 강력한 인스턴스들이 분석적 쿼리를 담당하는 게 더
낫기 때문에 생김.
3. Aurora 서버리스 : 자동화된 데이터베이스 예시화와 실제 사용량에 따른 오토 스케일링을 제공한다. 예측 불가능한
사용량에 대응한다.
4. Global Aurora : Aurora 교차 리전 읽기 전용 복제본보다 추천되는 방식이다.
- 모든 읽기와 쓰기가 일어나는 하나의 기본 리전이 있고, 최대 5개의 보조 읽기 전용 리전을 만들 수 있다.
- 읽기 업무량에 따른 DB 사용량 낮다.
5. Aurora - 기기학습 : SQL 인터페이스를 통해 응용프로그램에 기계 학습 기반의 예측을 추가할 수 있다.
Aurora와 다른 기계 학습 간의 통합.

## SNS + SQS -> 팬 아웃 패턴
- 교차 리전 배송 : 한 리전의 SNS 토픽에 따른 리전의 SQS 대기열에 메시지를 보낼 수 있다.
- SNS - 메시지 필터링 : SNS 토픽 구독자들에게 전송할 메시지를 필터링하는 JSON 정책. 모든 주문이 아닌, 발주된
주문만을 SQS 대기열에 넣는다.

## Kinesis Data Firehose
- 생산자로부터 데이터를 가져올 수 있는 유용한 서비스.
- Amazon s3, amazon redshift, amazon opensearch로 데이터를 보낸다.
- spark, datadog, mongoDB 등으로 데이터를 보낼 수 있다.
- 커스텀 대상으로 http 엔드포인트에 보낼 수 있다. 완전 관리형, 자동 확장 가능.
- kinesis data stream은 대규모 데이터 수집을 위한 스트리밍 서비스로, 커스텀 코드 필요. 직접 스케일링을 다룬다.
- kinesis data firehose는 데이터 수집 서비스로, 데이터 스트림 가능. 완전관리형

## 람다 snapstart
- 람다 함수의 성능을 높이기 위한 람다의 기능.
- snapstart가 활성화되어 있으면 함수가 미리 초기화된 상태에서 호출이 된다.

## Aurora
- 스토리지와 컴퓨팅이 구분되어 있다.
- 스토리지에 문제가 있으면 자동 복구, 용량 예측할 수 없을 때 aurora 서버리스 사용.

## DocumentDB
- mongodb의 aurora 버젼. json 데이터를 정의한다. 완전히 관리되는 서비스, 자동 확장.

## Amazon Redshift
- 온라인 분석 처리 데이터베이스. Athena보다 쿼리 및 조건이 빠르다.
- 복잡한 쿼리 많고 조인 등이 있으면 Aurora보다 Redshift가 적합하다.
- Amazon Kinesis Data Firehose에서 IAM 역할을 통해 복사, JDBC 드라이버를 통해 EC2에서 복사, COPY 명령어
를 통해 S3에서 복사.

## Redshift Spectrum
- s3에 데이터가 있고 redshift를 통해 분석하고 싶지만, redshift로 로드하고 싶지 않을 때, 또는 더 많은 처리
능력을 위해 사용한다.

## VPC 용어
1. Bastion host : ssh에 들어갈 수 있었던 퍼블릭 ec2 인스턴스
2. NAT 인스턴스 : 퍼블릭 서브넷에 배포된 EC2 인스턴스인데, 프라이빗 서브넷의 EC2 인스턴스에게 인터넷 접근을 제공한다.
3. NAT 게이트웨이: 프라이빗 EC2 인스턴스에 확장 가능한 인터넷 접근을 제공한다.
4. NACL : 서브넷 레벨에서 인바운드와 아웃바운드 접근을 정의하는 방화벽 규칙.
5. VPC Flow Log : vpc 내 모든 패킷에 관련된 로그 레벨의 메타데이터.
6. Transit gateway : vpc, vpn, direct connect를 위한 전송 피어링 연결.